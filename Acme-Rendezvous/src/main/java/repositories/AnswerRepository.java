
package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Answer;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

	// Dashboard queries

	@Query("select avg(q.answers.size) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double findAvgNoAnswersToTheQuestionPerRendezvous();

	@Query("select sqrt(sum(q.answers.size * q.answers.size) / count(q.answers.size) - (avg(q.answers.size) * avg(q.answers.size))) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double findStdNoAnswersToTheQuestionPerRendezvous();
}
