
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RendezvousRepository;
import domain.Actor;
import domain.Announcement;
import domain.Comment;
import domain.Question;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RendezvousService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService				userService;

	@Autowired
	private ActorService			actorService;


	// Constructors -----------------------------------------------------------

	public RendezvousService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Rendezvous create() {

		final Rendezvous result = new Rendezvous();
		final User u = this.userService.findByPrincipal();
		final Collection<RSVP> rsvps = new ArrayList<RSVP>();
		final Collection<Question> questions = new ArrayList<Question>();
		final Collection<Comment> comments = new ArrayList<Comment>();
		final Collection<Announcement> announcements = new ArrayList<Announcement>();
		final Collection<Rendezvous> isLinkedTo = new ArrayList<Rendezvous>();

		result.setIsDeleted(false);
		result.setCreator(u);
		result.setRsvps(rsvps);
		result.setQuestions(questions);
		result.setComments(comments);
		result.setAnnouncements(announcements);
		result.setIsLinkedTo(isLinkedTo);

		return result;
	}

	public Collection<Rendezvous> findAll() {
		Collection<Rendezvous> result = null;
		result = this.rendezvousRepository.findAll();
		return result;
	}

	public Rendezvous findOne(final int rendezvousId) {
		Rendezvous result = null;
		result = this.rendezvousRepository.findOne(rendezvousId);
		return result;
	}

	public Collection<Rendezvous> findByNotDeleted() {
		return this.rendezvousRepository.findByNotDeleted();
	}

	public Rendezvous save(final Rendezvous rendezvous) {
		Assert.notNull(rendezvous, "message.error.rendezvous.null");
		Rendezvous result;
		result = this.rendezvousRepository.save(rendezvous);

		return result;
	}
	public void delete(final int rendezvousId) {
		final Rendezvous r = this.rendezvousRepository.findOne(rendezvousId);
		final User u = this.userService.findByPrincipal();
		Assert.notNull(r, "message.error.rendezvous.null");
		Assert.isTrue(r.getCreator().equals(u), "message.error.rendezvous.user");
		Assert.isTrue(r.getIsDraft(), "message.error.rendezvous.isDraft");
		Assert.isTrue(!r.getIsDeleted(), "message.error.rendezvous.isDeleted");
		r.setIsDeleted(true);
		this.rendezvousRepository.save(r);

	}

	public void deleteAdmin(final int rendezvousId) {
		final Rendezvous r = this.rendezvousRepository.findOne(rendezvousId);
		final Actor actor = this.actorService.findByPrincipal();
		Assert.isTrue(this.actorService.checkAuthority(actor, "ADMIN"));
		Assert.notNull(r, "message.error.rendezvous.null");
		final User creator = r.getCreator();
		creator.getRendezvoussesCreated().remove(r);
		this.userService.save(creator);
		this.rendezvousRepository.delete(r);

	}

	// Other business methods -------------------------------------------------

	public Collection<Rendezvous> findAllAttendedByUserId(final int userId) {
		final Collection<Rendezvous> result;
		result = this.rendezvousRepository.findAllAttendedByUserId(userId);
		return result;
	}

	public Collection<Rendezvous> findAllAttendedByUserIdU18(final int userId) {
		Collection<Rendezvous> result;
		result = this.rendezvousRepository.findAllAttendedByUserIdU18(userId);
		return result;
	}

	public Collection<Rendezvous> findAllAvailableAnnouncementsByUserId(final int userId) {
		Collection<Rendezvous> result = null;
		result = this.rendezvousRepository.findAllAvailableAnnouncementsByUserId(userId);
		return result;
	}

	// Dashboard methods ------------------------------------------------------

	public Double findAvgRendezvousesCreatedPerUser() {
		Double result = null;
		result = this.rendezvousRepository.findAvgRendezvousesCreatedPerUser();
		return result;
	}

	public Double findStdRendezvousesCreatedPerUser() {
		Double result = null;
		result = this.rendezvousRepository.findStdRendezvousesCreatedPerUser();
		return result;
	}

	public Double findAvgRendezvousRSVPsPerUsers() {
		Double result = null;
		result = this.rendezvousRepository.findAvgRendezvousRSVPsPerUsers();
		return result;
	}

	public Double findStdRendezvousRSVPsPerUsers() {
		Double result = null;
		result = this.rendezvousRepository.findStdRendezvousRSVPsPerUsers();
		return result;
	}

	public Collection<Rendezvous> findTop10RendezvousByRSVPs() {
		Collection<Rendezvous> result = null;
		result = this.rendezvousRepository.findAllRendezvousByRSVPs();

		if (result != null && result.size() > 10)
			result = new ArrayList<Rendezvous>(result).subList(0, 10);

		return result;
	}

	public Collection<Rendezvous> findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous() {
		Collection<Rendezvous> result = null;
		result = this.rendezvousRepository.findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous();
		return result;
	}

	public Collection<Rendezvous> findRendezvousesThatLinkedToRvGreaterThanAvgPlus10() {
		Collection<Rendezvous> result = null;
		result = this.rendezvousRepository.findRendezvousesThatLinkedToRvGreaterThanAvgPlus10();
		return result;
	}

	public Double findAvgNoQuestionPerRendezvous() {
		Double result = null;
		result = this.rendezvousRepository.findAvgNoQuestionPerRendezvous();
		return result;
	}

	public Double findStdNoQuestionPerRendezvous() {
		Double result = null;
		result = this.rendezvousRepository.findStdNoQuestionPerRendezvous();
		return result;
	}
}
