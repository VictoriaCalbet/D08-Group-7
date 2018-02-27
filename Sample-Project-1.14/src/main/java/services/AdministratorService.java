
package services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.LoginService;
import security.UserAccount;
import domain.Administrator;

@Service
@Transactional
public class AdministratorService {

	//Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository	administratorRepository;


	//Supporting services ----------------------------------------------------

	//Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	//Simple CRUD methods ----------------------------------------------------

	public Administrator saveFromEdit(final Administrator administrator) {
		Assert.notNull(administrator);

		Administrator result;

		result = this.administratorRepository.save(administrator);

		return result;
	}

	//Other business methods -------------------------------------------------

	public Administrator findByPrincipal() {
		Administrator result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		Assert.notNull(userAccount);
		result = this.administratorRepository.findByUserAccountId(userAccount.getId());
		Assert.notNull(result);

		return result;
	}
}
