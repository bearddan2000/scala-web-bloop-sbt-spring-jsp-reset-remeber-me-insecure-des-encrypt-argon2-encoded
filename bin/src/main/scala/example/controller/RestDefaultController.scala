package example.controller;

import example.model.{Challenge, User};
import example.service.{UserService, SecurityService};
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.{GetMapping, RequestMapping, RequestParam, RestController};

@RestController
@RequestMapping(path = Array("/api"))
class RestDefaultController @Autowired()(
    securityService :SecurityService
    , userService :UserService
    , passwordEncoder: PasswordEncoder
  )
{
  @GetMapping(path = Array("/register"))
  def register(@RequestParam("username") username: String
  , @RequestParam("password") password: String
  , @RequestParam("question") question: Int
  , @RequestParam("answer") answer: String): String = {

      val user: User = new User(username
      , passwordEncoder.encode(password)
      , new Challenge(question, answer))

      println("username: %s, password: %s".format(user.username, user.password))

      userService.save(user);

      securityService.autoLogin(user.username, user.password);

      println("[LOG] user redirected")
      return "user";
  }

  @GetMapping(path = Array("/reset"))
  def reset(@RequestParam("username") username: String
  , @RequestParam("password") password: String
  , @RequestParam("question") question: Int
  , @RequestParam("answer") answer: String): String = {

      val user: User = userService.resetPassword(username, password);

      if (user == null) {
      println("[LOG] user not found")
        return "register";
      }

      if (user.challenge.question != question
      || user.challenge.answer != answer) {
      println("[LOG] challenge not met")
       return "reset";
      }

      securityService.autoLogin(user.username, user.password);

      println("[LOG] user redirected")
      return "user";
  }
}
