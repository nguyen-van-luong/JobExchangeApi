package com.fjs.jobexchange.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "jobs")
public class Job  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "employer_id", nullable = false, referencedColumnName = "id")
    private Employer employer;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Date duration;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "age_from")
    private Integer ageFrom;

    @NotNull
    @Column(name = "age_to", nullable = false)
    private Integer ageTo;

    @Column(name = "salary_from")
    private Integer salaryFrom;

    @Column(name = "salary_to")
    private Integer salaryTo;

    @Column(name = "sex")
    private Boolean sex;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "degree")
    private String degree;

    @Column(name = "working_form")
    private String workingForm;

    @NotNull
    @Column(name = "experience", nullable = false)
    private String experience;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;
    @NotNull
    @Column(name = "requirement", nullable = false)
    private String requirement;


    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "job_addresses",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "address_id"))
    private Set<Address> addresses;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "job_industries",
            joinColumns = @JoinColumn(name = "job_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_specialization_id"))
    private Set<IndustrySpecialization> industrySpecializations;

    @NotNull
    @Column(name = "is_private", nullable = false)
    private Boolean isPrivate;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (updatedAt == null) updatedAt = new Date().toInstant();
    }
}
