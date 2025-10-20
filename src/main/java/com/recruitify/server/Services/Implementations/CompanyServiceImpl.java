package com.recruitify.server.Services.Implementations;

import com.recruitify.server.Entities.Company;
import com.recruitify.server.Repositories.CompanyRepository;
import com.recruitify.server.Services.Interface.ICompanyService;
import com.recruitify.server.Util.Error.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompanyServiceImpl implements ICompanyService {
    private final CompanyRepository companyRepository;
    private static final Logger logger = LoggerFactory.getLogger(CompanyServiceImpl.class);

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> GetCompanyAll() {
        logger.debug("Retrieving all Company");
        List<Company> companyList = companyRepository.findAll();
        return companyList;
    }

    @Override
    public Company createCompany(Company request) {
        logger.info("Creating Company with title: {}", request.getCompanyType());
        // Validate Company exists
        Company company = companyRepository.findById(request.getId())
                .orElseThrow(() -> new EntityNotFoundException("Project", request.getId()));
        Company saveComp = companyRepository.save(company);
        logger.info("Company created successfully with ID: {}", request.getId());
        return saveComp;
    }

    @Override
    public Company updateCompany(Long id, Company request) {
        logger.info("Updating Company with ID: {}", id);
        Company existingComp = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company", id));
        existingComp.setName(request.getName());
        existingComp.setOverview(request.getOverview());
        existingComp.setImage(request.getImage());
        existingComp.setPhone(request.getPhone());
        existingComp.setIndustry(request.getIndustry());
        existingComp.setCompanyType(request.getCompanyType());
        existingComp.setCompanySize(request.getCompanySize());
        existingComp.setFounderYear(request.getFounderYear());
        Company company = companyRepository.save(existingComp);
        logger.info("Company update successfully with ID: {}", request.getId());
        return company;
    }

    @Override
    public void deleteCompany(Long id) {
        logger.info("Deleting Company with ID: {}", id);
        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company", id));
        companyRepository.delete(company);
        logger.info("Company deleted successfully with ID: {}", id);
    }

    @Override
    public Company getByCompanyId(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company", id));
    }
}
