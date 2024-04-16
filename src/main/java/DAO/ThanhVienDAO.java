/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */
package DAO;

import BUS.*;
import DAO.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.service.ServiceRegistry;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author Administrator
 */
public class ThanhVienDAO {

    Session session;
    SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//    ThanhVienBUS tvBUS = new ThanhVienBUS();
    public List<ThanhVien> layDanhSachThanhVien() {
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<ThanhVien> danhSachThanhVien = session.createQuery("FROM ThanhVien", ThanhVien.class).list();
            tx.commit();
            return danhSachThanhVien;
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

    public ThanhVien layThanhVienQuaID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        ThanhVien thanhVien = null;
        try {
            tx = session.beginTransaction();
            String hql = "FROM ThanhVien WHERE MaTV = :id";
            Query<ThanhVien> query = session.createQuery(hql);
            query.setParameter("id", id);
            thanhVien = query.uniqueResult();
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
        return thanhVien;
    }

    public boolean xoaThanhVienTheoID(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            ThanhVien thanhVien = session.get(ThanhVien.class, id);
            if (thanhVien != null) {
                session.delete(thanhVien);
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

    // xóa nhiều thành viên theo năm thành lập
    public boolean xoaThanhVienTheoKhoa(int nam) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        boolean success = false;
        try {
            tx = session.beginTransaction();
            String namVietTat = String.valueOf(nam).substring(2);
            System.out.println(namVietTat);
            Query query = session.createQuery("DELETE FROM ThanhVien WHERE SUBSTRING(MaTV, 3, 2) = '" + namVietTat + "'");
            int rowsAffected = query.executeUpdate();
            tx.commit();
            success = rowsAffected > 0;
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

    public boolean insertThanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        boolean check = false;
        ThanhVien tv = new ThanhVien();
        tv.setMaTV(matv);
        tv.setHoTen(hoten);
        tv.setKhoa(khoa);
        tv.setNganh(nganh);
        tv.setSDT(sdt);
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(tv);
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

    public boolean updateThanhVien(int matv, String hoten, String khoa, String nganh, String sdt) {
        boolean check = false;
        Session session = sessionFactory.openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            ThanhVien tv = session.get(ThanhVien.class, matv);
            if (tv != null) {
                tv.setMaTV(matv);
                tv.setHoTen(hoten);
                tv.setKhoa(khoa);
                tv.setNganh(nganh);
                tv.setSDT(sdt);
                session.update(tv);
                tx.commit();
                check = true;
            } else {
                System.out.println("KHÔNG TÌM RA THÀNH VIÊN CÓ ID: " + matv);
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
    
    public boolean themDS(){
        boolean check = false;
        JFileChooser fileChooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Excel files", "xlsx", "xls");
        fileChooser.setFileFilter(filter);
        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            try{
                File selectedFile = fileChooser.getSelectedFile();

                FileInputStream file = new FileInputStream(selectedFile);
                XSSFWorkbook workbook = new XSSFWorkbook(file);
                XSSFSheet sheet = workbook.getSheetAt(0);
                
            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                if (row.getRowNum() == 0) // Bỏ qua dòng tiêu đề
                    continue;

                String maTV = dataFormatter.formatCellValue(row.getCell(0));
                int maTVconver = Integer.parseInt(maTV);
                String hoTen = dataFormatter.formatCellValue(row.getCell(1));
                String khoa = dataFormatter.formatCellValue(row.getCell(2));
                String nganh = dataFormatter.formatCellValue(row.getCell(3));
                String sdt = dataFormatter.formatCellValue(row.getCell(4));
                
                if(kiemTraTrungId(maTV)){
                    insertThanhVien(maTVconver, hoTen, khoa, nganh, sdt);                               
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
     
    public boolean kiemTraTrungId(String MaTV){
         for (ThanhVien thanhVien : layDanhSachThanhVien()) {
             if((thanhVien.getMaTV()+"").equals(MaTV)){
                 return false;
             }
        }
         return true;
        
    }

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO();
    }
    
    public ThanhVien CheckThanhVienVaoKhuHocTap(int matv) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("from ThanhVien where MaTV = :matv");
            query.setParameter("matv", matv);
            List<ThanhVien> result = query.list();
            session.getTransaction().commit();
            if (!result.isEmpty()) {
                return result.get(0);
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean Checkthanhvien(int matv) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            Query query = session.createQuery("from ThanhVien where MaTV = :matv");
            query.setParameter("matv", matv);
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

    public boolean checkViPham(int matv) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            Query query = session.createQuery("select count(*) from XuLy where MaTV.MaTV = :matv and trangthaixl=1");
            query.setParameter("matv", matv);
            Long count = (Long) query.uniqueResult();
            session.getTransaction().commit();
            return count > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
