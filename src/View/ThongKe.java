package View;

import java.awt.EventQueue;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import DAO.DBConnect;

import javax.swing.JLabel;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTable;

public class ThongKe extends JFrame {

	private JPanel contentPane;
	Ql_HoTieuThuDienView hth = new Ql_HoTieuThuDienView();
	private JTable table;
	private JTable table_1;

	Object[] column = { "Mã khách hàng", "Họ tên", "Loại Điện", "LĐTT", "Tiền" };
	final DefaultTableModel tb_DSDN = new DefaultTableModel(column, 0);
	final DefaultTableModel tb_DSCN = new DefaultTableModel(column, 0);

	DBConnect cn = new DBConnect();
	Connection connection;

	public void loadBang_DSĐN() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM DanhSachDaNop";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			tb_DSDN.setRowCount(0);

			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				tb_DSDN.addRow(row);
			}

			table.setModel(tb_DSDN);
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void loadBang_DSCN() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM DanhSachChuaNop";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			tb_DSCN.setRowCount(0);

			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				tb_DSCN.addRow(row);
			}

			table_1.setModel(tb_DSCN);
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
					ThongKe frame = new ThongKe();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.loadBang_DSĐN();
					frame.loadBang_DSCN();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ThongKe() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 915, 746);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_thongKe = new JLabel("Danh sách hộ tiêu thụ điện đã nộp/chưa nộp tiền điện");
		jLable_thongKe.setOpaque(true);
		jLable_thongKe.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_thongKe.setForeground(Color.WHITE);
		jLable_thongKe.setFont(new Font("Calibri", Font.BOLD, 36));
		jLable_thongKe.setBackground(Color.BLUE);
		jLable_thongKe.setBounds(0, 0, 905, 113);
		contentPane.add(jLable_thongKe);

		JLabel lbl_daNop = new JLabel("Danh sách đã nộp tiền");
		lbl_daNop.setForeground(new Color(0, 0, 255));
		lbl_daNop.setFont(new Font("Calibri", Font.BOLD, 26));
		lbl_daNop.setBounds(10, 138, 252, 31);
		contentPane.add(lbl_daNop);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBounds(0, 216, 905, 171);
		contentPane.add(table);

		JLabel lbl_chuaNop = new JLabel("Danh sách chưa nộp tiền");
		lbl_chuaNop.setForeground(Color.BLUE);
		lbl_chuaNop.setFont(new Font("Calibri", Font.BOLD, 26));
		lbl_chuaNop.setBounds(10, 435, 271, 31);
		contentPane.add(lbl_chuaNop);

		table_1 = new JTable();
		table_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table_1.setBounds(0, 518, 905, 191);
		contentPane.add(table_1);

		JLabel jLable_maHD = new JLabel("Mã hoá đơn");
		jLable_maHD.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maHD.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maHD.setForeground(new Color(128, 0, 0));
		jLable_maHD.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_maHD.setBackground(Color.WHITE);
		jLable_maHD.setBounds(0, 179, 145, 38);
		contentPane.add(jLable_maHD);

		JLabel jLable_hoTen = new JLabel("Họ tên");
		jLable_hoTen.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_hoTen.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_hoTen.setForeground(new Color(128, 0, 0));
		jLable_hoTen.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_hoTen.setBackground(Color.WHITE);
		jLable_hoTen.setBounds(177, 179, 145, 38);
		contentPane.add(jLable_hoTen);

		JLabel jLable_loaiDien1 = new JLabel("Loại điện");
		jLable_loaiDien1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_loaiDien1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_loaiDien1.setForeground(new Color(128, 0, 0));
		jLable_loaiDien1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_loaiDien1.setBackground(Color.WHITE);
		jLable_loaiDien1.setBounds(360, 189, 139, 28);
		contentPane.add(jLable_loaiDien1);

		JLabel jLable_luongDien = new JLabel("LĐTT ");
		jLable_luongDien.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_luongDien.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_luongDien.setForeground(new Color(128, 0, 0));
		jLable_luongDien.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_luongDien.setBackground(Color.WHITE);
		jLable_luongDien.setBounds(542, 189, 86, 28);
		contentPane.add(jLable_luongDien);

		JLabel jLable_tien = new JLabel("Tiền");
		jLable_tien.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tien.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tien.setForeground(new Color(128, 0, 0));
		jLable_tien.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_tien.setBackground(Color.WHITE);
		jLable_tien.setBounds(719, 189, 86, 28);
		contentPane.add(jLable_tien);

		JLabel jLable_maHD_1 = new JLabel("Mã hoá đơn");
		jLable_maHD_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maHD_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maHD_1.setForeground(new Color(128, 0, 0));
		jLable_maHD_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_maHD_1.setBackground(Color.WHITE);
		jLable_maHD_1.setBounds(0, 481, 145, 38);
		contentPane.add(jLable_maHD_1);

		JLabel jLable_hoTen_1 = new JLabel("Họ tên");
		jLable_hoTen_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_hoTen_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_hoTen_1.setForeground(new Color(128, 0, 0));
		jLable_hoTen_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_hoTen_1.setBackground(Color.WHITE);
		jLable_hoTen_1.setBounds(177, 481, 145, 38);
		contentPane.add(jLable_hoTen_1);

		JLabel jLable_loaiDien1_1 = new JLabel("Loại điện");
		jLable_loaiDien1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_loaiDien1_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_loaiDien1_1.setForeground(new Color(128, 0, 0));
		jLable_loaiDien1_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_loaiDien1_1.setBackground(Color.WHITE);
		jLable_loaiDien1_1.setBounds(360, 491, 139, 28);
		contentPane.add(jLable_loaiDien1_1);

		JLabel jLable_luongDien_1 = new JLabel("LĐTT ");
		jLable_luongDien_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_luongDien_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_luongDien_1.setForeground(new Color(128, 0, 0));
		jLable_luongDien_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_luongDien_1.setBackground(Color.WHITE);
		jLable_luongDien_1.setBounds(542, 491, 86, 28);
		contentPane.add(jLable_luongDien_1);

		JLabel jLable_tien_1 = new JLabel("Tiền");
		jLable_tien_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tien_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tien_1.setForeground(new Color(128, 0, 0));
		jLable_tien_1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_tien_1.setBackground(Color.WHITE);
		jLable_tien_1.setBounds(719, 491, 86, 28);
		contentPane.add(jLable_tien_1);

	}
}
