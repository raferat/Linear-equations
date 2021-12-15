public class Equation
{
    private Matice koef;
	private double[] rightSide;

	public Matice getKoef() {
		return koef;
	}

	public double[] getRightSide() {
		return rightSide;
	}

	public Equation(Matice koef, Matice rightSide) {
		this.koef = koef;
		this.rightSide = rightSide.array()[0].clone();
	}

	public Equation(double[][] koef, double[] rightSide) {
		this.koef = new Matice(koef);
		this.rightSide = rightSide.clone();
	}

	public Equation(Matice koef, double[] rightSide) {
		this.koef = koef;
		this.rightSide = rightSide.clone();
	}

	public double[] findSolution(ResultCalculation c) {
		return c.calculation(this);
	}
}
