import java.util.Arrays;

public class Jacobnic extends IteratingCalculation 
{
	double [] prediction;
	double [] lastPrediction;
	
	public Jacobnic( double [] prediction) 
	{
		super ();
		this . prediction = prediction;
	}
	
	public Jacobnic( double [] prediction , int iterations) 
	{
		super (iterations);
		this . prediction = prediction;
	}

	public Jacobnic( double [] prediction , double exactness) 
	{
		super (exactness);
		this . prediction = prediction;
	}

	public Jacobnic( double [] prediction , double exactness , int iterations) 
	{
		super (exactness , iterations);
		this . prediction = prediction;
	}

	
	@Override
	public double[] calculation(Equation e) 
	{
		lastPrediction = new double[prediction.length];
		int i = 0;
		do
		{
		  lastPrediction = prediction.clone();
		  for (int y = 0 ; y < e.getKoef().width ; y ++ )
		  {
			  double citatel = e.getRightSide()[y];
			  for ( int x = 0 ; x < e.getKoef().height ; x ++ )
			  {
				  if ( x != y )
				  {
				     citatel -= e.getKoef().get(y, x) * lastPrediction[x];
				  }
			  }
			  prediction [y] = ( 1 / e.getKoef().get(y, y) ) * citatel;
		  }
		  i ++;
		} while ( !arraysMatch() && i < iterations ); 
			
		
		return prediction;
	}
	
	private boolean arraysMatch ( )
	{
		for ( int i = 0 ; i < prediction . length ; i ++)
		{
			if ( Math.abs( prediction [i] - lastPrediction [i] ) >= exactness )
				return false;
		}
		
		return true;
	}

}
