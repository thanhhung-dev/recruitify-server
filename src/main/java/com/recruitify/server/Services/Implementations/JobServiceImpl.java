package com.recruitify.server.Services.Implementations;

import com.recruitify.server.Dtos.Response.Job.JobResponse;
import com.recruitify.server.Entities.*;
import com.recruitify.server.Mapper.JobMapper;
import com.recruitify.server.Repositories.*;
import com.recruitify.server.Services.Interface.IJobService;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JobServiceImpl implements IJobService {
    private static final Logger logger = LoggerFactory.getLogger(JobServiceImpl.class);
    private final JobRepository jobRepository;
    private final CompanyRepository companyRepository;
    private final SkillsRepository skillsRepository;
    private final WardRepository wardRepository;
    private final JobMapper jobMapper;
    private final CategoryRepository categoryRepository;
    private final ExperienceLevelRepository experienceLevelRepository;
    private final EmploymentTypeRepository employmentTypeRepository;
    private final WorkApproachRepository workApproachRepository;

    public JobServiceImpl(JobRepository jobRepository, CompanyRepository companyRepository, SkillsRepository skillsRepository, WardRepository wardRepository, JobMapper jobMapper, CategoryRepository categoryRepository, ExperienceLevelRepository experienceLevelRepository, EmploymentTypeRepository employmentTypeRepository, WorkApproachRepository workApproachRepository) {
        this.jobRepository = jobRepository;
        this.companyRepository = companyRepository;
        this.skillsRepository = skillsRepository;
        this.wardRepository = wardRepository;
        this.jobMapper = jobMapper;
        this.categoryRepository = categoryRepository;
        this.experienceLevelRepository = experienceLevelRepository;
        this.employmentTypeRepository = employmentTypeRepository;
        this.workApproachRepository = workApproachRepository;
    }


    @Override
    @Transactional
    public List<JobResponse> getAllJob() {
        List<Job> jobs = jobRepository.findAll();
        return jobMapper.toResponseJobList(jobs);
    }


    @Override
    @Transactional
    public JobResponse createJob(Job job) {
        logger.info("Creating Job with name : {}", job.getTitle());

        //Check Experience
        if (job.getExperienceLevel() != null && job.getExperienceLevel().getId() != null) {
            Optional<ExperienceLevel> experienceLevel = this.experienceLevelRepository.findById(job.getExperienceLevel().getId());
            experienceLevel.ifPresent(job::setExperienceLevel);
        }
        //Check EmployeeType
        if (job.getEmploymentType() != null && job.getEmploymentType().getId() != null) {
            Optional<EmploymentType> employmentType = employmentTypeRepository.findById(job.getEmploymentType().getId());
            employmentType.ifPresent(job::setEmploymentType);
        }
        //Check Company
        if (job.getSkills() != null) {
            List<Long> reqSkills = job.getSkills()
                    .stream().map(Skills::getId)
                    .collect(Collectors.toList());
            List<Skills> dbSkills = this.skillsRepository.findByIdIn(reqSkills);
            job.setSkills(dbSkills);
        }
        //check ward
        if (job.getWard() != null && job.getWard().getCode() != null) {
            Optional<Ward> wardOpt = this.wardRepository.findByCode(job.getWard().getCode());
            wardOpt.ifPresent(job::setWard);
        }
        //check work_approach
        if (job.getWorkApproach() != null && job.getWorkApproach().getId() != null) {
            Optional<WorkApproach> wordOptional = this.workApproachRepository.findById(job.getWorkApproach().getId());
            wordOptional.ifPresent(job::setWorkApproach);
        }

        //Check category
        if (job.getCategory() != null && job.getCategory().getId() != null) {
            Optional<Category> cOptional = categoryRepository.findById(job.getCategory().getId());
            cOptional.ifPresent(job::setCategory);
        }

        // check company
        if (job.getCompany() != null) {
            Optional<Company> cOptional = this.companyRepository.findById(job.getCompany().getId());
            cOptional.ifPresent(job::setCompany);
        }
        // Save to DB
        Job savedJob = jobRepository.save(job);

        // Convert to Response using JobMapper
        JobResponse response = jobMapper.toResponseJob(savedJob);

        logger.info("Job created successfully with ID: {}", savedJob.getId());
        return response;
    }
}
