package Oauth2Web.Entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@ToString @Builder
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int mno;
    private String mid;
    private String mpassword;
    private Role role;
}
