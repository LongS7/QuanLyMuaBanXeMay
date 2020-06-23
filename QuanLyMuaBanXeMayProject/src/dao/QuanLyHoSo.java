package dao;

import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import entity.HoSo;

public class QuanLyHoSo {
	private HoSo hoSo;
	
	
	public HoSo getHoSo() {
		return hoSo;
	}
	public void setHoSo(HoSo hoSo) {
		this.hoSo = hoSo;
	}
	public void suaHoSo(HoSo New) {
		hoSo.setMaNhanVien(New.getMaNhanVien());
		hoSo.setHoTen(New.getHoTen());
		hoSo.setGioiTinh(New.getGioiTinh());
		hoSo.setDiaChi(New.getDiaChi());
		hoSo.setSoDienThoai(New.getSoDienThoai());
		hoSo.setEmail(New.getEmail());
		hoSo.setChucVu(New.getChucVu());
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
				
			hoSo = new HoSo(manv, hoten, gioitinh, diachi, sdt, email, chucvu);
		}
		
		con.close();
	}
}
