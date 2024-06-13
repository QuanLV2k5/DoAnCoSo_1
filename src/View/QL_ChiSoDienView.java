package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import Controller.QL_ChiSoDienController;
import DAO.DBConnect;
import Model.ChiSoDien;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QL_ChiSoDienView extends JFrame {

	private JPanel contentPane;
	private JTextField txt_MaKH;
	private JTextField txt_Mathang;
	private JTextField txt_CSC;
	private JTextField txt_CSM;
	private ChiSoDien model;
	private JTable bangCSD;
	Object[] column = { "Mã khách hàng", "Mã tháng", "Chỉ số cũ", "Chỉ số mới" };
	final DefaultTableModel tb = new DefaultTableModel(column, 0);

	DBConnect cn = new DBConnect();
	Connection connection;

	public void loadBang() {
		try {
			connection = cn.connect();
			int number;
			Vector row;
			String sql = "SELECT * FROM ChiSoDien";
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

			bangCSD.setModel(tb);
			st.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Load dữ liệu lên các textfield
		bangCSD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (bangCSD.getSelectedRow() >= 0) {
					txt_MaKH.setText(bangCSD.getValueAt(bangCSD.getSelectedRow(), 0) + "");
					txt_Mathang.setText(bangCSD.getValueAt(bangCSD.getSelectedRow(), 1) + "");
					txt_CSC.setText(bangCSD.getValueAt(bangCSD.getSelectedRow(), 2) + "");
					txt_CSM.setText(bangCSD.getValueAt(bangCSD.getSelectedRow(), 3) + "");
				}
			}
		});
	}

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					QL_ChiSoDienView frame = new QL_ChiSoDienView();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					frame.loadBang();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public QL_ChiSoDienView() {
		this.model = new ChiSoDien();
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 987, 722);

		ActionListener acListener = new QL_ChiSoDienController(this);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_QLCSD = new JLabel("Quản lý chỉ số điện");
		jLable_QLCSD.setBounds(-13, 0, 1003, 118);
		jLable_QLCSD.setBackground(new Color(0, 0, 255));
		jLable_QLCSD.setOpaque(true);
		jLable_QLCSD.setForeground(new Color(255, 255, 255));
		jLable_QLCSD.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_QLCSD.setFont(new Font("Calibri", Font.BOLD, 42));
		contentPane.add(jLable_QLCSD);

		JLabel jLable_maKH = new JLabel("Mã khách hàng:");
		jLable_maKH.setBounds(73, 142, 184, 38);
		jLable_maKH.setForeground(new Color(0, 0, 0));
		jLable_maKH.setBackground(new Color(192, 192, 192));
		jLable_maKH.setFont(new Font("Calibri", Font.BOLD, 24));
		jLable_maKH.setHorizontalAlignment(SwingConstants.RIGHT);
		contentPane.add(jLable_maKH);

		JLabel jLable_maThang = new JLabel("Mã tháng:");
		jLable_maThang.setBounds(73, 207, 184, 38);
		jLable_maThang.setForeground(new Color(0, 0, 0));
		jLable_maThang.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_maThang.setFont(new Font("Calibri", Font.BOLD, 24));
		contentPane.add(jLable_maThang);

		JLabel jLable_chiSoCu = new JLabel("Chỉ số cũ:");
		jLable_chiSoCu.setBounds(73, 275, 184, 38);
		jLable_chiSoCu.setForeground(new Color(0, 0, 0));
		jLable_chiSoCu.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_chiSoCu.setFont(new Font("Calibri", Font.BOLD, 24));
		contentPane.add(jLable_chiSoCu);

		JLabel jLable_chiSoMoi = new JLabel("Chỉ số mới:");
		jLable_chiSoMoi.setBounds(73, 344, 184, 38);
		jLable_chiSoMoi.setForeground(new Color(0, 0, 0));
		jLable_chiSoMoi.setHorizontalAlignment(SwingConstants.RIGHT);
		jLable_chiSoMoi.setFont(new Font("Calibri", Font.BOLD, 24));
		contentPane.add(jLable_chiSoMoi);

		txt_MaKH = new JTextField();
		txt_MaKH.setBounds(284, 142, 193, 28);
		txt_MaKH.setFont(new Font("Tahoma", Font.PLAIN, 16));
		contentPane.add(txt_MaKH);
		txt_MaKH.setColumns(10);

		txt_Mathang = new JTextField();
		txt_Mathang.setBounds(284, 207, 193, 28);
		txt_Mathang.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_Mathang.setColumns(10);
		contentPane.add(txt_Mathang);

		txt_CSC = new JTextField();
		txt_CSC.setBounds(284, 275, 193, 28);
		txt_CSC.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_CSC.setColumns(10);
		contentPane.add(txt_CSC);

		txt_CSM = new JTextField();
		txt_CSM.setBounds(284, 346, 193, 28);
		txt_CSM.setFont(new Font("Tahoma", Font.PLAIN, 16));
		txt_CSM.setColumns(10);
		contentPane.add(txt_CSM);

		JButton jButton_Them = new JButton("Thêm");
		jButton_Them.setBounds(597, 200, 127, 38);
		jButton_Them.setOpaque(true);
		jButton_Them.setBorderPainted(false);
		jButton_Them.setBackground(new Color(0, 255, 255));
		jButton_Them.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Them.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QL_ChiSoDienView.class.getResource("Icon/Add.png"))));
		jButton_Them.addActionListener(acListener);
		contentPane.add(jButton_Them);

		JButton jButton_Sua = new JButton("Sửa");
		jButton_Sua.setOpaque(true);
		jButton_Sua.setBorderPainted(false);
		jButton_Sua.setBounds(783, 197, 127, 38);
		jButton_Sua.setBackground(new Color(255, 215, 0));
		jButton_Sua.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Sua.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QL_ChiSoDienView.class.getResource("Icon/Edit.png"))));
		jButton_Sua.addActionListener(acListener);
		contentPane.add(jButton_Sua);

		JButton jButton_Xoa = new JButton("Xoá");
		jButton_Xoa.setOpaque(true);
		jButton_Xoa.setBorderPainted(false);
		jButton_Xoa.setBounds(597, 289, 127, 38);
		jButton_Xoa.setBackground(new Color(0, 255, 0));
		jButton_Xoa.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Xoa.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QL_ChiSoDienView.class.getResource("Icon/Delete.png"))));
		jButton_Xoa.addActionListener(acListener);
		contentPane.add(jButton_Xoa);

		JButton jButton_Dong = new JButton("Đóng");
		jButton_Dong.setOpaque(true);
		jButton_Dong.setBorderPainted(false);
		jButton_Dong.setBounds(840, 366, 123, 38);
		jButton_Dong.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jButton_Dong.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(QL_ChiSoDienView.class.getResource("Icon/Exit.png"))));
		jButton_Dong.addActionListener(acListener);
		contentPane.add(jButton_Dong);

		JLabel jLable_maKH_1 = new JLabel("Mã khách hàng");
		jLable_maKH_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maKH_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maKH_1.setForeground(new Color(128, 0, 0));
		jLable_maKH_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jLable_maKH_1.setBackground(new Color(255, 255, 255));
		jLable_maKH_1.setBounds(0, 427, 184, 38);
		contentPane.add(jLable_maKH_1);

		JLabel jLable_maThang_1 = new JLabel("Mã tháng");
		jLable_maThang_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_maThang_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_maThang_1.setForeground(new Color(128, 0, 0));
		jLable_maThang_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jLable_maThang_1.setBounds(241, 427, 184, 38);
		contentPane.add(jLable_maThang_1);

		JLabel jLable_chiSoCu_1 = new JLabel("Chỉ số cũ");
		jLable_chiSoCu_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_chiSoCu_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_chiSoCu_1.setForeground(new Color(128, 0, 0));
		jLable_chiSoCu_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jLable_chiSoCu_1.setBounds(483, 427, 184, 38);
		contentPane.add(jLable_chiSoCu_1);

		JLabel jLable_chiSoMoi_1 = new JLabel("Chỉ số mới");
		jLable_chiSoMoi_1.setVerticalAlignment(SwingConstants.BOTTOM);
		jLable_chiSoMoi_1.setHorizontalAlignment(SwingConstants.LEFT);
		jLable_chiSoMoi_1.setForeground(new Color(128, 0, 0));
		jLable_chiSoMoi_1.setFont(new Font("Tahoma", Font.PLAIN, 24));
		jLable_chiSoMoi_1.setBounds(726, 427, 184, 38);
		contentPane.add(jLable_chiSoMoi_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(961, 597, 11, 28);

		bangCSD = new JTable();
		bangCSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int x = bangCSD.getSelectedRow();
				if (x >= 0) {
					txt_MaKH.setText(bangCSD.getValueAt(x, 0) + "");
					txt_Mathang.setText(bangCSD.getValueAt(x, 1) + "");
					txt_CSC.setText(bangCSD.getValueAt(x, 2) + "");
					txt_CSM.setText(bangCSD.getValueAt(x, 3) + "");
					// Mã KH là khoá chính nên ko thể sửa
					txt_MaKH.setEnabled(false);
				}
			}
		});
		bangCSD.setBounds(0, 462, 972, 225);
		bangCSD.setFont(new Font("Tahoma", Font.PLAIN, 18));
		contentPane.add(bangCSD);

		JButton jButton_Reset = new JButton("Reset");
		jButton_Reset.setOpaque(true);
		jButton_Reset.setBorderPainted(false);
		jButton_Reset.setFont(new Font("Tahoma", Font.PLAIN, 26));
		jButton_Reset.setBorderPainted(false);
		jButton_Reset.setBackground(new Color(220, 20, 60));
		jButton_Reset.setBounds(783, 289, 127, 38);
		jButton_Reset.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(Ql_HoTieuThuDienView.class.getResource("Icon/Refresh.png"))));
		jButton_Reset.addActionListener(acListener);
		contentPane.add(jButton_Reset);

	}

	// hàm này sẽ xoá trắng sau khi thêm sửa xoá
	public void xoaTrang() {
		txt_MaKH.setText("");
		txt_Mathang.setText("");
		txt_CSC.setText("");
		txt_CSM.setText("");
		txt_MaKH.setEnabled(true);
	}

	public void add() {
		connection = cn.connect();
		try {
			if (txt_MaKH.getText().equals("") || txt_Mathang.getText().equals("") || txt_CSC.getText().equals("")
					|| txt_CSM.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				StringBuffer sb = new StringBuffer();
				String sql_check_pk = "SELECT MaKH FROM ChiSoDien WHERE MaKH = '" + txt_MaKH.getText() + "'";
				Statement st = connection.createStatement();
				ResultSet rs = st.executeQuery(sql_check_pk);

				if (rs.next()) {
					sb.append("Khách hàng này đã tồn tại!");
				}
				if (sb.length() > 0) {
					JOptionPane.showMessageDialog(this, sb.toString());
				} else {
					String sql = "Insert into ChiSoDien values('" + txt_MaKH.getText() + "', '" + txt_Mathang.getText()
							+ "', '" + txt_CSC.getText() + "', '" + txt_CSM.getText() + "')";
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
			if (txt_Mathang.getText().equals("") || txt_CSC.getText().equals("") || txt_CSM.getText().equals("")) {
				JOptionPane.showMessageDialog(this, "Bạn cần nhập đủ dữ liệu");
			} else {
				Statement st = connection.createStatement();
				String sql = "Update ChiSoDien set MaKH ='" + txt_MaKH.getText() + "', Mathang ='"
						+ txt_Mathang.getText() + "', ChiSoCu ='" + txt_CSC.getText() + "', ChisoMoi ='"
						+ txt_CSM.getText() + "' where MaKH ='" + txt_MaKH.getText() + "'";
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
			String sql = "Delete ChiSoDien where MaKH = '" + txt_MaKH.getText() + "'";
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

	public void dong() {
		this.setVisible(false);
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
}
