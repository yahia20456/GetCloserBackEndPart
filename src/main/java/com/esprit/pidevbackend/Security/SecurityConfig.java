package com.esprit.pidevbackend.Security;
import com.esprit.pidevbackend.Filter.CustomAuthenticationFilter;
import com.esprit.pidevbackend.Filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(
         securedEnabled = true,
        // jsr250Enabled = true,
        prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService ;
    private final BCryptPasswordEncoder bCryptPasswordEncoder ;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
       auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers( "/api/login/**","/api/token/refresh/**","/api/forgot-password/**","/api/reset-password/**","/timeTable/**","/","/api/update-user/**","/profile/**","/api/delete-role/**","/api/delete-user/**","/api/role-user/**","/api/images/**","/api/user/**","/api/user-name/**","/api/getToken","/commentPub/addCommentToPubAndUser/**","/api/user/save/**","/api/role/save","/api/role/addtouser/**","/ConseilUser/**","/QVT/**","/Answer/**"
                ,"/comment/**","/event/**","/commentPub/**").permitAll();
        http.authorizeRequests().antMatchers( "/Evaluation/Add-AchievementsAndEvaluation/**",
                "/Evaluation/Increment-ActivityPointsLike/**","/Evaluation/Increment-ActivityPointsComment/**",
                "/Evaluation/Decrement-ActivityPointsUnlike/**","/Evaluation/Decrement-ActivityPointsUncomment/**",
                "/Evaluation/Increment-QuizPoints/**","/Evaluation/Increment-GamesPoints/**",
                "/Evaluation/Affect-ActivityBadge/**","/Evaluation/Calcul-Score/**","/send-message",
                "/Evaluation/Resetkeudos","/Evaluation/Decrement-Keudos/**","/Evaluation/retrieveAllachievements",
                "/Evaluation/retrieve-AchievementsById/**","/Evaluation/deleteAchievements/**","/Evaluation/Update-Achievements/**",
                "/game/start/**","/game/connect/**","/game/gameplay").permitAll();
        //http.authorizeRequests().antMatchers(GET ,"/api/user/**").hasAnyAuthority("ROLE_USER","ROLE_ADMIN");
       // http.authorizeRequests().antMatchers(POST ,"/api/user/save/**").hasAnyAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(  "/admin/**").permitAll();
        http.authorizeRequests().antMatchers(  "/rooms/**","/competitions/**","/timeslots/**","/timeTable/solve/**","/timeTable/stopSolving/**","/api/delete-user-role/**").permitAll();
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter) ;
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);



    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws  Exception {
        return super.authenticationManagerBean();
    }




}
