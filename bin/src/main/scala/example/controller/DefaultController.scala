package example.controller

import example.service.ChallengeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller

@Controller
class DefaultController @Autowired()(
    challengeService: ChallengeService
  ){

  @GetMapping(Array("/", "/home"))
  def home(): String = {
      return "/home"
  }

  @GetMapping(Array("/admin"))
  def admin(): String = {
      return "/admin"
  }

  @GetMapping(Array("/super"))
  def super1(): String = {
      return "/super"
  }

  @GetMapping(Array("/user"))
  def user(): String = {
      return "/user"
  }

  @GetMapping(Array("/about"))
  def about(): String = {
      return "/about"
  }

  @GetMapping(Array("/login"))
  def login(): String = {
      return "/login"
  }

  @GetMapping(Array("/403"))
  def error403(): String = {
      return "/error/403"
  }

}
