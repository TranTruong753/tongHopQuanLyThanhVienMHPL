package BUS;

import DAO.HibernateUtil;
import DAO.ThongKeDAO;
import org.hibernate.Session;
import java.util.List;

public class ThongKeBUS {

    private final ThongKeDAO thongKeDAO;

    public ThongKeBUS() {
        this.thongKeDAO = new ThongKeDAO();
    }

    public List<String> getTableHeaders(String tableName) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<String> headers = thongKeDAO.getTableHeaders(session, tableName);
            session.getTransaction().commit();
            return headers;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Object[]> getAllThanhVienByCurrentDate (String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThanhVienByCurrentDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
   
    
    
    public List<String> getAllKhoa() {
        List<String> khoaList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            khoaList = new ThongKeDAO().getAllKhoa(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return khoaList;
    }
    public List<Object[]> getAllThanhVienKhoa(String nameKhoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThanhVienKhoa(session,nameKhoa);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Object[]> getAllTvKhoaByDate(String nameKhoa, String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllTvKhoaByDate(session,nameKhoa,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<String> getAllNganh(String khoaName) {
        List<String> nganhList = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            nganhList = new ThongKeDAO().getAllNganh(session,khoaName);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return nganhList;
    }
    
    public List<Object[]> getAllThanhVienNganh(String nameNganh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThanhVienNganh(session,nameNganh);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Object[]> getAllTvNganhByDate(String nameNganh, String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllTvNganhByDate(session,nameNganh,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    
    
    public List<Object[]> getAllThietBiDuocMuonByDate( String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThietBiDuocMuonByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Object[]> getAllThietBiDangDuocMuon() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThietBiDangDuocMuon(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Object[]> getAllThietBiDangDuocMuonByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllThietBiDangDuocMuonByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public List<Object[]> getAllViPhamDaXuLY() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllViPhamDaXuLY(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Object[]> getAllViPhamDaXuLYByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllViPhamDaXuLYByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Object[]> getAllViPhamDangXuLY() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllViPhamDangXuLY(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    public List<Object[]> getAllViPhamDangXuLYByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            List<Object[]> result = thongKeDAO.getAllViPhamDangXuLYByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Long countTvByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countTvByDate(session, date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countTvKhoa(String nameKhoa){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countTvKhoa(session,nameKhoa);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countTvKhoaByDate(String nameKhoa,String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countTvKhoaByDate(session,nameKhoa,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    
    public Long countTvNganh(String nameNganh){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countTvNganh(session,nameNganh);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countTvNganhByDate(String nameKhoa,String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countTvNganhByDate(session,nameKhoa,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countThietBiDuocMuon(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countThietBiDuocMuon(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countThietBiDuocMuonByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countThietBiDuocMuonByDate(session, date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countThietBiDangDuocMuon(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countThietBiDangDuocMuon(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countThietBiDangDuocMuonByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countThietBiDangDuocMuonByDate(session, date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countViPhamDaXuLY(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countViPhamDaXuLY(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    
    public Long countViPhamDaXuLYByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countViPhamDaXuLYByDate(session, date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Long countViPhamDangXuLY(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countViPhamDangXuLY(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    
    public Long countViPhamDangXuLYByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Long result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.countViPhamDangXuLYByDate(session, date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }
    
    public Object[] getClosestThanhVienKhoa(String nameKhoa) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThanhVienKhoa(session,nameKhoa);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThanhVienKhoaByCurrentDate(String nameKhoa, String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThanhVienKhoaByCurrentDate(session,nameKhoa,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThanhVienNganh(String nameNganh) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThanhVienNganh(session,nameNganh);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThanhVienNganhByCurrentDate(String nameNganh, String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThanhVienNganhByCurrentDate(session,nameNganh,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThietBiDuocMuon() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThietBiDuocMuon(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThietBiDuocMuonByCurrentDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThietBiDuocMuonByCurrentDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThietBiDaTra() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThietBiDaTra(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestThietBiDaTraByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestThietBiDaTraByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestViPhamDaXuLY() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestViPhamDaXuLY(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestViPhamDaXuLYByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestViPhamDaXuLYByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestViPhamDangXuLY() {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestViPhamDangXuLY(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }
    
    public Object[] getClosestViPhamDangXuLYByDate(String date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Object[] result = thongKeDAO.getClosestViPhamDangXuLYByDate(session,date);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    public List<Object[]> getKhoa_and_count_Khoa(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_Khoa(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_count_Nganh(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_Nganh(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_count_Khoa_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_Khoa_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_count_Nganh_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_Nganh_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDuocMuon(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongThietBiDuocMuon(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDuocMuon(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongThietBiDuocMuon(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDuocMuon_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongThietBiDuocMuon_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDuocMuon_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongThietBiDuocMuon_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDaTra(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongThietBiDaTra(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }


    public List<Object[]> getNganh_and_cout_SoLuongThietBiDaTra(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongThietBiDaTra(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDaTra_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongThietBiDaTra_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDaTra_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongThietBiDaTra_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDaXuLy(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongViPhamDaXuLy(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDaXuLy(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongViPhamDaXuLy(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDaXuLy_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongViPhamDaXuLy_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDaXuLy_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongViPhamDaXuLy_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDangXuLy(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongViPhamDangXuLy(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDangXuLy(){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongViPhamDangXuLy(session);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDangXuLy_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getKhoa_and_cout_SoLuongViPhamDangXuLy_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }


    public List<Object[]> getNganh_and_cout_SoLuongViPhamDangXuLy_ByDate(String date){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Object[]> result = null;

        try {
            session.beginTransaction();
            result = thongKeDAO.getNganh_and_cout_SoLuongViPhamDangXuLy_ByDate(session,date);
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public int sumTienBoiThuong(){
        Session session = HibernateUtil.getSessionFactory().openSession();
           
        try {
            session.beginTransaction();
            Integer result = thongKeDAO.sumTienBoiThuong(session);
            session.getTransaction().commit();
            return result;
        } catch (Exception e) {
            if (session.getTransaction() != null) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
            return 0;
        } finally {
            session.close();
        }
    }


}
