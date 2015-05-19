package synchro.dimension.source;

import synchro.dimension.Entry;
/**
 * 
 * @author inagakikenichi
 */
public interface DataSource {

	/**
	 * 
	 */
	public static enum Type {
		WAV(".wav"), MP3(".mp3");
		private String extension;
		
		Type(String extension) {
			this.extension = extension;
		}
		
		String getExtension() {
			return extension;
		}
	}

	/**
	 * データソースのデータ形式を返す。
	 * @return ソースのデータ形式
	 */
	abstract DataSource.Type getType();

	/**
	 * ファイルパスを返す。
	 * @return ファイルパス
	 */
	abstract String getPath();

	/**
	 * データソースのエントリを返す。
	 * @param <T> エントリの種類（多分）
	 * @return ソースのエントリ
	 */
	abstract <T extends Entry> T getEntry();

	/**
	 * データ列を返す。
	 * @param <T> データ列の構造（多分リストに成ると思う）
	 * @return データ列
	 */
	abstract <T> T getData();
}
