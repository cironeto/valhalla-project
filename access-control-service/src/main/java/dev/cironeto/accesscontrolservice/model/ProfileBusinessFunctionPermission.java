package dev.cironeto.accesscontrolservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileBusinessFunctionPermission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private BusinessFunctionPermission businessFunctionPermission;
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    private Profile profile;
}
