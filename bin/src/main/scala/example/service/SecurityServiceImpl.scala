package example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
class SecurityServiceImpl @Autowired()(
    authenticationManager :AuthenticationManager
    , userDetailsService :UserDetailsService
  ) extends SecurityService{

    override def isAuthenticated(): Boolean = {
      var value :Boolean = true
        val authentication :Authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            value = false;
        }
        else {
          value = authentication.isAuthenticated();
        }
        return value;
    }

    override def autoLogin(username :String, password :String): Unit = {

        val userDetails :UserDetails = userDetailsService.loadUserByUsername(username);

        val usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());

        val auth :Authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(auth);
            println("[LOG] Auto login successfully!");
        }
    }
}
