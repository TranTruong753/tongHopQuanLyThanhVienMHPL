
package GUI;

import BUS.ThongKeBUS;
import com.formdev.flatlaf.intellijthemes.FlatDarkPurpleIJTheme;
import java.awt.Color;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author ACER
 */
public class ThongKeGUI extends javax.swing.JFrame {
    private final ThongKeBUS thongKeBUS;
    private DefaultTableModel tableModel;
    private List<Object[]> data= new ArrayList<>();
    private List<String> header=new ArrayList<>();
    private List<String> dataCombobox=new ArrayList<>();
    private int value=0;
    private String formattedDate="";
    private Timestamp timestamp;
    private TitledBorder titledBorder, titleBorderNganh,borderLabelSL, borderLabelClosest;
    private Calendar currentDate;
    private String[] jpanelCombobox;
    private DefaultComboBoxModel<String> model;
    
           

    
    
    public ThongKeGUI() {
        try {
            UIManager.setLookAndFeel(new FlatDarkPurpleIJTheme());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }
        thongKeBUS = new ThongKeBUS();
        initComponents();
        tableModel = (DefaultTableModel) tblDanhSach.getModel();
        currentDate = Calendar.getInstance();
        dateChoose.setDate(currentDate.getTime());
        titledBorder = (TitledBorder) All_JP.getBorder();
        titleBorderNganh=(TitledBorder) Nganh_Jp.getBorder();
        borderLabelSL=(TitledBorder) JPanel_SL.getBorder();
        borderLabelClosest=(TitledBorder) JPanel_SLThietBi.getBorder();
        Nganh_Jp.setVisible(false);
        lbTextSub1.setVisible(false);
        lbTextSub2.setVisible(false);
        lbTextSub3.setVisible(false);
        JPanelTongTien.setVisible(false);
        lbTextTongTien.setVisible(false);
        
        loadDataCboxKhoa();
        loadDataTable(getDate());
        loadLabelSLThanhVien(getDate());
        loadLabelThietBiDuocMuon(getDate());
        loadLabelViPhamDaXuLy (getDate());
//        loadDataTienBoiThuongTable();
        
    }
    
    
    //giá trị của radiobtn
    private int valueRadioBtn(){
        if (radBtnDate.isSelected()){
            return 1;
        }
        else if(radBtnMonth.isSelected()){
            return 2;
        }
        else if(radBtnYear.isSelected()){
            return 3;
        }
        return 0;
    }
    //giá trị của jcalendar
    private String getDate(){
        timestamp= new Timestamp(dateChoose.getDate().getTime());
        switch (valueRadioBtn()) {
            case 1:
                formattedDate = timestamp.toString().substring(0, 10);
                break;
            case 2:
                formattedDate = timestamp.toString().substring(0, 7);
                break;
            case 3:
                formattedDate = timestamp.toString().substring(0, 4);
                break;
            default:
                formattedDate="";
                break;
        }
        return formattedDate;
    }
    private void changeVisibleTextSub(Boolean value){
        lbTextSub1.setVisible(value);
        lbTextSub2.setVisible(value);
        lbTextSub3.setVisible(value);
    }
    //mặc định
    //load số lượng thành viên cho label
    private void loadLabelSLThanhVien(String date) {
        Long tvCount = thongKeBUS.countTvByDate(date);
        lbTextSLThanhVien.setText("0 thành viên");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thành viên"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thiết bị được mượn cho label
    private void loadLabelThietBiDuocMuon(String date) {
        Long tvCount = thongKeBUS.countThietBiDuocMuonByDate(date);
        lbTextSLThietBiDaMuon.setText("0 thiết bị");
        if (tvCount != null) {
            lbTextSLThietBiDaMuon.setText(tvCount.toString()+" thiết bị"); // Set the label text with the count
        } else {
            lbTextSLThietBiDaMuon.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng vi phạm đã xử lý cho label
    private void loadLabelViPhamDaXuLy (String date) {
        Long tvCount = thongKeBUS.countViPhamDaXuLYByDate(date);
        lbTextSLViPhamDaXuLy.setText("0 vi phạm");
        if (tvCount != null) {
            lbTextSLViPhamDaXuLy.setText(tvCount.toString()+" vi phạm"); // Set the label text with the count
        } else {
            lbTextSLViPhamDaXuLy.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    
    
    //Khi chọn combobox
    //load số lượng thành viên khoa cho label 
    private void loadLabelSLThanhVienKhoa(String nameKhoa) {
        borderLabelSL.setTitle("Số lượng thành viên khoa đã vào");
        Long tvCount = thongKeBUS.countTvKhoa(nameKhoa);
        lbTextSLThanhVien.setText("0 thành viên");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thành viên"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thành viên khoa theo ngày cho label 
    private void loadLabelSLThanhVienKhoaByDate(String nameKhoa, String date) {
        borderLabelSL.setTitle("Số lượng thành viên khoa đã vào");
        Long tvCount = thongKeBUS.countTvKhoaByDate(nameKhoa,date);
        lbTextSLThanhVien.setText("0 thành viên");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thành viên"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    
    //load số lượng thành viên ngành cho label 
    private void loadLabelSLThanhVienNganh(String nameNganh) {
        borderLabelSL.setTitle("Số lượng thành viên ngành đã vào");
        Long tvCount = thongKeBUS.countTvNganh(nameNganh);
        lbTextSLThanhVien.setText("0 thành viên");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thành viên"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thành viên ngành theo ngày cho label 
    private void loadLabelSLThanhVienNganhByDate(String nameNganh, String date) {
        borderLabelSL.setTitle("Số lượng thành viên ngành đã vào");
        Long tvCount = thongKeBUS.countTvNganhByDate(nameNganh,date);
        lbTextSLThanhVien.setText("0 thành viên");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thành viên"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    
    //load số lượng thiết bị được mượn cho label
    private void loadLabelSLThietBiDuocMuon() {
        borderLabelSL.setTitle("Số lượng thiết bị đã mượn");
        Long tvCount = thongKeBUS.countThietBiDuocMuon();
        lbTextSLThanhVien.setText("0 thiết bị");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thiết bị"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thiết bị được mượn theo ngày cho label
    private void loadLabelSLThietBiDuocMuonByDate(String date) {
        borderLabelSL.setTitle("Số lượng thiết bị đã mượn");
        Long tvCount = thongKeBUS.countThietBiDuocMuonByDate(date);
        lbTextSLThanhVien.setText("0 thiết bị");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thiết bị"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thiết bị đang được mượn cho label
    private void loadLabelSLThietBiDangDuocMuon() {
        borderLabelSL.setTitle("Số lượng thiết bị đang mượn");
        Long tvCount = thongKeBUS.countThietBiDangDuocMuon();
        lbTextSLThanhVien.setText("0 thiết bị");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thiết bị"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng thiết bị được mượn theo ngày cho label
    private void loadLabelSLThietBiDangDuocMuonByDate(String date) {
        borderLabelSL.setTitle("Số lượng thiết bị đang mượn");
        Long tvCount = thongKeBUS.countThietBiDangDuocMuonByDate(date);
        lbTextSLThanhVien.setText("0 thiết bị");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" thiết bị"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    
    //load số lượng vi phạm đã xử lý cho label
    private void loadLabelSLViPhamDaXuLy() {
        borderLabelSL.setTitle("Số lượng vi phạm đã xử lý");
        Long tvCount = thongKeBUS.countViPhamDaXuLY();
        lbTextSLThanhVien.setText("0 vi phạm");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" vi phạm"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng vi phạm đã xử lý theo ngày cho label
    private void loadLabelSLViPhamDaXuLyByDate(String date) {
        borderLabelSL.setTitle("Số lượng vi phạm đã xử lý");
        Long tvCount = thongKeBUS.countViPhamDaXuLYByDate(date);
        lbTextSLThanhVien.setText("0 vi phạm");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" vi phạm"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng vi phạm đang xử lý cho label
    private void loadLabelSLViPhamDangXuLy() {
        borderLabelSL.setTitle("Số lượng vi phạm đang xử lý");
        Long tvCount = thongKeBUS.countViPhamDangXuLY();
        lbTextSLThanhVien.setText("0 vi phạm");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" vi phạm"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    //load số lượng vi phạm đang xử lý theo ngày cho label
    private void loadLabelSLViPhamDangXuLyByDate(String date) {
        borderLabelSL.setTitle("Số lượng vi phạm đang xử lý");
        Long tvCount = thongKeBUS.countViPhamDangXuLYByDate(date);
        lbTextSLThanhVien.setText("0 vi phạm");
        if (tvCount != null) {
            lbTextSLThanhVien.setText(tvCount.toString()+" vi phạm"); // Set the label text with the count
        } else {
            lbTextSLThanhVien.setText("Error: Unable to retrieve count"); // In case of an error
        }
    }
    
    private void loadLabelClosestTVKhoa(String nameKhoa){
        borderLabelClosest.setTitle("Thành viên của khoa mới vào");
        Object[] closestThanhVien = thongKeBUS.getClosestThanhVienKhoa(nameKhoa);
        if (closestThanhVien != null && closestThanhVien.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTV= (String) closestThanhVien[0].toString();
            String hoTen = (String) closestThanhVien[1];
            String sdt = (String) closestThanhVien[2].toString();
            String tgVao = (String) closestThanhVien[3].toString();
            lbTextSLThietBiDaMuon.setText("Mã Thành viên: "+maTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Họ tên: " + hoTen);
            lbTextSub2.setText("Số điện thoại: " + sdt);
            lbTextSub3.setText("Thời gian vào: " + tgVao );
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thành viên nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestTVKhoaByDate(String nameKhoa, String date){
        borderLabelClosest.setTitle("Thành viên của khoa mới vào");
        Object[] closestThanhVien = thongKeBUS.getClosestThanhVienKhoaByCurrentDate(nameKhoa,date);
        if (closestThanhVien != null && closestThanhVien.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTV= (String) closestThanhVien[0].toString();
            String hoTen = (String) closestThanhVien[1];
            String sdt = (String) closestThanhVien[2].toString();
            String tgVao = (String) closestThanhVien[3].toString();
            lbTextSLThietBiDaMuon.setText("Mã Thành viên: "+maTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Họ tên: " + hoTen);
            lbTextSub2.setText("Số điện thoại: " + sdt);
            lbTextSub3.setText("Thời gian vào: " + tgVao );
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thành viên nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
   
    
    private void loadLabelClosestTVNganh(String nameNganh){
        borderLabelClosest.setTitle("Thành viên của ngành mới vào");
        Object[] closestThanhVien = thongKeBUS.getClosestThanhVienNganh(nameNganh);
        if (closestThanhVien != null && closestThanhVien.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTV= (String) closestThanhVien[0].toString();
            String hoTen = (String) closestThanhVien[1];
            String sdt = (String) closestThanhVien[2].toString();
            String tgVao = (String) closestThanhVien[3].toString();
            lbTextSLThietBiDaMuon.setText("Mã Thành viên: "+maTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Họ tên: " + hoTen);
            lbTextSub2.setText("Số điện thoại: " + sdt);
            lbTextSub3.setText("Thời gian vào: " + tgVao );
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thành viên nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestTVNganhByDate(String nameKhoa, String date){
        borderLabelClosest.setTitle("Thành viên của ngành mới vào");
        Object[] closestThanhVien = thongKeBUS.getClosestThanhVienNganhByCurrentDate(nameKhoa,date);
        if (closestThanhVien != null && closestThanhVien.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTV= (String) closestThanhVien[0].toString();
            String hoTen = (String) closestThanhVien[1];
            String sdt = (String) closestThanhVien[2].toString();
            String tgVao = (String) closestThanhVien[3].toString();
            lbTextSLThietBiDaMuon.setText("Mã Thành viên: "+maTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Họ tên: " + hoTen);
            lbTextSub2.setText("Số điện thoại: " + sdt);
            lbTextSub3.setText("Thời gian vào: " + tgVao );
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thành viên nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestThietBiDaMuon(){
        borderLabelClosest.setTitle("Thiết bị đã mượn gần nhất");
        Object[] closestThietBi = thongKeBUS.getClosestThietBiDuocMuon();
        if (closestThietBi != null && closestThietBi.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTB= (String) closestThietBi[0].toString();
            String TenTB = (String) closestThietBi[1];
            String TGMuon = (String) closestThietBi[2].toString();

            lbTextSLThietBiDaMuon.setText("Mã thiết bị: "+maTB);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thiết bị: " + TenTB);
            lbTextSub2.setText("Thời gian mượn: " + TGMuon);
            lbTextSub3.setVisible(false);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thiết bị nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestThietBiDaMuonByDate(String date){
        borderLabelClosest.setTitle("Thiết bị đã mượn gần nhất");
        Object[] closestThietBi = thongKeBUS.getClosestThietBiDuocMuonByCurrentDate(date);
        if (closestThietBi != null && closestThietBi.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTB= (String) closestThietBi[0].toString();
            String TenTB = (String) closestThietBi[1];
            String TGMuon = (String) closestThietBi[2].toString();

            lbTextSLThietBiDaMuon.setText("Mã thiết bị: "+maTB);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thiết bị: " + TenTB);
            lbTextSub2.setText("Thời gian mượn: " + TGMuon);
            lbTextSub3.setVisible(false);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thiết bị nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestThietBiDaTra(){
        borderLabelClosest.setTitle("Thiết bị đã trả gần nhất");
        Object[] closestThietBi = thongKeBUS.getClosestThietBiDaTra();
        if (closestThietBi != null && closestThietBi.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTB= (String) closestThietBi[0].toString();
            String TenTB = (String) closestThietBi[1];
            String TGTra = (String) closestThietBi[2].toString();

            lbTextSLThietBiDaMuon.setText("Mã thiết bị: "+maTB);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thiết bị: " + TenTB);
            lbTextSub2.setText("Thời gian trả: " + TGTra);
            lbTextSub3.setVisible(false);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thiết bị nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestThietBiDaTraByDate(String date){
        borderLabelClosest.setTitle("Thiết bị đã trả gần nhất");
        Object[] closestThietBi = thongKeBUS.getClosestThietBiDaTraByDate(date);
        if (closestThietBi != null && closestThietBi.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String maTB= (String) closestThietBi[0].toString();
            String TenTB = (String) closestThietBi[1];
 
            String TGTra = (String) closestThietBi[2].toString();

            lbTextSLThietBiDaMuon.setText("Mã thiết bị: "+maTB);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thiết bị: " + TenTB);
            lbTextSub2.setText("Thời gian trả: " + TGTra);
            lbTextSub3.setVisible(false);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thiết bị nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestViPhamDaXuLy(){
        borderLabelClosest.setTitle("Thành viên đã xử lý gần nhất");
        Object[] closestViPham = thongKeBUS.getClosestViPhamDaXuLY();
        if (closestViPham != null && closestViPham.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String MaTV= (String) closestViPham[0].toString();
            String HoTen = (String) closestViPham[1];
            String HinhThucXL = (String) closestViPham[2].toString();
            String SoTien="0";
            if (closestViPham[3]!=null){
                SoTien=(String) closestViPham[3].toString();
            }

            lbTextSLThietBiDaMuon.setText("Mã thành viên: "+MaTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thành viên: " + HoTen);
            lbTextSub2.setText("Hình thức xử lý: " + HinhThucXL);
            lbTextSub3.setText("Số tiền: "+SoTien);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có xử lý nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestViPhamDaXuLyByDate(String date){
        borderLabelClosest.setTitle("Thành viên đã xử lý gần nhất");
        Object[] closestViPham = thongKeBUS.getClosestViPhamDaXuLYByDate(date);
        if (closestViPham != null && closestViPham.length > 0) {
            String MaTV= (String) closestViPham[0].toString();
            String HoTen = (String) closestViPham[1];
            String HinhThucXL = (String) closestViPham[2].toString();
            String SoTien="0";
            if (closestViPham[3]!=null){
                SoTien=(String) closestViPham[3].toString();
            }

            lbTextSLThietBiDaMuon.setText("Mã thiết bị: "+MaTV);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Tên thiết bị: " + HoTen);
            lbTextSub2.setText("Thời gian mượn: " + HinhThucXL);
            lbTextSub3.setText("Số tiền: "+SoTien);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có thiết bị nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestViPhamDangXuLy(){
        borderLabelClosest.setTitle("Vi phạm đã xử lý gần nhất");
        Object[] closestViPham = thongKeBUS.getClosestViPhamDangXuLY();
        if (closestViPham != null && closestViPham.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String MaXL= (String) closestViPham[0].toString();
            String MaTV = (String) closestViPham[1].toString();
            String HinhThucXL = String.valueOf(closestViPham[2]);
            String SoTien="0";
            if (closestViPham[3]!=null){
                SoTien=(String) closestViPham[3].toString();
            }

            lbTextSLThietBiDaMuon.setText("Mã xử lý: "+MaXL);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Mã thành viên: " + MaTV);
            lbTextSub2.setText("Hình thức xử lý: " + HinhThucXL);
            lbTextSub3.setText("Số tiền: "+SoTien);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có xử lý nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    private void loadLabelClosestViPhamDangXuLyByDate(String date){
        borderLabelClosest.setTitle("Vi phạm đã xử lý gần nhất");
        Object[] closestViPham = thongKeBUS.getClosestViPhamDangXuLYByDate(date);
        if (closestViPham != null && closestViPham.length > 0) {
            // Sử dụng thông tin của thành viên gần nhất để cập nhật giao diện
            String MaXL= (String) closestViPham[0].toString();
            String MaTV = (String) closestViPham[1].toString();
            String HinhThucXL = String.valueOf(closestViPham[2]);
            String SoTien="0";
            if (closestViPham[3]!=null){
                SoTien=(String) closestViPham[3].toString();
            }

            lbTextSLThietBiDaMuon.setText("Mã xử lý: "+MaXL);
            changeVisibleTextSub(true);
            lbTextSub1.setText("Mã thành viên: " + MaTV);
            lbTextSub2.setText("Hình thức xử lý: " + HinhThucXL);
            lbTextSub3.setText("Số tiền: "+SoTien);
            
        } else {
            lbTextSLThietBiDaMuon.setText("Không có xử lý nào"); // Trường hợp không có kết quả trả về
            changeVisibleTextSub(false);
        }
    }
    
    //Thay đổi combobox thành của khoa
    private void loadDataKhoaJPanel(String value){
        loadDataCboxNganh(value);
        JPanel_SLViPham.setVisible(false);
        if (!"".equals(getDate())){
            loadDataTvKhoaByDateTable(value,getDate());
            loadLabelSLThanhVienKhoaByDate(value,getDate());
            loadLabelClosestTVKhoaByDate(value, getDate());

        }
        else{
            loadDataTvKhoaTable(value);
            loadLabelSLThanhVienKhoa(value);
            loadLabelClosestTVKhoa(value );
 
        }   
    }
    
    
    //Thay đổi combobox thành của thiết bị 
    private void loadDataThietBiJPanel(String value){
        JPanel_SLViPham.setVisible(false);
        loadDataCboxTenThietBi();
        if (value.equals("Được mượn")){
            if (!"".equals(getDate())){
                loadDataTbDuocMuonByDateTable(getDate());
                loadLabelSLThietBiDuocMuonByDate(getDate());
                loadLabelClosestThietBiDaMuonByDate(getDate());
            }
            else{
                loadLabelSLThietBiDuocMuon();
                JOptionPane.showMessageDialog(null, "Không thể thống kê", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                loadLabelClosestThietBiDaMuon();
            }
        }
        else if(value.equals("Đang được mượn")){
            if (!"".equals(getDate())){
                loadDataTbDangDuocMuonByDateTable(getDate());
                loadLabelSLThietBiDangDuocMuonByDate(getDate());
                loadLabelClosestThietBiDaTraByDate(getDate());
            }
            else{
                loadDataTbDangDuocMuonTable();
                loadLabelSLThietBiDangDuocMuon();
                loadLabelClosestThietBiDaTra();
                
            }
        }
    }
    
    
    //Thay đổi combobox thành của vi phạm 
    private void loadDataViPhamJPanel(String value){
        JPanel_SLViPham.setVisible(false);
        if (value.equals("Đã được xử lý")){
            JPanelTongTien.setVisible(true);
            lbTextTongTien.setVisible(true);
            loadDataTienBoiThuongTable();
            if (!"".equals(getDate())){
                loadDataViPhamDaXuLyByDateTable(getDate());
                loadLabelSLViPhamDaXuLyByDate(getDate());
                loadLabelClosestViPhamDaXuLyByDate(getDate());
                
            }
            else{
                loadDataViPhamDaXuLyTable();
                loadLabelSLViPhamDaXuLy();
                loadLabelClosestViPhamDaXuLy();
            }
        }
        else if(value.equals("Đang xử lý")){
            JPanelTongTien.setVisible(true);
            lbTextTongTien.setVisible(true);
            lbTextTongTien.setText("0 VNĐ");
            if (!"".equals(getDate())){
                loadDataViPhamDangXuLyByDateTable(getDate());
                loadLabelSLViPhamDangXuLyByDate(getDate());
                loadLabelClosestViPhamDangXuLyByDate(getDate());
            }
            else{
                loadDataViPhamDangXuLyTable();
                loadLabelSLViPhamDangXuLy();
                loadLabelClosestViPhamDangXuLy();
            }
        }
    }
    
    
    //load dữ liệu cho combobox của khoa
    private void loadDataCboxKhoa() {
         // Lấy danh sách khoa từ BUS
        dataCombobox = thongKeBUS.getAllKhoa();
        // Tạo một DefaultComboBoxModel từ danh sách khoa
        model = new DefaultComboBoxModel<>();

        // Thêm các khoa vào model
        if (dataCombobox != null) {
            model.addElement("--Chon Khoa--");
            for (String khoa : dataCombobox) {
                model.addElement(khoa);
            }
        }
        // Đặt model mới cho cboxKhoa
        cboxAll.setModel(model);
        
    }
    
    //load dữ liệu cho combobox của ngành
    private void loadDataCboxNganh(String khoaName){
        dataCombobox = thongKeBUS.getAllNganh(khoaName);
        // Tạo một DefaultComboBoxModel từ danh sách khoa
        model = new DefaultComboBoxModel<>();

        // Thêm các khoa vào model
        if (dataCombobox != null) {
            model.addElement("--Chon Nganh--");
            for (String nganh : dataCombobox) {
                model.addElement(nganh);
            }
        }
        // Đặt model mới cho cboxKhoa
        cboxNganh.setModel(model);
    }
    private void loadDataCboxTenThietBi(){
        dataCombobox = thongKeBUS.getAllTenThietBi();
        // Tạo một DefaultComboBoxModel từ danh sách khoa
        model = new DefaultComboBoxModel<>();

        // Thêm các khoa vào model
        if (dataCombobox != null) {
            model.addElement("--Chọn tên thiết bị--");
            for (String nganh : dataCombobox) {
                model.addElement(nganh);
            }
        }
        // Đặt model mới cho cboxKhoa
        cboxNganh.setModel(model);
    }
    //thêm các hàng dữ liệu vào bảng
    private void tableAddRow(List<Object[]> data){
        // Xóa dữ liệu cũ trong bảng
        tableModel.setRowCount(0);
        // Thêm dữ liệu mới vào bảng
        if (data != null ) {
            for (Object[] row : data) {
                tableModel.addRow(row);
            }
        }
    }
    
    //tiêu đề của bảng danh sách
    private void loadHeaderTable(String tableName) {
        header = thongKeBUS.getTableHeaders(tableName);

        if (header != null) {
            boolean foundMaTV = false; // Biến để kiểm tra xem đã tìm thấy MaTV chưa
            List<String> additionalColumns = new ArrayList<>();

            for (int i = 0; i < header.size(); i++) {
                if ("MaTV".equals(header.get(i))&&i!=0) {
                    foundMaTV = true; // Đánh dấu đã tìm thấy MaTV
                } else if (foundMaTV) {
                    // Nếu đã tìm thấy MaTV, thêm HoTen và Khoa vào danh sách cột bổ sung
                    additionalColumns.add("HoTen");
                    additionalColumns.add("Khoa");
                    break; // Kết thúc vòng lặp sau khi đã tìm thấy MaTV
                }
            }
            // Thêm các cột bổ sung vào sau cột MaTV
            if (!additionalColumns.isEmpty()) {
                int maTVIndex = header.indexOf("MaTV");
                if (maTVIndex != -1) {
                    header.addAll(maTVIndex + 1, additionalColumns);
                } else {
                    // Nếu không tìm thấy cột MaTV, thêm các cột bổ sung vào cuối danh sách
                    header.addAll(additionalColumns);
                }
            }
            // Đặt tiêu đề cột
            tableModel.setColumnIdentifiers(header.toArray());
        }
        tableModel.setColumnCount(header.size());
    }
    
    private void loadDataTable(String date){
        loadHeaderTable("thanhvien");
        tableModel.addColumn("Thời gian vào");
        data = thongKeBUS.getAllThanhVienByCurrentDate(date);
        tableAddRow(data);
    }
    
    //load dữ liệu thành viên khoa vào bảng
    private void loadDataTvKhoaTable(String nameKhoa){
        loadHeaderTable("thanhvien");
        data = thongKeBUS.getAllThanhVienKhoa(nameKhoa);
        tableAddRow(data);
    }
    
    //load dữ liệu thành viên của khoa theo ngày vào bảng
    private void loadDataTvKhoaByDateTable(String nameKhoa, String date){
        loadHeaderTable("thanhvien");
        tableModel.addColumn("Thời gian vào");
        data = thongKeBUS.getAllTvKhoaByDate(nameKhoa,date);
        tableAddRow(data);
    }
    
    //load dữ liệu thành viên theo ngành vào bảng
    private void loadDataTvNganhTable(String nameNganh){
        loadHeaderTable("thanhvien");
        data = thongKeBUS.getAllThanhVienNganh(nameNganh);
        tableAddRow(data);
    }

    //load dữ liệu thành viên của ngành theo ngày vào bảng
    private void loadDataTvNganhByDateTable(String nameKhoa, String date){
        loadHeaderTable("thanhvien");
        tableModel.addColumn("Thời gian vào");
        data = thongKeBUS.getAllTvNganhByDate(nameKhoa,date);
        tableAddRow(data);
    }
    private void loadDataTenThietBiByDateTable(String nameThietBi, String date){
        loadHeaderTable("thietbi");
        tableModel.addColumn("Thời gian mượn");
        data = thongKeBUS.getAllTenThietBiByDate(nameThietBi,date);
        tableAddRow(data);
    }
    //load dữ liệu thiết bị được mượn theo ngày vào bảng
    private void loadDataTbDuocMuonByDateTable(String date){
        loadHeaderTable("thietbi");
        tableModel.addColumn("Thời gian mượn");
        data = thongKeBUS.getAllThietBiDuocMuonByDate(date);
        tableAddRow(data);
    }
    
    //load dữ liệu thiết bị đang được mượn  vào bảng
    private void loadDataTbDangDuocMuonTable(){
        loadHeaderTable("thietbi");
        tableModel.addColumn("Thời gian mượn");
        data = thongKeBUS.getAllThietBiDangDuocMuon();
        tableAddRow(data);   
    }
    
    //load dữ liệu thiết bị đang được mượn theo ngày vào bảng
    private void loadDataTbDangDuocMuonByDateTable(String date){
        loadHeaderTable("thietbi");
        tableModel.addColumn("Thời gian mượn");
        data = thongKeBUS.getAllThietBiDangDuocMuonByDate(date);
        tableAddRow(data);
    }
    
    //load dữ liệu vi phạm đã xử lý vào bảng
    private void loadDataViPhamDaXuLyTable(){
        loadHeaderTable("xuly");
        data = thongKeBUS.getAllViPhamDaXuLY();
        tableAddRow(data); 
    }
    
    //load dữ liệu vi phạm đã xử lý theo ngày vào bảng
    private void loadDataViPhamDaXuLyByDateTable(String date){
        loadHeaderTable("xuly");
        data = thongKeBUS.getAllViPhamDaXuLYByDate(date);
        tableAddRow(data);
    }
    
    //load dữ liệu vi phạm đang xử lý vào bảng
    private void loadDataViPhamDangXuLyTable(){
        loadHeaderTable("xuly");
        data = thongKeBUS.getAllViPhamDangXuLY();
        tableAddRow(data);
    }
    
    //load dữ liệu vi phạm đang xử lý theo ngày vào bảng
    private void loadDataViPhamDangXuLyByDateTable(String date){
        loadHeaderTable("xuly");
        data = thongKeBUS.getAllViPhamDangXuLYByDate(date);
        tableAddRow(data);
    }
    
    //load tổng tiền bồi thường vào bảng
    private void loadDataTienBoiThuongTable(){
        value = thongKeBUS.sumTienBoiThuong();
        lbTextTongTien.setText(value + " VNĐ");
    }
    
    //Export data from table to excel
    private void exportDataToExcel(){
        String filePath = "/E:/";
        String fileName = "ThongKe_" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) + ".xlsx";
        String fullPath = filePath + fileName;

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("Data");
            // Add title row
            XSSFRow titleRow = sheet.createRow(0);
            XSSFCell titleCell = titleRow.createCell(0);
            titleCell.setCellValue("DANH SÁCH THỐNG KÊ");

            // Add export time row
            XSSFRow exportTimeRow = sheet.createRow(1);
            XSSFCell exportTimeCell = exportTimeRow.createCell(0);
            exportTimeCell.setCellValue("Thời gian export: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            // Get the table data
            DefaultTableModel model = (DefaultTableModel) tblDanhSach.getModel();
            int rowCount = model.getRowCount();
            int columnCount = model.getColumnCount();

            // Create header row
            XSSFRow headerRow = sheet.createRow(3);
            for (int i = 0; i < columnCount; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(model.getColumnName(i));
            }

            // Create data rows

            for (int i = 0; i < rowCount; i++) {
                XSSFRow dataRow = sheet.createRow(i + 4); 
                for (int j = 0; j < columnCount; j++) {
                    XSSFCell cell = dataRow.createCell(j);
                    Object value = model.getValueAt(i, j);
                    if (value != null) {
                        cell.setCellValue(value.toString());
                    } else {
                        cell.setCellValue("");
                    }
                }
            }

            // Write the workbook to a file
            FileOutputStream fileOut = new FileOutputStream(fullPath);
            workbook.write(fileOut);
            fileOut.close();

            JOptionPane.showMessageDialog(null, "Xuất file thống kê thành công. Đường dẫn: " + fullPath, "Export Success", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Xuất file thống kê thất bại", "Export Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        radBtnGroup = new javax.swing.ButtonGroup();
        jPanel10 = new javax.swing.JPanel();
        ThoiGianPanel = new javax.swing.JPanel();
        radBtnDate = new javax.swing.JRadioButton();
        radBtnMonth = new javax.swing.JRadioButton();
        radBtnYear = new javax.swing.JRadioButton();
        radBtnNone = new javax.swing.JRadioButton();
        dateChoose = new com.toedter.calendar.JDateChooser();
        All_JP = new javax.swing.JPanel();
        cboxAll = new javax.swing.JComboBox<>();
        Nganh_Jp = new javax.swing.JPanel();
        cboxNganh = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDanhSach = new javax.swing.JTable();
        btnThongKe = new javax.swing.JButton();
        PhuongAncbb = new javax.swing.JComboBox<>();
        JPanel_SL = new javax.swing.JPanel();
        lbTextSLThanhVien = new javax.swing.JLabel();
        JPanel_SLThietBi = new javax.swing.JPanel();
        lbTextSLThietBiDaMuon = new javax.swing.JLabel();
        lbTextSub1 = new javax.swing.JLabel();
        lbTextSub2 = new javax.swing.JLabel();
        lbTextSub3 = new javax.swing.JLabel();
        JPanel_SLViPham = new javax.swing.JPanel();
        lbTextSLViPhamDaXuLy = new javax.swing.JLabel();
        JPanelTongTien = new javax.swing.JPanel();
        lbTextTongTien = new javax.swing.JLabel();
        btnExport1 = new javax.swing.JButton();
        button1 = new java.awt.Button();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Thống Kê");
        setBackground(new java.awt.Color(255, 255, 255));
        setBounds(new java.awt.Rectangle(0, 0, 0, 0));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setLocation(new java.awt.Point(0, 0));
        setResizable(false);
        setSize(new java.awt.Dimension(0, 0));

        ThoiGianPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Thời gian"));

        radBtnGroup.add(radBtnDate);
        radBtnDate.setText("Ngày");
        radBtnDate.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        radBtnDate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBtnDateActionPerformed(evt);
            }
        });

        radBtnGroup.add(radBtnMonth);
        radBtnMonth.setText("Tháng");
        radBtnMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBtnMonthActionPerformed(evt);
            }
        });

        radBtnGroup.add(radBtnYear);
        radBtnYear.setText("Năm");
        radBtnYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBtnYearActionPerformed(evt);
            }
        });

        radBtnGroup.add(radBtnNone);
        radBtnNone.setSelected(true);
        radBtnNone.setText("Không");
        radBtnNone.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radBtnNoneActionPerformed(evt);
            }
        });

        dateChoose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dateChooseMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout ThoiGianPanelLayout = new javax.swing.GroupLayout(ThoiGianPanel);
        ThoiGianPanel.setLayout(ThoiGianPanelLayout);
        ThoiGianPanelLayout.setHorizontalGroup(
            ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ThoiGianPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ThoiGianPanelLayout.createSequentialGroup()
                        .addGroup(ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radBtnDate)
                            .addComponent(radBtnNone, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addGroup(ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(radBtnYear)
                            .addComponent(radBtnMonth)))
                    .addComponent(dateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        ThoiGianPanelLayout.setVerticalGroup(
            ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ThoiGianPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(dateChoose, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBtnNone)
                    .addComponent(radBtnYear))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ThoiGianPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radBtnDate)
                    .addComponent(radBtnMonth))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        All_JP.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Khoa"));
        All_JP.setOpaque(false);

        cboxAll.setBorder(null);
        cboxAll.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cboxAll.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxAllActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout All_JPLayout = new javax.swing.GroupLayout(All_JP);
        All_JP.setLayout(All_JPLayout);
        All_JPLayout.setHorizontalGroup(
            All_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(All_JPLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboxAll, 0, 153, Short.MAX_VALUE)
                .addContainerGap())
        );
        All_JPLayout.setVerticalGroup(
            All_JPLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboxAll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        Nganh_Jp.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Ngành"));

        cboxNganh.setBorder(null);
        cboxNganh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboxNganhActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Nganh_JpLayout = new javax.swing.GroupLayout(Nganh_Jp);
        Nganh_Jp.setLayout(Nganh_JpLayout);
        Nganh_JpLayout.setHorizontalGroup(
            Nganh_JpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Nganh_JpLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        Nganh_JpLayout.setVerticalGroup(
            Nganh_JpLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cboxNganh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 0, 0), 1, true), "Danh sách"));

        tblDanhSach.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblDanhSach.setEnabled(false);
        tblDanhSach.setGridColor(new java.awt.Color(255, 255, 255));
        tblDanhSach.setOpaque(false);
        tblDanhSach.setSelectionBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblDanhSach);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 716, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
        );

        btnThongKe.setText("Xem biểu đồ");
        btnThongKe.setBorder(null);
        btnThongKe.setName(""); // NOI18N
        btnThongKe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThongKeActionPerformed(evt);
            }
        });

        PhuongAncbb.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SỐ LƯỢNG THÀNH VIÊN VÀO KHU HỌC TẬP", "THIẾT BỊ", "XỬ LÝ VI PHẠM" }));
        PhuongAncbb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                PhuongAncbbItemStateChanged(evt);
            }
        });
        PhuongAncbb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhuongAncbbActionPerformed(evt);
            }
        });
        PhuongAncbb.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                PhuongAncbbPropertyChange(evt);
            }
        });

        JPanel_SL.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Số lượng thành viên đã vào "));

        lbTextSLThanhVien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSLThanhVien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTextSLThanhVien.setText("none");

        javax.swing.GroupLayout JPanel_SLLayout = new javax.swing.GroupLayout(JPanel_SL);
        JPanel_SL.setLayout(JPanel_SLLayout);
        JPanel_SLLayout.setHorizontalGroup(
            JPanel_SLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextSLThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanel_SLLayout.setVerticalGroup(
            JPanel_SLLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextSLThanhVien, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanel_SLThietBi.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Số lượng thiết bị đã mượn"));

        lbTextSLThietBiDaMuon.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSLThietBiDaMuon.setText("none");

        lbTextSub1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSub1.setText("jLabel1");

        lbTextSub2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSub2.setText("jLabel2");

        lbTextSub3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSub3.setText("jLabel3");

        javax.swing.GroupLayout JPanel_SLThietBiLayout = new javax.swing.GroupLayout(JPanel_SLThietBi);
        JPanel_SLThietBi.setLayout(JPanel_SLThietBiLayout);
        JPanel_SLThietBiLayout.setHorizontalGroup(
            JPanel_SLThietBiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLThietBiLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(JPanel_SLThietBiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbTextSLThietBiDaMuon, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTextSub1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTextSub2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbTextSub3, javax.swing.GroupLayout.DEFAULT_SIZE, 225, Short.MAX_VALUE))
                .addContainerGap(62, Short.MAX_VALUE))
        );
        JPanel_SLThietBiLayout.setVerticalGroup(
            JPanel_SLThietBiLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLThietBiLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextSLThietBiDaMuon, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTextSub1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTextSub2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbTextSub3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        JPanel_SLViPham.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Số lượng vi phạm đã xử lý"));

        lbTextSLViPhamDaXuLy.setBackground(new java.awt.Color(255, 255, 255));
        lbTextSLViPhamDaXuLy.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextSLViPhamDaXuLy.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTextSLViPhamDaXuLy.setText("none");

        javax.swing.GroupLayout JPanel_SLViPhamLayout = new javax.swing.GroupLayout(JPanel_SLViPham);
        JPanel_SLViPham.setLayout(JPanel_SLViPhamLayout);
        JPanel_SLViPhamLayout.setHorizontalGroup(
            JPanel_SLViPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLViPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextSLViPhamDaXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        JPanel_SLViPhamLayout.setVerticalGroup(
            JPanel_SLViPhamLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanel_SLViPhamLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextSLViPhamDaXuLy, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JPanelTongTien.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)), "Tổng tiền bồi thường"));

        lbTextTongTien.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbTextTongTien.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbTextTongTien.setText("none");

        javax.swing.GroupLayout JPanelTongTienLayout = new javax.swing.GroupLayout(JPanelTongTien);
        JPanelTongTien.setLayout(JPanelTongTienLayout);
        JPanelTongTienLayout.setHorizontalGroup(
            JPanelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 287, Short.MAX_VALUE)
                .addContainerGap())
        );
        JPanelTongTienLayout.setVerticalGroup(
            JPanelTongTienLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelTongTienLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbTextTongTien, javax.swing.GroupLayout.DEFAULT_SIZE, 67, Short.MAX_VALUE)
                .addContainerGap())
        );

        btnExport1.setText("Xuất Excel");
        btnExport1.setBorder(null);
        btnExport1.setName(""); // NOI18N
        btnExport1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExport1ActionPerformed(evt);
            }
        });

        button1.setActionCommand("");
        button1.setBackground(new java.awt.Color(255, 51, 0));
        button1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        button1.setForeground(new java.awt.Color(255, 255, 255));
        button1.setLabel("back");
        button1.setName("back"); // NOI18N
        button1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                button1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(ThoiGianPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(All_JP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(Nganh_Jp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(PhuongAncbb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnThongKe, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnExport1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(JPanel_SLViPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPanel_SLThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPanel_SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(JPanelTongTien, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(button1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(ThoiGianPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(button1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(PhuongAncbb, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(All_JP, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(Nganh_Jp, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(JPanelTongTien, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(btnExport1, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnThongKe, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(16, 16, 16)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(JPanel_SL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPanel_SLThietBi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPanel_SLViPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 27, Short.MAX_VALUE))
        );

        button1.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void radBtnMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBtnMonthActionPerformed
        // TODO add your handling code here:
        PhuongAncbb.setSelectedIndex(0);
    }//GEN-LAST:event_radBtnMonthActionPerformed

    private void radBtnYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBtnYearActionPerformed
        // TODO add your handling code here:
        PhuongAncbb.setSelectedIndex(0);
    }//GEN-LAST:event_radBtnYearActionPerformed

    private void dateChooseMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dateChooseMouseClicked

    }//GEN-LAST:event_dateChooseMouseClicked

    private void cboxAllActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxAllActionPerformed
        // TODO add your handling code here:
        String selectedValue = (String) cboxAll.getSelectedItem();
        String panelName = titledBorder.getTitle();
        
        switch (panelName) {
            case "Khoa":
                if ("--Chon Khoa--".equals(selectedValue)){
                    Nganh_Jp.setVisible(false);
                }else{
                    titleBorderNganh.setTitle("Ngành");
                    Nganh_Jp.setVisible(true);
                }
                
                loadDataKhoaJPanel(selectedValue);
                break;
                
            case "Thiết Bị":
                if("Được mượn".equals(selectedValue)&&!"".equals(getDate())){
                    titleBorderNganh.setTitle("Tên thiết bị");
                    Nganh_Jp.setVisible(true);
                }
                else{
                    Nganh_Jp.setVisible(false);
                }
                loadDataThietBiJPanel(selectedValue);
                break;
                
            default:
                loadDataViPhamJPanel(selectedValue); 
                break;
        }  
        Nganh_Jp.repaint();
        Nganh_Jp.revalidate();
    }//GEN-LAST:event_cboxAllActionPerformed

    private void btnThongKeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThongKeActionPerformed
        ThongKeChart thongKeChart = new ThongKeChart(this, rootPaneCheckingEnabled);
        thongKeChart.tkdate = getDate();
        
        thongKeChart.setLocationRelativeTo(null);
        thongKeChart.setVisible(true);
        
        
        
       
    }//GEN-LAST:event_btnThongKeActionPerformed

    private void PhuongAncbbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhuongAncbbActionPerformed
        // TODO add your handling code here:  
        String selectedValue = (String) PhuongAncbb.getSelectedItem();
        switch (selectedValue) {
            case "XỬ LÝ VI PHẠM":
                titledBorder.setTitle("Vi Phạm");
                jpanelCombobox = new String[]{"--Chọn tình trạng--","Đã được xử lý","Đang xử lý"};
                model = new DefaultComboBoxModel<>(jpanelCombobox);
                cboxAll.setModel(model);
                Nganh_Jp.setVisible(false);
                break;
            case "THIẾT BỊ":
                titledBorder.setTitle("Thiết Bị");
                jpanelCombobox = new String[]{"--Chọn tình trạng--", "Được mượn", "Đang được mượn"};
                model = new DefaultComboBoxModel<>(jpanelCombobox);
                cboxAll.setModel(model);
                Nganh_Jp.setVisible(false);
                break;
            default:
                titledBorder.setTitle("Khoa");
                Nganh_Jp.setVisible(false);
                loadDataCboxKhoa();                
                break;
        }
        // Cập nhật lại giao diện
        All_JP.repaint();
        All_JP.revalidate();
    }//GEN-LAST:event_PhuongAncbbActionPerformed

    private void PhuongAncbbPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_PhuongAncbbPropertyChange
   
    }//GEN-LAST:event_PhuongAncbbPropertyChange

    private void PhuongAncbbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_PhuongAncbbItemStateChanged
        // TODO add your handling code here:
        
    }//GEN-LAST:event_PhuongAncbbItemStateChanged

    private void cboxNganhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboxNganhActionPerformed
        // TODO add your handling code here:
        String selectedValue = (String) cboxNganh.getSelectedItem();
        String panelName = titleBorderNganh.getTitle();
        System.out.println(panelName);
        if(panelName.equals("Ngành")){
            if (!"".equals(getDate())){
                loadDataTvNganhByDateTable(selectedValue,getDate());
                loadLabelSLThanhVienNganhByDate(selectedValue,getDate());
                loadLabelClosestTVNganhByDate(selectedValue,getDate());
            }
            else{
                loadDataTvNganhTable(selectedValue);
                loadLabelSLThanhVienNganh(selectedValue);
                loadLabelClosestTVNganh(selectedValue);
            }
        }
        else{
            loadDataTenThietBiByDateTable(selectedValue,getDate());
        }
    }//GEN-LAST:event_cboxNganhActionPerformed

    private void radBtnNoneActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBtnNoneActionPerformed
        // TODO add your handling code here:
        PhuongAncbb.setSelectedIndex(0);
    }//GEN-LAST:event_radBtnNoneActionPerformed

    private void radBtnDateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radBtnDateActionPerformed
        // TODO add your handling code here:
        PhuongAncbb.setSelectedIndex(0);
    }//GEN-LAST:event_radBtnDateActionPerformed

    private void btnExport1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExport1ActionPerformed
        // TODO add your handling code here:
        exportDataToExcel();
        
    }//GEN-LAST:event_btnExport1ActionPerformed

    private void button1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_button1ActionPerformed
    dispose();        // TODO add your handling code here:
    }//GEN-LAST:event_button1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ThongKeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ThongKeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ThongKeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ThongKeGUI.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ThongKeGUI thongKeGUI = new ThongKeGUI();
                thongKeGUI.setLocationRelativeTo(null); // Đặt JFrame ở giữa màn hình
                thongKeGUI.setVisible(true);
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel All_JP;
    private javax.swing.JPanel JPanelTongTien;
    private javax.swing.JPanel JPanel_SL;
    private javax.swing.JPanel JPanel_SLThietBi;
    private javax.swing.JPanel JPanel_SLViPham;
    private javax.swing.JPanel Nganh_Jp;
    private javax.swing.JComboBox<String> PhuongAncbb;
    private javax.swing.JPanel ThoiGianPanel;
    private javax.swing.JButton btnExport1;
    private javax.swing.JButton btnThongKe;
    private java.awt.Button button1;
    private javax.swing.JComboBox<String> cboxAll;
    private javax.swing.JComboBox<String> cboxNganh;
    private com.toedter.calendar.JDateChooser dateChoose;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbTextSLThanhVien;
    private javax.swing.JLabel lbTextSLThietBiDaMuon;
    private javax.swing.JLabel lbTextSLViPhamDaXuLy;
    private javax.swing.JLabel lbTextSub1;
    private javax.swing.JLabel lbTextSub2;
    private javax.swing.JLabel lbTextSub3;
    private javax.swing.JLabel lbTextTongTien;
    private javax.swing.JRadioButton radBtnDate;
    private javax.swing.ButtonGroup radBtnGroup;
    private javax.swing.JRadioButton radBtnMonth;
    private javax.swing.JRadioButton radBtnNone;
    private javax.swing.JRadioButton radBtnYear;
    private javax.swing.JTable tblDanhSach;
    // End of variables declaration//GEN-END:variables
}
