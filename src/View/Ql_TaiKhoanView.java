package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.Ql_TaiKhoanController;
import DAO.DBConnect;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
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
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;

public class Ql_TaiKhoanView extends JFrame {

	private JPanel contentPane;
	private JTextField txt_TK;
	private JTextField txt_Mk;
	private JTable table;
	Object[] column = { "Tên tài khoản", "Mật khẩu" };
	final DefaultTableModel tb = new DefaultTableModel(column, 0);

	DBConnect cn = new DBConnect();
	Connection connection;

	public void loadBang() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM QuanLyTaiKhoan";
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData metadata = rs.getMetaData();
			number = metadata.getColumnCount();
			tb.setRowCount(0);

			while (rs.next()) {
				row = new Vector();
				for (int i = 1; i <= number; i++) {
					row.addElement(rs.getString(i));
				}
				tb.addRow(row);
			}

			table.setModel(tb);
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (table.getSelectedRow() >= 0) {
					txt_TK.setText(table.getValueAt(table.getSelectedRow(), 0) + "");
					txt_Mk.setText(table.getValueAt(table.getSelectedRow(), 1) + "");

				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					Ql_TaiKhoanView frame = new Ql_TaiKhoanView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.loadBang();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Ql_TaiKhoanView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 798, 572);

		ActionListener actionListener = new Ql_TaiKhoanController(this);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Quản lý tài khoản");
		lblNewLabel.setBackground(new Color(0, 0, 255));
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 784, 100);
		contentPane.add(lblNewLabel);

		JLabel jLable_tenTK = new JLabel("Tên tài khoản:");
		jLable_tenTK.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_tenTK.setForeground(Color.BLACK);
		jLable_tenTK.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_tenTK.setBackground(Color.LIGHT_GRAY);
		jLable_tenTK.setBounds(140, 150, 184, 38);
		contentPane.add(jLable_tenTK);

		JLabel jLable_matKhau = new JLabel("Mật khẩu:");
		jLable_matKhau.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_matKhau.setForeground(Color.BLACK);
		jLable_matKhau.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_matKhau.setBackground(Color.LIGHT_GRAY);
		jLable_matKhau.setBounds(140, 219, 184, 38);
		contentPane.add(jLable_matKhau);

		txt_TK = new JTextField();
		txt_TK.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_TK.setColumns(10);
		txt_TK.setBounds(338, 152, 193, 28);
		contentPane.add(txt_TK);

		txt_Mk = new JTextField();
		txt_Mk.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Mk.setColumns(10);
		txt_Mk.setBounds(338, 221, 193, 28);
		contentPane.add(txt_Mk);

		JButton jButton_Them = new JButton("Thêm");
		jButton_Them.setOpaque(true);
		jButton_Them.setBorderPainted(false);
		jButton_Them.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Them.setBackground(Color.CYAN);
		jButton_Them.setBounds(462, 343, 127, 38);
		jButton_Them.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_TaiKhoanView.class.getResource("Icon/Add.png"))));
		jButton_Them.addActionListener(actionListener);
		contentPane.add(jButton_Them);

		JButton jButton_Sua = new JButton("Sửa");
		jButton_Sua.setOpaque(true);
		jButton_Sua.setBorderPainted(false);
		jButton_Sua.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Sua.setBackground(new Color(255, 215, 0));
		jButton_Sua.setBounds(635, 343, 127, 38);
		jButton_Sua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_TaiKhoanView.class.getResource("Icon/Edit.png"))));
		jButton_Sua.addActionListener(actionListener);
		contentPane.add(jButton_Sua);

		JButton jButton_Xoa = new JButton("Xoá");
		jButton_Xoa.setOpaque(true);
		jButton_Xoa.setBorderPainted(false);
		jButton_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Xoa.setBackground(Color.GREEN);
		jButton_Xoa.setBounds(462, 441, 127, 38);
		jButton_Xoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_TaiKhoanView.class.getResource("Icon/Delete.png"))));
		jButton_Xoa.addActionListener(actionListener);
		contentPane.add(jButton_Xoa);

		JButton jButton_Reset = new JButton("Reset");
		jButton_Reset.setOpaque(true);
		jButton_Reset.setBorderPainted(false);
		jButton_Reset.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Reset.setBackground(new Color(220, 20, 60));
		jButton_Reset.setBounds(635, 441, 127, 38);
		jButton_Reset.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_TaiKhoanView.class.getResource("Icon/Refresh.png"))));
		jButton_Reset.addActionListener(actionListener);
		jButton_Reset.addActionListener(actionListener);
		contentPane.add(jButton_Reset);

		table = new JTable();
		table.setFont(new Font("Tahoma", Font.PLAIN, 18));
		table.setBounds(0, 321, 452, 218);
		contentPane.add(table);

		JLabel jLable_tk = new JLabel("Tài khoản");
		jLable_tk.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_tk.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_tk.setForeground(new Color(128, 0, 0));
		jLable_tk.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_tk.setBackground(Color.WHITE);
		jLable_tk.setBounds(0, 284, 111, 38);
		contentPane.add(jLable_tk);

		JLabel jLable_mk = new JLabel("Mật khẩu");
		jLable_mk.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_mk.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_mk.setForeground(new Color(128, 0, 0));
		jLable_mk.setFont(new Font("Tahoma", Font.PLAIN, 22));
		jLable_mk.setBackground(Color.WHITE);
		jLable_mk.setBounds(225, 284, 111, 38);
		contentPane.add(jLable_mk);
	}

	public void xoaTrang() {
		txt_TK.setText("");
		txt_Mk.setText("");
	}

	public void add() {
		connection = cn.connect();
		try {
			if (txt_TK.getText().equals("") || txt_Mk.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				StringBuffer sb = new StringBuffer();
				String sql_check_pk = "SELECT TaiKhoan FROM QuanLyTaiKhoan WHERE TaiKhoan = '" + txt_TK.getText() + "'";
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql_check_pk);

				if (rs.next()) {
					sb.append("Tài khoản này đã tồn tại!");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(this, sb.toString());
				} else {
					String sql = "Insert into QuanLyTaiKhoan values('" + txt_TK.getText() + "', '" + txt_Mk.getText()
							+ "')";
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

	public void reset() {
		connection = cn.connect();
		try {
			xoaTrang();
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sua() {
		connection = cn.connect();
		try {
			if (txt_TK.getText().equals("") || txt_Mk.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				Statement st = connection.createStatement();
				String sql = "Update QuanLyTaiKhoan set TaiKhoan ='" + txt_TK.getText() + "', MatKhau ='"
						+ txt_Mk.getText() + "' where TaiKhoan ='" + txt_TK.getText() + "'";
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
			String sql = "Delete QuanLyTaiKhoan where TaiKhoan = '" + txt_TK.getText() + "'";
			Statement st = connection.createStatement();
			int check = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn muốn xoá?", "Thông báo",
					JOptionPane.YES_NO_OPTION);
			if (check == JOptionPane.YES_OPTION) {
				st.executeUpdate(sql);
				JOptionPane.showMessageDialog(this, "Xoá thành công!");
				xoaTrang();
				loadBang();
			}
			cn.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
