
package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.userAccount.id = ?1")
	public User findByUserAccountId(int id);

	@Query("select u from Rendezvous r join r.rsvps rv join rv.user u where r.id=?1 AND rv.isCancelled=false")
	Collection<User> findAttendantsOfRendezvous(int rendezvousId);

	// Dashboard queries
	
	@Query("select count(usr)*1.0/(select count(u) from User u where u.rendezvoussesCreated is not empty) from User usr where usr.rendezvoussesCreated is empty")
	public Double findRatioUserRendezvousesCreatedVsNeverCreated();
	
	@Query("select avg(rvs.rsvps.size) from Rendezvous rvs")
	public Double findAvgUsersRSVPsPerRendezvous();
	
	@Query("select sqrt(sum(rvs.rsvps.size * rvs.rsvps.size) / count(rvs.rsvps.size) - (avg(rvs.rsvps.size) * avg(rvs.rsvps.size))) from Rendezvous rvs")
	public Double findStdUsersRSVPsPerRendezvous();

}
