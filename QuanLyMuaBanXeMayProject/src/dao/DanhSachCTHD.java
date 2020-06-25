package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.ChiTietHD;
import entity.HoaDon;

public class DanhSachCTHD {
	public DanhSachCTHD() {
		
	}

	public String themCTHoaDon(ChiTietHD cthd) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, cthd.getHoaDon().getMaHD());
		if (!st.executeQuery().next())
			return "Không tồn tại hóa đơn này!";

		query = "select * from XeMay where maXe = ?";
		st = conn.prepareStatement(query);
		st.setString(1, cthd.getXeMay().getMaXe());

		if (!st.executeQuery().next())
			return "Mã xe máy không tồn tại!";

		query = "insert into ChiTietHD values (?, ?, ?, ?)";
		st = conn.prepareStatement(query);
		st.setString(1, cthd.getHoaDon().getMaHD());
		st.setString(2, cthd.getXeMay().getMaXe());
		st.setInt(3, cthd.getSoLuong());
		st.setDouble(4, cthd.getDonGia());

		st.execute();

		return null;
	}

	public boolean xoaCTHoaDon(String maHD, String maXeMay) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD where maHD = ? and maXeMay = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maHD);
		st.setString(2, maXeMay);
		if (!st.executeQuery().next())
			return false;

		query = "delete from ChiTietHD where maHD = ? and maXeMay = ?";
		st = conn.prepareStatement(query);		
		st.setString(1, maHD);
		st.setString(2, maXeMay);
		st.execute();
		return true;
	}

	public boolean suaHoaDon(HoaDon hdCu, HoaDon hdMoi) {

		return true;
	}

	public ArrayList<ChiTietHD> timTheoMaHD(String ma) throws SQLException {
		ArrayList<ChiTietHD> list = new ArrayList<ChiTietHD>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from NhanVien n join HoaDon on n.ma = a.maHD a join ChiTietHD b on a.maHD = b.maHD join XeMay c on b.maXeMay = c.maXe "
				+ "where maHD = ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, ma);
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maHD = result.getString("maHD");
			String maXM = result.getString("maXeMay");
			int soLuong = result.getInt("soLuong");
			double donGia = result.getDouble("donGia");
			
			String maXe = result.getString("maXe");
			String tenXe = result.getString("tenXe");
			String loaiXe = result.getString("loaiXe");
			String hangXe = result.getString("hangXe");
			int dungTich = result.getInt("dungTich");
			String mauXe = result.getString("mauXe");
			String nuocSX = result.getString("nuocSX");
			int slTon = result.getInt("soLuongTon");
			
			String maNV = result.getString("ma");
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);
			
//			ChiTietHD hd = new ChiTietHD(maHD, nv, kh, ngayLap);

//			if (!list.contains(hd))
//				list.add(hd);
		}

		conn.close();
		return list;
	}

}
