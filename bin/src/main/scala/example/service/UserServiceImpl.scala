package example.service;

import example.model.User;
import example.repository.UserRepository;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class UserServiceImpl @Autowired()(
    userRepository :UserRepository
  ) extends UserService {

  override def save(user :User): Unit = {
        userRepository.save(user);
        println("[LOG] user saved");
    }

    override def resetPassword(name: String, newPassword: String): User = userRepository.resetPassword(name, newPassword);

    override def findByUsername(username :String): UserDetails = userRepository.findUserByName(username);
}
