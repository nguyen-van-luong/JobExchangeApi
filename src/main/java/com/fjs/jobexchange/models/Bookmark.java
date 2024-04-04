package com.fjs.jobexchange.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "bookmarks")
public class Bookmark {

    @EmbeddedId
    private FollowId id;

    @MapsId("studentId")
    @OneToOne
    @JoinColumn(name = "student_id", nullable = false, referencedColumnName = "id")
    private Student studentId;

    @MapsId("jobId")
    @ManyToOne
    @JoinColumn(name = "job_id", nullable = false, referencedColumnName = "id")
    private Job job;

    @NotNull
    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;
}
