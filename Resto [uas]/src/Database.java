
import java.util.*;
import java.io.*;
import java.util.Comparator;

public class Database {
	private final static String DB_staff = "dataFiles/staff.txt";
	private final static String DB_manager = "dataFiles/manager.txt";
	private final static String DB_menu = "dataFiles/menu_item.txt";
	private final static String DB_nota = "dataFiles/Laporan/nota_";
	private final static String DB_gaji = "dataFiles/Laporan/gaji_";
	private final static String DB_info_gaji = "dataFiles/info_gaji.txt";

	private ArrayList<Staff> daftar_staff = new ArrayList<Staff>();
	private ArrayList<MenuItem> daftar_menu = new ArrayList<MenuItem>();
	private ArrayList<Pesanan> daftar_pesanan = new ArrayList<Pesanan>();

	private Date tgl;
	int hitung_pesanan_skrg;

	/****************************************************************************
	 * Constructor
	 ***************************************************************************/
	public Database() {
		tgl = new Date();
		hitung_pesanan_skrg = 0; // Load DB pesanan??
	}

	/****************************************************************************
	 * Getter
	 ***************************************************************************/
	public ArrayList<Staff> getDaftar_staff() {
		return daftar_staff;
	}

	public ArrayList<MenuItem> getDaftar_menu() {
		return daftar_menu;
	}

	public ArrayList<Pesanan> getDaftar_pesanan() {
		return daftar_pesanan;
	}

	public int getHitung_pesanan_skrg() {
		return this.hitung_pesanan_skrg;
	}

	// ----------------------------------------------------------
	// Cari staff berdasarkan ID
	// ----------------------------------------------------------
	public Staff cari_IDstaff(int id) {
		Iterator<Staff> it = daftar_staff.iterator();
		Staff re = null;
		boolean cek = false;

		if (id < 0) {
			return null;
		}

		while (it.hasNext() && !cek) {
			re = it.next();
			if (re.getID() == id) {
				cek = true;
			}
		}

		if (cek)
			return re;
		else
			return null;
	}

	// ----------------------------------------------------------
	// Cari menu item berdasarkan ID
	// ----------------------------------------------------------
	public MenuItem cari_IDitem(int id) {
		Iterator<MenuItem> it = daftar_menu.iterator();
		MenuItem re = null;
		boolean cek = false;

		if (id < 0) {
			return null;
		}

		while (it.hasNext() && !cek) {
			re = it.next();
			if (re.getID() == id) {
				cek = true;
			}
		}

		if (cek)
			return re;
		else
			return null;
	}

	// ----------------------------------------------------------
	// Cari pesanan berdasarkan ID
	// ----------------------------------------------------------
	public Pesanan cari_IDpesanan(int id) {
		Iterator<Pesanan> it = daftar_pesanan.iterator();
		Pesanan re = null;
		boolean cek = false;

		if (id < 0) {
			return null;
		}

		while (it.hasNext() && !cek) {
			re = it.next();
			if (re.getID_pesanan() == id) {
				cek = true;
			}
		}

		if (cek)
			return re;
		else
			return null;
	}

	/****************************************************************************
	 * Pengelolaan Data
	 ***************************************************************************/
	// ---------------------------------------------------------------
	// Data Staff
	// ---------------------------------------------------------------
	// edit DB staff
	// rStaff mereferensikan pada staff
	// dimana 1:namaBelakang 2:namaDepan 3:password
	public final static int edit_namaBelakang = 1;
	public final static int edit_namaDepan = 2;
	public final static int edit_password = 3;

	public void edit_dataStaff(int ID_staff, String password_baru, String namaDepan_baru, String namaBelakang_baru)
			throws DB_Exception {
		Staff rStaff = cari_IDstaff(ID_staff);
		rStaff.setPassword(password_baru);
		rStaff.setNama_Belakang(namaBelakang_baru);
		rStaff.setNama_Depan(namaDepan_baru);

		try {
			if (rStaff instanceof Manager)
				ubah_DB_staff(true);// update DB manager
			else
				ubah_DB_staff(false);// update DB Pegawai
		} catch (DB_Exception dbe) {
			throw dbe;
		}
	}

	public void edit_dataStaff(Staff rStaff, int kondisi, String data_baru) throws DB_Exception {
		switch (kondisi) {
		case edit_namaBelakang:
			rStaff.setNama_Belakang(data_baru);
			break;
		case edit_namaDepan:
			rStaff.setNama_Depan(data_baru);
			break;
		case edit_password:
			rStaff.setPassword(data_baru);
			break;
		default:
			break;
		}

		try {
			if (rStaff instanceof Manager)
				ubah_DB_staff(true);// update DB manager
			else
				ubah_DB_staff(false);// update DB Pegawai
		} catch (DB_Exception dbe) {
			throw dbe;
		}
	}

	public void hapus_staff(Staff rStaff) throws DB_Exception {
		boolean cek_manager = false;
		daftar_staff.remove(rStaff);
		if (rStaff instanceof Manager)
			cek_manager = true;
		try {
			ubah_DB_staff(cek_manager);
		} catch (DB_Exception dbe) {
			throw dbe;
		}
	}

	public void tambah_staff(int ID_baru, String password_baru, String namaDepan_baru, String namaBelakang_baru,
			boolean cek_manager) throws DB_Exception {
		Staff staff_baru;
		if (cek_manager)
			staff_baru = new Manager(ID_baru, namaBelakang_baru, namaDepan_baru, password_baru);
		else
			staff_baru = new Pegawai(ID_baru, namaBelakang_baru, namaDepan_baru, password_baru);
		daftar_staff.add(staff_baru);
		if (staff_baru instanceof Manager)
			cek_manager = true;
		try {
			ubah_DB_staff(cek_manager);
		} catch (DB_Exception dbe) {
			throw dbe;
		}
	}

	// ---------------------------------------------------------------
	// Menu Item
	// ---------------------------------------------------------------
	// edit DB menu item
	// rMenuItem mereferensikan MenuItem
	// dimana 1:nama 2:harga 3:tipe
	public final static int edit_namaItem = 1;
	public final static int edit_hargaItem = 2;
	public final static int edit_tipeItem = 3;

	public void edit_data_menuItem(int id, String nama_baru, double harga_baru, byte tipeMenu) throws DB_Exception {
		MenuItem rMenuItem = cari_IDitem(id);
		rMenuItem.setNama(nama_baru);
		rMenuItem.setHarga(harga_baru);
		rMenuItem.setTipe(tipeMenu);
	}

	public void edit_data_menuItem(MenuItem rMenuItem, int kondisi, String data_baru) throws DB_Exception {
		try {
			switch (kondisi) {
			case edit_namaItem:
				rMenuItem.setNama(data_baru);
				break;
			case edit_hargaItem:
				double harga_baru = Double.parseDouble(data_baru);
				if (harga_baru < 0)
					throw new DB_Exception("Harga haruslah bilangan positif");
				else
					rMenuItem.setHarga(harga_baru);
				break;
			case edit_tipeItem:
				byte tipe_baru = Byte.parseByte(data_baru);
				if (tipe_baru < MenuItem.Makanan || MenuItem.Cuci_Mulut < tipe_baru)
					throw new DB_Exception(
							"Tipe harus berada diantara " + MenuItem.Makanan + " dan " + MenuItem.Cuci_Mulut + ")");
				else
					rMenuItem.setTipe(Byte.parseByte(data_baru));
				break;
			default:
				break;
			}
		} catch (DB_Exception e) {
			throw e;
		} catch (Exception e) {
			throw new DB_Exception(e.getMessage());
		}
	}

	public void setMenu_sbgPromosi(MenuItem rMenuItem, double harga) {
		rMenuItem.setStatus(MenuItem.item_promosi, harga);
	}

	public void reset_kondisiMenu(MenuItem rMenuItem) {
		rMenuItem.resetStatus();
	}

	public void hapus_menuItem(MenuItem rMenuItem) throws DB_Exception {
		daftar_menu.remove(rMenuItem);
	}

	public void tambah_menuItem(int ID_baru, String nama_baru, double harga_baru, byte tipe_baru) throws DB_Exception {
		MenuItem menuItem_baru = new MenuItem(ID_baru, nama_baru, harga_baru, tipe_baru);
		daftar_menu.add(menuItem_baru);
		Collections.sort(daftar_menu, new MenuItemComparator());
	}

	// ---------------------------------------------------------------
	// Pesanan
	// ---------------------------------------------------------------
	public int tambah_pesanan(int ID_staff, String namaStaff) {
		int IDpesanan_baru = ++hitung_pesanan_skrg;
		Pesanan pesanan_baru = new Pesanan(ID_staff, namaStaff);
		pesanan_baru.setID_pesanan(IDpesanan_baru);
		daftar_pesanan.add(pesanan_baru);
		return IDpesanan_baru;
	}

	public void tambah_itemPesanan(int ID_pesanan, MenuItem rItem, byte jumlah) {
		Pesanan rOrder = cari_IDpesanan(ID_pesanan);
		rOrder.tambahItem(rItem, jumlah);
	}

	public boolean hapus_itemPesanan(int ID_pesanan, int index) {
		Pesanan rOrder = cari_IDpesanan(ID_pesanan);
		if (rOrder == null)
			return false;
		return rOrder.hapusItem(index);
	}

	// Batalkan pesanan: data pesanan tidak dihapus dari DB
	// hanya mengisi cancel Flag = on
	public boolean batalkan_pesanan(int ID_pesanan) {
		Pesanan rOrder = cari_IDpesanan(ID_pesanan);
		if (rOrder == null)
			return false;
		rOrder.setStatus(Pesanan.pesanan_dibatalkan);
		return true;
	}

	// hapus pesanan: data pesanan dihapus dari DB
	public boolean hapus_pesanan(int ID_pesanan) {
		Pesanan rOrder = cari_IDpesanan(ID_pesanan);
		if (rOrder == null)
			return false;
		daftar_pesanan.remove(rOrder);
		hitung_pesanan_skrg--;
		return true;
	}

	public boolean bayar_pesanan(int ID_pesanan) {
		Pesanan rOrder = cari_IDpesanan(ID_pesanan);
		if (rOrder == null)
			return false;
		rOrder.setStatus(Pesanan.pesanan_dibayar);
		return true;
	}

	public void bayar_semuaPesanan() {
		Iterator<Pesanan> it = daftar_pesanan.iterator();
		Pesanan re = null;

		while (it.hasNext()) {
			re = it.next();
			if (re.getStatus() == 0) // dibayar maupun dibatalkan
			{
				re.setStatus(Pesanan.pesanan_dibayar);
			}
		}
	}

	public int getKondisi_pesanan(int ID_pesanan) {
		Pesanan re = cari_IDpesanan(ID_pesanan);
		if (re == null)
			return -1;
		return re.getStatus();
	}

	public double getBiaya_totalPesanan(int ID_pesanan) {
		Pesanan re = cari_IDpesanan(ID_pesanan);
		if (re == null)
			return -1;
		return re.getTotal();
	}

	public boolean cek_semuaPesanan_dibayar() {
		Iterator<Pesanan> it = daftar_pesanan.iterator();
		Pesanan re = null;

		while (it.hasNext()) {
			re = it.next();
			if (re.getStatus() == 0) // dibayar maupun dibatalkan
			{
				return false;
			}
		}
		return true;
	}

	public boolean cek_semuaStaff_pulang() {
		Iterator<Staff> it = daftar_staff.iterator();
		Staff re = null;

		while (it.hasNext()) {
			re = it.next();
			if (re.getStatus_Kerja() == Staff.statusKerja_Aktif) {
				return false;
			}
		}
		return true;
	}

	public void pulangkan_semuaStaff() {
		Iterator<Staff> it = daftar_staff.iterator();
		Staff re = null;

		while (it.hasNext()) {
			re = it.next();
			if (re.getStatus_Kerja() == Staff.statusKerja_Aktif) {
				re.absen();
			}
		}
	}

	/****************************************************************************
	 * Load Data
	 ***************************************************************************/
	public void load_DB() throws DB_Exception {
		load_DB_staff();
		load_DB_manager();
		Collections.sort(daftar_staff, new StaffComparator());
		load_DB_menu();
		load_DB_gaji();
	}

	private void load_DB_staff() throws DB_Exception {
		try {
			BufferedReader baca = new BufferedReader(new FileReader(DB_staff));
			String baris = baca.readLine();

			while (baris != null) {
				String[] record = baris.split(",");

				String id = record[0].trim();
				String password = record[1].trim();
				String namaDepan = record[2].trim();
				String namaBelakang = record[3].trim();

				// ambil data dari file kemudian dimasukkan ke dalam array List
				Pegawai rPegawai = new Pegawai(Integer.parseInt(id), namaBelakang, namaDepan, password);
				daftar_staff.add(rPegawai);
				baris = baca.readLine();
			}
			baca.close();
		} catch (IOException ioe) {
			String pesan = ioe.getMessage() + ioe.getStackTrace();
			throw new DB_Exception(pesan);
		}
	}

	private void load_DB_manager() throws DB_Exception {
		try {
			BufferedReader baca = new BufferedReader(new FileReader(DB_manager));
			String baris = baca.readLine();

			while (baris != null) {
				String[] record = baris.split(",");

				String id = record[0].trim();
				String password = record[1].trim();
				String namaDepan = record[2].trim();
				String namaBelakang = record[3].trim();

				// ambil data dari file kemudian dimasukkan ke dalam array List
				Manager rManager = new Manager(Integer.parseInt(id), namaBelakang, namaDepan, password);
				daftar_staff.add(rManager);
				baris = baca.readLine();
			}
			baca.close();
		} catch (IOException ioe) {
			String pesan = ioe.getMessage() + ioe.getStackTrace();
			throw new DB_Exception(pesan);
		}
	}

	private void load_DB_menu() throws DB_Exception {
		try {
			BufferedReader baca = new BufferedReader(new FileReader(DB_menu));
			String baris = baca.readLine();

			while (baris != null) {
				String[] record = baris.split(",");

				String id = record[0].trim();
				String nama = record[1].trim();
				String harga = record[2].trim();
				String tipe = record[3].trim();

				// ambil data dari file kemudian dimasukkan ke dalam array List
				MenuItem rMenuItem = new MenuItem(Integer.parseInt(id), nama, Double.parseDouble(harga),
						Byte.parseByte(tipe));
				daftar_menu.add(rMenuItem);
				baris = baca.readLine();
			}
			baca.close();
		} catch (IOException ioe) {
			String pesan = ioe.getMessage() + ioe.getStackTrace();
			throw new DB_Exception(pesan);
		}
	}

	private void load_DB_gaji() throws DB_Exception {
		try {
			BufferedReader baca = new BufferedReader(new FileReader(DB_info_gaji));
			String baris = baca.readLine();

			while (baris != null) {
				String[] record = baris.split(",");

				String id = record[0].trim();
				String nominal = record[1].trim();

				double dNominal = Double.parseDouble(nominal);
				int iId = Integer.parseInt(id);

				Staff rStaff = cari_IDstaff(iId);
				if (rStaff == null) {
					throw new DB_Exception("Load error\n ID Staff:" + iId + " tidak ada.");
				}
				rStaff.setNominal_Gaji(dNominal);

				baris = baca.readLine();
			}
			baca.close();
		} catch (IOException ioe) {
			String pesan = ioe.getMessage() + ioe.getStackTrace();
			throw new DB_Exception(pesan);
		} catch (Exception e) {
			String pesan = e.getMessage() + e.getStackTrace();
			throw new DB_Exception(pesan);
		}
	}

	/****************************************************************************
	 * Edit DB
	 ***************************************************************************/
	public void ubah_DB_staff(boolean cek_manager) throws DB_Exception {
		Writer tulis;
		String id;
		String baris;
		String namaFile;
		String tempNamaFile = "dataFiles/temp.txt";

		if (cek_manager)
			namaFile = DB_manager;
		else
			namaFile = DB_staff;

		Collections.sort(daftar_staff, new StaffComparator());
		File tempFile = new File(tempNamaFile);

		try {
			tulis = new BufferedWriter(new FileWriter(tempFile));
			Iterator it = daftar_staff.iterator();

			while (it.hasNext()) {
				Staff re = (Staff) it.next();

				// -------- skip menulis data ----------
				if (cek_manager) {
					// skip data pegawai
					if (re instanceof Pegawai)
						continue;
				} else {
					// skip data manager
					if (re instanceof Manager)
						continue;
				}

				tulis.write(re.getID() + "," + re.getPassword() + "," + re.getNamaDepan() + "," + re.getNamaBelakang()
						+ "\r\n");
			}
			tulis.flush();
			tulis.close();
		} catch (IOException e) {
			String pesan = e.getMessage() + e.getStackTrace();
			throw new DB_Exception(pesan);
		}

		// hapus file sebelumnya
		File hapus_file = new File(namaFile);
		hapus_file.delete();

		// rename file temp ke file baru
		File file_baru = new File(namaFile);
		tempFile.renameTo(file_baru);

		ubah_DB_gaji();
	}

	public void ubah_DB_gaji() throws DB_Exception {
		Writer tulis;
		String id;
		String baris;
		String namaFile;
		String tempNamaFile = "dataFiles/temp.txt";

		File tempFile = new File(tempNamaFile);

		try {
			tulis = new BufferedWriter(new FileWriter(tempFile));
			Iterator it = daftar_staff.iterator();

			while (it.hasNext()) {
				Staff re = (Staff) it.next();
				tulis.write(re.getID() + "," + re.getNominal_Gaji() + "\r\n");
			}
			tulis.flush();
			tulis.close();
		} catch (IOException e) {
			String pesan = e.getMessage() + e.getStackTrace();
			throw new DB_Exception(pesan);
		}

		// hapus file sebelumnya
		File hapus_file = new File(DB_info_gaji);
		hapus_file.delete();

		// rename file temp ke file baru
		File file_baru = new File(DB_info_gaji);
		tempFile.renameTo(file_baru);
	}

	public void updateMenuFile() throws DB_Exception {
		Writer tulis;
		String id;
		String baris;
		String tempNamaFile = "dataFiles/temp.txt";

		File tempFile = new File(tempNamaFile);

		try {
			tulis = new BufferedWriter(new FileWriter(tempFile));
			Iterator it = daftar_menu.iterator();

			while (it.hasNext()) {
				MenuItem re = (MenuItem) it.next();

				tulis.write(re.getID() + "," + re.getNama() + "," + re.getHarga() + "," + re.getTipe() + "\r\n");
			}
			tulis.flush();
			tulis.close();
		} catch (IOException e) {
			String pesan = e.getMessage() + e.getStackTrace();
			throw new DB_Exception(pesan);
		}

		// hapus file sebelumnya
		File hapus_file = new File(DB_menu);
		hapus_file.delete();

		// rename file temp ke file baru
		File file_baru = new File(DB_menu);
		tempFile.renameTo(file_baru);
	}

	public String cetak_nota_pesanan(String tgl_skrg) throws DB_Exception {
		Writer tulis = null;
		String baris;
		int status;
		double total_pesanan = 0;
		String cetak_namaFile;
		File file_baru;
		int hitung_pesanan = 0;
		int hitung_dibatalkan = 0;
		double total_dibatalkan = 0;

		String[] record = tgl_skrg.split("/");
		String hari_ini = record[0].trim() + "_" + record[1].trim() + "_" + record[2].trim();
		cetak_namaFile = DB_nota + hari_ini + ".txt";
		file_baru = new File(cetak_namaFile);

		try {
			tulis = new BufferedWriter(new FileWriter(file_baru));

			baris = "*********** Daftar Pesanan (" + hari_ini + ") ***********\r\n";
			tulis.write(baris);

			Iterator<Pesanan> it = daftar_pesanan.iterator();
			while (it.hasNext()) {
				Pesanan re = it.next();
				status = re.getStatus();
				String status_string = "";
				double total_tiapPesanan = re.getTotal();
				switch (status) {
				case Pesanan.pesanan_dibayar:
					status_string = "";
					total_pesanan += total_tiapPesanan;
					hitung_pesanan++;
					break;
				case Pesanan.pesanan_dibatalkan:
					status_string = "Dibatalkan";
					total_dibatalkan += total_tiapPesanan;
					hitung_dibatalkan++;
					break;
				default:
					status_string = "";
					total_pesanan += total_tiapPesanan;
					hitung_pesanan++;
					break;
				}
				String output = String.format("ID Pesanan:%4d  Nama Staff:%-30s  Total:Rp.%-5.2f %s\r\n",
						re.getID_pesanan(), re.getNamaStaff(), total_tiapPesanan, status_string);
				tulis.write(output);

			}
			tulis.write("-------------------------------------------------------\r\n");

			tulis.write("Total Penjualan:Rp." + total_pesanan + "(" + hitung_pesanan + ")" + "  Dibatalkan:Rp."
					+ total_dibatalkan + "(" + hitung_dibatalkan + ")\r\n");
			tulis.flush();
			tulis.close();
		} catch (IOException e) {
			String pesan = e.getMessage() + e.getStackTrace();
			file_baru.delete();
			throw new DB_Exception(pesan);
		}
		return cetak_namaFile;
	}

	public String cetak_pembayaran(String tgl_skrg) throws DB_Exception {
		Writer tulis = null;
		String baris;
		double total_pembayaran = 0;
		String cetak_namaFile;
		File file_baru;
		int no_staff = 0;

		String[] record = tgl_skrg.split("/");
		String hari_ini = record[0].trim() + "_" + record[1].trim() + "_" + record[2].trim();
		cetak_namaFile = DB_gaji + hari_ini + ".txt";
		file_baru = new File(cetak_namaFile);

		try {
			tulis = new BufferedWriter(new FileWriter(file_baru));

			baris = "*********** Daftar Pembayaran Gaji (" + hari_ini + ") ***********\r\n";
			tulis.write(baris);

			Iterator<Staff> it = daftar_staff.iterator();
			while (it.hasNext()) {
				Staff re = it.next();

				if (re.getStatus_Kerja() == Staff.statusKerja_Selesai) {
					double bayar = re.hitung_Gaji();
					String output = String.format("ID Pegawai:%4d  Nama Staff:%-30s  Lama Kerja:%-5.2f Gaji:%-5.2f\r\n",
							re.getID(), re.getNamaLengkap(), re.hitung_WK(), bayar);
					tulis.write(output);
					no_staff++;
					total_pembayaran += bayar;
				}
			}
			tulis.write("-------------------------------------------------------\r\n");

			tulis.write("Total Pembayaran:Rp." + total_pembayaran + "(" + no_staff + ")\r\n");
			tulis.flush();
			tulis.close();
		} catch (IOException e) {
			String message = e.getMessage() + e.getStackTrace();
			file_baru.delete();
			throw new DB_Exception(message);
		}
		return cetak_namaFile;
	}

	/****************************************************************************
	 * Comparator
	 ***************************************************************************/
	private class StaffComparator implements Comparator<Staff> {

		@Override
		public int compare(Staff s1, Staff s2) {
			return s1.getID() < s2.getID() ? -1 : 1;
		}
	}

	private class MenuItemComparator implements Comparator<MenuItem> {

		@Override
		public int compare(MenuItem m1, MenuItem m2) {
			return m1.getID() < m2.getID() ? -1 : 1;
		}
	}
}
