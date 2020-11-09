package com.wvcneto.hr.worker.repositories;

import com.wvcneto.hr.worker.entities.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkerRepository extends JpaRepository<Worker, Long> {

}
