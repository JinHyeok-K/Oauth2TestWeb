package Oauth2Web.config;

import Oauth2Web.Service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {   // 모든 웹 페이지는 HTTP로부터 요청/ 응답
        http.authorizeHttpRequests().antMatchers("/").permitAll() // 모든페이지 접속 가능
            .and()
                .formLogin()
                .loginPage("/member/login")
                .loginProcessingUrl("/member/logincontroller")
                .failureUrl("/member/login/error")
                .usernameParameter("mid")
                .passwordParameter("mpassword")
            .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true)
            .and()
                .csrf()
                .ignoringAntMatchers("/member/logincontroller")
                .ignoringAntMatchers("/member/signupcontroller");
        //super.configure(http);
    }

    @Autowired
    private IndexService indexService; // Service package indexservice


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(indexService).passwordEncoder(new BCryptPasswordEncoder());
        //super.configure(auth);
    }
}
