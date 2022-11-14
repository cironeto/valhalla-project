package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.Profile;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProfileResponseBody {

    private Long id;
    private String name;

    public ProfileResponseBody(Profile entity){
        this.id = entity.getId();
        this.name = entity.getName();
    }
}
