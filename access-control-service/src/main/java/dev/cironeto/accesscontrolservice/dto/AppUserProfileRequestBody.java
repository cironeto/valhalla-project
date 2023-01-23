package dev.cironeto.accesscontrolservice.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AppUserProfileRequestBody {

    @NotNull
    private UUID appUserId;
    @NotNull
    private Long profileID;
}