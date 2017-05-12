package projectopenjpa.spring.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projectopenjpa.entity.Author;
import projectopenjpa.entity.Music;
import projectopenjpa.entity.Type;
import projectopenjpa.service.AuthorService;

@Controller
public class HelloController {

	@Autowired
	private AuthorService authorService;


	@RequestMapping("/")
	public String initialize() {
			
		try {
		
			Author author = new Author();
			author.setName("Tom jobim");
			author.setMusic(new Music());
			author.getMusic().setDescription("Garota de Ipanema");
			author.getMusic().setType(new Type());
			author.getMusic().getType().setDescription("Bossa Nova");
			
			authorService.findByEntityList(author);
		
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "welcome"; 
	}
	
	@GetMapping("/hello")
	public String hello(Model model) {

		model.addAttribute("name", "John Doe");

		return "welcome";
	}
}
