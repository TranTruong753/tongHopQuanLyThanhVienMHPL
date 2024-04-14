/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.*;
import java.util.List;

/**
 *
 * @author Admin
 */
public class ThongTinSDBUS {

    ThongTinSDDAO thongTinSdDAO = new ThongTinSDDAO();
    public List loadthongtinsdMaTT() {
        return thongTinSdDAO.loadthongtinsdMaTT();
    }
    public List loadthongtinsd() {
        return thongTinSdDAO.loadthongtinsd();
    }

    public List<ThongTinSD> layDanhSachMuonTra() {
        return thongTinSdDAO.layDanhSachMuonTra();
    }

    public boolean addthongtinsd(ThongTinSD ttsd) {
        return thongTinSdDAO.addthongtinsd(ttsd);
    }

    public boolean insertThongTinSuDung(ThongTinSD thongTinSuDung) {
        return thongTinSdDAO.insertThongTinSuDung(thongTinSuDung);
    }

    public boolean updateThoiGianTra(int maThietBi) {
        return thongTinSdDAO.updateThoiGianTra(maThietBi);
    }

    public ThongTinSD getLatestThongTinSuDung(int maThietBi) {
        return thongTinSdDAO.getLatestThongTinSuDung(maThietBi);
    }

}
