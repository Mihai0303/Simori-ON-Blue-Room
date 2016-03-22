/**
 * 
 */
package Simori;
import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * @author Airidas Juskaitis, Ollie McLean, Nicholas Higgins, Mihai Bratosin,
 * Alonso-Lopez Mendoza
 *
 */
public class ShopBoy {
	
	private static String[] songs={"smoke"};
	private static int counter = 0;
	
	public static void playShopBoy(){
		 ActionListener actionListener = new ActionListener() {
	            public void actionPerformed(ActionEvent actionEvent) {
	                SaveLoad.load(songs[counter]);
	                counter++;
	                if(counter==songs.length){counter=0;}
	            }
	        };
	        Timer timer = new Timer( 30000, actionListener );
	        timer.start();
	}
}
