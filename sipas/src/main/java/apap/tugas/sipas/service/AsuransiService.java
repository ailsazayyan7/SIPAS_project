package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;

import java.util.List;

public interface AsuransiService {
    List<AsuransiModel> getAsuransiList();
    void addAsuransi(AsuransiModel asuransi);
}
