package com.fjs.jobexchange.models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "follows")
public class Follow {

    @EmbeddedId
    private FollowId id;

    @MapsId("studentId")
    @OneToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student studentId;

    @MapsId("employerId")
    @ManyToOne
    @JoinColumn(name = "employer_id", referencedColumnName = "id")
    private Employer employer;
}
