package fos;

public class ColouredBox extends BoxType {
	
	protected final static double[] colourCost = {12,15}; // colours prices
    
    public ColouredBox()
    {
        super();
    }
       
    public ColouredBox(int[] cardGrade, int colours) {
        super(cardGrade, colours);
    }
    
    @Override
    protected double additionalCost() { // calculate the additional cost depending on the selected colours
        return colourCost[colours-1];
    }
    
}