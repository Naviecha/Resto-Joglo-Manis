
public class DB_Exception extends Exception {
	public String pesan_error;

	public DB_Exception(String pesan) {
		pesan_error = pesan;
	}

	public String getPesanError() {
		return pesan_error;
	}
}
