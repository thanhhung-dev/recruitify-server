package com.recruitify.server.Services;

import com.recruitify.server.Entities.Company;
import com.recruitify.server.Repositories.CompanyRepository;
import com.recruitify.server.Util.Error.IdInvalidException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;
    public Company createCompany(Company company){
        return companyRepository.save(company);
    }
    public List<Company> handleGetCompanyAll()
    {
        return this.companyRepository.findAll();
    }
    public Optional<Company> fetchCompanyById(long id)
    {
        return this.companyRepository.findById(id);
    }
    public Company updateCompany(Company company) throws IdInvalidException {
        return this.companyRepository.findById(company.getId())
                .map(currentCompany -> {
                    currentCompany.setName(company.getName());
                    currentCompany.setOverview(company.getOverview());
                    currentCompany.setImage(company.getImage());
                    currentCompany.setPhone(company.getPhone());
                    currentCompany.setIndustry(company.getIndustry());
                    currentCompany.setCompanyType(company.getCompanyType());
                    currentCompany.setCompanySize(company.getCompanySize());
                    currentCompany.setFounderYear(company.getFounderYear());
                    return this.companyRepository.save(currentCompany);
                })
                .orElseThrow(() -> new IdInvalidException("Company Not Found with id: " + company.getId()));
    }

    public void deleteCompany(Long id){
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if(companyOptional.isPresent()){
            this.companyRepository.deleteById(id);
        }
    }
}
