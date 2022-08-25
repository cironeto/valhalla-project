package dev.cironeto.accesscontrolservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "applicationName", "functionName" }) })
@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String applicationName;
    @NotBlank
    private String functionName;
}
