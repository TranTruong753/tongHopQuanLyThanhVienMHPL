/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import java.util.ArrayList;

import DAO.xulyDAO;
import BUS.xuly2;

/**
 *
 * @author Tony
 */
public class xulyBUS {
       private ArrayList<xuly2> xulyList;

    public xulyBUS() {
        xulyList = new ArrayList<>();
        xulyList = (ArrayList<xuly2>) xulyDAO.read();
    }

    public ArrayList<xuly2> getXulyList() {
        return xulyList;
    }

    public void setXulyList(ArrayList<xuly2> xulyList) {
        this.xulyList = xulyList;
    }

    public void add(xuly2 xuly) {
        if(xuly.getSoTien() == null){
            xulyDAO.create(xuly.getMaXL(), xuly.getMaTV(), xuly.getHinhThucXL(), xuly.getNgayXL(), xuly.getTrangThaiXL());
        }else{
            xulyDAO.create(xuly.getMaXL(), xuly.getMaTV(), xuly.getHinhThucXL(), xuly.getSoTien(), xuly.getNgayXL(), xuly.getTrangThaiXL());
        }
        xulyList.add(xuly);
    }

    public void delete(int MaXL) {
        xulyDAO.delete(MaXL);
        for (xuly2 xuly : xulyList) {
            if (xuly.getMaXL() == MaXL) {
                xulyList.remove(xuly);
                return;
            }
        }
    }

    public void update(xuly2 xuly) {
        if (xuly.getSoTien() == null){
            xulyDAO.update(xuly.getMaXL(), xuly.getMaTV(), xuly.getHinhThucXL(), xuly.getNgayXL(), xuly.getTrangThaiXL());
            for (xuly2 xuly1 : xulyList) {
                if (xuly1.getMaXL() == xuly.getMaXL()) {
                    xuly1.setMaTV(xuly.getMaTV());
                    xuly1.setHinhThucXL(xuly.getHinhThucXL());
                    xuly1.setNgayXL(xuly.getNgayXL());
                    xuly1.setTrangThaiXL(xuly.getTrangThaiXL());
                    return;
                }
            }
        }else{
            xulyDAO.update(xuly.getMaXL(), xuly.getMaTV(), xuly.getHinhThucXL(), xuly.getSoTien(), xuly.getNgayXL(), xuly.getTrangThaiXL());
            for (xuly2 xuly1 : xulyList) {
                if (xuly1.getMaXL() == xuly.getMaXL()) {
                    xuly1.setMaTV(xuly.getMaTV());
                    xuly1.setHinhThucXL(xuly.getHinhThucXL());
                    xuly1.setSoTien(xuly.getSoTien());
                    xuly1.setNgayXL(xuly.getNgayXL());
                    xuly1.setTrangThaiXL(xuly.getTrangThaiXL());
                    return;
                }
            }
        }
        
    }
    
    public int createMaXL(){
        int max = 0;
        for(xuly2 xuly : xulyList){
            if(xuly.getMaXL() > max){
                max = xuly.getMaXL();
            }
        }
        return max + 1;
    }
}
