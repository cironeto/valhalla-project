package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionRequestBody {

    @NotBlank
    private String applicationName;
    @NotBlank
    private String functionName;

    public BusinessFunctionRequestBody(BusinessFunction entity){
        this.applicationName = entity.getApplicationName();
        this.functionName = entity.getFunctionName();
    }
}
