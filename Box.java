package fos;

public class Box {
    protected BoxType boxType;
    protected double boxWidth;
    protected double boxHeight;
    protected double boxLength;
    protected boolean sealableTops;
    protected final static double[] cardGradePrice = {0.45,0.59,0.68,0.92,1.30}; // card grade prices for 1 sq.m.
    protected final static int sealableTopsCost = 5; // price for sealable tops
    
    public Box() {    
    }
    
    public Box(BoxType boxType, double boxWidth, double boxHeight, double boxLength, boolean sealableTops) {
        
       this.boxType = boxType;
       this.boxWidth = boxWidth;
       this.boxHeight = boxHeight;
       this.boxLength = boxLength;
       this.sealableTops = sealableTops;
       
    }
    

    protected double basicPrice() {
    
        double boxArea;
        double price;
        double cost;
        int cardGrade;
        
        boxArea = 2*(boxWidth*boxHeight) + 2*(boxLength*boxHeight) + (boxWidth*boxLength);
        cardGrade = boxType.getCardGrade()[0];
        cost = cardGradePrice[cardGrade-1];
        price = boxArea * (cost);
        return price;
        
    }
    
    protected double cost() { // calculate the basic price + the additional cost for sealable tops 
        double finalPrice;
        double additionalPrice;
        
        finalPrice = basicPrice();
  
        additionalPrice = boxType.additionalCost();
        if(sealableTops) {
            additionalPrice += sealableTopsCost;
        }

        finalPrice += additionalPrice * finalPrice / 100;
        return finalPrice;
    }
    
}