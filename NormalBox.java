package fos;

public class NormalBox extends BoxType {
    
    public NormalBox()
    {
        super();
    }
       
    public NormalBox(int[] cardGrade) { // assign the box card grade
        super(cardGrade);
    }
    
    @Override
    protected double additionalCost() { // return 0 for additional cost as there are no box enhancements available
        return 0;
    }
    
}