package dev.cironeto.accesscontrolservice.validation;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

@RequiredArgsConstructor
public class BeanValidator {

    public static void validate(Object object){
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator() ;
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object);

        if (!constraintViolations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            constraintViolations.forEach(violation -> {
                sb.append("Invalid field: ");
                sb.append(violation.getMessage());
            });

            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Validation error: " + sb.toString());
        }
    }
}
