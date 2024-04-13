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
@Table(name = "cvs")
public class CV {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    private Student student;

    @NotNull
    @Column(name = "fullname", nullable = false)
    private String fullname;

    @NotNull
    @Column(name = "birthday", nullable = false)
    private Date birthday;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "province_id", nullable = false, referencedColumnName = "id")
    private Province province;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "sex")
    private Boolean sex;

    @NotNull
    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "position_current")
    private String positionCurrent;

    @Column(name = "position_want")
    private String positionWant;

    @NotNull
    @Column(name = "degree", nullable = false)
    private String degree;

    @Column(name = "experience")
    private String experience;

    @Column(name = "salary_want")
    private Integer salaryWant;

    @Column(name = "working_form")
    private String workingForm;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_skill",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_industry",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "industry_specialization_id"))
    private Set<IndustrySpecialization> industrySpecializations;

    @Column(name = "description")
    private String description;

    @Column(name = "work_experience")
    private String workExperience;

    @NotNull
    @Column(name = "isPrivate", nullable = false)
    private boolean isPrivate;

    @NotNull
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    @PrePersist
    public void prePersist() {
        if (updatedAt == null) updatedAt = new Date().toInstant();
    }
}
