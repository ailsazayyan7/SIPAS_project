package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class PasienController {
    @Autowired
    private PasienService pasienService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String viewall(Model model){

        //Mengambil semua objek RestoranModel yang ada
        List<PasienModel> listPasien = pasienService.getPasienList();

        // Add model restoran ke "resto" untuk di render
        model.addAttribute("pasienList", listPasien);

        // Return view template
        return "view-all";
    }

    //URL mapping yang digunakan untuk mengakses halaman add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.GET)
    public String addPasienFormPage(Model model){
        PasienModel newPasien = new PasienModel();
        model.addAttribute("pasien", newPasien);
        return "form-add-pasien";

    }

    //URL mapping yang digunakan untuk submit form yang telah Anda masukkan pada halaman add pasien
    @RequestMapping(value = "/pasien/tambah", method = RequestMethod.POST)
    public String addRestoranSubmit(@ModelAttribute PasienModel pasien, Model model){
        pasienService.addPasien(pasien);
        model.addAttribute("namaPasien", pasien.getNama());
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

}
