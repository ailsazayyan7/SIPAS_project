package apap.tugas.sipas.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="ASURANSI")
public class AsuransiModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    @Size(max = 255)
    @Column(name = "nama", nullable = false)
    private String nama;

    @NotNull
    @Size(max = 255)
    @Column(name = "jenis", nullable = false)
    private String jenis;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getJenis() {
        return jenis;
    }

    public void setJenis(String jenis) {
        this.jenis = jenis;
    }

    @ManyToMany
    @JoinTable(
            name = "PASIEN_ASURANSI",
            joinColumns = @JoinColumn(name = "id_asuransi"),
            inverseJoinColumns = @JoinColumn(name = "id_pasien"))
    private List<PasienModel> listPasienAsuransi;

    public List<PasienModel> getListPasienAsuransi() {
        return listPasienAsuransi;
    }

    public void setListPasienAsuransi(List<PasienModel> listPasienAsuransi) {
        this.listPasienAsuransi = listPasienAsuransi;
    }
}
