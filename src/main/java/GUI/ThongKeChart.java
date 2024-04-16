/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package GUI;

import BUS.ThongKeBUS;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author M S I
 */

public class ThongKeChart extends javax.swing.JDialog {

    /**
     * Creates new form DepartmentGUI
     */
   
    public String tkdate;
    ThongKeBUS tkBUS = new ThongKeBUS();
    
    public ThongKeChart(java.awt.Frame parent, boolean modal) {
        
        super(parent, modal);
        
        initComponents();
        
        
    }
    
    
  
   
//BIỂU ĐỒ SỐ LƯỢNG THÀNH VIÊN THEO TỪNG KHOA (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Khoa(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_count_Khoa();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng sinh viên", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng sinh viên theo khoa".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng sinh viên".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THÀNH VIÊN THEO TỪNG NGÀNH (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Nganh(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_count_Nganh();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng sinh viên", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng sinh viên theo ngành".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng sinh viên".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }
   
    //BIỂU ĐỒ SỐ LƯỢNG THÀNH VIÊN THEO TỪNG KHOA (CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_count_Khoa_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng sinh viên", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng sinh viên theo khoa vào khu học tập trong "+date+"".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng sinh viên".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THÀNH VIÊN THEO TỪNG NGÀNH (CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_count_Nganh_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng sinh viên", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng sinh viên theo ngành vào khu học tập trong "+date+"".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng sinh viên".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

   //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC MƯỢN THEO TỪNG KHOA (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongThietBiDaMuon(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongThietBiDuocMuon();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được mượn theo khoa".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC MƯỢN THEO TỪNG NGÀNH (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongThietBiDaMuon(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongThietBiDuocMuon();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được mượn theo ngành".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC MƯỢN THEO TỪNG KHOA (CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongThietBiDaMuon_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongThietBiDuocMuon_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được mượn theo khoa trong "+date+"".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC MƯỢN THEO TỪNG NGÀNH (CHỌN THỜI GIAN)   
    public void Chart_Count_Nganh_SoLuongThietBiDaMuon_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongThietBiDuocMuon_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được mượn theo ngành trong "+date+"".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }


    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC TRẢ THEO TỪNG KHOA (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongThietBiDaTra(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongThietBiDaTra();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được trả theo khoa".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC TRẢ THEO TỪNG NGÀNH (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongThietBiDaTra(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongThietBiDaTra();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được trả theo ngành".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC TRẢ THEO TỪNG KHOA (CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongThietBiDaTra_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongThietBiDaTra_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được trả theo khoa trong "+date+"".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG THIẾT BỊ ĐÃ ĐƯỢC TRẢ THEO TỪNG NGÀNH (CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongThietBiDaTra_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongThietBiDaTra_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng thiết bị", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng thiết bị đã được trả theo ngành trong "+date+"".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng thiết bị".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐÃ XỬ LÝ THEO TỪNG KHOA (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongViPhamDaXuLy(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongViPhamDaXuLy();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm đã được xử lý theo khoa".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐÃ XỬ LÝ THEO TỪNG NGÀNH (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongViPhamDaXuLy(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongViPhamDaXuLy();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm đã được xử lý theo ngành".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));

        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();
    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐÃ XỬ LÝ THEO TỪNG KHOA (CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongViPhamDaXuLy_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongViPhamDaXuLy_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm đã được xử lý theo khoa trong "+date+"".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);

        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐÃ XỬ LÝ THEO TỪNG NGÀNH (CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongViPhamDaXuLy_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongViPhamDaXuLy_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm đã được xử lý theo ngành trong "+date+"".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐANG ĐƯỢC XỬ LÝ THEO TỪNG KHOA (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Khoa_SoLuongViPhamDangXuLy(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongViPhamDangXuLy();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm chưa được xử lý theo khoa".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐANG ĐƯỢC XỬ LÝ THEO TỪNG NGÀNH (KHÔNG CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongViPhamDangXuLy(JPanel jpnItem) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongViPhamDangXuLy();
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm chưa được xử lý theo ngành".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐANG ĐƯỢC XỬ LÝ THEO TỪNG KHOA (CHỌN THỜI GIAN)

    public void Chart_Count_Khoa_SoLuongViPhamDangXuLy_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getKhoa_and_cout_SoLuongViPhamDangXuLy_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm chưa được xử lý theo khoa trong "+date+"".toUpperCase(),
                "Khoa".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }

    //BIỂU ĐỒ SỐ LƯỢNG VI PHẠM ĐANG ĐƯỢC XỬ LÝ THEO TỪNG NGÀNH (CHỌN THỜI GIAN)
    public void Chart_Count_Nganh_SoLuongViPhamDangXuLy_ByDate(JPanel jpnItem, String date) {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        List<Object[]> list = new ArrayList<>();
        list = tkBUS.getNganh_and_cout_SoLuongViPhamDangXuLy_ByDate(date);
        for (int i = 0; i < list.size(); i++) {
            dataset.setValue((Long) list.get(i)[1], "Số lượng vi phạm", (String) list.get(i)[0]);
        }
        JFreeChart barChart = ChartFactory.createBarChart(
                "Biểu đồ số lượng vi phạm chưa được xử lý theo ngành trong "+date+"".toUpperCase(),
                "Ngành".toUpperCase(), "Số Lượng vi phạm".toUpperCase(),
                dataset, PlotOrientation.VERTICAL, false, true, false);
        
        CategoryPlot plot = barChart.getCategoryPlot();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new Dimension(jpnItem.getWidth(), jpnItem.getHeight()));
        
        jpnItem.removeAll();
        jpnItem.setLayout(new CardLayout());
        jpnItem.add(chartPanel);
        jpnItem.validate();
        jpnItem.repaint();

    }




    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        Chart_Panel = new javax.swing.JPanel();
        JcbList = new javax.swing.JComboBox<>();
        cbTime = new javax.swing.JCheckBox();
        jPanel1 = new javax.swing.JPanel();
        cbNganh = new javax.swing.JCheckBox();
        cbKhoa = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(311, 358));
        setResizable(false);

        Chart_Panel.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout Chart_PanelLayout = new javax.swing.GroupLayout(Chart_Panel);
        Chart_Panel.setLayout(Chart_PanelLayout);
        Chart_PanelLayout.setHorizontalGroup(
            Chart_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 750, Short.MAX_VALUE)
        );
        Chart_PanelLayout.setVerticalGroup(
            Chart_PanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        JcbList.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Chọn biểu đồ", "Số lượng thành viên", "Số thiết bị được mượn", "Số thiết bị đã trả", "Vi phạm đã xử lý", "Vi phạm chưa xử lý" }));
        JcbList.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                JcbListItemStateChanged(evt);
            }
        });
        JcbList.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                JcbListActionPerformed(evt);
            }
        });

        cbTime.setText("Theo thời gian");
        cbTime.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbTimeItemStateChanged(evt);
            }
        });
        cbTime.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cbTimeMouseClicked(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        buttonGroup1.add(cbNganh);
        cbNganh.setText("Theo ngành");
        cbNganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbNganhActionPerformed(evt);
            }
        });

        buttonGroup1.add(cbKhoa);
        cbKhoa.setSelected(true);
        cbKhoa.setText("Theo khoa");
        cbKhoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbKhoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cbNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbKhoa, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addComponent(cbKhoa)
                .addGap(18, 18, 18)
                .addComponent(cbNganh)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Chart_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JcbList, 0, 187, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cbTime, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(Chart_Panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(JcbList, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cbTime)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void JcbListActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_JcbListActionPerformed
        
    }//GEN-LAST:event_JcbListActionPerformed

    private void JcbListItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_JcbListItemStateChanged
        // TODO add your handling code here:
        if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên")){
            if(cbKhoa.isSelected() && !cbTime.isSelected()  ){
                Chart_Count_Khoa(Chart_Panel);
                
            }else if(cbNganh.isSelected() && !cbTime.isSelected()){
                Chart_Count_Nganh(Chart_Panel);
            }
            else if (cbKhoa.isSelected() && cbTime.isSelected() ){
                Chart_Count_Khoa_ByDate(Chart_Panel, tkdate);
            }else if(cbNganh.isSelected() && cbTime.isSelected()){
                Chart_Count_Nganh_ByDate(Chart_Panel, tkdate);
            }
        }
    
         if(JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn")){
            if(cbKhoa.isSelected() && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaMuon(Chart_Panel);
                
            }else if(cbNganh.isSelected() && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaMuon(Chart_Panel);
            }
            else if (cbKhoa.isSelected() && cbTime.isSelected() ){
                Chart_Count_Khoa_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            
            }else if(cbNganh.isSelected() && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            }
            
        }

        
           
        if(JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả")){
            if(cbKhoa.isSelected() && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaTra(Chart_Panel);
                
            }else if(cbNganh.isSelected() && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaTra(Chart_Panel);
            }
            else if (cbKhoa.isSelected() && cbTime.isSelected() ){
                Chart_Count_Khoa_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);

            }
            else if(cbNganh.isSelected() && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);}

         }
        if(JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý")){
            if(cbKhoa.isSelected() && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDaXuLy(Chart_Panel);
                
            }else if(cbNganh.isSelected() && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDaXuLy(Chart_Panel);
            }
            else if (cbKhoa.isSelected() && cbTime.isSelected() ){
                Chart_Count_Khoa_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
            else if(cbNganh.isSelected() && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
        }
        if(JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý")){
            if(cbKhoa.isSelected() && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDangXuLy(Chart_Panel);
                
            }else if(cbNganh.isSelected() && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDangXuLy(Chart_Panel);
            }
            else if (cbKhoa.isSelected() && cbTime.isSelected() ){
                Chart_Count_Khoa_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
            else if(cbNganh.isSelected() && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
            
        }

    }//GEN-LAST:event_JcbListItemStateChanged

    private void cbTimeItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbTimeItemStateChanged
        // TODO add your handling code here:
       
        if (cbTime.isSelected()){
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && cbKhoa.isSelected()){
                Chart_Count_Khoa_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && cbNganh.isSelected()){
                Chart_Count_Nganh_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && cbKhoa.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && cbNganh.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && cbKhoa.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && cbNganh.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && cbKhoa.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && cbNganh.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && cbKhoa.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && cbNganh.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
        }
        
       
       
    }//GEN-LAST:event_cbTimeItemStateChanged

    private void cbKhoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbKhoaActionPerformed
        // TODO add your handling code here:
        if (cbKhoa.isSelected()){
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && !cbTime.isSelected()){
                Chart_Count_Khoa(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && cbTime.isSelected()){
                Chart_Count_Khoa_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaMuon(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaTra(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDaXuLy(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && !cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDangXuLy(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && cbTime.isSelected()){
                Chart_Count_Khoa_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
            
        }
    }//GEN-LAST:event_cbKhoaActionPerformed

    private void cbNganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbNganhActionPerformed
        // TODO add your handling code here:
        if (cbNganh.isSelected()){
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && !cbTime.isSelected()){
                Chart_Count_Nganh(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số lượng thành viên") && cbTime.isSelected()){
                Chart_Count_Nganh_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaMuon(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị được mượn") && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaMuon_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaTra(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Số thiết bị đã trả") && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongThietBiDaTra_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDaXuLy(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm đã xử lý") && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDaXuLy_ByDate(Chart_Panel, tkdate);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && !cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDangXuLy(Chart_Panel);
            }
            if (JcbList.getSelectedItem().toString().equals("Vi phạm chưa xử lý") && cbTime.isSelected()){
                Chart_Count_Nganh_SoLuongViPhamDangXuLy_ByDate(Chart_Panel, tkdate);
            }
        }
    }//GEN-LAST:event_cbNganhActionPerformed

    private void cbTimeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbTimeMouseClicked
        
    }//GEN-LAST:event_cbTimeMouseClicked
   
    
    /**
     * @param args the command line arguments
     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(ThongKeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(ThongKeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(ThongKeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(ThongKeChart.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//        //</editor-fold>
//
//        /* Create and display the dialog */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                ThongKeChart dialog = new ThongKeChart(new javax.swing.JFrame(), true, 1);
//                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
//                    @Override
//                    public void windowClosing(java.awt.event.WindowEvent e) {
//                        System.exit(0);
//                    }
//                });
//                dialog.setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Chart_Panel;
    private javax.swing.JComboBox<String> JcbList;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JCheckBox cbKhoa;
    private javax.swing.JCheckBox cbNganh;
    private javax.swing.JCheckBox cbTime;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

    
}
