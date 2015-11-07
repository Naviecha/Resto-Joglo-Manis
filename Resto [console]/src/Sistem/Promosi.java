package Sistem;

import java.util.ArrayList;
import java.util.Iterator;

public class Promosi {

	private String nama, promoID;
	private double totalHarga;
	private ArrayList<Item> daftarItem;

	public Promosi() {

	}

	public Promosi(String promoID, String nama, double totalHarga, ArrayList<Item> daftarItem) {
		this.promoID = promoID;
		this.nama = nama;
		this.totalHarga = totalHarga;
		this.daftarItem = daftarItem;
	}

	public String getPromoID() {
		return promoID;
	}

	public void setPromoID(String promoID) {
		this.promoID = promoID;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public double getHarga() {
		return totalHarga;
	}

	public void setHarga(double totalHarga) {
		this.totalHarga = totalHarga;
	}

	public ArrayList<Item> getDaftarItem() {
		return daftarItem;
	}

	public void setDaftarItem(ArrayList<Item> daftarItem) {
		this.daftarItem = daftarItem;
	}

	public double hargaPromo() {
		double tempHarga = 0;
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			tempHarga += Double.parseDouble(item.getHarga());

		}
		return tempHarga;
	}

}
