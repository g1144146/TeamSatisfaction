package synchro.dimension;

import java.io.InputStream;

import synchro.dimension.source.DataSource;
/**
 *
 * @author inagakikenichi
 */
public interface Entry {
	public static enum Type {
		WAV(".wav"), MP3(".mp3");
		
		private String extension;
		
		Type(String extension) {
			this.extension = extension;
		}
	};

	/**
	 * 
	 * @return 
	 */
	abstract DataSource getSource();

	/**
	 * 
	 * @return 
	 */
	abstract String getPath();

	/**
	 * 
	 * @return 
	 */
	abstract Entry.Type getType();

}
