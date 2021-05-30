package model;
import java.time.LocalDate;
public class ProductForRent extends Product implements Rentable {

    private LocalDate devolutionDate;
    private State state;

	public ProductForRent(String code, String name, double price, ProductType type) {
		super(code,name,1,price,type);
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
		boolean rent=false;

		return rent;
	}

    public double getRentPrice(int amountDays){
    	double days=amountDays;
        return days;
    }

    @Override
    public void rentProduct (int amountDays){
    	int days=amountDays;

    }

    public void setState(State state){
        this.state=state;
    }


}
