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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JRadioButton;
import javax.swing.JToggleButton;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class DangNhapView extends JFrame {

	private JPanel contentPane;
	private JTextField jTextField_tenDangNhap;
	private JPasswordField passwordField;

	DBConnect cn = new DBConnect();
	Connection connection;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DangNhapView frame = new DangNhapView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public DangNhapView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 569, 490);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel dangNhap = new JLabel("Đăng Nhập");
		dangNhap.setForeground(new Color(255, 255, 255));
		dangNhap.setHorizontalAlignment(SwingConstants.CENTER);
		dangNhap.setFont(new Font("Cambria", Font.BOLD, 38));
		dangNhap.setBackground(new Color(0, 0, 255));
		dangNhap.setOpaque(true);
		dangNhap.setBounds(0, 0, 555, 97);
		contentPane.add(dangNhap);

		JLabel jLabel_tenDangNhap = new JLabel("Tài khoản");
		jLabel_tenDangNhap.setForeground(new Color(0, 0, 0));
		jLabel_tenDangNhap.setHorizontalAlignment(SwingConstants.RIGHT);
		jLabel_tenDangNhap.setFont(new Font("Cambria", Font.BOLD, 26));
		jLabel_tenDangNhap.setBackground(Color.BLACK);
		jLabel_tenDangNhap.setBounds(26, 120, 198, 65);
		jLabel_tenDangNhap.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhapView.class.getResource("Icon/User.png"))));
		contentPane.add(jLabel_tenDangNhap);

		jTextField_tenDangNhap = new JTextField();
		jTextField_tenDangNhap.setFont(new Font("Tahoma", Font.PLAIN, 16));
		jTextField_tenDangNhap.setBounds(241, 143, 193, 26);
		contentPane.add(jTextField_tenDangNhap);
		jTextField_tenDangNhap.setColumns(10);

		JLabel jLable_matKhau = new JLabel("Mật khẩu");
		jLable_matKhau.setForeground(new Color(0, 0, 0));
		jLable_matKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_matKhau.setFont(new Font("Cambria", Font.BOLD, 26));
		jLable_matKhau.setBackground(Color.BLACK);
		jLable_matKhau.setBounds(54, 195, 170, 65);
		jLable_matKhau.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(DangNhapView.class.getResource("Icon/Lock.png"))));
		contentPane.add(jLable_matKhau);

		JButton btn_signIn = new JButton("Đăng nhập");
		btn_signIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					String username = jTextField_tenDangNhap.getText();
					String password = passwordField.getText();
					String passwordEncrypted = EncryptByMD5.encryptMD5(password);
					String sql = "Select * from QuanLyTaiKhoan where TaiKhoan = ? and MatKhau = ?";
					PreparedStatement ps = connection.prepareCall(sql);
					ps.setString(1, username);
					ps.setString(2, passwordEncrypted);
					ResultSet rs = ps.executeQuery();

					if (rs.next()) {
						HomeMainVIew hm = new HomeMainVIew();
						hm.setVisible(true);
						hm.setLocationRelativeTo(null);
						new DangNhapView().dispose();;
					} else {
						JOptionPane.showMessageDialog(contentPane, "Sai tên tài khoản hoặc mật khẩu!", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btn_signIn.setBackground(new Color(0, 255, 255));
		btn_signIn.setOpaque(true);
		btn_signIn.setBorderPainted(false);
		btn_signIn.setForeground(new Color(0, 100, 0));
		btn_signIn.setFont(new Font("Calibri", Font.BOLD, 26));
		btn_signIn.setBounds(173, 289, 224, 45);
		contentPane.add(btn_signIn);

		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.PLAIN, 14));
		passwordField.setBounds(241, 219, 193, 26);
		contentPane.add(passwordField);

		JButton btn_signUp = new JButton("Đăng ký");
		btn_signUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					SignUpView sv = new SignUpView();
					sv.setVisible(true);
					sv.setLocationRelativeTo(null);

				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btn_signUp.setOpaque(true);
		btn_signUp.setBorderPainted(false);
		btn_signUp.setForeground(new Color(0, 100, 0));
		btn_signUp.setFont(new Font("Calibri", Font.BOLD, 26));
		btn_signUp.setBackground(Color.CYAN);
		btn_signUp.setBounds(173, 367, 224, 45);
		contentPane.add(btn_signUp);

		JToggleButton toggleButton_hideAnShow = new JToggleButton("Show");
		toggleButton_hideAnShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (toggleButton_hideAnShow.isSelected()) {
					passwordField.setEchoChar((char) 0);
					toggleButton_hideAnShow.setText("Hide");
				} else {
					passwordField.setEchoChar('●');
					toggleButton_hideAnShow.setText("Show");
				}
			}
		});
		toggleButton_hideAnShow.setOpaque(true);
		toggleButton_hideAnShow.setForeground(Color.RED);
		toggleButton_hideAnShow.setFont(new Font("Calibri", Font.BOLD, 20));
		toggleButton_hideAnShow.setBorderPainted(false);
		toggleButton_hideAnShow.setBackground(Color.CYAN);
		toggleButton_hideAnShow.setBounds(451, 206, 83, 39);
		contentPane.add(toggleButton_hideAnShow);
	}
}
