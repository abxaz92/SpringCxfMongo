package local.david.service.common.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

/**
 * Created by [david] on 22.11.16.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    @Qualifier("accessDeniedHandler")
    AccessDeniedHandler accessDeniedHandler;

    @Autowired
    @Qualifier("authenticationEntryPoint")
    AuthenticationEntryPoint authenticationEntryPoint;

//    @Autowired
//    BasicAuthenticationFilter basicAuthenticationFilter;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/api/public/");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/api/**").authenticated();
        http.authorizeRequests().antMatchers("/private/**").authenticated();
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/api/logout")).logoutSuccessUrl("/");
        http.exceptionHandling().accessDeniedHandler(accessDeniedHandler);
        http.httpBasic().authenticationEntryPoint(authenticationEntryPoint);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED);
//        http.addFilter(basicAuthenticationFilter);
        http.csrf().disable();
    }
}