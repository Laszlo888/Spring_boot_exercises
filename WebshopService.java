package com.store.webshop;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class WebshopService {

	List<ShopItem> shItem;

	// ha a konstruktort így adjuk át akkor frissítésnél nem fog duplázódni a
	// tartalom folyamatosan. Ezek az elemek kezdésnél már létrejönnek.
	public WebshopService() {
		this.shItem = new ArrayList<>();
		shItem.add(new ShopItem("Running shoes", "Nike running shoes for every day sport", 1000.0, 5,
				"Clothes and Shoes"));
		shItem.add(new ShopItem("Printer", "Some HP printer that will print pages", 3000.0, 2, "Electronics"));
		shItem.add(new ShopItem("Coca cola", "0.5l standard coke", 25.0, 0, "Beverages and Snacks"));
		shItem.add(
				new ShopItem("Wokin", "Chicken with fried rice and WOKIN sauce", 119.0, 100, "Beverages and Snacks"));
		shItem.add(new ShopItem("T-shirt", "Blue with a corgi on a bike", 300.0, 1, "Clothes and Shoes"));
	}

	public List<ShopItem> getShItem() {
		return shItem;
	}

	public void setShItem(List<ShopItem> shItem) {
		this.shItem = shItem;
	}

	
	// only-available
	List<ShopItem> oAv = new ArrayList<>();

	public List<ShopItem> getOav() {
		oAv = shItem.stream().filter(barmi -> barmi.getQstock() > 0).collect(Collectors.toList());
		return oAv;
	}

	
	// cheapest-first
	List<ShopItem> chFirst = new ArrayList<>();

	public List<ShopItem> getChFirst() {
		chFirst = shItem.stream().sorted(Comparator.comparingDouble(ShopItem::getPrice)).collect(Collectors.toList());
		return chFirst;
	}

	
	// contains-nike (name or description)
	List<ShopItem> coNike = new ArrayList<>();

	public List<ShopItem> getCoNike() {

		coNike = shItem.stream().filter(p -> p.getName().toLowerCase().contains("nike")).collect(Collectors.toList());
		if (coNike.isEmpty()) {
			coNike = shItem.stream().filter(p -> p.getDescription().toLowerCase().contains("nike"))
					.collect(Collectors.toList());
		} else {
		}

		return coNike;
	}

	
	// average stock
	public String getAverageStock() {
		String stock;
		double average = shItem.stream().mapToInt(a -> a.getQstock()).average().orElse(0);
		stock = "Average stock: " + String.valueOf(average);
		return stock;
	}

	
	// most expensive amelyik készleten van
	public String getMostExpensive() {
		String stock;
		ShopItem mostExp = getOav().stream().max(Comparator.comparing(v -> v.getPrice())).get();
		stock = mostExp.name + "  " + String.valueOf(mostExp.price);
		return stock;
	}

	// keresősáv találatainak megjelenítése
	List<ShopItem> searchResult = new ArrayList<>();

	public List<ShopItem> getSearchResults(String value) {

		String searchValueToLowercase = value.toLowerCase(); // kisbetűssé tesszük a beírt keresett kifejezést
		// a streamben is kisbetűssé alakítjuk az adatbázisunk stringjeit
		searchResult = shItem.stream().filter(p -> p.getName().toLowerCase().contains(searchValueToLowercase))
				.collect(Collectors.toList());
		if (searchResult.isEmpty()) {
			searchResult = shItem.stream()
					.filter(p -> p.getDescription().toLowerCase().contains(searchValueToLowercase))
					.collect(Collectors.toList());
		} else {
		}

		return searchResult;
	}

	
	// szűrés ruhára,cipőre
	List<ShopItem> clothShoes = new ArrayList<>();

	public List<ShopItem> getClothShoes() {
		clothShoes = shItem.stream().filter(s -> s.getType().contains("Clothes")).collect(Collectors.toList());
		return clothShoes;
	}

	
	// szűrés elektronikára
	List<ShopItem> electro = new ArrayList<>();

	public List<ShopItem> getElectro() {
		electro = shItem.stream().filter(s -> s.getType().contains("Electro")).collect(Collectors.toList());
		return electro;
	}

	
	// szűrés italokra,ropogtatni valókra
	List<ShopItem> bevSna = new ArrayList<>();

	public List<ShopItem> getBevSna() {
		bevSna = shItem.stream().filter(s -> s.getType().contains("Beverage")).collect(Collectors.toList());
		return bevSna;
	}

	
	// ár euro-ra átszámítása
	List<ShopItem> prEu = new ArrayList<>();

	public List<ShopItem> getEuro() {
		prEu = shItem.stream().map(
				z -> new ShopItem(z.getName(), z.getDescription(), z.getPrice() * 0.03871, z.getQstock(), z.getType()))
				.collect(Collectors.toList());
		return prEu;
	}

	
	// bizonyos ár fölöttiek
	List<ShopItem> prAbove = new ArrayList<>();

	public List<ShopItem> getPriceAbove(double ab) {
		prAbove = shItem.stream().filter(a -> a.getPrice() > ab).collect(Collectors.toList());
		return prAbove;
	}

	
	// bizonyos ár alattiak
	List<ShopItem> prBelow = new ArrayList<>();

	public List<ShopItem> getPriceBelow(double abc) {
		prBelow = shItem.stream().filter(a -> a.getPrice() < abc).collect(Collectors.toList());
		return prBelow;
	}

	
	// bizonyos árral egyezőek
	List<ShopItem> prExactly = new ArrayList<>();

	public List<ShopItem> getPriceExactly(double abcd) {
		prExactly = shItem.stream().filter(a -> a.getPrice().equals(abcd)).collect(Collectors.toList());
		return prExactly;
	}
	
	
	// segédfüggvény: ellenőrizzük, hogy számot írtak-e be a szűrő mezőbe
	public boolean checkNumberOrNot(String input) {
		boolean stringIsDigits = true;
	      if(input.length()>0){
	      for(int i = 0; i < input.length(); i++) {
	        if(Character.isDigit(input.charAt(i))==false){
	            stringIsDigits=false;
	            break;
	        }
	      }}
	      else{stringIsDigits=false;}
	      return stringIsDigits;
	}

}
