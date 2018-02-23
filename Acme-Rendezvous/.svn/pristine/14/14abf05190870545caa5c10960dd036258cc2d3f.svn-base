
package controllers.user;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RendezvousService;
import services.UserService;
import services.form.RendezvousFormService;
import controllers.AbstractController;
import domain.Rendezvous;
import domain.User;
import domain.form.RendezvousForm;

@Controller
@RequestMapping("/rendezvous/user")
public class RendezvousUserController extends AbstractController {

	@Autowired
	private RendezvousService		rendezvousService;

	@Autowired
	private RendezvousFormService	rendezvousFormService;

	@Autowired
	private UserService				userService;


	//	@Autowired
	//	private Validator				rendezvousFormValidator;

	// Constructors -----------------------------------------------------------

	public RendezvousUserController() {
		super();
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required = false) final String message) {
		ModelAndView result;
		Collection<Rendezvous> rendezvouses = new ArrayList<Rendezvous>();
		final User u = this.userService.findByPrincipal();
		rendezvouses = u.getRendezvoussesCreated();

		result = new ModelAndView("rendezvous/list");
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("message", message);
		result.addObject("requestURI", "rendezvous/user/list.do");

		return result;
	}

	// Creation ----------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		RendezvousForm rendezvousForm;

		rendezvousForm = this.rendezvousFormService.create();
		result = this.createModelAndView(rendezvousForm);

		return result;

	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
	public ModelAndView create(@Valid final RendezvousForm rendezvousForm, final BindingResult binding) {
		//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(rendezvousForm);
		else
			try {
				this.rendezvousFormService.saveFromCreate(rendezvousForm);
				result = new ModelAndView("redirect:/rendezvous/user/list.do");
			} catch (final Throwable oops) {
				String messageError = "rendezvous.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createModelAndView(rendezvousForm, messageError);
			}

		return result;
	}

	// Edit ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int rendezvousId) {
		ModelAndView result;
		RendezvousForm rendezvousForm;

		try {
			rendezvousForm = this.rendezvousFormService.create(rendezvousId);
			result = this.createModelAndView(rendezvousForm);
		} catch (final Throwable oops) {
			result = new ModelAndView("redirect:/rendezvous/user/list.do");
			result.addObject("message", oops.getMessage());
		}

		return result;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final RendezvousForm rendezvousForm, final BindingResult binding) {
		//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createModelAndView(rendezvousForm);
		else
			try {
				this.rendezvousFormService.saveFromEdit(rendezvousForm);
				result = new ModelAndView("redirect:/rendezvous/user/list.do");
			} catch (final Throwable oops) {
				String messageError = "rendezvous.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createModelAndView(rendezvousForm, messageError);
			}

		return result;
	}

	//Delete
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam final int rendezvousId) {
		ModelAndView result;
		try {
			this.rendezvousService.delete(rendezvousId);
			result = new ModelAndView("redirect:/rendezvous/user/list.do");
		} catch (final Throwable oops) {
			String messageError = "rendezvous.delete.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/rendezvous/user/list.do");
			result.addObject("message", messageError);
		}
		return result;
	}

	// Ancillaty methods
	protected ModelAndView createModelAndView(final RendezvousForm rendezvousForm) {
		ModelAndView result;

		result = this.createModelAndView(rendezvousForm, null);

		return result;
	}

	protected ModelAndView createModelAndView(final RendezvousForm rendezvousForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/user/create");
		result.addObject("rendezvousForm", rendezvousForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView editModelAndView(final RendezvousForm rendezvousForm) {
		ModelAndView result;

		result = this.editModelAndView(rendezvousForm, null);

		return result;
	}

	protected ModelAndView editModelAndView(final RendezvousForm rendezvousForm, final String message) {
		ModelAndView result;

		result = new ModelAndView("rendezvous/user/edit");
		result.addObject("rendezvousForm", rendezvousForm);
		result.addObject("message", message);

		return result;
	}

}
