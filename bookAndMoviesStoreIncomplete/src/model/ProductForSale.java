package model;

public class ProductForSale extends Product implements Saleable {


    private double discount;
    private State state;

    public ProductForSale (String code, String name,int units,double price, ProductType type) {
        super(code,name,units,price,type);
    }

    public void setDiscount (double discount){
        this.discount=discount;
    }

    @Override
    public String getInformation (){

        String msg= "               Product Information\n"+
                    "Code: " + getCode() + "\n"+
                    "Product Name:" + getName()+ "\n"+
                    "Units Available: "+ getUnits()+ "\n"+
                    "Product Price: " + price + "\n";

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
    public double getSalePrice (int units) {

        double totalPrice=( ( units* price) - discount);

        return totalPrice;
    }

    @Override
    public boolean isSafeSale (int units){

        boolean forSale=false;

        if (units>0 && units<=getUnits()){
            forSale=true;
            setUnits(getUnits()-units);
        }
        return forSale;
    }

   @Override
    public double applyExtraDiscount(double subtotal, double percenteageDiscount){
        double price= ( subtotal - (subtotal*percenteageDiscount) );
        return price;
    }


    @Override
    public double calculateTax (double totalPrice, double percenteage) {
        double price= ( totalPrice + (totalPrice*percenteage) );
        return price;
    }
}
