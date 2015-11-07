package Utility;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class formatTanggal {
	SimpleDateFormat sdf;
	Calendar kal;
	
	public Date setTahun(Date tahun){
		kal = Calendar.getInstance();
		kal.setTime(tahun);
		kal.add(Calendar.YEAR, 2015);
	    return tahun = kal.getTime();
	}
	
	public Date tambahJam(Date jam){
		kal = Calendar.getInstance();
		kal.setTime(jam);
		kal.add(Calendar.HOUR_OF_DAY, 1);
	    return jam = kal.getTime();
		
	}
	
	public String getWaktu(Date waktu){
		 sdf = new SimpleDateFormat("HH:mm");
		 return sdf.format(waktu);
	}
	
	public Date formatStringWaktu_keTanggal(String waktu) throws ParseException{
		sdf = new SimpleDateFormat("HH:mm");
		Date tanggal = sdf.parse(waktu);
		return tanggal;
	}
		
	public String getTanggal(Date tanggal){
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		String s = sdf.format(tanggal);
		return s;
	}
	
	public String formatStringTanggal(String tanggal) throws ParseException{
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		 Date tanggal1 = sdf.parse(tanggal);
		return sdf.format(tanggal1);
	}
	
	public Date formatString_keTanggal(String tanggal) throws ParseException{
		sdf = new SimpleDateFormat("dd/MM/yyyy");
		return sdf.parse(tanggal);
	}
}
