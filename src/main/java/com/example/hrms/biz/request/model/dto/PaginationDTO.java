package com.example.hrms.biz.request.model.dto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDTO {
    private Long id;
    private String title;
    private String description;
}
