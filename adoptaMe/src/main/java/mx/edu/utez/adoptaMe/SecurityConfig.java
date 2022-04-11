package mx.edu.utez.adoptaMe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import mx.edu.utez.adoptaMe.security.SimpleAuthenticationSuccessHandler;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true,jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    private SimpleAuthenticationSuccessHandler successHandler;
    
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
           .ignoring()
           .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
    	
    	
        http
            .authorizeRequests()
            .antMatchers("/", "/imagenes/**", "login", "/logout", "/inicio", "/mascotas", "/usuarios/registro", "/usuarios/guardar", "/noticias").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin().successHandler(successHandler).loginPage("/login").permitAll()
//            .loginPage("/login").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID");

    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

       /*@Bean
    @Override
    protected UserDetailsService userDetailsService() {

        UserDetails userAdmin = User.withDefaultPasswordEncoder()
                .username("admin@localhost.com")
                .password("Uno23456")
                .roles("ADMIN")
                .build();
        return new InMemoryUserDetailsManager(userAdmin);
    }*/



}
