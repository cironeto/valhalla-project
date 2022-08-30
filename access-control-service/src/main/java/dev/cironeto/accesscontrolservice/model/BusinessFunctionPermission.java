package dev.cironeto.accesscontrolservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessFunction businessFunction;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Permission permission;
}
