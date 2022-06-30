package Oauth2Web.Dto;

import Oauth2Web.Entity.MemberEntity;
import Oauth2Web.Entity.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
public class MemberDto {
    private int mno; // 회원 번호
    private String mid; // 회원 id
    private String mpassword; // 회원 비밀번호


    public MemberEntity toentity(){ // DTO -> entity

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(); // 비밀번호 변조용 객체 선언

        return MemberEntity.builder() // memberentity 반환
                .mid(this.mid) // 해당 mid
                .mpassword(encoder.encode(this.mpassword)) // 변조된 해당 비밀번호
                .role(Role.Member) // Role(권한) 부여
                .build(); //
    }

}
