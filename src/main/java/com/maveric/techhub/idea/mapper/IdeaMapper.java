package com.maveric.techhub.idea.mapper;

import com.maveric.techhub.idea.entity.Idea;
import com.maveric.techhub.idea.model.IdeaRequest;
import com.maveric.techhub.idea.model.IdeaDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface IdeaMapper {

    Idea toEntity(IdeaRequest request);
    IdeaDTO toDTO(Idea idea);

    List<IdeaDTO> toDTO(List<Idea> ideas);

}
