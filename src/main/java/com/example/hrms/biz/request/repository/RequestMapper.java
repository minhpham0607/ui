package com.example.hrms.biz.request.repository;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.biz.request.model.criteria.RequestCriteria;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RequestMapper {

    @Select("SELECT COUNT(request_id) FROM requests")
    int count(RequestCriteria criteria);

    @Select("SELECT " +
            "    r.request_id AS requestId, " +
            "    r.username AS username, " +
            "    r.department_id AS departmentId, " +
            "    r.request_type AS requestType, " +
            "    r.request_reason AS requestReason, " +
            "    r.request_status AS requestStatus, " +
            "    r.approver_username AS approverUsername, " +
            "    r.start_time AS startTime, " +
            "    r.end_time AS endTime, " +
            "    r.created_at AS createdAt, " +
            "    r.updated_at AS updatedAt, " +
            "    r.approved_at AS approvedAt " +
            "FROM " +
            "    requests r " +
            "WHERE " +
            "    (#{requestId} IS NULL OR r.request_id = #{requestId}) " +
            "    AND (#{username} IS NULL OR r.username = #{username}) " +
            "    AND (#{departmentId} IS NULL OR r.department_id = #{departmentId}) " +
            "    AND (#{requestType} IS NULL OR r.request_type = #{requestType}) " +
            "    AND (#{requestReason} IS NULL OR r.request_reason = #{requestReason}) " +
            "    AND (#{requestStatus} IS NULL OR r.request_status = #{requestStatus}) " +
            "    AND (#{approverUsername} IS NULL OR r.approver_username = #{approverUsername}) " +
            "    AND (#{startTime} IS NULL OR r.start_time >= #{startTime}) " +
            "    AND (#{endTime} IS NULL OR r.end_time <= #{endTime}) " +
            "ORDER BY " +
            "    r.request_id ASC " +
            "LIMIT #{pageSize} OFFSET #{offset}")
    List<Request> select(@Param("pageSize") int pageSize,
                         @Param("offset") int offset,
                         @Param("requestId") Long requestId,
                         @Param("username") String username,
                         @Param("departmentId") Long departmentId,
                         @Param("requestType") RequestTypeEnum requestType,
                         @Param("requestReason") String requestReason,
                         @Param("requestStatus") RequestStatusEnum requestStatus,
                         @Param("approverUsername") String approverUsername,
                         @Param("startTime") Date startTime,
                         @Param("endTime") Date endTime);

    @Select("SELECT * FROM requests WHERE request_id = #{requestId}")
    @Results({
            @Result(property = "requestId", column = "request_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "requestType", column = "request_type", javaType = RequestTypeEnum.class),
            @Result(property = "requestReason", column = "request_reason"),
            @Result(property = "requestStatus", column = "request_status", javaType = RequestStatusEnum.class),
            @Result(property = "approverUsername", column = "approver_username"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "approvedAt", column = "approved_at")
    })
    Request getRequestById(Long requestId);
    @Select("SELECT * FROM Requests WHERE request_type = #{requestType}")
    @Results({
            @Result(property = "requestId", column = "request_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "requestType", column = "request_type", javaType = RequestTypeEnum.class),
            @Result(property = "requestReason", column = "request_reason"),
            @Result(property = "requestStatus", column = "request_status", javaType = RequestStatusEnum.class),
            @Result(property = "approverUsername", column = "approver_username"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "approvedAt", column = "approved_at")
    })
    List<Request> getRequestsByType(RequestTypeEnum requestType);

    @Select("SELECT * FROM Requests WHERE request_status = #{requestStatus}")
    @Results({
            @Result(property = "requestId", column = "request_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "requestType", column = "request_type", javaType = RequestTypeEnum.class),
            @Result(property = "requestReason", column = "request_reason"),
            @Result(property = "requestStatus", column = "request_status", javaType = RequestStatusEnum.class),
            @Result(property = "approverUsername", column = "approver_username"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "approvedAt", column = "approved_at")
    })
    List<Request> getRequestsByStatus(RequestStatusEnum requestStatus);

    @Select("SELECT * FROM Requests WHERE created_at BETWEEN #{startDate} AND #{endDate}")
    @Results({
            @Result(property = "requestId", column = "request_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "departmentId", column = "department_id"),
            @Result(property = "requestType", column = "request_type", javaType = RequestTypeEnum.class),
            @Result(property = "requestReason", column = "request_reason"),
            @Result(property = "requestStatus", column = "request_status", javaType = RequestStatusEnum.class),
            @Result(property = "approverUsername", column = "approver_username"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "approvedAt", column = "approved_at")
    })
    List<Request> getRequestsByCreatedAtRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

}