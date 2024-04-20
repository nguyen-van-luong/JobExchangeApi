package com.fjs.jobexchange.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.Hibernate;

import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class CVExperienceId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567800123466789L;

    @NotNull
    @Column(name = "cv_id", nullable = false)
    private Integer cvId;

    @NotNull
    @Column(name = "experience_id", nullable = false)
    private Integer experienceId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CVExperienceId entity = (CVExperienceId) o;
        return Objects.equals(this.experienceId, entity.experienceId) &&
                Objects.equals(this.cvId, entity.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(experienceId, cvId);
    }
}
