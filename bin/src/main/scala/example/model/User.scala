package example.model;

import java.util.ArrayList;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

case class User (
  var username :String
  , var password :String
  , var authorities: java.util.Collection[_ <:GrantedAuthority]
  , var challenge: Challenge
) extends org.springframework.security.core.userdetails.User(username, password, true, true, true, true, authorities)
 with org.springframework.security.core.userdetails.UserDetails
{
  def this(){
    this("noob", "pass", User.assignRole("USER"), null)
    println("aux constructor no args")
  }

  def this(username: String, password: String){
    this(username, password, User.assignRole("USER"), new Challenge(0, "1900"))
    println("aux constructor 2 args")
  }

  def this(username: String, password: String, challenge: Challenge){
    this(username, password, User.assignRole("USER"), challenge)
    println("aux constructor 3 args")
  }

  override def getPassword(): String = password;

  override def getUsername(): String = username;

  override def isAccountNonExpired(): Boolean = true

  override def isAccountNonLocked(): Boolean = true

  override def isCredentialsNonExpired(): Boolean = true

  override def isEnabled(): Boolean = true
}

object User {

    def assignRole(r: String): ArrayList[GrantedAuthority] =
    {
      val grantedAuthoritiesList= new ArrayList[GrantedAuthority]();
      grantedAuthoritiesList.add(new SimpleGrantedAuthority("ROLE_" + r));
      return grantedAuthoritiesList;
    }

    def emptyModel(): User = {
      val user = new User()
      user.username = ""
      user.password = ""

      return user;
    }
}
