package fos;

// Box Type based on the card grade, the colours and the reinforcements
public abstract class BoxType {
    
    protected int[] cardGrade;
    protected int colours = 0;
    protected boolean reinforcedBottom = false;
    protected boolean reinforcedCorners = false;    
    
    public BoxType()
    {
    }
    
    public BoxType(int[] cardGrade)
    {
        this.cardGrade = cardGrade;
    }
    
    public BoxType(int[] cardGrade, int colours)
    {
        this.cardGrade = cardGrade;
        this.colours = colours;
    }
       
   
    public BoxType(int[] cardGrade, int colours, boolean reinforcedBottom, boolean reinforcedCorners) {
        this.cardGrade = cardGrade;
        this.colours = colours;
        this.reinforcedBottom = reinforcedBottom;
        this.reinforcedCorners = reinforcedCorners;
    }
    
    
    // accessor for gard grade
    protected int[] getCardGrade() {
        return cardGrade;
    }
    
    // accessor for colour
    protected int getColour() {
        return colours;
    }

    // accessor for reinforced bottom
    protected boolean getReinforcedBottom() {
        return reinforcedBottom;
    }
    
    // accessor for reinforced cornres
    protected boolean getReinforcedCorners() {
        return reinforcedCorners;
    }
    
    // setter for the card grade to the user input
    protected void setCardGrade(int newCardGrade) {
        cardGrade = new int[] {newCardGrade};

    }
    
    // validate if the selected card grade exists
    protected boolean containsGrade(int userValue)
    {
        for(int i=0; i<cardGrade.length; i++)
        {
            if(cardGrade[i] == userValue)
            {
            return true;
            }
        }
    return false;
    }
    
    // define abstract method calculation the additional box enhancements cost
    protected abstract double additionalCost();

}
