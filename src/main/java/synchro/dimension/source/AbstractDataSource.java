package synchro.dimension.source;


import synchro.dimension.Entry;
/**
 * データソースクラスのアダプタクラス（みたいな扱いだと思う）
 * @author inagakikenichi
 */
abstract class AbstractDataSource implements DataSource {

	/**
	 * データ形式
	 */
	private Entry.Type type;
	/**
	 * ファイルパス
	 */
	private String path;

	/**
	 * コンストラクタ。
	 * @param type データ形式
	 * @param path ファイルパス
	 */
	public AbstractDataSource(Entry.Type type, String path) {
		this.type = type;
		this.path = path;
	}

	@Override
	public Entry.Type getType() {
		return type;
	}

	@Override
	public String getPath() {
		return path;
	}
}
