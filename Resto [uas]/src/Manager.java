
public class Manager extends Staff {
	private static final double nominal_Minimum = 100.0;

	public Manager() {
		super();
	}

	public Manager(int ID_baru, String namaBelakang_baru, String namaDepan_baru, String password_baru) {
		super(ID_baru, namaBelakang_baru, namaDepan_baru, password_baru);
		nominal_gaji = nominal_Minimum;
	}

	@Override
	public void setNominal_Gaji(double gaji_baru) {
		if (nominal_gaji < nominal_Minimum)
			gaji_baru = nominal_Minimum;
		nominal_gaji = gaji_baru;
	}

	@Override
	public double hitung_Gaji() {
		if (getStatus_Kerja() != statusKerja_Selesai)
			return 0;

		return this.nominal_gaji;
	}
}
