package com.ray.literalura.repository;

import com.ray.literalura.models.CBooks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CBooksRepository extends JpaRepository<CBooks, Long> {

   Optional<CBooks> findByTitleContainsIgnoreCase(String bookTitle);

   List<CBooks> findByLanguagesIgnoreCase(String option);
}
