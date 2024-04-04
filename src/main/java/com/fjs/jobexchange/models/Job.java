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
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employer_id", nullable = false, referencedColumnName = "id")
    private Employer employer;

    @NotNull
    @Column(name = "duration", nullable = false)
    private Date duration;

    @NotNull
    @Column(name = "title", nullable = false)
    private String title;

    @NotNull
    @Column(name = "ageFrom", nullable = false)
    private Integer ageFrom;

    @NotNull
    @Column(name = "ageTo", nullable = false)
    private Integer ageTo;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Integer salary;
    @NotNull
    @Column(name = "experience", nullable = false)
    private Integer experience;

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
    @Column(name = "isPrivate", nullable = false)
    private boolean isPrivate;

    @NotNull
    @Column(name = "updatedAt", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (updatedAt == null) updatedAt = new Date().toInstant();
    }
}
