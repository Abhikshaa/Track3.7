package com.Track.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class LeadDTO {
    private long id;
    @NotEmpty
    @Size(min=2,max = 17,message = "character should be 17")
    private String name;
    @NotEmpty
    @Size(min=5,max=16,message = "Valid email")
    private String email;

    private long mobile;
}
