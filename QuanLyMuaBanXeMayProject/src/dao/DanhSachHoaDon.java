package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import entity.HoaDon;
import entity.KhachHang;

public class DanhSachHoaDon {
	private ArrayList<HoaDon> dsHD;
	private DanhSachKhachHang dsKH;
	private DanhSachKhachHang dsNV;

	public ArrayList<HoaDon> getDsHD() {
		return dsHD;
	}

	public DanhSachHoaDon() {
		dsHD = new ArrayList<HoaDon>();
		dsKH = new DanhSachKhachHang();
	}
	
	public void updateData() throws SQLException {
		Connection conn = null;
		
		conn = DatabaseConnection.getConnection();

		String query = "select * from HoaDon";
		
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maHD = result.getString("maHD");
			String maKH = result.getString("maKH");
			String maNV = result.getString("maNV");
			Date ngayLap = result.getDate("ngayLap");
			
			KhachHang kh = null;
			
			dsKH.getAll();
			ArrayList<KhachHang> list = dsKH.getDsKH();
			for(KhachHang item : list) {
				if(item.getMaKH().equals(maKH))
					kh = item;
			}
			
			HoaDon hd = new HoaDon(maHD, null, kh, ngayLap);
			
			if(!dsHD.contains(hd))
				dsHD.add(hd);
		}
		
		conn.close();
	}

	public boolean themHoaDon(HoaDon hd) throws SQLException {
		if (dsHD.contains(hd))
			return false;
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "insert into HoaDon values (?, ?, ?, ?)";
		
		PreparedStatement st = conn.prepareStatement(query);
		st.setString(1, hd.getMaHD());
		st.setString(2, "nv03");
		st.setString(3, hd.getKhachHang().getMaKH());
		st.setDate(4, (java.sql.Date) hd.getNgayLap());
		
		boolean x = st.execute(query);
		
		dsHD.add(hd);
		
		return x;
	}

	public boolean xoaHoaDon(String maHD) {
		for (HoaDon item : dsHD) {
			if (item.getMaHD().equalsIgnoreCase(maHD)) {
				dsHD.remove(item);
				return true;
			}
		}
		return false;
	}

	public boolean suaHoaDon(HoaDon hdCu, HoaDon hdMoi) {
		if (!dsHD.contains(hdCu))
			return false;
		dsHD.set(dsHD.indexOf(hdCu), hdMoi);
		return true;
	}

	public ArrayList<HoaDon> layDanhSach() {
		return dsHD;
	}

	@Override
	public String toString() {
		return "DanhSachHoaDon [dsHD=" + dsHD + "]";
	}

}
