package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

import dao.DanhSachKhachHang;
import entity.HoaDon;
import entity.KhachHang;
import entity.XeMay;

public class QuanLyKhachHangPanel extends JPanel implements ActionListener,MouseListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtTimKiem;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtEmail;
	private JTextField txtSdt;
	private JButton btnTimKiem;
	private JButton btnThem;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnQuayLai;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoTen;
	private ButtonGroup buttonGroup;
	private JTable tableKhachHang;
	private DefaultTableModel modelKhachHang;
	private JComboBox<String> jcbGioiTinh;

	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private DanhSachKhachHang dsKH;

	public QuanLyKhachHangPanel() {
		setLookAndFeel();
		setSize(500, 600);
		// north
		JPanel pnlNorth = new JPanel();
		pnlNorth.setLayout(new BoxLayout(pnlNorth, BoxLayout.Y_AXIS));

		JLabel lblTitle = new JLabel("Quản lý khách hàng");
		lblTitle.setFont(new Font("Times new roman", Font.BOLD, 20));
		JPanel pnlTitle = new JPanel();
		pnlTitle.add(lblTitle);
		JPanel pnlTimKiem = new JPanel();
		txtTimKiem = new JTextField(50);
		pnlTimKiem.add(txtTimKiem);
		btnTimKiem = new JButton("Tìm kiếm");
		pnlTimKiem.add(btnTimKiem);

		JPanel pnlTimTuyChon = new JPanel();
		pnlTimTuyChon.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 5));
		radTimTheoMa = new JRadioButton("Tìm theo mã");
		radTimTheoMa.setSelected(true);
		radTimTheoTen = new JRadioButton("Tìm theo tên");
		buttonGroup = new ButtonGroup();
		buttonGroup.add(radTimTheoMa);
		buttonGroup.add(radTimTheoTen);
		pnlTimTuyChon.add(radTimTheoMa);
		pnlTimTuyChon.add(radTimTheoTen);

		pnlNorth.add(pnlTitle);
		pnlNorth.add(pnlTimKiem);
		pnlNorth.add(pnlTimTuyChon);

		// center
		Box boxNorth = Box.createVerticalBox();

		pnlNorth.add(boxNorth);
		/// line1
		Box box1 = Box.createHorizontalBox();
		box1.add(Box.createHorizontalStrut(20));
		JLabel lblMa = new JLabel("Mã khách hàng");
		box1.add(lblMa);
		box1.add(Box.createHorizontalStrut(10));
		txtMa = new JTextField();
		box1.add(txtMa);
		box1.add(Box.createHorizontalStrut(20));
		JLabel lblTen = new JLabel("Tên khách hàng");
		box1.add(lblTen);
		box1.add(Box.createHorizontalStrut(10));
		txtTen = new JTextField();
		box1.add(txtTen);
		box1.add(Box.createHorizontalStrut(20));

		/// line 2
		Box box2 = Box.createHorizontalBox();
		box2.add(Box.createHorizontalStrut(20));
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(lblMa.getPreferredSize());
		JLabel lblGioiTinh = new JLabel("Giới tính");
		String[] items = { "Nam", "Nữ" };
		jcbGioiTinh = new JComboBox<String>(items);
		box2.add(lblDiaChi);
		box2.add(Box.createHorizontalStrut(10));
		txtDiaChi = new JTextField();
		box2.add(txtDiaChi);
		box2.add(Box.createHorizontalStrut(20));
		box2.add(lblGioiTinh);
		box2.add(jcbGioiTinh);
		box2.add(Box.createHorizontalStrut(20));

		/// line 3
		Box box3 = Box.createHorizontalBox();
		box3.add(Box.createHorizontalStrut(20));
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setPreferredSize(lblMa.getPreferredSize());
		JLabel lblSdt = new JLabel("Số điện thoại");
		box3.add(lblEmail);
		box3.add(Box.createHorizontalStrut(10));
		txtEmail = new JTextField();

		box3.add(txtEmail);
		box3.add(Box.createHorizontalStrut(10));
		box3.add(lblSdt);
		box3.add(Box.createHorizontalStrut(10));
		txtSdt = new JTextField();
		box3.add(txtSdt);
		box3.add(Box.createHorizontalStrut(20));

		boxNorth.add(box1);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(box2);
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(box3);

		// south
		Box boxCenter = Box.createHorizontalBox();

		String[] header = { "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Giới tính", "Số điện thoại", "Email" };
		modelKhachHang = new DefaultTableModel(header, 0);
		tableKhachHang = new JTable(modelKhachHang);
		tableKhachHang.setRowHeight(25);
		tableKhachHang.setFont(NORMAL_FONT);
		JScrollPane pane = new JScrollPane(tableKhachHang);

		boxCenter.add(pane);

//		JPanel pnlRight = new JPanel();
//		pnlRight.setLayout(new BoxLayout(pnlRight, BoxLayout.Y_AXIS));

		Box boxEast = Box.createVerticalBox();

		btnThem = new JButton("Thêm");
		btnSua = new JButton("Sửa");
		btnXoa = new JButton("Xóa");
		btnQuayLai = new JButton("Quay lại");
		boxEast.add(btnThem);
//		boxRight.add(Box.createVerticalStrut(20));
		boxEast.add(btnSua);
		boxEast.add(btnXoa);
		boxEast.add(btnQuayLai);
		btnThem.setMaximumSize(btnQuayLai.getMaximumSize());
		btnXoa.setMaximumSize(btnQuayLai.getMaximumSize());
		btnSua.setMaximumSize(btnQuayLai.getMaximumSize());

		// set font
		txtTimKiem.setFont(NORMAL_FONT);
		btnTimKiem.setFont(NORMAL_FONT);
		lblMa.setFont(NORMAL_FONT);
		txtMa.setFont(NORMAL_FONT);
		lblTen.setFont(NORMAL_FONT);
		txtTen.setFont(NORMAL_FONT);
		lblDiaChi.setFont(NORMAL_FONT);
		txtDiaChi.setFont(NORMAL_FONT);
		lblGioiTinh.setFont(NORMAL_FONT);
		// items
		lblEmail.setFont(NORMAL_FONT);
		txtEmail.setFont(NORMAL_FONT);
		lblSdt.setFont(NORMAL_FONT);
		txtSdt.setFont(NORMAL_FONT);
		btnThem.setFont(NORMAL_FONT);
		btnSua.setFont(NORMAL_FONT);
		btnXoa.setFont(NORMAL_FONT);
		btnQuayLai.setFont(NORMAL_FONT);

		this.setLayout(new BorderLayout());
		this.add(pnlNorth, BorderLayout.NORTH);
		this.add(boxCenter, BorderLayout.CENTER);
		this.add(boxEast, BorderLayout.EAST);
		this.setPreferredSize(new Dimension(500, 600));
		
		dsKH = new DanhSachKhachHang();
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnQuayLai.addActionListener(this);
		btnTimKiem.addActionListener(this);
		tableKhachHang.addMouseListener(this);

	}

	private void setFontComponent(JComponent c) {
		c.setFont(NORMAL_FONT);
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

	public void focus() {
		txtTimKiem.requestFocus();
	}

	public void loadDataToTable() {
		
		try {
			deleteDataInTable();
			dsKH.getAll();

			List<KhachHang> list = dsKH.getAll();

			for (KhachHang item : list) {
				String[] row = { item.getMaKH(), item.getHoTenKH(), item.getDiaChiKH(),
						item.isGioiTinhKH() ? "Nam" : "Nữ", item.getSdtKH(), item.getEmailKH()};

				modelKhachHang.addRow(row);
			}

		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);

		}
	}

	private void deleteDataInTable() {
		while (modelKhachHang.getRowCount() > 0) {
			modelKhachHang.removeRow(0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		try {
			if(o.equals(btnThem))
				themKhachHang();
			else if(o.equals(btnXoa))
				xoaKhachHang();
			else if(o.equals(btnSua))
				suaKhachHang();
			else if(o.equals(btnTimKiem) && radTimTheoMa.isSelected()) {
				timTheoMa();
			}
			else if(o.equals(btnTimKiem) && radTimTheoTen.isSelected())
				timTheoTen();
				
		}catch(Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
		}
		
	}
	
	
	private void timTheoTen() throws SQLException {
		if(txtTimKiem.getText().trim().equals(""))
			loadDataToTable();
		else {
			List<KhachHang> dskh = new ArrayList<KhachHang>();
			dskh = dsKH.timTheoTenKhachHang(txtTimKiem.getText().trim());
			if(dskh == null){
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng");
			}
			else {
				deleteDataInTable();
				for(KhachHang kh : dskh) {
					modelKhachHang.addRow(new Object[] {kh.getMaKH(),kh.getHoTenKH(),
							kh.getDiaChiKH(),kh.isGioiTinhKH() ? "Nam" : "Nữ",kh.getSdtKH(),kh.getEmailKH()});
				}
			}
		}
		
	}

	private void timTheoMa() throws SQLException {
		if(txtTimKiem.getText().trim().equals(""))
			loadDataToTable();
		else {
			KhachHang kh = new KhachHang();
			kh = dsKH.timTheoMa(txtTimKiem.getText().trim());
			if(kh == null)
				JOptionPane.showMessageDialog(this, "Không tìm thấy khách hàng!");
			else {
				deleteDataInTable();
				modelKhachHang.addRow(new Object[] {kh.getMaKH(),kh.getHoTenKH(),
						kh.getDiaChiKH(),kh.isGioiTinhKH() ? "Nam" : "Nữ",kh.getSdtKH(),kh.getEmailKH()});
			}
		}
		
	}

	private void suaKhachHang() throws SQLException{
		int row = tableKhachHang.getSelectedRow();
		if(row == - 1)
			JOptionPane.showMessageDialog(this, "Chọn khách hàng cần sửa");
		else {
			String ma = txtMa.getText().trim();
			String hoTen = txtTen.getText().trim();
			boolean gioiTinh = jcbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false;
			String diaChi = txtDiaChi.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			if(!kh.getMaKH().equalsIgnoreCase(tableKhachHang.getValueAt(row, 0).toString()))
				JOptionPane.showMessageDialog(this, "Không được sửa mã xe máy!!");
			else {
				if(dsKH.suaTTKhachHang(kh)) {
					loadDataToTable();
					JOptionPane.showMessageDialog(this, "Sửa thành công");
				}
				else
					JOptionPane.showMessageDialog(this, "Sửa không thành công");
			}
			
			
		}
		
	}

	private void xoaKhachHang() throws SQLException {
		int row = tableKhachHang.getSelectedRow();
		if(row == -1)
			JOptionPane.showMessageDialog(this, "Phải chọn khách hàng cần xóa");
		else {
			int replay = JOptionPane.showConfirmDialog(this, "Bạn có chắc muốn xóa dòng này ?",
					"Cảnh báo",JOptionPane.YES_NO_OPTION);
			if(replay == JOptionPane.YES_OPTION) {
				List<KhachHang> dskh = dsKH.getAll();
				int rows = tableKhachHang.getSelectedRow();
				if(rows >= 0 || rows < dskh.size()) {
					KhachHang kh = dskh.get(rows);
					if(dsKH.xoaKhachHang(kh)) {
						loadDataToTable();
						xoaTrang();
						JOptionPane.showMessageDialog(this, "Xóa thành công");
					}else
						JOptionPane.showMessageDialog(this, "Xóa không thành công");
				}
			}
		}
		
	}

	private void themKhachHang() throws SQLException { //////////////
		if(validData()) {
			String ma = txtMa.getText().trim();
			String hoTen = txtTen.getText().trim();
			boolean gioiTinh = jcbGioiTinh.getSelectedItem().toString().equals("Nam") ? true : false;
			String diaChi = txtDiaChi.getText().trim();
			String sdt = txtSdt.getText().trim();
			String email = txtEmail.getText().trim();
			
			KhachHang kh = new KhachHang(ma, hoTen, gioiTinh, diaChi, sdt, email);
			if(dsKH.themKhachHang(kh)) {
				loadDataToTable();
				xoaTrang();
				JOptionPane.showMessageDialog(this, "Thêm thành công");
			}
			else
				JOptionPane.showMessageDialog(this, "Thêm thất bại");
		}
		
	}

	private void xoaTrang() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		jcbGioiTinh.setSelectedItem("Nam");
		txtEmail.setText("");
		txtSdt.setText("");
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableKhachHang.getSelectedRow();
		txtMa.setText(tableKhachHang.getValueAt(row, 0).toString());
		txtTen.setText(tableKhachHang.getValueAt(row, 1).toString());
		txtDiaChi.setText(tableKhachHang.getValueAt(row, 2).toString());
		jcbGioiTinh.setSelectedItem(tableKhachHang.getValueAt(row, 3).toString().equals("Nam") ? true : false);
		
		Object sdt = tableKhachHang.getValueAt(row, 4);
		Object email = tableKhachHang.getValueAt(row, 5);
		if(sdt != null)
			txtSdt.setText(sdt.toString());
		if(email != null)
			txtEmail.setText(email.toString());
		
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
	
	private boolean validData() {
		String ma = txtMa.getText().trim();
		String hoTen = txtTen.getText().trim();
		String diaChi = txtDiaChi.getText().trim();
		String std = txtSdt.getText().trim();
		String email = txtEmail.getText().trim();
	
		
		Pattern pattern = Pattern.compile("KH[0-9]{2,5}", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher(ma);
		boolean match_ma = matcher.matches();
		if(ma.length() < 1) {
			JOptionPane.showMessageDialog(this, "Mã khách hàng không được để trống");
			return false;
		}
		else if(!match_ma){
			JOptionPane.showMessageDialog(this, "Mã khách hàng không đúng định dạng");
			return false;
		}
		else if(hoTen.length() < 1) {
			JOptionPane.showMessageDialog(this, "Tên không được để trống");
			return false;
		}
		else if(!hoTen.matches("[a-zA-Z ]+")) {
			JOptionPane.showMessageDialog(this, "Tên không chứa số và kí tự đặc biệt");
			return false;
		}
		else if(diaChi.length() < 1) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống");
			return false;
		}
		else if(!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			JOptionPane.showMessageDialog(this, "Email không đúng định dạng");
			return false;
		}
		else if(!std.matches("0[0-9]{9}")) {
			JOptionPane.showMessageDialog(this, "Số điện thoại không đúng");
			return false;
		}
		
		return true;
	}
	public void setPopupMenu(JPopupMenu popup) {
		txtMa.setComponentPopupMenu(popup);
		txtTen.setComponentPopupMenu(popup);
		txtDiaChi.setComponentPopupMenu(popup);
		txtEmail.setComponentPopupMenu(popup);
		txtSdt.setComponentPopupMenu(popup);
	}
}
