package dev.cironeto.accesscontrolservice.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Path;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StandardError {

    private Instant timestamp;
    private Integer status;
    private String error;
    private String message;
    private String path;
    private List<FieldMessage> errors = new ArrayList<>();

    public void addError(Path fieldName, String message){
        errors.add(new FieldMessage(fieldName, message));
    }

}
