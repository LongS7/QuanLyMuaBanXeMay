package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachCTHD;
import dao.DanhSachHoaDon;
import dao.DanhSachKhachHang;
import dao.DanhSachNhanVien;
import dao.DanhSachXeMay;
import dao.DatabaseConnection;
import entity.ChiTietHD;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.XeMay;

public class QuanLyHoaDonPanel extends JPanel implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 14);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtMaHD;
	private JTextField txtMaNV;
	private JTextField txtMaKH;
	private JTextField txtNgayLap;
	private JTable tableCTHD;
	private DefaultTableModel modelCTHD;
	private JButton btnThemCTHD;
	private JButton btnXoaCTHD;
	private JButton btnSuaCTHD;
	private JTextField txtMaXeMay;
	private JTextField txtSoLuong;
	private DefaultTableModel modelHoaDon;
	private JTable tableHoaDon;
	private JButton btnThemHD;
	private JTextField txtTimKiem;
	private JButton btnTimKiem;
	private JRadioButton radMaHD;
	private JRadioButton radMaKH;
	private JRadioButton radMaNV;
	private JButton btnXoaHD;
	private JButton btnSuaHD;
	private JButton btnXoaRong;
	private DanhSachHoaDon dsHD;
	private DanhSachCTHD dsCTHD;

	public QuanLyHoaDonPanel() {
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());

		setLookAndFeel();
		addNorth();
		addCenter();
		addEast();

		addEvent();

		dsHD = new DanhSachHoaDon();
		dsCTHD = new DanhSachCTHD();

	}

	private void deleteDataInTable() {
		while (modelHoaDon.getRowCount() > 0) {
			modelHoaDon.removeRow(0);
		}
	}

	private void deleteDataInTableCTHD() {
		while (modelCTHD.getRowCount() > 0) {
			modelCTHD.removeRow(0);
		}
	}

	public void loadAllDataToTable() {
		try {
			deleteDataInTable();

			ArrayList<HoaDon> list = dsHD.getAll();

			for (HoaDon item : list) {
				String[] row = { item.getMaHD(), item.getNhanVien().getMaNV(), item.getKhachHang().getMaKH(),
						item.getNgayLap().toString() };

				modelHoaDon.addRow(row);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	public void loadDataToChiTietHDTable(String ma) {
		try {

			deleteDataInTableCTHD();

			ArrayList<ChiTietHD> list = dsCTHD.timTheoMaHD(ma);

			for (ChiTietHD item : list) {
				XeMay xm = item.getXeMay();
				modelCTHD.addRow(new Object[] { xm.getMaXe(), item.getSoLuong(),
						NumberFormat.getInstance().format(item.getDonGia()),
						NumberFormat.getInstance().format(item.getSoLuong() * item.getDonGia()) });
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi trong khi truy xuất dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private void loadDataToTable(ArrayList<HoaDon> data) {
		deleteDataInTable();

		for (HoaDon item : data) {
			String[] row = { item.getMaHD(), item.getNhanVien().getMaNV(), item.getKhachHang().getMaKH(),
					item.getNgayLap().toString() };

			modelHoaDon.addRow(row);
		}
	}

	public void focus() {
		txtTimKiem.requestFocus();
	}

	public void setPopupMenu(JPopupMenu popup) {
		txtMaHD.setComponentPopupMenu(popup);
		txtMaKH.setComponentPopupMenu(popup);
		txtMaNV.setComponentPopupMenu(popup);
		txtMaXeMay.setComponentPopupMenu(popup);
		txtNgayLap.setComponentPopupMenu(popup);
		txtTimKiem.setComponentPopupMenu(popup);
		txtSoLuong.setComponentPopupMenu(popup);
		tableCTHD.setComponentPopupMenu(popup);
		tableHoaDon.setComponentPopupMenu(popup);
	}

	private void addEvent() {
		btnThemHD.addActionListener(this);
		btnTimKiem.addActionListener(this);
		btnXoaHD.addActionListener(this);
		btnXoaRong.addActionListener(this);
		btnSuaHD.addActionListener(this);
		btnThemCTHD.addActionListener(this);
		btnXoaCTHD.addActionListener(this);
		btnSuaCTHD.addActionListener(this);
		tableHoaDon.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				changeText();
				updateCTHDTable();
			}
		});
		tableCTHD.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				changeCTHDText();
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnThemHD))
			themHoaDon();
		if (o.equals(btnXoaHD))
			xoaHoaDon();
		if (o.equals(btnXoaRong))
			xoaRong();
		if (o.equals(btnTimKiem))
			timKiem();
		if (o.equals(btnSuaHD))
			suaHoaDon();
		if (o.equals(btnThemCTHD))
			themCTHD();
		if (o.equals(btnSuaCTHD))
			suaCTHD();
		if (o.equals(btnXoaCTHD))
			xoaCTHD();
	}

	private void xoaCTHD() {
		int i;
		while ((i = tableCTHD.getSelectedRow()) != -1) {
			String maHD = tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();
			try {
				if(dsCTHD.xoaCTHD(maHD, tableCTHD.getValueAt(i, 0).toString())) {
					JOptionPane.showMessageDialog(this, "Xoá thành công!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
					modelCTHD.removeRow(i);
				}
				else {
					JOptionPane.showMessageDialog(this, "Xoá không thành công!!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				}
				
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi xóa dữ liệu!!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}
	}

	private void suaCTHD() {
		
		
	}

	private void themCTHD() {
		if(tableHoaDon.getSelectedRow() == -1) {
			JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
			return;
		}
		String maHD = tableHoaDon.getValueAt(tableHoaDon.getSelectedRow(), 0).toString();
		
		String maXe = txtMaXeMay.getText().trim();
		int soLuong = 0;
		double donGia = 0;
		XeMay xm = null;
		HoaDon hd = null;

		try {
			soLuong = Integer.parseInt(txtSoLuong.getText());
			
			xm = new DanhSachXeMay().timTheoMa(maXe);
			if(xm == null) {
				JOptionPane.showMessageDialog(this, "Mã xe máy không tồn tại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			donGia = xm.getDonGia();
			
			hd = new DanhSachHoaDon().timTheoMaHD(maHD).get(0);
			
			if(hd == null) {
				JOptionPane.showMessageDialog(this, "Mã hóa đơn không tồn tại!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				return;
			}

			ChiTietHD cthd = new ChiTietHD(soLuong, donGia, hd, xm);
			
			if(dsCTHD.themCTHD(cthd)) {
				JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
				modelCTHD.addRow(new Object[] {
					cthd.getXeMay().getMaXe(), cthd.getSoLuong(), cthd.getDonGia(), cthd.tinhThanhTien()
				});
			
			} else {
				JOptionPane.showMessageDialog(this, "Đã tồn tại CTHD này!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}
			
		}catch(NumberFormatException e) {
			JOptionPane.showMessageDialog(this, "Số lượng phải nhập số nguyên lớn hơn 0!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi truy xuất dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
	}

	private void suaHoaDon() {
		if (!isValidData())
			return;
		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();
		String maNV = txtMaNV.getText().trim();
		String ngayLap = txtNgayLap.getText().trim();

		try {
			NhanVien nv = new DanhSachNhanVien().timTheoMaNhanVien(maNV);
			if (nv == null) {
				JOptionPane.showMessageDialog(this, "Mã nhân viên không tồn tại!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
				txtMaNV.requestFocus();
				return;
			}
			KhachHang kh = new DanhSachKhachHang().timKHTheoMa(maKH);
			if (kh == null) {
				JOptionPane.showMessageDialog(this, "Mã khách hàng không tồn tại!", "Lỗi", JOptionPane.WARNING_MESSAGE);
				txtMaKH.requestFocus();
				return;
			}
			HoaDon hd = new HoaDon(maHD, nv, kh, LocalDate.parse(ngayLap));
			if (dsHD.suaHoaDon(maHD, hd)) {
				JOptionPane.showMessageDialog(this, "Sửa hóa đơn thành công!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(this, "Không thể sửa hóa đơn!", "Thông báo",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void updateCTHDTable() {
		int selected = tableHoaDon.getSelectedRow();
		if (selected == -1 || tableHoaDon.getSelectedRows().length > 1) {
			deleteDataInTableCTHD();
			return;
		}

		String maHD = (String) tableHoaDon.getValueAt(selected, 0);

		loadDataToChiTietHDTable(maHD);
	}

	private void timKiem() {
		if (txtTimKiem.getText().trim().isEmpty()) {
			loadAllDataToTable();
			return;
		}

		ArrayList<HoaDon> list = new ArrayList<HoaDon>();
		if (radMaHD.isSelected()) {
			try {
				list = dsHD.timTheoMaHD(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaNV.isSelected()) {
			try {
				list = dsHD.timTheoMaNV(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		} else if (radMaKH.isSelected()) {
			try {
				list = dsHD.timTheoMaKH(txtTimKiem.getText().trim());

			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi khi tìm kiếm dữ liệu!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			}
		}

		loadDataToTable(list);
	}

	private void xoaRong() {
		txtMaHD.setText("");
		txtMaNV.setText("");
		txtMaKH.setText("");
		txtNgayLap.setText("");
		txtSoLuong.setText("");
		txtMaXeMay.setText("");
		txtTimKiem.setText("");
		while (tableHoaDon.getSelectedRow() != -1)
			tableHoaDon.removeRowSelectionInterval(tableHoaDon.getSelectedRow(), tableHoaDon.getSelectedRow());
		while (tableCTHD.getSelectedRow() != -1)
			tableCTHD.removeRowSelectionInterval(tableCTHD.getSelectedRow(), tableCTHD.getSelectedRow());

	}

	private void changeCTHDText() {
		int selected = tableCTHD.getSelectedRow();
		if (selected == -1)
			return;
		if (tableHoaDon.getSelectedRows().length > 1) {
			txtMaXeMay.setText("");
			txtSoLuong.setText("");
			return;
		}

		txtMaXeMay.setText(tableCTHD.getValueAt(selected, 0).toString());
		txtSoLuong.setText(tableCTHD.getValueAt(selected, 1).toString());
	}

	private void changeText() {
		int selected = tableHoaDon.getSelectedRow();
		if (selected == -1)
			return;
		if (tableHoaDon.getSelectedRows().length > 1) {
			txtMaHD.setText("");
			txtMaNV.setText("");
			txtMaKH.setText("");
			txtNgayLap.setText("");
			txtSoLuong.setText("");
			txtMaXeMay.setText("");
			return;
		}

		txtMaHD.setText((String) tableHoaDon.getValueAt(selected, 0));
		txtMaNV.setText((String) tableHoaDon.getValueAt(selected, 1));
		txtMaKH.setText((String) tableHoaDon.getValueAt(selected, 2));
		txtNgayLap.setText((String) tableHoaDon.getValueAt(selected, 3));
	}

	private void xoaHoaDon() {
		int selectedRow = tableHoaDon.getSelectedRow();

		if (selectedRow == -1) {
			JOptionPane.showMessageDialog(this,
					"Bạn chưa chọn hóa đơn cần xóa, nhấp chọn vào hóa đơn cần xóa trong bảng!", "Thông báo",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa!", "Xác nhận",
				JOptionPane.OK_CANCEL_OPTION) == JOptionPane.CANCEL_OPTION)
			return;

		int count = 0;
		while (tableHoaDon.getSelectedRow() != -1) {
			int selected = tableHoaDon.getSelectedRow();
			String maHD = (String) tableHoaDon.getValueAt(selected, 0);

			try {
				if (dsHD.xoaHoaDon(maHD)) {
					modelHoaDon.removeRow(selected);
					count++;
				}
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi trong khi xóa!", "Lỗi", JOptionPane.ERROR_MESSAGE);
				return;
			}
		}

		JOptionPane.showMessageDialog(this, "Xóa thành công " + count + " hóa đơn!", "Thông báo",
				JOptionPane.INFORMATION_MESSAGE);
	}

	private boolean isValidData() {
		String maHD = txtMaHD.getText().trim();

		if (maHD.isEmpty() || !maHD.matches("HD\\d{2,3}")) {
			JOptionPane.showMessageDialog(this,
					"Mã hóa đơn không được bỏ trống và phải bắt đầu bằng 'HD', theo sau là 2 đến 3 kí tự số!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMaHD.requestFocus();
			return false;
		}

		String maKH = txtMaKH.getText().trim();

		if (maKH.isEmpty() || !maKH.matches("KH\\d{2,5}")) {
			JOptionPane.showMessageDialog(this,
					"Mã khách hàng không được bỏ trống và phải bắt đầu bằng 'KH', theo sau là 2 đến 5 kí tự số!",
					"Cảnh báo", JOptionPane.WARNING_MESSAGE);
			txtMaKH.requestFocus();
			return false;
		}

		String ngayLap = txtNgayLap.getText().trim();
		if (!ngayLap.matches("\\d{4}-\\d{1,2}-\\d{1,2}")) {
			JOptionPane.showMessageDialog(this, "Ngày lập phải theo đinh dạng YYYY-MM-DD!", "Cảnh báo",
					JOptionPane.WARNING_MESSAGE);
			txtNgayLap.requestFocus();
			return false;
		}
		return true;
	}

	private void themHoaDon() {
		if (!isValidData())
			return;

		String maHD = txtMaHD.getText().trim();
		String maKH = txtMaKH.getText().trim();

		NhanVien nv = new NhanVien(DatabaseConnection.userName.toUpperCase());
		KhachHang kh = new KhachHang(maKH);
		LocalDate ngayLap = LocalDate.now();

		HoaDon hd = new HoaDon(maHD, nv, kh, ngayLap);

		try {
			String mess = dsHD.themHoaDon(hd);

			if (mess != null) {
				JOptionPane.showMessageDialog(this, mess, "Lỗi", JOptionPane.ERROR_MESSAGE);
			} else {
				modelHoaDon.addRow(new Object[] { maHD, nv.getMaNV(), maKH, ngayLap.toString() });
				tableHoaDon.addRowSelectionInterval(modelHoaDon.getRowCount() - 1, modelHoaDon.getRowCount() - 1);
				JOptionPane.showMessageDialog(this, "Thêm thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
	}

	private void addEast() {
		Box boxEast = Box.createVerticalBox();
		this.add(boxEast, BorderLayout.EAST);

		btnThemHD = addButtonTo(boxEast, "Thêm hóa đơn", "Images/add.png");
		btnXoaHD = addButtonTo(boxEast, "Xóa hóa đơn", "Images/delete.png");
		btnSuaHD = addButtonTo(boxEast, "Sửa hóa đơn", "Images/update.png");
		btnXoaRong = addButtonTo(boxEast, "Xoá rỗng", "Images/erase.png");
	}

	private void addCenter() {
		String[] hoaDonHeader = { "Mã hóa đơn", "Mã nhân viên", "Mã khách hàng", "Ngày lập" };
		modelHoaDon = new DefaultTableModel(hoaDonHeader, 0);
		tableHoaDon = new JTable(modelHoaDon) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		tableHoaDon.setRowHeight(25);
		tableHoaDon.setFont(NORMAL_FONT);
		JScrollPane scroll = new JScrollPane(tableHoaDon);
		scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, 300));

		this.add(scroll, BorderLayout.CENTER);
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

	private JTextField addInputItemTo(Box box, String name) {
		JLabel label = new JLabel(name);
		label.setFont(NORMAL_FONT);
		if (name != "Mã xe máy" && name != "Số lượng" && name != "Đơn giá")
			label.setPreferredSize(new Dimension(90, 25));
		JTextField text = new JTextField();
		text.setFont(NORMAL_FONT);

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

	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();

		this.add(boxNorth, BorderLayout.NORTH);

		Box boxHeader = Box.createHorizontalBox();

		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxHeader);
		boxNorth.add(Box.createVerticalStrut(5));

		JLabel lblHeader = new JLabel("QUẢN LÝ HÓA ĐƠN");
		lblHeader.setForeground(HEADER_COLOR);
		lblHeader.setFont(HEADER_FONT);

		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());

		Box boxSearch = Box.createHorizontalBox();

		txtTimKiem = new JTextField();
		txtTimKiem.setFont(NORMAL_FONT);
		btnTimKiem = new JButton(new ImageIcon("Images/search.png"));
		btnTimKiem.setFont(NORMAL_FONT);

		boxSearch.add(Box.createHorizontalGlue());
		boxSearch.add(Box.createHorizontalStrut(50));
		boxSearch.add(txtTimKiem);
		boxSearch.add(Box.createHorizontalStrut(5));
		boxSearch.add(btnTimKiem);
		boxSearch.add(Box.createHorizontalStrut(50));
		boxSearch.add(Box.createHorizontalGlue());

		Box boxRadio = Box.createHorizontalBox();

		JLabel lblTimTheo = new JLabel("Tìm theo");
		radMaHD = new JRadioButton("Mã hóa đơn");
		radMaHD.setSelected(true);
		radMaNV = new JRadioButton("Mã nhân viên");
		radMaKH = new JRadioButton("Mã khách hàng");

		ButtonGroup group = new ButtonGroup();
		group.add(radMaHD);
		group.add(radMaNV);
		group.add(radMaKH);

		boxRadio.add(Box.createHorizontalGlue());
		boxRadio.add(lblTimTheo);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaHD);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaNV);
		boxRadio.add(Box.createHorizontalStrut(5));
		boxRadio.add(radMaKH);
		boxRadio.add(Box.createHorizontalGlue());

		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxSearch);
		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxRadio);
		boxNorth.add(Box.createVerticalStrut(10));

		Box boxLeft = Box.createVerticalBox();
		Box boxRight = Box.createVerticalBox();

		Box boxInput = Box.createHorizontalBox();

		boxInput.add(Box.createHorizontalStrut(10));
		boxInput.add(boxLeft);
		boxInput.add(Box.createHorizontalStrut(10));
		boxInput.add(boxRight);
		boxInput.add(Box.createHorizontalStrut(10));

		boxNorth.add(boxInput);

		txtMaHD = addInputItemTo(boxLeft, "Mã hóa đơn");
		txtMaNV = addInputItemTo(boxRight, "Mã nhân viên");
		txtMaNV.setEditable(false);
		txtMaKH = addInputItemTo(boxLeft, "Mã khách hàng");
		txtNgayLap = addInputItemTo(boxRight, "Ngày lập");

		JLabel lblChiTietHD = new JLabel("Chi tiết hóa đơn");
		lblChiTietHD.setFont(NORMAL_FONT);

		Box boxLblCTHD = Box.createHorizontalBox();
		boxLblCTHD.add(Box.createHorizontalGlue());
		boxLblCTHD.add(lblChiTietHD);
		boxLblCTHD.add(Box.createHorizontalGlue());

		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxLblCTHD);

		JPanel pnlChiTietHD = new JPanel(new BorderLayout());
		pnlChiTietHD.setBorder(BorderFactory.createLineBorder(Color.black));

		Box boxFixedCTHD = Box.createHorizontalBox();
		boxFixedCTHD.add(Box.createHorizontalStrut(100));
		boxFixedCTHD.add(pnlChiTietHD);
		boxFixedCTHD.add(Box.createHorizontalStrut(100));

		boxNorth.add(boxFixedCTHD);

		Box boxCTHDCenter = Box.createVerticalBox();
		pnlChiTietHD.add(boxCTHDCenter, BorderLayout.CENTER);

		Box boxCTHDInput = Box.createHorizontalBox();
		pnlChiTietHD.add(boxCTHDInput, BorderLayout.NORTH);

		boxCTHDInput.add(Box.createHorizontalStrut(10));
		txtMaXeMay = addInputItemTo(boxCTHDInput, "Mã xe máy");
		boxCTHDInput.add(Box.createHorizontalStrut(5));
		txtSoLuong = addInputItemTo(boxCTHDInput, "Số lượng");
		boxCTHDInput.add(Box.createHorizontalStrut(10));

		String[] cthdHeader = { "Mã xe máy", "Số lượng", "Đơn giá", "Thành tiền" };
		modelCTHD = new DefaultTableModel(cthdHeader, 0);
		tableCTHD = new JTable(modelCTHD);
		tableCTHD.setFont(NORMAL_FONT);
		JScrollPane scroll = new JScrollPane(tableCTHD);
		scroll.setPreferredSize(new Dimension(scroll.getPreferredSize().width, 150));

		boxCTHDCenter.add(Box.createVerticalStrut(10));
		boxCTHDCenter.add(scroll);

		Box boxCTHDButton = Box.createVerticalBox();
		pnlChiTietHD.add(boxCTHDButton, BorderLayout.EAST);

		boxCTHDButton.add(Box.createVerticalStrut(5));
		btnThemCTHD = addButtonTo(boxCTHDButton, "Thêm chi tiết", "Images/add.png");
		btnXoaCTHD = addButtonTo(boxCTHDButton, "Xóa chi tiết", "Images/delete.png");
		btnSuaCTHD = addButtonTo(boxCTHDButton, "Sửa chi tiết", "Images/update.png");

		boxNorth.add(Box.createVerticalStrut(20));
	}

	private JButton addButtonTo(Box box, String name, String iconPath) {
		JButton btn = new JButton(name, new ImageIcon(iconPath));
		btn.setFont(NORMAL_FONT);
		btn.setPreferredSize(new Dimension(150, 25));

		Box boxButton = Box.createHorizontalBox();
		boxButton.add(Box.createHorizontalGlue());
		boxButton.add(btn);
		boxButton.add(Box.createHorizontalGlue());

		box.add(Box.createVerticalStrut(5));
		box.add(boxButton);
		box.add(Box.createVerticalStrut(5));

		return btn;
	}

}
