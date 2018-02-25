
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

import services.AnnouncementService;
import services.UserService;
import controllers.AbstractController;
import domain.Announcement;
import domain.Rendezvous;
import domain.User;

@Controller
@RequestMapping("/announcement/user")
public class AnnouncementUserController extends AbstractController {

	// Services -------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private UserService			userService;


	// Constructors ---------------------------------------------------------

	public AnnouncementUserController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;
		Collection<Announcement> announcements = null;
		User user = null;

		announcements = new ArrayList<Announcement>();
		user = this.userService.findByPrincipal();

		announcements = this.announcementService.getAnnouncementsCreatedByUser(user.getId());

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("requestURI", "announcement/user/list.do");

		return result;
	}

	@RequestMapping(value = "/stream", method = RequestMethod.GET)
	public ModelAndView stream() {
		ModelAndView result = null;
		Collection<Announcement> announcements = null;
		User user = null;

		announcements = new ArrayList<Announcement>();
		user = this.userService.findByPrincipal();

		announcements = this.announcementService.getAnnouncementsPostedAndAcceptedByUser(user.getId());

		result = new ModelAndView("announcement/list");
		result.addObject("announcements", announcements);
		result.addObject("requestURI", "announcement/user/stream.do");

		return result;
	}

	// Creation  ------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		final ModelAndView result;
		Announcement announcement;

		announcement = this.announcementService.create();
		result = this.createEditModelAndView(announcement);

		return result;
	}

	// Display --------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam final int announcementId) {
		ModelAndView result = null;
		Announcement announcement = null;
		String cancelURI = null;

		cancelURI = "/";
		announcement = this.announcementService.findOne(announcementId);

		result = new ModelAndView("announcement/display");
		result.addObject("announcement", announcement);
		result.addObject("cancelURI", cancelURI);

		return result;
	}

	// Edition    -----------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam final int announcementId) {
		ModelAndView result = null;
		Announcement announcement = null;

		announcement = this.announcementService.findOne(announcementId);

		result = this.createEditModelAndView(announcement);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView edit(@Valid final Announcement announcement, final BindingResult binding) {
		ModelAndView result = null;

		if (binding.hasErrors())
			result = this.createEditModelAndView(announcement);
		else
			try {
				this.announcementService.save(announcement);
				result = new ModelAndView("redirect:/announcement/list.do?rendezvousId=" + announcement.getRendezvous().getId());
			} catch (final Throwable oops) {
				String messageError = "announcement.commit.error";
				if (oops.getMessage().contains("message.error"))
					messageError = oops.getMessage();
				result = this.createEditModelAndView(announcement, messageError);
			}

		return result;
	}

	// Ancillary methods ----------------------------------------------------

	protected ModelAndView createEditModelAndView(final Announcement announcement) {
		ModelAndView result = null;
		result = this.createEditModelAndView(announcement, null);
		return result;
	}

	protected ModelAndView createEditModelAndView(final Announcement announcement, final String message) {
		ModelAndView result = null;
		User user = null;
		Collection<Rendezvous> rendezvouses = null;

		user = this.userService.findByPrincipal();
		rendezvouses = user.getRendezvoussesCreated();

		result = new ModelAndView("announcement/edit");
		result.addObject("announcement", announcement);
		result.addObject("rendezvouses", rendezvouses);
		result.addObject("message", message);

		return result;
	}
}
