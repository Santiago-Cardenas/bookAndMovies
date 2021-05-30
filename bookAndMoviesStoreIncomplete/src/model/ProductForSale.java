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
    public double getSalePrice (int units) {
        double d=units;

        return d;
    }

    @Override
    public boolean isSafeSale (int units){

        boolean x=true;
        return x;
    }

    @Override
    public double applyExtraDiscount(double subtotal, double percenteageDiscount){
        double extra=0;
        return extra;
    }

    @Override
    public double calculateTax (double totalPrice, double percenteage) {
        double c=0;
        return c;
    }
}
