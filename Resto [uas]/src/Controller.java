
/**
 * Class Controller
 * Menjembatani antar class
 * 
 * @author Ivan 
 * @version 1.0
 */
import java.util.*;
import java.text.*;

public class Controller {
	private UserInterface cView;
	private Database cDatabase;
	private int tipe_user;
	private int IDuser_skrg;
	private String user_skrg;
	private String tgl_skrg;

	private int hitung_pesanan_skrg; // menghitung pesanan hari ini
	private double total_penjualan; // total penjualan hari ini
	private int hitung_dibatalkan_skrg; // hitung pesanan dibatalkan hari ini
	private double total_dibatalkan; // total pesanan dibatalkan hari ini

	private String pesan_error;

	// mendefinisikan tipe user
	public final static int USER_ANONYMOUS = 0;
	public final static int USER_EMPLOYEE = 1;
	public final static int USER_MANAGER = 2;

	public Controller() {
		this.cDatabase = new Database();
		try {
			cDatabase.load_DB();
		} catch (DB_Exception de) {
			System.out.println(de.getPesanError());
			System.exit(0);
		}

		cView = new UserInterface(this);

		Date date = new Date();
		SimpleDateFormat stf = new SimpleDateFormat("dd/MM/yyyy");
		tgl_skrg = stf.format(date);
		cView.setVisible(true);
		cView.setTodaysDate(tgl_skrg);
		this.tipe_user = USER_ANONYMOUS;

		hitung_pesanan_skrg = 0;
		total_penjualan = 0;
		hitung_dibatalkan_skrg = 0;
		total_dibatalkan = 0;
	}

	private void setErrorMessage(String errorMessage) {
		this.pesan_error = errorMessage;
	}

	public String getErrorMessage() {
		String hasil = this.pesan_error;
		this.pesan_error = "";
		return hasil;
	}

	public int getTodaysOrderCnt() {
		return this.hitung_pesanan_skrg;
	}

	public int getTodaysCancelCnt() {
		return this.hitung_dibatalkan_skrg;
	}

	public double getTotalSales() {
		return this.total_penjualan;
	}

	public double getCancelTotal() {
		return this.total_dibatalkan;
	}

	public double getOrderTotalCharge(int ID_pesanan) {
		return cDatabase.getBiaya_totalPesanan(ID_pesanan);
	}

	public int getOrderState(int ID_pesanan) {
		return cDatabase.getKondisi_pesanan(ID_pesanan);
	}

	public String getCurrentUserName() {
		return this.user_skrg;
	}

	public boolean checkIfUserClockedOut() {
		Staff rStaff = cDatabase.cari_IDstaff(IDuser_skrg);

		if (rStaff == null)
			return false;
		if (rStaff.getStatus_Kerja() == Staff.statusKerja_Aktif)
			return true;
		else
			return false;
	}

	/***********************************************************
	 * Login
	 **********************************************************/
	// ----------------------------------------------------------
	// Cek user
	// ----------------------------------------------------------
	public boolean loginCheck(int inputID, String inputPassword, boolean cek_manager) {
		String cari_namaClass;

		// ---------cari user----------
		Staff rStaff = cDatabase.cari_IDstaff(inputID);

		if (cek_manager)
			cari_namaClass = "Manager";
		else
			cari_namaClass = "Pegawai";

		if (rStaff != null) // data user ketemu
		{
			// cari pada target tertentu (Manager / Pegawai)
			if (rStaff.getClass().getName().equalsIgnoreCase(cari_namaClass)) {
				if (rStaff.getPassword().equals(inputPassword)) {
					if (rStaff.getStatus_Kerja() == 0) // belum masuk
					{
						rStaff.presensi();
					}
					if (cek_manager) {
						tipe_user = USER_MANAGER;
						cView.changeMode(UserInterface.MODE_MANAGER);
					} else {
						tipe_user = USER_EMPLOYEE;
						cView.changeMode(UserInterface.MODE_EMPLOYEE);
					}
					IDuser_skrg = inputID;
					user_skrg = rStaff.getNamaLengkap();
					cView.setLoginUserName(user_skrg); // tampilkan username
																// on the view

					return true; // Login berhasil
				} else {
					setErrorMessage("Password tidak sesuai.");
					return false;
				}
			} else // ID ketemu tapi tipe(Manager / Pegawai) tidak sesuai
			{
				setErrorMessage("Data tidak sesuai.");
				return false;
			}
		} else {
			setErrorMessage("Data tidak sesuai.");
			return false;
		}

	}

	// ----------------------------------------------------------
	// Logout (Set status sbg Anonymous)
	// ----------------------------------------------------------
	public void userLogout() {
		tipe_user = USER_ANONYMOUS;
		IDuser_skrg = 0;
		cView.setLoginUserName("");
	}

	/***********************************************************
	 * Atur Staff
	 **********************************************************/
	public boolean addNewStaff(int ID_baru, String password_baru, String namaDepan_baru, String namaBelakang_baru,
			boolean cek_manager) {
		Staff rStaff = cDatabase.cari_IDstaff(ID_baru);
		if (rStaff != null) {
			setErrorMessage("ID:" + ID_baru + " telah digunakan oleh " + rStaff.getNamaLengkap());
			return false;
		}

		try {
			cDatabase.tambah_staff(ID_baru, password_baru, namaDepan_baru, namaBelakang_baru, cek_manager);
			return true;
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
	}

	public boolean updateStaff(int id, String password_baru, String namaDepan_baru, String namaBelakang_baru) {
		try {
			cDatabase.edit_dataStaff(id, password_baru, namaDepan_baru, namaBelakang_baru);
			return true;
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
	}

	public boolean deleteStaff(int id) {
		Staff rStaff = cDatabase.cari_IDstaff(id);
		if (rStaff == null) {
			setErrorMessage("ID Staff:" + id + " tidak ada.");
			return false;
		}

		try {
			cDatabase.hapus_staff(rStaff);
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
		return true;
	}

	public Staff getStaffData(int staffID) {
		return cDatabase.cari_IDstaff(staffID);
	}

	public boolean clockOut() {
		return clockOut(IDuser_skrg);
	}

	public boolean clockOut(int staffID) {
		Staff rStaff = cDatabase.cari_IDstaff(staffID);

		byte status = rStaff.getStatus_Kerja();
		boolean hasil = false;
		switch (status) {
		case Staff.statusKerja_Aktif:
			rStaff.absen();
			hasil = true;
			break;
		case Staff.statusKerja_Selesai:
			setErrorMessage("Staff:" + rStaff.getNamaLengkap() + " telah pulang.");
			break;
		default:
			setErrorMessage("Staff:" + rStaff.getNamaLengkap() + " hari ini tidak bekerja.");
			break;
		}

		return hasil;
	}

	public void clockOutAll() {
		cDatabase.pulangkan_semuaStaff();
	}

	/***********************************************************
	 * Atur Menu
	 **********************************************************/
	public boolean addNewMenuItem(int ID_baru, String nama_baru, double harga_baru, byte tipe_menu) {
		MenuItem rMenuItem = cDatabase.cari_IDitem(ID_baru);
		if (rMenuItem != null) {
			setErrorMessage("ID:" + ID_baru + " telah digunakan oleh " + rMenuItem.getNama());
			return false;
		}

		try {
			cDatabase.tambah_menuItem(ID_baru, nama_baru, harga_baru, tipe_menu);
			return true;
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
	}

	public boolean updateMenuItem(int id, String nama_baru, double harga_baru, byte tipe_menu) {
		try {
			cDatabase.edit_data_menuItem(id, nama_baru, harga_baru, tipe_menu);
			return true;
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
	}

	public boolean deleteMenuItem(int id) {
		MenuItem rMenuItem = cDatabase.cari_IDitem(id);
		if (rMenuItem == null) {
			setErrorMessage("Menu item ID:" + id + " tidak ada.");
			return false;
		}

		try {
			cDatabase.hapus_menuItem(rMenuItem);
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return false;
		}
		return true;
	}

	public MenuItem getMenuItemData(int menuItemID) {
		return cDatabase.cari_IDitem(menuItemID);
	}

	/***********************************************************
	 * Atur Pesanan
	 **********************************************************/
	public int createOrder() {
		return cDatabase.tambah_pesanan(IDuser_skrg, user_skrg);
	}

	public boolean addNewOrderItem(int ID_pesanan, int tambah_IDitem, byte tambah_jumlahItem) {
		Pesanan rOrder = cDatabase.cari_IDpesanan(ID_pesanan);
		if (IDuser_skrg != rOrder.getID_staff()) {
			setErrorMessage("Anda tidak berhak mengubah pesanan ini \n Pesanan ini telah ditangani oleh "
					+ rOrder.getNamaStaff() + ")");
			return false;
		}

		MenuItem rItem_baru = null;

		rItem_baru = cDatabase.cari_IDitem(tambah_IDitem);
		if (rItem_baru == null) {
			setErrorMessage("MenuID[" + tambah_IDitem + "] tidak ada.");
			tambah_IDitem = 0;
			return false;
		}

		// tambah (database)
		cDatabase.tambah_itemPesanan(ID_pesanan, rItem_baru, tambah_jumlahItem);
		return true;
	}

	public boolean deleteOrderItem(int ID_pesanan, int hapus_no) {
		Pesanan rOrder = cDatabase.cari_IDpesanan(ID_pesanan);
		if (IDuser_skrg != rOrder.getID_staff()) {
			setErrorMessage("Anda tidak berhak menghapus pesanan ini \n Pesanan ini telah ditangani oleh "
					+ rOrder.getNamaStaff() + ")");
			return false;
		}

		hapus_no -= 1; // index sebenarnya dimulai dari 0
		if (!cDatabase.hapus_itemPesanan(ID_pesanan, hapus_no)) {
			setErrorMessage("Tidak ada.");
			return false;
		}
		return true;
	}

	public boolean closeOrder(int close_IDpesanan) {
		Pesanan rOrder = cDatabase.cari_IDpesanan(close_IDpesanan);
		if (IDuser_skrg != rOrder.getID_staff()) {
			setErrorMessage("Anda tidak berhak menghapus pesanan ini \n Pesanan ini telah ditangani oleh "
					+ rOrder.getNamaStaff() + ")");
			return false;
		}

		if (rOrder.getStatus() != 0) {
			setErrorMessage("Pesanan ini telah dibayar / dibatalkan.");
			return false;
		}
		cDatabase.bayar_pesanan(close_IDpesanan);
		hitung_pesanan_skrg++;
		total_penjualan += rOrder.getTotal();
		return true;
	}

	public boolean cancelOrder(int cancel_IDpesanan) {
		Pesanan rOrder = cDatabase.cari_IDpesanan(cancel_IDpesanan);
		if (IDuser_skrg != rOrder.getID_staff()) {
			setErrorMessage("Anda tidak berhak menghapus pesanan ini \n Pesanan ini telah ditangani oleh "
					+ rOrder.getNamaStaff() + ")");
			return false;
		}

		if (rOrder.getStatus() != 0) {
			setErrorMessage("Pesanan ini telah dibayar / dibatalkan.");
			return false;
		}

		cDatabase.batalkan_pesanan(cancel_IDpesanan);
		hitung_dibatalkan_skrg++;
		total_dibatalkan += rOrder.getTotal();
		return true;
	}

	public void closeAllOrder() {
		cDatabase.bayar_semuaPesanan();
	}

	/***********************************************************
	 * File Laporan dan Nota
	 **********************************************************/
	public String generateSalesReport() {
		if (!cDatabase.cek_semuaPesanan_dibayar()) {
			setErrorMessage("Sebelum mencetak nota, \n Pastikan semua pesanan telah dibayar / dibatalkan.");
			return null;
		}

		try {
			return cDatabase.cetak_nota_pesanan(tgl_skrg);
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return null;
		}
	}

	public String generatePaymentReport() {
		if (!cDatabase.cek_semuaStaff_pulang()) {
			setErrorMessage("Sebelum mencetak laporan gaji pegawai, \n Pastikan semua pegawai sudah pulang.");
			return null;
		}

		try {
			return cDatabase.cetak_pembayaran(tgl_skrg);
		} catch (DB_Exception de) {
			setErrorMessage(de.getPesanError());
			return null;
		}
	}

	/***********************************************************
	 * Buat daftar management proses
	 **********************************************************/
	public ArrayList<String> createStaffList() {
		Iterator<Staff> it = cDatabase.getDaftar_staff().iterator();
		ArrayList<String> initData = new ArrayList<String>();

		while (it.hasNext()) {
			Staff re = it.next();
			String namaLengkap = re.getNamaLengkap();
			String output = String.format("ID:%4d  Nama:%-25s", re.getID(), namaLengkap);
			switch (re.getStatus_Kerja()) {
			case Staff.statusKerja_Aktif:
				output += "[dari:" + re.getWaktu_Mulai() + "]";
				break;
			case Staff.statusKerja_Selesai:
				output += "[dari:" + re.getWaktu_Mulai() + " s/d " + re.getWaktu_Selesai() + "]";
				break;
			default:
				output += "[Tidak bekerja]";
				break;
			}

			if (re instanceof Manager) {
				output += " * Manager *";
			}
			initData.add(output);
		}

		return initData;
	}

	public ArrayList<String> createOrderList() {
		Iterator<Pesanan> it = cDatabase.getDaftar_pesanan().iterator();
		String status;
		ArrayList<String> initData = new ArrayList<String>();
		String output;

		while (it.hasNext()) {
			Pesanan re = it.next();
			switch (re.getStatus()) {
			case Pesanan.pesanan_dibayar:
				status = "Closed";
				break;
			case Pesanan.pesanan_dibatalkan:
				status = "Canceled";
				break;
			default:
				status = "-";
				break;
			}

			output = String.format("ID:%4d  Nama Staff: %-20s  Total: Rp.%5.2f \t Status: %-8s\n", re.getID_pesanan(),
					re.getNamaStaff(), re.getTotal(), status);
			initData.add(output);
		}
		if (initData.isEmpty())
			initData.add("Belum ada pesanan.");
		return initData;
	}

	public ArrayList<String> createOrderItemlList(int orderID)
	{
		Pesanan rOrder = cDatabase.cari_IDpesanan(orderID);
		ArrayList<String> initData = new ArrayList<String>();

		if (rOrder == null) {
			initData.add("Belum ada informasi pemesanan");
			return initData;
		}

		String output;

		Iterator<RincianPesanan> it = rOrder.getRincian_pesanan().iterator();
		RincianPesanan re;

		int hitung = 0;

		while (it.hasNext()) {
			re = it.next();
			output = String.format("%-4d|%-24s|%5d|%5.2f", ++hitung, re.getNama_item(), re.getJumlah(),
					re.getTotal_harga());
			initData.add(output);
		}
		if (initData.isEmpty())
			initData.add("Tidak ada item");
		return initData;
	}

	public ArrayList<String> createMenuList(int tampil_tipeMenu) {
		Iterator<MenuItem> it = cDatabase.getDaftar_menu().iterator();
		ArrayList<String> initData = new ArrayList<String>();

		while (it.hasNext()) {
			MenuItem re = it.next();
			byte tipe_menu = re.getTipe();
			if (tampil_tipeMenu != 0 && tampil_tipeMenu != tipe_menu)
				continue;
			String strMenuType;
			switch (tipe_menu) {
			case MenuItem.Makanan:
				strMenuType = "Main";
				break;
			case MenuItem.Minuman:
				strMenuType = "Drink";
				break;
			case MenuItem.Penyegar:
				strMenuType = "Alcohol";
				break;
			case MenuItem.Cuci_Mulut:
				strMenuType = "Dessert";
				break;
			default:
				strMenuType = "Undefined";
				break;
			}
			String output = String.format("Menu ID:%4d  Nama: %-20s  Harga: %5.2f \t Tipe: %s", re.getID(), re.getNama(),
					re.getHarga(), strMenuType);
			if (re.getStatus() == MenuItem.item_promosi) {
				output += " ** Promo Special!! **";
			}

			initData.add(output);
		}
		if (initData.isEmpty())
			initData.add("Belum ada pesanan.");
		return initData;
	}

	public String createPaymentList() {
		double total_pembayaran = 0;
		int no_staff = 0;
		String output = "";

		Iterator<Staff> it = cDatabase.getDaftar_staff().iterator();
		while (it.hasNext()) {
			Staff re = it.next();

			if (re.getStatus_Kerja() == Staff.statusKerja_Selesai) {
				double pay = re.hitung_Gaji();
				output += String.format("ID Staff:%4d  Nama Staff:%-20s  Lama kerja:%5.2f Gaji:%5.2f\n", re.getID(),
						re.getNamaLengkap(), re.hitung_WK(), pay);
				no_staff++;
				total_pembayaran += pay;
			} else if (re.getStatus_Kerja() == Staff.statusKerja_Aktif) {
				output += String.format("ID Staff:%4d  Nama Staff:%-20s  * Bekerja *\n", re.getID(),
						re.getNamaLengkap());
				no_staff++;
			}
		}
		output += "-------------------------------------------------------\n";
		output += String.format("Total Pembayaran:Rp.%.2f (%d)", total_pembayaran, no_staff);
		return output;
	}
}
