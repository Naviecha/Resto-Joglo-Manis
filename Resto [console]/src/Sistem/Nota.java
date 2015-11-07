package Sistem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Nota {
	
	public void bacaNota(){
		try {
			BufferedReader baca = new BufferedReader(new FileReader("nota.txt"));
			
			ArrayList<String> list = new ArrayList<String>();
			String data;
			while ((data = baca.readLine()) != null) {
				list.add(data);
				System.out.println(data);
			}
			baca.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
