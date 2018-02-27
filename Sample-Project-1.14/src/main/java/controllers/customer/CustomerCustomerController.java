
package controllers.customer;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.forms.ActorFormService;
import controllers.AbstractController;
import domain.Customer;
import domain.forms.ActorForm;

@Controller
@RequestMapping(value = "/customer/customer")
public class CustomerCustomerController extends AbstractController {

	// Services ----------------------------------------------------------

	@Autowired
	private CustomerService		customerService;

	@Autowired
	private ActorFormService	actorFormService;

	@Autowired
	private Validator			actorFormValidator;


	// Constructors ----------------------------------------------------------

	public CustomerCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display() {
		ModelAndView result;
		Customer customer;

		customer = this.customerService.findByPrincipal();

		result = new ModelAndView("customer/display");
		result.addObject("customer", customer);

		return result;
	}

	// Creation ----------------------------------------------------------
	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView result;
		ActorForm actorForm;

		actorForm = this.actorFormService.create();

		result = this.createEditModelAndView(actorForm);

		return result;
	}

	// Edition ----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid final ActorForm customer, final BindingResult binding) {
		this.actorFormValidator.validate(customer, binding);

		ModelAndView result;

		if (binding.hasErrors()) {
			System.out.println("Errors: " + binding.toString());
			result = this.createEditModelAndView(customer);
		} else
			try {
				this.actorFormService.saveFromEdit(customer);
				result = new ModelAndView("redirect:display.do");
			} catch (final Throwable oops) {
				result = this.createEditModelAndView(customer, "customer.commit.error");
			}

		return result;
	}

	// Ancillary Methods
	// ----------------------------------------------------------

	protected ModelAndView createEditModelAndView(final ActorForm customer) {
		ModelAndView result;

		result = this.createEditModelAndView(customer, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(final ActorForm customer, final String message) {
		ModelAndView result;

		result = new ModelAndView("actorForm/edit");
		result.addObject("actorForm", customer);
		result.addObject("message", message);
		result.addObject("urlAction", "customer/customer/edit.do");

		return result;
	}
}
