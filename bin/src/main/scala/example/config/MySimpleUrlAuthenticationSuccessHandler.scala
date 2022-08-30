package example.config

import java.io.IOException
import java.util.{Collection, HashMap, Map}
import scala.collection.convert.Wrappers.JCollectionWrapper

import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.servlet.http.HttpSession

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.web.DefaultRedirectStrategy
import org.springframework.security.web.RedirectStrategy
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.AuthenticationSuccessHandler

class MySimpleUrlAuthenticationSuccessHandler extends AuthenticationSuccessHandler {

  @throws(classOf[IOException])
  override def onAuthenticationSuccess(request :HttpServletRequest, response :HttpServletResponse, authentication :Authentication): Unit = {
      // run custom logics upon successful login

      handle(request, response, authentication)
      clearAuthenticationAttributes(request)
  }

  @throws(classOf[IOException])
  def handle(request :HttpServletRequest, response :HttpServletResponse, authentication :Authentication): Unit = {
      val targetUrl = determineTargetUrl(authentication)
/*
      Option(request) match {
        case None => return;
      }

      Option(response) match {
        case None => return;
      }
*/
      val redirectStrategy :RedirectStrategy = new DefaultRedirectStrategy()

      if (response.isCommitted()) {
          println("Response has already been committed. Unable to redirect to ${targetUrl}")
          return
      }

      redirectStrategy.sendRedirect(request, response, targetUrl)
  }

  def determineTargetUrl(authentication :Authentication): String = {
/*
    Option(authentication) match {
      case None => return "/";
    }
*/
      var roleTargetUrlMap = new HashMap[String, String]()
      roleTargetUrlMap.put("ROLE_USER", "/user")
      roleTargetUrlMap.put("ROLE_ADMIN", "/admin")
      roleTargetUrlMap.put("ROLE_SUPER", "/super")

      // getAuthorities returns java.util.Collection<GrantedAuthority>
          val authorities: java.util.Collection[_ <:GrantedAuthority] = authentication.getAuthorities
          // Wrap the collection is a Scala class
          val authoritiesWrapper = JCollectionWrapper.apply(authorities);
          for(grantedAuthority <- authoritiesWrapper.iterator){
/*
          Option(grantedAuthority) match {
            case None => return "/";
          }
*/
          val authorityName :String = grantedAuthority.getAuthority()
/*
          Option(authorityName) match {
            case None => return "/";
          }
*/
          if(roleTargetUrlMap.containsKey(authorityName)) {
                return roleTargetUrlMap.get(authorityName)
          }

      }

      return "/"
  }
  /**
   * Removes temporary authentication-related data which may have been stored in the session
   * during the authentication process.
   */
  def clearAuthenticationAttributes(request :HttpServletRequest): Unit = {

      val session = request.getSession(false)
/*
      Option(session) match {
        case None => return;
      }
*/
      session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION)
  }
}
