package com.Track.controller;

import com.Track.payload.LeadDTO;
import com.Track.service.LeadService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private LeadService leadService;

    public LeadController(LeadService leadService) {
        this.leadService = leadService;
    }
@PostMapping
@PreAuthorize("hasRole('ADMIN')")
    public   ResponseEntity<LeadDTO> Savedata(@Valid@RequestBody LeadDTO  leadDTO){
        LeadDTO lead = leadService.createLead(leadDTO);

        return new ResponseEntity<>(lead, HttpStatus.CREATED);

    }
    @GetMapping
    @PreAuthorize("hasRole('USER')")
    public List<LeadDTO> getAllLead(){
        return leadService.getAllLead();

    }
    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<LeadDTO> getById(@PathVariable("id") long id){

        LeadDTO dto = leadService.getById(id);

        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<LeadDTO> updateById(@Valid @PathVariable("id") long id, @RequestBody  LeadDTO leadDTO){

        LeadDTO dto = leadService.updateById(id,leadDTO);

        return new ResponseEntity<>(dto,HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") long id){

        leadService.deleteById(id);

        return new ResponseEntity<String>("Deleted!!!",HttpStatus.OK);

    }



}
