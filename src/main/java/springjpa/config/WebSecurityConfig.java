package springjpa.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@EnableAutoConfiguration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @SuppressWarnings("jdcwarning")
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username,password, enabled from userss where username=?")
                .authoritiesByUsernameQuery("select username, role from userss_role where username=?")
                .passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests().antMatchers(
                                                    "/",
                                                    "/login" ,
                                                    "/registration",
                                                    "/register" ,
                                                    "/succ" ,
                                                    "/fail" ,
                                                    "/403"
                                                ).permitAll()
                .antMatchers("/main").hasRole("USER").anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/login").defaultSuccessUrl("/main").permitAll()
                .and()
                .logout().logoutSuccessUrl("/").permitAll();
        http.exceptionHandling().accessDeniedPage("/403");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring().antMatchers("/**/*.css" , "/**/*.js" , "/**/*.jpg" );
    }

}
