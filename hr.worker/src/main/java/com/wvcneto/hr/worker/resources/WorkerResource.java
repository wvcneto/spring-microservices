package com.wvcneto.hr.worker.resources;

import com.wvcneto.hr.worker.entities.Worker;
import com.wvcneto.hr.worker.services.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/workers")
public class WorkerResource {

    @Autowired
    WorkerService workerService;

    @GetMapping
    public ResponseEntity<List<Worker>> findAll() {
        List<Worker> workers = workerService.findAll();

        return ResponseEntity.ok().body(workers);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Worker> findById(@PathVariable Long id) {
        Worker worker = workerService.findById(id);

        return ResponseEntity.ok().body(worker);
    }

    @PostMapping
    public ResponseEntity<Worker> create(@RequestBody Worker worker) {
        worker = workerService.create(worker);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(worker.getId()).toUri();

        return ResponseEntity.created(uri).body(worker);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Worker> update(@PathVariable Long id,@RequestBody Worker newData) {
        newData = workerService.update(id, newData);

        return ResponseEntity.ok().body(newData);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        workerService.delete(id);

        return ResponseEntity.noContent().build();
    }
}
