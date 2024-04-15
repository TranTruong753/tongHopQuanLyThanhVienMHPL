/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import java.util.List;

import org.hibernate.Session;

import BUS.xuly2;
/**
 *
 * @author Tony
 */
public class xulyDAO {
    public static List<xuly2> read(){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            List<xuly2> xylyList = session.createQuery("from xuly2", xuly2.class).list();
            session.getTransaction().commit();
            session.close();
            return xylyList;
        }
    }

    public static void create(int MaXL, int MaTV, String HinhThucXL, int SoTien, String NgayXL, int TrangThaiXL){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            xuly2 xuly = new xuly2(MaXL, MaTV, HinhThucXL, SoTien, NgayXL, TrangThaiXL);
            session.save(xuly);
            session.getTransaction().commit();
            session.close();
        }
    }
    public static void create(int MaXL, int MaTV, String HinhThucXL, String NgayXL, int TrangThaiXL){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            xuly2 xuly = new xuly2(MaXL, MaTV, HinhThucXL, NgayXL, TrangThaiXL);
            session.save(xuly);
            session.getTransaction().commit();
            session.close();
        }
    }

    public static void update(int MaXL, int MaTV, String HinhThucXL, int SoTien, String NgayXL, int TrangThaiXL){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            xuly2 xuly = session.get(xuly2.class, MaXL);
            xuly.setMaTV(MaTV);
            xuly.setHinhThucXL(HinhThucXL);
            xuly.setSoTien(SoTien);
            xuly.setNgayXL(NgayXL);
            xuly.setTrangThaiXL(TrangThaiXL);
            session.update(xuly);
            session.getTransaction().commit();
            session.close();
        }
    }
    public static void update(int MaXL, int MaTV, String HinhThucXL, String NgayXL, int TrangThaiXL){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            xuly2 xuly = session.get(xuly2.class, MaXL);
            xuly.setMaTV(MaTV);
            xuly.setHinhThucXL(HinhThucXL);
            xuly.setNgayXL(NgayXL);
            xuly.setTrangThaiXL(TrangThaiXL);
            session.update(xuly);
            session.getTransaction().commit();
            session.close();
        }
    }
    
    public static void delete(int MaXL){
        try(Session session = HibernateUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            xuly2 xuly = session.get(xuly2.class, MaXL);
            session.delete(xuly);
            session.getTransaction().commit();
            session.close();
        }
    }
}
