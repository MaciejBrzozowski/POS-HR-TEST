package pl.brzozowski.maciej.recrutationpointofsale.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.brzozowski.maciej.recrutationpointofsale.commons.Barcode;

@Controller
@RequestMapping(value = "/")
public class PageController {

    @GetMapping
    public String mainPageTemplate(Model model) {
        model.addAttribute("barcodeModel", new Barcode());
        return "main.html";
    }


}
