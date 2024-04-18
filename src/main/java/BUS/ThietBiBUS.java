/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import BUS.ThietBi;
import DAO.ThietBiDAO;
import java.util.List;

/**
 *
 * @author M S I
 */
public class ThietBiBUS {

    ThietBiDAO thietBiDAO = new ThietBiDAO();

    public List<ThietBi> layDanhSachThietBi() {
        return thietBiDAO.layDanhSachThietBi();
    }

    public boolean CheckThietBi(int matb) {
        return thietBiDAO.CheckThietBi(matb);
    }

    public boolean checkMuon(int matb) {
        return thietBiDAO.checkMuon(matb);
    }
    public List<ThietBi> getAllThietBi() {
        return thietBiDAO.getAllThietBi();
    }
    public ThietBi layThietBiQuaID(int id) {
        return thietBiDAO.layThietBiQuaID(id);
    }
    public boolean insertThietBi(int matb,String tentb, String mota) {
        return thietBiDAO.insertThietBi(matb,tentb, mota);
    }
    public boolean updateThietBi(int matb,String tentb, String mota) {
        return thietBiDAO.updateThietBi(matb,tentb, mota);
    }
    public boolean xoaThietBiTheoID(int id) {
        return thietBiDAO.xoaThietBiTheoID(id);
    }
    public boolean themDS(){ 
        return thietBiDAO.themDS();
    }
    
     public boolean XoaThietBiTheoKiTuDau(String maDautb){ 
        return thietBiDAO.XoaThietBiQuaMaDau(maDautb);
    }
}
