package Sistem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;


public class Menu {
	
	ArrayList<Item> daftarItem =  new ArrayList<Item>();
	ArrayList<Item> daftarMainCourse =  new ArrayList<Item>();
	ArrayList<Item> daftarDessert =  new ArrayList<Item>();
	ArrayList<Item> daftarMinuman =  new ArrayList<Item>();
	ArrayList<Promosi> daftarPromo =  new ArrayList<Promosi>();
	Scanner s;
	Item tempItem;
	
	public Menu(){
		getItem();
		tataDaftarItem();
		getPromosi();
	}
	
	private void tataDaftarItem(){
		for(Iterator<Item> i = daftarItem.iterator(); i.hasNext(); ) {
			Item item = i.next();
			
			if(item.getTipe().equals("main course")){
				daftarMainCourse.add(item);
			}
			else
				if(item.getTipe().equals("drink"))
					daftarMinuman.add(item);
				else
					if(item.getTipe().equals("dessert"))
						daftarDessert.add(item);					
			
		}
	}
	
	private void getItem(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("item.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Item item = new Item(temp[0],temp[1],temp[2], temp[3], temp[4], temp[5]);
				daftarItem.add(item);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	
	//harus run getItem() dulu sebelum getPromosi() karena itu berdasarkan urutan item di daftar Item
	private void getPromosi(){
		try {
			s = new Scanner(new BufferedReader(new FileReader("promosi.txt")));
			s.nextLine();
			while (s.hasNext()) {
				String temp[] = s.nextLine().split(",");
				Promosi p = new Promosi();
				ArrayList<Item> tempDaftarPromo = new ArrayList<Item>();
				p.setPromoID(temp[0]);
				p.setNama(temp[1]);
				//loop sebanyak jumlah item yang ada pada 1 daftar promosi
				for(int i=2; i<temp.length; i++){
					//bandingkan itemID dengan daftarItem yang berisi semua itemID, untuk memastikan objek item yang benar
					for(Iterator<Item> it = daftarItem.iterator(); it.hasNext();){
						Item itemInItemList = it.next();
						if(temp[i].equals(itemInItemList.getItemID())){
							tempDaftarPromo.add(itemInItemList);
						}
					}
				}
				//System.out.println("tempPromoList.size(): "+tempPromoList.size());
				p.setDaftarItem(tempDaftarPromo);
				daftarPromo.add(p);
			}
			s.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public Object tampilMenu(){
			s = new Scanner(System.in);
			System.out.println("================================================");
			System.out.println("               		Menu                 ");
			System.out.println("================================================");
			
			System.out.println("=========== Hidangan Utama ===========");
			for(Iterator<Item> i = daftarMainCourse.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getNama() + "   " + item.getKet() + "   "  + "Rp. "+item.getHarga()+ " ribu");
			}
			System.out.println();
			System.out.println("=========== Minuman ===========");
			for(Iterator<Item> i = daftarMinuman.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getNama() + "   " + item.getKet()  + "   " + "Rp. "+item.getHarga()+ " ribu");
			}
			System.out.println();
			System.out.println("=========== Hidangan Penutup ============");
			for(Iterator<Item> i = daftarDessert.iterator(); i.hasNext(); ) {
				Item item = i.next();
				System.out.println(item.getItemID() + "   "+ item.getNama() + "   " + item.getKet() + "   " + "Rp. "+ item.getHarga()+ " ribu");
			}
			System.out.println();
			System.out.println("=========== Paket Combo Hemat ============");
			for(Iterator<Promosi> p = daftarPromo.iterator(); p.hasNext(); ) {
				Promosi promo = p.next();
				System.out.print(promo.getPromoID() + "   ");
				for(Iterator<Item> i = promo.getDaftarItem().iterator(); i.hasNext(); ){
					Item item = i.next();
					System.out.print(item.getNama()+ "   ");
				}
				System.out.println("   " + "Rp. "+ promo.hargaPromo()+ " ribu");
				
			}
			System.out.println();
			System.out.println("Masukan kode item untuk memilih :");
			return pilihItem(s.next());
	}
	
	public Object pilihItem(String objectID){
		
		Object object =null;
		for(Item i : daftarItem){
	        if(i.getItemID() != null && i.getItemID().equals(objectID))
	           object = i;
	    }
		
		for(Promosi p : daftarPromo){
	        if(p.getPromoID() != null && p.getPromoID().equals(objectID))
	           object = p;
	    }	
		return object;
	}
}
