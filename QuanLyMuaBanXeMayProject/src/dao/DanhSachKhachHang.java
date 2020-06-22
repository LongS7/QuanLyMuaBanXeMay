package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.HoaDon;
import entity.KhachHang;

public class DanhSachKhachHang {
	private ArrayList<KhachHang> dsKH;
	
	public DanhSachKhachHang() {
		dsKH = new ArrayList<KhachHang>();
	}
	public ArrayList<KhachHang> getDsKH() {
		return dsKH;
	}
	public void getAll() throws SQLException {
		Connection conn = null;
		conn = DatabaseConnection.getConnection();
		String query = "select * from KhachHang";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maKH =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			dsKH.add(kh);
		}
		
		conn.close();

	}
	// thêm
	
	public boolean themKhachHang(KhachHang kh) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select Count(*) from KhachHang where ma = ?";
		
		PreparedStatement pstmt = conn.prepareStatement(query);
		pstmt.setString(1, kh.getMaKH());
		ResultSet rs = pstmt.executeQuery(query);
		
		
		
		if (dsKH.contains(kh))
			return false;
		dsKH.add(kh);
		return true;
	}
	
	// xoa
	
	public boolean xoaKhachHang(String maKH) {
		for (KhachHang item : dsKH) {
			if (item.getMaKH().equalsIgnoreCase(maKH)) {
				dsKH.remove(item);
				return true;
			}
		}
		return false;
	}
	
	
	// sửa
	public boolean suaKhachHang(KhachHang khCu, KhachHang khMoi) {
		if (!dsKH.contains(khCu))
			return false;
		dsKH.set(dsKH.indexOf(khCu), khMoi);
		return true;
	}
	
	public ArrayList<KhachHang> layDanhSach() {
		return dsKH;
	}

	@Override
	public String toString() {
		return "DanhSachKhachHang [dsKH=" + dsKH + "]";
	}
	
	
	
	
}
