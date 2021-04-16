package com.springboot.lombok.config;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import com.springboot.lombok.service.UserService;


@EnableWebSecurity // Security 사용
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	  private final UserService userService;

	  @Override
	  public void configure(WebSecurity web) { // static 하위 파일 목록(css, js, img) 인증 무시
	    web.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
	  }

	  @Override
	  protected void configure(HttpSecurity http) throws Exception { // http 관련 인증 설정
	    http
	          .authorizeRequests() // 접근 인증 설정
	            .antMatchers("/login", "/signup", "/user").permitAll() // 전체 접근 허용
	            .antMatchers("/").hasRole("USER") // USER + ADMIN 접근 가능
	            .antMatchers("/admin").hasRole("ADMIN") // ADMIN 접근 가능
	            .anyRequest().authenticated() // 다른 요청 권한의종류 상관 없이 권한 있어야 접근 가능
	        .and()
	          .formLogin() // 로그인 설정
	            .loginPage("/login") // 로그인 페이지 링크
	            //.defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
	            .defaultSuccessUrl("/loginmain") // 로그인 성공 후 리다이렉트 주소
	        .and()
	          .logout() // 로그아웃
	            .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
	            .invalidateHttpSession(true) // 세션 날리기
	    ;
	  }

	  @Override
	  public void configure(AuthenticationManagerBuilder auth) throws Exception { // 필요한 정보들을 가져오는 곳
	    auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder()); // 해당 서비스(userService)에서는 UserDetailsService를 implements해서 loadUserByUsername() 구현해야함 (서비스 참고)
	  }
	

}
