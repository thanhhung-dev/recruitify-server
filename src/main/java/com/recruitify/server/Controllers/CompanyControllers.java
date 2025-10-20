package com.recruitify.server.Controllers;

import com.recruitify.server.Services.Implementations.CompanyServiceImpl;
import com.recruitify.server.Util.Annotation.ApiMessage;
import com.recruitify.server.Util.Error.IdInvalidException;
import com.recruitify.server.Entities.Company;
import jakarta.validation.Valid;
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
    private final CompanyServiceImpl companyService;

    @PostMapping("/companies")
    @ApiMessage("Create a company")
    public ResponseEntity<Company> createCompany(@Valid @RequestBody Company company) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.companyService.createCompany(company));
    }
    @PutMapping("companies/{id}")
    @ApiMessage("Update a Company")
    public ResponseEntity<Company> updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody Company company) {
        Company updatedCompany = companyService.updateCompany(id, company);
        return ResponseEntity.ok(updatedCompany);
    }
    @GetMapping("/companies")
    @ApiMessage("Fetch all companies data")
    public ResponseEntity<List<Company>> getAllCompany() {
        return ResponseEntity.status(HttpStatus.OK)
                .body(this.companyService.GetCompanyAll());
    }
    @GetMapping("/companies/{id}")
    @ApiMessage("Fetch By id company data")
    public ResponseEntity<Company> fetchCompanyById(@PathVariable("id") long id) {
        Company company = companyService.getByCompanyId(id);
        return ResponseEntity.ok(company);
    }
    @DeleteMapping("companies/{id}")
    @ApiMessage("Delete a Company")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }
}
