
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Rendezvous;

public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r where r.isDeleted = false")
	Collection<Rendezvous> findByNotDeleted();

	@Query("select rsvp.rendezvous from RSVP rsvp where rsvp.user.id = ?1 AND rsvp.isCancelled IS FALSE")
	Collection<Rendezvous> findAllAttendedByUserId(int userId);

	@Query("select rsvp.rendezvous from RSVP rsvp where rsvp.user.id = ?1 AND rsvp.isCancelled IS FALSE AND rsvp.rendezvous.isAdultOnly IS FALSE")
	Collection<Rendezvous> findAllAttendedByUserIdU18(int userId);

	// Dashboard queries
	//	@Query("select r from Rendezvous r where r.user.id = ?1")
	//	public Collection<Rendezvous> getRendezvousesFromPrincipal(int userId);

	//	@Query("select avg(rvs.attendants.size) from Rendezvous rvs")
	//	public Double avgUsersPerRendezvous();
	//
	//	@Query("select sqrt(sum(rvs.attendants.size * rvs.attendants.size) / count(rvs.attendants.size) - (avg(rvs.attendants.size) * avg(rvs.attendants.size))) from Rendezvous rvs")
	//	public Double stdUsersPerRendezvous();

	@Query("select avg(rvs.announcements.size) from Rendezvous rvs")
	public Double avgAnnouncementPerRendezvous();

	@Query("select sqrt(sum(rvs.announcements.size * rvs.announcements.size) / count(rvs.announcements.size) - (avg(rvs.announcements.size) * avg(rvs.announcements.size))) from Rendezvous rvs")
	public Double stdAnnouncementPerRendezvous();

	@Query("select avg(rvs.questions.size) from Rendezvous rvs")
	public Double avgNoQuestionsPerRendezvous();

	@Query("select  sqrt(sum(rvs.questions.size * rvs.questions.size) / count(rvs.questions.size) - (avg(rvs.questions.size) * avg(rvs.questions.size))) from Rendezvous rvs")
	public Double stdNoQuestionsPerRendezvous();

	@Query("select avg(q.answers.size) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double avgNoAnswersPerRendezvous();

	@Query("select sqrt(sum(q.answers.size * q.answers.size) / count(q.answers.size) - (avg(q.answers.size) * avg(q.answers.size))) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double stdNoAnswersPerRendezvous();

}
