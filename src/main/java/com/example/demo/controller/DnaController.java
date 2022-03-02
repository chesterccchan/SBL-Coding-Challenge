package com.example.demo.controller;

import com.example.demo.model.DNAData;
import com.example.demo.service.DNACheckingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

@Controller
public class DnaController {

    @Autowired
    private DNACheckingService dnaCheckingService;

    @GetMapping("/result")
    public String submitForm(Model model) throws IOException {
        File file = ResourceUtils.getFile("classpath:input.txt");
        String fileContent = null;
        if (file.exists()) {
            byte[] fileData = Files.readAllBytes(file.toPath());
            fileContent = new String(fileData);
        }
        model.addAttribute("dnaForm", new DNAData(fileContent, 2));
        return "submitForm";
    }

    @PostMapping("/result")
    public String displayResult(@ModelAttribute("dnaForm") DNAData dnaData, Model model) {
        String[] seq = dnaData.getSequences().split("\r\n");
        List<List<String>> result = dnaCheckingService.Check(seq, dnaData.getThreshold());
        model.addAttribute("resultList", result);
        return "result";
    }
}
