package apap.tugas.sipas.service;

import apap.tugas.sipas.model.DiagnosisModel;

import java.util.List;
import java.util.Optional;

public interface DiagnosisPenyakitService {
    List<DiagnosisModel> getDiagnosisList();
    Optional<DiagnosisModel> getDiagnosisPenyakitById(Long id);
    void addDiagnosisPenyakit(DiagnosisModel diagnosisPenyakit);
    void deleteDiagnosisPenyakit(DiagnosisModel diagnosisPenyakit);
}
