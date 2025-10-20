package com.recruitify.server.Services.Interface;

import com.recruitify.server.Entities.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> GetCompanyAll();
    Company createCompany(Company request);
    Company updateCompany(Long id , Company request);
    void deleteCompany(Long id);
    Company getByCompanyId(long id);
}
