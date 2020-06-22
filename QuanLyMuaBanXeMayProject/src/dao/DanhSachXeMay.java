package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entity.XeMay;

public class DanhSachXeMay {
	private ArrayList<XeMay> dsXM;
	public DanhSachXeMay() {
		dsXM = new ArrayList<XeMay>();
	}
	public ArrayList<XeMay> getDsXM() {
		return dsXM;
	}
	public void setDsXM(ArrayList<XeMay> dsXM) {
		this.dsXM = dsXM;
	}
	public void getAll() throws SQLException{
		Connection con = null;
		con = DatabaseConnection.getConnection();
		String query = "select * from XeMay";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		while(result.next()) {
			String maxe = result.getString("maXe");
			String tenxe = result.getString("tenXe");
			String loaixe = result.getString("loaiXe");
			String hangxe = result.getString("hangXe");
			int dungtich = Integer.parseInt(result.getString("dungTich"));
			String mauxe = result.getString("mauXe");
			String nuocsx = result.getString("nuocSX");
			int soluongton = Integer.parseInt(result.getString("soLuongTon"));
			double dongia = result.getDouble("donGia");
			
			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, soluongton, dongia);
			dsXM.add(xm);
		}
		con.close();
	}
	public boolean themXeMay(XeMay xemay) throws SQLException{
		Connection conn = DatabaseConnection.getConnection();
		String query = "select Count(*) from XeMay where ?";
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery(query);
		
		
		if(dsXM.contains(xemay))
			return false;
		else {
			dsXM.add(xemay);
			return true;
		}
	}
	
	
	public ArrayList<XeMay> getdanhSach(){
		return dsXM;
	}
	@Override
	public String toString() {
		return "DanhSachXeMay [dsXM=" + dsXM + "]";
	}
	
}
