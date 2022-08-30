package example.repository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
class ChallengeRepositoryImpl extends ChallengeRepository {

    override def findAllQuestions(): List[String] = example.model.Challenge.listQuestions()

    override def findByQuestion(questionId: Int): String = {
      val list = this.findAllQuestions()
      return list.get(questionId)
    }
}
