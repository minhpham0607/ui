package com.example.hrms.biz.request.repository;

import com.example.hrms.biz.request.model.Pagination;
import com.example.hrms.biz.request.model.criteria.PaginationCriteria;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class PaginationMapper {
    @PersistenceContext
    private EntityManager entityManager;

    public List<Pagination> findPaginated(PaginationCriteria criteria) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Pagination> cq = cb.createQuery(Pagination.class);
        Root<Pagination> root = cq.from(Pagination.class);
        cq.select(root);

        TypedQuery<Pagination> query = entityManager.createQuery(cq);
        query.setFirstResult(criteria.getPage() * criteria.getSize()); // Bắt đầu từ vị trí (page * size)
        query.setMaxResults(criteria.getSize()); // Số lượng phần tử trên mỗi trang
        return query.getResultList();
    }

    public long countTotalItems() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Pagination> root = cq.from(Pagination.class);
        cq.select(cb.count(root));
        return entityManager.createQuery(cq).getSingleResult();
    }
}
