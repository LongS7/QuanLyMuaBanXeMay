package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import entity.NhanVien;

public class QuanLyHoSo {
	private NhanVien nhanVien;
	
	
	public NhanVien getNhanVien() {
		return nhanVien;
	}
	public void setnhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}
	public void suanhanVien(NhanVien New) {
		nhanVien.setMaNV(New.getMaNV());
		nhanVien.setHoTenNV(New.getHoTenNV());
		nhanVien.setGioiTinh(New.isGioiTinh());
		nhanVien.setDiaChi(New.getDiaChi());
		nhanVien.setSDT(New.getSDT());
		nhanVien.setEmail(New.getEmail());
		nhanVien.setQuanLyVien(New.isQuanLyVien());
	}
	public void getProfile() throws SQLException{
		
		Connection con = DatabaseConnection.getConnection();
		String userName = DatabaseConnection.userName;
		String query = "Select * from NhanVien where ma = '" + userName + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
			
		while(result.next()) {
			String manv = result.getString("ma");
			String hoten = result.getString("hoTen");
			boolean gioitinh = result.getBoolean("gioiTinh");
			String diachi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			boolean chucvu = result.getBoolean("quanLyVien");
				
			nhanVien = new NhanVien(manv, hoten, gioitinh, diachi, sdt, email, chucvu);
		}
		
		con.close();
	}
}
