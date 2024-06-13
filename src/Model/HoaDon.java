package Model;

public class HoaDon {
	private String maKH;
	private String maHoaDon;
	private String loaiDien;
	private String hoTen;
	private int luongDienTieuThu;
	private int thanhTien;

	public HoaDon() {
	}

	public HoaDon(String maKH, String hoten, String maHoaDon, String loaiDien, int luongDienTieuThu, int thanhTien) {
		this.maKH = maKH;
		this.maHoaDon = maHoaDon;
		this.hoTen = hoten;
		this.loaiDien = loaiDien;
		this.luongDienTieuThu = luongDienTieuThu;
		this.thanhTien = thanhTien;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	public void setMaHoaDon(String maHoaDon) {
		this.maHoaDon = maHoaDon;
	}

	public String getLoaiDien() {
		return loaiDien;
	}

	public void setLoaiDien(String loaiDien) {
		this.loaiDien = loaiDien;
	}

	public int getLuongDienTieuThu() {
		return luongDienTieuThu;
	}

	public void setLuongDienTieuThu(int luongDienTieuThu) {
		this.luongDienTieuThu = luongDienTieuThu;
	}

	public int getThanhTien() {
		return thanhTien;
	}

	public void setThanhTien(int thanhTien) {
		this.thanhTien = thanhTien;
	}

	public String getHoten() {
		return hoTen;
	}

	public void setHoten(String hoten) {
		this.hoTen = hoten;
	}

	public String[] toStringArray() {
		String[] arr = { maHoaDon, hoTen, maKH, loaiDien, "" + luongDienTieuThu, "" + thanhTien };
		return arr;
	}

	@Override
	public String toString() {
		return "HoaDonModel{" + "maKH=" + maKH + ", maHoaDon=" + maHoaDon + ", loaiDien=" + loaiDien
				+ ", luongDienTieuThu=" + luongDienTieuThu + ", thanhTien=" + thanhTien + '}';
	}
}
