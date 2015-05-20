package synchro.dimension;

/**
 *
 * @author inagakikenichi
 */
public class ComplexNumber {
	private double real;
	private double imaginary;
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	public void addReal(double real) {
		this.real += real;
	}

	public void addImaginary(double imaginary) {
		this.imaginary += imaginary;
	}

	public void setReal(double real) {
		this.real = real;
	}

	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

	public double getReal() {
		return real;
	}
	
	public double getImaginary() {
		return imaginary;
	}

	public double getABS() {
		return Math.sqrt(real*real + imaginary*imaginary);
	}

	@Override
	public String toString() {
		return real + " + " + imaginary + "i";
	}
}
