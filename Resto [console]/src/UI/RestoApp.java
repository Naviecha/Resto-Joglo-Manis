package UI;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Date;
import java.util.Calendar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import Controller.AturMeja;
import Controller.ManageSistem;
import Sistem.Nota;
import Sistem.Pesanan;
import Sistem.Reservasi;
import Sistem.Staff;

/**
 * Resto App digunakan untuk mengintegrasikan keseluruhan sistem, controller dan utility
 */
public class RestoApp {

	static Scanner sc = new Scanner(System.in);
	static Reservasi r;
	static ArrayList<Reservasi> daftarPesanan;
	static SimpleDateFormat sdf;
	static ManageSistem sm;
	static Staff staff;
	AturMeja aturMeja;

	/**
	 * method utama ini berisi control flow program
	 * @param args
	 * @throws ParseException
	 * @throws IOException
	 */

	public static void main(String[] args) throws ParseException, IOException {
		//Pesanan o = new Pesanan();
		AturMeja aturMeja = new AturMeja();
		aturMeja.getMejaDitempati();
		staff = new Staff();
		boolean k;
		// mulai program
		k = setIdStaff();
		if (k) {
			int pilih;
			do {
				System.out.println("\n===========================");
				System.out.println("1: Cek ketersediaan Meja ");
				System.out.println("2: Atur Pesanan Makan ");
				System.out.println("3: Atur Reservasi ");
				System.out.println("4: Atur Promosi ");
				System.out.println("5: Atur Item");
				System.out.println("6: Lihat Data Penjualan");
				System.out.println("7: keluar");
				System.out.print("Masukkan Pilihan : ");
				pilih = sc.nextInt();
				switch (pilih) {
				case 1:
					System.out.print("\nButuh berapa kursi ?? ");
					int jum_kursi = sc.nextInt();
					sc.nextLine();
					if (jum_kursi > 0 && (jum_kursi % 2 == 0)) {
						aturMeja.cek_KetersediaanMeja(Integer.toString(jum_kursi));
					} else {
						aturMeja.cek_KetersediaanMeja(Integer.toString(jum_kursi + 1));
					}
					
					break;
				case 2:
					int input_2;
					do {
						System.out.println("\n1: Buat Pesanan ");
						System.out.println("2: Hapus Pesanan ");
						System.out.println("3: Lihat Pesanan ");
						System.out.println("4: Bayar Pesanan");
						System.out.println("5: kembali ke menu utama");
						System.out.print("Masukkan pilihan : ");
						input_2 = sc.nextInt();
						sc.nextLine();
						switch (input_2) {
						case 1:
							boolean hasil = false;
							int idMeja;

							System.out.println("\nMasukkan No. Meja : ");
							idMeja = sc.nextInt();

							for (Reservasi r : aturMeja.listMejaDitempati) {
								if (Integer.parseInt(r.getIdMeja()) == idMeja) {
									r.meja[idMeja].order.ambilPesanan();
									hasil = true;
									break;
								}

							}
							if (hasil)
								System.out.println("\nItem ditambahkan !!");
							else {
								System.out.println("\nTidak bisa memesan untuk meja kosong !");
								System.out.println("Silahkan pesan meja terlebih dahulu ");
							}

							break;
						case 2:
							boolean hasil_2 = false;
							int idMeja_2;

							System.out.println("\nMasukkan No. Meja : ");
							idMeja_2 = sc.nextInt();

							for (Reservasi r : aturMeja.listMejaDitempati) {
								if (Integer.parseInt(r.getIdMeja()) == idMeja_2) {
									r.meja[idMeja_2].order.hapusPesanan();
									hasil_2 = true;
								}
							}
							if (hasil_2)
								System.out.println("\nItem/Promosi dihapus!");
							else
								System.out.println("\nPesanan tidak jadi dihapus!");

							break;
						case 3:
							int idMeja_3;
							boolean hasil_3 = false;
							System.out.println("\nMasukkan No. Meja : ");
							idMeja_3 = sc.nextInt();

							for (Reservasi r : aturMeja.listMejaDitempati) {
								if (Integer.parseInt(r.getIdMeja()) == idMeja_3) {
									r.meja[idMeja_3].order.lihatPesanan();
									hasil_3 = true;
								}

							}
							if (!hasil_3)
								System.out.println("\nBelum ada pesanan untuk meja ini!");

							break;
						case 4:
							int idMeja_4;
							//boolean hasil_4 = false;
							System.out.println("\nMasukkan No. Meja : ");
							idMeja_4 = sc.nextInt();

							for (Reservasi r : aturMeja.listMejaDitempati) {
								if (Integer.parseInt(r.getIdMeja()) == idMeja_4) {
									//r.meja[idMeja_4].order.notaTagihan();
									r.meja[idMeja_4].order.tampilNota(staff, r);
									r.meja[idMeja_4].hapusReservasiDibayar(idMeja_4);
									break;
								}
							}
							//o.notaTagihan();
							break;

						case 5:
							input_2 = 5;
						}
					} while (input_2 < 5);

					break;
				case 3:
					int input_4;
					do {
						System.out.println("\n1: Buat Reservasi ");
						System.out.println("2: Lihat Reservasi ");
						System.out.println("3: Cek/Hapus Reservasi ");
						System.out.println("4: Kembali ke menu utama ");
						System.out.print("Pilih menu : ");
						input_4 = sc.nextInt();
						sc.nextLine();
						switch (input_4) {
						case 1:
							if (getBuatReservasi())
								System.out.println("\nReservasi Sukses ");
							else
								System.out.println("\nReservasi Gagal ");
							break;
						case 2:
							getReservasiMeja();
							break;
						case 3:
							getHapusReservasiMeja();
							break;
						case 4:
							input_4 = 4;
						}
					} while (input_4 < 4);
					break;
				case 4:
					int input_6;
					do {
						System.out.println("\n1: Buat Promosi Baru");
						System.out.println("2: Lihat Promosi");
						System.out.println("3: Ubah Promosi");
						System.out.println("4: Hapus Promosi");
						System.out.println("5: Kembali ke menu utama ");
						System.out.print("Pilih menu : ");
						input_6 = sc.nextInt();
						switch (input_6) {
						case 1:
							sm = new ManageSistem();
							if (sm.buatPromosi())
								System.out.println("\nPromosi baru telah ditambahkan!");
							else
								System.out.println("\nPromosi tidak jadi ditambahkan!");
							break;
						case 2:
							getLihatPromosi();
							break;
						case 3:
							break;
						case 4:
							getHapusPromosi();
							break;
						case 5:
							input_6 = 5;
						}
					} while (input_6 < 5);
					break;

				case 5:
					int input_7;
					do {
						System.out.println("\n1: Buat Item ");
						System.out.println("2: Lihat Item ");
						System.out.println("3: Ubah Item ");
						System.out.println("4: Hapus Item ");
						System.out.println("5: Kembali ke menu utama ");
						System.out.print("Pilih menu : ");
						input_7 = sc.nextInt();
						switch (input_7) {
						case 1:
							if (getBuatItem())
								System.out.println("\nItem telah ditambahkan");
							else
								System.out.println("\nItem tidak jadi ditambahkan");
							break;
						case 2:
							getLihatItem();
							break;
						case 3:
							getUbahItem();
							break;
						case 4:
							getHapusItem();
							break;
						case 5:
							input_7 = 5;
						}
					} while (input_7 < 5);
					break;
				case 6:
					if (staff.cekAdmin()) {
						RestoApp admin = new RestoApp();
						System.out.println("Login Admin Sukses");
						admin.Admin();
					}
					else {
						System.out.println("Login Gagal");
					}
					
					break;
				case 7:
					System.out.println("\n Program Dihentikan ");
					System.out.println(" =============\n Terima Kasih");
				}
			} while (pilih < 7);
		} else {
			main(args);
		}
	}

	/**
	 * menu buat reservasi baru untuk user by phone
	 * dengan estimasi waktu sekarang -> kedepan
	 * @return
	 * @throws ParseException
	 */
	public static boolean getBuatReservasi() throws ParseException {
		r = new Reservasi();
		System.out.print("\nTanggal Reservasi ? ");
		r.setTanggal(sc.nextLine());
		System.out.print("Waktu Reservasi ? ");
		r.setWaktuAwal(sc.nextLine());
		System.out.print("Untuk berapa orang ? ");
		r.setJum_Kursi(sc.nextLine());
		System.out.print("Nama Anda ? ");
		r.setNamaPelanggan(sc.nextLine());
		System.out.print("Kontak Anda ? ");
		r.setContact(sc.nextLine());
		return r.buatReservasi();
	}

	/**
	 * melihat daftar reservasi meja
	 * @throws ParseException
	 */

	public static void getReservasiMeja() throws ParseException {
		r = new Reservasi();
		daftarPesanan = r.getReservasiMeja();
		sdf = new SimpleDateFormat("HH:mm");
		System.out.println("\nTanggal Reservasi" + "    " + " Waktu Reservasi" + "     " + "No. Meja" + "    "
				+ "Nama Pelanggan " + "    " + "Kursi" + "    " + "Telephon");
		for (Iterator<Reservasi> r = daftarPesanan.iterator(); r.hasNext();) {
			Reservasi reservasi = r.next();
			reservasi.setWaktuAkhir();
			System.out.println(reservasi.getTanggal() + "          " + sdf.format(reservasi.getWaktuAwal()) + "-"
					+ sdf.format(reservasi.getWaktuAkhir()) + "          " + reservasi.getIdMeja() + "             "
					+ reservasi.getNamaPelanggan() + "            " + reservasi.getJum_Kursi() + "            "
					+ reservasi.getContact());
		}
	}

	/**
	 * hapus reservasi meja setelah proses memesan selesai
	 * @throws ParseException
	 */
	public static void getHapusReservasiMeja() throws ParseException {
		r = new Reservasi();
		r.getHapusReservasi();
	}

	/**
	 * @return buatItem() baru
	 */
	public static boolean getBuatItem() {
		sm = new ManageSistem();
		return sm.buatItem();
	}

	/**
	 * mengubah item yang sudah ada di daftar item.txt
	 */
	public static void getUbahItem() {
		sm = new ManageSistem();
		sm.ubahItem();
	}

	/**
	 * hapus item di item.txt berdasarkan index
	 */
	public static void getHapusItem() {
		sm = new ManageSistem();
		sm.hapusItem();
	}
	
	/**
	 * menghapus daftar item di paket Promosi
	 */
	public static void getHapusPromosi() {
		sm = new ManageSistem();
		sm.hapusPromosi();
	}

	/**
	 * melihat daftar item di paket Promosi
	 */
	public static void getLihatPromosi() {
		sm = new ManageSistem();
		sm.lihatPromosi();
	}

	/**
	 * melihat daftar item di item.txt kemudian tampilkan dalam menu
	 */
	public static void getLihatItem() {
		sm = new ManageSistem();
		sm.lihatItem();

	}

	/**
	 * cek id_staff berdasarkan ID dalam file staff.txt
	 * @throws FileNotFoundException
	 */
	public static boolean setIdStaff() throws FileNotFoundException {
		boolean hasil = false;
		sm = new ManageSistem();
		ArrayList<Staff> daftarStaff = sm.getStaffID();
		System.out.println("=================================");
		System.out.println("\tResto Joglo Manis");
		System.out.println("=================================\n");
		System.out.println("\t Menu Login Staff");
		System.out.println("\t ================");
		System.out.print("\n Masukkan ID Staff : ");
		staff.setIdStaff(sc.next());
		for (Staff s : daftarStaff) {
			if (staff.getIdStaff().equals(s.getIdStaff())) {
				staff = s;
				hasil = true;
			}
		}
		if (hasil) {
			System.out.println("\n Mau ngapain mas " + staff.getNamaStaff() + " ?");
		} else {
			System.out.println("\n ID Staff tidak ada");
		}
		return hasil;
	}
	
	public void Admin() {
		int input;
		Nota cek = new Nota();
		System.out.println("Cieee Admin,..");
		do {
			System.out.println("\n===========================");
			System.out.println("1: Lihat Data Penjualan");
			System.out.println("2: Keluar ");
			System.out.println("Masukkan pilihan : ");
			input = sc.nextInt();
			
			switch (input) {
			case 1: {
				cek.bacaNota();
			}
				break;
			case 2:
				input = 2	;
			}
		} while (input < 2);
	}
}
