package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

@Entity
@Table (name = "PASIEN_DIAGNOSIS_PENYAKIT")
public class PasienDiagnosisPenyakitModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Column(name = "tanggal_diagnosis", nullable = false)
    private Date tanggal_diagnosis;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getTanggal_diagnosis() {
        return tanggal_diagnosis;
    }

    public void setTanggal_diagnosis(Date tanggal_diagnosis) {
        this.tanggal_diagnosis = tanggal_diagnosis;
    }

    @ManyToOne
    @JoinColumn(name = "id_pasien")
    PasienModel pasien;

    @ManyToOne
    @JoinColumn(name = "id_diagnosis_penyakit")
    DiagnosisModel diagnosis;
}
