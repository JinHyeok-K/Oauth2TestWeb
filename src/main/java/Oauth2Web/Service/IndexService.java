package Oauth2Web.Service;

import Oauth2Web.Dto.LoginDto;
import Oauth2Web.Dto.MemberDto;
import Oauth2Web.Entity.MemberEntity;
import Oauth2Web.Entity.MemberRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;

@Service // 서비스 영역 선언 : 실제 로직 구현 (back-end)
public class IndexService implements UserDetailsService { //  UserDetailsService 상속(인터페이스) : implemets 는 재정의(override) 필요
                                    // 1. UserDetailsService : 커스텀 로그인


    @Autowired // memberRepository 객체 메모리 할당
    private MemberRepository memberRepository;

    // 1. 회원가입
    public void signup(MemberDto memberDto){ // memberDto 의 인수로 받는 회원가입 저리 메소드
        System.out.println("회원가입 id :"+ memberDto.toString()); // 콘솔 확인용
        memberRepository.save(memberDto.toentity()); // memberRepository 객체 내에 memberDto.toentity() 란 메소드의 데이터를 저장

    }

    // 2. 로그인
    // 패스워드 검증 안함  -> security, passwordEncoder에서
    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {


       MemberEntity memberEntity = memberRepository.findByMid(mid).get(); // 회원 아이디로 엔티티 찾기

       List<GrantedAuthority> authorities = new ArrayList<>(); // 찾은 회원 엔티티에 권한을 리스트에 담기
            // GrantedAuthority : 부여된 인증의 클래스
            // List<GrantedAuthority> : 부여된 인증들을 모아두기
        authorities.add( new SimpleGrantedAuthority(memberEntity.getRole().getKey()));
            // authorities 리스트에 인증된 엔티티의 키를 저장 ( memberEntity 의 role(권한) 의 키(key)값


        //@Builder 사용 시
//        LoginDto loginDto = LoginDto.builder()
//                .mid(memberEntity.getMid())
//                .mpassword(memberEntity.getMpassword())
//                .authorities(Collections.unmodifiableSet(new LinkedHashSet<>(authorities)))
//                .build();

        LoginDto loginDto = new LoginDto( memberEntity.getMid() // 로그인 때 사용 될 DTO 생성 ( memberEntity 의 mid, memberEntity 의 mpassword, memberEntity 의 authorities(권한) )
                                        ,memberEntity.getMpassword()
                                        ,authorities);
     //  System.out.println("유저네임 :" +username);

        return loginDto; // loginDTO 반환
    }
}
