package controllers.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import services.CommentService;

import controllers.AbstractController;

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
		
		
}
