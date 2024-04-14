package BUS;

import DAO.*;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class XuLy {

    @Id
    private int MaXL;
    @Column(name = "HinhThucXL")
    private String htXL;
    @Column
    private int SoTien;
    @Column(name = "NgayXL")
    private Date NgXl;
    @Column(name ="trangthaixl")
    private int TrangThaiXL;
    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien MaTV;
}
