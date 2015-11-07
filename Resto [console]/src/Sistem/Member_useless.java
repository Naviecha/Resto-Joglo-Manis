package Sistem;


import java.io.File;
import java.io.IOException;


public class Member_useless {
	
	private String nama;
	private String email;
	private String id_member;
	private long telpon;
	private static final File membersFile = new File("members.txt");
	
	
	
	private Member_useless(String nama, String email, String id_member,long telpon) {
		super();
		this.nama = nama;
		this.email = email;
		this.id_member = id_member;
		this.telpon = telpon;
	}

	
	private static void tambahMembership() throws IOException{
		//TODO: method untuk menulis data member ke file txt
	}
	
	private static void bacaMembership(){
		//TODO: method untuk membaca data dari file dan ditulis ke member
		
	}
	
	public static void inisialisasi() throws IOException{
		if(!membersFile.isFile()){
			membersFile.createNewFile();
		}
		bacaMembership();
	}
	
	
	/*public static boolean addMember(String id_member, String nama, String email, long telpon) throws IOException{
		if(membersFile.get(id_member) != null){
			return false;
		}
		
		membersFile.put(id_member, new Member(name, email, id_member, telpon));
		tambahMembership();
		return true;
	}*/
	
	
	// tambah hapus Member



	public String getNama() {
		return nama;
	}


	public String getEmail() {
		return email;
	}


	public String getMemberId() {
		return id_member;
	}

	public long getTelpon() {
		return telpon;
	}
	
	

}