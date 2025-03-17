package com.example.hrms.biz.request.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "pagination")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pagination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
}