package proxy_test;

import java.util.Random;

import proxy_virtuel.IWebImage;
import proxy_virtuel.WebImage;

public class TestImage {
	
	public static void main(String ... args){
	
		IWebImage [] tabImage = new WebImage[30];
		
		for(int i = 0; i < 30; i++){
			tabImage[i] = new WebImage("http://the.web.site/image-" +(i+1) +".jpg");
		}
		
		Random rand = new Random();
		for(int i = 0; i < 5; i++){
			int temp = rand.nextInt(30);
			System.out.println("Nombre choisi : " +temp);
			tabImage[temp].afficher(320, 200);
		}
		
	}
}
