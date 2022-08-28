package dev.cironeto.accesscontrolservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AccessTokenResponseBody {

    @JsonProperty("access_token")
    private String accessToken;
}
