package synchro.dimension.source;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.BitSet;
import java.util.logging.Logger;
import java.util.logging.Level;
import javax.sound.sampled.AudioInputStream;

import synchro.dimension.Entry;
import synchro.dimension.AudioStreamEntry;

/**
 * mp3形式の音声ファイルのデータソースクラス。
 * @author inagakikenichi
 */
public class MP3FileDataSource extends AbstractDataSource {
	
	private AudioStreamEntry entry;
	/**
	 * フレームヘッダのバイト列</br>
	 * 4バイト（32ビット）
	 */
	private byte[] frameHeader;
	/**
	 * 音声データのバイト列
	 */
	private byte[] soundData;
	/**
	 * ID3タグのバイト列</br>
	 * 128バイト
	 */
	private byte[] id3Tag;

	public MP3FileDataSource(String mp3File, Entry entry) {
		super(Entry.Type.MP3, mp3File);
		this.entry = (AudioStreamEntry)entry;
		try {
//			this.stream = AudioSystem.getAudioInputStream(new File(mp3File));
			readSoundFile(new File(mp3File));
		} catch(IOException e) {
			e.printStackTrace();
			Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, e, null);
		}
	}

	/**
	 * mp3ファイルを読み込む。
	 * @param soundFile mp3ファイル
	 * @throws FileNotFoundException
	 * @throws IOException 
	 */
	private void readSoundFile(File soundFile) throws FileNotFoundException, IOException {
		if(!soundFile.exists()) {
			throw new FileNotFoundException();
		}
		byte[] mp3Data = Files.readAllBytes(soundFile.toPath());
		// 先頭4バイトがフレームヘッダ
		this.frameHeader = Arrays.copyOfRange(mp3Data, 0, 4);
		// 末尾128バイトがID3タグ
		this.id3Tag = Arrays.copyOfRange(mp3Data, mp3Data.length - 128, mp3Data.length);
		// それ以外はデータ領域
		this.soundData = Arrays.copyOfRange(mp3Data, 4, mp3Data.length - 128);
	}

	@Override
	public AudioStreamEntry getEntry() {
		return entry;
	}

	@Override
	public byte[] getData() {
		return soundData;
	}
}
