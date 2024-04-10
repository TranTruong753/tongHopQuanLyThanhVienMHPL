/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 *
 * @author Administrator
 */
@Data
@Entity
@Table(name="ThanhVien")
public class ThanhVien {

    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MaTV;
    
    @Column
    private String HoTen;
    
    @Column
    private String Khoa;
    
    @Column
    private String Nganh; 
    
    @Column
    private String SDT;
}

