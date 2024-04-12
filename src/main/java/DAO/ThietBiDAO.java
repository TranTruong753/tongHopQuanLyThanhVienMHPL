/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

/**
 *
 * @author M S I
 */
public class ThietBiDAO {

    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    public List<ThietBi> layDanhSachThietBi() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<ThietBi> danhSachThietBi = session.createQuery("FROM ThietBi", ThietBi.class).list();
            tx.commit();
            return danhSachThietBi;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    public boolean CheckThietBi(int matb) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from ThietBi where MaTB = :matb");
            query.setParameter("matb", matb);
            List<ThanhVien> result = query.list();
            session.getTransaction().commit();
            if (!result.isEmpty()) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkMuon(int matb) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        try {
            session.getTransaction().begin();

            // Kiểm tra xem có dòng nào trong bảng ThongTinSuDung có mã thiết bị đã cho và có giá trị thời gian trả không
            String hql = "SELECT COUNT(*) FROM ThongTinSuDung t WHERE t.ThietBi.MaTB = :matb AND t.TGTra IS NOT NULL";
            Query<Long> query = session.createQuery(hql, Long.class);
            query.setParameter("matb", matb);
            Long count = query.uniqueResult();

            session.getTransaction().commit();
            return count > 0;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        }
    }
}
