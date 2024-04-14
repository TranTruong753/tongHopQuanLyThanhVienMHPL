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
public class ThongTinSD {
    @Id
    private int MaTT;
    @Column
    private Date TGVao;
    @Column
    private Date TGMuon;
    @Column
    private Date TGTra;
    @ManyToOne
    @JoinColumn(name = "MaTV")
    private ThanhVien MaTV;
    @ManyToOne
    @JoinColumn(name = "MaTB")
    private ThietBi MaTB;

}

