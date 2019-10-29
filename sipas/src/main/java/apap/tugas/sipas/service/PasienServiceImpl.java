package apap.tugas.sipas.service;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;
    private PasienModel pasien;

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public String generateCode() {
        String kode;
        int year = (Calendar.getInstance().get(Calendar.YEAR))+5;
        String[] bdate = pasien.getTanggal_lahir().toString().split(" - ");
        String date = bdate[2];
        String month = bdate[1];
        String year1 = bdate[0];
        int gender = 0;
        if (pasien.getJenis_kelamin().equals("Laki-laki")){
            gender = 1;
        }
        else{
            gender = 0;
        }
        String code = year + date + month + year + gender;
        System.out.println(code);
        return code;
    }

    @Override
    public PasienModel getPasienByNIK(String nik) {
        return pasienDb.findByNik(nik);
    }

    @Override
    public void addPasien(PasienModel pasien) {
        pasienDb.save(pasien);
    }

    @Override
    public PasienModel changePasien(PasienModel pasien) {
        //ngambil object pasien yang ingin diubah
        PasienModel targetPasien = pasienDb. findByNik(pasien.getNik());

        try{
            targetPasien.setNama(pasien.getNama());
            targetPasien.setNik(pasien.getNik());
            targetPasien.setJenis_kelamin(pasien.getJenis_kelamin());
            targetPasien.setTanggal_lahir(pasien.getTanggal_lahir());
            targetPasien.setTempat_lahir(pasien.getTempat_lahir());
            pasienDb.save(targetPasien);
            return targetPasien;
        }
        catch (NullPointerException nullException){
            return null;
        }
    }
}
