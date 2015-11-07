package Sistem;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Scanner;

import Utility.formatTanggal;

public class Reservasi {
	private String tanggal, namaPelanggan, jum_kursi, contact, idMeja;
	private Date waktuAwal, waktuAkhir;
	String pola = "HH:mm";
	SimpleDateFormat sdf = new SimpleDateFormat(pola);
	formatTanggal df = new formatTanggal();
	Calendar kal = Calendar.getInstance();
	Scanner sc;
	ArrayList<Reservasi> ListAlokasiMeja = new ArrayList<Reservasi>();

	public Tempat meja[] = new Tempat[10];
	Tempat t1;

	public Reservasi() {
		// Pesanan order = new Pesanan();
		int ukuranMeja = 2;
		for (int i = 0; i < 10; i++) {
			if (i == 4)
				ukuranMeja = 4;
			if (i == 7)
				ukuranMeja = 6;
			if (i == 8)
				ukuranMeja = 8;
			if (i == 9)
				ukuranMeja = 10;
			meja[i] = new Tempat(ukuranMeja, i, 0);
		}
	}

	public Reservasi(String tanggal, String waktuAwal, String jum_kursi, String namaPelanggan, String contact,
			String idMeja) throws ParseException {

		this.tanggal = tanggal;
		this.waktuAwal = sdf.parse(waktuAwal);
		this.jum_kursi = jum_kursi;
		this.namaPelanggan = namaPelanggan;
		this.contact = contact;
		this.idMeja = idMeja;

		int ukuranMeja = 2;
		for (int i = 0; i < 10; i++) {
			meja[i] = new Tempat(ukuranMeja, i, 0);
			if (i == 4)
				ukuranMeja = 4;
			if (i == 7)
				ukuranMeja = 6;
			if (i == 8)
				ukuranMeja = 8;
			if (i == 9)
				ukuranMeja = 10;
		}
	}

	public Date getWaktuAwal() {
		return waktuAwal;
	}

	public void setWaktuAwal(String waktuAwal) {
		try {
			this.waktuAwal = sdf.parse(waktuAwal);
		} catch (ParseException e) {
			// Exception handling goes here
			System.out.println("sistem waktu awal belum selesai dikerjakan");
		}
	}

	public Date getWaktuAkhir() {
		return waktuAkhir;
	}

	public void setWaktuAkhir() throws ParseException {
		kal.setTime(waktuAwal);
		kal.add(Calendar.HOUR_OF_DAY, 1);
		this.waktuAkhir = kal.getTime();
	}

	public String getTanggal() {
		return tanggal;
	}

	public void setTanggal(String tanggal) {
		this.tanggal = tanggal;
	}

	public String getNamaPelanggan() {
		return namaPelanggan;
	}

	public void setNamaPelanggan(String namaPelanggan) {
		this.namaPelanggan = namaPelanggan;
	}

	public String getJum_Kursi() {
		return jum_kursi;
	}

	public void setJum_Kursi(String jum_kursi) {
		this.jum_kursi = jum_kursi;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getIdMeja() {
		return idMeja;
	}

	public void setIdMeja(String idMeja) {
		this.idMeja = idMeja;
	}

	public Tempat[] getMeja() {
		return meja;
	}

	public void setMeja(Tempat[] meja) {
		this.meja = meja;
	}

	public boolean buatReservasi() throws ParseException {

		// if(cekTanggal())
		for (int i = 0; i < 10; i++) {
			if (meja[i].getUkuran() == Integer.parseInt(jum_kursi)) {
				System.out.println("i: " + i);
				idMeja = Integer.toString(meja[i].getIdMeja() + 1);
				return meja[i].pesanMeja(tanggal, waktuAwal, jum_kursi, namaPelanggan, contact, idMeja);
			}
		}
		return false;

	}

	// untuk memastikan tanggal reservasi merupakan tanggal sekarang dan
	// setelahnya
	public boolean cekTanggal() throws ParseException {
		Date skrg = new Date();
		kal.setTime(waktuAwal);
		Date tgl_reservasi = kal.getTime();
		System.out.println(tgl_reservasi);
		System.out.println("Hari ini : " + skrg + "   tanggal reservasi : " + tgl_reservasi);
		System.out.println(skrg.compareTo(tgl_reservasi) > 0);
		if (skrg.compareTo(tgl_reservasi) >= 0)
			return false;
		else
			return true;
	}

	public ArrayList<Reservasi> getReservasiMeja() {
		t1 = new Tempat();
		return t1.getReservasiMeja();
	}

	public ArrayList<Reservasi> getReservasiMeja(String tanggal) {
		t1 = new Tempat();
		return t1.getReservasiMeja(tanggal);
	}

	public ArrayList<Reservasi> getReservasi_Skrg() {

		t1 = new Tempat();
		return t1.lamaMejaDipesan();
	}

	public void getHapusReservasi() throws ParseException {
		t1 = new Tempat();
		t1.hapusReservasiMeja();
	}

	// atur alokasi meja
	public void alokasiMeja(String tanggal, Date waktuAwal, String jum_kursi) throws ParseException, IOException {

		/*
		 * cek untuk setiap meja, jika ada id_meja sama dengan id_meja yang ada
		 * di daftar reservasi, cek juga waktuAwal dan waktuAkhir nya jika
		 * id_meja waktu reservasinya tidak sama, cek id_meja lain yang mungkin
		 * memiliki jum_kursi sama
		 */
		for (int i = 0; i < 10; i++) {
			if (meja[i].getUkuran() == Integer.parseInt(jum_kursi)) {
				idMeja = Integer.toString(meja[i].getIdMeja() + 1);
				Reservasi r = meja[i].pesanMejaTersedia(tanggal, waktuAwal, jum_kursi, idMeja);
				if (r != null) {
					this.setContact(r.getContact());
					this.setNamaPelanggan(r.getNamaPelanggan());
					this.setTanggal(r.getTanggal());
					this.setJum_Kursi(r.getJum_Kursi());
					this.setWaktuAwal(df.getWaktu(r.getWaktuAwal()));
					this.setWaktuAkhir();
					break;
				}
			}
		}
	}
}
