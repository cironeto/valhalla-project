package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionPermissionRequestBody {

    @NotNull
    private Long businessFunctionId;
    @NotNull
    private Long permissionId;
}