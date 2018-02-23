
package services.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import security.UserAccount;
import security.UserAccountService;
import services.ActorService;
import services.AdministratorService;
import services.CustomerService;
import domain.Actor;
import domain.Administrator;
import domain.Customer;
import domain.forms.ActorForm;

@Service
@Transactional
public class ActorFormService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService			actorService;

	@Autowired
	private CustomerService			customerService;

	@Autowired
	private AdministratorService	administratorService;

	@Autowired
	private UserAccountService		userAccountService;


	// Constructors -----------------------------------------------------------

	public ActorFormService() {
		super();
	}

	public ActorForm create() {
		final ActorForm result = new ActorForm();
		final Actor principal = this.actorService.findByPrincipal();

		if (this.actorService.checkAuthority(principal, "CUSTOMER") || this.actorService.checkAuthority(principal, "ADMIN")) {
			result.setName(principal.getName());
			result.setSurname(principal.getSurname());
			result.setPhone(principal.getPhone());
			result.setUsername(principal.getUserAccount().getUsername());
		}

		return result;
	}

	public Actor saveFromCreate(final ActorForm actorForm) {
		Assert.notNull(actorForm);
		Assert.isTrue(actorForm.getPassword().equals(actorForm.getRepeatedPassword()), "actorForm.error.passwordMismatch");
		Assert.isTrue(actorForm.getAcceptTerm(), "actorForm.error.termsDenied");

		Customer customer;
		Customer result;
		UserAccount userAccount;

		userAccount = this.userAccountService.createComplete(actorForm.getName(), actorForm.getSurname(), "CUSTOMER");
		customer = this.customerService.create();
		customer.setName(actorForm.getName());
		customer.setSurname(actorForm.getSurname());
		customer.setPhone(actorForm.getPhone());
		customer.setUserAccount(userAccount);

		result = this.customerService.saveFromCreate(customer);

		return result;
	}

	public Actor saveFromEdit(final ActorForm actorForm) {
		Assert.notNull(actorForm);
		Assert.isTrue(actorForm.getPassword().equals(actorForm.getRepeatedPassword()), "actorForm.error.passwordMismatch");

		final Actor principal = this.actorService.findByPrincipal();
		UserAccount userAccount;
		final String password;

		userAccount = principal.getUserAccount();
		password = actorForm.getPassword();

		userAccount.setUsername(actorForm.getUsername());
		userAccount.setPassword(password);
		userAccount = this.userAccountService.modifyPassword(userAccount);

		if (this.actorService.checkAuthority(principal, "CUSTOMER")) {
			final Customer customer = this.customerService.findByPrincipal();
			customer.setName(actorForm.getName());
			customer.setSurname(actorForm.getSurname());
			customer.setPhone(actorForm.getPhone());
			customer.setUserAccount(userAccount);

			Customer result;

			result = this.customerService.saveFromEdit(customer);
			return result;

		} else {
			final Administrator administrator = this.administratorService.findByPrincipal();
			administrator.setName(actorForm.getName());
			administrator.setSurname(actorForm.getSurname());
			administrator.setPhone(actorForm.getPhone());
			administrator.setUserAccount(userAccount);

			Administrator result;

			result = this.administratorService.saveFromEdit(administrator);
			return result;
		}
	}

}
