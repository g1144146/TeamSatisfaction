package synchro.dimension;

/**
 * 複素数を持つクラス。
 * @author inagakikenichi
 */
public class ComplexNumber {

	/**
	 * 実部
	 */
	private double real;
	/**
	 * 虚部
	 */
	private double imaginary;

	/**
	 * コンストラクタ。
	 * @param real 実部
	 * @param imaginary 虚部 
	 */
	public ComplexNumber(double real, double imaginary) {
		this.real = real;
		this.imaginary = imaginary;
	}

	/**
	 * 実部に加算する。
	 * @param real 実数値
	 */
	public void addReal(double real) {
		real += real;
	}

	/**
	 * 虚部に加算する。
	 * @param imaginary 虚数値
	 */
	public void addImaginary(double imaginary) {
		imaginary += imaginary;
	}

	/**
	 * 実部をセットする。
	 * @param real 実数値
	 */
	public void setReal(double real) {
		this.real = real;
	}

	/**
	 * 虚部をセットする。
	 * @param imaginary 虚数値
	 */
	public void setImaginary(double imaginary) {
		this.imaginary = imaginary;
	}

	/**
	 * 実部を返す。
	 * @return 実部
	 */
	public double getReal() {
		return real;
	}

	/**
	 * 虚部を返す。
	 * @return 虚部
	 */
	public double getImaginary() {
		return imaginary;
	}

	/**
	 * 複素数の絶対値を返す。
	 * @return 複素数の絶対値
	 */
	public double getABS() {
		return Math.sqrt(real*real + imaginary*imaginary);
	}

	@Override
	public String toString() {
		if(real == 0 && imaginary == 0) {
			return "0.0";
		} else if(real == 0) {
			return imaginary + "i";
		} else if(imaginary == 0) {
			return String.valueOf(real);
		} else {
			if(imaginary < 0) {
				return real + " " + imaginary + "i";
			} else {
				return real + " + " + imaginary + "i";
			}
		}
	}
}
