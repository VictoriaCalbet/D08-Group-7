
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Rendezvous;

public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

	@Query("select r from Rendezvous r where r.isDeleted = false AND r.isDraft = false AND r.isAdultOnly = false")
	Collection<Rendezvous> findRendezvousesAllUser();

	@Query("select r from Rendezvous r where r.isDeleted = false AND r.isDraft = false")
	Collection<Rendezvous> findRendezvousesOnlyAdult();

	@Query("select rsvp.rendezvous from RSVP rsvp where rsvp.user.id = ?1 AND rsvp.isCancelled IS FALSE AND rsvp.rendezvous.isDraft IS FALSE AND rsvp.rendezvous.isDeleted IS FALSE")
	Collection<Rendezvous> findAllAttendedByUserId(int userId);

	@Query("select rsvp.rendezvous from RSVP rsvp where rsvp.user.id = ?1 AND rsvp.isCancelled IS FALSE AND rsvp.rendezvous.isDraft IS FALSE AND rsvp.rendezvous.isDeleted IS FALSE AND rsvp.rendezvous.isAdultOnly IS FALSE")
	Collection<Rendezvous> findAllAttendedByUserIdU18(int userId);

	@Query("select rvs from Rendezvous rvs where rvs.meetingMoment < CURRENT_TIMESTAMP and rvs.isDraft is false and rvs.isDeleted is false and rvs.creator.id = ?1")
	Collection<Rendezvous> findAllAvailableAnnouncementsByUserId(int userId);

	// Dashboard queries

	@Query("select avg(usr.rendezvoussesCreated.size) from User usr")
	public Double findAvgRendezvousesCreatedPerUser();

	@Query("select  sqrt(sum(usr.rendezvoussesCreated.size * usr.rendezvoussesCreated.size) / count(usr.rendezvoussesCreated.size) - (avg(usr.rendezvoussesCreated.size) * avg(usr.rendezvoussesCreated.size))) from User usr")
	public Double findStdRendezvousesCreatedPerUser();

	@Query("select avg(usr.rsvps.size) from User usr")
	public Double findAvgRendezvousRSVPsPerUsers();

	@Query("select sqrt(sum(usr.rsvps.size * usr.rsvps.size) / count(usr.rsvps.size) - (avg(usr.rsvps.size) * avg(usr.rsvps.size))) from User usr")
	public Double findStdRendezvousRSVPsPerUsers();

	@Query("select rvs from Rendezvous rvs order by rvs.rsvps.size")
	public Collection<Rendezvous> findAllRendezvousByRSVPs();

	@Query("select rvs from Rendezvous rvs where rvs.announcements.size > (select avg(rv.announcements.size) * 0.75 from Rendezvous rv)")
	public Collection<Rendezvous> findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous();

	@Query("select rvs from Rendezvous rvs where rvs.isLinkedTo.size > (select avg(rv.isLinkedTo.size)*1.1 from Rendezvous rv)")
	public Collection<Rendezvous> findRendezvousesThatLinkedToRvGreaterThanAvgPlus10();

	@Query("select avg(q.answers.size) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double findAvgNoQuestionPerRendezvous();

	@Query("select sqrt(sum(q.answers.size * q.answers.size) / count(q.answers.size) - (avg(q.answers.size) * avg(q.answers.size))) from Rendezvous rvs join rvs.questions q join q.answers")
	public Double findStdNoQuestionPerRendezvous();

}
