
import java.util.*;

public class Pesanan {
	final public static int pesanan_dibayar = 1;
	final public static int pesanan_dibatalkan = 2;

	private int ID_pesanan;
	private int ID_staff;
	private String namaStaff;
	private String tanggal;
	private int status; // 0:idle 1:dibayar 2:dibatalkan
	private double total;
	private ArrayList<RincianPesanan> List_rincianPesanan = new ArrayList<RincianPesanan>();

	/**
	 * Constructor for objects of class Order
	 */
	public Pesanan(int IDstaff_baru, String namaStaff_baru) {
		this.ID_pesanan = -1;
		this.status = 0;
		this.ID_staff = IDstaff_baru;
		this.namaStaff = namaStaff_baru;
		this.total = 0;
	}

	/**
	 * Getter
	 */
	int getID_pesanan() {
		return this.ID_pesanan;
	}

	int getID_staff() {
		return this.ID_staff;
	}

	String getNamaStaff() {
		return this.namaStaff;
	}

	int getStatus() {
		return this.status;
	}

	double getTotal() {
		return this.total;
	}

	ArrayList<RincianPesanan> getRincian_pesanan() {
		return this.List_rincianPesanan;
	}

	/**
	 * Setter
	 */
	public void setID_pesanan(int ID_baru) {
		this.ID_pesanan = ID_baru;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public void tambahItem(MenuItem rMenuItem_baru, byte jumlah) {
		Iterator<RincianPesanan> it = List_rincianPesanan.iterator();
		RincianPesanan re;

		boolean found = false;

		while (it.hasNext() && !found) {
			re = it.next();
			if (rMenuItem_baru.getID() == re.getID_item()) {
				found = true;
				re.tambahJumlah(jumlah);
			}
		}

		if (!found) {
			RincianPesanan rincian = new RincianPesanan(rMenuItem_baru, jumlah);
			List_rincianPesanan.add(rincian);

		}

		hitungTotal();
	}

	public boolean hapusItem(int index) {
		try {
			List_rincianPesanan.remove(index);
			hitungTotal();
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void hitungTotal() {
		total = 0;
		RincianPesanan re;
		Iterator<RincianPesanan> it = List_rincianPesanan.iterator();
		while (it.hasNext()) {
			re = it.next();
			total += re.getTotal_harga();
		}
	}
}
