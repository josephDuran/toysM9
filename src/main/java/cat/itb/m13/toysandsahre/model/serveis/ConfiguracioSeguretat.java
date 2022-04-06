package cat.itb.m13.toysandsahre.model.serveis;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class ConfiguracioSeguretat extends WebSecurityConfigurerAdapter {

//    private final ElMeuAuthenticationEntryPoint elmeuEntryPoint;
    private final ElMeuUserDetailsService elmeuUserDetailsService;
    private final PasswordEncoder xifrat;


    //Per fer proves al principi, per poder fer post i put d'usuaris sense seguretat
//    @Override
//    public void configure(WebSecurity web) {
//        web.ignoring().anyRequest();
//    }

    @Bean
    public PasswordEncoder xifrat() {
        return new BCryptPasswordEncoder();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .passwordEncoder(xifrat())
//                .withUser("joseph")
//                .password(xifrat().encode("joseph1234"))
//                .roles("ADMIN");
//
//
//
//    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(elmeuUserDetailsService).passwordEncoder(xifrat);
    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.cors() //amb aquesta línia evitem la configuració custom del cors en un fitxer a part
//                .and()
//                .httpBasic()
//                .authenticationEntryPoint(elmeuEntryPoint)
//                .and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.GET, "/me/**").hasRole("ADMIN") //per fer proves del forbidden
//                .antMatchers(HttpMethod.GET, "/users/**", "/videojocs/**").hasRole("USER")
//                .antMatchers(HttpMethod.POST, "/users/**", "/videojocs/**").hasRole("USER")
//                .antMatchers(HttpMethod.PUT, "/users/**").hasRole("USER")
//                .antMatchers(HttpMethod.DELETE, "/videojocs/**").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST, "/videojocs/**").hasAnyRole("USER", "ADMIN")
//                .anyRequest().authenticated();
//        // .and()
//        // .csrf().disable();
//    }
}
