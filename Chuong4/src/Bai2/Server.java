package Bai2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) throws IOException {
		DataInputStream dataInputStream = null;
		DataOutputStream dataOutputStream = null;
		ServerSocket serverSocket = null;
		Socket socket = null;
		try {
			serverSocket = new ServerSocket(3333);
			
			socket = serverSocket.accept();
			
			dataInputStream = new DataInputStream(socket.getInputStream());
			dataOutputStream = new DataOutputStream(socket.getOutputStream());
			
			Scanner sc = new Scanner(System.in);
			
			while(true) {
				//doc du lieu tu client
				String str = dataInputStream.readUTF();
				String[] arr = str.split(" ");
				int numberOne = Integer.parseInt(arr[0]);
				int numberTwo = Integer.parseInt(arr[2]);
				switch (arr[1]) {
				case "+":
					dataOutputStream.writeUTF(str + " = " + (numberOne + numberTwo));
					dataOutputStream.flush();
					break;
				case "*":
					dataOutputStream.writeUTF(str + " = " + (numberOne * numberTwo));
					dataOutputStream.flush();
				case "/":
					dataOutputStream.writeUTF(str + " = " + (numberOne / numberTwo));
					dataOutputStream.flush();
				case "-":
					dataOutputStream.writeUTF(str + " = " + (numberOne - numberTwo));
					dataOutputStream.flush();
				default:
					break;
				} 
				
				
				//Truyen du lieu ve client
				
				String str2 = sc.nextLine();
				dataOutputStream.writeUTF(str2);
				dataOutputStream.flush();
			}
			
			
		} catch (IOException e) {
			dataOutputStream.close();
			dataInputStream.close();
			socket.close();
			serverSocket.close();
			e.printStackTrace();
		}
		

	}

}
