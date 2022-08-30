package example.config

import org.springframework.security.crypto.password.PasswordEncoder
import example.model.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.provisioning.InMemoryUserDetailsManager

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.context.annotation.Bean

import java.util.ArrayList
import java.util.List

@Configuration
@EnableWebSecurity
class Security  @Autowired()(
    accessDeniedHandler :AccessDeniedHandler
  ) extends WebSecurityConfigurerAdapter {

    // roles admin allow to access /admin/**
    // roles user allow to access /user/**
    // custom 403 access denied handler
    @throws(classOf[Exception])
    override def configure(http :HttpSecurity): Unit = {
  		http
  			.authorizeRequests()
        .antMatchers("/user").access("hasRole('USER') or hasRole('SUPER')")
        .antMatchers("/admin").access("hasRole('ADMIN') or hasRole('SUPER')")
        .antMatchers("/super").access("hasRole('SUPER')")
  				.antMatchers("/login", "/register", "/reset", "/api/register", "/api/reset").permitAll()
  				.anyRequest().authenticated()
  				.and()
  			.formLogin()
  				.loginPage("/login")
          .loginProcessingUrl("/login")
          .successHandler(myAuthenticationSuccessHandler())
  				.permitAll()
          .and()
          .logout().deleteCookies("JSESSIONID")
          .and()
          .rememberMe()
          .userDetailsService(this.userDetailsService())
          .key("uniqueAndSecret")
          .tokenValiditySeconds(120)
    }

    @Bean
    def myAuthenticationSuccessHandler(): AuthenticationSuccessHandler = {
        return new MySimpleUrlAuthenticationSuccessHandler()
    }

  	@Bean
  	def passwordEncoder(): PasswordEncoder =
  	{
      try {
        return new example.security.DESasswordEncoder()
      } catch{
        case e :Exception => {}
      }
      println("Using default.")
      return new org.springframework.security.crypto.argon2.Aron2PasswordEncoder()
  	}

    @Bean
    def inMemoryUserDetailsManager(): InMemoryUserDetailsManager = {
      return userDetailsService().asInstanceOf[InMemoryUserDetailsManager]
    }

  	override def userDetailsService(): UserDetailsService = {

      val challenge = new example.model.Challenge(0, "1900")

      val pass = passwordEncoder().encode("pass")

      var userDetailsList = new ArrayList[UserDetails]()

      userDetailsList.add(new User("user", pass, User.assignRole("USER"), challenge))
      userDetailsList.add(new User("admin", pass, User.assignRole("ADMIN"), challenge))
      userDetailsList.add(new User("super", pass, User.assignRole("SUPER"), challenge))

      return new InMemoryUserDetailsManager(userDetailsList)
  	}

    @throws(classOf[Exception])
    @Bean
    override def authenticationManagerBean() : AuthenticationManager = {
        return super.authenticationManagerBean();
    }

}
