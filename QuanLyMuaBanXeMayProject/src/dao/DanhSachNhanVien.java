
package dao;


import java.sql.SQLException;
import java.util.ArrayList;
import entity.NhanVien;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class DanhSachNhanVien {
    private ArrayList<NhanVien> dsNV;
    
    public DanhSachNhanVien()
    {
        dsNV = new ArrayList<NhanVien>();
    }

    public ArrayList<NhanVien> getDsNV() {
        return dsNV;
    }

    public void setDsNV(ArrayList<NhanVien> dsNV) {
        this.dsNV = dsNV;
    }
    
    public void getAll() throws SQLException
    {
        Connection conn = null ;
        conn = DatabaseConnection.getConnection();
        String query = "select * from NhanVien";
        Statement stmt = conn.createStatement();
        ResultSet result = stmt.executeQuery(query);
        
        while(result.next())
        {
            String maNV = result.getString("ma");
            String hoTen = result.getString("hoTen");
            boolean gioiTinh = result.getBoolean("gioiTinh");
            String diaChi = result.getString("diaChi");
            String sdt = result.getString("sdt");
            String email = result.getString("email");
            boolean quanLyVien = result.getBoolean("quanLyVien");
            
            NhanVien nv = new NhanVien(maNV,hoTen,gioiTinh,diaChi,sdt,email,quanLyVien);
            if(!dsNV.contains(nv))
            {
                dsNV.add(nv);
            }
            
        }
        conn.close();
    }
    
    public boolean themNhanVien(NhanVien nv) throws SQLException
    {
        Connection conn = DatabaseConnection.getConnection();
        
        String query = "select Count(*) from NhanVien where ma = ?";
        
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, nv.getMaNV());
        ResultSet rs = pstmt.executeQuery(query);
        
        if(dsNV.contains(nv))
        {
            return false;
            
        }
        dsNV.add(nv);
        return true;
    }
    
    public boolean xoaNhanVien(String maNV)
    {
        for (NhanVien nhanVien : dsNV) {
            if(nhanVien.getMaNV().equalsIgnoreCase(maNV))
            {
                dsNV.remove(nhanVien);
                return true;
            }
        }
        return false;
    }
    
    public NhanVien timTheoMaNhanVien(String maNV)
    {
        for (NhanVien nhanVien : dsNV) {
            if(nhanVien.getMaNV().equalsIgnoreCase(maNV))
            {
                return nhanVien;
            }
        }
        return null;
    }
    
    public ArrayList<NhanVien> timTheoTenNhanVien(String hoTen)
    {
        ArrayList<NhanVien> list = new ArrayList<NhanVien>();
        for (NhanVien nhanVien : dsNV) {
              if(nhanVien.getHoTenNV().equalsIgnoreCase(hoTen))
              {
                  list.add(nhanVien);
              }
        }
        return list;
    }
    
    public boolean suaNhanVien(NhanVien nvCu,NhanVien nvMoi)
    {
        if(!dsNV.contains(nvCu))
        {
            return false;
        }
        dsNV.set(dsNV.indexOf(nvCu), nvMoi);
        return true;
    }
    
    public ArrayList<NhanVien> layDanhSach()
    {
        return dsNV;
    }

    @Override
    public String toString() {
        return "DanhSachNhanVien{" + "dsNV=" + dsNV + '}';
    }
    
}