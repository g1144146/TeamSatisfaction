package synchro.dimension.source;

/**
 * 対応していないデータソースに対する例外クラス。
 * @author inagakikenichi
 */
public class UnknownDataSourceException extends Exception {

	public UnknownDataSourceException() {
		super();
	}

	public UnknownDataSourceException(String message) {
		super(message);
	}

	public UnknownDataSourceException(Throwable cause) {
		super(cause);
	}

	public UnknownDataSourceException(String message, Throwable cause) {
		super(message, cause);
	}
}
