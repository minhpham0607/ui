package com.example.hrms.biz.request.model.criteria;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaginationCriteria {
    private int page = 0;  // Mặc định trang đầu tiên (0-based index)
    private int size = 10; // Mặc định mỗi trang có 10 phần tử
}

