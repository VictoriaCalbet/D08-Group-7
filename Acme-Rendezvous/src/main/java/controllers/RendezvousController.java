
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.RendezvousService;
<<<<<<< HEAD
import domain.Actor;
=======
import services.UserService;
>>>>>>> David
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	@Autowired
	private RendezvousService	rendezvousService;
	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserService			userService;

	@Autowired
	private ActorService		actorService;


	public RendezvousController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer rendezvousId, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();

		if (rendezvousId == null)
			try {
				final Actor a = this.actorService.findByPrincipal();
				rendezvouses = this.rendezvousService.findRendezvousesLogged(a);
			} catch (final Throwable oops) {
				rendezvouses = this.rendezvousService.findRendezvousesNotLogged();
			}
		else
			rendezvouses = this.rendezvousService.findOne(rendezvousId).getIsLinkedTo();

		//RSVP button control
		Collection<Rendezvous> principalRendezvouses = new ArrayList<Rendezvous>();
		if (this.actorService.checkLogin()) {
			final User principal = this.userService.findByPrincipal();
			principalRendezvouses = this.rendezvousService.findAllAttendedByUserId(principal.getId());
		}

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("principalRendezvouses", principalRendezvouses);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

}
