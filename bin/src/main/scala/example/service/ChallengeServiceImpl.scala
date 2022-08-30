package example.service;

import example.repository.ChallengeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
class ChallengeServiceImpl @Autowired()(challengeRepository: ChallengeRepository)
  extends ChallengeService {

    override def findAllQuestions(): List[String] = challengeRepository.findAllQuestions()

    override def findByQuestion(questionId: Int): String = {
      return challengeRepository.findByQuestion(questionId);
    }

}
