package fos;

public class ReinforcedBox extends BoxType {
	
	protected final static double[] colourCost = {12,15}; // colours prices
	protected final static int reinforcedBottomCost = 10; // additional cost for reinforced bottom
	protected final static int reinforcedCornersCost = 7; // additional cost for reinforced cornres
    
    public ReinforcedBox()
    {
        super();
    }
    
    public ReinforcedBox(int[] cardGrade, int colourPrint, boolean reinforcedBottom, boolean reinforcedCorners) { // get the user input
        super(cardGrade, colourPrint, reinforcedBottom, reinforcedCorners);
        
    }
    
    @Override
    protected double additionalCost() { // calculate the additional cost depending on the selected colours and reinforcements
        
        double cost = colourCost[1]; // reinforced type of box must have 2 colours selected
                
        if(reinforcedBottom)
        {
            cost = cost + reinforcedBottomCost;
        }
        if(reinforcedCorners)
        {
            cost = cost + reinforcedCornersCost;
        }
        
        return cost;
    }
        
}