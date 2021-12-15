
public class CarnersRule implements ResultCalculation 
{

	@Override
	public double[] calculation(Equation e) 
	{
		Matice leftSide = e.getKoef();
		System.out.println ( leftSide.determinant() );
		double [] result = new double [e.getRightSide().length];
		for ( int i = 0 ; i < e.getRightSide().length ; i ++ )
		  result [i] = columnInjection(leftSide, e.getRightSide(), i).determinant() / leftSide.determinant();
		
		return result;
	}
	
	public Matice columnInjection ( Matice dst , double[] src , int columnIndex )
	{
		Matice c = new Matice(dst);
		
		for ( int i = 0 ; i < src .length ; i ++ )
		{
			c . set ( i , columnIndex , src [i] );
		}
		
		return c;
	}
	
	

}
