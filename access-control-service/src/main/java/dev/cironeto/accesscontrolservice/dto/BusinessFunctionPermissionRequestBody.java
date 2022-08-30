package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionPermissionRequestBody {

    @NotEmpty
    private String applicationName;
    @NotEmpty
    private String functionName;
    @NotEmpty
    private String permission;
}