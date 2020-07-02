package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;

import dao.DatabaseConnection;
import dao.QuanLyDangNhap;
import entity.NhanVien;
import entity.XeMay;
import dao.QuanLyHoSo;


public class ProfilePanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Arial", Font.PLAIN, 13);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtMaNhanVien;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSoDienThoai;
	private JTextField txtEmail;
	private QuanLyHoSo hoSo = new QuanLyHoSo();
	private JComboBox<String> ckbGioiTinh;
	private JComboBox<String> ckbChucVu;
	private JButton btnChinhSua;
	private JButton btnLuu;
	
	public ProfilePanel() {
		setPreferredSize(new Dimension(500,600));
		setLayout(new BorderLayout());
		setLookAndFeel();
		
		addNorth();
		
		btnChinhSua.addActionListener(this);
		btnLuu.addActionListener(this);
	}
	private void addNorth(){
		Dimension dLabel = new Dimension(90,25);
		Box boxNorth, boxGroup, boxGioiTinh, boxChucVu, boxDiaChi, boxLeft, boxRight;
		
		boxNorth = Box.createVerticalBox();
		
		JPanel pnlImage = new JPanel();
		JLabel lblImage = new JLabel(new ImageIcon("Images/dinosaur.png"));
		pnlImage.add(lblImage);
		
		JPanel pnlTitle = new JPanel();
		JLabel lblHeader = new JLabel("Hồ sơ của tôi");
		lblHeader.setFont(HEADER_FONT);
		lblHeader.setForeground(HEADER_COLOR);
		pnlTitle.add(lblHeader);
		
		JLabel lblGioiTinh = new JLabel("Giới tính");
		lblGioiTinh.setPreferredSize(dLabel);
		JLabel lblChucVu = new JLabel("Chức vụ");
		lblChucVu.setPreferredSize(dLabel);
		ckbGioiTinh = new JComboBox<String>();
		ckbGioiTinh.addItem("Nam");
		ckbGioiTinh.addItem("Nữ");
		ckbGioiTinh.setPreferredSize(new Dimension(100,25));
		ckbChucVu = new JComboBox<String>();
		ckbChucVu.addItem("Quản lý viên");
		ckbChucVu.addItem("Nhân viên");
		ckbChucVu.setPreferredSize(new Dimension(100,25));
		
		JLabel lblMaNhanVien = new JLabel("Mã nhân viên");
		lblMaNhanVien.setPreferredSize(dLabel);
		JLabel lblHoTen = new JLabel("Họ tên");
		lblHoTen.setPreferredSize(dLabel);
		JLabel lblDiaChi = new JLabel("Địa chỉ");
		lblDiaChi.setPreferredSize(dLabel);
		JLabel lblSoDienThoai = new JLabel("Số điện thoại");
		lblSoDienThoai.setPreferredSize(dLabel);
		JLabel lblEmail = new JLabel("Email");
		lblEmail.setPreferredSize(dLabel);
		
		
		txtDiaChi = new JTextField();
		txtDiaChi.setEditable(false);
		
		boxGroup = Box.createHorizontalBox();
		boxGioiTinh = Box.createHorizontalBox();
		boxChucVu = Box.createHorizontalBox();
		boxDiaChi = Box.createHorizontalBox();
		boxLeft = Box.createVerticalBox();
		boxRight = Box.createVerticalBox();
		
		boxDiaChi.add(Box.createHorizontalStrut(20));
		boxDiaChi.add(lblDiaChi);
		boxDiaChi.add(Box.createHorizontalStrut(5));
		boxDiaChi.add(txtDiaChi);
		boxDiaChi.add(Box.createHorizontalStrut(20));
		boxGioiTinh.add(lblGioiTinh); boxGioiTinh.add(ckbGioiTinh);
		boxChucVu.add(lblChucVu); boxChucVu.add(ckbChucVu);
		
		txtMaNhanVien = addInputItemTo(boxLeft, "Mã nhân viên");
		txtMaNhanVien.setEditable(false);
		txtHoTen = addInputItemTo(boxRight, "Họ tên");
		txtHoTen.setEditable(false);
		boxLeft.add(boxGioiTinh);
		ckbGioiTinh.setEnabled(false);
		boxRight.add(boxChucVu);
		ckbChucVu.setEnabled(false);
		txtSoDienThoai = addInputItemTo(boxLeft, "Số điện thoại");
		txtSoDienThoai.setEditable(false);
		txtEmail = addInputItemTo(boxRight, "Email");
		txtEmail.setEditable(false);
		
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxLeft);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.add(boxRight);
		boxGroup.add(Box.createHorizontalStrut(20));
		boxGroup.setFont(NORMAL_FONT);
		
		boxNorth.add(pnlTitle);
		boxNorth.add(pnlImage);
		boxNorth.add(Box.createVerticalStrut(20));
		boxNorth.add(boxGroup);
		boxNorth.add(boxDiaChi);
		
		Box boxButton;
		btnChinhSua = new JButton("Chỉnh sửa");
		btnChinhSua.setPreferredSize(new Dimension(100,40));
		btnChinhSua.setFont(NORMAL_FONT);
		btnLuu = new JButton("Lưu");
		btnLuu.setPreferredSize(new Dimension(100,40));
		btnLuu.setFont(NORMAL_FONT);
		
		boxButton = Box.createHorizontalBox();
		boxButton.add(btnChinhSua);
		boxButton.add(btnLuu);
		boxNorth.add(boxButton);
		
		this.add(boxNorth, BorderLayout.NORTH);
		
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
	public void loadDataFromDatabaseToPanel() {
		try {
			hoSo.getProfile();
			NhanVien nhanvien = hoSo.getNhanVien();
			txtMaNhanVien.setText(nhanvien.getMaNV());
			txtHoTen.setText(nhanvien.getHoTenNV());
			ckbGioiTinh.setSelectedItem(nhanvien.isGioiTinh()? "Nam" : "Nữ");
			txtDiaChi.setText(nhanvien.getDiaChi());
			txtSoDienThoai.setText(nhanvien.getSDT());
			txtEmail.setText(nhanvien.getEmail());
			ckbChucVu.setSelectedItem(nhanvien.isQuanLyVien()? "Quản lý viên" : "Nhân viên");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(this, "Lỗi kết nối!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o.equals(btnChinhSua))
			chinhSua();
		else if(o.equals(btnLuu))
			luu();
	}
	private void chinhSua() {
		txtHoTen.setEditable(true);
		txtDiaChi.setEditable(true);	
		txtSoDienThoai.setEditable(true);	
		txtEmail.setEditable(true);	
		ckbGioiTinh.setEnabled(true);
		ckbChucVu.setEnabled(true);
	}
	
	private void luu() {
		
		
		txtHoTen.setEditable(false);
		txtDiaChi.setEditable(false);	
		txtSoDienThoai.setEditable(false);	
		txtEmail.setEditable(false);	
		ckbGioiTinh.setEnabled(false);
		ckbChucVu.setEnabled(false);
	}
}
