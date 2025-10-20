package com.recruitify.server.Services.Implementations;

import com.recruitify.server.Dtos.Request.CreateJobRequest;
import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.*;
import com.recruitify.server.Mapper.JobMapper;
import com.recruitify.server.Repositories.*;
import com.recruitify.server.Services.Interface.IJobService;
import com.recruitify.server.Util.Error.EntityNotFoundException;
import com.recruitify.server.Util.Error.IdInvalidException;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements IJobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final CategoryRepository categoryRepository;
    private final SkillsRepository skillsRepository;
    private final WardRepository wardRepository;
    private final JobMapper jobMapper;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, EmploymentTypeRepository employmentTypeRepository, ExperienceLevelRepository experienceLevelRepository, CategoryRepository categoryRepository, SkillsRepository skillsRepository, WardRepository wardRepository, JobMapper jobMapper) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.employmentTypeRepository = employmentTypeRepository;
        this.experienceLevelRepository = experienceLevelRepository;
        this.categoryRepository = categoryRepository;
        this.skillsRepository = skillsRepository;
        this.wardRepository = wardRepository;
        this.jobMapper = jobMapper;
    }


    @Override
    @Transactional
    public List<JobResponse> getAllJob() {
        List<Job> jobs = jobRepository.findAll();
        return jobMapper.toResponseJobList(jobs);
    }

    @Override
    public JobResponse createJob(CreateJobRequest request) {
        logger.info("Creating Job with name : {}", request.getTitle());
        Job job = new Job();
        job.setTitle(request.getTitle());
        job.setDescription(request.getDescription());
        job.setResponsibilities(request.getResponsibilities());
        job.setRequirement(request.getRequirement());
        job.setSalary(request.getSalary());
        job.setBenefit(request.getBenefit());

        Optional.ofNullable(request.getCategoryId())
                .map(id -> companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company ", id)))
                .isPresent(job::setCompany);


        Job savedJob = jobRepository.save(job);
        logger.info("Job created successfully with ID: {}", savedJob.getId());
        return jobMapper.toResponseJob(savedJob);
    }
}
