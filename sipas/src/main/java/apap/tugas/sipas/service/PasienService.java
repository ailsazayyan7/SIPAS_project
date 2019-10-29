package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;

import java.util.List;

public interface PasienService {
    //Method untuk mendapatkan semua data Restoran yang tersimpan
    List<PasienModel> getPasienList();
    String generateCode();
    PasienModel getPasienByNIK(String nik);
    //Method untuk menambah Pasien
    void addPasien(PasienModel restoran);

    PasienModel changePasien(PasienModel pasien);
}
