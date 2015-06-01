package synchro.dimension.source;

import synchro.dimension.Entry;
/**
 * データソースのクラス。
 * データ形式・データ列などのデータソースを構成する要素を持つ（予定）。
 * @author inagakikenichi
 */
public interface DataSource {

	/**
	 * データソースのデータ形式を返す。
	 * @return ソースのデータ形式
	 */
	abstract Entry.Type getType();

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
