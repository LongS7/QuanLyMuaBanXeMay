package entity;

public class KhachHang {
	private String maKH;
	private String hoTenKH;
	private boolean gioiTinhKH;
	private String diaChiKH;
	private String sdtKH;
	private String emailKH;
	
	public KhachHang() {
	}
	
	public KhachHang(String maKH) {
		this.maKH = maKH;
	}

	public KhachHang(String maKH, String hoTenKH, boolean gioiTinhKH, String diaChiKH, String sdtKH, String emailKH) {
		super();
		this.maKH = maKH;
		this.hoTenKH = hoTenKH;
		this.gioiTinhKH = gioiTinhKH;
		this.diaChiKH = diaChiKH;
		this.sdtKH = sdtKH;
		this.emailKH = emailKH;
	}

	public String getMaKH() {
		return maKH;
	}
	
	
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getHoTenKH() {
		return hoTenKH;
	}

	public void setHoTenKH(String hoTenKH) {
		this.hoTenKH = hoTenKH;
	}

	public boolean isGioiTinhKH() {
		return gioiTinhKH;
	}

	public void setGioiTinhKH(boolean gioiTinhKH) {
		this.gioiTinhKH = gioiTinhKH;
	}

	public String getDiaChiKH() {
		return diaChiKH;
	}

	public void setDiaChiKH(String diaChiKH) {
		this.diaChiKH = diaChiKH;
	}

	public String getSdtKH() {
		return sdtKH;
	}

	public void setSdtKH(String sdtKH) {
		this.sdtKH = sdtKH;
	}

	public String getEmailKH() {
		return emailKH;
	}

	public void setEmailKH(String emailKH) {
		this.emailKH = emailKH;
	}

	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", hoTenKH=" + hoTenKH + ", gioiTinhKH=" + gioiTinhKH + ", diaChiKH="
				+ diaChiKH + ", sdtKH=" + sdtKH + ", emailKH=" + emailKH + "]";
	}
	
	
}
