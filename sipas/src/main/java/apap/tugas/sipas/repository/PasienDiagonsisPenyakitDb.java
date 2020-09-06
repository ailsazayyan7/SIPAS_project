package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PasienDiagonsisPenyakitDb extends JpaRepository<PasienDiagnosisPenyakitModel, Long> {
}
