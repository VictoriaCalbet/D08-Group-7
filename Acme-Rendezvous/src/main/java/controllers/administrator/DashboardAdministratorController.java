
package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AnnouncementService;
import services.CommentService;
import services.QuestionService;
import services.RendezvousService;
import services.UserService;
import domain.Rendezvous;

@Controller
@RequestMapping("/administrator")
public class DashboardAdministratorController {

	// Services -------------------------------------------------------------

	@Autowired
	private AnnouncementService	announcementService;

	@Autowired
	private CommentService		commentService;

	@Autowired
	private RendezvousService	rendezvousService;

	@Autowired
	private QuestionService		questionService;

	@Autowired
	private UserService			userService;


	// Constructors ---------------------------------------------------------

	public DashboardAdministratorController() {
		super();
	}

	// Listing --------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result = null;

		final Double avgAnnouncementPerRendezvous = this.announcementService.findAvgAnnouncementPerRendezvous();
		final Double stdAnnouncementPerRendezvous = this.announcementService.findStdAnnouncementPerRendezvous();

		final Double avgRepliesPerComment = this.commentService.findAvgRepliesPerComment();
		final Double stdRepliesPerComment = this.commentService.findStdRepliesPerComment();

		final Double avgRendezvousesCreatedPerUser = this.rendezvousService.findAvgRendezvousesCreatedPerUser();
		final Double stdRendezvousesCreatedPerUser = this.rendezvousService.findStdRendezvousesCreatedPerUser();
		final Double avgRendezvousRSVPsPerUsers = this.rendezvousService.findAvgRendezvousRSVPsPerUsers();
		final Double stdRendezvousRSVPsPerUsers = this.rendezvousService.findStdRendezvousRSVPsPerUsers();
		final Collection<Rendezvous> rendezvousByRSVPs = this.rendezvousService.findAllRendezvousByRSVPs();
		final Collection<Rendezvous> findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous = this.rendezvousService.findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous();
		final Collection<Rendezvous> rendezvousesThatLinkedToRvGreaterThanAvgPlus10 = this.rendezvousService.findRendezvousesThatLinkedToRvGreaterThanAvgPlus10();
		final Double avgNoQuestionPerRendezvous = this.rendezvousService.findAvgNoQuestionPerRendezvous();
		final Double stdNoQuestionPerRendezvous = this.rendezvousService.findStdNoQuestionPerRendezvous();

		final Double avgQuestionsPerRendezvous = this.questionService.findAvgQuestionsPerRendezvous();
		final Double stdQuestionsPerRendezvous = this.questionService.findStdQuestionsPerRendezvous();

		final Double ratioUserRendezvousesCreatedVsNeverCreated = this.userService.findRatioUserRendezvousesCreatedVsNeverCreated();
		final Double avgUsersRSVPsPerRendezvous = this.userService.findAvgUsersRSVPsPerRendezvous();
		final Double stdUsersRSVPsPerRendezvous = this.userService.findStdUsersRSVPsPerRendezvous();

		result = new ModelAndView("administrator/dashboard");

		result.addObject("avgAnnouncementPerRendezvous", avgAnnouncementPerRendezvous);
		result.addObject("stdAnnouncementPerRendezvous", stdAnnouncementPerRendezvous);
		result.addObject("avgRepliesPerComment", avgRepliesPerComment);
		result.addObject("stdRepliesPerComment", stdRepliesPerComment);
		result.addObject("avgRendezvousesCreatedPerUser", avgRendezvousesCreatedPerUser);
		result.addObject("stdRendezvousesCreatedPerUser", stdRendezvousesCreatedPerUser);
		result.addObject("avgRendezvousRSVPsPerUsers", avgRendezvousRSVPsPerUsers);
		result.addObject("stdRendezvousRSVPsPerUsers", stdRendezvousRSVPsPerUsers);
		result.addObject("rendezvousByRSVPs", rendezvousByRSVPs);
		result.addObject("findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous", findAllRendezvousNoAnnouncementsIsAbove75PerCentNoAnnouncementPerRendezvous);
		result.addObject("rendezvousesThatLinkedToRvGreaterThanAvgPlus10", rendezvousesThatLinkedToRvGreaterThanAvgPlus10);
		result.addObject("avgNoQuestionPerRendezvous", avgNoQuestionPerRendezvous);
		result.addObject("stdNoQuestionPerRendezvous", stdNoQuestionPerRendezvous);
		result.addObject("avgQuestionsPerRendezvous", avgQuestionsPerRendezvous);
		result.addObject("stdQuestionsPerRendezvous", stdQuestionsPerRendezvous);
		result.addObject("ratioUserRendezvousesCreatedVsNeverCreated", ratioUserRendezvousesCreatedVsNeverCreated);
		result.addObject("avgUsersRSVPsPerRendezvous", avgUsersRSVPsPerRendezvous);
		result.addObject("stdUsersRSVPsPerRendezvous", stdUsersRSVPsPerRendezvous);

		result.addObject("requestURI", "administrator/dashboard.do");

		return result;
	}

	// Display --------------------------------------------------------------

	// Creation  ------------------------------------------------------------

	// Edition    -----------------------------------------------------------

	// Ancillary methods ----------------------------------------------------
}
