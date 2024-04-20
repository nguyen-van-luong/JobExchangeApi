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
public class CVHobbyId implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567890123666789L;

    @NotNull
    @Column(name = "cv_id", nullable = false)
    private Integer cvId;

    @NotNull
    @Column(name = "hoppby_id", nullable = false)
    private Integer hobbyId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CVHobbyId entity = (CVHobbyId) o;
        return Objects.equals(this.hobbyId, entity.hobbyId) &&
                Objects.equals(this.cvId, entity.cvId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hobbyId, cvId);
    }
}
