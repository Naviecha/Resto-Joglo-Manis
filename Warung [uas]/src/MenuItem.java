
public class MenuItem {
	// definition of menu item type
	public final static int Makanan = 1;
	public final static int Minuman = 2;
	public final static int Penyegar = 3;
	public final static int Cuci_Mulut = 4;

	private int ID;
	private String nama;
	private byte tipe;
	private double harga;

	private byte status;
	private double harga_promo;
	public final static byte item_promosi = 1;
	public final static byte item_khusus = 2;

	public MenuItem(int ID_baru, String nama_baru, double harga_baru, byte tipe_baru) {
		this.ID = ID_baru;
		this.nama = nama_baru;
		this.harga = harga_baru;
		this.tipe = tipe_baru;
		this.status = 0;
		this.harga_promo = 0;
	}

	// setter
	public void setNama(String nama_baru) {
		this.nama = nama_baru;
	}

	public void setHarga(double harga_baru) {
		this.harga = harga_baru;
	}

	public void setTipe(byte tipe_baru) {
		this.tipe = tipe_baru;
	}

	public void setStatus(byte status_baru, double tempHarga) {
		this.status = status_baru;
		this.harga_promo = tempHarga;
	}

	public void resetStatus() {
		this.status = 0;
		this.harga_promo = 0;
	}

	// getter
	int getID() {
		return this.ID;
	}

	String getNama() {
		return this.nama;
	}

	double getHarga() {
		if (this.status != 0 && this.harga_promo != 0) {
			return this.harga_promo;
		} else
			return this.harga;
	}

	double getHargaBiasa() {
		return this.harga;
	}

	byte getTipe() {
		return this.tipe;
	}

	byte getStatus() {
		return this.status;
	}
}
