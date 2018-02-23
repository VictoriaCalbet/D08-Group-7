
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.Announcement;

public interface AnnouncementRepository extends JpaRepository<Announcement, Integer> {

	@Query("select ann from User us join us.rendezvoussesCreated rvs join rvs.announcements ann where us.id = ?1")
	public Collection<Announcement> getAnnouncementsCreatedByUser(int userId);

	@Query("select ann from User us join us.rsvps rsvp join rsvp.rendezvous rv join rv.announcements ann where us.id = ?1 and rsvp.isCancelled = false order by ann.momentMade desc")
	public Collection<Announcement> getAnnouncementsPostedAndAcceptedByUser(int userId);
}
