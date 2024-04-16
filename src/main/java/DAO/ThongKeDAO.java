package DAO;

import org.hibernate.Session;
import org.hibernate.query.Query;
import java.util.List;

public class ThongKeDAO {

    public List<String> getTableHeaders(Session session, String tableName) {
        String hql = "SELECT COLUMN_NAME " +
                     "FROM INFORMATION_SCHEMA.COLUMNS " +
                     "WHERE TABLE_NAME = :tableName";
        Query<String> query = session.createSQLQuery(hql);
        query.setParameter("tableName", tableName);
        return query.getResultList();
    }
    
    public List<Object[]> getAllThanhVienByCurrentDate(Session session,String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.Khoa, tv.Nganh, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");  
        return query.getResultList();
    }
    
    
    public List<String> getAllKhoa(Session session) {
        List<String> khoa = null;
        try {
            String hql = "SELECT DISTINCT Khoa FROM ThanhVien";
            Query<String> query = session.createQuery(hql, String.class);
            khoa = query.getResultList();
        } catch (Exception e) {
            
        }
        return khoa;
    }
    
    public List<Object[]> getAllThanhVienKhoa(Session session,String nameKhoa) {
        String hql = "SELECT MaTV,HoTen,Khoa,Nganh,SDT " +
                    "FROM ThanhVien "+
                   "WHERE Khoa = :khoa ";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("khoa", nameKhoa);  
        return query.getResultList();
    }
    
    public List<Object[]> getAllTvKhoaByDate(Session session, String nameKhoa, String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.Khoa, tv.Nganh, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Khoa = :khoa AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("khoa", nameKhoa); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<String> getAllNganh(Session session, String khoaName) {
        List<String> nganhList = null;
        try {
            // Sử dụng tham số trong câu truy vấn HQL
            String hql = "SELECT DISTINCT Nganh FROM ThanhVien WHERE Khoa = :khoa";
            Query<String> query = session.createQuery(hql, String.class);
            // Đặt giá trị cho tham số khoa
            query.setParameter("khoa", khoaName);
            // Lấy danh sách các ngành
            nganhList = query.getResultList();
        } catch (Exception e) {
            
        }
        return nganhList;
    }
    
    public List<Object[]> getAllThanhVienNganh(Session session,String nameNganh) {
        String hql = "SELECT MaTV,HoTen,Khoa,Nganh,SDT " +
                    "FROM ThanhVien "+
                   "WHERE Nganh = :nganh ";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("nganh", nameNganh);  
        return query.getResultList();
    }
    
    public List<Object[]> getAllTvNganhByDate(Session session, String nameNganh, String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.Khoa, tv.Nganh, tv.SDT,ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Nganh = :nganh AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("nganh", nameNganh); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }
    
    public List<Object[]> getAllThietBiDuocMuonByDate(Session session, String date) {
        String hql = "SELECT tb.MaTB, tb.TenTB, tb.MoTaTB,ttsd.TGMuon " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }
    
    public List<Object[]> getAllThietBiDangDuocMuon(Session session) {
        String hql = "SELECT tb.MaTB, tb.TenTB, tb.MoTaTB, ttsd.TGMuon " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE ttsd.TGTra IS NULL" ;
        Query<Object[]> query = session.createQuery(hql); 
        return query.getResultList();
    }
    
    public List<Object[]> getAllThietBiDangDuocMuonByDate(Session session, String date) {
        String hql = "SELECT tb.MaTB, tb.TenTB, tb.MoTaTB, ttsd.TGMuon " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate " + // Thêm khoảng trắng ở đây
                     "AND ttsd.TGTra IS NULL";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }
    
    public List<Object[]> getAllViPhamDaXuLY(Session session) {
        String hql = "SELECT xl.MaXL, tv.MaTV, tv.HoTen,tv.Khoa,xl.htXL,xl.SoTien, xl.NgXl,xl.TrangThaiXL " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 1" ;
        Query<Object[]> query = session.createQuery(hql); 
        return query.getResultList();
    }
    
    public List<Object[]> getAllViPhamDaXuLYByDate(Session session, String date) {
        String hql ="SELECT xl.MaXL, tv.MaTV, tv.HoTen, tv.Khoa, xl.htXL, xl.SoTien, xl.NgXl, xl.TrangThaiXL\n" +
                    "FROM XuLy xl\n" +
                    "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV\n" +
                    "WHERE xl.TrangThaiXL = 1\n" +
                    "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate ";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }
    
    public List<Object[]> getAllViPhamDangXuLY(Session session) {
        String hql = "SELECT xl.MaXL, tv.MaTV, tv.HoTen,tv.Khoa,xl.htXL,xl.SoTien, xl.NgXl,xl.TrangThaiXL " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0" ;
        Query<Object[]> query = session.createQuery(hql); 
        return query.getResultList();
    }
    
    public List<Object[]> getAllViPhamDangXuLYByDate(Session session, String date) {
        String hql ="SELECT xl.MaXL, tv.MaTV, tv.HoTen, tv.Khoa, xl.htXL, xl.SoTien, xl.NgXl, xl.TrangThaiXL\n" +
                    "FROM XuLy xl\n" +
                    "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV\n" +
                    "WHERE xl.TrangThaiXL = 0\n" +
                    "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate ";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    //đếm số lượng thành viên theo ngày
    public Long countTvByDate(Session session, String date) {
        String hql = "SELECT COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }   
    
    //đếm số lượng thành viên khoa 
    public Long countTvKhoa(Session session,String nameKhoa) {
        String hql = "SELECT COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Khoa = :khoa";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("khoa", nameKhoa);      
        return query.getSingleResult();
    }
    
    //đếm số lượng thành viên khoa  theo ngày
    public Long countTvKhoaByDate(Session session,String nameKhoa,String date) {
        String hql = "SELECT COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Khoa = :khoa AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("khoa", nameKhoa);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }
    
    //đếm số lượng thành viên ngành 
    public Long countTvNganh(Session session,String nameNganh) {
        String hql = "SELECT COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Nganh = :nganh";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nganh", nameNganh);
        return query.getSingleResult();
    }
    
    //đếm số lượng thành viên ngành theo ngày
    public Long countTvNganhByDate(Session session,String nameNganh,String date) {
        String hql = "SELECT COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Nganh = :nganh AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("nganh", nameNganh);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }
    
    //đếm số lượng thiết bị được mượn 
    public Long countThietBiDuocMuon(Session session) {
        String hql = "SELECT COUNT(tb.MaTB) " +
                     "FROM ThietBi tb ";
        Query<Long> query = session.createQuery(hql, Long.class);
        return query.getSingleResult();
    }
    
    //đếm số lượng thiết bị được mượn theo ngày 
    public Long countThietBiDuocMuonByDate(Session session, String date) {
        String hql = "SELECT COUNT(tb.MaTB) " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate";
        Query<Long> query = session.createQuery(hql, Long.class);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }

    //đếm số lượng thiết bị đang được mượn 
    public Long countThietBiDangDuocMuon(Session session) {
        String hql = "SELECT COUNT(tb.MaTB) " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE ttsd.TGTra IS NULL" ;
        Query<Long> query = session.createQuery(hql, Long.class); 
        return query.getSingleResult();
    }
    
    //đếm số lượng thiết bị đang được mượn theo ngày 
    public Long countThietBiDangDuocMuonByDate(Session session, String date) {
        String hql = "SELECT COUNT(tb.MaTB) " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate " + // Thêm khoảng trắng ở đây
                     "AND ttsd.TGTra IS NULL";
        Query<Long> query = session.createQuery(hql, Long.class); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }
    
    //đếm số lượng vi phạm đã xử lý 
    public Long countViPhamDaXuLY(Session session) {
        String hql ="SELECT COUNT(xl.MaTV) " +
                    "FROM XuLy xl " +
                    "WHERE xl.TrangThaiXL = 1 ";
        Query<Long> query = session.createQuery(hql, Long.class); 
        return query.getSingleResult();
    }
    
    //đếm số lượng vi phạm đã xử lý theo ngày 
    public Long countViPhamDaXuLYByDate(Session session, String date) {
        String hql ="SELECT COUNT(xl.MaTV) " +
                    "FROM XuLy xl " +
                    "WHERE xl.TrangThaiXL = 1 " +
                    "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate ";
        Query<Long> query = session.createQuery(hql, Long.class); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }

    //đếm số lượng vi phạm đang xử lý 
    public Long countViPhamDangXuLY(Session session) {
        String hql = "SELECT COUNT(xl.MaXL) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0" ;
        Query<Long> query = session.createQuery(hql, Long.class); 
        return query.getSingleResult();
    }
    
    //đếm số lượng vi phạm đang xử lý theo ngày
    public Long countViPhamDangXuLYByDate(Session session, String date) {
        String hql ="SELECT COUNT(xl.MaXL) " +
                    "FROM XuLy xl " +
                    "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                    "WHERE xl.TrangThaiXL = 0 " +
                    "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate ";
        Query<Long> query = session.createQuery(hql, Long.class); 
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getSingleResult();
    }
    
    public Object[] getClosestThanhVienKhoa(Session session, String nameKhoa) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Khoa = :khoa " +
                     "ORDER BY ttsd.TGVao DESC"; // Sắp xếp theo TGVao giảm dần
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("khoa", nameKhoa);
        query.setMaxResults(1); // Chỉ lấy một kết quả (thành viên gần nhất)
        return (Object[]) query.uniqueResult(); // Trả về một mảng Object chứa thông tin của thành viên gần nhất
    }
    
    public Object[] getClosestThanhVienKhoaByCurrentDate(Session session, String nameKhoa,String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Khoa= :khoa AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate " +
                     "ORDER BY ttsd.TGVao DESC"; // Sắp xếp theo TGVao giảm dần
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("khoa", nameKhoa);
        query.setParameter("formattedDate", "%" + date + "%");  
        query.setMaxResults(1); // Chỉ lấy một kết quả (thành viên gần nhất)
        return (Object[]) query.uniqueResult(); // Trả về một mảng Object chứa thông tin của thành viên gần nhất
    }
    
    public Object[] getClosestThanhVienNganh(Session session, String nameNganh) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Nganh = :nganh " +
                     "ORDER BY ttsd.TGVao DESC"; // Sắp xếp theo TGVao giảm dần
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("nganh", nameNganh);
        query.setMaxResults(1); // Chỉ lấy một kết quả (thành viên gần nhất)
        return (Object[]) query.uniqueResult(); // Trả về một mảng Object chứa thông tin của thành viên gần nhất
    }
    public Object[] getClosestThanhVienNganhByCurrentDate(Session session, String nameNganh,String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, tv.SDT, ttsd.TGVao " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE tv.Nganh= :nganh AND DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate " +
                     "ORDER BY ttsd.TGVao DESC"; // Sắp xếp theo TGVao giảm dần
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("nganh", nameNganh);
        query.setParameter("formattedDate", "%" + date + "%");  
        query.setMaxResults(1); // Chỉ lấy một kết quả (thành viên gần nhất)
        return (Object[]) query.uniqueResult(); // Trả về một mảng Object chứa thông tin của thành viên gần nhất
    }
    
    public Object[] getClosestThietBiDuocMuon(Session session) {
        String hql = "SELECT tb.MaTB, tb.TenTB, ttsd.TGMuon " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "ORDER BY ttsd.TGMuon DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setMaxResults(1); // Chỉ lấy một kết quả (thiết bị được mượn gần nhất)
        return (Object[]) query.uniqueResult();
    }

    
    public Object[] getClosestThietBiDuocMuonByCurrentDate(Session session, String date) {
        String hql = "SELECT tb.MaTB, tb.TenTB, ttsd.TGMuon " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate " +
                     "ORDER BY ttsd.TGMuon DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        query.setMaxResults(1); // Chỉ lấy một kết quả (thiết bị được mượn gần nhất)
        return (Object[]) query.uniqueResult();
    }
    
    public Object[] getClosestThietBiDaTra(Session session) {
        String hql = "SELECT tb.MaTB, tb.TenTB, ttsd.TGTra " +
                     "FROM ThietBi tb " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                     "WHERE ttsd.TGTra IS NOT NULL " + // Sửa thành IS NOT NULL
                     "ORDER BY ttsd.TGTra DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setMaxResults(1); // Chỉ lấy một kết quả (thiết bị đang được mượn gần nhất)
        return (Object[]) query.uniqueResult();
    }


    
    public Object[] getClosestThietBiDaTraByDate(Session session, String date) {
        String hql = "SELECT tb.MaTB, tb.TenTB, ttsd.TGTra " +
                    "FROM ThietBi tb " +
                    "INNER JOIN ThongTinSD ttsd ON ttsd.MaTB = tb.MaTB " +
                    "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') = :formattedDate " + // Sửa thành '=' để so sánh ngày chính xác
                    "AND ttsd.TGTra IS NOT NULL " +
                    "ORDER BY ttsd.TGTra DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        query.setMaxResults(1); // Chỉ lấy một kết quả (thiết bị đang được mượn gần nhất)
        return (Object[]) query.uniqueResult();
    }
    
    
    public Object[] getClosestViPhamDaXuLY(Session session) {
        String hql = "SELECT tv.MaTV, tv.HoTen, xl.htXL, xl.SoTien " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 1 " + 
                     "ORDER BY xl.NgXl DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setMaxResults(1); // Chỉ lấy một kết quả (vi phạm đã được xử lý gần nhất)
        return (Object[]) query.uniqueResult();
    }

    
    public Object[] getClosestViPhamDaXuLYByDate(Session session, String date) {
        String hql = "SELECT tv.MaTV, tv.HoTen, xl.htXL, xl.SoTien " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 1 " +
                     "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " + // Thêm khoảng trắng ở đây
                     "ORDER BY xl.NgXl DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        query.setMaxResults(1); // Chỉ lấy một kết quả (vi phạm đã được xử lý gần nhất)
        return (Object[]) query.uniqueResult();
    }

    
    public Object[] getClosestViPhamDangXuLY(Session session) {
        String hql = "SELECT xl.MaXL, tv.MaTV, xl.htXL, xl.SoTien " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0 " +
                     "ORDER BY xl.NgXl DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setMaxResults(1); // Chỉ lấy một kết quả (vi phạm đã được xử lý gần nhất)
        return (Object[]) query.uniqueResult();
    }


    
    public Object[] getClosestViPhamDangXuLYByDate(Session session, String date) {
        String hql = "SELECT xl.MaXL, tv.MaTV, xl.htXL, xl.SoTien " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0 " +
                     "AND DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " +
                     "ORDER BY xl.NgXl DESC";
        Query<Object[]> query = session.createQuery(hql); 
        query.setParameter("formattedDate", "%" + date + "%");
        query.setMaxResults(1); // Chỉ lấy một kết quả (vi phạm đã được xử lý gần nhất)
        return (Object[]) query.uniqueResult();
    }

    //VẼ BIỂU ĐỒ

    public List <Object[]> getKhoa_and_cout_Khoa (Session session) {
        String hql = "SELECT Khoa, COUNT(MaTV) " +
                     "FROM ThanhVien " +
                     "GROUP BY Khoa";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }
    
    public List <Object[]> getNganh_and_cout_Nganh (Session session) {
        String hql = "SELECT Nganh, COUNT(MaTV) " +
                     "FROM ThanhVien " +
                     "GROUP BY Nganh";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List <Object[]> getKhoa_and_cout_Khoa_ByDate(Session session, String date){
        String hql = "SELECT tv.Khoa, COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY Khoa";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }
    

    public List <Object[]> getNganh_and_cout_Nganh_ByDate(Session session, String date){
        String hql = "SELECT tv.Nganh, COUNT(tv.MaTV) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "WHERE DATE_FORMAT(ttsd.TGVao, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY Nganh";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDuocMuon (Session session) {
        String hql = "SELECT tv.Khoa, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDuocMuon (Session session) {
        String hql = "SELECT tv.Nganh, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDuocMuon_ByDate(Session session, String date) {
        String hql = "SELECT tv.Khoa, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDuocMuon_ByDate(Session session, String date) {
        String hql = "SELECT tv.Nganh, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGMuon, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }


    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDaTra (Session session) {
        String hql = "SELECT tv.Khoa, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE ttsd.TGTra IS NOT NULL " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongThietBiDaTra (Session session) {
        String hql = "SELECT tv.Nganh, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE ttsd.TGTra IS NOT NULL " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongThietBiDaTra_ByDate(Session session, String date) {
        String hql = "SELECT tv.Khoa, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGTra, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }


    public List<Object[]> getNganh_and_cout_SoLuongThietBiDaTra_ByDate(Session session, String date) {
        String hql = "SELECT tv.Nganh, COUNT(tb.MaTB) " +
                     "FROM ThanhVien tv " +
                     "INNER JOIN ThongTinSD ttsd ON ttsd.MaTV = tv.MaTV " +
                     "INNER JOIN ThietBi tb ON tb.MaTB = ttsd.MaTB " +
                     "WHERE DATE_FORMAT(ttsd.TGTra, '%Y-%m-%d') LIKE :formattedDate " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDaXuLy (Session session) {
        String hql = "SELECT tv.Khoa, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 1 " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDaXuLy (Session session) {
        String hql = "SELECT tv.Nganh, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 1 " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDaXuLy_ByDate(Session session, String date) {
        String hql = "SELECT tv.Khoa, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " +
                     "AND xl.TrangThaiXL = 1 " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDaXuLy_ByDate(Session session, String date) {
        String hql = "SELECT tv.Nganh, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " +
                     "AND xl.TrangThaiXL = 1 " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDangXuLy (Session session) {
        String hql = "SELECT tv.Khoa, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0 " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getNganh_and_cout_SoLuongViPhamDangXuLy (Session session) {
        String hql = "SELECT tv.Nganh, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE xl.TrangThaiXL = 0 " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        return query.getResultList();
    }

    public List<Object[]> getKhoa_and_cout_SoLuongViPhamDangXuLy_ByDate(Session session, String date) {
        String hql = "SELECT tv.Khoa, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " +
                     "AND xl.TrangThaiXL = 0 " +
                     "GROUP BY tv.Khoa";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }


    public List<Object[]> getNganh_and_cout_SoLuongViPhamDangXuLy_ByDate(Session session, String date) {
        String hql = "SELECT tv.Nganh, COUNT(xl.MaTV) " +
                     "FROM XuLy xl " +
                     "INNER JOIN ThanhVien tv ON tv.MaTV = xl.MaTV " +
                     "WHERE DATE_FORMAT(xl.NgXl, '%Y-%m-%d') LIKE :formattedDate " +
                     "AND xl.TrangThaiXL = 0 " +
                     "GROUP BY tv.Nganh";
        Query<Object[]> query = session.createQuery(hql);
        query.setParameter("formattedDate", "%" + date + "%");
        return query.getResultList();
    }

    public int sumTienBoiThuong(Session session) {
        String hql = "SELECT SUM(SoTien) FROM XuLy WHERE SoTien IS NOT NULL AND TrangThaiXL = 1";
        Query<Long> query = session.createQuery(hql, Long.class);
        Long totalMoney = query.uniqueResult();
        return totalMoney != null ? totalMoney.intValue() : 0;

    }



}

