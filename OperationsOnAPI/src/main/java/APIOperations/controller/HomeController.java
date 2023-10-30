package APIOperations.controller;

import APIOperations.model.Files;
import APIOperations.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.List;


@Controller
public class HomeController {

    @Autowired
    private FileService fileService;

    @GetMapping(value="/")
    public String get(Model model){
        List<Files> files = fileService.getFiles();
        model.addAttribute("files", files);
        return "index";
    }



    @PostMapping(value="/upload")
    public String uploadFiles(@RequestParam("files") MultipartFile[] files){
        for (MultipartFile file:files) {
            fileService.saveFile(file);
        }
        return "redirect:/";
    }

    @GetMapping(value = "/download/{id}")
    public ResponseEntity<ByteArrayResource> downloadFile(@PathVariable int id){
        Files file = fileService.getFile(id);
        return ResponseEntity.ok().
                contentType(MediaType.parseMediaType(file.getFileType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment:filename=\"" +
                        file.getFileName()+"\"")
                .body(new ByteArrayResource(file.getData()));
    }

    @GetMapping(value = "/delete/{id}")
    public String deleteFile(@PathVariable int id){
        fileService.deleteFile(id);
        return "redirect:/";
    }
}
