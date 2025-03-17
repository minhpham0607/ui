package com.example.hrms.biz.request.controller.Rest;
import com.example.hrms.biz.request.model.criteria.PaginationCriteria;
import com.example.hrms.biz.request.model.dto.PaginationDTO;
import com.example.hrms.biz.request.service.PaginationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pagination")
@RequiredArgsConstructor
public class PaginationRestController {
    private final PaginationService paginationService;

    @GetMapping
    public List<PaginationDTO> getPaginations(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        PaginationCriteria criteria = new PaginationCriteria();
        criteria.setPage(page);
        criteria.setSize(size);
        return paginationService.getPaginations(criteria);
    }
}
