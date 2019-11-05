package Chat_talk;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.FlowLayout;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.awt.event.ActionEvent;

public class Server extends JFrame {

	static JPanel contentPane;
	static JTextField txtMsg;
	
	static String temp = "";
	static String msg = "";
	static JTextArea content;
	
	static ServerSocket serverSocket;
	static Socket socket;
    static PrintWriter gui;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Server frame = new Server();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		});
		try {
			serverSocket = new ServerSocket(3333);
			socket = serverSocket.accept();
			//Nhan du lieu tu Client
			while(true) {
				BufferedReader nhan = new BufferedReader(
		                  new InputStreamReader(socket.getInputStream()));
				
				while((msg = nhan.readLine()) != null) {
					temp += msg + "\n";
					content.setText(temp);
					content.setVisible(false);
					content.setVisible(true);
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	/**
	 * Create the frame.
	 */
	public Server() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Server");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		content = new JTextArea();
		content.setEditable(false);
		content.setBounds(10, 0, 414, 208);
		contentPane.add(content);
		
		txtMsg = new JTextField();
		txtMsg.setBounds(10, 218, 301, 30);
		contentPane.add(txtMsg);
		txtMsg.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						//truyen du lieu qua Client
						gui = new PrintWriter(socket.getOutputStream()); // luồn gửi tin
						temp += txtMsg.getText() + "\n";
						gui.println("Server: " + txtMsg.getText());
						gui.flush();
						content.setText(temp);
						txtMsg.setText("");
						txtMsg.requestFocus();
						content.setVisible(false);
						content.setVisible(true);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
				}
		});
		btnSend.setBounds(334, 219, 90, 29);
		contentPane.add(btnSend);
	}

}
