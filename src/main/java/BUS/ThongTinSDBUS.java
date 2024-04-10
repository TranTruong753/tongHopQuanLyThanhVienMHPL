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
    
     public List loadthongtinsd() {
        return thongTinSdDAO.loadthongtinsd();
    }

    public boolean addthongtinsd(ThongTinSD ttsd) {
        return thongTinSdDAO.addthongtinsd(ttsd);
    }
}
