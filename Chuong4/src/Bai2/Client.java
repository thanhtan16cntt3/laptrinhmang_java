package Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = new Socket("localhost", 3333);
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
		
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			//truyen du lieu ve Server
			System.out.print("Nhap:");
			String str = sc.nextLine();
			dataOutputStream.writeUTF(str);
			dataOutputStream.flush();
			
			//doc du lieu tu server
			String str2 = dataInputStream.readUTF();
			if(str2.equals("q")) { break;}
			System.out.println("Server say: " + str2);
		}
		
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
	}

}
