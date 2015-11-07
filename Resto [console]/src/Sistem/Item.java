package Sistem;

public class Item {

	private String itemID, nama, tipe, harga, ket, diskon;

	public Item() {

	}

	public Item(String itemID, String tipe, String nama, String harga, String ket, String diskon) {
		this.itemID = itemID;
		this.tipe = tipe;
		this.nama = nama;
		this.harga = harga;
		this.ket = ket;
		this.diskon = diskon;
	}

	public String getNama() {
		return nama;
	}

	public void setName(String nama) {
		this.nama = nama;
	}

	public String getTipe() {
		return tipe;
	}

	public void setTipe(String tipe) {
		this.tipe = tipe;
	}

	public String getHarga() {
		return harga;
	}

	public void setHarga(String harga) {
		this.harga = harga;
	}

	public String getKet() {
		return ket;
	}

	public void setKet(String ket) {
		this.ket = ket;
	}

	public String getItemID() {
		return itemID;
	}

	public void setItemID(String itemID) {
		this.itemID = itemID;
	}

	public String getDiskon() {
		return diskon;
	}

	public void setDiskon(String diskon) {
		this.diskon = diskon;
	}

}
