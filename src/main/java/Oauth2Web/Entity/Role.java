package Oauth2Web.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {

    Member("ROLE_MEMBER","회원"),
    ADMIN("ROLE_ADMIN","관리자");

    private final String key;
    private final String keyword;


}
