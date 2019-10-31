package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.DiagnosisModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiagnosisPenyakitDb extends JpaRepository<DiagnosisModel, Long> {
    DiagnosisModel findById(long id);
}
