package Controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import Sistem.Item;
import Sistem.Promosi;
import Sistem.Staff;

//Mengatur fungsi CRUD dari item dan promosi
//ambil staffID
public class ManageSistem {

	private Scanner s;
	private Item tempItem;
	private Promosi tempPromosi;
	private ArrayList<Item> daftarItem = new ArrayList<Item>();
	private ArrayList<Promosi> daftarPromo = new ArrayList<Promosi>();
	Staff staff;

	public ManageSistem() {
		getItem();
		getPromosi();
	}

	public void getItem() {

		try {
			s = new Scanner(new BufferedReader(new FileReader("item.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Item item = new Item(temp[0], temp[1], temp[2], temp[3], temp[4], temp[5]);
				daftarItem.add(item);
			}
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public void getPromosi() {
		try {
			s = new Scanner(new BufferedReader(new FileReader("promosi.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Promosi p = new Promosi();
				ArrayList<Item> tempDaftarPromo = new ArrayList<Item>();
				p.setPromoID(temp[0]);
				p.setNama(temp[1]);
				// loop untuk mencari berdasarkan nomor item di daftar file
				// promosi.txt
				for (int i = 2; i < temp.length; i++) {
					// bandingkan itemID dengan daftarItem yang berisi semua
					// itemID
					// untuk mengecek apakah posisi itemID sudah tepat dengan
					// objek item
					for (Iterator<Item> it = daftarItem.iterator(); it.hasNext();) {
						Item itemDiDaftarItem = it.next();
						if (temp[i].equals(itemDiDaftarItem.getItemID())) {
							tempDaftarPromo.add(itemDiDaftarItem);
						}
					}
				}
				// System.out.println("tempPromoList.size():
				// "+tempPromoList.size());
				p.setDaftarItem(tempDaftarPromo);
				daftarPromo.add(p);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	public boolean buatItem() {
		boolean hasil = false;
		s = new Scanner(System.in);
		tempItem = new Item();
		boolean adaItem = false;
		System.out.println("\nMasukkan nama item :");
		tempItem.setName(s.nextLine());
		System.out.println("Masukkan tipe item :");
		tempItem.setTipe(s.nextLine());
		System.out.println("Masukkan harga item :");
		tempItem.setHarga(s.nextLine());
		System.out.println("Masukkan ket item :");
		tempItem.setKet(s.nextLine());
		System.out.println("Masukkan diskon item :");
		tempItem.setDiskon(s.nextLine());
		// cek berdasarkan nama untuk mengetahui item ada atau tidak
		for (Item i : daftarItem) {
			if (i.getNama().equals(tempItem.getNama()))
				adaItem = true;
		}

		if (!adaItem) {
			// generate id_item
			int id_item = 0;
			for (Item i : daftarItem) {
				if (id_item < Integer.parseInt(i.getItemID()))
					id_item = Integer.parseInt(i.getItemID());
			}
			id_item++;
			tempItem.setItemID((Integer.toString(id_item)));

			try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("item.txt", true)))) {
				out.print("\n" + tempItem.getItemID() + "," + tempItem.getTipe() + "," + tempItem.getNama() + ","
						+ tempItem.getHarga() + "," + tempItem.getKet() + "," + tempItem.getDiskon());
				hasil = true;

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return hasil;
	}

	public void ubahItem() {
		s = new Scanner(System.in);
		int pilih;
		String id_item;
		String temp;
		System.out.print("\nID Item" + "       " + "Nama Item" + "       " + "Harga Item" + "       " + "Ket Item"
				+ "                  " + "Tipe Item" + "       " + "Harga Diskon");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			System.out.print("\n" + item.getItemID() + "            " + item.getNama() + "            "
					+ item.getHarga() + "            " + item.getKet() + "            " + item.getTipe()
					+ "            " + item.getDiskon());
		}
		System.out.println();
		System.out.print("\nMasukkan ID item yang akan diubah : ");
		id_item = s.nextLine();
		System.out.println("1. Ubah Nama");
		System.out.println("2. Ubah Harga");
		System.out.println("3. Ubah Keterangan");
		System.out.println("4. Ubah Tipe");
		System.out.println("5. Ubah Harga Diskon");
		pilih = s.nextInt();
		switch (pilih) {
		case 1:
			s.nextLine();
			System.out.print("\nNama baru untuk item : ");
			temp = s.nextLine();
			for (Item i : daftarItem) {
				if (i.getItemID().equals(id_item))
					i.setName(temp);
			}
			simpanItemArray_file();
			break;
		case 2:
			s.nextLine();
			System.out.println("Harga baru untuk item : ");
			temp = s.nextLine();
			for (Item i : daftarItem) {
				if (i.getItemID().equals(id_item))
					i.setHarga(temp);
			}
			simpanItemArray_file();
			break;
		case 3:
			s.nextLine();
			System.out.println("Keterangan baru untuk item :");
			temp = s.nextLine();
			for (Item i : daftarItem) {
				if (i.getItemID().equals(id_item))
					i.setKet(temp);
			}
			simpanItemArray_file();
			break;
		case 4:
			s.nextLine();
			System.out.println("Tipe baru untuk item");
			temp = s.nextLine();
			for (Item i : daftarItem) {
				if (i.getItemID().equals(id_item))
					i.setTipe(temp);
			}
			simpanItemArray_file();
			break;
		case 5:
			s.nextLine();
			System.out.println("Diskon baru untuk item");
			temp = s.nextLine();
			for (Item i : daftarItem) {
				if (i.getItemID().equals(id_item))
					i.setHarga(temp);
			}
			simpanItemArray_file();
			break;
		}
	}

	public void lihatItem() {

		System.out.println("\n=========== Hidangan Utama ===========");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			if (item.getTipe().equals("main course")) {
				System.out.println(item.getItemID() + "   " + item.getNama() + "   Rp. " + item.getHarga() + " ribu  "
						+ item.getKet());
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("=========== Minuman ===========");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			if (item.getTipe().equals("drink")) {
				System.out.println(item.getItemID() + "   " + item.getNama() + "   Rp. " + item.getHarga() + " ribu  "
						+ item.getKet());
				System.out.println();
			}
		}
		System.out.println();
		System.out.println("=========== Hidangan Penutup ============");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			if (item.getTipe().equals("dessert")) {
				System.out.println(item.getItemID() + "   " + item.getNama() + "   Rp. " + item.getHarga() + " ribu  "
						+ item.getKet());
				System.out.println();
			}
		}

		System.out.println();
		System.out.println("=========== Paket Promo ============");
		for (Iterator<Promosi> p = daftarPromo.iterator(); p.hasNext();) {
			Promosi promo = p.next();
			System.out.print(promo.getPromoID() + "   ");
			for (Iterator<Item> i = promo.getDaftarItem().iterator(); i.hasNext();) {
				Item item = i.next();
				System.out.print(item.getNama() + "   ");
			}
			System.out.println("   Rp. " + promo.hargaPromo() + " ribu ");
			System.out.println();
		}
		System.out.println();
	}

	public void hapusItem() {
		s = new Scanner(System.in);
		String id_item;
		tempItem = new Item();
		System.out.print("\nID Item" + "       " + "Nama Item" + "       " + "Harga Item" + "       " + "Ket Item"
				+ "                  " + "Tipe Item" + "       " + "Diskon");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			System.out.print("\n" + item.getItemID() + "            " + item.getNama() + "            "
					+ item.getHarga() + "            " + item.getKet() + "            " + item.getTipe()
					+ "            " + item.getDiskon());
		}
		System.out.println("\nMasukkan ID item yang akan dihapus");
		id_item = s.nextLine();
		System.out.println("\nDaftar Item : " + daftarItem.size());
		System.out.println("ID Item : " + id_item);
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();

			if (item.getItemID().equals(id_item)) {
				tempItem = item;
			}
		}
		daftarItem.remove(tempItem);
		System.out.println("\nDaftar Item : " + daftarItem.size());
		simpanItemArray_file();
	}

	public boolean buatPromosi() {

		boolean hasil = false;
		s = new Scanner(System.in);
		String id_item, namaPromo;
		ArrayList<String> tempList = new ArrayList<String>();
		// tampilkan isi item.txt, user bisa memilih item berdasarkan ID untuk
		// ditambahkan ke promosi.txt
		System.out.print("\nID Item" + "       " + "Nama Item" + "       " + "Harga Item" + "       " + "Ket Item"
				+ "                  " + "Tipe Item" + "       " + "Diskon");
		for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
			Item item = i.next();
			System.out.print("\n" + item.getItemID() + "            " + item.getNama() + "            "
					+ item.getHarga() + "            " + item.getKet() + "            " + item.getTipe()
					+ "            " + item.getDiskon());
		}

		do {
			System.out.println("\nmasukkan ID item untuk ditambahkan ke paket promosi \n tekan 0 untuk exit");
			id_item = s.nextLine();
			if (!id_item.equals("0"))
				tempList.add(id_item);
		} while (!id_item.equals("0"));
		System.out.println("\nMasukkan nama paket promosi");
		namaPromo = s.nextLine();

		// generate promoID
		int promoID = 0;
		for (Promosi promo : daftarPromo) {
			// hapus P (promo) untuk mendapatkan nomor
			if (promoID < Integer.parseInt(promo.getPromoID().substring(1, promo.getPromoID().length())))
				promoID = Integer.parseInt(promo.getPromoID().substring(1, promo.getPromoID().length()));
		}
		promoID++;
		// menulis hasil ke file txt ===
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promosi.txt", true)))) {

			out.print("\n" + "P" + Integer.toString(promoID) + "," + namaPromo);
			for (int i = 0; i < tempList.size(); i++) {
				out.print("," + tempList.get(i));
			}
			hasil = true;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return hasil;
	}

	public void hapusPromosi() {

		s = new Scanner(System.in);
		String id_promo;
		tempPromosi = new Promosi();

		lihatPromosi();

		System.out.println("\nMasukkan ID promosi yang akan dihapus");
		id_promo = s.nextLine();

		for (Iterator<Promosi> i = daftarPromo.iterator(); i.hasNext();) {
			Promosi promosi = i.next();

			if (promosi.getPromoID().equals(id_promo)) {
				tempPromosi = promosi;
			}
		}
		daftarPromo.remove(tempPromosi);
		// simpanPromoArray_file();
	}

	private void simpanPromoArray_file() {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("promosi.txt", false)))) {
			out.print("promoID, NamaPromo, item1, item2, item3, item4....");

			for (Iterator<Promosi> promo = daftarPromo.iterator(); promo.hasNext();) {
				Promosi p = promo.next();

				System.out.println("\nBerhasil diubah!");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void lihatPromosi() {
		int hitung = 1;
		System.out.print("\n============= Promosi ===============");
		for (Iterator<Promosi> promo = daftarPromo.iterator(); promo.hasNext();) {
			Promosi p = promo.next();
			System.out.print("\n" + hitung + ": " + p.getNama() + ":");
			for (Item item : p.getDaftarItem()) {
				System.out.print(" |" + item.getNama());
			}
			hitung++;
		}
		System.out.println();
		System.out.println();
	}

	private void simpanItemArray_file() {
		try (PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("item.txt", false)))) {
			out.print("itemID, tipe, nama, harga, ket, diskon");
			for (Iterator<Item> i = daftarItem.iterator(); i.hasNext();) {
				Item item = i.next();
				out.print("\n" + item.getItemID() + "," + item.getTipe() + "," + item.getNama() + "," + item.getHarga()
						+ "," + item.getKet() + "," + item.getDiskon());
			}
			System.out.println("\nBerhasil diubah!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<Staff> getStaffID() throws FileNotFoundException {
		ArrayList<Staff> daftarIDstaff = new ArrayList<Staff>();
		Scanner s = new Scanner(new BufferedReader(new FileReader("staff.txt")));
		s.nextLine();
		while (s.hasNext()) {

			String temp[] = s.nextLine().split(",");
			Staff staff = new Staff();
			staff.setIdStaff(temp[0]);
			staff.setNamaStaff(temp[1]);
			staff.setGender(temp[2]);
			staff.setJabatan(temp[3]);
			;
			daftarIDstaff.add(staff);

		}
		return daftarIDstaff;
	}
}
