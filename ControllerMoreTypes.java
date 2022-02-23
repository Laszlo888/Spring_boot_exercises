package com.store.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/filter-by-type")
public class ControllerMoreTypes {
	
	@Autowired
	WebshopService webshopService;
		
	@GetMapping(path="/{types}")
	public String getTypes(Model model, @PathVariable String types) {
		
		if(types.equals("cloth_and_shoes")) {
			model.addAttribute("clsh", webshopService.getClothShoes());
			return "wsMoreFilters";}
			
			else if(types.equals("electronics")){
				model.addAttribute("el", webshopService.getElectro());
				return "wsMoreFilters";
			}
			else if(types.equals("beverages_and_snacks")){
				model.addAttribute("bevsna", webshopService.getBevSna());
				return "wsMoreFilters";
			}
			else {return "wsMoreFilters";}
	}
			
			






}
