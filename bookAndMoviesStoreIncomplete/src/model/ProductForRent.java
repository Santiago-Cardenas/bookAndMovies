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
                "Product Price: " + getPrice() + "\n";

        switch (getType()){

            case BOOK:
            msg+= "\n" + "Product Type: Book";
            break; 
            case MAGAZINE:
            msg+= "\n" + "Product Type: Magazine";
            break; 
            case DVD_MOVIE:
            msg+= "\n" + "Product Type: DVD Movie";
            break; 
            case DOWNLOAD_MOVIE:
            msg+= "\n" + "Product Type: Downloadable Movie";
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
    	double price=amountDays * getPrice();
        return price;
    }

    @Override
    public void rentProduct (int amountDays){
    	state=State.RENTED;
        devolutionDate=devolutionDate.plusDays(amountDays);
    }

}
