package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.sql.SQLException;
import java.util.ArrayList;

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

public class QuanLyKhachHangPanel extends JPanel {
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
	private DanhSachKhachHang dsKH = new DanhSachKhachHang();
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);

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
		JLabel lblSdt = new JLabel("Số điện thoại");
		box3.add(lblEmail);
		box3.add(Box.createHorizontalStrut(10));
		txtEmail = new JTextField();
		
		box3.add(txtEmail);
		box3.add(Box.createHorizontalStrut(20));
		box3.add(lblSdt);
		box3.add(Box.createHorizontalStrut(10));
		txtSdt = new JTextField();
		box3.add(txtSdt);
		box3.add(Box.createHorizontalStrut(20));
		Dimension d = new Dimension(100, 25);
		
		
		lblDiaChi.setPreferredSize(d);
		lblEmail.setPreferredSize(d);
		lblGioiTinh.setPreferredSize(d);
		lblMa.setPreferredSize(d);
		lblSdt.setPreferredSize(d);
		

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
			
			ArrayList<KhachHang> list = dsKH.getDsKH();
			
			for(KhachHang item : list) {
				String[] row = {item.getMaKH(), item.getHoTenKH(), item.getDiaChiKH(), item.isGioiTinhKH()? "Nam" : "Nữ",item.getSdtKH(),item.getEmailKH()};
				
				modelKhachHang.addRow(row);
			}
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
			
		}
	}
	
	private void deleteDataInTable() {
		while(modelKhachHang.getRowCount() > 0) {
			modelKhachHang.removeRow(0);
		}
	}

}
