package com.training.projectForum.config.security;

import com.training.projectForum.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@Profile("prod")
public class securityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    AuteinticacaoService auteinticacaoService;

    @Autowired
    TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Bean
    protected AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

       http.authorizeRequests()
               .antMatchers(HttpMethod.GET, "/topicos").permitAll()
               .antMatchers(HttpMethod.GET, "/topicos/*").permitAll()
               .antMatchers(HttpMethod.GET, "/usuarios").permitAll()
               .antMatchers(HttpMethod.POST, "/auth").permitAll()
               .antMatchers(HttpMethod.DELETE, "/topicos/*").hasRole("ADM")
               .anyRequest().authenticated()//resto do endpoint precisa de autenticação
               .and().csrf().disable()
               .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
               .and().addFilterBefore(new AutenticationTokenFilter(tokenService, usuarioRepository), UsernamePasswordAuthenticationFilter.class);


    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(auteinticacaoService).passwordEncoder(new BCryptPasswordEncoder());

    }


        @Override
        public void configure(WebSecurity web) throws Exception {
            web.ignoring()
                    .antMatchers("/**.html", "/v2/api-docs", "/webjars/**", "/configuration/**", "/swagger-resources/**");
        }


}
