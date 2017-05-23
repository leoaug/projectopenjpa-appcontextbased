package projectopenjpa.spring.controller;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import bossanovadata.constants.Constants;
import bossanovadata.model.persistence.query.BOSSAQuery;
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
		
			
			SimpleDateFormat sdfFrom = new SimpleDateFormat("dd/MM/yyyy");   
			Date dateFrom = sdfFrom.parse("01/01/1981"); 
			
			SimpleDateFormat sdfTo = new SimpleDateFormat("dd/MM/yyyy");   
			Date dateTo = sdfTo.parse("01/01/2017"); 
			
			
			
			
			
			BOSSAQuery<Author> query = authorService.initialize();
		
			Predicate predAND =	query.groupFilterAND(query.addFilter("dateOfBirth", new Date[]{dateFrom,dateTo}, Constants.OPERATION_BETWEEN),
								 				query.addFilter("music.type.description", "Bossa Nova", Constants.OPERATION_EQUAL)
						  );
			
			Predicate finalQuery = query.groupFilterOR(predAND, 
													  query.addFilter("music.description", "Garota de Ipanema", Constants.OPERATION_EQUAL));
			
			
			
			query.addQuery(finalQuery);
			

			
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
