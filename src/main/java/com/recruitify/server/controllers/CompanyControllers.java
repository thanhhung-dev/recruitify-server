package com.recruitify.server.controllers;

import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.entities.Company;
import com.recruitify.server.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/companies")
@CrossOrigin(origins = "*")
@RestController
@RequiredArgsConstructor
public class CompanyControllers {
    private final CompanyService companyService;

    @PostMapping("")
    @ApiMessage("Create a company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.companyService.createCompany(company));
    }

    @GetMapping("")
    @ApiMessage("Fetch all company data")
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.companyService.handleGetCompanyAll());
    }
}
