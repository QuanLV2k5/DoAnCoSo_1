package Model;

import java.util.ArrayList;
import java.util.Date;

public class ChiSoDien {
	private String maKH;
	private String maThang;
	private int chiSoCu;
	private int chiSoMoi;

	public ChiSoDien() {
	}

	public ChiSoDien(String maKH, String maThang, int chiSoCu, int chiSoMoi) {
		this.maKH = maKH;
		this.maThang = maThang;
		this.chiSoCu = chiSoCu;
		this.chiSoMoi = chiSoMoi;
	}

	public int getChiSoCu() {
		return chiSoCu;
	}

	public void setChiSoCu(int chiSoCu) {
		this.chiSoCu = chiSoCu;
	}

	public int getChiSoMoi() {
		return chiSoMoi;
	}

	public void setChiSoMoi(int chiSoMoi) {
		this.chiSoMoi = chiSoMoi;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getMaThang() {
		return maThang;
	}

	public void setMaThang(String maThang) {
		this.maThang = maThang;
	}

	public String[] toStringArray() {
		String[] stringArray = { maKH, maThang, String.valueOf(chiSoCu), String.valueOf(chiSoMoi) };
		return stringArray;
	}
}
