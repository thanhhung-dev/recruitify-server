package com.recruitify.server.repositories;

import com.recruitify.server.entities.JobDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobDetailRepository extends JpaRepository<JobDetail, Long> {

}
