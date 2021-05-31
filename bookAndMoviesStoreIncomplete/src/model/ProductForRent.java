package model;
import java.time.LocalDate;
public class ProductForRent extends Product implements Rentable {

    private LocalDate devolutionDate;
    private State state;

	public ProductForRent(String code, String name, double price, ProductType type) {
		super(code,name,1,price,type);
        state=State.AVAILABLE;
        devolutionDate=LocalDate.of(2021, 05, 28);
	}

	@Override
	public String getInformation (){

	 String msg= "               Product Information\n"+
                "Code: " + getCode() + "\n"+
                "Product Name:" + getName()+ "\n"+
                "Product Price: " + price + "\n" +
                "Product State: " + state + "\n";

        switch (getType()){

            case BOOK:
            msg+= "Product Type: Book\n";
            break; 
            case MAGAZINE:
            msg+= "Product Type: Magazine\n";
            break; 
            case DVD_MOVIE:
            msg+= "Product Type: DVD Movie\n";
            break; 
            case DOWNLOAD_MOVIE:
            msg+= "Product Type: Downloadable Movie\n";
            break; 

        }
        return msg;
	}

    @Override
	public boolean isSafeRent (){
		boolean forRent=false;

        if (state.equals(State.AVAILABLE)){
            forRent=true;
        }

		return forRent;
	}

    @Override
    public double getRentPrice(int amountDays){
    	double totalPrice=amountDays * price;
        return totalPrice;
    }

    @Override
    public void rentProduct (int amountDays){
    	state=State.RENTED;
        devolutionDate=devolutionDate.plusDays(amountDays);
    }

}
