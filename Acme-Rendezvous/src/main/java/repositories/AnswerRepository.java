
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	// Dashboard queries

	@Query("select avg(rvs.questions.size) from Rendezvous rvs")
	public Double findAvgNoAnswersToTheQuestionsPerRendezvous();

	@Query("select  sqrt(sum(rvs.questions.size * rvs.questions.size) / count(rvs.questions.size) - (avg(rvs.questions.size) * avg(rvs.questions.size))) from Rendezvous rvs")
	public Double findStdNoAnswersToTheQuestionsPerRendezvous();
}
