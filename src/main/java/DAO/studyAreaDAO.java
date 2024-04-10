package DAO;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.query.Query;

public class studyAreaDAO {

//    Session session;
//
//    public studyAreaDAO() {
//        session = HibernateUtils.getSessionFactory().openSession();
//    }
//
//    public List loadthongtinsd() {
//        List<thongtinsd> thongtinsd;
//        session.beginTransaction();
//        thongtinsd = session.createQuery("from thongtinsd", thongtinsd.class).list();
//        session.getTransaction().commit();
//        return thongtinsd;
//    }
//
//    public thanhvien Checkthanhvien(int matv) {
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("from thanhvien where MaTV = :matv");
//            query.setParameter("matv", matv);
//            List<thanhvien> result = query.list();
//            session.getTransaction().commit();
//            if (!result.isEmpty()) {
//                return result.get(0);
//            } else {
//                return null;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    public boolean checkViPham(int matv) {
//        try {
//            session.beginTransaction();
//            Query query = session.createQuery("select count(*) from xuly where MaTV.MaTV = :matv");
//            query.setParameter("matv", matv);
//            Long count = (Long) query.uniqueResult();
//            session.getTransaction().commit();
//            return count > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }
//
//    public boolean addthongtinsd(thongtinsd ttsd) {
//        try {
//            session.beginTransaction();
//            session.save(ttsd);
//            session.getTransaction().commit();
//            return true;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return false;
//        }
//    }

}
