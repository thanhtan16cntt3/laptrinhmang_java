package Baitap;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

public class demo {

	public static void main(String[] args) throws UnknownHostException, MalformedURLException {
		Scanner sc = new Scanner(System.in);
		
		String str = sc.nextLine();
		URL url = new URL(str);
		InetAddress inet = InetAddress.getByName(str);
		if(str.equals(url.toString()) || str == inet.getHostAddress().toString()) {
			System.out.println("IP: " + inet.getHostAddress());
		}

	}

}
