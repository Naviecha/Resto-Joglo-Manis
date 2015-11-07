package Sistem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
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


public class Tempat {
	
	public int ukuran, idMeja, ditempati;
	ArrayList<Reservasi> ListPemesan;
	Scanner sc;
	formatTanggal df = new formatTanggal();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	public Pesanan order = new Pesanan();
	
	public Pesanan getOrder() {
		return order;
	}

	public void setOrder(Pesanan order) {
		this.order = order;
	}

	public Tempat(){
		
	}

	public Tempat(int ukuran, int idMeja, int ditempati){
		this.ukuran = ukuran;
		this.idMeja = idMeja;
		this.ditempati = ditempati;
	}
	
	public int getUkuran(){
		return ukuran;
	}
	
	public int getIdMeja(){
		return idMeja;
	}
	
	public int getDitempati(){
		return ditempati;
	}
	
	public void setUkuran(int ukuran) {
		this.ukuran = ukuran;
	}

	public void setIdMeja(int idMeja) {
		this.idMeja = idMeja;
	}

	public void setDitempati(int ditempati) {
		this.ditempati = ditempati;
	}
	
	public ArrayList<Reservasi> getReservasiMeja(){
		try {
			ListPemesan = new ArrayList<Reservasi>();
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservasi.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservasi r = new Reservasi();
				r.setTanggal(temp[0]);
				r.setWaktuAwal(temp[1]);
				r.setNamaPelanggan(temp[2]);
				r.setJum_Kursi(temp[3]);
				r.setContact(temp[4]);
				r.setIdMeja(temp[5]);
				ditempati = Integer.parseInt(temp[6]);
				ListPemesan.add(r);
			}
			s.close();
			System.out.println("List Pemesan : " + ListPemesan.size());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ListPemesan;
	}
	
	public void hapusReservasiMeja() throws ParseException{
		int pilih;
		sc = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		Calendar cal = Calendar.getInstance();
		Date waktu_Skrg = cal.getTime();
		
		do{
			int hitungIndex=1;
			ListPemesan = getReservasiMeja(); // baca data dari txt dan simpan kedalam objek reservasi
			System.out.println("Tanggal Reservasi " + "    " + "Waktu Reservasi " + "     " + "No. Meja" + "    " + 
					"Nama Pelanggan" + "    " + "Jumlah Kursi" + "    " + "Contact Pelanggan");
			for(Iterator<Reservasi> r = ListPemesan.iterator(); r.hasNext();){ // tampilkan objek reservasi
				Reservasi reservasi = r.next();
				reservasi.setWaktuAkhir();
				System.out.println( hitungIndex +" " +reservasi.getTanggal()+ "          " + sdf.format(reservasi.getWaktuAwal())+ "-" 
						+ sdf.format(reservasi.getWaktuAkhir()) + "          " + reservasi.getIdMeja()	+ "             " + reservasi.getNamaPelanggan()
						+"            " + reservasi.getJum_Kursi() + "            " + reservasi.getContact());
				hitungIndex++;
			}
			System.out.println("Pilih index reservasi yang akan dihapus \n tekan 0 untuk exit");
			pilih = sc.nextInt();
			// tulis ke dalam file
			if(pilih>0 && pilih < ListPemesan.size() || pilih == ListPemesan.size()){ //cek input user di dalam range
				ListPemesan.remove(pilih-1); // hapus reservasi dari arraylist
				//overwrite file txt dengan objek yang ada dalam list
				try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reservasi.txt", false)))) {
					out.println("tanggal,waktu,nama,jum_kursi,contact,idMeja,ditempati");
					for(Iterator<Reservasi> r = ListPemesan.iterator(); r.hasNext();){
						Reservasi reservasi = r.next();
					out.print(reservasi.getTanggal()+","+sdf.format(reservasi.getWaktuAwal())+","+reservasi.getNamaPelanggan()+","+reservasi.getJum_Kursi()+","+
							reservasi.getContact()+","+ reservasi.getIdMeja());
						if(waktu_Skrg.after(reservasi.getWaktuAwal())&&waktu_Skrg.before(reservasi.getWaktuAkhir())){
							out.print(",1\n");
						}
						else
							out.print(",0\n");
					}
					System.out.println("Reservasi berhasil dihapus");
				}catch (IOException e) {
				    //exception handling (tugas lain)
				}
			}
			else
				if(pilih==0)
					break;
			else
				System.out.println("Invalid input");
		}while(pilih!=0);
		
			
	}

	public ArrayList<Reservasi> getReservasiMeja(String tanggal){
		ListPemesan = new ArrayList<Reservasi>();
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservasi.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservasi r = new Reservasi();
				r.setTanggal(temp[0]);
				r.setWaktuAwal(temp[1]);
				r.setNamaPelanggan(temp[2]);
				r.setJum_Kursi(temp[3]);
				r.setContact(temp[4]);
				r.setIdMeja(temp[5]);
				ditempati = Integer.parseInt(temp[6]);
				if(r.getTanggal().equals(tanggal))
					ListPemesan.add(r);
			}
			s.close();
			System.out.println("List Pemesan Meja : " + ListPemesan.size());
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ListPemesan;
	}
	
	public ArrayList<Reservasi> lamaMejaDipesan(){
		ListPemesan = new ArrayList<Reservasi>();
		try {
			Scanner s = new Scanner(new BufferedReader(new FileReader("reservasi.txt")));
	
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Reservasi r = new Reservasi();
				//Date startTime = sdf.parse(temp[0]+" "+temp[1]);
				if(temp[6].equals("1")){
					r.setTanggal(temp[0]);
					r.setWaktuAwal(temp[1]);
					r.setNamaPelanggan(temp[2]);
					r.setJum_Kursi(temp[3]);
					r.setContact(temp[4]);
					r.setIdMeja(temp[5]);
					ditempati = Integer.parseInt(temp[6]);
					ListPemesan.add(r);
				}
				
			}
			s.close(); 
		}catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ListPemesan;
	}
	
	public boolean pesanMeja(String tanggal,Date waktuAwal, String jml_kursi, String namaPelanggan, String contact, String idMeja)throws ParseException{
		
		boolean dipesan = false;
		getReservasiMeja(tanggal);
		String pattern = "HH:mm";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar cal = Calendar.getInstance();
        //jika size 0, itu berarti sudah ada reservasi yang dibuat pada tanggal itu di getReservasiMeja, buatlah sebuah reservasi baru yang berbeda
        if(ListPemesan.size()>0){
	        for(Iterator<Reservasi> i = ListPemesan.iterator(); i.hasNext(); ) {
	        	Reservasi r = i.next();
	        	if(r.getJum_Kursi().equals(jml_kursi)){
	        			r.setWaktuAkhir();
	        			cal.setTime(waktuAwal);
	        			cal.add(Calendar.HOUR_OF_DAY, 1);
	        			Date waktuAkhir = cal.getTime();
	        			//rentang kondisi : waktuAwal dan waktuAkhir dalam reservasi tidak boleh sama dengan rentang waktuAwal
	        			//dan waktuAkhir di objek reservasi lainnya
	        			if(!(waktuAwal.after(r.getWaktuAwal()) && waktuAwal.before(r.getWaktuAkhir()))
	        					|| !(waktuAkhir.after(r.getWaktuAwal()) && waktuAkhir.before(r.getWaktuAkhir()))){
	        				//cek jika kedua reservasi punya waktuAwal dan waktuAkhir yang sama
	        				if(!((waktuAwal.getTime() == r.getWaktuAwal().getTime()) || (waktuAkhir.getTime() == r.getWaktuAkhir().getTime()))){
	        					//cek jika durasi reservasi itu selama 60 untuk menghindari kasus dimana waktuAwal itu lebih cepat dan waktu Akhir lebih lama
	        					//dimana hal tsb bisa melebihi waktu reservasi yg lain
	        					//cek ini juga berfungsi untuk menetapkan waktu reservasi hanya selama 60 menit
	        					if((waktuAwal.getTime() - waktuAkhir.getTime())/(60 * 1000) % 60 <= 60*60*1000){
		        					try
		        					{
		        						//tambah counter 1 di bagian ditempati = 0
		        					    String filename= "reservasi.txt";
		        					    FileWriter fw = new FileWriter(filename,true); //kondisi true akan menulis ke data (file) baru
		        					    fw.write("\r"+tanggal + "," + sdf.format(waktuAwal) + "," + namaPelanggan + "," + jml_kursi + "," + contact + "," + idMeja +","
		        					    		+ "0");//menambah string ke dalam file
		        					    fw.close();
		        					    dipesan = true;
		        					}
		        					catch(IOException ioe)
		        					{
		        					    System.err.println("IOException: " + ioe.getMessage());
		        					}
		        					
		        				}
	        				}
	        				
	        			}
	        	}
        			
	        }
        }else{
        	try
			{
			    String namaFile = "reservasi.txt";
			    FileWriter fw = new FileWriter(namaFile,true); //kondisi true akan menulis ke data (file) baru
			    fw.write(""+ tanggal + "," + sdf.format(waktuAwal) + "," + namaPelanggan + "," + jml_kursi + "," + contact + "," + idMeja+","
			    		+ "0");//menambah string ke dalam file
			    fw.close();
			    dipesan = true;
			}
			catch(IOException ioe)
			{
			    System.err.println("IOException: " + ioe.getMessage());
			}
        }
        return dipesan;
	}
	
	
public Reservasi pesanMejaTersedia(String tanggal,Date waktuAwal, String jml_kursi, String idMeja)throws ParseException, IOException{
	sc = new Scanner(System.in);
	ListPemesan = new ArrayList<Reservasi>();
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
	Date waktuAkhir = df.tambahJam(waktuAwal);
	ListPemesan = getReservasiMeja(tanggal);
		/*
		 * method ini mengecek reservasi berdasarkan id_meja iterasi pertama
		 * mengecek id_meja1, dan begitu pada iterasi kedua id_meja1 dihapus
		 * jika id_meja dihapus maka method cek akan berjalan
		 */
	for(Iterator<Reservasi> tempReservasi = ListPemesan.iterator(); tempReservasi.hasNext();){
		Reservasi r1 = tempReservasi.next();
		if(Integer.parseInt(r1.getIdMeja())<Integer.parseInt(idMeja)){
			tempReservasi.remove();
		}
	}
	if(ListPemesan.size()>0){
		
		for(Reservasi r: ListPemesan){
			if(ukuran == Integer.parseInt(r.getJum_Kursi())){
				Date rWaktuAwal  = sdf.parse(r.getTanggal()+ " " + df.getWaktu(r.getWaktuAwal()));
				Date rTanggalAkhir = df.tambahJam(rWaktuAwal );
				r.setWaktuAkhir();
				//rentang kondisi : waktuAwal dan waktuAkhir dalam reservasi tidak boleh sama dengan rentang waktuAwal
    			//dan waktuAkhir di objek reservasi lainnya
				//jika tidak melebihi dan id_meja juga tidak sama, maka akan melakukan reservasi
				if(!((waktuAwal.after(rWaktuAwal ) && waktuAwal.before(rTanggalAkhir) && idMeja.toString().equals(r.getIdMeja()))
						|| waktuAkhir.after(rWaktuAwal ) && waktuAkhir.before(rTanggalAkhir) && idMeja.toString().equals(r.getIdMeja()) )){
					//cek jika kedua reservasi punya waktuAwal dan waktuAkhir yang sama	
					if(!((waktuAwal.equals(rWaktuAwal )) || waktuAkhir.equals(rTanggalAkhir))){
						//cek jika durasi reservasi itu selama 60 untuk menghindari kasus dimana waktuAwal itu lebih cepat dan waktu Akhir lebih lama
        				//dimana hal tsb bisa melebihi waktu reservasi yg lain.
						//cek ini juga berfungsi untuk menetapkan waktu reservasi hanya selama 60 menit
	    				if((waktuAwal.getTime() - waktuAkhir.getTime())/(60 * 1000) % 60 <= 60*60*1000){
	    					System.out.println("Nama Pelanggan : ");
	    					String namaPelanggan = sc.nextLine();
	    					System.out.println("Contact Pelnggan : ");
	    					String contact = sc.nextLine();
							String namaFile = "reservasi.txt";
							FileWriter fw = new FileWriter(namaFile,true); //kondisi true akan menulis ke data (file) baru
							fw.write("\r"+tanggal + "," + df.getWaktu(waktuAwal) + "," + namaPelanggan + "," + jml_kursi + "," + contact + "," + idMeja+","
    					    		+ "1");//menambah string ke dalam file
							fw.close();
							this.ditempati = 1;
							return r;
	    				}else{
	    					System.out.println("Gagal membuat reservasi");
	    					return null;
	    				}
					}else{
						System.out.println("waktu ini sudah melebihi tenggat reservasi ");
						return null;
					}
				}else{ 
					System.out.println("waktu ini sudah melebihi tenggat reservasi lain ");
					return null;
				}
			}
		}
	}else{
		System.out.print("\nMasukkan Nama Pelanggan : ");
		String namaPelanggan = sc.nextLine();
		System.out.print("Masukkan Contact Pelanggan : ");
		String contact = sc.nextLine();
		String namaFile= "reservasi.txt";
		FileWriter fw = new FileWriter(namaFile,true); //kondisi true akan menulis ke data (file) baru
		fw.write("\r"+tanggal + "," + df.getWaktu(waktuAwal) + "," + namaPelanggan + "," + jml_kursi + "," + contact + "," + idMeja+","
	    		+ "1");//menambah string ke dalam file
		fw.close();
		this.ditempati = 1;
		return new Reservasi(tanggal, df.getWaktu(waktuAwal),jml_kursi,namaPelanggan,contact,idMeja);
	}
 	return null;

	}


public void hapusReservasiDibayar(int idMeja) throws ParseException{
	//idMeja, ditempati bit=1
	//tulis ke file
	Calendar cal = Calendar.getInstance();
	Date waktu_Skrg = cal.getTime();
	ListPemesan = getReservasiMeja();
	for(Iterator <Reservasi> r = ListPemesan.iterator(); r.hasNext(); ){
		Reservasi reservasi = r.next(); 
		if(Integer.parseInt(reservasi.getIdMeja())==idMeja){
			r.remove();
		}
	}
	//idMeja, ditempati bit=1
	try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("reservasi.txt", false)))) {
	out.println("tanggal,waktu,nama,jum_kursi,contact,idMeja,ditempati");
	for(Iterator<Reservasi> r = ListPemesan.iterator(); r.hasNext();){
		Reservasi reservasi = r.next();
		reservasi.setWaktuAkhir();
	out.print(reservasi.getTanggal()+","+df.getWaktu(reservasi.getWaktuAwal())+","+reservasi.getNamaPelanggan()+","+reservasi.getJum_Kursi()+","+
			reservasi.getContact()+","+ reservasi.getIdMeja());
		if(reservasi.getWaktuAwal().before(waktu_Skrg)&&waktu_Skrg.before(reservasi.getWaktuAkhir())){
			out.print(",1\n");
		}
		else
			out.print(",0\n");
	}
	System.out.println("Reservasi berhasil dihapus ");
	}catch (IOException e) {
    //exception handling buat jaga2 :D
		}
	
	}

}
