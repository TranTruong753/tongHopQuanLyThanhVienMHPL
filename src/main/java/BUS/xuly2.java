package BUS;



import javax.persistence.Table;

import javax.persistence.Column;    
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Getter
@Setter
@Table(name = "xuly")
public class xuly2 {

    @Id
    @Column(name = "MaXL")
    private int MaXL;

    @Column(name = "MaTV")
    private int MaTV;

    @Column(name = "HinhThucXL")
    private String HinhThucXL;

    @Column(name = "SoTien", nullable = true)
    private Integer SoTien;

    @Column(name = "NgayXL")
    private String NgayXL;

    @Column(name = "TrangThaiXL")
    private int TrangThaiXL;

    public xuly2() {
    }

    public xuly2(int maXL, int maTV, String hinhThucXL, Integer soTien, String ngayXL, int trangThaiXL) {
        this.MaXL = maXL;
        this.MaTV = maTV;
        this.HinhThucXL = hinhThucXL;
        this.SoTien = soTien;
        this.NgayXL = ngayXL;
        this.TrangThaiXL = trangThaiXL;
    }
    public xuly2(int maXL, int maTV, String hinhThucXL, String ngayXL, int trangThaiXL) {
        this.MaXL = maXL;
        this.MaTV = maTV;
        this.HinhThucXL = hinhThucXL;
        this.NgayXL = ngayXL;
        this.TrangThaiXL = trangThaiXL;
    }
}
