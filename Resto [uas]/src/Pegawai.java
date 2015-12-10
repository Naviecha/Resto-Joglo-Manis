
public class Pegawai extends Staff
{
    private static final double nominal_Minimum = 13.5; 
    
    public Pegawai()
    {
        super();
    }   
    public Pegawai( int ID_baru, String namaBelakang_baru, String namaDepan_baru, String password_baru)
    {
        super(ID_baru, namaBelakang_baru, namaDepan_baru, password_baru);
        nominal_gaji = nominal_Minimum;
    }
    
    @Override
	public void setNominal_Gaji(double gaji_baru)
    {
        if( gaji_baru < nominal_Minimum)
        	nominal_gaji = nominal_Minimum;
        else
        	nominal_gaji = gaji_baru;
    }
    
    @Override
	public double hitung_Gaji()
    {
        return nominal_gaji * hitung_WK();
    }
}
