package synchro.dimension.source;

import synchro.dimension.Entry;

/**
 *
 * @author inagakikenichi
 */
public class MP3FileDataSource extends AbstractDataSource {

	public MP3FileDataSource(String mp3File) {
		super(DataSource.Type.MP3, mp3File);
	}

	public void readSoundFile(String path) {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public <T extends Entry> T getEntry() {
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override
	public <T> T getData() {
		throw new UnsupportedOperationException("Not supported yet.");
	}
}
