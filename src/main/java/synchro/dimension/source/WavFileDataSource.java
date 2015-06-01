package synchro.dimension.source;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.BufferedInputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;

import com.gs.collections.impl.list.mutable.primitive.IntArrayList;

import synchro.dimension.Entry;
import synchro.dimension.AudioStreamEntry;
/**
 * wav形式の音声ファイルのデータソースクラス。
 * @author inagakikenichi
 */
public class WavFileDataSource extends AbstractDataSource {

	/**
	 * オーディオストリームのエントリ
	 */
	private AudioStreamEntry entry;
	/**
	 * フレームバッファ
	 */
	private byte[] frameBuffer;
	/**
	 * フレームサイズ
	 */
	private int frameSize;
	/**
	 * サンプリングレート
	 */
	private float samplingRate;
	/**
	 * チャンネル数（モノラル:1, ステレオ:2）
	 */
	private int channel;
	/**
	 * 量子化ビット数
	 */
	private int samplingBitRate;
	/**
	 * エンディアン
	 */
	private ByteOrder order;
	/**
	 * 音声データ列
	 */
	private IntArrayList data;
	/**
	 * オーディオストリーム
	 */
	private AudioInputStream stream;
	
	/**
	 * コンストラクタ。
	 * @param path ファイルパス
	 * @param entry データソースのエントリ
	 */
	public WavFileDataSource(String path, Entry entry) {
		super(Entry.Type.WAV, path);
		this.data = new IntArrayList();
		try {
			this.stream = AudioSystem.getAudioInputStream(new File(path));
			this.entry = (AudioStreamEntry)entry;
			readSoundFile(path);
		} catch(UnsupportedAudioFileException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * wavファイルを読み込む。
	 * @param path ファイルパス
	 * @throws UnsupportedAudioFileException
	 * @throws IOException 
	 */
	private void readSoundFile(String path) throws UnsupportedAudioFileException, IOException {
		File file = new File(path);
		if(!file.exists()) {
			throw new FileNotFoundException("the file ("+path+") is not found.");
		}
		System.out.println(file);
		
		try(AudioInputStream stream = AudioSystem.getAudioInputStream(file);
			BufferedInputStream input = new BufferedInputStream(stream)) {
			extractHeader(stream.getFormat());
			extractSoundData(input);
		}
	}

	/**
	 * 入力されたwavファイルからヘッダを取り出す。
	 * @param format サウンドデータのオーディオ形式オブジェクト
	 */
	private void extractHeader(AudioFormat format) {
		this.frameSize = format.getFrameSize();
		this.frameBuffer = new byte[frameSize];
		this.samplingRate = format.getSampleRate();
		this.channel = format.getChannels();
		this.samplingBitRate = format.getSampleSizeInBits() / 8;
		this.order = format.isBigEndian() ? ByteOrder.BIG_ENDIAN : ByteOrder.LITTLE_ENDIAN;
	}

	/**
	 * wavファイルの音声データを取り出す。
	 * @param input サウンドデータを持ったバッファストリーム
	 * @throws IOException 読み込み時にエラーが発生した場合
	 */
	private void extractSoundData(BufferedInputStream input) throws IOException {
		int offset = Integer.BYTES - samplingBitRate
				, bitShift = (Integer.BYTES - samplingBitRate) * 8;
		byte[] buffer = new byte[Integer.BYTES];
		while(input.read(frameBuffer) > 0) {
			for(int ch = 0; ch < channel; ch++) {
				for(int i = 0; i < samplingBitRate; i++) {
					buffer[offset + i] = frameBuffer[ch * samplingBitRate];
				}
				ByteBuffer bb = ByteBuffer.wrap(buffer).order(order);
				data.add(bb.getInt() >> bitShift);
			}
		}
	}

	/**
	 * オーディストリームを返す。
	 * @return オーディオストリーム
	 */
	public AudioInputStream getStream() {
		return stream;
	}

	@Override
	public AudioStreamEntry getEntry() {
		return entry;
	}

	@Override
	public IntArrayList getData() {
		return data;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Frame Size: ").append(frameSize).append(System.getProperty("line.separator"))
				.append("Sampling Rate: ").append(samplingRate).append(System.getProperty("line.separator"))
				.append("Channel: ").append(channel).append(System.getProperty("line.separator"))
				.append("Sampling Bit Rate").append(samplingBitRate);
		return builder.toString();
	}
}
