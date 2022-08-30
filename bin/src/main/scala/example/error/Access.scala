package example.error

import org.springframework.security.access.AccessDeniedException
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.stereotype.Component

import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import java.io.IOException

// handle 403 page
@Component
class Access extends AccessDeniedHandler {

    @throws (classOf[Exception])
    override def handle(httpServletRequest :HttpServletRequest,
                      httpServletResponse :HttpServletResponse,
                      _e :AccessDeniedException): Unit = {

        val auth :Authentication = SecurityContextHolder.getContext().getAuthentication()

        if (auth != null) {
            println("User '" + auth.getName()
                    + "' attempted to access the protected URL: "
                    + httpServletRequest.getRequestURI())
        }

        httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/403")

    }
}
