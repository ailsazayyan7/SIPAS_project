package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.*;
import apap.tugas.sipas.service.AsuransiService;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.PasienDiagnosisPenyakitService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class PasienController {
    @Qualifier("pasienServiceImpl")
    @Autowired
    private PasienService pasienService;

    @Qualifier("asuransiServiceImpl")
    @Autowired
    private AsuransiService asuransiService;

    @Qualifier("diagnosisPenyakitServiceImpl")
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Qualifier("pasienDiagnosisPenyakitServiceImpl")
    @Autowired
    private PasienDiagnosisPenyakitService pasienDiagnosisPenyakitService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewall(Model model){

        //Mengambil semua objek PasienModel yang ada
        List<PasienModel> listPasien = pasienService.getPasienList();

        // Add model restoran ke "pasien" untuk di render
        model.addAttribute("pasienList", listPasien);

        // Return view template
        return "view-all";
    }

    //URL mapping yang digunakan untuk mengakses halaman add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model){
        PasienModel newPasien = new PasienModel();
        newPasien.setListAsuransi(new ArrayList<AsuransiModel>());
        newPasien.getListAsuransi().add(new AsuransiModel());
        List<AsuransiModel> listAsuransi = asuransiService.getAsuransiList();
        newPasien.setEmergencyContact(new EmergencyContactModel());
        model.addAttribute("pasien", newPasien);
        model.addAttribute("listAsuransi", listAsuransi);
        return "form-add-pasien";

    }

    //URL mapping yang digunakan untuk submit form yang telah Anda masukkan pada halaman add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute PasienModel pasien, Model model){
        String kode = pasienService.generateCode(pasien);
        pasien.setKode(kode);
        pasienService.addPasien(pasien);
        PasienModel pasienFromDb = pasienService.getPasienByNIK(pasien.getNik());
        for (AsuransiModel asuransi:pasienFromDb.getListAsuransi()){
            asuransi.getListPasienAsuransi().add(pasienFromDb);
            asuransiService.addAsuransi(asuransi);
        }
        model.addAttribute("namaPasien", pasien.getNama());
        model.addAttribute("kodePasien", pasien.getKode());
        return "add-pasien";
    }

    // URL mapping nik-pasien
    @RequestMapping(value = "/pasien", method = RequestMethod.GET)
    public String viewId(@RequestParam(value = "nik") String nik, Model model){
        // Mengambil objek PasienModel yang dituju
        PasienModel pasien = pasienService.getPasienByNIK(nik);

        // Add model pasien ke "pasien" untuk dirender
        model.addAttribute("pasien", pasien);
        // Return view template
        return "view-pasien";
    }

    //API yang digunakan untuk menuju halaman form change restoran
    @RequestMapping(value="pasien/ubah/{nik}", method = RequestMethod.GET)
    public String changePasienFormPage(@PathVariable String nik, Model model){
        //Mengambil existing data pasien
        PasienModel existingPasien = pasienService.getPasienByNIK(nik);
        model.addAttribute("pasien", existingPasien);
        return "form-change-pasien";
    }

    //API yang digunakan untuk submit form change pasien
    @RequestMapping(value = "pasien/ubah/{nik}", method = RequestMethod.POST)
    public String changePasienFormSubmit(@PathVariable String nik, @ModelAttribute PasienModel pasien, Model model){
        PasienModel newPasienData = pasienService.changePasien(pasien);
        model.addAttribute("pasien", newPasienData);

        return "change-pasien";
    }

    @RequestMapping(value = "pasien/{nik}/tambah-diagnosis", method = RequestMethod.GET)
    public String addPasienDiagnosisForm(@PathVariable String nik, Model model) {
        PasienModel pasien = pasienService.getPasienByNIK(nik);
        model.addAttribute("pasien", pasien);

        PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit = new PasienDiagnosisPenyakitModel();
        List<DiagnosisModel> diagnosisList = diagnosisPenyakitService.getDiagnosisList();
        List<PasienDiagnosisPenyakitModel> pasienDiagosisPenyakitList = pasien.getPasienDiagnosisPenyakit();
        model.addAttribute("pasienDiagnosisPenyakit", pasienDiagnosisPenyakit);
        model.addAttribute("listPasienDiagnosisPenyakit", pasienDiagosisPenyakitList);
        model.addAttribute("listDiagnosisPenyakit", diagnosisList);
        return "form-tambah-diagnosis-pasien";
    }

    @RequestMapping(value = "pasien/{nik}/tambah-diagnosis", method = RequestMethod.POST)
    public String addPasienDiagnosisSubmit(@PathVariable String nik, @ModelAttribute PasienDiagnosisPenyakitModel pasienDiagnosisPenyakit,
                                           Model model) {

        PasienModel pasien = pasienService.getPasienByNIK(nik);
        pasienDiagnosisPenyakit.setPasien(pasien);
        model.addAttribute("pasien", pasien);

/*        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");*/
        Date tanggal = new Date();
        pasienDiagnosisPenyakit.setTanggal_diagnosis(tanggal);
        /*String nowDate = formatter.format(tanggal);*/
        model.addAttribute("tanggal_diagnosis" , tanggal);


        pasienDiagnosisPenyakitService.addPasienDiagnosisPenyakit(pasienDiagnosisPenyakit);

        String namaPenyakit = (diagnosisPenyakitService.getDiagnosisPenyakitById(pasienDiagnosisPenyakit.getDiagnosis().getId()).get()).getNama();
        model.addAttribute("namaPenyakit", namaPenyakit);

        return "tambah-diagnosis-penyakit-pasien";
    }

}
