package model;
import java.time.LocalDate;
public class ProductForRent extends Product implements Rentable {

    private LocalDate devolutionDate;
    private State state;

	public ProductForRent(String code, String name, double price, ProductType type) {
		super(code,name,1,price,type);
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

	public boolean isSafeRent (){
		boolean forRent=false;

        if (getState().equals(State.AVAILABLE)){
            forRent=true;
        }

		return forRent;
	}

    public double getRentPrice(int amountDays){
    	double price=amountDays * getPrice();
        return price;
    }

    @Override
    public void rentProduct (int amountDays){
    	setState(State.RENTED);
        int i=amountDays;
        long days=i;
        devolutionDate.plusDays(days);

    }

    public State getState(){
        return state;
    }
    public void setState(State state){
        this.state=state;
    }

}
