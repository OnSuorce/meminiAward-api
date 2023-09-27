package it.mbdev.meminiaward.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class LoginModel {

    public LoginModel(String jwt){
        setJwtToken(jwt);
    }

    @JsonProperty("code")
    private String code;

    @JsonProperty("redirect_uri")
    private String redirectUri;

    @JsonProperty("jwt_token")
    private String jwtToken;
}
