package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.QL_ChiSoDienView;
import View.Ql_HoTieuThuDienView;

public class Ql_HoTieuThuDienController implements ActionListener {
	public Ql_HoTieuThuDienView view;

	public Ql_HoTieuThuDienController(Ql_HoTieuThuDienView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("Thêm")) {
			this.view.add();
		} else if (ac.equals("Reset")) {
			this.view.reset();
		} else if (ac.equals("Sửa")) {
			this.view.sua();
		} else if (ac.equals("Xoá")) {
			this.view.xoa();
		} else if (ac.equals("Đóng")) {
			this.view.dong();
		}
	}
}
