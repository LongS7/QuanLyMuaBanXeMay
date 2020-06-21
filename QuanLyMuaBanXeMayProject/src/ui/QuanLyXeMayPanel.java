package quanlymuabanxemay;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.table.DefaultTableModel;

public class QuanLyXeMayPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 14);
	private final Color HEADER_COLOR = new Color(0x1E1346);
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
	private JTable tableXeMay;
	private JScrollPane scroll;
	private DefaultTableModel defaultTable;
	private JPanel pnlTitle;
	private JButton btnSearch;
	private JTextField txtSearch;
	private JRadioButton radTimTheoMa;
	private JRadioButton radTimTheoHang;
	private JRadioButton radTimTheoDungTich;
	public QuanLyXeMayPanel() {
		
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());	
		setLookAndFeel();
		addEvent();		
		addNorth();
		addCenter();
		addEast();
		
	}
	public static void main(String[] args) {
		QuanLyXeMayPanel abc = new QuanLyXeMayPanel();
		JFrame frame = new JFrame();
		frame.setLocationRelativeTo(null);
		frame.setTitle("test");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(abc, BorderLayout.NORTH);
		frame.getRootPane().setWindowDecorationStyle(JRootPane.FRAME);
		frame.pack();
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

		
		JComboBox<String> ckbHangXe = new JComboBox<String>();
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
		
		this.add(boxNorth, BorderLayout.NORTH);
	}
	private void addCenter() {
		
		String[] header = {"Mã xe", "Tên xe", "Loại xe", "Hãng xe", "Dung tích", "Màu xe", "Nước sản xuất", "Số lượng tồn"};
		defaultTable = new DefaultTableModel(header, 0);
		tableXeMay = new JTable(defaultTable);
		tableXeMay.setFont(NORMAL_FONT);
		scroll = new JScrollPane(tableXeMay, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		
		this.add(scroll, BorderLayout.CENTER);
	}
	private void addEast() {
		
		Box boxButton;
		
		boxButton = Box.createVerticalBox();
		boxButton.add(Box.createVerticalStrut(100));
		btnThem = addButtonTo(boxButton, "Thêm");
		btnSua = addButtonTo(boxButton, "Sửa");
		btnXoa = addButtonTo(boxButton, "Xoá");
		btnQuayLai = addButtonTo(boxButton, "Quay lại");
		
		this.add(boxButton, BorderLayout.EAST);
	}
	public void focus() {
		txtSearch.requestFocus();
	}
	
	private void addEvent() {
	}

	public void actionPerformed(ActionEvent e) {
		
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
}
