package dev.cironeto.accesscontrolservice.dto;

import dev.cironeto.accesscontrolservice.model.AppUserProfile;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserProfileResponseBody {

    private Long id;
    private String appUserEmail;
    private String permissionName;

    public AppUserProfileResponseBody(AppUserProfile entity){
        this.id = entity.getId();
        this.appUserEmail = entity.getAppUser().getEmail();
        this.permissionName = entity.getProfile().getName();
    }
}
