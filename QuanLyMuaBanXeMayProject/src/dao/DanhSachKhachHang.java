package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import entity.HoaDon;
import entity.KhachHang; 
import entity.XeMay;

public class DanhSachKhachHang {
	private Connection con;
	private ArrayList<KhachHang> dsKH;
	
	public DanhSachKhachHang() {
		try {
			con = DatabaseConnection.getConnection();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Không thể kết nối!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	public ArrayList<KhachHang> getDsKH() {
		return dsKH;
	}
	public List<KhachHang> getAll() throws SQLException {
		
//		Connection conn = null;
//		conn = DatabaseConnection.getConnection();
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		String query = "select * from KhachHang";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maKH =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String diaChi = result.getString("diaChi");
			String sdt = result.getString("sdt");
			String email = result.getString("email");
			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			dskh.add(kh);
		}
		
		return dskh;

	}
	// thêm
	
	public boolean themKhachHang(KhachHang kh) throws SQLException {
//		Connection conn = DatabaseConnection.getConnection();
//		
//		String query = "select Count(*) from KhachHang where ma = ?";
//		
//		PreparedStatement pstmt = conn.prepareStatement(query);
//		pstmt.setString(1, kh.getMaKH());
//		ResultSet rs = pstmt.executeQuery(query);
//		
//		
//		
//		if (dsKH.contains(kh))
//			return false;
//		dsKH.add(kh);
//		return true;
		String sql = "insert into KhachHang values(?,?,?,?,?,?)";
		try {
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTenKH());
			stmt.setString(4, kh.getDiaChiKH());
			stmt.setBoolean(3, kh.isGioiTinhKH());
			stmt.setString(6, kh.getEmailKH());
			stmt.setString(5, kh.getSdtKH());
			
			int n = stmt.executeUpdate();
			if(n > 0)
				return true;
		}catch(SQLException e) {
			throw new SQLException(e);
			
		}
		return false;
	}
	
	// xoa
	
	public boolean xoaKhachHang(KhachHang kh) throws SQLException {
//		for (KhachHang item : dsKH) {
//			if (item.getMaKH().equalsIgnoreCase(maKH)) {
//				dsKH.remove(item);
//				return true;
//			}
//		}
//		return false;
		try {
			String sql = "delete from KhachHang where ma = ?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			int n = stmt.executeUpdate();
			if(n > 0)
				return true;
		}catch (SQLException e) {
			throw new SQLException(e);
		}
		return false;
	}
	
	public KhachHang timKHTheoMa(String maKH) throws SQLException {
		Connection con = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang where ma = '" + maKH + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);

		if (result.next()) {
			String ma =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			return kh;
		} else
			return null;
	}
	
	public boolean suaTTKhachHang(KhachHang kh) throws SQLException {
		try {
			String sql = "update KhachHang set ma = ?, hoTen = ?, gioiTinh = ?, diaChi = ?, sdt = ?, email = ? where ma = '" + kh.getMaKH() + "'";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, kh.getMaKH());
			stmt.setString(2, kh.getHoTenKH());
			stmt.setString(3, kh.getDiaChiKH());
			stmt.setBoolean(4, kh.isGioiTinhKH());
			stmt.setString(5, kh.getEmailKH());
			stmt.setString(6, kh.getSdtKH());
			
			int n = stmt.executeUpdate();
			if(n > 0) 
				return true;
			}catch (SQLException e) {
				throw new SQLException(e);
			}
			return false;
		
	}
	
	public ArrayList<KhachHang> layDanhSach() {
		return dsKH;
	}

	@Override
	public String toString() {
		return "DanhSachKhachHang [dsKH=" + dsKH + "]";
	}
	
	public KhachHang timTheoMa(String ma) throws SQLException{
		KhachHang kh;
		String query = "select * from KhachHang where ma = '" + ma + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		if (result.next()) {
			String maa =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
			return kh;
		} else
			return null;
	}
	
	public List<KhachHang> timTheoTenKhachHang(String tenKH) throws SQLException{
		List<KhachHang> dskh = new ArrayList<KhachHang>();
		try {
			String query = "select * from KhachHang where hoTen like " + "'%" + tenKH + "%'";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(query);
			while(result.next()) {
				String maa =  result.getString("ma");
				String hoTen = result.getString("hoTen");
				String diaChi = result.getString("diaChi");
				boolean gioiTinh = result.getBoolean("gioiTinh");
				String email = result.getString("email");
				String sdt = result.getString("sdt");
				
				KhachHang kh = new KhachHang(maa, hoTen, gioiTinh, diaChi, sdt, email);
				dskh.add(kh);
				
			}
			if(dskh.size() > 0)
				return dskh;
			else 
				return null;
			
		}catch(Exception e) {
			throw new SQLException(e);
		}
		
	}
	
	
}
