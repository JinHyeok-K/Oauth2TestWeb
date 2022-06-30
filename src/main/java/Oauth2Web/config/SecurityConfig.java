package Oauth2Web.config;

import Oauth2Web.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration // 설정 파일을 만들기 위한 어노테이션
public class SecurityConfig extends WebSecurityConfigurerAdapter { // WebSecurityConfigurerAdapter 상속(웹 보안)

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }

    @Override // 재 정의 (이미 구현 된(상속 받은) 메소드 재 정의)
    protected void configure(HttpSecurity http) throws Exception {   // Http(url)관련 시큐리티 보안 |모든 웹 페이지는 HTTP로부터 요청/ 응답
        http // http
            .authorizeHttpRequests().antMatchers("/").permitAll() // 인증이 없어도 요청 가능   = 모든 접근 허용
            .and()
                .formLogin() // 로그인 페이지 보안 설정
                .loginPage("/member/login") // 로그인을 위한 데이터(ID/ PW) 를 입력 받을 page url
                .loginProcessingUrl("/member/logincontroller") // 로그인을 처리할 URL 정의 :loadUserByUsername
                .failureUrl("/member/login/error") // 실패 시 이동할 url
                .usernameParameter("mid") // 로그인 시 아이디로 입력 받을 변수 명
                .passwordParameter("mpassword") // 로그인 시 비밀번호로 입력 받을 변수 명
            .and()
                .logout() //로그 아웃
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) // 로그아웃을 처리할 url
                .logoutSuccessUrl("/") // 로그 아웃 성공 시 이동할 url -> main page(최상위)
                .invalidateHttpSession(true) //세션 초기화
            .and()
                .csrf() //서버에게 요청 할 수 있는 페이지 제한
                .ignoringAntMatchers("/member/logincontroller") // 로그인 페이지 제한 해제
                .ignoringAntMatchers("/member/signupcontroller"); // 회원가입 페이지 제한 해제
        //super.configure(http);
    }

    @Autowired //자동 빈 생성 [ 자동 생성자 이용한 객체에 메모리 할당 ]
    private IndexService indexService; // indexService 객체 메모리 할당  Service package indexservice


    @Override  // 재 정의 (이미 구현 된(상속 받은) 메소드 재 정의) , 인증(로그인) 관리 메소드
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(indexService).passwordEncoder(new BCryptPasswordEncoder());
            // 인증할 서비스 객체                 // 패스워드 인코딩( BCrypt 객체 )
        //super.configure(auth);
    }
}
