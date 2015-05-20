package synchro.dimension;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import synchro.dimension.source.WavFileDataSource;

import com.gs.collections.impl.list.mutable.primitive.IntArrayList;
import com.gs.collections.impl.list.mutable.FastList;

public class Main {
	public Main(String[] args) throws Exception {
		for(String file : args) {
			Entry entry = new AudioStreamEntry(Entry.Type.WAV, file);
			WavFileDataSource wav = (WavFileDataSource)entry.getSource();
			System.out.println(wav.getData().size());
			String home = System.getProperty("user.home") + "/Desktop/output.csv";
			File f = new File(home);
			if(f.exists()) {
				f.delete();
			}
			f.createNewFile();
			IntArrayList list = wav.getData();
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
				for(int i = 0; i < list.size(); i++) {
//					System.out.println(list.get(i));
					bw.write(String.valueOf(list.get(i)) + ",");
				}
			}
			System.out.println(wav.toString());
			System.out.println(home);
			
			FastFourierTransformer fft = new FastFourierTransformer(wav.getStream());
			fft.executeFFT();
			FastList<ComplexNumber> data = fft.getTransfomedData();
			System.out.println(data.size());
			home = System.getProperty("user.home") + "/Desktop/output2.csv";
			f = new File(home);
			if(f.exists()) {
				f.delete();
			}
			f.createNewFile();
			try(BufferedWriter bw = new BufferedWriter(new FileWriter(f))) {
				for(int i = 0; i < data.size(); i++) {
//					System.out.println(data.get(i));
					bw.write(String.valueOf(data.get(i).getABS()) + ",");
				}
			}
		}
	}
	
	public static void main(String[] args) throws Exception {
		if(args.length < 1) {
			System.out.println("Please input your files.");
			return;
		}
		new Main(args);
	}
}