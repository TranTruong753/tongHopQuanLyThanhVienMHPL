/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThanhVien;
import DAO.ThanhVienDAO;
import java.util.List;

/**
 *
 * @author M S I
 */
public class ThanhVienBUS {

    ThanhVienDAO thanhVienDAO = new ThanhVienDAO();

    public List<ThanhVien> layDanhSachThanhVien() {
        return thanhVienDAO.layDanhSachThanhVien();
    }

    public ThanhVien layThanhVienQuaID(int id) {
        return thanhVienDAO.layThanhVienQuaID(id);
    }

    public boolean xoaThanhVienTheoID(int id) {
        return thanhVienDAO.xoaThanhVienTheoID(id);
    }
    
    public boolean xoaThanhVienTheoKhoa(int nam) {
        return thanhVienDAO.xoaThanhVienTheoKhoa(nam);
    }
    
    public boolean insertThanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        return thanhVienDAO.insertThanhVien(matv, hoten, khoa, nganh, sdt);
    }
     
    public boolean updateThanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        return thanhVienDAO.updateThanhVien(matv, hoten, khoa, nganh, sdt);
    }
    
    public boolean themDS(){ 
         return thanhVienDAO.themDS();
    
    }
    
    public ThanhVien Checkthanhvien(int matv) {
        return thanhVienDAO.Checkthanhvien(matv);
    }

    public boolean checkViPham(int matv) {
        return thanhVienDAO.checkViPham(matv);
    }
}
