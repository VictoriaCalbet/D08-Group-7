
package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.forms.ActorFormService;
import controllers.AbstractController;
import domain.Administrator;
import domain.forms.ActorForm;

@Controller
@RequestMapping(value = "/administrator/administrator")
public class AdministratorAdministratorController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private ActorFormService		actorFormService;

	@Autowired
	private Validator				actorFormValidator;


	// Constructors ----------------------------------------------------------

	public AdministratorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Administrator administrator;

		administrator = this.administratorService.findByPrincipal();

		result = new ModelAndView("administrator/display");
		result.addObject("administrator", administrator);

		return result;
	}

	// Creation ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		ActorForm administrator;

		administrator = this.actorFormService.create();

		result = this.createEditModelAndView(administrator);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm actorForm, final BindingResult binding) {
		this.actorFormValidator.validate(actorForm, binding);

		ModelAndView result;

		if (binding.hasErrors())
			result = this.createEditModelAndView(actorForm);
		else
			try {
				this.actorFormService.saveFromEdit(actorForm);
				result = new ModelAndView("redirect:display.do");
			} catch (final Throwable oops) {
				String errorCode;
				errorCode = "actorForm.commit.error";
				result = this.createEditModelAndView(actorForm, errorCode);
			}

		return result;
	}
	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm administrator) {
		ModelAndView result;

		result = this.createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm administrator, final String message) {
		ModelAndView result;

		result = new ModelAndView("actorForm/edit");
		result.addObject("actorForm", administrator);
		result.addObject("message", message);
		result.addObject("urlAction", "administrator/administrator/edit.do");

		return result;
	}

}
