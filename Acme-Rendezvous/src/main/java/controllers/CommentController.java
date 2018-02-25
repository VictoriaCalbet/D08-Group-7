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

import services.ActorService;
import services.CommentService;
import services.RendezvousService;
import services.UserService;

import domain.Actor;
import domain.Comment;
import domain.Rendezvous;
import domain.User;

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
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ActorService actorService;
	
	//Methods
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam(required=true) final int rendezvousId) {
		ModelAndView result;
		
		try{
		Collection<Comment> comments = new ArrayList<Comment>();
		
		
		Rendezvous rendez = this.rendezvousService.findOne(rendezvousId);
		
		Assert.notNull(rendez,"message.error.rendezvous.null");
		
		comments = this.commentService.getOriginalCommentsByRendezvousId(rendez.getId());
		
		final Actor actor = this.actorService.findByPrincipal();
		
		Collection<Rendezvous> principalRendezvouses = new ArrayList<Rendezvous>();
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", "comment/list.do");
		result.addObject("comments",comments);
		result.addObject("rendezvous",rendez);
		
		if(actor!=null){ //If it's logged in
			if(this.actorService.checkAuthority(actor, "USER")){ //checks if the actor is a user
				principalRendezvouses = this.rendezvousService.findAllAttendedByUserId(this.userService.findByPrincipal().getId());
				result.addObject("principalRendezvouses",principalRendezvouses);
			}
		}
		
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
		
		Assert.notNull(this.commentService.findOne(commentId),"message.error.comment.badId");
		Comment comment = this.commentService.findOne(commentId);
		Collection<Rendezvous> principalRendezvouses = new ArrayList<Rendezvous>();
		Rendezvous rendez = comment.getRendezvous();
		
		comments = comment.getReplies();
		
		result = new ModelAndView("comment/list");
		result.addObject("requestURI", "comment/listReplies.do");
		result.addObject("comments",comments);
		result.addObject("rendezvous",rendez);
		
		final Actor actor = this.actorService.findByPrincipal();
		
		if(actor!=null){ //If it's logged in
			if(this.actorService.checkAuthority(actor, "USER")){ //checks if the actor is a user
				principalRendezvouses = this.rendezvousService.findAllAttendedByUserId(this.userService.findByPrincipal().getId());
				result.addObject("principalRendezvouses",principalRendezvouses);
			}
		}
		
		
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
