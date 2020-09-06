package apap.tugas.sipas.repository;

import apap.tugas.sipas.model.AsuransiModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AsuransiDb extends JpaRepository<AsuransiModel, Long> {
}
