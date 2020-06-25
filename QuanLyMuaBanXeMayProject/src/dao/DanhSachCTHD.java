package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.XeMay;

public class DanhSachCTHD {
	private ArrayList<ChiTietHD> dsCTHD;

	public ArrayList<ChiTietHD> getDsCTHD() {
		return dsCTHD;
	}
	
	public DanhSachCTHD() {
		dsCTHD = new ArrayList<ChiTietHD>();
	}
	
	public boolean themChiTietHD(ChiTietHD cthd) {
		if(dsCTHD.contains(cthd))
			return false;
		dsCTHD.add(cthd);
		return true;
	}
	
	public boolean xoaChiTietHD(ChiTietHD cthd) {
		if(dsCTHD.contains(cthd))
			return false;
		dsCTHD.remove(cthd);
		return true;
	}
	
	public boolean suaChiTietHD(ChiTietHD cthdCu, ChiTietHD cthdMoi) {
		if(!dsCTHD.contains(cthdCu))
			return false;
		dsCTHD.set(dsCTHD.indexOf(cthdCu), cthdMoi);
		return true;
	}
	
	public ArrayList<ChiTietHD> layDanhSach() {
		return dsCTHD;
	}

	@Override
	public String toString() {
		return "DanhSachChiTietHD [dsHD=" + dsCTHD + "]";
	}
	
	
	
	
	
	
	
	
	public XeMay getXeMay(String maXeMay) throws SQLException {
		Connection con = null;
		con = DatabaseConnection.getConnection();
		String query = "select * from XeMay where maXe = '" + maXeMay + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		
		if(result.next()) {
			String maxe = result.getString(1);
			String tenxe = result.getString(2);
			String loaixe = result.getString(3);
			String hangxe = result.getString(4);
			int dungtich = result.getInt(5);
			String mauxe = result.getString(6);
			String nuocsx = result.getString(7);
			int slt = result.getInt(8);
			double dongia = result.getDouble(9);
			
			XeMay xm = new XeMay(maxe, tenxe, loaixe, hangxe, dungtich, mauxe, nuocsx, slt, dongia);
			return xm;
		} else
			return null;	
	}
	
	public HoaDon getHoaDon(String maHoaDon) throws SQLException{
		Connection con = null;
		con = DatabaseConnection.getConnection();
		String query = "select * from HoaDon where maHD = '" + maHoaDon + "'";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		KhachHang kh = null;
		NhanVien nv = null;
		
		while(result.next()) {
			String makh = result.getString(2);
			String manv = result.getString(3);
			kh = getKhachHang(makh);
			nv = getNhanVien(manv);
		}
		
		con.close();
		
		
		con = DatabaseConnection.getConnection();
		query = "select * from HoaDon where maHD = '" + maHoaDon + "'";
		stmt = con.createStatement();
		result = stmt.executeQuery(query);
		
		LocalDate ngayLap = null;
		if(result.next()) {
			ngayLap = LocalDate.parse(result.getDate(4).toString());
		}
		HoaDon hd = new HoaDon(maHoaDon, nv, kh, ngayLap);
		return hd;
	}
	
	public KhachHang getKhachHang(String maHoaDon) throws SQLException{
		Connection conn = null;
		conn = DatabaseConnection.getConnection();
		String query = "select * from KhachHang";
		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
		
		if(result.next()) {
			String maKH =  result.getString("ma");
			String hoTen = result.getString("hoTen");
			String diaChi = result.getString("diaChi");
			boolean gioiTinh = result.getBoolean("gioiTinh");
			String email = result.getString("email");
			String sdt = result.getString("sdt");
			
			KhachHang kh = new KhachHang(maKH, hoTen, gioiTinh, diaChi, sdt, email);
			return kh;
		}else 
		return null;
	}
	
	public NhanVien getNhanVien(String maNhanVien) throws SQLException{
		 Connection conn = null ;
	        conn = DatabaseConnection.getConnection();
	        String query = "select * from NhanVien";
	        Statement stmt = conn.createStatement();
	        ResultSet result = stmt.executeQuery(query);
	        
	        if(result.next())
	        {
	            String maNV = result.getString("ma");
	            String hoTen = result.getString("hoTen");
	            boolean gioiTinh = result.getBoolean("gioiTinh");
	            String diaChi = result.getString("diaChi");
	            String sdt = result.getString("sdt");
	            String email = result.getString("email");
	            boolean quanLyVien = result.getBoolean("quanLyVien");
	            
	            NhanVien nv = new NhanVien(maNV,hoTen,gioiTinh,diaChi,sdt,email,quanLyVien);
	            return nv;
	        }else
	        return null;
	}
	
	
	public ArrayList<ChiTietHD> getAll() throws SQLException{
		ArrayList<ChiTietHD> dscthd = new ArrayList<ChiTietHD>();
		Connection conn = null;

		conn = DatabaseConnection.getConnection();

		String query = "select * from ChiTietHD";

		Statement stmt = conn.createStatement();
		ResultSet result = stmt.executeQuery(query);
	
		while(result.next()) {
			String mahd = result.getString(1);
			String maxm = result.getString(2);
			int soluong = result.getInt(3);
			double dongia = result.getDouble(4);
			
			HoaDon hd = getHoaDon(mahd);
			XeMay xm = getXeMay(maxm);
			ChiTietHD chitietHD = new ChiTietHD(soluong, dongia, hd, xm);
			if(!dscthd.contains(chitietHD))
				dscthd.add(chitietHD);
		}
		conn.close();
		return dscthd;
	}
}
