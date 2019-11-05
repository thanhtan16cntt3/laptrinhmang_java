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
import javax.swing.*;
import javax.swing.text.AbstractDocument.Content;


public class server extends JFrame implements ActionListener {

	static JTextArea content; // Khai báo ô nhập nội dung chat
    static JButton send; // Khai báo nút gửi nội dung
    static JTextField nhap, toName; // Khai báo ô nhập tên 
    static String chuoi=""; // Chuỗi nội dung tin chat giữa 2 máy
    static String temp=""; // Chuỗi tạm chứa thông tin xuất lên giao diện
    static ServerSocket serA; // Tạo socket server 
    static Socket s, sA; // Tạo socket kết nối
    static PrintWriter gui; // Khai báo biến gửi tin
    
	public static void main(String[] args) {
		new server();
		
		try {
			serA = new ServerSocket(2222);
			s = serA.accept();
			while(true) {
				BufferedReader nhan = new BufferedReader(new InputStreamReader(s.getInputStream()));
				while((chuoi = nhan.readLine())!= null) // Kiểm tra xem có tin để nhận từ Client hay không
                {
                    temp+=chuoi+"\n"; // Khi tin mới được gửi về sẽ được tự động xuống hàng và gán vào biến tạm
                    content.setText(temp); // Đưa dữ liệu chat lên giao diện
                    content.setVisible(false); // Cập nhật lại giao diện
                    content.setVisible(true);// Cập nhật lại giao diện
                }
			}
		}catch (Exception e) {     // Xử lý ngoại lệ khi gặp lỗi
            e.printStackTrace();                  
         }

	}
	public server() {
        setSize(600,600); // Định nghĩa kích thước giao diện chat
        setTitle("Server"); // Tiêu đề giao diện
        Font f = new Font("Arial",Font.BOLD,20);  // Định nghĩa Font chữ nội dung   
       
        content = new JTextArea(); // Tạo mới thành phần từ khai báo phía trên
        content.setFont(f); // Đặt font đã định nghĩa và ô nội dung chat
        content.setBackground(Color.cyan); // Định nghĩa ô nền nội dung
        content.setEditable(false); // Đặt chế độ không có phép chỉnh sửa trên ô
        JScrollPane sp = new JScrollPane(content); // Định nghĩa cho phép scrolling trên ô nội dung nếu nội dung quá dài
        send = new JButton("Gửi"); // Định nghĩa nút gửi tin
        nhap = new JTextField(30); // Định nghĩa độ dài kí tự nhập vào khung tên
        nhap.setFont(f); // Đặt font đã khai báo phía trên cho khung tên
        toName = new JTextField("Nhập tên bạn");  // Đặt nội dung yêu cầu mặc định
        toName.setFont(f); // Định nghĩa font cho toName
        toName.setBackground(Color.pink); // Định nghĩa nền cho toName
       
        add(toName, BorderLayout.PAGE_START); // Chia bố cục cho toName nằm ở vị trí đầu tiên
        add(sp, BorderLayout.CENTER); // Chia bố cục cho sp nằm ở vị trí giữa
        add(nhap, BorderLayout.PAGE_END); // Chia bố cục cho khung nhập liệu ở vị trí cuối
        nhap.addActionListener(this); // Gán sự kiện khi người dùng nhấn gửi
       
        setVisible(true); // Cập nhật lại giao diện
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Thoát giao diện khi đóng
    }
	@Override
    public void actionPerformed(ActionEvent e) { // Khai báo sử lý sự kiện
        if (e.getSource().equals(nhap))
        {
            try
            {                                 
               gui = new PrintWriter(s.getOutputStream(),true); // Tạo mới đối tượng gửi tin
               temp+=toName.getText()+": "+nhap.getText()+"\n"; // Cộng dồn chuỗi tin nhắn vào biến tạm
               gui.println(toName.getText()+": "+nhap.getText()); // Gửi tin qua mạng kèm tên
               content.setText(temp); // Cập nhật lại giao diện hiển thị nội dung tin
               nhap.setText(""); // Xóa trường nhập liệu
               nhap.requestFocus(); // Focus con chỏ ngay ô nhập liệu
               content.setVisible(false); // Cập nhật lại giao diện
               content.setVisible(true); // Cập nhật lại giao diện                                
            }
            catch (Exception r) { // Xử lý ngoại lệ khi xảy ra lỗi
                r.printStackTrace();
            }
        }
       
    }
}
