package example.service;

import example.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
trait UserService {
  def save(user :User): Unit;
  def resetPassword(name: String, newPassword: String): User;
  def findByUsername(username :String): UserDetails;
}
