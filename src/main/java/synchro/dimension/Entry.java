package synchro.dimension;

import synchro.dimension.source.DataSource;
/**
 * 入力ファイルのエントリクラス。
 * @author inagakikenichi
 */
public interface Entry {
	
	/**
	 * 
	 */
	public static enum Type {
		WAV(".wav"), MP3(".mp3");
		
		private String extension;
		
		Type(String extension) {
			this.extension = extension;
		}
	};

	/**
	 * 入力ファイルのデータソースを返す。
	 * @return 
	 */
	abstract DataSource getSource();

	/**
	 * 入力ファイルのパスを返す。
	 * @return 
	 */
	abstract String getPath();

	/**
	 * 入力ファイルのデータ形式を返す。
	 * @return 
	 */
	abstract Entry.Type getType();

}
