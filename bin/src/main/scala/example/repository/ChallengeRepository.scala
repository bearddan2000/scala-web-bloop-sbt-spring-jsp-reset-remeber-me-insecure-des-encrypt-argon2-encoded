package example.repository;

import java.util.List;
import org.springframework.stereotype.Repository;

@Repository("challengeRepository")
trait ChallengeRepository {
  def findAllQuestions(): List[String];
  def findByQuestion(questionId: Int): String;
}
