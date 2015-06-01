package synchro.dimension.source;

import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import synchro.dimension.Entry;
/**
 * DataSource用のビルダークラス。
 * シングルトン。
 * @author inagakikenichi
 */
public class DataSourceBuilder {

	/**
	 * ビルダー（自インスタンス）
	 */
	private static final DataSourceBuilder builder = new DataSourceBuilder();
	
	private DataSourceBuilder() {}

	/**
	 * ビルダーを返す。
	 * @return ビルダー
	 */
	public static DataSourceBuilder getBuilder() {
		return builder;
	}

	/**
	 * 音声ファイルのデータソースをビルドする。
	 * @param fileName ファイル名
	 * @param entry データソースのエントリ
	 * @return ファイルのデータソース
	 * @throws UnknownDataSourceException サポートしてない種類のファイルが入力された場合
	 * @throws FileNotFoundException ファイルが見つからない場合
	 */
	public DataSource build(String fileName, Entry entry)
			throws UnknownDataSourceException, FileNotFoundException {
		Path path = FileSystems.getDefault().getPath(fileName);
		if(Files.isRegularFile(path) && Files.exists(path)) {
			if(fileName.endsWith(".wav")) {
				return new WavFileDataSource(path.toString(), entry);
			} else if(fileName.endsWith(".mp3")) {
				return new MP3FileDataSource(path.toString(), entry);
			}
			throw new UnknownDataSourceException();
		} else {
			throw new FileNotFoundException();
		}
	}
}
