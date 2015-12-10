
import java.util.*;
import java.text.*;

public abstract class Staff {
	private int ID;
	private String namaBelakang;
	private String namaDepan;
	private String password;
	private byte kondisi;

	private Pesanan[] daftarPesanan;

	//protected byte statusKerja; //0:tdk aktif 1:aktif (bekerja) 2:selesai bekerja
	protected Date mulai_WK;
	protected Date akhir_WK;
	protected double nominal_gaji;

	// ------------------------------------------------------------
	// constructor
	// ------------------------------------------------------------
	public Staff() {
		ID = 0;
		namaBelakang = "";
		namaDepan = "";
		mulai_WK = null;
		akhir_WK = null;
		kondisi = 0;
	}

	public Staff(int ID_baru, String namaBelakang_baru, String namaDepan_baru, String password_baru) {
		setID(ID_baru);
		setNama_Belakang(namaBelakang_baru);
		setNama_Depan(namaDepan_baru);
		setPassword(password_baru);
		mulai_WK = null;
		akhir_WK = null;
		kondisi = 0;
		// workState = 0;
	}

	// ------------------------------------------------------------
	// setter
	// ------------------------------------------------------------
	protected void setID(int ID_baru) {
		this.ID = ID_baru;
	}

	protected void setNama_Belakang(String namaBelakang_baru) {
		this.namaBelakang = namaBelakang_baru;
	}

	protected void setNama_Depan(String namaDepan_baru) {
		this.namaDepan = namaDepan_baru;
	}

	protected void setPassword(String password_baru) {
		this.password = password_baru;
	}

	protected void setKondisi_Kerja(byte kondisi_baru) {
		this.kondisi = kondisi_baru;
	}

	// ------------------------------------------------------------
	// getter
	// ------------------------------------------------------------
	public int getID() {
		return this.ID;
	}

	public String getNamaBelakang() {
		return this.namaBelakang;
	}

	public String getNamaDepan() {
		return this.namaDepan;
	}

	public String getNamaLengkap() {
		String namaLengkap = this.namaDepan + " " + this.namaBelakang;
		return namaLengkap;
	}

	public String getPassword() {
		return this.password;
	}

	public double getNominal_Gaji() {
		return this.nominal_gaji;
	}

	public static final byte statusKerja_nonAktif = 0;
	public static final byte statusKerja_Aktif = 1;
	public static final byte statusKerja_Selesai = 2;

	public byte getStatus_Kerja() {
		return this.kondisi;
	}

	public String getWaktu_Mulai() {
		if (mulai_WK == null)
			return "getWaktu_Mulai Error";
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(mulai_WK);
	}

	public String getWaktu_Selesai() {
		if (akhir_WK == null)
			return "getWaktu_Selesai Error";
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(akhir_WK);
	}

	// ------------------------------------------------------------
	// method lain
	// ------------------------------------------------------------
	public void presensi() {
		mulai_WK = new Date(System.currentTimeMillis());
		kondisi = statusKerja_Aktif;
	}

	public boolean absen() {
		if (kondisi != statusKerja_Aktif)
			return false;
		akhir_WK = new Date(System.currentTimeMillis());
		kondisi = statusKerja_Selesai;
		return true;
	}

	public boolean ubahWaktu_Mulai(Date waktuMulai_baru) {
		if (kondisi == statusKerja_Selesai && waktuMulai_baru.after(akhir_WK)) {
			return false;
		}

		if (waktuMulai_baru.after(new Date(System.currentTimeMillis()))) {
			return false;
		}

		mulai_WK = waktuMulai_baru;
		return true;
	}

	public boolean ubahWaktu_Selesai(Date waktuSelesai_baru) {
		if (waktuSelesai_baru.before(mulai_WK)) {
			return false;
		}

		if (waktuSelesai_baru.after(new Date(System.currentTimeMillis()))) {
			return false;
		}

		akhir_WK = waktuSelesai_baru;
		return true;
	}

	public double hitung_WK() {
		if (getStatus_Kerja() != statusKerja_Selesai)
			return 0;

		long hitung_LK = (akhir_WK.getTime() - mulai_WK.getTime()) / 60000; //konversi ms ke menit
		long waktuInti = hitung_LK / 60;
		long waktuSisa = hitung_LK % 60;
		double tambahWaktu;

		if (waktuSisa < 15)
			tambahWaktu = 0;
		else if (waktuSisa < 30)
			tambahWaktu = 0.25;
		else if (waktuSisa < 45)
			tambahWaktu = 0.5;
		else
			tambahWaktu = 0.75;

		double waktuKerja = waktuInti + tambahWaktu;
		return waktuKerja;
	}

	protected abstract void setNominal_Gaji(double gajiBaru);

	protected abstract double hitung_Gaji();
}
