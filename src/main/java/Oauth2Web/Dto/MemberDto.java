package Oauth2Web.Dto;

import Oauth2Web.Entity.MemberEntity;
import Oauth2Web.Entity.Role;
import lombok.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Getter @Setter @NoArgsConstructor
@AllArgsConstructor @ToString
public class MemberDto {
    private int mno;
    private String mid;
    private String mpassword;


    public MemberEntity toentity(){

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        return MemberEntity.builder()
                .mid(this.mid)
                .mpassword(encoder.encode(this.mpassword))
                .role(Role.Member)
                .build();
    }

}
