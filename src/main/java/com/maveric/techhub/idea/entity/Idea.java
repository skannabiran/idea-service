package com.maveric.techhub.idea.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "Idea")
public class Idea {
    @Id
    @Column
    private String id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String postedBy;
    @Column
    private String technologies;
    @Column
    private String duration;
    @Column
    private String challenges;
    @Column
    private String referenceMaterials;
}
