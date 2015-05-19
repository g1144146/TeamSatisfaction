package synchro.dimension.source;

/**
 *
 * @author inagakikenichi
 */
abstract class AbstractDataSource implements DataSource {
	
	/**
	 * データ形式
	 */
	private DataSource.Type type;
	/**
	 * ファイルパス
	 */
	private String path;

	/**
	 * コンストラクタ。
	 * @param type データ形式
	 * @param path ファイルパス
	 */
	public AbstractDataSource(DataSource.Type type, String path) {
		this.type = type;
		this.path = path;
	}

	@Override
	public DataSource.Type getType() {
		return type;
	}

	@Override
	public String getPath() {
		return path;
	}
}
