package dev.cironeto.accesscontrolservice.controller.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Path;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FieldMessage {

    private Path fieldName;
    private String message;
}
