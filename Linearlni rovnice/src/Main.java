import java.util.Arrays;

public class Main 
{
  public static void main ( String[] args )
  {
    Matice matice = new Matice(new double [][] 
    {
    	{11,4},
    	{6,6}
    }); 
    
    //matice.adjungovana().print();
    
    double [] rightSide = new double [] { 157,120};
    
    Equation e = new Equation(matice, rightSide);
    ResultCalculation r = new Gauss();
    ResultCalculation r1 = new CarnersRule();
    ResultCalculation r2 = new InverseMatrixMethod();
    ResultCalculation r3 = new Jacobnic(new double[] {0,0},10000);
    ResultCalculation r4 = new GaussSeidel(new double[] {0,0},10000);

    System.out.println( Arrays.toString(e.findSolution(r3)) );
    System.out.println( Arrays.toString(e.findSolution(r4)) );

    //System.out.println( Arrays.toString(e.findSolution(r2)) );

    //System.out.println (Arrays.toString(e.findSolution(r)));
    //System.out.println (Arrays.toString(e.findSolution(r1)));
    
  }
}
