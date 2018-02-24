/*
 * AdministratorServiceTest.java
 * 
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the
 * TDG Licence, a copy of which you may download from
 * http://www.tdg-seville.info/License.html
 */

package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.RSVP;
import domain.Rendezvous;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
	"classpath:spring/datasource.xml", "classpath:spring/config/packages.xml"
})
@Transactional
public class RSVPServiceTest extends AbstractTest {

	// System under test ------------------------------------------------------
	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private RSVPService			rsvpService;

	@Autowired
	private UserService			userService;


	// Tests ------------------------------------------------------------------

	@Test
	public void testCreate1() {
		this.authenticate("user1");
		final Rendezvous rendezvous = this.rendezvousService.findAll().iterator().next();

		final RSVP createdRSVP = this.rsvpService.create(rendezvous);

		Assert.notNull(createdRSVP);
		this.unauthenticate();
	}
	@Test
	public void testRSVPaRendezvous() {
		this.authenticate("user2");
		final Rendezvous rendezvous = this.rendezvousService.findAll().iterator().next();
		final User principal = this.userService.findByPrincipal();
		final User creator = this.userService.findOne(33);
		rendezvous.setCreator(creator);
		final RSVP createdRSVP = this.rsvpService.create(rendezvous);
		Assert.notNull(createdRSVP);

		Collection<RSVP> principalRSVPS = principal.getRsvps();
		this.rsvpService.save(createdRSVP);
		principalRSVPS = principal.getRsvps();

		this.rsvpService.RSVPaRendezvous(createdRSVP.getRendezvous().getId());

		Assert.isTrue(!principalRSVPS.contains(createdRSVP));

		this.unauthenticate();
	}
}
