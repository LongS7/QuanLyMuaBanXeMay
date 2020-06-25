package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import dao.ThongKeDoanhThu;

import javax.swing.UIManager.LookAndFeelInfo;

public class ThongKeDoanhThuPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final Font HEADER_FONT = new Font("Times new roman", Font.BOLD, 20);
	private final Font NORMAL_FONT = new Font("Times new roman", Font.PLAIN, 14);
	private final Color HEADER_COLOR = new Color(0x1E1346);
	private JTextField txtYear;
	private JButton btnTK;
	private ChartPanel pnlChart;
	private JPanel pnlCenter;
	private JLabel lblTongDT;
	private double tong = 0;

	public ThongKeDoanhThuPanel() {
		setPreferredSize(new Dimension(500, 600));
		setLayout(new BorderLayout());
		
		addNorth();
		
		pnlCenter = new JPanel(new BorderLayout());
		add(pnlCenter, BorderLayout.CENTER);

		setLookAndFeel();
	}

	private void addNorth() {
		Box boxNorth = Box.createVerticalBox();

		this.add(boxNorth, BorderLayout.NORTH);

		Box boxHeader = Box.createHorizontalBox();

		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxHeader);
		boxNorth.add(Box.createVerticalStrut(5));

		JLabel lblHeader = new JLabel("THỐNG KÊ DOANH THU");
		lblHeader.setForeground(HEADER_COLOR);
		lblHeader.setFont(HEADER_FONT);

		boxHeader.add(Box.createHorizontalGlue());
		boxHeader.add(lblHeader);
		boxHeader.add(Box.createHorizontalGlue());
		
		Box boxYear = Box.createHorizontalBox();
		
		boxNorth.add(Box.createVerticalStrut(5));
		boxNorth.add(boxYear);
		boxNorth.add(Box.createVerticalStrut(5));
		
		JLabel lblYear = new JLabel("Chọn năm muốn thống kê");
		lblYear.setFont(NORMAL_FONT);
		txtYear = new JTextField();
		txtYear.setFont(NORMAL_FONT);
		btnTK = new JButton("Thống kê");
		btnTK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				thongKe();
			}
		});
		
		boxYear.add(Box.createHorizontalGlue());
		boxYear.add(Box.createHorizontalStrut(50));
		boxYear.add(lblYear);
		boxYear.add(Box.createHorizontalStrut(5));
		boxYear.add(txtYear);
		boxYear.add(Box.createHorizontalStrut(5));
		boxYear.add(btnTK);
		boxYear.add(Box.createHorizontalStrut(50));
		boxYear.add(Box.createHorizontalGlue());
		
		JLabel lblTong = new JLabel("Tổng doanh thu: ");
		lblTong.setFont(NORMAL_FONT);
		lblTongDT = new JLabel();
		lblTongDT.setFont(NORMAL_FONT);
		
		Box boxTong = Box.createHorizontalBox();
		boxTong.add(Box.createHorizontalStrut(10));
		boxTong.add(lblTong);
		boxTong.add(Box.createHorizontalStrut(10));
		boxTong.add(lblTongDT);
		boxTong.add(Box.createHorizontalGlue());
		
		boxNorth.add(Box.createVerticalStrut(10));
		boxNorth.add(boxTong);
		boxNorth.add(Box.createVerticalStrut(10));
	}
	
	private void thongKe() {
		String namString = txtYear.getText().trim();
		if(namString.isEmpty() || !namString.matches("[1-9]\\d{3}")) {
			JOptionPane.showMessageDialog(this, "Năm cần thống kê không được bỏ trống và phải lớn hơn 1000", "Lỗi", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		int nam = Integer.parseInt(namString);
		
		JFreeChart lineChart = ChartFactory.createLineChart("Doanh Thu Bán Hàng Năm " + nam, "Tháng", "Doanh thu (Triệu đồng)",
				createDataSet(nam), PlotOrientation.VERTICAL, true, true, false);
		
		pnlChart = new ChartPanel(lineChart);
		
		lblTongDT.setText(tong + " triệu đồng.");
		tong = 0;
		
		pnlCenter.removeAll();
		pnlCenter.add(pnlChart, BorderLayout.CENTER);
		
		revalidate();
		repaint();
	}

	private DefaultCategoryDataset createDataSet(int nam) {
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		ThongKeDoanhThu tkDoanhThu = new ThongKeDoanhThu();

		for (int i = 1; i <= 12; i++) {
			try {
				double t = tkDoanhThu.thongKeTheoThang(i, nam);
				tong += t;
				dataset.addValue(t, "Doanh thu", i + "");
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(this, "Lỗi trong quá trình xử lý dữ liệu!", "Lỗi",
						JOptionPane.ERROR_MESSAGE);
				return null;
			}
		}

		return dataset;
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

}
