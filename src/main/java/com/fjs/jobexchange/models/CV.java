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

    @Column(name = "avatar")
    private String avatar;

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

    @Column(name = "position_want")
    private String positionWant;

    @Column(name = "year_of_experience")
    private Integer yearOfExperience;

    @Column(name = "salary_want")
    private Integer salaryWant;

    @Column(name = "working_form")
    private String workingForm;

    @Column(name = "career_objective")
    private String careerObjective;

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

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_activity",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id"))
    private Set<Activity> activities;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_certificate",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "certificate_id"))
    private Set<Certificate> certificates;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_education",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "education_id"))
    private Set<Education> educations;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_experience",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "experience_id"))
    private Set<Experience> experiences;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_hobby",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "hoppby_id"))
    private Set<Hobby> hobbies;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "projectId",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "project_id"))
    private Set<Project> projects;

    @ManyToMany(cascade = CascadeType.PERSIST )
    @JoinTable(
            name = "cv_reference_persons",
            joinColumns = @JoinColumn(name = "cv_id"),
            inverseJoinColumns = @JoinColumn(name = "reference_person_id"))
    private Set<ReferencePerson> referencePeople;

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
