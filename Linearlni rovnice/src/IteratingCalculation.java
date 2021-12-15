
public abstract class IteratingCalculation implements ResultCalculation
{
	public static final double defaultExactness = 0.0001d;
	public static final int defaultIterations = 1000;
	
	protected double exactness;
	protected int iterations;
	
	public IteratingCalculation ( double exactness , int iterations )
	{
		this . exactness = exactness;
		this . iterations = iterations;
	}
	
	public IteratingCalculation ( double exactness )
	{
		this ( exactness , defaultIterations );
	}
	
	public IteratingCalculation ( int iterations )
	{
		this ( defaultExactness , iterations );
	}
	
	public IteratingCalculation ()
	{
		this ( defaultExactness , defaultIterations );
	}

}
