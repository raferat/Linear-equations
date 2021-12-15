public class Gauss implements ResultCalculation 
{
	@Override
	public double[] calculation(Equation e) 
	{
		double[][] g = new double[e.getKoef().width][e.getKoef().height+1];
		
		for ( int i = 0 ; i < g.length ; i ++ )
		{
			for ( int j = 0 ; j < g[i].length -1; j ++ )
			  g[i][j] = e.getKoef().array()[i][j];
		}
		for ( int j = 0 ; j < e.getRightSide().length; j ++ )
		{
		  g[j][g[j].length -1] = e.getRightSide()[j];
		}
			
		
		
		for ( int i = 0 ; i < g.length ; i ++ )
		{
			for ( int j = i + 1 ; j < g.length ; j ++ )
			{
				double multiplier = -(g[j][i]/g[i][i]);
				g[j] = addRow ( g[j] , multiplyRow(multiplier, g[i]) );
			}
		}
		
		Matice gauss = new Matice(g);
		double[] result = new double [gauss.width];
		for ( int i = gauss.width -1 ; i >= 0 ; i -- )
		{
			double previous = 0;
			for ( int j = i + 1 ; j < gauss.width ; j ++)
			    previous += gauss.get(i, j) * result[j];
			
			result [i] = ( gauss.get(i, gauss.height-1) - previous)/gauss.get(i, i); 
		}
		
		return result;
	}
	
	double[] addRow ( double [] row1 , double [] row2 )
	{
		if ( row1 . length != row2.length )
			throw new RuntimeException("row1 . length != row2 . length");
		
		double [] result = row1.clone();
		
		for ( int i = 0 ; i < result . length ; i ++ )
		{
			result[i] += row2[i]; 
		}
		
		return result;
	}

	double[] multiplyRow ( double multiplier , double [] row )
	{
		double [] result = row.clone();
		
		for ( int i = 0 ; i < result . length ; i ++ )
		{
			result[i] *= multiplier; 
		}
		
		return result;
	}

}
