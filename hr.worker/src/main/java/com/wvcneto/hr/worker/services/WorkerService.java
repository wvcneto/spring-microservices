package com.wvcneto.hr.worker.services;

import com.wvcneto.hr.worker.entities.Worker;
import com.wvcneto.hr.worker.repositories.WorkerRepository;
import com.wvcneto.hr.worker.services.exceptions.DatabaseException;
import com.wvcneto.hr.worker.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public List<Worker> findAll() {
        return workerRepository.findAll();
    }

    public Worker findById(Long id) {
        Optional<Worker> worker = workerRepository.findById(id);

        return worker.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Worker create(Worker worker) {
        return workerRepository.save(worker);
    }

    public void delete(Long id) {
        try {
            workerRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new ResourceNotFoundException(id);
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException(e.getMessage());
        }
    }

    public Worker update(Long id, Worker newData) {
        try {
            Worker entity = workerRepository.getOne(id);
            updateData(entity, newData);
            return workerRepository.save(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Worker entity, Worker newData) {
        entity.setName(newData.getName());
        entity.setDailyIncome(newData.getDailyIncome());
    }
}
