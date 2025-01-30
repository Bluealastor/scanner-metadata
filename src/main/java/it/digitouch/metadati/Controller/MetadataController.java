package it.digitouch.metadati.Controller;

import it.digitouch.metadati.service.MetadataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CA01CN01/LDIG002")
public class MetadataController {

    @Autowired
    private MetadataService metadataService;

    @PostMapping("/PK0000004")
    public String processFiles(@RequestParam String directory){
        metadataService.processFiles(directory);
        return "Elaborazione completata!";
    }
}

