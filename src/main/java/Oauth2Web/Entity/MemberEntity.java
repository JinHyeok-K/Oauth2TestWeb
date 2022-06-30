package Oauth2Web.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity // 엔티티 영역 선언
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString @Builder
public class MemberEntity {
    @Id // 기본 키 생성(primary ke)
    @GeneratedValue(strategy = GenerationType.AUTO) //  pk 생성 방식 : 특정 선택
    private int mno; // 회원번호
    private String mid; // 회원 id
    private String mpassword; // 회원 비밀번호
    private Role role; // 회원 권한

}

