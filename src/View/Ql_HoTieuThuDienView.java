package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Ql_HoTieuThuDienController;
import DAO.DBConnect;
import Model.HoTieuThuDien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JComboBox;
import javax.swing.JTable;
import java.awt.event.ActionEvent;

public class Ql_HoTieuThuDienView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_MaKH;
	private JTextField txt_tenKH;
	private JTextField txt_CCCD;
	private JTextField txt_diaChi;
	private JTextField txt_ngaySinh;
	private JTextField txt_SDT;
	private JTextField txt_ngayDKy;
	private JTable table;
	private HoTieuThuDien model;

	Object[] column = { "Mã khách hàng", "Họ tên", "CMND", "Địa chỉ", "Giới tính", "Ngày sinh", "SĐT", "Ngày đăng ký",
			"Loại Điện" };
	final DefaultTableModel tb_HTT = new DefaultTableModel(column, 0);

	DBConnect cn = new DBConnect();
	Connection connection;
	private JRadioButton jRadioButton_Nam;
	private JRadioButton jRadioButton_Nu;
	private JComboBox jcomboBox_loaiDien;

	public void loadBang() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM HoTieuThu";
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

			table.setModel(tb_HTT);
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Load dữ liệu lên các textfield
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					// Vì dữ liệu có thể là số nên cần phải + "" để nó hiểu là chuỗi
					txt_MaKH.setText(table.getValueAt(table.getSelectedRow(), 0) + "");
					txt_tenKH.setText(table.getValueAt(table.getSelectedRow(), 1) + "");
					txt_CCCD.setText(table.getValueAt(table.getSelectedRow(), 2) + "");
					txt_diaChi.setText(table.getValueAt(table.getSelectedRow(), 3) + "");

					String gioiTinh = table.getValueAt(table.getSelectedRow(), 4).toString();
					if (gioiTinh.equals("Nam")) {
						jRadioButton_Nam.setSelected(true);
					} else {
						jRadioButton_Nu.setSelected(true);
					}

					txt_ngaySinh.setText(table.getValueAt(table.getSelectedRow(), 5) + "");
					txt_SDT.setText(table.getValueAt(table.getSelectedRow(), 6) + "");
					txt_ngayDKy.setText(table.getValueAt(table.getSelectedRow(), 7) + "");

					String loaiDien = table.getValueAt(table.getSelectedRow(), 8).toString();
					if (loaiDien.equals("Hành chính")) {
						jcomboBox_loaiDien.setSelectedIndex(0);
					} else if (loaiDien.equals("Sinh hoạt")) {
						jcomboBox_loaiDien.setSelectedIndex(1);
					} else if (loaiDien.equals("Kinh doanh")) {
						jcomboBox_loaiDien.setSelectedIndex(2);
					} else if (loaiDien.equals("Sản xuất")) {
						jcomboBox_loaiDien.setSelectedIndex(3);
					}
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Ql_HoTieuThuDienView frame = new Ql_HoTieuThuDienView();
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.loadBang();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ql_HoTieuThuDienView() {
		this.model = new HoTieuThuDien();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1424, 734);

		ActionListener actionListener = new Ql_HoTieuThuDienController(this);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_QLHTTD = new JLabel("Quản lý hộ tiêu thụ điện");
		jLable_QLHTTD.setForeground(new Color(255, 255, 255));
		jLable_QLHTTD.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_QLHTTD.setFont(new Font("Calibri", Font.BOLD, 36));
		jLable_QLHTTD.setBackground(new Color(0, 0, 255));
		jLable_QLHTTD.setOpaque(true);
		jLable_QLHTTD.setBounds(0, 0, 1410, 113);
		contentPane.add(jLable_QLHTTD);

		JLabel jLable_maKH = new JLabel("Mã khách hàng:");
		jLable_maKH.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_maKH.setForeground(Color.BLACK);
		jLable_maKH.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_maKH.setBackground(Color.LIGHT_GRAY);
		jLable_maKH.setBounds(44, 140, 184, 38);
		contentPane.add(jLable_maKH);

		JLabel jLable_tenKH = new JLabel("Tên khách hàng:");
		jLable_tenKH.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_tenKH.setForeground(Color.BLACK);
		jLable_tenKH.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_tenKH.setBackground(Color.LIGHT_GRAY);
		jLable_tenKH.setBounds(44, 199, 184, 38);
		contentPane.add(jLable_tenKH);

		JLabel jLable_CMND = new JLabel("CMND/CCCD:");
		jLable_CMND.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_CMND.setForeground(Color.BLACK);
		jLable_CMND.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_CMND.setBackground(Color.LIGHT_GRAY);
		jLable_CMND.setBounds(44, 257, 184, 38);
		contentPane.add(jLable_CMND);

		JLabel jLable_diaChi = new JLabel("Địa chỉ:");
		jLable_diaChi.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_diaChi.setForeground(Color.BLACK);
		jLable_diaChi.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_diaChi.setBackground(Color.LIGHT_GRAY);
		jLable_diaChi.setBounds(107, 316, 121, 38);
		contentPane.add(jLable_diaChi);

		txt_MaKH = new JTextField();
		txt_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_MaKH.setColumns(10);
		txt_MaKH.setBounds(260, 142, 193, 28);
		contentPane.add(txt_MaKH);

		txt_tenKH = new JTextField();
		txt_tenKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_tenKH.setColumns(10);
		txt_tenKH.setBounds(260, 201, 193, 28);
		contentPane.add(txt_tenKH);

		txt_CCCD = new JTextField();
		txt_CCCD.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_CCCD.setColumns(10);
		txt_CCCD.setBounds(260, 259, 193, 28);
		contentPane.add(txt_CCCD);

		txt_diaChi = new JTextField();
		txt_diaChi.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_diaChi.setColumns(10);
		txt_diaChi.setBounds(260, 318, 193, 28);
		contentPane.add(txt_diaChi);

		JLabel jLable_gioiTinh = new JLabel("Giới tính:");
		jLable_gioiTinh.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_gioiTinh.setForeground(Color.BLACK);
		jLable_gioiTinh.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_gioiTinh.setBackground(Color.LIGHT_GRAY);
		jLable_gioiTinh.setBounds(629, 140, 115, 38);
		contentPane.add(jLable_gioiTinh);

		JLabel jLable_ngaySinh = new JLabel("Ngày sinh:");
		jLable_ngaySinh.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_ngaySinh.setForeground(Color.BLACK);
		jLable_ngaySinh.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_ngaySinh.setBackground(Color.LIGHT_GRAY);
		jLable_ngaySinh.setBounds(617, 199, 127, 38);
		contentPane.add(jLable_ngaySinh);

		JLabel jLable_sdt = new JLabel("Số điện thoại:");
		jLable_sdt.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_sdt.setForeground(Color.BLACK);
		jLable_sdt.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_sdt.setBackground(Color.LIGHT_GRAY);
		jLable_sdt.setBounds(590, 257, 154, 38);
		contentPane.add(jLable_sdt);

		JLabel jLable_loaiDien = new JLabel("Loại điện:");
		jLable_loaiDien.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_loaiDien.setForeground(Color.BLACK);
		jLable_loaiDien.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_loaiDien.setBackground(Color.LIGHT_GRAY);
		jLable_loaiDien.setBounds(617, 316, 127, 38);
		contentPane.add(jLable_loaiDien);

		txt_ngaySinh = new JTextField();
		txt_ngaySinh.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_ngaySinh.setColumns(10);
		txt_ngaySinh.setBounds(780, 201, 146, 28);
		contentPane.add(txt_ngaySinh);

		txt_SDT = new JTextField();
		txt_SDT.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_SDT.setColumns(10);
		txt_SDT.setBounds(780, 259, 193, 28);
		contentPane.add(txt_SDT);

		JLabel jLable_ngayDK = new JLabel("Ngày đăng ký:");
		jLable_ngayDK.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_ngayDK.setForeground(Color.BLACK);
		jLable_ngayDK.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_ngayDK.setBackground(Color.LIGHT_GRAY);
		jLable_ngayDK.setBounds(991, 199, 175, 38);
		contentPane.add(jLable_ngayDK);

		txt_ngayDKy = new JTextField();
		txt_ngayDKy.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_ngayDKy.setColumns(10);
		txt_ngayDKy.setBounds(1196, 201, 146, 28);
		contentPane.add(txt_ngayDKy);

		JButton jButton_Them = new JButton("Thêm");
		jButton_Them.setOpaque(true);
		jButton_Them.setBorderPainted(false);
		jButton_Them.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Them.setBackground(Color.CYAN);
		jButton_Them.setBounds(171, 370, 127, 38);
		jButton_Them.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Add.png"))));
		jButton_Them.addActionListener(actionListener);
		contentPane.add(jButton_Them);

		JButton jButton_Sua = new JButton("Sửa");
		jButton_Sua.setOpaque(true);
		jButton_Sua.setBorderPainted(false);
		jButton_Sua.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Sua.setBackground(new Color(255, 215, 0));
		jButton_Sua.setBounds(384, 370, 127, 38);
		jButton_Sua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Edit.png"))));
		jButton_Sua.addActionListener(actionListener);
		contentPane.add(jButton_Sua);

		JButton jButton_Xoa = new JButton("Xoá");
		jButton_Xoa.setOpaque(true);
		jButton_Xoa.setBorderPainted(false);
		jButton_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Xoa.setBackground(Color.GREEN);
		jButton_Xoa.setBounds(602, 370, 127, 38);
		jButton_Xoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Delete.png"))));
		jButton_Xoa.addActionListener(actionListener);
		contentPane.add(jButton_Xoa);

		JButton jButton_Reset = new JButton("Reset");
		jButton_Reset.setOpaque(true);
		jButton_Reset.setBorderPainted(false);
		jButton_Reset.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Reset.setBackground(new Color(220, 20, 60));
		jButton_Reset.setBounds(820, 370, 127, 38);
		jButton_Reset.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Refresh.png"))));
		jButton_Reset.addActionListener(actionListener);
		contentPane.add(jButton_Reset);

		JButton jButton_Dong = new JButton("Đóng");
		jButton_Dong.setOpaque(true);
		jButton_Dong.setBorderPainted(false);
		jButton_Dong.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Dong.setBounds(1263, 370, 123, 38);
		jButton_Dong.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Exit.png"))));
		jButton_Dong.addActionListener(actionListener);
		contentPane.add(jButton_Dong);

		jRadioButton_Nam = new JRadioButton("Nam");
		jRadioButton_Nam.setBackground(new Color(192, 192, 192));
		jRadioButton_Nam.setHorizontalAlignment(SwingConstants.CENTER);
		jRadioButton_Nam.setFont(new Font("Cambria", Font.BOLD, 22));
		jRadioButton_Nam.setBounds(768, 144, 84, 21);
		contentPane.add(jRadioButton_Nam);

		jRadioButton_Nu = new JRadioButton("Nữ");
		jRadioButton_Nu.setBackground(new Color(192, 192, 192));
		jRadioButton_Nu.setHorizontalAlignment(SwingConstants.CENTER);
		jRadioButton_Nu.setFont(new Font("Cambria", Font.BOLD, 22));
		jRadioButton_Nu.setBounds(864, 144, 72, 21);
		contentPane.add(jRadioButton_Nu);

		ButtonGroup buttonGroup_gioiTinh = new ButtonGroup();
		buttonGroup_gioiTinh.add(jRadioButton_Nam);
		buttonGroup_gioiTinh.add(jRadioButton_Nu);

		String[] loaiDien = new String[] { "Hành chính", "Sinh hoạt", "Kinh doanh", "Sản xuất" };
		jcomboBox_loaiDien = new JComboBox(loaiDien);
		jcomboBox_loaiDien.setFont(new Font("Cambria", Font.BOLD, 22));
		jcomboBox_loaiDien.setBounds(780, 316, 146, 29);
		contentPane.add(jcomboBox_loaiDien);

		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = table.getSelectedRow();
				if (x >= 0) {
					txt_MaKH.setText(table.getValueAt(x, 0) + "");
					txt_tenKH.setText(table.getValueAt(x, 1) + "");
					txt_CCCD.setText(table.getValueAt(x, 2) + "");
					txt_diaChi.setText(table.getValueAt(x, 3) + "");

					String gioiTinh = table.getValueAt(x, 4).toString();
					if (gioiTinh.equals("Nam")) {
						jRadioButton_Nam.setSelected(true);
					} else {
						jRadioButton_Nu.setSelected(true);
					}
					txt_ngaySinh.setText(table.getValueAt(x, 5) + "");
					txt_SDT.setText(table.getValueAt(x, 6) + "");
					txt_ngayDKy.setText(table.getValueAt(x, 7) + "");

					String loaiDien = table.getValueAt(x, 8).toString();
					if (loaiDien.equals("Hành chính")) {
						jcomboBox_loaiDien.setSelectedIndex(0);
					} else if (loaiDien.equals("Sinh hoạt")) {
						jcomboBox_loaiDien.setSelectedIndex(1);
					} else if (loaiDien.equals("Kinh doanh")) {
						jcomboBox_loaiDien.setSelectedIndex(2);
					} else if (loaiDien.equals("Sản xuất")) {
						jcomboBox_loaiDien.setSelectedIndex(3);
					}
					// Mã KH là khoá chính nên ko thể sửa
					txt_MaKH.setEnabled(false);
				}
			}
		});
		table.setFont(new Font("Tahoma", Font.PLAIN, 16));
		table.setBounds(0, 475, 1410, 220);
		contentPane.add(table);

		JLabel jLable_maKH_1 = new JLabel("Mã khách hàng");
		jLable_maKH_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maKH_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maKH_1.setForeground(new Color(128, 0, 0));
		jLable_maKH_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_maKH_1.setBackground(Color.WHITE);
		jLable_maKH_1.setBounds(0, 446, 139, 28);
		contentPane.add(jLable_maKH_1);

		JLabel jLable_tenKH1 = new JLabel("Tên khách hàng");
		jLable_tenKH1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tenKH1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tenKH1.setForeground(new Color(128, 0, 0));
		jLable_tenKH1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_tenKH1.setBackground(Color.WHITE);
		jLable_tenKH1.setBounds(155, 446, 146, 28);
		contentPane.add(jLable_tenKH1);

		JLabel jLable_CMND1 = new JLabel("CMND/CCCD");
		jLable_CMND1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_CMND1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_CMND1.setForeground(new Color(128, 0, 0));
		jLable_CMND1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_CMND1.setBackground(Color.WHITE);
		jLable_CMND1.setBounds(316, 446, 123, 28);
		contentPane.add(jLable_CMND1);

		JLabel jLable_diaChi1 = new JLabel("Địa chỉ");
		jLable_diaChi1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_diaChi1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_diaChi1.setForeground(new Color(128, 0, 0));
		jLable_diaChi1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_diaChi1.setBackground(Color.WHITE);
		jLable_diaChi1.setBounds(473, 446, 95, 28);
		contentPane.add(jLable_diaChi1);

		JLabel jLable_gioiTinh1 = new JLabel("Giới tính");
		jLable_gioiTinh1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_gioiTinh1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_gioiTinh1.setForeground(new Color(128, 0, 0));
		jLable_gioiTinh1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_gioiTinh1.setBackground(Color.WHITE);
		jLable_gioiTinh1.setBounds(629, 446, 139, 28);
		contentPane.add(jLable_gioiTinh1);

		JLabel jLable_ngaySinh1_1 = new JLabel("Ngày sinh");
		jLable_ngaySinh1_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_ngaySinh1_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_ngaySinh1_1.setForeground(new Color(128, 0, 0));
		jLable_ngaySinh1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_ngaySinh1_1.setBackground(Color.WHITE);
		jLable_ngaySinh1_1.setBounds(787, 446, 139, 28);
		contentPane.add(jLable_ngaySinh1_1);

		JLabel jLable_sdt1 = new JLabel("Số điện thoại");
		jLable_sdt1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_sdt1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_sdt1.setForeground(new Color(128, 0, 0));
		jLable_sdt1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_sdt1.setBackground(Color.WHITE);
		jLable_sdt1.setBounds(946, 446, 139, 28);
		contentPane.add(jLable_sdt1);

		JLabel jLable_ngayDK1 = new JLabel("Ngày đăng ký");
		jLable_ngayDK1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_ngayDK1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_ngayDK1.setForeground(new Color(128, 0, 0));
		jLable_ngayDK1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_ngayDK1.setBackground(Color.WHITE);
		jLable_ngayDK1.setBounds(1095, 446, 139, 28);
		contentPane.add(jLable_ngayDK1);

		JLabel jLable_loaiDien1 = new JLabel("Loại điện");
		jLable_loaiDien1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_loaiDien1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_loaiDien1.setForeground(new Color(128, 0, 0));
		jLable_loaiDien1.setFont(new Font("Tahoma", Font.PLAIN, 19));
		jLable_loaiDien1.setBackground(Color.WHITE);
		jLable_loaiDien1.setBounds(1261, 446, 139, 28);
		contentPane.add(jLable_loaiDien1);

		JButton jButton_timKiem = new JButton("Tìm kiếm");
		jButton_timKiem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TimKiemView tk = new TimKiemView();
				tk.setVisible(true);
				tk.setLocationRelativeTo(null);
			}
		});
		jButton_timKiem.setOpaque(true);
		jButton_timKiem.setBorderPainted(false);
		jButton_timKiem.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_timKiem.setBorderPainted(false);
		jButton_timKiem.setBackground(new Color(34, 139, 34));
		jButton_timKiem.setBounds(1039, 370, 139, 38);
		contentPane.add(jButton_timKiem);
	}

	public void xoaTrang() {
		txt_MaKH.setText("");
		txt_tenKH.setText("");
		txt_CCCD.setText("");
		txt_diaChi.setText("");
		txt_ngaySinh.setText("");
		txt_SDT.setText("");
		txt_ngayDKy.setText("");
		jcomboBox_loaiDien.setSelectedIndex(0);
		txt_MaKH.setEnabled(true);
	}

	public void reset() {
		connection = cn.connect();
		try {
			xoaTrang();
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add() {
		connection = cn.connect();
		try {
			if (txt_MaKH.getText().equals("") || txt_tenKH.getText().equals("") || txt_CCCD.getText().equals("")
					|| txt_diaChi.getText().equals("")
					|| (!jRadioButton_Nam.isSelected() && !jRadioButton_Nu.isSelected())
					|| txt_ngaySinh.getText().equals("") || txt_SDT.getText().equals("")
					|| txt_ngayDKy.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				StringBuffer sb = new StringBuffer();
				String sql_check_pk = "SELECT MaKH FROM HoTieuThu WHERE MaKH = '" + txt_MaKH.getText() + "'";
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql_check_pk);

				if (rs.next()) {
					sb.append("Khách hàng này đã tồn tại!");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(this, sb.toString());
				} else {
					String gioiTinh;
					if (jRadioButton_Nam.isSelected()) {
						gioiTinh = "Nam";
					} else {
						gioiTinh = "Nữ";
					}

					String loaiDien = jcomboBox_loaiDien.getSelectedItem().toString();
					String sql = "Insert into HoTieuThu values(N'" + txt_MaKH.getText() + "', N'" + txt_tenKH.getText()
							+ "', N'" + txt_CCCD.getText() + "', N'" + txt_diaChi.getText() + "', N'" + gioiTinh
							+ "', N'" + txt_ngaySinh.getText() + "', '" + txt_SDT.getText() + "', '"
							+ txt_ngayDKy.getText() + "', N'" + loaiDien + "')";
					st = connection.createStatement();
					int kq = st.executeUpdate(sql);

					if (kq > 0) {
						JOptionPane.showMessageDialog(this, "Thêm mới thành công!");
						xoaTrang();
						loadBang();
					}
				}
				st.close();
				rs.close();
			}
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sua() {
		connection = cn.connect();
		try {
			if (txt_MaKH.getText().equals("") || txt_tenKH.getText().equals("") || txt_CCCD.getText().equals("")
					|| txt_diaChi.getText().equals("")
					|| (!jRadioButton_Nam.isSelected() && !jRadioButton_Nu.isSelected())
					|| txt_ngaySinh.getText().equals("") || txt_SDT.getText().equals("")
					|| txt_ngayDKy.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				String gioiTinh;
				if (jRadioButton_Nam.isSelected()) {
					gioiTinh = "Nam";
				} else {
					gioiTinh = "Nữ";
				}

				String loaiDien = jcomboBox_loaiDien.getSelectedItem().toString();
				Statement st = connection.createStatement();
				String sql = "Update HoTieuThu set MaKH ='" + txt_MaKH.getText() + "', HoTen = N'" + txt_tenKH.getText()
						+ "', CMND ='" + txt_CCCD.getText() + "', DiaChi = N'" + txt_diaChi.getText()
						+ "', GioiTinh = N'" + gioiTinh + "', NgaySinh ='" + txt_ngaySinh.getText() + "', SDT ='"
						+ txt_SDT.getText() + "', NgayDangKy ='" + txt_ngayDKy.getText() + "', LoaiDien = N'" + loaiDien
						+ "' where MaKH ='" + txt_MaKH.getText() + "'";
				int kq = st.executeUpdate(sql);
				if (kq > 0) {
					JOptionPane.showMessageDialog(this, "Cập nhật thành công!");
					xoaTrang();
					loadBang();
				}
				st.close();
			}
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void xoa() {
		connection = cn.connect();
		try {
			String sql = "Delete HoTieuThu where MaKH = '" + txt_MaKH.getText() + "'";
			Statement st = connection.createStatement();
			int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (check == JOptionPane.YES_OPTION) {
				xoaHoaDon();
				st.executeUpdate(sql);
				xoaTrang();
				loadBang();
				JOptionPane.showMessageDialog(this, "Xoá thành công!");
			}
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void xoaHoaDon() {
		connection = cn.connect();
		try {
			QuanLyHoaDon.xoaHoaDonKhoiDS(txt_MaKH.getText());
			String sqlHD = "Delete HoaDon where MaKH = '" + txt_MaKH.getText() + "'";
			Statement st = connection.createStatement();
			st.executeUpdate(sqlHD);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void dong() {
		this.setVisible(false);
	}

}
