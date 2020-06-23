/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;


public class QuanLyNhanVienPanel extends JPanel{
    private JButton btnThem,btnXoa,btnSua,btnQuayLai,btnTim;
    private Font NORMAL_FONT;
    private JTable tableNhanVien;
    private DefaultTableModel tableModel;
    private JLabel lblHeader,lblMaNV,lblHoTen,lblGioiTinh,lblDiaChi,lblSDT,lblEmail,lblQuanLyVien;
    private JTextField txtTim,txtMaNV,txtHoTen,txtDiaChi,txtSDT,txtEmail;
    private JCheckBox cbGioiTinh,cbQuanLyVien;
    private JComboBox<String> jcbGioiTinh;
    private JRadioButton radTimTheoMa;
    private JRadioButton radTimTheoTen;
    private ButtonGroup buttonGroup;
    public QuanLyNhanVienPanel()
    {   setPreferredSize(new Dimension(800, 600));
	setLayout(new BorderLayout());
        setLookAndFeel();
        addNorth();
        addCenter();
        addEast();
        focus();
    }
    private void addNorth()
    {
        Box boxNorth = Box.createVerticalBox();
        add(boxNorth,BorderLayout.NORTH);
        Box boxHeader = Box.createHorizontalBox();
        boxNorth.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        boxNorth.add(boxHeader);
        boxHeader.add(lblHeader = new JLabel("QUẢN LÝ NHÂN VIÊN"));
        lblHeader.setFont(new Font("Times nem roman",1,25));
        boxNorth.add(Box.createVerticalStrut(5));
        
        Box boxTim = Box.createHorizontalBox();
        boxTim.add(Box.createHorizontalGlue());
        boxTim.add(Box.createHorizontalStrut(20));
        boxTim.add(txtTim = new JTextField());
        boxTim.add(Box.createHorizontalStrut(5));
        boxTim.add(btnTim = new JButton("Tìm"));
        boxNorth.add(boxTim);
        boxNorth.add(Box.createVerticalStrut(10));
       
        JPanel pnlTimTuyChon = new JPanel();
        pnlTimTuyChon.setLayout(new FlowLayout(FlowLayout.CENTER));
        radTimTheoMa = new JRadioButton("Tìm theo mã");
	radTimTheoMa.setSelected(true);
	radTimTheoTen = new JRadioButton("Tìm theo tên");
	buttonGroup = new ButtonGroup();
	buttonGroup.add(radTimTheoMa);
	buttonGroup.add(radTimTheoTen);
        pnlTimTuyChon.add(radTimTheoMa);
	pnlTimTuyChon.add(Box.createHorizontalStrut(20));
        pnlTimTuyChon.add(radTimTheoTen);
        boxNorth.add(pnlTimTuyChon);
        boxNorth.add(Box.createVerticalStrut(30));
        
       Box boxNhap = Box.createVerticalBox();         
       Box box1 = Box.createHorizontalBox();
       box1.add(lblMaNV = new JLabel("Mã nhân viên:"));
       box1.add(Box.createHorizontalStrut(20));
       box1.add(txtMaNV = new JTextField(25));
       box1.add(Box.createHorizontalStrut(20));
       box1.add(lblHoTen = new JLabel("Họ tên NV:"));
       box1.add(Box.createHorizontalStrut(20));
       box1.add(txtHoTen = new JTextField(25));
          
       Box box2 = Box.createHorizontalBox();
       box2.add(lblDiaChi = new JLabel("Địa chỉ:"));
       box2.add(Box.createHorizontalStrut(20));
       box2.add(txtDiaChi = new JTextField());
       box2.add(Box.createHorizontalStrut(20));
       box2.add(lblGioiTinh = new JLabel("Giới tính"));
       String[] items = { "Nam", "Nữ" };
       jcbGioiTinh = new JComboBox<String>(items);
       box2.add(jcbGioiTinh);
       box2.add(Box.createHorizontalStrut(40));
     
       
       Box box3 = Box.createHorizontalBox();
       box3.add(lblSDT = new JLabel("SĐT:"));
       box3.add(Box.createHorizontalStrut(20));
       box3.add(txtSDT = new JTextField(25));
       box3.add(Box.createHorizontalStrut(20));
       box3.add(lblEmail = new JLabel("Email:"));
       box3.add(Box.createHorizontalStrut(20));
       box3.add(txtEmail = new JTextField(25));
    //   box3.add(Box.createHorizontalStrut(20));
     
   /*    Box box4 = Box.createHorizontalBox();
       box4.add(lblQuanLyVien = new JLabel("Quản lý viên:"));
       box4.add(Box.createHorizontalStrut(20));
       box4.add(cbQuanLyVien = new JCheckBox(""));
     */
       JPanel pnQuanLyVien = new JPanel();
       pnQuanLyVien.setLayout(new FlowLayout(FlowLayout.LEFT));
       pnQuanLyVien.add(lblQuanLyVien = new JLabel("Quản lý viên:"));
       pnQuanLyVien.add(Box.createHorizontalStrut(8));
       pnQuanLyVien.add(cbQuanLyVien = new JCheckBox(""));
               
               
       boxNhap.add(box1);
       boxNhap.add(Box.createVerticalStrut(10));
       boxNhap.add(box2);
       boxNhap.add(Box.createVerticalStrut(10));
       boxNhap.add(box3);
       boxNhap.add(Box.createVerticalStrut(10));
       boxNhap.add(pnQuanLyVien);
       boxNorth.add(boxNhap);
    
       
       lblDiaChi.setPreferredSize(lblMaNV.getPreferredSize());
       lblEmail.setPreferredSize(lblMaNV.getPreferredSize());
       lblGioiTinh.setPreferredSize(lblMaNV.getPreferredSize());
       lblHoTen.setPreferredSize(lblMaNV.getPreferredSize());
       lblSDT.setPreferredSize(lblMaNV.getPreferredSize());
       lblQuanLyVien.setPreferredSize(lblMaNV.getPreferredSize());
    
    }
    private void addCenter()
    {
        String[] headers = {"Mã NV","Họ tên NV","Địa chỉ","Giới tính","SDT","Email","Quản lý viên"}; 
        tableModel = new DefaultTableModel(headers,0);
        tableNhanVien = new JTable(tableModel);
        JScrollPane sp = new JScrollPane(tableNhanVien);
        sp.setPreferredSize(new Dimension(sp.getPreferredSize().width,300));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(sp,BorderLayout.CENTER);
        
    }
    
    private void addEast()
    {
        Box boxEast = Box.createVerticalBox();
        this.add(boxEast,BorderLayout.EAST);
        boxEast.add(Box.createVerticalStrut(100));
        btnThem = addButtonTo(boxEast,"Thêm nhân viên");
        boxEast.add(Box.createVerticalStrut(10));
        btnSua = addButtonTo(boxEast, "Sửa nhân viên");
        boxEast.add(Box.createVerticalStrut(10));
        btnXoa = addButtonTo(boxEast, "Xoá nhân viên");
        boxEast.add(Box.createVerticalStrut(10));
        btnQuayLai = addButtonTo(boxEast,"Quay lại");
    }
    private JButton addButtonTo(Box box, String name)
    {
        JButton btn = new JButton(name);
        btn.setFont(NORMAL_FONT);
        btn.setPreferredSize(new Dimension(130,25));
        Box boxButton = Box.createHorizontalBox();
        boxButton.add(Box.createHorizontalGlue());
        boxButton.add(btn);
        boxButton.add(Box.createHorizontalGlue());	
	box.add(Box.createVerticalStrut(5));
	box.add(boxButton);
        box.add(Box.createVerticalStrut(5));	
        return btn;
    }
    private void setLookAndFeel() {
		for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
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
		txtTim.requestFocus();
	}
	
}
