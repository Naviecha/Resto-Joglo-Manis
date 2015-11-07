package Controller;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import Sistem.Reservasi;
import Sistem.Tempat;
import Utility.formatTanggal;


public class AturMeja {
	//untuk melakukan pengecekan terhadap kapasitas resto sekarang
	//juga untuk control sistem pemesanan agar mendapatkan ID Meja untuk memesan makanan
	Tempat meja[];
	Reservasi r = new Reservasi();
	public ArrayList<Reservasi> listMejaDitempati = new ArrayList<Reservasi>();
	formatTanggal df = new formatTanggal();
	public Tempat[] getMeja() {
		return meja;
	}

	public void setMeja(Tempat[] meja) {
		this.meja = meja;
	}

	public ArrayList<Reservasi> getListMejaDitempati() {
		return listMejaDitempati;
	}

	public void setListMejaDitempati(ArrayList<Reservasi> listMejaDitempati) {
		this.listMejaDitempati = listMejaDitempati;
	}

	
	public AturMeja(){
		//contstructor untuk jaga2 :D
	}
	
	public void hapusReservasiTerlambat(){
		
	}
	
	public void getMejaDitempati(){
		r = new Reservasi();
		listMejaDitempati = r.getReservasi_Skrg();		
	}
	
	
	public void tampilMejaKosong(){
			
			
		/*
		r = new Reservation();
		Calendar cal = Calendar.getInstance();
		Date d1 = cal.getTime();
		r.getCurrentHourReservation(date);
		
		/*
		r = new Reservation();
		 String date;
		 Calendar cal = Calendar.getInstance();
		 Date d1 = cal.getTime();
		 date = df.getDate(d1);
		tableOccupiedList = r.getTableReservation(date);
		
			for(Table t: r.getTable()){
				if(t.getIsReserved()==1){
					table[(t.getTableNum()-1)].setIsReserved(1);
				}
			}
		
		for(int i=0; i<10; i++){
			if(table[i].getIsReserved()==1){
				System.out.println("Table "+table[i].getTableNum()+1+ ": Occupied");
			}else
				System.out.println("Table "+(table[i].getTableNum()+1)+ ": Free");
			
		}
		*/
		
		/*
		if(tableOccupiedList!=null && tableOccupiedList.size()>0){
			
			for(Reservation r: tableOccupiedList){
				int tableNum = Integer.parseInt(r.getTableNum());
				for(Table t: table){
					if((t.getTableNum()+1)==tableNum){
						t.occupied = 1;
					}
				}
			}
			
			
			
		}
		for(Table t: table){
			if(t.getIsReserved()!=1){
				System.out.println("Table No." + (t.getTableNum()+1));
			}
		}
		*/
	}
	

	
	public void cek_KetersediaanMeja(String jum_kursi) throws ParseException, IOException{
		// get sistem tanggal dan waktu
		 r = new Reservasi();
		 Date d1, waktuAwal;
		 String tanggal;
		 Calendar cal = Calendar.getInstance();
		 d1 = cal.getTime();
		 tanggal = df.getTanggal(d1);
		 waktuAwal = cal.getTime();
		// startTime = df.getTime(d1);
		 
		 //alokasi meja berdasarkan tanggal, waktuMulai dan jum_kursi
		 r.alokasiMeja(tanggal, waktuAwal, jum_kursi);
		 if(r.getNamaPelanggan()!=null){
			 listMejaDitempati.add(r);
			System.out.println("ID Meja : " + r.getIdMeja());
		}else{
			System.out.println("Semua meja dengan kursi "+ jum_kursi + " sudah ditempati ");
		}
	}
	
	public boolean mejaKosong(String idMeja){
		boolean hasil = false;
		for(int i=0; i<10; i++){
			if(meja[i].getIdMeja() == Integer.parseInt(idMeja)){
				if(meja[i].getDitempati()==1){
					meja[i].setDitempati(0);
					hasil = true;
				}
			}
		}
		return hasil;
	}
}
