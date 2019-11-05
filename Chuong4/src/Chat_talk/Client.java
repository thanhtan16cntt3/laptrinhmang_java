package Chat_talk;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Client extends JFrame {

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
					Client frame = new Client();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
		try {
			socket = new Socket("localhost",3333);
			
			while(true) {
				//Nhan du lieu tu Server
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
	public Client() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Client");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		content = new JTextArea();
		content.setEditable(false);
		content.setBounds(10, 11, 414, 190);
		contentPane.add(content);
		
		txtMsg = new JTextField();
		txtMsg.setBounds(10, 212, 318, 36);
		contentPane.add(txtMsg);
		txtMsg.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						//truyen du lieu qua Client
						gui = new PrintWriter(socket.getOutputStream()); // luồn gửi tin
	                    gui.println("Client: " + txtMsg.getText());
	                    gui.flush();
						temp += txtMsg.getText() + "\n";
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
		btnSend.setBounds(338, 212, 86, 36);
		contentPane.add(btnSend);
	}

}
