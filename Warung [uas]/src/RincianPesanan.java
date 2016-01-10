
public class RincianPesanan {
	private int ID_item;
	private String namaItem;
	private double harga;
	private byte jumlah;
	private double totalHarga;

	/**
	 * Constructor for objects of class OrderDetail
	 */
	public RincianPesanan(MenuItem menuItem_baru, byte jumlah_baru) {
		this.ID_item = menuItem_baru.getID();
		this.namaItem = menuItem_baru.getNama();
		this.harga = menuItem_baru.getHarga();
		this.jumlah = jumlah_baru;
		this.totalHarga = this.harga * this.jumlah;
	}

	/**************************************************
	 * Getter
	 *************************************************/
	public int getID_item() {
		return this.ID_item;
	}

	public String getNama_item() {
		return this.namaItem;
	}

	public double getHarga() {
		return this.harga;
	}

	public byte getJumlah() {
		return this.jumlah;
	}

	public double getTotal_harga() {
		return this.totalHarga;
	}

	public void tambahJumlah(byte tambah) {
		jumlah += tambah;
		totalHarga = harga * jumlah;
	}

}
