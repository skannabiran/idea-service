package com.maveric.techhub.idea.model;

import lombok.Data;

@Data
public class IdeaDTO {

    private String id;
    private String title;
    private String description;
    private String postedBy;
    private String recommendedTechnologies;
    private String duration;
    private String challenges;
    private String referenceData;
}
