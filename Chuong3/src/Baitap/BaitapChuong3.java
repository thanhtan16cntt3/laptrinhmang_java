package Baitap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.UnknownHostException;
import java.sql.Date;
import java.util.Scanner;

public class BaitapChuong3 {

	public static void Bai_1() throws MalformedURLException, UnknownHostException {
		System.out.print("Nhap dia chi URL: ");
		Scanner sc = new Scanner(System.in);
		String link = sc.nextLine();
		URL url = new URL(link);
		InetAddress add = InetAddress.getByName(url.getHost());
		System.out.println("URL: " + url.toString());
		System.out.println("File: " + url.getFile());
		System.out.println("Host: "+url.getHost());
		System.out.println("Port: "+url.getPort());
		System.out.println("Protocol: " + url.getProtocol());
		System.out.println("IP: "+ add.getHostAddress());
	}
	public static void Bai_2() {
		int i;
	    InputStream bis;
		System.out.print("Nhap dia chi URL: ");
		Scanner sc = new Scanner(System.in);
		String url = sc.nextLine();
	    try{
	        URL u = new URL(url);
	        bis = (InputStream)u.getContent();
	        while((i=bis.read())>0) {
	        	System.out.print((char)i);
	        }

	        System.out.println();
	    }

	    catch(MalformedURLException  e){
	        System.out.println("The URL is incorrect!!");
	    }
	    catch (IOException e){
	      System.out.println(e);
	    }
	}
	public static void Bai_3() {
		String linkURL = "https://docbao.vn";
		InetAddress i; 
		URL url;
        try{
        	url = new URL(linkURL);
            i=InetAddress.getLocalHost();
            System.out.println("The localhost is: "+ i);
            System.out.println("URL: " + url.toString());
            i=InetAddress.getByName(url.getHost());
            System.out.println("The Host address is: "+i);
        }catch(IOException e){
            System.out.println(e);
        }
	}
	public static void Bai_4() throws IOException {
		System.out.print("Nhap dia chi URL: ");
		Scanner sc = new Scanner(System.in);
		String url = sc.nextLine();
		
		URL address = new URL(url);
		
		URLConnection conn = address.openConnection();
		System.out.println("URL: " + address.toString());
		System.out.println("Date: " + new Date(conn.getIfModifiedSince()));
		System.out.println("Content-type: " + conn.getContentType());
		System.out.println("Expiration-date: " + new Date(conn.getExpiration()).toString());
		System.out.println("Last Modified-date: "+ new Date(conn.getLastModified()).toString());
	}
	public static void Bai_5() throws IOException {
		String [] listURL = {
				"facebook.com",
				"gmail.com",
				"google.com.vn",
				"instagram.com"
		};
		
		BufferedReader br = null;
		String linkURL;
		System.out.print("Nhap URL: ");
		Scanner sc = new Scanner(System.in);
		linkURL = sc.nextLine();
		URL url;
		InetAddress inet;
		try {
			url = new URL(linkURL);
			inet = InetAddress.getByName(url.getHost());
			 boolean exist = true;
			 for(int i = 0; i < listURL.length; i++){
		            if(listURL[i].equals(inet.getHostName())) {
		                exist = false;
		                break;
		            }
			 }
			 for(int i = 0; i < listURL.length; i++){
		            if(listURL[i].equals(inet.getHostAddress())) {
		                exist = false;
		                break;
		            }
			 }
			 if(exist == true){
		            System.out.println("Waiting !!!");
		            br = new BufferedReader(new InputStreamReader(url.openStream()));
					String line;
					StringBuilder sb = new StringBuilder();
					while((line = br.readLine()) != null) {
						sb.append(line);
						sb.append(System.lineSeparator());
					}
					System.out.println(sb);
		        } else {
		            System.out.println("Cannot access !!!");
		        }
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			System.err.println(linkURL + " is not a parseable URL");
		}
	}
	public static void Bai_6() throws UnknownHostException, MalformedURLException {
		String linkURL;
		System.out.print("Nhap URL: ");
		Scanner sc = new Scanner(System.in);
		linkURL = sc.nextLine();
		URL url = new URL(linkURL);
		InetAddress address = InetAddress.getByName(url.getHost());
		System.out.println("Hostname: " + address.getHostName());
		System.out.println("Dia chi IP: " + address.getHostAddress());
	}
	
	public static void main(String[] args) throws IOException {

		
		
		boolean next = true;
		
		do {
			System.out.println("1. Truy cap URL. Tra ve ten FILE, ten HOST, so hieu cong, kieu giao thuc.");
			System.out.println("2. Lien ket URL, Tra ve noi dung trang web");
			System.out.println("3. Su dung InetAddress in ra dia chi Localhost, dia chi URL cua mot trang web");
			System.out.println("4. Ket noi URL, tra ve Ngay tao, Ngay chinh sua, Ngay het han");
			System.out.println("5. Nhap dia chi URL, tra ve noi dung trang web. Neu sai dia chi URL thong bao. Co the nhap dia chi IP hoac URL");
			System.out.println("6. Nhap dia chi URL, Tra ve hostname, dia chi IP");
			System.out.println("7. EXIT");
			System.out.print("Moi chon [1 -> 7]: ");
			int chon;
			Scanner scanner = new Scanner(System.in);
			chon = scanner.nextInt();
			switch(chon) {
			case 1: 
				Bai_1();
				System.out.println("==================================================");
				break;
			case 2: 
				Bai_2();
				System.out.println("==================================================");
				break;
			case 3:
				Bai_3();
				System.out.println("==================================================");
				break;
			case 4:
				Bai_4();
				System.out.println("==================================================");
				break;
			case 5:
				Bai_5();
				System.out.println("==================================================");
				break;
			case 6:
				Bai_6();
				System.out.println("==================================================");
				break;
				
			default:
				System.out.println("Tam Biet");
				next = false;
				break;
		}
		}while(next);
		
		
		
	}

}
