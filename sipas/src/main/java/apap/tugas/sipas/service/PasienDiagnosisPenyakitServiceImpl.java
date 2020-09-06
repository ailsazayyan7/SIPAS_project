package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.repository.PasienDiagonsisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PasienDiagnosisPenyakitServiceImpl implements PasienDiagnosisPenyakitService {

    @Autowired
    PasienDiagonsisPenyakitDb pasienDiagonsisPenyakitDb;

    @Override
    public void addPasienDiagnosisPenyakit(PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit) {
        pasienDiagonsisPenyakitDb.save(pasienDiagnosisPenyakit);
    }
}
