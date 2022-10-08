package com.maveric.techhub.idea.model;

import com.maveric.techhub.idea.util.ServiceConstants;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class IdeaRequest {

    @NotBlank(message = ServiceConstants.TITLE_IS_MANDATORY)
    private String title;
    @NotBlank(message = ServiceConstants.DESCRIPTION_IS_MANDATORY)
    private String description;
    @NotBlank(message = ServiceConstants.POSTED_BY_MANDATORY)
    private String postedBy;
    @NotBlank(message = ServiceConstants.TECHNOLOGIES_IS_MANDATORY)
    private String technologies;
    private String duration;
    private String challenges;
    private String referenceMaterials;

}
