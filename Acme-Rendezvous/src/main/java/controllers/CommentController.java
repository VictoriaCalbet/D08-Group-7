package controllers;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import services.RendezvousService;

import domain.Comment;
import domain.Rendezvous;

@Controller
@RequestMapping("/comment")
public class CommentController extends AbstractController{
	
	//Constructor
	
	public CommentController(){
		
		super();
	}
	
	//Supporting services
	
	@Autowired
	private CommentService commentService;
	
	@Autowired
	private RendezvousService rendezvousService;
	
	//Methods
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=true) final int rendezvousId) {
		ModelAndView result;
		
		try{
		Collection<Comment> comments = new ArrayList<Comment>();
		
		
		Rendezvous rendez = this.rendezvousService.findOne(rendezvousId);
		
		Assert.notNull(rendez,"message.error.rendezvous.null");
		
		comments = this.commentService.getOriginalCommentsByRendezvousId(rendez.getId());
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", "comment/list.do");
		result.addObject("comments",comments);
		
		
		}catch(Throwable oops){
			
			String messageError = "comment.commit.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/rendezvous/list.do");
			//result = new ModelAndView("rendezvous/list");
			//Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();
			//result.addObject(rendezvouses);
			result.addObject("message",messageError);
			//result.addObject("requestURI", "rendezvous/list.do");
			//result = new ModelAndView("redirect:/rendezvous/user/list.do");
		}
		
	return result;	
	}
	
	@RequestMapping(value = "/listReplies", method = RequestMethod.GET)
	public ModelAndView listReplies(@RequestParam(required=true) final int commentId) {
		ModelAndView result;
		
		try{
		Collection<Comment> comments = new ArrayList<Comment>();
		
		Comment comment = this.commentService.findOne(commentId);
		Rendezvous rendez = comment.getRendezvous();
		
		Assert.notNull(comment,"message.error.comment.null");
		
		comments = comment.getReplies();
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", "comment/listReplies.do");
		result.addObject("comments",comments);
		result.addObject("rendezvous",rendez);
		
		}catch(Throwable oops){
			
			String messageError = "comment.commit.error";
			if (oops.getMessage().contains("message.error"))
				messageError = oops.getMessage();
			result = new ModelAndView("redirect:/rendezvous/list.do");
			//result = new ModelAndView("rendezvous/list");
			//Collection<Rendezvous> rendezvouses = this.rendezvousService.findAll();
			//result.addObject(rendezvouses);
			result.addObject("message",messageError);
			//result.addObject("requestURI", "rendezvous/list.do");
			//result = new ModelAndView("redirect:/rendezvous/user/list.do");
		}
		
	return result;	
	}

}
