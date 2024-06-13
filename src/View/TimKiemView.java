package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.Box;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DBConnect;

import javax.swing.border.EtchedBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TimKiemView extends JFrame {

	private JPanel contentPane;
	private JTextField textField_maKH;
	private JTextField textField_tenKH;
	private JTable table;

	Object[] column = { "Mã khách hàng", "Họ tên", "CMND", "Địa chỉ", "Giới tính", "Ngày sinh", "SĐT", "Ngày đăng ký",
			"Loại Điện" };
	final DefaultTableModel tb_HTT = new DefaultTableModel(column, 0);

	DBConnect cn = new DBConnect();
	Connection connection;
	private JRadioButton jRadioButton_Nam;
	private JRadioButton jRadioButton_Nu;
	private JComboBox jcomboBox_loaiDien;

	public void findID() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM HoTieuThu where MaKH = '" + textField_maKH.getText() + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			tb_HTT.setRowCount(0);

			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				tb_HTT.addRow(row);
			}
			if (tb_HTT.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng này!");
			} else {
				table.setModel(tb_HTT);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void findName() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM HoTieuThu where HoTen = N'" + textField_tenKH.getText() + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			tb_HTT.setRowCount(0);

			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				tb_HTT.addRow(row);
			}
			if (tb_HTT.getRowCount() == 0) {
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng này!");
			} else {
				table.setModel(tb_HTT);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TimKiemView frame = new TimKiemView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TimKiemView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1424, 590);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_timKiem = new JLabel("Tìm kiếm");
		jLable_timKiem.setBounds(0, 0, 1410, 118);
		jLable_timKiem.setOpaque(true);
		jLable_timKiem.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_timKiem.setForeground(Color.WHITE);
		jLable_timKiem.setFont(new Font("Cambria", Font.BOLD, 46));
		jLable_timKiem.setBackground(Color.BLUE);
		contentPane.add(jLable_timKiem);

		JLabel jLable_maKH_1 = new JLabel("Mã khách hàng");
		jLable_maKH_1.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_maKH_1.setForeground(Color.BLACK);
		jLable_maKH_1.setFont(new Font("Cambria", Font.BOLD, 26));
		jLable_maKH_1.setBackground(Color.LIGHT_GRAY);
		jLable_maKH_1.setBounds(312, 169, 184, 51);
		contentPane.add(jLable_maKH_1);

		JLabel jLable_tenKH = new JLabel("Tên khách hàng");
		jLable_tenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_tenKH.setForeground(Color.BLACK);
		jLable_tenKH.setFont(new Font("Cambria", Font.BOLD, 26));
		jLable_tenKH.setBackground(Color.LIGHT_GRAY);
		jLable_tenKH.setBounds(297, 253, 199, 51);
		contentPane.add(jLable_tenKH);

		textField_maKH = new JTextField();
		textField_maKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_maKH.setColumns(10);
		textField_maKH.setBounds(537, 185, 199, 26);
		contentPane.add(textField_maKH);

		textField_tenKH = new JTextField();
		textField_tenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField_tenKH.setColumns(10);
		textField_tenKH.setBounds(537, 269, 199, 26);
		contentPane.add(textField_tenKH);

		JButton btnFindId = new JButton("FIND ID");
		btnFindId.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					findID();
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnFindId.setForeground(Color.GREEN);
		btnFindId.setFont(new Font("Calibri", Font.BOLD, 24));
		btnFindId.setBorderPainted(false);
		btnFindId.setBackground(new Color(0, 100, 0));
		btnFindId.setBounds(812, 180, 164, 43);
		contentPane.add(btnFindId);

		JButton btnFindName = new JButton("FIND NAME");
		btnFindName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					findName();
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnFindName.setForeground(Color.GREEN);
		btnFindName.setFont(new Font("Calibri", Font.BOLD, 24));
		btnFindName.setBorderPainted(false);
		btnFindName.setBackground(new Color(0, 100, 0));
		btnFindName.setBounds(812, 259, 164, 43);
		contentPane.add(btnFindName);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBounds(0, 367, 1410, 192);
		contentPane.add(table);

		JButton btnDelete = new JButton("DELETE");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					textField_maKH.setText("");
					textField_tenKH.setText("");
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		btnDelete.setForeground(new Color(255, 0, 0));
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 24));
		btnDelete.setBorderPainted(false);
		btnDelete.setBackground(new Color(128, 0, 0));
		btnDelete.setBounds(1052, 217, 151, 43);
		contentPane.add(btnDelete);

		JLabel jLable_maKH_1_1 = new JLabel("Mã khách hàng");
		jLable_maKH_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maKH_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maKH_1_1.setForeground(new Color(128, 0, 0));
		jLable_maKH_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_maKH_1_1.setBackground(Color.WHITE);
		jLable_maKH_1_1.setBounds(0, 337, 139, 28);
		contentPane.add(jLable_maKH_1_1);

		JLabel jLable_tenKH1 = new JLabel("Tên khách hàng");
		jLable_tenKH1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tenKH1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tenKH1.setForeground(new Color(128, 0, 0));
		jLable_tenKH1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_tenKH1.setBackground(Color.WHITE);
		jLable_tenKH1.setBounds(155, 337, 146, 28);
		contentPane.add(jLable_tenKH1);

		JLabel jLable_CMND1 = new JLabel("CMND/CCCD");
		jLable_CMND1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_CMND1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_CMND1.setForeground(new Color(128, 0, 0));
		jLable_CMND1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_CMND1.setBackground(Color.WHITE);
		jLable_CMND1.setBounds(316, 337, 123, 28);
		contentPane.add(jLable_CMND1);

		JLabel jLable_diaChi1 = new JLabel("Địa chỉ");
		jLable_diaChi1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_diaChi1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_diaChi1.setForeground(new Color(128, 0, 0));
		jLable_diaChi1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_diaChi1.setBackground(Color.WHITE);
		jLable_diaChi1.setBounds(473, 337, 95, 28);
		contentPane.add(jLable_diaChi1);

		JLabel jLable_gioiTinh1 = new JLabel("Giới tính");
		jLable_gioiTinh1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_gioiTinh1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_gioiTinh1.setForeground(new Color(128, 0, 0));
		jLable_gioiTinh1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_gioiTinh1.setBackground(Color.WHITE);
		jLable_gioiTinh1.setBounds(629, 337, 139, 28);
		contentPane.add(jLable_gioiTinh1);

		JLabel jLable_ngaySinh1_1 = new JLabel("Ngày sinh");
		jLable_ngaySinh1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_ngaySinh1_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_ngaySinh1_1.setForeground(new Color(128, 0, 0));
		jLable_ngaySinh1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_ngaySinh1_1.setBackground(Color.WHITE);
		jLable_ngaySinh1_1.setBounds(787, 337, 139, 28);
		contentPane.add(jLable_ngaySinh1_1);

		JLabel jLable_sdt1 = new JLabel("Số điện thoại");
		jLable_sdt1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_sdt1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_sdt1.setForeground(new Color(128, 0, 0));
		jLable_sdt1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_sdt1.setBackground(Color.WHITE);
		jLable_sdt1.setBounds(946, 337, 139, 28);
		contentPane.add(jLable_sdt1);

		JLabel jLable_ngayDK1 = new JLabel("Ngày đăng ký");
		jLable_ngayDK1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_ngayDK1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_ngayDK1.setForeground(new Color(128, 0, 0));
		jLable_ngayDK1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_ngayDK1.setBackground(Color.WHITE);
		jLable_ngayDK1.setBounds(1095, 337, 139, 28);
		contentPane.add(jLable_ngayDK1);

		JLabel jLable_loaiDien1 = new JLabel("Loại điện");
		jLable_loaiDien1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_loaiDien1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_loaiDien1.setForeground(new Color(128, 0, 0));
		jLable_loaiDien1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_loaiDien1.setBackground(Color.WHITE);
		jLable_loaiDien1.setBounds(1261, 337, 139, 28);
		contentPane.add(jLable_loaiDien1);
	}
}
