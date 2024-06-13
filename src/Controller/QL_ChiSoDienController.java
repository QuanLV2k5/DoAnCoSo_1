package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import View.QL_ChiSoDienView;

public class QL_ChiSoDienController implements ActionListener {
	public QL_ChiSoDienView view;

	public QL_ChiSoDienController(QL_ChiSoDienView view) {
		this.view = view;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String ac = e.getActionCommand();
		if (ac.equals("Thêm")) {
			this.view.add();
		} else if (ac.equals("Sửa")) {
			this.view.sua();
		} else if (ac.equals("Xoá")) {
			this.view.xoa();
		} else if (ac.equals("Đóng")) {
			this.view.dong();
		} else if (ac.equals("Reset")) {
			this.view.reset();
		}
	}
}
