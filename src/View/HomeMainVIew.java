package View;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HomeMainVIew extends JFrame {

	private JPanel contentPane;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeMainVIew frame = new HomeMainVIew();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HomeMainVIew() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1385, 679);

		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel jLable_heThong = new JLabel("Hệ thống quản lý thanh toán tiền điện");
		jLable_heThong.setBounds(224, 255, 701, 76);
		jLable_heThong.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_heThong.setForeground(new Color(255, 255, 255));
		jLable_heThong.setFont(new Font("Calibri", Font.BOLD, 40));
		jLable_heThong.setBackground(Color.LIGHT_GRAY);
		jLable_heThong.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/EVN1.png"))));
		contentPane.add(jLable_heThong);

		JLabel jLable_heThong_1 = new JLabel("khu phố A");
		jLable_heThong_1.setHorizontalAlignment(SwingConstants.CENTER);
		jLable_heThong_1.setForeground(Color.WHITE);
		jLable_heThong_1.setFont(new Font("Calibri", Font.BOLD, 40));
		jLable_heThong_1.setBackground(Color.LIGHT_GRAY);
		jLable_heThong_1.setBounds(443, 341, 261, 76);
		contentPane.add(jLable_heThong_1);

		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 1371, 62);
		menuBar.setBackground(Color.LIGHT_GRAY);
		menuBar.setOpaque(true);
		contentPane.add(menuBar);

		JMenu jMenu_quanlyCSD = new JMenu("Quản lý chỉ số điện");
		jMenu_quanlyCSD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

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
		});
		jMenu_quanlyCSD.setHorizontalAlignment(SwingConstants.CENTER);
		jMenu_quanlyCSD.setForeground(Color.BLACK);
		jMenu_quanlyCSD.setFont(new Font("Segoe UI", Font.BOLD, 22));
		jMenu_quanlyCSD.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/Numbered list.png"))));
		menuBar.add(jMenu_quanlyCSD);

		JMenu jMenu_quanlyHTTD = new JMenu("Quản lý hộ tiêu thụ điện");
		jMenu_quanlyHTTD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							Ql_HoTieuThuDienView frame = new Ql_HoTieuThuDienView();
							frame.setLocationRelativeTo(null);
							frame.setVisible(true);
							frame.loadBang();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		jMenu_quanlyHTTD.setHorizontalAlignment(SwingConstants.CENTER);
		jMenu_quanlyHTTD.setForeground(Color.BLACK);
		jMenu_quanlyHTTD.setFont(new Font("Segoe UI", Font.BOLD, 22));
		jMenu_quanlyHTTD.setIcon(
				new ImageIcon(Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/Users.png"))));
		menuBar.add(jMenu_quanlyHTTD);

		JMenu jMenu_quanlyHD = new JMenu("Quản lý hoá đơn");
		jMenu_quanlyHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		});
		jMenu_quanlyHD.setHorizontalAlignment(SwingConstants.CENTER);
		jMenu_quanlyHD.setForeground(Color.BLACK);
		jMenu_quanlyHD.setFont(new Font("Segoe UI", Font.BOLD, 22));
		jMenu_quanlyHD.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/Price list.png"))));
		menuBar.add(jMenu_quanlyHD);

		JMenu jMenu_thongkeKH = new JMenu("Thống kê danh sách khách hàng");
		jMenu_thongkeKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
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
		});
		jMenu_thongkeKH.setHorizontalAlignment(SwingConstants.CENTER);
		jMenu_thongkeKH.setForeground(Color.BLACK);
		jMenu_thongkeKH.setFont(new Font("Segoe UI", Font.BOLD, 22));
		jMenu_thongkeKH.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/Statistics.png"))));
		menuBar.add(jMenu_thongkeKH);

		JMenu jMenu_quanlyTk = new JMenu("Quản lý tài khoản");
		jMenu_quanlyTk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							Ql_TaiKhoanView frame = new Ql_TaiKhoanView();
							frame.setVisible(true);
							frame.setLocationRelativeTo(null);
							frame.loadBang();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		jMenu_quanlyTk.setHorizontalAlignment(SwingConstants.CENTER);
		jMenu_quanlyTk.setForeground(Color.BLACK);
		jMenu_quanlyTk.setFont(new Font("Segoe UI", Font.BOLD, 22));
		jMenu_quanlyTk.setIcon(new ImageIcon(
				Toolkit.getDefaultToolkit().createImage(HomeMainVIew.class.getResource("Icon/Unknown person.png"))));
		menuBar.add(jMenu_quanlyTk);
	}
}
