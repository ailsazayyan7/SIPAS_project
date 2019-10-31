package apap.tugas.sipas.service;

import apap.tugas.sipas.model.AsuransiModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.repository.AsuransiDb;
import apap.tugas.sipas.repository.PasienDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional

public class PasienServiceImpl implements PasienService {
    @Autowired
    private PasienDb pasienDb;
    private PasienModel pasien;
    private AsuransiDb asuransiDb;

    @Override
    public List<PasienModel> getPasienList() {
        return pasienDb.findAll();
    }

    @Override
    public String generateCode(PasienModel pasien) {
        String code;
        String[] date = LocalDate.now().toString().split("-");
        String year = date[0];
        int year5 = Integer.parseInt(year) + 5;

        int birthDate = pasien.getTanggal_lahir().getDate();
        String tanggal = String.format("%02d", birthDate);
        int birthMonth = pasien.getTanggal_lahir().getMonth() + 1;
        String bulan = String.format("%02d", birthMonth);
        int birthYear = pasien.getTanggal_lahir().getYear() + 1900;
        String tahun =Integer.toString(birthYear).substring(2,4);

        int gender = 0;
        if (pasien.getJenis_kelamin().equals("Laki-laki")){
            gender = 1;
        }
        else{
            gender = 2;
        }
        code = year + tanggal + bulan + tahun + gender;

        Random x = new Random();
        Random y = new Random();

        code += (char) (x.nextInt(26) + 'A');
        code+= (char) (y.nextInt(26) + 'A');
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

    @Override
    public List<AsuransiModel> getAsuransiList() {
        return asuransiDb.findAll();
    }

}
