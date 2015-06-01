package synchro.dimension;

import java.io.FileNotFoundException;

import synchro.dimension.source.DataSourceBuilder;
import synchro.dimension.source.UnknownDataSourceException;
/**
 * 音声ファイル用のエントリクラス。
 * @author inagakikenichi
 */
public class AudioStreamEntry extends AbstractEntry {

	/**
	 * コンストラクタ。
	 * @param type データ形式
	 * @param path ファイルパス
	 * @throws UnknownDataSourceException
	 * @throws FileNotFoundException 
	 */
	public AudioStreamEntry(Entry.Type type, String path)
			throws UnknownDataSourceException, FileNotFoundException {
		super(type, path);
		setSource(DataSourceBuilder.getBuilder().build(path, this));
	}
}
