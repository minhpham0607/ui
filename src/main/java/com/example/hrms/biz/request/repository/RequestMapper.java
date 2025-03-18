package com.example.hrms.biz.request.repository;

import com.example.hrms.biz.request.model.Request;
import com.example.hrms.enumation.RequestStatusEnum;
import com.example.hrms.enumation.RequestTypeEnum;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

@Mapper
public interface RequestMapper {

    @Select("""
    SELECT request_id, username, department_id, request_type, 
           request_reason, request_status, approver_username, 
           created_at, updated_at, approved_at 
    FROM requests 
    WHERE 
        (#{requestId} IS NULL OR request_id = #{requestId}) 
        AND (#{username} IS NULL OR username = #{username}) 
        AND (#{departmentId} IS NULL OR department_id = #{departmentId}) 
        AND (#{requestType} IS NULL OR request_type = #{requestType}) 
        AND (#{requestReason} IS NULL OR request_reason = #{requestReason}) 
        AND (#{requestStatus} IS NULL OR request_status = #{requestStatus}) 
        AND (#{approverUsername} IS NULL OR approver_username = #{approverUsername}) 
        AND (#{startTime} IS NULL OR created_at >= #{startTime}) 
        AND (#{endTime} IS NULL OR created_at <= #{endTime})
    ORDER BY created_at DESC
    LIMIT #{limit} OFFSET #{offset}
""")
    List<Request> select(
            @Param("limit") int limit,
            @Param("offset") int offset,
            @Param("requestId") Long requestId,
            @Param("username") String username,
            @Param("departmentId") Long departmentId,
            @Param("requestType") RequestTypeEnum requestType,
            @Param("requestReason") String requestReason,
            @Param("requestStatus") RequestStatusEnum requestStatus,
            @Param("approverUsername") String approverUsername,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    @Select("""
    SELECT COUNT(*) FROM requests
    WHERE 
        (request_id = #{requestId} OR #{requestId} IS NULL)
        AND (username LIKE CONCAT('%', #{username}, '%') OR #{username} IS NULL)
        AND (department_id = #{departmentId} OR #{departmentId} IS NULL)
        AND (request_type = #{requestType} OR #{requestType} IS NULL)
        AND (request_reason = #{requestReason} OR #{requestReason} IS NULL)
        AND (request_status = #{requestStatus} OR #{requestStatus} IS NULL)
        AND (approver_username = #{approverUsername} OR #{approverUsername} IS NULL)
        AND (created_at >= #{startTime} OR #{startTime} IS NULL)
        AND (created_at <= #{endTime} OR #{endTime} IS NULL)
""")
    int count(
            @Param("requestId") Long requestId,
            @Param("username") String username,
            @Param("departmentId") Long departmentId,
            @Param("requestType") RequestTypeEnum requestType,
            @Param("requestReason") String requestReason,
            @Param("requestStatus") RequestStatusEnum requestStatus,
            @Param("approverUsername") String approverUsername,
            @Param("startTime") Date startTime,
            @Param("endTime") Date endTime
    );

    @Select("SELECT * FROM requests WHERE request_id = #{requestId}")
    Request getRequestById(@Param("requestId") Long requestId);

    @Select("SELECT * FROM requests WHERE request_type = #{requestType}")
    List<Request> getRequestsByType(@Param("requestType") RequestTypeEnum requestType);

    @Select("SELECT * FROM requests WHERE request_status = #{requestStatus}")
    List<Request> getRequestsByStatus(@Param("requestStatus") RequestStatusEnum requestStatus);

    @Select("""
        SELECT * FROM requests 
        WHERE created_at BETWEEN #{startDate} AND #{endDate}
    """)
    List<Request> getRequestsByCreatedAtRange(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate
    );

}
