package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;

public class DanhSachHoaDon {

	public DanhSachHoaDon() {
	}

	public ArrayList<HoaDon> getAll() throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();

		Connection conn = null;

		conn = DatabaseConnection.getConnection();

		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma";

		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();

		return list;
	}

	public String themHoaDon(HoaDon hd) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from HoaDon where maHD = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, hd.getMaHD());
		if (st.executeQuery().next())
			return "Mã hóa đơn bị trùng!";

		query = "select * from KhachHang where ma = ?";
		st = conn.prepareStatement(query);
		st.setString(1, hd.getKhachHang().getMaKH());

		if (!st.executeQuery().next())
			return "Mã khách hàng không tồn tại!";

		query = "insert into HoaDon values (?, ?, ?, ?)";
		st = conn.prepareStatement(query);
		st.setString(1, hd.getMaHD());
		st.setString(2, hd.getKhachHang().getMaKH());
		st.setString(3, hd.getNhanVien().getMaNV());
		st.setDate(4, java.sql.Date.valueOf(hd.getNgayLap()));

		st.execute();

		return null;
	}

	public boolean xoaHoaDon(String maHD) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();

		String query = "select * from HoaDon where maHD = ?";
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, maHD);
		if (!st.executeQuery().next())
			return false;

		query = "delete from HoaDon where maHD = ?";
		st = conn.prepareStatement(query);
		st.setString(1, maHD);
		st.execute();
		return true;
	}

	public boolean suaHoaDon(HoaDon hdCu, HoaDon hdMoi) {

		return true;
	}

	public ArrayList<HoaDon> timTheoMaHD(String ma) throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where maHD like ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, '%' + ma + '%');
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();
		return list;
	}

	public ArrayList<HoaDon> timTheoMaKH(String ma) throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where a.ma like ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, '%' + ma + '%');
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();
		return list;
	}

	public ArrayList<HoaDon> timTheoMaNV(String ma) throws SQLException {
		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select * from KhachHang a join HoaDon b on a.ma = b.maKH join NhanVien c on b.maNV = c.ma "
				+ "where c.ma like ?";

		PreparedStatement pst = conn.prepareStatement(query);
		pst.setString(1, '%' + ma + '%');
		
		ResultSet result = pst.executeQuery();

		while (result.next()) {
			String maKH = result.getString(1);
			String hoTenKH = result.getString(2);
			boolean gtKH = result.getBoolean(3);
			String diaChiKH = result.getString(4);
			String sdtKH = result.getString(5);
			String emailKH = result.getString(6);

			String maHD = result.getString(7);
			LocalDate ngayLap = LocalDate.parse(result.getDate(10).toString());

			String maNV = result.getString(9);
			String hoTenNV = result.getString(11);
			boolean gtNV = result.getBoolean(12);
			String diaChiNV = result.getString(13);
			String sdtNV = result.getString(14);
			String emailNV = result.getString(15);
			boolean quanLyVien = result.getBoolean(16);

			KhachHang kh = new KhachHang(maKH, hoTenKH, gtKH, diaChiKH, sdtKH, emailKH);
			NhanVien nv = new NhanVien(maNV, hoTenNV, gtNV, diaChiNV, sdtNV, emailNV, quanLyVien);

			HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

			if (!list.contains(hd))
				list.add(hd);
		}

		conn.close();
		return list;
	}

}
