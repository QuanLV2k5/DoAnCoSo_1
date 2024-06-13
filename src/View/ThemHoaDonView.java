package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DBConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ThemHoaDonView extends JFrame {

	private JPanel contentPane;
	private JTextField txt_MaHD;
	private JTextField txt_HoTen;
	private JTextField txt_loaiDien;
	private JTextField txt_LDTT;
	private double giaTienTren1Kwh = 2006.79;
	private double tongTien;
	QuanLyHoaDon quanLyHoaDon = new QuanLyHoaDon();

	DBConnect cn = new DBConnect();
	Connection connection;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ThemHoaDonView frame = new ThemHoaDonView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ThemHoaDonView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 715, 423);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_ThemHD = new JLabel("Thêm hoá đơn");
		jLable_ThemHD.setOpaque(true);
		jLable_ThemHD.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_ThemHD.setForeground(Color.WHITE);
		jLable_ThemHD.setFont(new Font("Calibri", Font.BOLD, 38));
		jLable_ThemHD.setBackground(Color.BLUE);
		jLable_ThemHD.setBounds(0, 0, 701, 118);
		contentPane.add(jLable_ThemHD);

		JLabel jLable_maHD = new JLabel("Mã hoá đơn:");
		jLable_maHD.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_maHD.setForeground(Color.BLACK);
		jLable_maHD.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_maHD.setBackground(Color.LIGHT_GRAY);
		jLable_maHD.setBounds(41, 150, 184, 38);
		contentPane.add(jLable_maHD);

		JLabel jLable_hoTen = new JLabel("Họ tên:");
		jLable_hoTen.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_hoTen.setForeground(Color.BLACK);
		jLable_hoTen.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_hoTen.setBackground(Color.LIGHT_GRAY);
		jLable_hoTen.setBounds(41, 198, 184, 38);
		contentPane.add(jLable_hoTen);

		JLabel jLable_loaiDien = new JLabel("Loại điện:");
		jLable_loaiDien.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_loaiDien.setForeground(Color.BLACK);
		jLable_loaiDien.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_loaiDien.setBackground(Color.LIGHT_GRAY);
		jLable_loaiDien.setBounds(41, 246, 184, 38);
		contentPane.add(jLable_loaiDien);

		JLabel jLable_LDTT = new JLabel("Lượng điện tiêu thụ:");
		jLable_LDTT.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_LDTT.setForeground(Color.BLACK);
		jLable_LDTT.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_LDTT.setBackground(Color.LIGHT_GRAY);
		jLable_LDTT.setBounds(10, 294, 215, 38);
		contentPane.add(jLable_LDTT);

		txt_MaHD = new JTextField();
		txt_MaHD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_MaHD.setColumns(10);
		txt_MaHD.setBounds(250, 150, 193, 28);
		contentPane.add(txt_MaHD);

		txt_HoTen = new JTextField();
		txt_HoTen.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_HoTen.setColumns(10);
		txt_HoTen.setBounds(250, 198, 193, 28);
		contentPane.add(txt_HoTen);

		txt_loaiDien = new JTextField();
		txt_loaiDien.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_loaiDien.setColumns(10);
		txt_loaiDien.setBounds(250, 248, 193, 28);
		contentPane.add(txt_loaiDien);

		txt_LDTT = new JTextField();
		txt_LDTT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_LDTT.setColumns(10);
		txt_LDTT.setBounds(250, 296, 193, 28);
		contentPane.add(txt_LDTT);

		JButton jButton_Them = new JButton("Thêm");
		jButton_Them.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					if (txt_MaHD.getText().equals("") || txt_HoTen.getText().equals("")
							|| txt_loaiDien.getText().equals("") || txt_LDTT.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Bạn cần nhập đủ dữ liệu");
					} else {
						StringBuffer sb = new StringBuffer();
						String sql_check_pk = "SELECT MaHD FROM HoaDon WHERE MaHD = '" + txt_MaHD.getText() + "'";
						Statement st = connection.createStatement();
						ResultSet rs = st.executeQuery(sql_check_pk);

						if (rs.next()) {
							sb.append("Hoá đơn này đã tồn tại!");
						}
						if (sb.length() > 0) {
							JOptionPane.showMessageDialog(contentPane, sb.toString());
						} else {
							tongTien = Integer.parseInt(txt_LDTT.getText()) * giaTienTren1Kwh;
							String sql = "Insert into HoaDon values('" + txt_MaHD.getText() + "', N'"
									+ txt_HoTen.getText() + "', '" + txt_loaiDien.getText() + "', '"
									+ txt_LDTT.getText() + "', '" + tongTien + "')";
							st = connection.createStatement();
							int kq = st.executeUpdate(sql);

							if (kq > 0) {
								JOptionPane.showMessageDialog(contentPane, "Thêm mới thành công!");
							}
						}
						st.close();
						rs.close();
					}
					cn.disconnect();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		jButton_Them.setOpaque(true);
		jButton_Them.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Them.setBorderPainted(false);
		jButton_Them.setBackground(Color.CYAN);
		jButton_Them.setBounds(519, 264, 127, 38);
		jButton_Them.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(ThemHoaDonView.class.getResource("Icon/Add.png"))));
		contentPane.add(jButton_Them);
	}
}
