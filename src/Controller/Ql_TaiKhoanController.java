package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.QL_ChiSoDienView;
import View.Ql_TaiKhoanView;

public class Ql_TaiKhoanController implements ActionListener {
	public Ql_TaiKhoanView view;

	public Ql_TaiKhoanController(Ql_TaiKhoanView view) {
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
		}
	}
}
