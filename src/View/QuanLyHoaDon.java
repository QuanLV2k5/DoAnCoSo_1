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
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import DAO.DBConnect;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.print.PrinterException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;
import javax.swing.JTextArea;

public class QuanLyHoaDon extends JFrame {

	private JPanel contentPane;
	private JTable table;
	Object[] column = { "Mã khách hàng", "Họ tên", "Loại Điện", "LĐTT", "Tiền" };
	final DefaultTableModel tb_HTT = new DefaultTableModel(column, 0);

	static DBConnect cn = new DBConnect();
	static Connection connection;
	private JTextArea textArea;

	public void loadBang() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM HoaDon";
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

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					textArea.setText("Họ Tên KH: " + table.getValueAt(table.getSelectedRow(), 1) + "\n" + "\n"
							+ "Mã hoá đơn: " + table.getValueAt(table.getSelectedRow(), 0) + "\n" + "\n"
							+ "Số lượng điện tiêu thụ: " + table.getValueAt(table.getSelectedRow(), 3) + " kWh" + "\n"
							+ "\n" + "Tổng tiền: " + table.getValueAt(table.getSelectedRow(), 4) + " VNĐ");
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					QuanLyHoaDon frame = new QuanLyHoaDon();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					frame.loadBang();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QuanLyHoaDon() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 825, 759);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_QLHD = new JLabel("Quản lý hoá đơn/Thanh toán");
		jLable_QLHD.setOpaque(true);
		jLable_QLHD.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_QLHD.setForeground(Color.WHITE);
		jLable_QLHD.setFont(new Font("Calibri", Font.BOLD, 42));
		jLable_QLHD.setBackground(Color.BLUE);
		jLable_QLHD.setBounds(0, 0, 810, 118);
		contentPane.add(jLable_QLHD);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		table.setBounds(0, 177, 810, 238);
		contentPane.add(table);

		JLabel jLable_maHD = new JLabel("Mã hoá đơn");
		jLable_maHD.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maHD.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maHD.setForeground(new Color(128, 0, 0));
		jLable_maHD.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_maHD.setBackground(Color.WHITE);
		jLable_maHD.setBounds(0, 140, 145, 38);
		contentPane.add(jLable_maHD);

		JLabel jLable_hoTen = new JLabel("Họ tên");
		jLable_hoTen.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_hoTen.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_hoTen.setForeground(new Color(128, 0, 0));
		jLable_hoTen.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_hoTen.setBackground(Color.WHITE);
		jLable_hoTen.setBounds(160, 140, 145, 38);
		contentPane.add(jLable_hoTen);

		JLabel jLable_loaiDien1 = new JLabel("Loại điện");
		jLable_loaiDien1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_loaiDien1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_loaiDien1.setForeground(new Color(128, 0, 0));
		jLable_loaiDien1.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_loaiDien1.setBackground(Color.WHITE);
		jLable_loaiDien1.setBounds(322, 150, 139, 28);
		contentPane.add(jLable_loaiDien1);

		JLabel jLable_luongDien = new JLabel("LĐTT ");
		jLable_luongDien.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_luongDien.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_luongDien.setForeground(new Color(128, 0, 0));
		jLable_luongDien.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_luongDien.setBackground(Color.WHITE);
		jLable_luongDien.setBounds(483, 150, 86, 28);
		contentPane.add(jLable_luongDien);

		JLabel jLable_tien = new JLabel("Tiền");
		jLable_tien.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tien.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tien.setForeground(new Color(128, 0, 0));
		jLable_tien.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_tien.setBackground(Color.WHITE);
		jLable_tien.setBounds(643, 150, 86, 28);
		contentPane.add(jLable_tien);

		JPanel panel = new JPanel();
		panel.setBounds(0, 472, 380, 249);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel jLable_thanhToan = new JLabel("Thanh toán");
		jLable_thanhToan.setOpaque(true);
		jLable_thanhToan.setBounds(0, 0, 380, 29);
		jLable_thanhToan.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_thanhToan.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_thanhToan.setForeground(new Color(128, 0, 0));
		jLable_thanhToan.setFont(new Font("Tahoma", Font.BOLD, 20));
		jLable_thanhToan.setBackground(new Color(0, 128, 255));
		panel.add(jLable_thanhToan);

		textArea = new JTextArea();
		textArea.setBounds(10, 39, 370, 200);
		panel.add(textArea);
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 18));

		JButton jButton_thanhToan = new JButton("Thanh Toán");
		jButton_thanhToan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					if (textArea.getText().equals("")) {
						JOptionPane.showMessageDialog(contentPane, "Bạn cần chọn khách hàng muốn thanh toán!");
					} else {
						add_DSĐN();
						delete_DSCN();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		jButton_thanhToan.setOpaque(true);
		jButton_thanhToan.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jButton_thanhToan.setBorderPainted(false);
		jButton_thanhToan.setBackground(new Color(0, 128, 255));
		jButton_thanhToan.setBounds(435, 570, 215, 38);
		jButton_thanhToan.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyHoaDon.class.getResource("Icon/Price list.png"))));
		contentPane.add(jButton_thanhToan);

		JButton jButton_print = new JButton("In hoá đơn");
		jButton_print.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					textArea.print();
				} catch (PrinterException e1) {
					e1.printStackTrace();
				}
			}
		});
		jButton_print.setOpaque(true);
		jButton_print.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jButton_print.setBorderPainted(false);
		jButton_print.setBackground(new Color(128, 128, 192));
		jButton_print.setBounds(435, 640, 215, 38);
		jButton_print.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyHoaDon.class.getResource("Icon/Print preview.png"))));
		contentPane.add(jButton_print);

		JButton jButton_themHoaDon = new JButton("Thêm hoá đơn");
		jButton_themHoaDon.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ThemHoaDonView themHoaDonView = new ThemHoaDonView();
				themHoaDonView.setVisible(true);
				themHoaDonView.setLocationRelativeTo(null);
				add_DSCN();
			}
		});
		jButton_themHoaDon.setOpaque(true);
		jButton_themHoaDon.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jButton_themHoaDon.setBorderPainted(false);
		jButton_themHoaDon.setBackground(new Color(0, 255, 255));
		jButton_themHoaDon.setBounds(435, 500, 215, 38);
		jButton_themHoaDon.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(QuanLyHoaDon.class.getResource("Icon/Add.png"))));
		contentPane.add(jButton_themHoaDon);

		JButton jButton_Xoa = new JButton("Xoá");
		jButton_Xoa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					String maHD = table.getValueAt(table.getSelectedRow(), 0).toString();
					String sql = "Delete HoaDon where MaHD = '" + maHD + "'";
					Statement st = connection.createStatement();
					int check = JOptionPane.showConfirmDialog(contentPane, "Bạn chắc chắn muốn xoá?", "Thông báo",
							JOptionPane.YES_NO_OPTION);
					if (check == JOptionPane.YES_OPTION) {
						st.executeUpdate(sql);
						JOptionPane.showMessageDialog(contentPane, "Xoá thành công!");
						loadBang();
					}
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		jButton_Xoa.setOpaque(true);
		jButton_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Xoa.setBorderPainted(false);
		jButton_Xoa.setBackground(Color.GREEN);
		jButton_Xoa.setBounds(683, 500, 127, 38);
		jButton_Xoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyHoaDon.class.getResource("Icon/Delete.png"))));
		contentPane.add(jButton_Xoa);

		JButton jButton_Reset = new JButton("Reset");
		jButton_Reset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connection = cn.connect();
				try {
					loadBang();
					cn.disconnect();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		jButton_Reset.setOpaque(true);
		jButton_Reset.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Reset.setBorderPainted(false);
		jButton_Reset.setBackground(new Color(220, 20, 60));
		jButton_Reset.setBounds(683, 570, 127, 38);
		jButton_Reset.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QuanLyHoaDon.class.getResource("Icon/Refresh.png"))));
		contentPane.add(jButton_Reset);
	}

	public void add_DSĐN() {
		connection = cn.connect();
		try {
			String maHD = table.getValueAt(table.getSelectedRow(), 0).toString();
			String hoTen = table.getValueAt(table.getSelectedRow(), 1).toString();
			String maKH = table.getValueAt(table.getSelectedRow(), 2).toString();
			String loaiDien = table.getValueAt(table.getSelectedRow(), 3).toString();
			String luongDien = table.getValueAt(table.getSelectedRow(), 4).toString();
			String tien = table.getValueAt(table.getSelectedRow(), 5).toString();

			StringBuffer sb = new StringBuffer();
			String sql_check_pk = "SELECT MaHD FROM DanhSachDaNop WHERE MaHD = '" + maHD + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql_check_pk);

			if (rs.next()) {
				sb.append("Khách hàng này đã thanh toán!");
			}
			if (sb.length() > 0) {
				JOptionPane.showMessageDialog(contentPane, sb.toString());
			} else {
				String sql = "Insert into DanhSachDaNop values('" + maHD + "', N'" + hoTen + "', '" + maKH + "', N'"
						+ loaiDien + "', " + luongDien + ", '" + tien + "');";
				st.executeUpdate(sql);

				JOptionPane.showMessageDialog(this, "Thanh toán thành công!");
			}
			st.close();
			rs.close();

			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void add_DSCN() {
		connection = cn.connect();
		try {
			String maHD = table.getValueAt(table.getSelectedRow(), 0).toString();
			String hoTen = table.getValueAt(table.getSelectedRow(), 1).toString();
			String maKH = table.getValueAt(table.getSelectedRow(), 2).toString();
			String loaiDien = table.getValueAt(table.getSelectedRow(), 3).toString();
			String luongDien = table.getValueAt(table.getSelectedRow(), 4).toString();
			String tien = table.getValueAt(table.getSelectedRow(), 5).toString();

			String sql = "Insert into DanhSachChuaNop values('" + maHD + "', N'" + hoTen + "', '" + maKH + "', N'"
					+ loaiDien + "', " + luongDien + ", '" + tien + "');";
			Statement st = connection.createStatement();
			st.executeUpdate(sql);

			st.close();
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void delete_DSCN() {
		connection = cn.connect();
		try {
			String maHD = table.getValueAt(table.getSelectedRow(), 0).toString();
			String sql = "Delete DanhSachChuaNop where MaHD = '" + maHD + "'";
			Statement st = connection.createStatement();
			st.executeUpdate(sql);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void xoaHoaDonKhoiDS(String maKhachHang) {
		connection = cn.connect();
		try {
			StringBuffer sb = new StringBuffer();
			String sqlCheckDSDN = "Select MaHD from DanhSachDaNop where MaKH = '" + maKhachHang + "'";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sqlCheckDSDN);

			if (rs.next()) {
				String sqlXoaHDDSDN = "Delete DanhSachDaNop where MaKH = '" + maKhachHang + "'";
				st.executeUpdate(sqlXoaHDDSDN);
			} else {
				String sqlXoaHDDSCN = "Delete DanhSachChuaNop where MaKH = '" + maKhachHang + "'";
				st.executeUpdate(sqlXoaHDDSCN);
			}
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
