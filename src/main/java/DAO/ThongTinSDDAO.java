/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author Admin
 */
public class ThongTinSDDAO {
    Session session;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
    
    
     public List loadthongtinsd() {
        session = sessionFactory.openSession();
        List<ThongTinSD> thongtinsd;
        session.beginTransaction();
        thongtinsd = session.createQuery("from ThongTinSD", ThongTinSD.class).list();
        session.getTransaction().commit();
        return thongtinsd;
    }
    
    
    public boolean addthongtinsd(ThongTinSD ttsd) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(ttsd);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
}
