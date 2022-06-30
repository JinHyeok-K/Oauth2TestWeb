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

@Service
public class IndexService implements UserDetailsService {
                                    // 1. UserDetailsService : 커스텀 로그인


    @Autowired
    private MemberRepository memberRepository;

    // 1. 회원가입
    public void signup(MemberDto memberDto){
        System.out.println("회원가입 id :"+ memberDto.toString());
        memberRepository.save(memberDto.toentity());

    }

    // 2. 로그인
    // 패스워드 검증 안함  -> security, passwordEncoder에서
    @Override
    public UserDetails loadUserByUsername(String mid) throws UsernameNotFoundException {

       MemberEntity memberEntity = memberRepository.findByMid(mid).get();

       List<GrantedAuthority> authorities = new ArrayList<>();

        authorities.add( new SimpleGrantedAuthority(memberEntity.getRole().getKey()));

        //@Builder 사용 시
//        LoginDto loginDto = LoginDto.builder()
//                .mid(memberEntity.getMid())
//                .mpassword(memberEntity.getMpassword())
//                .authorities(Collections.unmodifiableSet(new LinkedHashSet<>(authorities)))
//                .build();

        LoginDto loginDto = new LoginDto( memberEntity.getMid()
                                        ,memberEntity.getMpassword()
                                        ,authorities);
     //  System.out.println("유저네임 :" +username);

        return loginDto;
    }
}
