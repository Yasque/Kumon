package com.miss;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
    @Autowired
    private UserDetailsService userDetailsService;
    
    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        //para la seguridad hay que agregar esto, recursos y vistas
        //todo esto es para saber a que vistas tendra acceso el usuario, ya logeado   
        	.authorizeRequests()
                .antMatchers("/", "/login").permitAll()
                .antMatchers("/", "/signin").permitAll()
                .antMatchers("/usuario/login").permitAll()
                 .antMatchers("/login*").permitAll()
                 .antMatchers("/index**").permitAll()
                 .antMatchers("/alumno/guardar**").permitAll()
                
                 
                 //hay que poner las carpetas que vamos a ocupar en el proyecto
                 //esta "/", "/home" es la raiz de todo
                 .antMatchers("/", "/index").permitAll()
                 /*permitAll() es par que este permitido todo lo que se le
                 esta brindando al usuario*/
                
                 .antMatchers("/public/**").permitAll() 
                .antMatchers("/css/**").permitAll()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/doctos/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/user/**").permitAll()
                
               
                .anyRequest().authenticated()
                .and()
                //parte del login
            .formLogin()
	            .loginPage("/login")
	            ///j_spring_security_check se le puede poner el nombre que sea
	            .loginProcessingUrl("/j_spring_security_check")
	            .defaultSuccessUrl("/index.html")/*al hacer el login y el usurario existe,
	            pide auntentificar y despues redirecciona a index.html*/
	            .failureUrl("/login?error=true")
	            /*en caso de error, se puede enviar un mensaje*/
	            .successHandler(myAuthenticationSuccessHandler)
	            //myAuthenticationSuccessHandler manejador de seguridad
	            .usernameParameter("j_username")
	            .passwordParameter("j_password")
	        .permitAll()
	            .and()
	        .sessionManagement()
	            .invalidSessionUrl("/invalidSession.html")
	            //la sesion invalida es por si el tiempo que podia estar conectado o logeado termino.
	            .sessionFixation().none()
	        .and()
	        .logout()
	            .invalidateHttpSession(false)
	            .logoutUrl("/j_spring_security_logout")
	            
	            .logoutSuccessUrl("/login?logSucc=true")
	            //borrado de cookies
	            .deleteCookies("JSESSIONID")
	            .permitAll();
    }
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider());
    }
	
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

       registry.addResourceHandler("/public/**")
          .addResourceLocations("/public/", "classpath:/public/");
    }
    
    
    
    @Bean
    public DaoAuthenticationProvider authProvider() {
        final DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }
}
