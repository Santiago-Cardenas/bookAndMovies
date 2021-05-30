package ui;

import model.ProductType;
import model.Shop;

/**
 * Clase que declara productos y los agrega al catalogo para hacer pruebas.
 * Last modified on may 2021
 * @author avillota
 *	
 */
public class TestScenario {
	
	public void createScenario1(Shop shop) {
		//Creating and adding Books
		
		//Products for rent
		shop.addProduct("B001", "Harry Potter y la piedra filosofal",5000, ProductType.BOOK);
		//Products for sale
		shop.addProduct("B002", "Harry Potter y la piedra filosofal",10, 85000, ProductType.BOOK);
		
		//Products for rent
		shop.addProduct("B003", "Harry Potter y la cámara de los secretos",5000, ProductType.BOOK);
		//Products for sale
		shop.addProduct("B004", "El señor de los anillos, la comunidad del anillo",10, 50000, ProductType.BOOK);

		
		
		
		//Creating and adding Magazines
		shop.addProduct("M001", "Nintendo",3000, ProductType.MAGAZINE);
		//Products for sale
		shop.addProduct("M002", "SOHO",20, 20000, ProductType.MAGAZINE);
		
		//Creating and adding DVD's
		shop.addProduct("D001", "Love Actually",3000, ProductType.DVD_MOVIE);
		//Products for sale
		shop.addProduct("D002", "Cars",10, 13000, ProductType.DVD_MOVIE);
		
		//Creating and adding Downloadable Movies
		shop.addProduct("DM001", "Mulan 2020",5000, ProductType.DOWNLOAD_MOVIE);
		//Products for sale
		shop.addProduct("DM002", "Cruella",10, 100000, ProductType.DOWNLOAD_MOVIE);
		
	
		
		
	}

}
