
package services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.RSVPRepository;
import repositories.RendezvousRepository;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Service
@Transactional
public class RSVPService {

	// Managed Repository -----------------------------------------------------

	@Autowired
	private RSVPRepository			rsvpRepository;

	@Autowired
	private RendezvousRepository	rendezvousRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService				userService;


	// Constructors -----------------------------------------------------------

	public RSVPService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public RSVP create(final Rendezvous result) {

		final RSVP rsvp = new RSVP();
		final User u = this.userService.findByPrincipal();
		rsvp.setUser(u);
		rsvp.setIsCancelled(false);
		rsvp.setRendezvous(result);

		return rsvp;
	}

	public Collection<RSVP> findAll() {
		Collection<RSVP> result = null;
		result = this.rsvpRepository.findAll();
		return result;
	}

	public RSVP findOne(final int rsvpId) {
		RSVP result = null;
		result = this.rsvpRepository.findOne(rsvpId);
		return result;
	}

	public RSVP save(final RSVP rsvp) {
		Assert.notNull(rsvp, "message.error.rsvp.null");
		final RSVP result = this.rsvpRepository.save(rsvp);

		return result;
	}

	public void delete(final RSVP rsvp) {
		Assert.notNull(rsvp, "message.error.rsvp.null");

	}

	public void RSVPaRendezvous(final int rvId) {
		final Rendezvous rendezvousToRSVP = this.rendezvousRepository.findOne(rvId);
		final User creator = rendezvousToRSVP.getCreator();

		final User principal = this.userService.findByPrincipal();
		Assert.isTrue(!(creator.equals(principal)));

		Collection<RSVP> principalRsvps = new ArrayList<RSVP>();

		principalRsvps = principal.getRsvps();

		final Collection<Rendezvous> principalRendezvouses = new ArrayList<Rendezvous>();

		for (final RSVP rsvp : principalRsvps)
			principalRendezvouses.add(rsvp.getRendezvous());

		Assert.isTrue(!(principalRendezvouses.contains(rendezvousToRSVP)));
		final RSVP rsvp = this.create(rendezvousToRSVP);

		rsvp.setRendezvous(rendezvousToRSVP);

		final RSVP result = this.save(rsvp);

		principal.getRsvps().add(result);
	}
	// Other business methods -------------------------------------------------

}
