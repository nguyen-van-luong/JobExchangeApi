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
public class JobIndustryId implements Serializable {

    @Serial
    private static final long serialVersionUID = -3995321831031202909L;

    @NotNull
    @Column(name = "job_id", nullable = false)
    private Integer jobId;

    @NotNull
    @Column(name = "industry_specialization_id", nullable = false)
    private Integer industrySpecializationId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        JobIndustryId entity = (JobIndustryId) o;
        return Objects.equals(this.industrySpecializationId, entity.industrySpecializationId) &&
                Objects.equals(this.jobId, entity.jobId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(industrySpecializationId, jobId);
    }

}
