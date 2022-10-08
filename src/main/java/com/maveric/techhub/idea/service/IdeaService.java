package com.maveric.techhub.idea.service;

import com.maveric.techhub.idea.exception.EntityNotFoundException;
import com.maveric.techhub.idea.repository.IdeaRepository;
import com.maveric.techhub.idea.mapper.IdeaMapper;
import com.maveric.techhub.idea.model.IdeaDTO;
import com.maveric.techhub.idea.entity.Idea;
import com.maveric.techhub.idea.model.IdeaRequest;
import com.maveric.techhub.idea.model.ServiceResponse;
import com.maveric.techhub.idea.util.ServiceConstants;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class IdeaService {

    private final IdeaRepository ideaRepository;
    private final IdeaMapper ideaMapper;

    public ResponseEntity<ServiceResponse> createIdea(IdeaRequest ideaRequest) {
        String title = ideaRequest.getTitle();
        Optional<Idea> optionalIdea = ideaRepository.findByTitle(ideaRequest.getTitle());
        if (!optionalIdea.isPresent()) {
            Idea idea = ideaMapper.toEntity(ideaRequest);
            idea.setId(ServiceConstants._UUID());
            ideaRepository.save(idea);
            IdeaDTO ideaDTO = ideaMapper.toDTO(idea);
            return ResponseEntity.status(HttpStatus.CREATED).
                    body(ServiceResponse.builder().response(ideaDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_ALREADY_EXISTS, title));
    }

    public ResponseEntity<ServiceResponse> getIdea(String id) {
        Optional<Idea> optionalUser = ideaRepository.findById(id);
        if (optionalUser.isPresent()) {
            Idea idea = optionalUser.get();
            IdeaDTO ideaDTO = ideaMapper.toDTO(idea);
            return ResponseEntity.status(HttpStatus.OK).
                    body(ServiceResponse.builder().response(ideaDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<ServiceResponse> updateIdea(String id, IdeaRequest ideaRequest) {
        Optional<Idea> optionalUserDB = ideaRepository.findById(id);
        if (optionalUserDB.isPresent()) {
            Idea ideaDB = optionalUserDB.get();
            Idea idea = ideaMapper.toEntity(ideaRequest);
            idea.setId(ideaDB.getId());
            ideaRepository.save(idea);
            IdeaDTO ideaDTO = ideaMapper.toDTO(idea);
            return ResponseEntity.status(HttpStatus.OK).
                    body(ServiceResponse.builder().response(ideaDTO).build());
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<ServiceResponse> deleteIdea(String id) {
        Optional<Idea> optionalUser = ideaRepository.findById(id);
        if (optionalUser.isPresent()) {
            Idea idea = optionalUser.get();
            ideaRepository.delete(idea);
            ServiceResponse response = ServiceResponse.builder().
                    message(String.format(ServiceConstants.ENTITY_DELETED, id)).build();
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }
        throw new EntityNotFoundException(String.format(ServiceConstants.ENTITY_NOT_FOUND, id));
    }

    public ResponseEntity<ServiceResponse> getIdeas() {
        ServiceResponse response = ServiceResponse.builder().build();
        List<Idea> ideas = ideaRepository.findAll();
        if (!ideas.isEmpty()) {
            List<IdeaDTO> ideaDTOS = ideaMapper.toDTO(ideas);
            response.setResponse(ideaDTOS);
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
