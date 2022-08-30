package example.service;
import org.springframework.stereotype.Service;

@Service
trait SecurityService {
    def isAuthenticated(): Boolean;
    def autoLogin(username :String, password :String): Unit;
}
