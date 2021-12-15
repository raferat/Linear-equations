public class InverseMatrixMethod implements ResultCalculation  
{

	@Override
	public double[] calculation(Equation e)
	{
		Matice a = e.getKoef().inverse();
		
		a.print();
		
		double [][] b = new double [e.getRightSide().length][1];
		for ( int i = 0 ; i < e.getRightSide().length ; i ++ )
			b[i][0] = e.getRightSide()[i];
		
		new Matice(b).print();
		Matice result = a.multiply(new Matice(b));
		
		
		double [] resultArr = new double [result.width];
		for ( int i = 0 ; i < result.width ; i ++ )
		{
			resultArr [i] = result.get(i, 0); 
		}
		
		return resultArr;
	}

}
