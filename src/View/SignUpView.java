package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DAO.DBConnect;
import Security.EncryptByMD5;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToggleButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SignUpView extends JFrame {

	private JPanel contentPane;
	private JTextField txt_TK;
	private JPasswordField pw_mk;
	private JPasswordField pw_xacnhan;
	private JButton btn_signUp;
	private JToggleButton tglbtnShow;

	DBConnect cn = new DBConnect();
	Connection connection;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					SignUpView frame = new SignUpView();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public SignUpView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 561, 450);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLabel_tenDangNhap = new JLabel("Tài khoản");
		jLabel_tenDangNhap.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel_tenDangNhap.setForeground(Color.BLACK);
		jLabel_tenDangNhap.setFont(new Font("Cambria", Font.BOLD, 26));
		jLabel_tenDangNhap.setBackground(Color.BLACK);
		jLabel_tenDangNhap.setBounds(77, 112, 150, 65);
		jLabel_tenDangNhap.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignUpView.class.getResource("Icon/User.png"))));
		contentPane.add(jLabel_tenDangNhap);

		txt_TK = new JTextField();
		txt_TK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_TK.setColumns(10);
		txt_TK.setBounds(237, 135, 209, 26);
		contentPane.add(txt_TK);

		JLabel jLable_matKhau = new JLabel("Mật khẩu");
		jLable_matKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_matKhau.setForeground(Color.BLACK);
		jLable_matKhau.setFont(new Font("Cambria", Font.BOLD, 26));
		jLable_matKhau.setBackground(Color.BLACK);
		jLable_matKhau.setBounds(64, 164, 163, 65);
		jLable_matKhau.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(SignUpView.class.getResource("Icon/Lock.png"))));
		contentPane.add(jLable_matKhau);

		pw_mk = new JPasswordField();
		pw_mk.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pw_mk.setBounds(237, 188, 209, 26);
		contentPane.add(pw_mk);

		JLabel jLable_matKhau_1 = new JLabel("Xác nhận mật khẩu");
		jLable_matKhau_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_matKhau_1.setForeground(Color.BLACK);
		jLable_matKhau_1.setFont(new Font("Cambria", Font.BOLD, 26));
		jLable_matKhau_1.setBackground(Color.BLACK);
		jLable_matKhau_1.setBounds(0, 229, 244, 65);
		contentPane.add(jLable_matKhau_1);

		pw_xacnhan = new JPasswordField();
		pw_xacnhan.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pw_xacnhan.setBounds(237, 253, 209, 26);
		contentPane.add(pw_xacnhan);

		btn_signUp = new JButton("Đăng ký");
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				SignUpView frame = new SignUpView();
				try {
					String username = txt_TK.getText();
					String password = pw_mk.getText();
					String passwordEncrypted = EncryptByMD5.encryptMD5(password);
					if (username.equals("") || pw_xacnhan.getText().equals("") || password.equals("")) {
						JOptionPane.showMessageDialog(frame, "Bạn cần nhập đủ dữ liệu");
					} else {
						if (!pw_xacnhan.getText().equals(password)) {
							JOptionPane.showMessageDialog(frame, "Xác nhận mật khẩu chưa chính xác!");
						} else {
							StringBuffer sb = new StringBuffer();
							String sql_check_pk = "SELECT TaiKhoan FROM QuanLyTaiKhoan WHERE TaiKhoan = '" + username
									+ "'";
							Statement st = connection.createStatement();
							ResultSet rs = st.executeQuery(sql_check_pk);

							if (rs.next()) {
								sb.append("Tài khoản này đã tồn tại!");
							}
							if (sb.length() > 0) {
								JOptionPane.showMessageDialog(frame, sb.toString());
							} else {
								String sql = "Insert into QuanLyTaiKhoan values('" + username + "', '"
										+ passwordEncrypted + "')";
								st = connection.createStatement();
								st.executeUpdate(sql);

								JOptionPane.showMessageDialog(frame, "Tạo tài khoản thành công!");
								frame.dispose();
							}
							st.close();
							rs.close();
						}
					}
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btn_signUp.setOpaque(true);
		btn_signUp.setBorderPainted(false);
		btn_signUp.setForeground(new Color(0, 100, 0));
		btn_signUp.setFont(new Font("Cambria", Font.BOLD, 26));
		btn_signUp.setBackground(Color.CYAN);
		btn_signUp.setBounds(170, 319, 227, 45);
		contentPane.add(btn_signUp);

		JLabel dangKy = new JLabel("Đăng Ký");
		dangKy.setOpaque(true);
		dangKy.setHorizontalAlignment(SwingConstants.CENTER);
		dangKy.setForeground(Color.WHITE);
		dangKy.setFont(new Font("Cambria", Font.BOLD, 38));
		dangKy.setBackground(Color.BLUE);
		dangKy.setBounds(0, 0, 547, 97);
		contentPane.add(dangKy);

		tglbtnShow = new JToggleButton("Show");
		tglbtnShow.setHorizontalAlignment(SwingConstants.LEADING);
		tglbtnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tglbtnShow.isSelected()) {
					pw_mk.setEchoChar((char) 0);
					tglbtnShow.setText("Hide");
				} else {
					pw_mk.setEchoChar('●');
					tglbtnShow.setText("Show");
				}
			}
		});
		tglbtnShow.setOpaque(true);
		tglbtnShow.setForeground(Color.RED);
		tglbtnShow.setFont(new Font("Cambria", Font.BOLD, 20));
		tglbtnShow.setBorderPainted(false);
		tglbtnShow.setBackground(new Color(0, 255, 255));
		tglbtnShow.setBounds(456, 182, 81, 39);
		contentPane.add(tglbtnShow);
	}

}
