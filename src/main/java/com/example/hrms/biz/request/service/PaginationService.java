package com.example.hrms.biz.request.service;
import com.example.hrms.biz.request.model.criteria.PaginationCriteria;
import com.example.hrms.biz.request.model.Pagination;
import com.example.hrms.biz.request.model.dto.PaginationDTO;
import com.example.hrms.biz.request.repository.PaginationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaginationService {
    private final PaginationMapper paginationMapper;

    public List<PaginationDTO> getPaginations(PaginationCriteria criteria) {
        List<Pagination> paginations = paginationMapper.findPaginated(criteria);
        return paginations.stream()
                .map(p -> new PaginationDTO(p.getId(), p.getTitle(), p.getDescription()))
                .toList();
    }
}
