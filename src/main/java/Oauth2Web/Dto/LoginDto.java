package Oauth2Web.Dto;

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Builder
public class LoginDto implements UserDetails { // UserDetails interface 상속

    private String mid; // id 선언
    private String mpassword; // password 선언
    private Set<GrantedAuthority> authorities; // 부여된 인증들의 권한 선언

    public  LoginDto(String mid, String mpassword,Collection<? extends GrantedAuthority> authorities){
        this.mid = mid; // 인수로 받은 해당 mid
        this.mpassword = mpassword; // 인수로 받은 해당 mpassword
        this.authorities = Collections.unmodifiableSet(new LinkedHashSet<>(authorities)); // 인수로 받은 해당 권한(role)

    }


    @Override // 인터페이스 상속으로 인한 재정의
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override  // 인터페이스 상속으로 인한 재정의
    public String getPassword() {
        return this.mpassword;
    } // 비밀번호 반환

    @Override  // 인터페이스 상속으로 인한 재정의
    public String getUsername() {
        return this.mid;
    } // id 반환

    @Override  // 인터페이스 상속으로 인한 재정의
    public boolean isAccountNonExpired() {
        return true;
    } // 회원 계정(account ) 인증 만료 확인 [사용 가능 true]

    @Override  // 인터페이스 상속으로 인한 재정의
    public boolean isAccountNonLocked() {
        return true;
    } // 회원 계정(account ) 잠금 확인  [사용가능 true]

    @Override  // 인터페이스 상속으로 인한 재정의
    public boolean isCredentialsNonExpired() {
        return true;
    }  // 회원 계정(account )의 비밀번호 만료 확인 [사용 가능 true]

    @Override  // 상속으로 인한 재정의
    public boolean isEnabled() {
        return true;
    }  // 회원 계정(account )의  사용 가능 확인 [사용 가능 true]
}
