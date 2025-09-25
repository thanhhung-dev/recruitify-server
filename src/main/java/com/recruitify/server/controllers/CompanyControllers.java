package com.recruitify.server.controllers;

import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Util.Error.IdInvalidException;
import com.recruitify.server.entities.Company;
import com.recruitify.server.services.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/")
@RestController
@RequiredArgsConstructor
public class CompanyControllers {
    private final CompanyService companyService;

    @PostMapping("/companies")
    @ApiMessage("Create a company")
    public ResponseEntity<Company> createCompany(@RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.companyService.createCompany(company));
    }
    @PutMapping("companies/{id}")
    @ApiMessage("Update a Company")
    public ResponseEntity<Company> updateCompany(@RequestBody Company company) throws IdInvalidException {
        Company updateCompany = this.companyService.updateCompany(company);
        if(updateCompany == null)
        {
            throw new IdInvalidException("Company with id: " + company.getId() + "does not exit");
        }
        return ResponseEntity.status(HttpStatus.OK).body(updateCompany);
    }
    @GetMapping("/companies")
    @ApiMessage("Fetch all companies data")
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.companyService.handleGetCompanyAll());
    }
    @GetMapping("/companies/{id}")
    @ApiMessage("Fetch By id company data")
    public ResponseEntity<Company> fetchCompanyById(@PathVariable("id") long id) throws IdInvalidException {
        Optional<Company> companyOpt = this.companyService.fetchCompanyById(id);
        if (companyOpt.isEmpty())
        {
            throw new IdInvalidException("Company with id: " + id + " does not exit");
        }
        return ResponseEntity.ok(companyOpt.get());
    }
    @DeleteMapping("companies/{id}")
    @ApiMessage("Delete a Company")
    public ResponseEntity<Void> deleteCompany(@PathVariable("id") long id) throws IdInvalidException {
        Optional<Company> deleteCompanyOpt = this.companyService.fetchCompanyById(id);
        if (deleteCompanyOpt.isEmpty())
        {
            throw new IdInvalidException("Company with id: " + id + " does not exit");
        }
        this.companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
