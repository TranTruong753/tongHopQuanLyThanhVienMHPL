/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThietBi;
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
}
