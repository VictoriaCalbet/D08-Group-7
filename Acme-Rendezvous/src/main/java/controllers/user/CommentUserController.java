package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RendezvousService;
import services.form.CommentFormService;

import controllers.AbstractController;
import domain.Comment;
import domain.Rendezvous;
import domain.form.CommentForm;

@Controller
@RequestMapping("/comment/user")
public class CommentUserController extends AbstractController{

	
	//Constructor
	
		public CommentUserController(){
			
			super();
		}
		
	//Supporting services
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private CommentFormService commentFormService;
	
	@Autowired
	private RendezvousService rendezvousService;
	
	// Creation ----------------------------------------------------------------

		@RequestMapping(value = "/create", method = RequestMethod.GET)
		public ModelAndView create() {
			final ModelAndView result;
			CommentForm commentForm;

			commentForm = this.commentFormService.create();
			result = this.createModelAndView(commentForm);

			return result;

		}
		
		@RequestMapping(value = "/reply", method = RequestMethod.GET)
		public ModelAndView reply() {
			final ModelAndView result;
			CommentForm commentForm;

			commentForm = this.commentFormService.create();
			result = this.createModelAndView(commentForm);

			return result;

		}

		@RequestMapping(value = "/create", method = RequestMethod.POST, params = "save")
		public ModelAndView create(@ModelAttribute("commentForm") @Valid final CommentForm commentForm, @RequestParam(required=true) final int rendezvousId, final BindingResult binding) {
			//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createModelAndView(commentForm);
			else
				try {
					Assert.notNull(commentForm.getText(),"message.error.commentForm.text");
					Rendezvous rendezvous = this.rendezvousService.findOne(rendezvousId);
					this.commentFormService.saveFromCreate(commentForm, rendezvous);
					result = new ModelAndView("redirect:/rendezvous/list.do");
				} catch (final Throwable oops) {
					String messageError = "comment.commit.error";
					if (oops.getMessage().contains("message.error"))
						messageError = oops.getMessage();
					result = this.createModelAndView(commentForm, messageError);
				}

			return result;
		}
		
		@RequestMapping(value = "/reply", method = RequestMethod.POST, params = "save")
		public ModelAndView reply(@ModelAttribute("commentForm") @Valid final CommentForm commentForm, @RequestParam(required=true) final int commentId, final BindingResult binding) {
			//		this.rendezvousFormValidator.validate(rendezvousForm, binding);

			ModelAndView result;

			if (binding.hasErrors())
				result = this.createModelAndView(commentForm);
			else
				try {
					Comment comment = this.commentService.findOne(commentId);
					this.commentFormService.saveReply(commentForm, comment);
					result = new ModelAndView("redirect:/rendezvous/list.do");
				} catch (final Throwable oops) {
					String messageError = "comment.commit.error";
					if (oops.getMessage().contains("message.error"))
						messageError = oops.getMessage();
					result = this.createModelAndView(commentForm, messageError);
				}

			return result;
		}
		
		
		// Ancillaty methods
		protected ModelAndView createModelAndView(final CommentForm commentForm) {
			ModelAndView result;

			result = this.createModelAndView(commentForm, null);

			return result;
		}

		protected ModelAndView createModelAndView(final CommentForm commentForm, final String message) {
			ModelAndView result;

			result = new ModelAndView("comment/user/create");
			result.addObject("commentForm", commentForm);
			result.addObject("message", message);
			

			return result;
		}
	
		
		
}
