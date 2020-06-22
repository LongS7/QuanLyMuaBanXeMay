package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

import entity.HoaDon;

public class DanhSachHoaDon {
	private ArrayList<HoaDon> dsHD;

	public ArrayList<HoaDon> getDsHD() {
		return dsHD;
	}

	public DanhSachHoaDon() {
		dsHD = new ArrayList<HoaDon>();
	}
	
	public void getAll() throws SQLException {
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
			
			HoaDon hd = new HoaDon(maHD, null, null, ngayLap);
			
			dsHD.add(hd);
		}
		
		conn.close();
	}

	public boolean themHoaDon(HoaDon hd) throws SQLException {
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select Count(*) from HoaDon where ?";
		
		Statement st = conn.createStatement();
		
		ResultSet rs = st.executeQuery(query);
		
		
		
		if (dsHD.contains(hd))
			return false;
		dsHD.add(hd);
		return true;
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
