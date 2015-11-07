package Sistem;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Scanner;


public class Staff {
	
	
	String namaStaff, gender, jabatan, idStaff;
	Menu m;
	Reservasi r;
	
	public Staff(String namaStaff,String idStaff,String gender,String jabatan){
		
		this.namaStaff = namaStaff;
		this.idStaff = idStaff;
		this.gender = gender;
		this.jabatan = jabatan;
	}
	
	public Staff(){
		//constructor untuk jaga2 :D
	}
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getJabatan() {
		return jabatan;
	}

	public void setJabatan(String jabatan) {
		this.jabatan = jabatan;
	}

	public void setNamaStaff(String namaStaff) {
		this.namaStaff = namaStaff;
	}

	public void setIdStaff(String idStaff) {
		this.idStaff = idStaff;
	}

	public String getIdStaff(){
		return idStaff;
	}
	
	public String getNamaStaff(){
		return namaStaff;
	}
	
	public boolean cekAdmin() {
		if (jabatan.equals("Manager")) {
			return true;
		}
		else {
			return false;
		}
	}
}
