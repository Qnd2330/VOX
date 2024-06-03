package VOX_Giat_La.Config;

import VOX_Giat_La.Filters.JwtTokenFilter;
import VOX_Giat_La.Models.Roles;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig  {
    private final JwtTokenFilter jwtTokenFilter;
    @Value("${api.prefix}")
    private String apiPrefix;
    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(requests -> {
                    requests
                            .requestMatchers(
                                    String.format("%s/user/register", apiPrefix),
                                    String.format("%s/user/login", apiPrefix)
                            )
                            .permitAll()
                            .requestMatchers(GET,String.format("%s/user/list/",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(DELETE,String.format("%s/user/delete**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(GET, String.format("%s/user/get**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)


                            .requestMatchers(GET, String.format("%s/bill**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/bill**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/bill**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(DELETE, String.format("%s/bill**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER)

                            .requestMatchers(GET, String.format("%s/bill_details**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/bill_details**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/bill_details**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(DELETE, String.format("%s/bill_details**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)

                            .requestMatchers(GET, String.format("%s/customerstorage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.USER,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/customerstorage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/customerstorage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/customerstorage**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/salary**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/salary**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PUT, String.format("%s/salary**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/salary**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/salarydetail**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(POST, String.format("%s/salarydetail**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PUT, String.format("%s/salarydetail**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/salarydetail**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/storage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/storage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/storage**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/storage**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/storestorage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/storestorage**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(PUT, String.format("%s/storestorage**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/storestorage**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/washing_method**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/washing_method**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PUT, String.format("%s/washing_method**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/washing_method**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                            .requestMatchers(GET, String.format("%s/workschedule**",apiPrefix)).hasAnyRole(Roles.ADMIN,Roles.EMPLOYEE)
                            .requestMatchers(POST, String.format("%s/workschedule**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(PUT, String.format("%s/workschedule**",apiPrefix)).hasAnyRole(Roles.ADMIN)
                            .requestMatchers(DELETE, String.format("%s/workschedule**",apiPrefix)).hasAnyRole(Roles.ADMIN)

                     .anyRequest().authenticated();

                });
        return http.build();

    }

}
