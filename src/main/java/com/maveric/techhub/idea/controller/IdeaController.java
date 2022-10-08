package com.maveric.techhub.idea.controller;
import com.maveric.techhub.idea.model.IdeaRequest;
import com.maveric.techhub.idea.model.ServiceResponse;
import com.maveric.techhub.idea.service.IdeaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * IdeaController is the class to manage for employee ideas
 * @author      Kannabiran Shanmugam
 * @version     %I%, %G%
 * @since       1.0
 */
@RestController
@RequestMapping("/ideas")
@RequiredArgsConstructor
class IdeaController {

    private final IdeaService ideaService;

    @PostMapping
    ResponseEntity<ServiceResponse> createIdea(@Valid @RequestBody IdeaRequest ideaRequest) {
        return ideaService.createIdea(ideaRequest);
    }

    @GetMapping("/{id}")
    ResponseEntity<ServiceResponse> getIdea(@PathVariable(name = "id") String id) {
        return ideaService.getIdea(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<ServiceResponse> updateIdea(@PathVariable(name = "id") String id,
                                            @RequestBody IdeaRequest ideaRequest) {
        return ideaService.updateIdea(id, ideaRequest);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ServiceResponse> deleteIdea(@PathVariable(name = "id") String id) {
        return ideaService.deleteIdea(id);
    }

    @GetMapping
    ResponseEntity<ServiceResponse> getIdeas() {
        return ideaService.getIdeas();
    }

}