/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import BUS.*;
import java.io.File;
import java.io.FileInputStream;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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

    Session session;
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
    
     public List<ThietBi> getAllThietBi() {
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

    public ThietBi layThietBiQuaID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ThietBi thietbi = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM ThietBi WHERE MaTB = :id";
            Query<ThietBi> query = session.createQuery(hql);
            query.setParameter("id", id);
            thietbi = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return thietbi;
    }
    
     public boolean kiemTraTonTaiTrongThongTinSD(String id) {
         boolean success= false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ThietBi thietbi = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM thongtinsd WHERE MaTB = :id";
            Query<ThietBi> query = session.createQuery(hql);
            query.setParameter("id", id);
            thietbi = query.uniqueResult();
            tx.commit();
            success = true;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            success = false;
            e.printStackTrace();
            
        } finally {
            session.close();
        }
        return success;
    }
    
    

    public boolean insertThietBi(int matb, String tentb, String mota) {
        boolean check = false;
        ThietBi tb = new ThietBi();
        tb.setMaTB(matb);
        tb.setTenTB(tentb);
        tb.setMoTaTB(mota);

        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tb);
            tx.commit();
            check = true;
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return check;
    }
//

    public boolean updateThietBi(int matb, String tentb, String mota) {
        boolean check = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThietBi tb = session.get(ThietBi.class, matb);
            if (tb != null) {
                tb.setMaTB(matb);
                tb.setTenTB(tentb);
                tb.setMoTaTB(mota);
                session.update(tb);
                tx.commit();
                check = true;
            } else {
                System.out.println("KHÔNG TÌM RA THIẾT BỊ CÓ ID: " + matb);
            }
        } catch (HibernateException e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();

        }
        return check;
    }

    public boolean xoaThietBiTheoID(int matb) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            ThietBi tb = session.get(ThietBi.class, matb);
            if (tb != null) {
                session.delete(tb);
                tx.commit();
                success = true;
            }
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
    
    
    public boolean XoaThietBiQuaMaDau(String maDautb) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            List<ThietBi> danhSachThietBi = session.createQuery("FROM ThietBi", ThietBi.class).list();
            tx.commit();
            for(ThietBi i:danhSachThietBi){
                try{
                    if((i.getMaTB()+"").startsWith(maDautb)){
                    tx = session.beginTransaction();
                    session.delete(i);
                    tx.commit();
                }
                }catch(Exception ex){
                    
                }
            }
            success= true;
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
//    public boolean XoaThietBiQuaMaDau(String maDauTB) {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction tx = null;
//        boolean success = false;
//        try {
//              // Bắt đầu phiên truy vấn
//            tx = session.beginTransaction();
//
//            // Tạo truy vấn HQL để xóa các thiết bị có mã bắt đầu bằng ký tự "1"
//            int deletedCount = session.createQuery(
//                "DELETE FROM thietbi WHERE MaTB LIKE '"+maDauTB+"%'"
//            )
//            .executeUpdate();
//
//            // Hoàn tất giao dịch
//            session.getTransaction().commit();
//
//            // In ra số lượng thiết bị đã bị xóa
//            System.out.println("Số lượng thiết bị đã bị xóa: " + deletedCount);
////            tx = session.beginTransaction();
////            ThietBi tb = session.get(ThietBi.class, maDauTB+"%");
////            if (tb != null) {
////                session.delete(tb);
////                tx.commit();
////                success = true;
////            }
//        } catch (Exception e) {
//            if (tx != null) {
//                tx.rollback();
//            }
//            e.printStackTrace();
//        } finally {
//            session.close();
//        }
//        return success;
//    }

//    public boolean XoaThietBiQuaMaDau(String txt) {
//        // Bắt đầu một phiên Hibernate
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        Transaction transaction = null;
//        boolean success = false;
//        try {
//            // Bắt đầu một giao dịch
//            transaction = session.beginTransaction();
//
//            // Tạo truy vấn xóa
//            String hql = "DELETE FROM thietbi WHERE MaTB LIKE prefix";
//            Query query = session.createQuery(hql);
//            query.setParameter("prefix", txt + "%");
//
//            // Thực thi truy vấn xóa
//            int rowsAffected = query.executeUpdate();
//
//            // Xác nhận và ghi nhận các thay đổi vào cơ sở dữ liệu
//            transaction.commit();
//            success = true;
//
////            System.out.println("Deleted " + rowsAffected + " devices starting with '1'.");
//        } catch (Exception e) {
//            // Nếu có lỗi xảy ra, hủy bỏ giao dịch
//            if (transaction != null) {
//                transaction.rollback();
//            }
//            success = false;
//            e.printStackTrace();
//        } finally {
//            // Đóng phiên sau khi hoàn thành
//            session.close();
//        }
//        return success;
//    }

    public boolean themDS() {
        boolean check = false;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try {
                File selectedFile = fileChooser.getSelectedFile();

                FileInputStream file = new FileInputStream(selectedFile);
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);

                DataFormatter dataFormatter = new DataFormatter();
                for (Row row : sheet) {
                    if (row.getRowNum() == 0) // Bỏ qua dòng tiêu đề
                    {
                        continue;
                    }

                    String matb = dataFormatter.formatCellValue(row.getCell(0));
                    int maTVconver = Integer.parseInt(matb);
                    String tentb = dataFormatter.formatCellValue(row.getCell(1));
                    String motatb = dataFormatter.formatCellValue(row.getCell(2));
                    if (kiemTraTrungId(maTVconver)) {
                        insertThietBi(maTVconver, tentb, motatb);
                    } else {
                        continue;
                    }
                }
                workbook.close();
                file.close();
                check = true;

            } catch (Exception e) {
                e.printStackTrace();
                check = false;

            }
        }
        return check;
    }

    public boolean kiemTraTrungId(int matb) {
        for (ThietBi tb : getAllThietBi()) {
            if ((tb.getMaTB() + "").equals(matb + "")) {
                return false;
            }
        }
        return true;

    }
    
    public static void main(String[] args) {
        ThietBiDAO a =new ThietBiDAO();
        System.out.println(a.XoaThietBiQuaMaDau("1"));
    }
}
