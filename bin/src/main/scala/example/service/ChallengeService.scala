package example.service;

import java.util.List;

trait ChallengeService {
  def findAllQuestions(): List[String];
  def findByQuestion(questionId: Int): String;
}
