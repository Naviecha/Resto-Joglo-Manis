package Sistem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.Scanner;

public class Pesanan {

	Menu m;
	private ArrayList<Item> daftarItem = new ArrayList<Item>();
	private ArrayList<Promosi> daftarPromo = new ArrayList<Promosi>();
	private double notaPesanan = 0;
	private double notaTotal = 0;// dengan pajak

	public Pesanan() {

	}

	/**
	 * Fungsi untuk mengambil pesanan
	 */
	public void ambilPesanan() {
		m = new Menu();
		Object object = m.tampilMenu();
		if (object != null) {
			if (object instanceof Item)
				daftarItem.add((Item) object);
			if (object instanceof Promosi)
				daftarPromo.add((Promosi) object);
		}

	}

	public void ambilPesanan(Promosi p) {
		daftarPromo.add(p);
	}

	public void lihatPesanan() {
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			System.out.println("Item : " + item.getNama() + " " + "Rp. " + item.getHarga() + " ribu");
		}

		for (Iterator<Promosi> i = daftarPromo.iterator(); i.hasNext();) {
			Promosi promo = i.next();
			System.out.println("Promosi : " + promo.getNama() + " " + "Rp. " + promo.hargaPromo() + " ribu");
		}
	}

	public double notaTagihan() {

		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			notaPesanan += Double.parseDouble(item.getHarga());
		}

		for (Iterator<Promosi> i = daftarPromo.iterator(); i.hasNext();) {
			Promosi promo = i.next();
			notaPesanan += promo.hargaPromo();
		}

		System.out.println("Nota Pesanan : " + notaPesanan);
		return notaPesanan;
	}

	public void hapusPesanan() {
		Scanner sc = new Scanner(System.in);
		String input;

		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			System.out.println("Item: " + item.getNama() + " " + "Rp. " + item.getHarga() + " ribu");
		}
		System.out.println("\n Masukkan ID item untuk menghapus pesanan \n tekan 0 jika nggak jadi menghapus");
		input = sc.nextLine();
		if (!input.equals(0)) {
			for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
				Item item = i.next();
				if (item.getItemID().equals(input)) {
					i.remove();
				}
			}
		}

		for (Iterator<Promosi> i = daftarPromo.iterator(); i.hasNext();) {
			Promosi promo = i.next();
			System.out.println("Promosi : " + promo.getNama() + " " + "Rp. " + promo.hargaPromo() + " ribu");
		}
		System.out.println("\n Masukkan ID item untuk menghapus pesanan \n tekan 0 jika nggak jadi menghapus");
		input = sc.nextLine();
		if (!input.equals(0)) {
			for (Iterator<Promosi> p = daftarPromo.iterator(); p.hasNext();) {
				Promosi promo = p.next();
				if (promo.getPromoID().equals(input)) {
					p.remove();
				}
			}
		}
	}

	public void tampilNota(Staff staff, Reservasi r) throws ParseException {

		r.setWaktuAkhir();

		Calendar c = Calendar.getInstance();

		DecimalFormat df = new DecimalFormat("#.##");

		System.out.println("================= Resto Joglo Manis =================");
		System.out.println("Nama Staff : " + staff.getNamaStaff());
		System.out.println("No. Meja   : " + r.getIdMeja());
		System.out.format("Tanggal	   : %te %tB, %tY%n", c, c, c);
		System.out.println("---------------------------------------------------------");

		if (daftarItem.size() > 0) {
			System.out.println("Menu Items : ");

			for (int i = 0; i < daftarItem.size(); i++) {
				System.out.println(" " + (daftarItem.get(i)).getNama() + " " + (daftarItem.get(i)).getHarga());
			}
		}

		if (daftarPromo.size() > 0) {
			System.out.println("---------------------------------------------------------");
			System.out.println("Paket Promosi : ");

			for (int i = 0; i < daftarPromo.size(); i++) {
				System.out.println(" " + (daftarPromo.get(i)).getNama() + " " + (daftarPromo.get(i)).hargaPromo());
			}
		}
		notaTagihan();
		notaTotal = notaPesanan * 1.17;

		System.out.println("---------------------------------------------------------");
		System.out.println("Sub Total : " + df.format((notaPesanan)));
		System.out.println("Pajak : " + df.format((notaPesanan * 0.17)));
		System.out.println("--------------------------------------------------------- ");
		System.out.println("TOTAL : " + df.format((notaPesanan) * 1.17));
		System.out.println("============= Terima Kasih! Datang lagi yaa,..=============\n");
		cetakNota(staff, r);
	}

	public void cetakNota(Staff staff, Reservasi r) {
		
		DecimalFormat df = new DecimalFormat("#.##");
		Calendar c = Calendar.getInstance();
		
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("nota.txt", false)))) {
			out.println("================= Resto Joglo Manis =================");
			out.println("Nama Staff : " + staff.getNamaStaff());
			out.println("No. Meja   : " + r.getIdMeja());
			out.format("Tanggal	   : %te %tB, %tY%n", c, c, c);
			out.println("---------------------------------------------------------");

			if (daftarItem.size() > 0) {
				out.println("Menu Items : ");

				for (int i = 0; i < daftarItem.size(); i++) {
					out.println(" " + (daftarItem.get(i)).getNama() + " " + (daftarItem.get(i)).getHarga());
				}
			}

			if (daftarPromo.size() > 0) {
				out.println("---------------------------------------------------------");
				out.println("Paket Promosi : ");

				for (int i = 0; i < daftarPromo.size(); i++) {
					out.println(" " + (daftarPromo.get(i)).getNama() + " " + (daftarPromo.get(i)).hargaPromo());
				}
			}
			
			out.println("Nota Pesanan : " + notaPesanan);
			out.println("---------------------------------------------------------");
			out.println("Sub Total : " + df.format((notaPesanan)));
			out.println("Pajak : " + df.format((notaPesanan * 0.17)));
			out.println("--------------------------------------------------------- ");
			out.println("TOTAL : " + df.format((notaPesanan) * 1.17));
			
		} catch (IOException e) {
			// exception handling (tugas lain)
		}
	}

}
