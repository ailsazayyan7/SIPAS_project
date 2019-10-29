package apap.tugas.sipas.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="DIAGNOSIS_PENYAKIT")
public class DiagnosisModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "kode", nullable = false)
    private String kode;

    public long getId() {
        return id;
    }

    public void setId(long idDiagnosis) {
        this.id = idDiagnosis;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getKode() {
        return kode;
    }

    public void setKode(String kode) {
        this.kode = kode;
    }

    @OneToMany(mappedBy = "diagnosis")
    List<PasienDiagnosisPenyakitModel> pasienDiagnosisPenyakit;

}
