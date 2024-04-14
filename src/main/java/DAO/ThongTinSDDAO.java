/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import BUS.*;

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
        thongtinsd = session.createQuery("from ThongTinSD where tgvao is not null and tgvao != '' and tgmuon is null and tgtra is null order by MaTT desc", ThongTinSD.class).list();
        session.getTransaction().commit();
        return thongtinsd;
    }
    public List loadthongtinsdMaTT() {
        session = sessionFactory.openSession();
        List<ThongTinSD> thongtinsd;
        session.beginTransaction();
        thongtinsd = session.createQuery("from ThongTinSD order by MaTT desc", ThongTinSD.class).list();
        session.getTransaction().commit();
        return thongtinsd;
    }

    public boolean addthongtinsd(ThongTinSD ttsd) {
        System.out.println("Thông tin sử dụng đã được thêm vào:");
        System.out.println("MaTT: " + ttsd.getMaTT());
        System.out.println("TgVao: " + ttsd.getTGVao());
        System.out.println("TgMuon: " + ttsd.getTGMuon());
        System.out.println("TgTra: " + ttsd.getTGTra());
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

// code th quy
    // lấy ra toàn bộ thông tin sử dụng
    public List<ThongTinSD> layDanhSachMuonTra() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        List<ThongTinSD> danhSachMuonTra = null;
        try {
            tx = session.beginTransaction();
            String query = "FROM ThongTinSD ORDER BY TGMuon DESC";
            danhSachMuonTra = session.createQuery(query, ThongTinSD.class).list();
            tx.commit();
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return danhSachMuonTra;
    }

    public boolean insertThongTinSuDung(ThongTinSD thongTinSuDung) {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            session.save(thongTinSuDung);
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return success;
    }

    public boolean updateThoiGianTra(int maThietBi) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();

            // Lấy thông tin sử dụng gần nhất dựa trên mã thiết bị
            ThongTinSD thongTinSuDung = getLatestThongTinSuDung(maThietBi);

            if (thongTinSuDung != null) {
                // Cập nhật thời gian trả thành thời gian hiện tại
                thongTinSuDung.setTGTra(new Date());

                session.update(thongTinSuDung);
                transaction.commit();

                return true; // Cập nhật thành công
            }

            return false; // Không tìm thấy thông tin sử dụng dựa trên mã thiết bị
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false; // Có lỗi xảy ra
        }
    }

    public ThongTinSD getLatestThongTinSuDung(int maThietBi) {
        Session session = sessionFactory.openSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();

            // Truy vấn thông tin sử dụng gần nhất dựa trên mã thiết bị, sắp xếp theo thời gian mượn giảm dần
            String hql = "FROM ThongTinSD t WHERE t.MaTB.MaTB = :maThietBi AND t.TGTra IS NULL ORDER BY t.TGMuon DESC";
            Query<ThongTinSD> query = session.createQuery(hql, ThongTinSD.class);
            query.setParameter("maThietBi", maThietBi);
            query.setMaxResults(1);
            ThongTinSD thongTinSuDung = query.uniqueResult();

            transaction.commit();

            return thongTinSuDung;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return null;
        }
    }

}
