package dao;

import java.util.ArrayList;

import entity.ChiTietHD;

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
	

}
