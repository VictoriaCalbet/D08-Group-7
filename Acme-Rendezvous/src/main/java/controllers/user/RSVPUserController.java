
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RSVPService;
import services.RendezvousService;
import services.UserService;
import controllers.AbstractController;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/RSVP/user")
public class RSVPUserController extends AbstractController {

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private RSVPService			RSVPService;

	@Autowired
	private UserService			userService;


	// Constructors -----------------------------------------------------------

	public RSVPUserController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String message) {
		final ModelAndView result;
		Collection<RSVP> rsvps = new ArrayList<RSVP>();

		final User u = this.userService.findByPrincipal();
		rsvps = u.getRsvps();
		final Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		for (final RSVP rsvp : rsvps)
			rendezvouses.add(rsvp.getRendezvous());
		Collection<Rendezvous> rendezvousesCreated = new ArrayList<Rendezvous>();
		rendezvousesCreated = u.getRendezvoussesCreated();
		for (final Rendezvous rv : rendezvousesCreated)
			if (!rendezvouses.contains(rv))
				rendezvouses.add(rv);

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/user/list.do");

		return result;
	}

	@RequestMapping(value = "/RSVPAssure", method = RequestMethod.GET)
	public ModelAndView rsvpAssure(@RequestParam final int rendezvousId) {

		final ModelAndView result;

		Rendezvous rv;

		rv = this.rendezvousService.findOne(rendezvousId);
		Assert.notNull(rv);

		result = this.createEditModelAndView(rv);

		result.addObject(rendezvousId);

		return result;

	}
	@RequestMapping(value = "/RSVP", method = RequestMethod.POST, params = "save")
	public ModelAndView enrol(@Valid final Rendezvous rv, final BindingResult binding) {

		ModelAndView result;
		if (binding.hasErrors())
			result = this.createEditModelAndView(rv);
		else
			try {

				this.RSVPService.RSVPaRendezvous(rv.getId());

				result = new ModelAndView("redirect:/rendezvous/list.do");

			} catch (final Throwable oops) {

				String messageError = "rendezvous.commit.error";

				if (oops.getMessage().contains("message.error"))

					messageError = oops.getMessage();

				result = this.createEditModelAndView(rv, messageError);

			}

		return result;

	}
	// Ancillaty methods

	protected final ModelAndView createEditModelAndView(final Rendezvous rv) {

		final ModelAndView result;

		result = this.createEditModelAndView(rv, null);

		return result;

	}

	protected final ModelAndView createEditModelAndView(final Rendezvous rendezvous, final String messageCode) {

		ModelAndView result;

		result = new ModelAndView("RSVP/user/RSVP");

		result.addObject("rendezvous", rendezvous);

		result.addObject("message", messageCode);

		result.addObject("requestURI", "RSVP/user/RSVP.do");

		return result;

	}

	//Ancillary methods

	protected ModelAndView enrolModelAndView(final Rendezvous rendezvous) {

		ModelAndView result;

		result = this.enrolModelAndView(rendezvous, null);

		return result;

	}

	protected ModelAndView enrolModelAndView(final Rendezvous rendezvous, final String message) {

		ModelAndView result;

		result = new ModelAndView("RSVP/user/RSVP");

		result.addObject("rendezvous", rendezvous);

		result.addObject("message", message);

		return result;

	}
}
