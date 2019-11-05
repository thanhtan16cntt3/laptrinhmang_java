package Bai1;
import java.io.DataOutputStream;
import java.io.DataInputStream;

import java.io.IOException;

import java.net.ServerSocket;

import java.net.Socket;


public class numberServer {

private static ServerSocket serverSocket = null;

public static void main(String[] args) throws IOException{
    DataOutputStream dos = null;

    DataInputStream dis=null;

    try {

        serverSocket = new ServerSocket(8002);

        System.out.print("Server đã được mở \n" );

        Socket clientSocket = null;

        clientSocket = serverSocket.accept();

        dos=new DataOutputStream(clientSocket.getOutputStream());

        dis=new DataInputStream(clientSocket.getInputStream());

        String inline="";
//        String a="";
//        String b="";
//        String c="";
        int tram,chuc,donvi;
        while(true)

        {
            inline = dis.readUTF();
//          	int tram = Integer.parseInt(a);
//           	int chuc = Integer.parseInt(b);
//           	int donvi = Integer.parseInt(c);
            int i=Integer.parseInt(inline);
               tram = i/100;
               chuc = (i-(tram*100))/10;
               donvi = (i-((tram*100)+(chuc*10))); 
                
                

                switch(tram)

                {

                    case 1:inline="Mot tram";break;

                    case 2:inline="Hai tram";break;

                    case 3:inline="Ba tram";break;

                    case 4:inline="Bon tram";break;

                    case 5:inline="Nam tram";break;

                    case 6:inline="Sau tram";break;

                    case 7:inline="bay tram";break;

                    case 8:inline="Tam tram";break;

                    case 9:inline="chin tram";break;

                }
//                System.out.println(a);
                switch(chuc)

                {

                    case 1:inline=inline+" Muoi";break;

                    case 2:inline=inline+" Hai muoi";break;

                    case 3:inline=inline+" Ba muoi";break;

                    case 4:inline=inline+" Bon muoi";break;

                    case 5:inline=inline+" Nam muoi";break;

                    case 6:inline=inline+" Sau muoi";break;

                    case 7:inline=inline+" bay muoi";break;

                    case 8:inline=inline+" Tam muoi";break;

                    case 9:inline=inline+" chin muoi";break;
                    
                }
                switch(donvi)

                {

                    case 1:inline=inline+" Mot";break;

                    case 2:inline=inline+" Hai ";break;

                    case 3:inline=inline+" Ba ";break;

                    case 4:inline=inline+" Bon ";break;

                    case 5:inline=inline+" Nam ";break;

                    case 6:inline=inline+" Sau ";break;

                    case 7:inline=inline+" bay ";break;

                    case 8:inline=inline+" Tam ";break;

                    case 9:inline=inline+" chin ";break;
                    
                }
                dos.writeUTF(inline);
        }
    }

    catch(Exception e) {

        dos.close();

        serverSocket.close();

        dis.close();

        System.out.print(e.getMessage());

    }

}

}
