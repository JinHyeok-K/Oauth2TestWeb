package Oauth2Web.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//@RequiredArgsConstructor
@AllArgsConstructor
@Getter
public enum Role {
    // 열거형 : Member[0], ADMIN[1]
    Member("ROLE_MEMBER","회원"), // Member 의  key, keyword 설정
    ADMIN("ROLE_ADMIN","관리자");//  ADMIN 의  key, keyword 설정

    private final String key; // 데이터 고정 선언 (key, keyword)
    private final String keyword;


}
