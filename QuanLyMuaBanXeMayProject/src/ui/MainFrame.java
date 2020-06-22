package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class MainFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Box boxMenu;
	private final Font H1_FONT = new Font("Times new roman", Font.BOLD, 22);
	private final Font NORMAL_FONT = new Font("Time new roman", Font.PLAIN, 12);
	private final Color H1_COLOR = new Color(0xF3F3F3);
	private final Color ACTIVE_COLOR = new Color(0xd6d9df);
	private final Color NORTH_BACKGROUND = new Color(0x393742);
	private final Color WEST_BACKGROUND = new Color(0x2C2A33);
	private final Color CENTER_BACKGROUND = new Color(0xd6d9df);
	private Component selectedMenuItem = null;

	private JPanel pnlCenter;
	private JPanel pnlCenterOfCenter;
	private JButton btnMenu;
	private JScrollPane scrollMenu;
	private QuanLyHoaDonPanel pnlQLHD;
	private QuanLyXeMayPanel pnlQLXM;
	private JPanel menuTrangChu;
	private JPanel menuHoSo;
	private JPanel menuQLKH;
	private JPanel menuQLNV;
	private JPanel menuQLHD;
	private JPanel menuQLXM;
	private TrangChuPanel pnlTrangChu;
	private boolean isManager;
	private JPopupMenu popMenu;
	private JMenuItem miCut;
	private JMenuItem miCopy;
	private JMenuItem miPaste;
	private String tempData = "";
	private QuanLyKhachHangPanel pnlQLKH;

	/**
	 * Hàm khởi tạo
	 * 
	 * @param isManager
	 */
	public MainFrame(boolean isManager) {
		this.isManager = isManager;
		setSize(900, 700);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setTitle("Ứng Dụng Quản Lý Mua Bán Xe Máy");

		setLookAndFeel();

		pnlCenter = new JPanel(new BorderLayout());
		pnlCenter.setBorder(null);
		pnlCenter.setBackground(null);

		getContentPane().add(pnlCenter, BorderLayout.CENTER);

		addCenterOfCenter();
		addNorthOfCenter();
		addSouth();
		addWest();

		ImageIcon icon = new ImageIcon("Images/moto.png");
		setIconImage(icon.getImage());

		popMenu = new JPopupMenu();

		miCut = new JMenuItem("Cut", new ImageIcon("Images/cut.png"));
		miCopy = new JMenuItem("Copy", new ImageIcon("Images/copy.png"));
		miPaste = new JMenuItem("Paste", new ImageIcon("Images/paste.png")); miPaste.setEnabled(false);

		popMenu.add(miCut);
		popMenu.add(miCopy);
		popMenu.add(miPaste);

		addEvent();

		pnlTrangChu = new TrangChuPanel(isManager);
		pnlQLHD = new QuanLyHoaDonPanel();
		pnlQLXM = new QuanLyXeMayPanel();
		pnlQLKH = new QuanLyKhachHangPanel();
		pnlQLHD.setPopupMenu(popMenu);
		pnlQLXM.setPopupMenu(popMenu);

	}

	private void addEvent() {
		this.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				menuTrangChu.setBackground(ACTIVE_COLOR);
				changePanel(pnlTrangChu);
				selectedMenuItem = menuTrangChu;
			}
		});

		btnMenu.addActionListener(this);
		menuQLHD.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlQLHD);
				pnlQLHD.loadDataToTable();
			}
		});
		menuQLXM.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlQLXM);
				pnlQLXM.loadDataToTable();
			}
		});
		menuQLKH.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlQLKH);
				pnlQLKH.loadDataToTable();
			}
		});
		menuTrangChu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				changePanel(pnlTrangChu);
			}
		});

		miCut.addActionListener(this);
		miCopy.addActionListener(this);
		miPaste.addActionListener(this);
		popMenu.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				if(tempData.isEmpty())
					miPaste.setEnabled(false);
				Component c = popMenu.getInvoker();
				if (c == null)
					return;
				if(c instanceof JTextField) {
					JTextField txt = (JTextField)c;
					if(txt.getSelectedText() == null) {
						miCopy.setEnabled(false);
						miCut.setEnabled(false);
					}
					else {
						miCopy.setEnabled(true);
						miCut.setEnabled(true);
					}
				}
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
	}

	private void changePanel(JPanel panel) {
		getCenter().removeAll();
		getCenter().add(panel);
		if (panel.equals(pnlTrangChu))
			pnlTrangChu.focus();
		if (panel.equals(pnlQLHD))
			pnlQLHD.focus();
		getCenter().revalidate();
		getCenter().repaint();
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

	public JPanel getCenter() {
		return pnlCenterOfCenter;
	}

	private void addCenterOfCenter() {
		pnlCenterOfCenter = new JPanel(new BorderLayout());
		pnlCenterOfCenter.setBackground(CENTER_BACKGROUND);
		pnlCenterOfCenter.setBorder(null);

		JScrollPane scroll = new JScrollPane(pnlCenterOfCenter);
		scroll.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));

		pnlCenter.add(scroll, BorderLayout.CENTER);
	}

	private void addWest() {
		JPanel pnlWest = new JPanel(new BorderLayout());

		JPanel pnlMenu = new JPanel(new BorderLayout());
		pnlMenu.setBackground(null);

		scrollMenu = new JScrollPane(pnlWest);
		scrollMenu.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black));
		scrollMenu.setVisible(false);

		getContentPane().add(scrollMenu, BorderLayout.WEST);

		pnlWest.setBackground(WEST_BACKGROUND);

		boxMenu = Box.createVerticalBox();

		pnlMenu.add(boxMenu, BorderLayout.NORTH);

		pnlWest.add(pnlMenu, BorderLayout.NORTH);

		JLabel lblIcon = new JLabel(new ImageIcon("Images/icon.png"));

		Box boxIcon = Box.createHorizontalBox();
		boxIcon.add(Box.createHorizontalGlue());
		boxIcon.add(lblIcon);
		boxIcon.add(Box.createHorizontalGlue());

		boxMenu.add(boxIcon);
		menuTrangChu = addMenuItem("Trang chủ", "Images/home_white.png");
		menuHoSo = addMenuItem("Hồ sơ của tôi", "Images/profile_white.png");
		menuQLKH = addMenuItem("Quản lý khách hàng", "Images/customer_white.png");
		if (isManager)
			menuQLNV = addMenuItem("Quản lý nhân viên", "Images/employee_white.png");
		menuQLHD = addMenuItem("Quản lý hóa đơn", "Images/order_white.png");
		menuQLXM = addMenuItem("Quản lý xe máy", "Images/product_white.png");
	}

	private void addSouth() {
	}

	private void addNorthOfCenter() {
		JPanel pnlNorthOfCenter = new JPanel();
		pnlNorthOfCenter.setLayout(new BoxLayout(pnlNorthOfCenter, BoxLayout.Y_AXIS));
		pnlNorthOfCenter.setBackground(NORTH_BACKGROUND);

		pnlCenter.add(pnlNorthOfCenter, BorderLayout.NORTH);

		btnMenu = new JButton(new ImageIcon("Images/menu.png"));
		btnMenu.setBackground(CENTER_BACKGROUND);

		Box boxMenuButton = Box.createHorizontalBox();
		boxMenuButton.add(Box.createHorizontalStrut(5));
		boxMenuButton.add(btnMenu);

		JLabel lbHeader = new JLabel("CỬA HÀNG MUA BÁN XE MÁY HONDO");
		lbHeader.setFont(H1_FONT);
		lbHeader.setForeground(H1_COLOR);

		Box boxNorth = Box.createHorizontalBox();

		pnlNorthOfCenter.add(Box.createVerticalStrut(5));
		pnlNorthOfCenter.add(boxNorth);
		pnlNorthOfCenter.add(Box.createVerticalStrut(5));

		boxNorth.add(boxMenuButton);
		boxNorth.add(Box.createHorizontalGlue());
		boxNorth.add(lbHeader);
		boxNorth.add(Box.createHorizontalGlue());

	}

	public JPanel addMenuItem(String name, String iconPath) {
		JPanel pnl = new JPanel();
		pnl.setLayout(new BoxLayout(pnl, BoxLayout.Y_AXIS));
		pnl.setBackground(null);
		pnl.setPreferredSize(new Dimension(150, 60));

		JLabel lbIcon = new JLabel(new ImageIcon(iconPath));

		JLabel lbName = new JLabel(name);
		lbName.setForeground(Color.WHITE);
		lbName.setFont(NORMAL_FONT);

		Box boxItemIcon = Box.createHorizontalBox();
		boxItemIcon.add(Box.createHorizontalGlue());
		boxItemIcon.add(lbIcon);
		boxItemIcon.add(Box.createHorizontalGlue());

		Box boxItem = Box.createHorizontalBox();
		boxItem.add(Box.createHorizontalStrut(10));
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(lbName);
		boxItem.add(Box.createHorizontalGlue());
		boxItem.add(Box.createHorizontalStrut(10));

		pnl.add(Box.createVerticalGlue());
		pnl.add(boxItemIcon);
		pnl.add(boxItem);
		pnl.add(Box.createVerticalGlue());

		boxMenu.add(pnl);
		boxMenu.add(Box.createVerticalStrut(5));

		pnl.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER)
					;
			}
		});
		pnl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				if (selectedMenuItem != null)
					if (pnl.equals(selectedMenuItem))
						return;
				pnl.setBackground(ACTIVE_COLOR);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				if (selectedMenuItem != null)
					if (pnl.equals(selectedMenuItem))
						return;
				pnl.setBackground(null);
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (selectedMenuItem != null)
					if (!pnl.equals(selectedMenuItem))
						selectedMenuItem.setBackground(null);
				selectedMenuItem = pnl;
				pnl.setBackground(ACTIVE_COLOR);
			}
		});

		return pnl;

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();

		if (o.equals(btnMenu)) {
			scrollMenu.setVisible(!scrollMenu.isVisible());
			pnlCenter.revalidate();
		}
		if (o.equals(miCut))
			cutData();
		if (o.equals(miCopy))
			copyData();
		if (o.equals(miPaste))
			pasteData();
	}

	private void pasteData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if(c instanceof JTextField) {
			JTextField txt = (JTextField)c;
			txt.replaceSelection(tempData);
		}
	}

	private void copyData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if(c instanceof JTextField) {
			JTextField txt = (JTextField)c;
			tempData = txt.getSelectedText();
			miPaste.setEnabled(true);
		}
	}

	private void cutData() {
		Component c = popMenu.getInvoker();
		if (c == null)
			return;
		if(c instanceof JTextField) {
			JTextField txt = (JTextField)c;
			tempData = txt.getSelectedText();
			txt.replaceSelection("");
			miPaste.setEnabled(true);
		}
	}

}