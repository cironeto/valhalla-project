package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@ToString
public class BusinessFunctionResponseBody {

    private Long businessFunctionCreatedId;

    public BusinessFunctionResponseBody(Long id){
        this.businessFunctionCreatedId = id;
    }
}
