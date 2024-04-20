package com.fjs.jobexchange.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "educations")
public class Education {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "student_id", nullable = false)
    private Integer studentId;

    @NotNull
    @Column(name = "degree", nullable = false)
    private String degree;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "industry_specialization_id", nullable = false, referencedColumnName = "id")
    private IndustrySpecialization industrySpecialization;

    @NotNull
    @Column(name = "period", nullable = false)
    private String period;

    @NotNull
    @Column(name = "score", nullable = false)
    private float score;
}
