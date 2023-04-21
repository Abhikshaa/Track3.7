package com.Track.service;

import com.Track.payload.LeadDTO;

import java.util.List;

public interface LeadService {
    LeadDTO createLead(LeadDTO leadDTO);

    List<LeadDTO> getAllLead();

    LeadDTO getById(long id);

    LeadDTO updateById(long id, LeadDTO leadDTO);

    void deleteById(long id);
}
