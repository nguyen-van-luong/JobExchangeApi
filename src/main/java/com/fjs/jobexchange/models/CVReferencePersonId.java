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
public class CVReferencePersonId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567890123466389L;

    @NotNull
    @Column(name = "cv_id", nullable = false)
    private Integer cvId;

    @NotNull
    @Column(name = "reference_person_id", nullable = false)
    private Integer referencePersonId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CVReferencePersonId entity = (CVReferencePersonId) o;
        return Objects.equals(this.referencePersonId, entity.referencePersonId) &&
                Objects.equals(this.cvId, entity.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(referencePersonId, cvId);
    }
}
