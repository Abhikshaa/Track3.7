package com.Track.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="leader")
public class Lead {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String email;
    private long mobile;

}
