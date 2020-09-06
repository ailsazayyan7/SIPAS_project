package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisModel;
import apap.tugas.sipas.repository.DiagnosisPenyakitDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class DiagnosisPenyakitServiceImpl implements DiagnosisPenyakitService {
    @Autowired
    private DiagnosisPenyakitDb diagnosisPenyakitDb;

    @Override
    public List<DiagnosisModel> getDiagnosisList() {
        return diagnosisPenyakitDb.findAll();
    }

    @Override
    public Optional<DiagnosisModel> getDiagnosisPenyakitById(Long id) {
        return diagnosisPenyakitDb.findById(id);
    }

    @Override
    public void addDiagnosisPenyakit(DiagnosisModel diagnosisPenyakit) {
        diagnosisPenyakitDb.save(diagnosisPenyakit);
    }

    @Override
    public void deleteDiagnosisPenyakit(DiagnosisModel diagnosisPenyakit) {
        diagnosisPenyakitDb.delete(diagnosisPenyakit);
    }


}
