package com.fjs.jobexchange.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "images")
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "extension", nullable = false)
    private String extension;

    @NotNull
    @Column(name = "status", nullable = false)
    private boolean status;

    @NotNull
    @Column(name = "createdAt", nullable = false)
    private LocalDate createdAt;
}
