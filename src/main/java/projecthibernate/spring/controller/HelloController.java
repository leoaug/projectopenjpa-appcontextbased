package projecthibernate.spring.controller;


import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import projecthibernate.entity.Author;
import projecthibernate.entity.Music;
import projecthibernate.entity.Type;
import projecthibernate.service.AuthorService;

@Controller
public class HelloController {

	@Autowired
	private AuthorService authorService;

	@PostConstruct
	public void init(){
		Author author = new Author();
		System.out.println(author);
	}

	@RequestMapping("/")
	public String initialize() {
			
		try {
		
			Author author = new Author();
			author.setName("Tom jobim");
			author.setMusic(new Music());
			author.getMusic().setDescription("Garota de Ipanema");
			author.getMusic().setType(new Type());
			author.getMusic().getType().setDescription("Bossa Nova");
			
			authorService.consultarPorEntidadeList(author);
		
			
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
