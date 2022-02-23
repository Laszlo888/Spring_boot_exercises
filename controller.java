package com.store.webshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

	@Autowired
	WebshopService webshopService;

	@RequestMapping("/webshop")
	public String addItems(Model model) {
		model.addAttribute("elso", webshopService.getShItem());
		return "ws";
	}

	@GetMapping("/only-available")
	public String onlyAvailable(Model model) {
		model.addAttribute("masodik", webshopService.getOav());
		return "ws";
	}

	@GetMapping("/cheapest-first")
	public String cheapestFirst(Model model) {
		model.addAttribute("cheapest", webshopService.getChFirst());
		return "ws";
	}

	@GetMapping("/contains-nike")
	public String containsNike(Model model) {
		model.addAttribute("cnike", webshopService.getCoNike());
		return "ws";
	}

	@GetMapping("/average-stock")
	public String averageStock(Model model) {
		model.addAttribute("aveStock", webshopService.getAverageStock());
		return "wslight";
	}

	@GetMapping("/most-expensive")
	public String mostExpensive(Model model) {
		model.addAttribute("mExp", webshopService.getMostExpensive());
		return "wslight";
	}

	// search mező beolvasásához
	@PostMapping(value = "/search")
	public String takeTheString(@RequestBody String value, Model model) {

		// megkaptuk a http kérés body részét így wsearh=beírt string. Leszedjük az
		// egyenlőségjel előtti részt róla
		// mert csak a value érdekel minket az input mező neve nem
		int equalSignIndex = value.lastIndexOf('=');
		String result = value.substring(equalSignIndex + 1, value.length());
		model.addAttribute("searchf", webshopService.getSearchResults(result));
		return "ws";
	}

	@GetMapping("/more-filters")
	public String moreFilters(Model model) {
		model.addAttribute("mFilt", webshopService.getShItem());
		return "wsMoreFilters";
	}

	@GetMapping("/price-in-eur")
	public String pEuro(Model model) {
		model.addAttribute("eu", webshopService.getEuro());
		return "wsMoreFilters";
	}

	@GetMapping("/price-in-original")
	public String getOrCurrency(Model model) {
		model.addAttribute("mFilt", webshopService.getShItem());
		return "wsMoreFilters";
	}

	// több submit gomb egy formban. A name paraméterük alapján tudjuk
	// megkülönböztetni őket

	@PostMapping(value = "/filter-by-price", params = "above")
	public String filtByPriceAbove(@RequestBody String aboveValue, Model model) {
		// megkaptuk a http kérés body részét így pl.: above=Above&wsearchMore=beírt
		// String.
		// Leszedjük az utolsó egyenlőségjel előtti részt róla
		// mert csak a wsearchMore value érdekel minket

		int equalSignIndex = aboveValue.lastIndexOf('=');
		String result = aboveValue.substring(equalSignIndex + 1, aboveValue.length());

		// ellenőrizzük számot írtak-e be vagy nem írtak be semmit
		boolean stringIsDigits = webshopService.checkNumberOrNot(result);
		if (stringIsDigits == true) {
			double pr = Double.parseDouble(result);
			model.addAttribute("pa", webshopService.getPriceAbove(pr));
			return "wsMoreFilters";
		} else {
			model.addAttribute("mFilt", webshopService.getShItem());
			return "wsMoreFilters";
		}
	}

	@PostMapping(value = "/filter-by-price", params = "below")
	public String filtByPriceBelow(@RequestBody String aboveValue, Model model) {
		int equalSignIndex = aboveValue.lastIndexOf('=');
		String result = aboveValue.substring(equalSignIndex + 1, aboveValue.length());

		// ellenőrizzük számot írtak-e be vagy nem írtak be semmit
		boolean stringIsDigits = webshopService.checkNumberOrNot(result);
		if (stringIsDigits == true) {
			double pr = Double.parseDouble(result);
			model.addAttribute("pb", webshopService.getPriceBelow(pr));
			return "wsMoreFilters";
		} else {
			model.addAttribute("mFilt", webshopService.getShItem());
			return "wsMoreFilters";
		}
	}

	@PostMapping(value = "/filter-by-price", params = "exactly")
	public String filtByPriceExactly(@RequestBody String aboveValue, Model model) {
		int equalSignIndex = aboveValue.lastIndexOf('=');
		String result = aboveValue.substring(equalSignIndex + 1, aboveValue.length());

		// ellenőrizzük számot írtak-e be vagy nem írtak be semmit
		boolean stringIsDigits = webshopService.checkNumberOrNot(result);
		if (stringIsDigits == true) {
			double pr = Double.parseDouble(result);
			model.addAttribute("pe", webshopService.getPriceExactly(pr));
			return "wsMoreFilters";
		} else {
			model.addAttribute("mFilt", webshopService.getShItem());
			return "wsMoreFilters";
		}
	}

}
