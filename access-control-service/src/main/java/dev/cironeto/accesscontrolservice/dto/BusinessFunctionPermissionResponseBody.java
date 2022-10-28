package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class BusinessFunctionPermissionResponseBody {

    private Long businessFunctionPermissionCreatedId;

    public BusinessFunctionPermissionResponseBody(Long id){
        this.businessFunctionPermissionCreatedId = id;
    }
}
