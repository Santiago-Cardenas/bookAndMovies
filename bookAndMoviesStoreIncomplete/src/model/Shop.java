package model;

import java.util.ArrayList;
import java.util.Date;

/**
 * 
 * @author angievig
 *
 */
public class Shop {

	/**
	 * Constante con el valor del impuesto
	 */
	public static final double TAX_IVA= 0.19;
	
	/**
	 * Constantes para definir la operación a ejecutar
	 */
	public static final int RENT=1;
	public static final int SALE=2;
	
	/**
	 * Nombre de la tienda
	 */
	private String name;
	
	/**
	 * Lista de productos que representa el catalogo
	 */
	private ArrayList<Product> catalog;
	
	/**
	 * Valor acumulado de las ventas 
	 */
	private double totalSales;
	
	/**
	 * Valor acumulado de los alquileres
	 */
	private double totalRents;
	

	/**
	 * Metodo constructor
	 * pos: se inicializa el errayList de productos
	 * totalSales y totalRents son inicializados en cero
	 * @param aName es el nombre de la tienda
	 */
	public Shop(String aName) {
		name = aName;
		catalog = new ArrayList<Product>();
		totalSales=0;
		totalRents=0;
		
	}


	/**
	 * Método que agrega al catalogo un producto para vender
	 * pre: el arrayList catalog está inicializado
	 * pos: catalog contiene un nuevo producto, si ya existe un producto
	 * con el mismo código no hay cambios.
	 * @param code
	 * @param name
	 * @param units
	 * @param price
	 * @param type
	 * @return una cadena informando si el producto fue agregado al catalogo o un mensaje
	 * informando que el producto ya existe. 
	 */
	public String addProduct(String code,String name, int units, double price, ProductType type) {
		
		boolean found=false;
		String msg="";

		for(int pos=0; pos<catalog.size() && !found; pos++){

			if(catalog.get(pos).getCode().equalsIgnoreCase(code)){
				msg+="This product already exist";
				found=true;
			}

		}

		if(!found){
			ProductForSale product= new ProductForSale(code,name,units,price,type);
			catalog.add(product);
			msg+="The product has been registered";
		}
		return msg;
	}
	


	/**
	 * Método que agrega al catalogo un producto para alquiler, 
	 * pre: el arrayList catalog está inicializado
	 * pos: catalog contiene un nuevo producto, si ya existe un producto
	 * con el mismo código no hay cambios. 
	 * @param code, código del producto
	 * @param name, nombre del producto
	 * @param price, precio del alquiler 
	 * @param type, tipo del producto
	 * @return una cadena informando si el producto fue agregado al catalogo o un mensaje
	 * informando que el producto ya existe. 
	 */
	public String addProduct(String code, String name, double price, ProductType type) {
		boolean found=false;
		String msg="";
		
		for(int pos=0; pos<catalog.size() && !found; pos++){

			if(catalog.get(pos).getCode().equalsIgnoreCase(code)){
				msg+="This product already exist";
				found=true;
			}

		}

		if(!found){
			ProductForRent product= new ProductForRent(code,name,price,type);
			catalog.add(product);
			msg+="The product has been registered";
		}

		return msg;
	}
	
	/**
	 * Metodo que crea una cadena con la información de los productos
	 * que hay en el catalogo.
	 * @return cadena con la informacion de los productos
	 */
	public String showCatalog() {
		String msg="";

		for(int pos=0; pos<catalog.size(); pos++){
			msg+= catalog.get(pos).getInformation() + "\n\n";
		}
		return msg;
	}
	
	/**
	 * Método que busca un producto en el catalogo por código
	 * pre: catalog es un arrayList que ha sido inicializado
	 * @param code, cadena con el código a buscar 
	 *        code!= null && code != ""
	 * @return el objeto de la clase Product con el mismo
	 * código que el parámetro, null en el caso en que la lista
	 * no contiene un producto con ese código
	 */
	public Product findProduct(String code) {
		Product product=null;
		Boolean found=false;
			for(int pos=0; pos<catalog.size() && !found ;pos++){
				if(catalog.get(pos).getCode().equalsIgnoreCase(code)){
					product=catalog.get(pos);
					found=true;
				}
			}
		return product;
	}
	
	
	/*
	 * ================================================
	 * Metodos para las operaciones de venta y alquiler
	 * ================================================
	 */
	
	/**
	 * Método que busca un producto por código e informa el tipo 
	 * del producto usando las constantes definidas en esta clase
	 * @param code es una cadena con el código 
	 * @return SALE si es para vender, 
	 * RENT si es para alquilar
	 */
	public int getOperation(String code) {
		int operation= -1;
		Product p = findProduct(code);

		if(p instanceof Saleable) {
			operation=SALE;
		}else if (p instanceof Rentable){
			operation=RENT;
		}
		return operation;
		
	}
	/**
	 * Método que recibe los datos para hacer una venta y llama al 
	 * método que se encarga de hacer el proceso de ventas asegurándose
	 * que solo se procesen objetos que implementen la interfaz Saleable
	 * @param cod String con el codigo del producto
	 * @param units int con la cantidad de unidades a vender
	 * @param discount double con el porcentaje de descuento extra (numero entre 0 y 1)
	 * @return mensaje de respuesta de la venta
	 */
	public String saleProduct(String cod, int units, double discount) {
		String answer="";
		
		Product p = findProduct(cod);
		answer= sale((Saleable)p, units, discount);
		return answer;
		
	}
	/**
	 * Método que recibe los datos para hacer un alquiler y llama al 
	 * método que se encarga de hacer el proceso de alquiler asegurándose
	 * que solo se procesen objetos que implementen la interfaz Rentable
	 * @param cod String con el codigo del producto
	 * @param days int con la cantidad de días 
	 * @return mensaje de respuesta del alquiler
	 */
	public String rentProduct(String cod, int days) {
		String answer="";
		
		Product p = findProduct(cod);
		answer= rent((Rentable)p, days);
		return answer;
		
	}	

	/**
	 * Metodo que realiza el proceso de venta, usando los métodos
	 * de la interfaz Saleable
	 * Pre: la variable totalSales acumula el total de ventas
	 * Pos: 
	 * - Se incrementa totalSales con el monto total de la venta 
	 * - A la cantidad de unidades de un producto se descuentan
	 *  las unidades vendidas.
	 * @param p objeto que implementa la interfaz Saleable
	 * @param units int con la cantidad de unidades a vender
	 * @param discount double con el porcentaje de descuento extra
	 *  (numero entre 0 y 1)units int cantidad de unidades a vender
	 * @return un mensaje con el resultado de la venta
	 */
	private  String sale(Saleable p, int units, double discount) {
		
		/*
		 * Para hacer una venta
		 * 1. Se verifica si es seguro vender, es decir, si
		 * hay suficientes unidades para vender
		 * si es seguro: 
		 * 	2. se calcula el precio de la venta 
		 * 	3. se aplica el descuento extra
		 * 	4. se calcula el monto de los impuestos
		 * 	5. Se calcula el total a pagar y se incrementa el total de ventas
		 * 	6. Se retorna un mensaje con el total a pagar y confirmando la venta
		 * si no: 
		 *  - Se muestra un mensaje reportando el error.
		 */
		String msg="";
		double totalPrice=0;
		if(p.isSafeSale(units)){
            totalPrice= p.getSalePrice(units);
            totalPrice= p.applyExtraDiscount(totalPrice,discount);
            totalPrice= p.calculateTax(totalPrice,TAX_IVA);
            totalSales++;
            msg+= "The product was sold at: " + totalPrice;
        }
        else {
            msg+="This product is not available for sale";
        }
        return msg;
	}
	
	/**
	 *  Metodo que realiza el proceso de alquiler usando los métodos de la 
	 *  interfaz Rentable
	 * Pre: la variable totalRents acumula el total de alquileres
	 * Pos: 
	 * - Se incrementa totalRents con el monto total de la venta 
	 * - Se cambia el estado del producto a RENTED
	 * @param  p objeto de una clase que implementa la interfaz Rentable
	 * @param days cantidad de días que se alquila un producto
	 * @return un mensaje con el resultado del alquiler
	 */
	private  String rent(Rentable p, int days) {
		/*
		 * Para hacer una venta
		 * 1. Se verifica si es eguro alquilar, es decir si el producto 
		 * está disponible
		 * si es seguro: 
		 * 	2. se calcula el precio del alquiler
		 * 	3. se alquila el producto cambiando el estado a alquilado 
		 * y calculando la fecha de devolución
		 * 	6. Se retorna un mensaje con el total a pagar y confirmando el alquiler
		 * si no: 
		 *  - Se muestra un mensaje reportando el error.
		 */
		String msg="";
		double totalPrice=0;
		if(p.isSafeRent()){

			totalPrice=p.getRentPrice(days);
			p.rentProduct(days);
			msg+="The product has been rented at: " + totalPrice + "\n";
		}
		else{
			msg+="The product cannot be rented\n";
		}

		return msg;
	}
	

	/**
	 * 
	 * @return el nombre de la tienda
	 */
	public String getName() {
		return name;
	}
}
