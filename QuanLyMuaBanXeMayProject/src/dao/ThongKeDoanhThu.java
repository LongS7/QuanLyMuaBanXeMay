package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThongKeDoanhThu {
	public ThongKeDoanhThu() {
		// TODO Auto-generated constructor stub
	}
	
	public double thongKeTheoThang(int thang, int nam) throws SQLException {
		double doanhThu = 0;
		
		Connection conn = DatabaseConnection.getConnection();
		
		String query = "select SUM(soLuong * donGia) as Tong from ChiTietHD a join HoaDon b on a.maHD = b.maHD "
				+ "where YEAR(ngayLap) = ? and MONTH(ngayLap) = ?";
		PreparedStatement pst = conn.prepareStatement(query);
		pst.setInt(1, nam);
		pst.setInt(2, thang);
		
		ResultSet rs = pst.executeQuery();
		
		if(rs.next())
			doanhThu = rs.getDouble(1);
		
		return doanhThu / 1000000;
	}

}
