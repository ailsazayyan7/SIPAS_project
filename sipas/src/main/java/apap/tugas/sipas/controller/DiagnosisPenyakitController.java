package apap.tugas.sipas.controller;

import apap.tugas.sipas.model.DiagnosisModel;
import apap.tugas.sipas.model.PasienDiagnosisPenyakitModel;
import apap.tugas.sipas.model.PasienModel;
import apap.tugas.sipas.service.DiagnosisPenyakitService;
import apap.tugas.sipas.service.PasienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class DiagnosisPenyakitController {
    @Autowired
    private DiagnosisPenyakitService diagnosisPenyakitService;

    @Autowired
    private PasienService pasienService;

    @RequestMapping(value = "/diagnosis-penyakit-all", method = RequestMethod.GET)
    public String viewallDiagnosis(Model model){

        //Mengambil semua objek RestoranModel yang ada
        List<DiagnosisModel> listDiagnosisPenyakit = diagnosisPenyakitService.getDiagnosisList();

        // Add model restoran ke "resto" untuk di render
        model.addAttribute("diagnosisPenyakitList", listDiagnosisPenyakit);

        // Return view template
        return "view-all-diagnosis";
    }

    @RequestMapping(value = "/diagnosis-penyakit", method = RequestMethod.GET)
    public String viewId(@RequestParam(value = "id") Long id, Model model){
        // Mengambil objek DiagnosisPenyakitModel yang dituju
        DiagnosisModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(id).get();

        model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
        // Return view template
        return "view-diagnosis-penyakit";
    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.GET)
    public String addDiagnosisFormPage(Model model){
        DiagnosisModel newDiagnosisPenyakit = new DiagnosisModel();
        model.addAttribute("diagnosisPenyakit", newDiagnosisPenyakit);
        return "form-add-diagnosis-penyakit";

    }

    @RequestMapping(value = "/diagnosis-penyakit/tambah", method = RequestMethod.POST)
    public String addDiagnosisPenyakitSubmit(@ModelAttribute DiagnosisModel diagnosisPenyakit, Model model){
        diagnosisPenyakitService.addDiagnosisPenyakit(diagnosisPenyakit);
        model.addAttribute("namaDiagnosisPenyakit", diagnosisPenyakit.getNama());
        return "add-diagnosis-penyakit";
    }
    @RequestMapping(value = "diagnosis-penyakit/hapus/{id}")
    public String viewDeleteDiagnosisPenyakit(
            @PathVariable(value = "id") Long id,
            Model model
    ) {
        DiagnosisModel diagnosisPenyakit = diagnosisPenyakitService.getDiagnosisPenyakitById(id).get();
       /* List<MenuModel> listMenu = menuService.findAllMenuByIdRestoran(idRestoran);*/
/*        if (listMenu.size() == 0) {*/
            String nama = diagnosisPenyakit.getNama();
            diagnosisPenyakitService.deleteDiagnosisPenyakit(diagnosisPenyakit);
            model.addAttribute("namaDiagnosisPenyakit", nama);
            model.addAttribute("diagnosisPenyakit", diagnosisPenyakit);
            return "delete-diagnosis";
/*        } else {
            model.addAttribute("resto", restoran);
            return "delete-restoran-error";

        }*/
    }
}
