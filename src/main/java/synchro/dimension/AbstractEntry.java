package synchro.dimension;

import synchro.dimension.source.DataSource;

/**
 *
 * @author inagakikenichi
 */
abstract class AbstractEntry implements Entry {

	/**
	 * データ形式
	 */
	private Entry.Type type;
	/**
	 * データソース
	 */
	private DataSource source;
	/**
	 * ファイルパス
	 */
	private String path;

	/**
	 * コンストラクタ。
	 * @param type データ形式
	 * @param path ファイルパス
	 */
	public AbstractEntry(Entry.Type type, String path) {
		this.type = type;
		this.path = path;
	}

	/**
	 * データソースをセットする。
	 * @param source 
	 */
	protected void setSource(DataSource source) {
		this.source = source;
	}

	@Override
	public DataSource getSource() {
		return source;
	}

	@Override
	public String getPath() {
		return path;
	}

	@Override
	public Entry.Type getType() {
		return type;
	}
	
}
