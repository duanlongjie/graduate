package cn.edu.haue.graduate.config;

import cn.edu.haue.graduate.security.AdminUserDetailsService;
import cn.edu.haue.graduate.utils.PasswordEncrypter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.annotation.Resource;

/**
 * Spring Security配置类
 * Created by 杨晋升 on 2018/5/30.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private AdminUserDetailsService adminUserDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(adminUserDetailsService).passwordEncoder(PasswordEncrypter.getPasswordEncoder());
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/static/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.authorizeRequests()
                .antMatchers("/admin/**").authenticated()
                .and()
                .formLogin().loginPage("/admin/login").permitAll()
                .defaultSuccessUrl("/admin/home")
                .and()
                .logout().logoutUrl("/admin/logout");
    }
}
