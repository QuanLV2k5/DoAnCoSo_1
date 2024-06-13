package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HoTieuThuDien {
	protected String maKH;
	protected String hoTen;
	protected String gioiTinh;
	protected Date ngaySinh;
	protected String CMND;
	protected String SDT;
	protected Date ngayDK;
	protected String diaChi;
	protected String loaiDien;

	public HoTieuThuDien() {
	}

	public HoTieuThuDien(String maKH, String hoTen, String gioiTinh, Date ngaySinh, String cmnd, String sdt,
			Date ngayDK, String diaChi, String loaiDien) {
		this.maKH = maKH;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.CMND = cmnd;
		this.SDT = sdt;
		this.ngayDK = ngayDK;
		this.diaChi = diaChi;
		this.loaiDien = loaiDien;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getCmnd() {
		return CMND;
	}

	public void setCmnd(String cmnd) {
		this.CMND = cmnd;
	}

	public String getSdt() {
		return SDT;
	}

	public void setSdt(String sdt) {
		this.SDT = sdt;
	}

	public Date getNgayDK() {
		return ngayDK;
	}

	public void setNgayDK(Date ngayDK) {
		this.ngayDK = ngayDK;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getLoaiDien() {
		return loaiDien;
	}

	public void setLoaiDien(String loaiDien) {
		this.loaiDien = loaiDien;
	}

	public String dateToString(Date date) {
		return new SimpleDateFormat("dd/MM/yyyy").format(date);
	}

	public String[] toStringArray() {
		String[] stringArray = { maKH, hoTen, gioiTinh, dateToString(ngaySinh), CMND, SDT, dateToString(ngayDK), diaChi,
				loaiDien };
		return stringArray;
	}

	public java.sql.Date utilDateToSQLDate(Date date) {
		return new java.sql.Date(date.getTime());
	}

	public Date sqlDateToUtilDate(java.sql.Date date) {
		return new java.util.Date(date.getTime());
	}

	@Override
	public String toString() {
		return "ThongTinHoTieuThuModel{" + "CMND=" + CMND + ", diaChi=" + diaChi + ", maKH=" + maKH + ", ngayDK="
				+ ngayDK + ", ngaySinh=" + ngaySinh + ", SDT=" + SDT + ", ten=" + hoTen + '}';
	}
}
