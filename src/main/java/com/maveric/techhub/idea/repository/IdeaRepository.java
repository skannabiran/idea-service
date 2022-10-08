package com.maveric.techhub.idea.repository;

import com.maveric.techhub.idea.entity.Idea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IdeaRepository extends JpaRepository<Idea, String> {

    Optional<Idea> findById(String Id);

    Optional<Idea> findByTitle(String title);

}