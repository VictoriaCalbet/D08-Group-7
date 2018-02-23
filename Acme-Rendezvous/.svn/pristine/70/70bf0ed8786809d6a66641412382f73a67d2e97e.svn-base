
package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import domain.Rendezvous;

@Controller
@RequestMapping("/rendezvous")
public class RendezvousController extends AbstractController {

	@Autowired
	private RendezvousService	rendezvousService;


	public RendezvousController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final Integer rendezvousId, @RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();

		if (rendezvousId == null)
			rendezvouses = this.rendezvousService.findByNotDeleted();
		else
			rendezvouses = this.rendezvousService.findOne(rendezvousId).getIsLinkedTo();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/list.do");

		return result;
	}

}
