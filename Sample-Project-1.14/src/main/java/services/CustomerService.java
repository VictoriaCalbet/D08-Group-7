
package services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.LoginService;
import security.UserAccount;
import security.UserAccountService;
import domain.Customer;

@Service
@Transactional
public class CustomerService {

	//Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository	customerRepository;

	//Supporting services ----------------------------------------------------

	@Autowired
	private ActorService		actorService;

	@Autowired
	private UserAccountService	userAccountService;


	//Constructors -----------------------------------------------------------

	public CustomerService() {
		super();
	}

	//Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		Customer result;
		UserAccount userAccount;

		result = new Customer();

		userAccount = this.userAccountService.create("CUSTOMER");
		result.setUserAccount(userAccount);

		return result;
	}

	public Customer save(final Customer customer) {
		Assert.notNull(customer);

		Customer result;

		result = this.customerRepository.save(customer);

		return result;
	}

	public Customer saveFromCreate(final Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(this.actorService.checkAuthority(customer, "CUSTOMER"));

		UserAccount userAccount = customer.getUserAccount();
		userAccount = this.userAccountService.modifyPassword(userAccount);
		customer.setUserAccount(userAccount);

		Customer result;

		result = this.save(customer);

		return result;
	}

	public Customer saveFromEdit(final Customer customer) {
		Assert.notNull(customer);
		Assert.isTrue(this.actorService.checkAuthority(customer, "CUSTOMER"));

		Customer result;

		result = this.save(customer);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = this.customerRepository.findAll();

		return result;
	}

	public Customer findOne(final int customerId) {
		Customer result;

		result = this.customerRepository.findOne(customerId);

		return result;
	}

	//Other business methods -------------------------------------------------

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.customerRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}

}
