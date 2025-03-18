package com.example.hrms.biz.request.model.criteria;

import com.example.hrms.common.http.criteria.Page;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestCriteria extends Page {
    private Long requestId;
    private String username;
    private Long departmentId;
    private RequestTypeEnum requestType;
    private String requestReason;
    private RequestStatusEnum requestStatus;
    private String approverUsername;
    private Date startTime;
    private Date endTime;

    public RequestCriteria() {}

    public RequestCriteria(Long requestId, String username, Long departmentId, RequestTypeEnum requestType,
                           String requestReason, RequestStatusEnum requestStatus, String approverUsername,
                           Date startTime, Date endTime ){
        this.requestId = requestId;
        this.username = username;
        this.departmentId = departmentId;
        this.requestType = requestType;
        this.requestReason = requestReason;
        this.requestStatus = requestStatus;
        this.approverUsername = approverUsername;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
