package synchro.dimension;

import javax.sound.sampled.AudioInputStream;

import java.io.IOException;

import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gs.collections.impl.list.mutable.FastList;

/**
 * 高速フーリエ変換を行うクラス。
 * @author inagakikenichi
 */
public class FastFourierTransformer {
	/**
	 * 高速フーリエ変換適用後のデータ列
	 */
	private FastList<ComplexNumber> transformedData;
	/**
	 * 変換後のデータ数
	 */
	private int numOfData;

	/**
	 * コンストラクタ。
	 * @param stream 入力された音声ファイルのオーディオストリーム 
	 */
	public FastFourierTransformer(AudioInputStream stream) {
		System.out.println(">> "+stream.getFrameLength());
		try {
			double[] realNumbers = getData(stream);
			setNumOfData(realNumbers.length);
			setTransformedData(realNumbers);
		} catch(IOException ex) {
			Logger.getLogger(FastFourierTransformer.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	/**
	 * オーディオストリームから音声データを取得する。
	 * @param stream オーディストリーム
	 * @return 音声データ列
	 * @throws IOException 
	 */
	private double[] getData(AudioInputStream stream) throws IOException {
		byte[] byteData = new byte[stream.available()];
		stream.read(byteData);
		stream.close();
		double[] realNumbers = new double[byteData.length];
		for(int i = 0; i < byteData.length; i++) {
			realNumbers[i] = (byteData[i] & 0xFF) - 128.0;
		}
		return realNumbers;
	}

	/**
	 * 音声データ列を複素数化し、音声データ数以上かつ最小の2の累乗になるまでデータをパディングする。
	 * @param realNumbers 音声データ
	 */
	private void setTransformedData(double[] realNumbers) {
		this.transformedData = new FastList<>(numOfData);
		for(int i = 0; i < realNumbers.length; i++) {
			transformedData.add(new ComplexNumber(realNumbers[i], 0.0));
		}
		for(int i = realNumbers.length; i < numOfData; i++) {
			transformedData.add(new ComplexNumber(0.0, 0.0));
		}
	}

	/**
	 * 変換後のデータ数を計算する。
	 * @param dataSize 音声データのデータ数
	 */
	private void setNumOfData(int dataSize) {
		int count = 1;
		while(true) {
			System.out.println(count +", " +(count >= dataSize));
			if(count >= dataSize) {
				numOfData = count;
				return;
			}
			count <<= 1;
		}
	}

	/**
	 * 音声データに高速フーリエ変換を適用する。
	 */
	public void executeFFT() {
		double theta = -2 * Math.PI / numOfData;
		for(int i = numOfData, half; (half = i >> 1) >= 1; i = half) {
			for(int j = 0; j < half; j++) {
				double cos = Math.cos(j * theta);
				double sin = Math.sin(j * theta);
				for(int k = j; k < numOfData; k += i) {
					ComplexNumber comKHalf = transformedData.get(k + half);
					ComplexNumber comK = transformedData.get(k);
					double real = comKHalf.getReal()
						, imagin = comKHalf.getImaginary();
					double Rm = comK.getReal() - real
						, Im = comK.getImaginary() - imagin;
					transformedData.get(k).addReal(real);
					transformedData.get(k).addImaginary(imagin);
					transformedData.get(k + half).setReal(Rm*cos - Im*sin);
					transformedData.get(k + half).setImaginary(Rm*sin + Im*cos);
				}
			}
			theta *= 2;
		}
		swap();
	}

	/**
	 * 変換後のデータ列を並べ替える。
	 */
	private void swap() {
		for(int i = 0, j = 0, half = numOfData >> 1; j < half; j += 2) {
			int halfOne = half + 1;
			if(j < i) {
				Collections.swap(transformedData, i, j);
				Collections.swap(transformedData, i + halfOne, j + halfOne);
			}
			Collections.swap(transformedData, i + half, j + half);
		}
	}

	/**
	 * 変換後のデータ列を返す。
	 * @return 
	 */
	public FastList<ComplexNumber> getTransfomedData() {
		return transformedData;
	}
}
