package Simori;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveLoad {
	
	public static void save() {		
		try{
			FileOutputStream fos = new FileOutputStream(GUI.textField.getText() + ".song");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(GUI.allContents());
			System.out.println("saved");
		}
		catch(Exception e){
			
		}
		
	}

	public static void load() {
		Boolean[][][] newArray = new Boolean[16][16][16];
		
		try{
			FileInputStream fis = new FileInputStream(GUI.textField.getText() + ".song");
			ObjectInputStream iis = new ObjectInputStream(fis);
			newArray = (Boolean[][][]) iis.readObject();
			System.out.println("loaded");
		} catch (Exception e){
			
		}
		
		GUI.setContents(newArray);
		
	}
	
	
	public static void setFileName(int x, int y){
		GUI.clearBoard();
		GUI.textField.setText(addCharToTempFileName(x, y));
		System.out.println("x: " + x + " y: " + y);
		
		for(int i = 0; i < GUI.display[i].length; i++){
			for(int j = 0; j < GUI.display[i].length; j++){
				if(j==x || i == y){
					GUI.display[i][j].setSelected(true);
				}
			}
		}
	}
	
	
	public static String addCharToTempFileName(int x, int y){
		String tempFileName = GUI.textField.getText();
		
		if (tempFileName != null && tempFileName.length() > 0 && y == 0) {
		      tempFileName = tempFileName.substring(0, tempFileName.length()-1);
		}
		
		if(y==5){
			if(x==3){  tempFileName += '1';}
			if(x==4){  tempFileName += '2';}
			if(x==5){  tempFileName += '3';}
			if(x==6){  tempFileName += '4';}
			if(x==7){  tempFileName += '5';}
			if(x==8){  tempFileName += '6';}
			if(x==9){  tempFileName += '7';}
			if(x==10){  tempFileName += '8';}
			if(x==11){  tempFileName += '9';}
			if(x==12){  tempFileName += '0';}
		}
		if(y==6){
			if(x==3){  tempFileName += 'q';}
			if(x==4){  tempFileName += 'w';}
			if(x==5){  tempFileName += 'e';}
			if(x==6){  tempFileName += 'r';}
			if(x==7){  tempFileName += 't';}
			if(x==8){  tempFileName += 'y';}
			if(x==9){  tempFileName += 'u';}
			if(x==10){  tempFileName += 'i';}
			if(x==11){  tempFileName += 'o';}
			if(x==12){  tempFileName += 'p';}
		}
		if(y==7){
			if(x==3){  tempFileName += 'a';}
			if(x==4){  tempFileName += 's';}
			if(x==5){  tempFileName += 'd';}
			if(x==6){  tempFileName += 'f';}
			if(x==7){  tempFileName += 'g';}
			if(x==8){  tempFileName += 'h';}
			if(x==9){  tempFileName += 'j';}
			if(x==10){  tempFileName += 'k';}
			if(x==11){  tempFileName += 'l';}
		}
		if(y==8){
			if(x==3){  tempFileName += 'z';}
			if(x==4){  tempFileName += 'x';}
			if(x==5){  tempFileName += "c";}
			if(x==6){  tempFileName += 'v';}
			if(x==7){  tempFileName += 'b';}
			if(x==8){  tempFileName += 'n';}
			if(x==9){  tempFileName += 'm';}
		}
		
		return tempFileName;
		
	}

}
