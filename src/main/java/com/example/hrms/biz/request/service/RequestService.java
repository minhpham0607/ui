package com.example.hrms.biz.request.service;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.model.criteria.RequestCriteria;
import com.example.hrms.biz.request.model.dto.RequestDto;
import com.example.hrms.biz.request.repository.RequestMapper;
import com.example.hrms.common.http.criteria.Page;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@Service
public class RequestService {
    private final RequestMapper requestMapper;

    public RequestService(RequestMapper requestMapper) {
        this.requestMapper = requestMapper;
    }

    public int count(RequestCriteria criteria) {
        if (criteria == null) {
            criteria = new RequestCriteria();
        }
        log.info("Counting requests with criteria: {}", criteria);

        return requestMapper.count(
                criteria.getRequestId(),
                criteria.getUsername() ,
                criteria.getDepartmentId(),
                criteria.getRequestType(),
                criteria.getRequestReason(),
                criteria.getRequestStatus(),
                criteria.getApproverUsername(),
                criteria.getStartTime() ,
                criteria.getEndTime()
        );
    }


    public List<RequestDto.Resp> list(Page page, RequestCriteria criteria) {
        page.validate();
        if (criteria == null) {
            criteria = new RequestCriteria();
        }
        log.info("Fetching request list with criteria: {}", criteria);
        try {
            List<Request> requests = requestMapper.select(
                    page.getPageSize(),
                    page.getOffset(),
                    criteria.getRequestId(),
                    criteria.getUsername(),
                    criteria.getDepartmentId(),
                    criteria.getRequestType(),
                    criteria.getRequestReason(),
                    criteria.getRequestStatus(),
                    criteria.getApproverUsername(),
                    criteria.getStartTime(),
                    criteria.getEndTime()
            );

            log.info("Requests fetched from DB: {}", requests);  // ✅ Log kiểm tra dữ liệu

            return requests.stream()
                    .map(RequestDto.Resp::toResponse)
                    .peek(resp -> log.info("Mapped response: {}", resp))  // ✅ Log kiểm tra ánh xạ DTO
                    .toList();
        } catch (Exception e) {
            log.error("Error fetching request list", e);
            throw new RuntimeException("Could not fetch request list, please try again later.");
        }
    }


    public Request getRequestById(Long requestId) {
        if (requestId == null) {
            log.warn("Request ID is null, cannot fetch request.");
            throw new IllegalArgumentException("Request ID cannot be null");
        }
        return requestMapper.getRequestById(requestId);
    }

    public List<Request> getRequestsByType(RequestTypeEnum requestType) {
        try {
            return requestMapper.getRequestsByType(requestType);
        } catch (Exception e) {
            log.error("Error fetching requests by type: {}", requestType, e);
            throw new RuntimeException("Could not fetch requests by type, please try again later.");
        }
    }

    public List<Request> getRequestsByStatus(RequestStatusEnum requestStatus) {
        try {
            return requestMapper.getRequestsByStatus(requestStatus);
        } catch (Exception e) {
            log.error("Error fetching requests by status: {}", requestStatus, e);
            throw new RuntimeException("Could not fetch requests by status, please try again later.");
        }
    }

    public List<Request> getRequestsByCreatedAtRange(Date startDate, Date endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        try {
            return requestMapper.getRequestsByCreatedAtRange(startDate, endDate);
        } catch (Exception e) {
            log.error("Error fetching requests by created date range: {} - {}", startDate, endDate, e);
            throw new RuntimeException("Could not fetch requests by created date range, please try again later.");
        }
    }
}
