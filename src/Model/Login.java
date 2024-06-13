package Model;

public class Login {
	private String taiKhoan;
	private String matKhau;

	public Login() {
	}

	public Login(String taiKhoan, String matKhau) {
		this.taiKhoan = taiKhoan;
		this.matKhau = matKhau;
	}

	public String getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(String taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public boolean isEmpty() {
		if (taiKhoan.isEmpty() || matKhau.isEmpty()) {
			return true;
		} else
			return false;
	}
}
