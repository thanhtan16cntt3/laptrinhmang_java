package chatTalk;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class client extends JFrame implements ActionListener{
	static JTextArea content; // Khai báo nội dung nhập liệu
    static JButton send; // Khai báo nút gửi tin
    static JTextField nhap, toName; // Khai báo nội dung nhập liệu
    static String chuoi=""; // Tạo chuỗi chưa nội dung nhận
    static String temp="",addrr=""; // Tạo chuỗi tạm
    static Socket s, sB; // Khai báo socket phía client 
    static PrintWriter gui; // Khai báo luồn gửi tin
    public static void main(String[] args) {
                
          try
          {
            String ip = JOptionPane.showInputDialog(null, "Nhập IP máy chủ"); // họp thoại thông báo yêu cầu nhập ip server
             new client();  // Khởi tạo giao diện
              sB = new Socket(ip,2222); // Kết nối dựa trên ip vừa nhập và cổng mặc định   
            BufferedReader nhan = new BufferedReader(
                  new InputStreamReader(sB.getInputStream())); // Luồng nhận tin
           while((chuoi = nhan.readLine())!= null) // Kiểm tra xem có tin để nhận hay không?
         {
                temp+=chuoi+"\n"; // Cộng chuỗi tin chat vào biến tạm
                content.setText(temp); // Đưa nội dung lên giao diện
             content.setVisible(false); // Cập nhật lại giao diện
             content.setVisible(true); // Cập nhật lại giao diện
         }
          }
           catch (Exception e) { // Xử lý ngoại lệ
             e.printStackTrace();                  
          }      
    }
     public client() { 
            setSize(600,600); // Định nghĩa kích thước client
            setTitle("Client"); // Tiêu đề
            Font f = new Font("Arial",Font.BOLD,20);  // Font chữ
           
            content = new JTextArea(); // Nội dung chat
            content.setFont(f); // Định nghĩa font cho nội dung
            content.setBackground(Color.cyan); // Đặt hình nền
            JScrollPane sp = new JScrollPane(content); // Cho phép scrolling khi nội dung quá dài
            content.setEditable(false); // Không cho phép chỉnh sửa nội dung
            send = new JButton("Gửi"); // Tạo nút gửi tin
            nhap = new JTextField(30); // Tạo khung nhập tên với chiều dài tối đa
            nhap.setFont(f); // Định nghĩa font cho khung
            toName = new JTextField("Nhập tên bạn"); // Định nghĩa nội dung mặc định cho khung
            toName.setFont(f); // Đặt font cho khung
            toName.setBackground(Color.pink); // Đặt nền cho khung
           
           
            add(toName, BorderLayout.PAGE_START); // Bố trí toName ở trị trí bắt đầu
            add(sp, BorderLayout.CENTER); // sp ở vị trí giữa
            add(nhap, BorderLayout.PAGE_END); // nhap ở vị trí cuối
            nhap.addActionListener(this); // Định nghĩa sự kiện cho khung nhập

            setVisible(true); // cập nhật giao diện
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát khi đóng chương trình
     }
     @Override
     public void actionPerformed(ActionEvent e) {
         if (e.getSource().equals(nhap)) // Bắt sự kiện khi người dùng gửi
         {
             try
             {      gui = new PrintWriter(sB.getOutputStream(),true); // luồn gửi tin
                    gui.println(toName.getText()+": "+nhap.getText()); // Gửi tin và kèm tên
                    temp+=toName.getText()+": "+nhap.getText()+"\n"; // Thêm tin gửi vào biến tạm
                    nhap.setText(""); // Xóa nội dung nhập liệu
                    nhap.requestFocus(); // Focus con trỏ vào khung nhập liệu
                    content.setText(temp); // Đưa lên giao diện
                    content.setVisible(false); // Cập nhật lại giao diện
                    content.setVisible(true); // Cập nhật lại giao diện                                            
             }
             catch (Exception r) { // Xử lý ngoại lệ khi xảy ra lỗi
                 r.printStackTrace();
             }
         }
     }
}
