package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachXeMay;
import entity.XeMay;

public class QuanLyXeMayPanel extends JPanel implements ActionListener ,MouseListener{

	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private DanhSachXeMay listXeMay;
	private JTextField txtMaXe;
	private JTextField txtLoaiXe;
	private JTextField txtDungTich;
	private JTextField txtTenXe;
	private JTextField txtNuocSanXuat;
	private JTextField txtSoLuongTon;
	private JTextField txtDonGia;
	private JTextField txtMauXe;
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnQuayLai;
	private JButton btnXoaTrang;
	private JTable tableXeMay;
	private JScrollPane scroll;
	private DefaultTableModel defaultTable;
	private JPanel pnlTitle;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoHang;
	private JRadioButton radTimTheoDungTich;
	private JComboBox<String> ckbHangXe;
	public QuanLyXeMayPanel() {
		
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());	
		setLookAndFeel();
		addEvent();		
		addNorth();
		addCenter();
		addEast();
		
		listXeMay = new DanhSachXeMay();
		tableXeMay.addMouseListener(this);
		btnSearch.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnSua.addActionListener(this);
		btnQuayLai.addActionListener(this);
		
	}
	private void addNorth() {
		Box boxNorth, boxSearch, boxLineRad;
		btnSearch = new JButton("Tìm");
		btnSearch.setFont(NORMAL_FONT);
		txtSearch = new JTextField();
		pnlTitle = new JPanel();
		JLabel lblHeader = new JLabel("QUẢN LÝ XE MÁY");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		
		JLabel lblTimTheoMa = new JLabel("Tìm theo mã xe");
		lblTimTheoMa.setFont(NORMAL_FONT);
		radTimTheoMa = new JRadioButton();
		JLabel lblTimTheoHang = new JLabel("Tìm theo hãng xe");
		lblTimTheoHang.setFont(NORMAL_FONT);
		radTimTheoHang = new JRadioButton();
		JLabel lblTimTheoDungTich = new JLabel("Tìm theo dung tích");
		lblTimTheoDungTich.setFont(NORMAL_FONT);
		radTimTheoDungTich = new JRadioButton();
		ButtonGroup group = new ButtonGroup();
		group.add(radTimTheoDungTich);
		group.add(radTimTheoHang);
		group.add(radTimTheoMa);
		
		boxLineRad = Box.createHorizontalBox();
		boxLineRad.add(radTimTheoMa);
		radTimTheoMa.setSelected(true);
		boxLineRad.add(lblTimTheoMa);
		boxLineRad.add(Box.createHorizontalStrut(20));
		boxLineRad.add(radTimTheoHang);
		boxLineRad.add(lblTimTheoHang);
		boxLineRad.add(Box.createHorizontalStrut(20));
		boxLineRad.add(radTimTheoDungTich);
		boxLineRad.add(lblTimTheoDungTich);
		
		boxSearch = Box.createHorizontalBox();
		boxSearch.add(Box.createHorizontalStrut(20));
		boxSearch.add(txtSearch);
		boxSearch.add(Box.createHorizontalStrut(5));
		boxSearch.add(btnSearch);
		boxSearch.add(Box.createHorizontalStrut(20));
		
		boxNorth = Box.createVerticalBox();
		pnlTitle.add(lblHeader);
		boxNorth.add(pnlTitle); 
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxSearch);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxLineRad); 
		boxNorth.add(Box.createVerticalStrut(20));
		
		Box boxLeft, boxRight, boxGroup;
		
		JLabel lblHangXe = new JLabel("Hãng xe");
		
		txtMaXe = new JTextField();
		txtTenXe = new JTextField();
		txtLoaiXe = new JTextField();
		txtDungTich = new JTextField();
		txtMauXe = new JTextField();
		txtNuocSanXuat = new JTextField();
		txtSoLuongTon = new JTextField();
		txtDonGia = new JTextField();

		
		ckbHangXe = new JComboBox<String>();
		ckbHangXe.addItem("Honda");
		ckbHangXe.addItem("Yamaha");
		ckbHangXe.addItem("Suzuki");
		ckbHangXe.addItem("Piaggio");
		ckbHangXe.addItem("SYM");
		ckbHangXe.addItem("Kawasaki");
		
		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		boxGroup = Box.createHorizontalBox();
		
		txtMaXe = addInputItemTo(boxLeft, "Mã xe");
		txtTenXe = addInputItemTo(boxRight, "Tên xe");
		txtLoaiXe = addInputItemTo(boxLeft, "Loại xe");
		txtNuocSanXuat = addInputItemTo(boxRight, "Nước sản xuất");
		txtDungTich = addInputItemTo(boxLeft, "Dung tích");
		txtMauXe = addInputItemTo(boxRight, "Màu xe");
		txtSoLuongTon = addInputItemTo(boxLeft, "Số lượng tồn");
		txtDonGia = addInputItemTo(boxRight, "Đơn giá");
		
		Box boxCkb;
		boxCkb = Box.createHorizontalBox();
		boxCkb.add(Box.createHorizontalGlue());
		boxCkb.add(lblHangXe);
		boxCkb.add(Box.createHorizontalStrut(5));
		boxCkb.add(ckbHangXe);
		boxCkb.add(Box.createHorizontalGlue());
		
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxLeft);
		boxGroup.add(Box.createHorizontalStrut(10));
		boxGroup.add(boxRight);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxNorth.add(boxGroup);
		boxNorth.add(boxCkb);
		boxNorth.add(Box.createVerticalStrut(20));
		
		this.add(boxNorth, BorderLayout.NORTH);
	}
	private void addCenter() {
		
		String[] header = {"Mã xe", "Tên xe", "Loại xe", "Hãng xe", "Dung tích", "Màu xe", "Nước sản xuất", "Số lượng tồn", "Đơn giá"};
		defaultTable = new DefaultTableModel(header, 0);
		tableXeMay = new JTable(defaultTable);
		tableXeMay.setFont(NORMAL_FONT);
		tableXeMay.setRowHeight(25);
		scroll = new JScrollPane(tableXeMay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll, BorderLayout.CENTER);
	}
	private void addEast() {
		
		Box boxButton;
		
		boxButton = Box.createVerticalBox();
		boxButton.add(Box.createVerticalStrut(50));
		btnThem = addButtonTo(boxButton, "Thêm");
		btnSua = addButtonTo(boxButton, "Sửa");
		btnXoa = addButtonTo(boxButton, "Xoá");
		btnQuayLai = addButtonTo(boxButton, "Quay lại");
		btnXoaTrang = addButtonTo(boxButton, "Xoá trắng");
		
		this.add(boxButton, BorderLayout.EAST);
	}
	public void focus() {
		txtSearch.requestFocus();
	}
	
	private void addEvent() {
	}

	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if(o.equals(btnThem))
				themXeMay();
			else if(o.equals(btnXoa))
				xoaXeMay();
			else if(o.equals(btnSua))
				suaTTXeMay();
			else if(o.equals(btnSearch) && radTimTheoDungTich.isSelected())
				timTheoDungTich();
			else if(o.equals(btnSearch) && radTimTheoHang.isSelected())
				timTheoHangXe();
			else if(o.equals(btnSearch) && radTimTheoMa.isSelected())
				timTheoMa();
			else
				xoaTrang();
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
	}
	private void setLookAndFeel() {
		for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
			if (info.getName().equals("Nimbus")) {
				try {
					UIManager.setLookAndFeel(info.getClassName());
					break;

				} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
						| UnsupportedLookAndFeelException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}
	private JButton addButtonTo(Box box, String name) {
		JButton btn = new JButton(name); 
		btn.setFont(NORMAL_FONT);
		btn.setPreferredSize(new Dimension(130, 25));
		
		Box boxButton = Box.createHorizontalBox();
		boxButton.add(Box.createHorizontalGlue());
		boxButton.add(btn);
		boxButton.add(Box.createHorizontalGlue());
		
		box.add(Box.createVerticalStrut(5));
		box.add(boxButton);
		box.add(Box.createVerticalStrut(5));
		
		return btn;
	}
	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name); label.setFont(NORMAL_FONT); label.setPreferredSize(new Dimension(90, 25));
		JTextField text = new JTextField(); text.setFont(NORMAL_FONT);
		
		Box boxItem = Box.createHorizontalBox();
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(label);
		boxItem.add(Box.createHorizontalStrut(5));
		boxItem.add(text);
		boxItem.add(Box.createHorizontalGlue());
		
		box.add(Box.createVerticalStrut(5));
		box.add(boxItem);
		box.add(Box.createVerticalStrut(5));
		
		return text;
	}
	public void loadDataToTable() {
		try {
			deleteDataInTable();
			NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
			
			List<XeMay> dsxm = listXeMay.getAll();
			for (XeMay xeMay : dsxm) {
				String donGia = nf.format(xeMay.getDonGia());
				defaultTable.addRow(new Object[] {
					xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
					xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
					xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});		
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void deleteDataInTable() {
		while(defaultTable.getRowCount() > 0) {
			defaultTable.removeRow(0);
		}
	}
	
	public void setPopupMenu(JPopupMenu popup) {
		txtMaXe.setComponentPopupMenu(popup);
		txtTenXe.setComponentPopupMenu(popup);
		txtLoaiXe.setComponentPopupMenu(popup);
		txtNuocSanXuat.setComponentPopupMenu(popup);
		txtDungTich.setComponentPopupMenu(popup);
		txtMauXe.setComponentPopupMenu(popup);
		txtSoLuongTon.setComponentPopupMenu(popup);
		txtDonGia.setComponentPopupMenu(popup);
		txtSearch.setComponentPopupMenu(popup);
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableXeMay.getSelectedRow();
		txtMaXe.setText(tableXeMay.getValueAt(row, 0).toString());
		txtTenXe.setText(tableXeMay.getValueAt(row, 1).toString());
		txtLoaiXe.setText(tableXeMay.getValueAt(row, 2).toString());
		ckbHangXe.setSelectedItem(tableXeMay.getValueAt(row, 3).toString());
		txtDungTich.setText(tableXeMay.getValueAt(row, 4).toString());
		txtMauXe.setText(tableXeMay.getValueAt(row, 5).toString());
		txtNuocSanXuat.setText(tableXeMay.getValueAt(row, 6).toString());
		txtSoLuongTon.setText(tableXeMay.getValueAt(row, 7).toString());
		txtDonGia.setText(tableXeMay.getValueAt(row, 8).toString());
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	private void themXeMay() throws SQLException {
		XeMay xm = new XeMay(txtMaXe.getText().trim(),
				txtTenXe.getText().trim(), txtLoaiXe.getText().trim(),
				ckbHangXe.getSelectedItem().toString(), Integer.parseInt(txtDungTich.getText()),
				txtMauXe.getText().trim(), txtNuocSanXuat.getText().trim(),
				Integer.parseInt(txtSoLuongTon.getText()), Double.parseDouble(txtDonGia.getText()));
		if(listXeMay.themXeMay(xm)) {
			loadDataToTable();
			xoaTrang();
			JOptionPane.showMessageDialog(this, "Thêm thành công");
		}else 
			JOptionPane.showMessageDialog(this, "Thêm không thành công");
	}
	private void xoaXeMay() throws SQLException{
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn xe cần xoá");
		else {
			int replay = JOptionPane.showConfirmDialog(this, "Bạn chó chắc muốn xoá dòng này!!", "Cảnh báo", JOptionPane.YES_NO_OPTION);
			if(replay == JOptionPane.YES_OPTION) {
				List<XeMay> dsxm = listXeMay.getAll();
				int rows = tableXeMay.getSelectedRow();
				if(rows >=0 || rows < dsxm.size()) {
					XeMay xm = dsxm.get(rows);
					if(listXeMay.xoaXeMay(xm)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xoá thành công");
					}else
						JOptionPane.showMessageDialog(this, "Xoá không thành công");
				}
			}
			return;
		}
	}
	private void suaTTXeMay() throws SQLException {
		int row = tableXeMay.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn xe cần sửa");
		else {
			XeMay xm = new XeMay(txtMaXe.getText().trim(),
					txtTenXe.getText().trim(), txtLoaiXe.getText().trim(),
					ckbHangXe.getSelectedItem().toString(), Integer.parseInt(txtDungTich.getText()),
					txtMauXe.getText().trim(), txtNuocSanXuat.getText().trim(),
					Integer.parseInt(txtSoLuongTon.getText()), Double.parseDouble(txtDonGia.getText()));
			if(!xm.getMaXe().equalsIgnoreCase(tableXeMay.getValueAt(row, 0).toString()))
				JOptionPane.showMessageDialog(this, "Không được sửa mã xe máy!!");
			else {
				if(listXeMay.suaTTXeMay(xm)) {
					loadDataToTable();
					JOptionPane.showMessageDialog(this, "Sửa thành công");
			}else 
				JOptionPane.showMessageDialog(this, "Sửa không thành công");
			}
		}
	}
	private void xoaTrang() {
		txtMaXe.setText("");
		txtTenXe.setText("");
		txtLoaiXe.setText("");
		ckbHangXe.setSelectedItem("");
		txtDungTich.setText("");
		txtMauXe.setText("");
		txtNuocSanXuat.setText("");
		txtSoLuongTon.setText("");
		txtDonGia.setText("");
		txtMaXe.requestFocus();
	}
	private void timTheoMa() throws SQLException{
		if(txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			XeMay xeMay = new XeMay();
			xeMay = listXeMay.timTheoMa(txtSearch.getText());
			if(xeMay == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				String donGia = nf.format(xeMay.getDonGia());
				defaultTable.addRow(new Object[] {
						xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
						xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
						xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
			}
		}
	}
	private void timTheoDungTich() throws SQLException {
		if(txtSearch.getText().equals(""))
			loadDataToTable();
		else {
			List<XeMay> dsxm = new ArrayList<XeMay>();
			dsxm = listXeMay.timTheoDungTich(Integer.parseInt(txtSearch.getText()));
			if(dsxm == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy!");
			else {
				deleteDataInTable();
				NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
				for (XeMay xeMay : dsxm) {
					String donGia = nf.format(xeMay.getDonGia());
					defaultTable.addRow(new Object[] {
							xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
							xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
							xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
				}
			}
		}
	}
		private void timTheoHangXe() throws SQLException {
			if(txtSearch.getText().equals(""))
				loadDataToTable();
			else {
				List<XeMay> dsxm = new ArrayList<XeMay>();
				dsxm = listXeMay.timTheoHangXe(txtSearch.getText());
				if(dsxm == null)
					JOptionPane.showMessageDialog(this, "Không tìm thấy!");
				else {
					deleteDataInTable();
					NumberFormat nf = NumberFormat.getInstance(new Locale("vn", "VN"));
					for (XeMay xeMay : dsxm) {
						String donGia = nf.format(xeMay.getDonGia());
						defaultTable.addRow(new Object[] {
								xeMay.getMaXe(), xeMay.getTenXe(), xeMay.getLoaiXe(),
								xeMay.getHangXe(), xeMay.getDungTich(), xeMay.getMauXe(),
								xeMay.getNuocSanXuat(), xeMay.getSoLuongTon(), donGia});
					}
				}
			}
	}
}
