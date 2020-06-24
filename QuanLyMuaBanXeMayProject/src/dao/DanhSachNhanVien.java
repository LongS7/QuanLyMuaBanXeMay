
package dao;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import entity.NhanVien;

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
    }
    
}