package com.Track.service.impl;

import com.Track.entities.Lead;
import com.Track.exception.ResourceNotFoundException;
import com.Track.payload.LeadDTO;
import com.Track.repositories.LeadRepository;
import com.Track.service.LeadService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeadServiceImpl implements LeadService {

    private LeadRepository leadRepository;

    public LeadServiceImpl(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public LeadDTO createLead(LeadDTO leadDTO) {
        Lead lead= new Lead();
        lead.setName(leadDTO.getName());
        lead.setEmail(leadDTO.getEmail());
        lead.setMobile(leadDTO.getMobile());
        Lead save = leadRepository.save(lead);
        LeadDTO dto = new LeadDTO();
        dto.setId(save.getId());
        dto.setName(save.getName());
        dto.setEmail(save.getEmail());
        dto.setMobile(save.getMobile());
        return dto;
    }

    @Override
    public List<LeadDTO> getAllLead() {
        List<Lead> list = leadRepository.findAll();
        return list.stream().map(l1->mapToDto(l1)).collect(Collectors.toList());
    }

    @Override
    public LeadDTO getById(long id) {
        Lead lead1 = leadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LEAD NOT FOUND", "ID", id));
        Lead lead = leadRepository.getById(id);
        return mapToDto(lead);
    }

    @Override
    public LeadDTO updateById(long id, LeadDTO leadDTO) {
        Lead lead = leadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LEAD NOT FOUND", "ID", id));
        lead.setName(leadDTO.getName());
        lead.setEmail(leadDTO.getEmail());
        lead.setMobile(leadDTO.getMobile());
        Lead update = leadRepository.save(lead);
        return mapToDto(update);
    }

    @Override
    public void deleteById(long id) {
         leadRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("LEAD NOT FOUND", "ID", id));
        leadRepository.deleteById(id);
    }

    LeadDTO mapToDto(Lead lead){
        LeadDTO dto = new LeadDTO();
        dto.setName(lead.getName());
        dto.setEmail(lead.getEmail());
        dto.setMobile(lead.getMobile());
        return dto;
    }
}
