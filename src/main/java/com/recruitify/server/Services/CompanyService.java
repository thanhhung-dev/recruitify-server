package com.recruitify.server.Services;

import com.recruitify.server.Entities.Company;
import com.recruitify.server.Repositories.CompanyRepository;
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
    public Company findCompanyById(long id)
    {
        Optional<Company> company = companyRepository.findById(id);
        return company.orElse(null);
    }
    public Company updateCompany(Company company){
        Optional<Company> companyOptional = this.companyRepository.findById(company.getId());
        if(companyOptional.isPresent()){
            Company currentCompany = companyOptional.get();
            currentCompany.setName(company.getName());
            currentCompany.setOverview(company.getOverview());
            currentCompany.setImage(company.getImage());
            currentCompany.setPhone(company.getPhone());
            currentCompany.setIndustry(company.getIndustry());
            currentCompany.setCompanyType(company.getCompanyType());
            currentCompany.setCompanySize(company.getCompanySize());
            currentCompany.setFounderYear(company.getFounderYear());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    public void deleteCompany(Long id){
        Optional<Company> companyOptional = this.companyRepository.findById(id);
        if(companyOptional.isPresent()){
            Company company = companyOptional.get();
            this.companyRepository.deleteById(id);
        }
    }
}
