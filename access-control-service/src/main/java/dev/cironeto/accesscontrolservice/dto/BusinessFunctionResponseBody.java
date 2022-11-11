package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.BusinessFunction;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BusinessFunctionResponseBody {

    private Long id;
    private String applicationName;
    private String functionName;

    public BusinessFunctionResponseBody(BusinessFunction entity){
        this.id = entity.getId();
        this.applicationName = entity.getApplicationName();
        this.functionName = entity.getFunctionName();
    }
}
