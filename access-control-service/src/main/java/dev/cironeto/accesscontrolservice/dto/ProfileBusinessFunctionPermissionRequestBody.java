package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileBusinessFunctionPermissionRequestBody {

    @NotNull
    private Long profileId;
    @NotNull
    private Long businessFunctionPermissionId;
}