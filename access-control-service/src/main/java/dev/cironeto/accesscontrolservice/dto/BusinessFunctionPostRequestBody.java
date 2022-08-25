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
public class BusinessFunctionPostRequestBody {

    @NotBlank
    private String applicationName;
    @NotBlank
    private String functionName;

    public BusinessFunctionPostRequestBody(BusinessFunction entity){
        this.applicationName = entity.getApplicationName();
        this.functionName = entity.getFunctionName();
    }
}
