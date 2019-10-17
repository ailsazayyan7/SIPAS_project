package apap.tugas.sipas.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PasienController {

    @RequestMapping("/")
    public String beranda() {
        return "a";
    }
}
