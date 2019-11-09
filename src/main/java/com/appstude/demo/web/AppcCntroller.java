package com.appstude.demo.web;


import com.appstude.demo.poi.writers.ExcelWriter;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/reportGenerate")
public class AppcCntroller {

    @Autowired
    ExcelWriter excelWriter;



    @GetMapping(value = "/download/customers.xlsx")
    public ResponseEntity<InputStreamResource> excelCustomersReport() throws IOException {
        //List<Customer> customers = (List<Customer>) customerRepository.findAll();

        ByteArrayInputStream in = null;
        try {
            in = excelWriter.data();
        } catch (InvalidFormatException e) {
            e.printStackTrace();
        }
        // return IOUtils.toByteArray(in);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment; filename=customers.xlsx");

        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new InputStreamResource(in));
    }
}
