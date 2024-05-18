package com.ray.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ray.literalura.models.CPerson;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CPersonRepository extends JpaRepository<CPerson, Long> {

   @Query("select p from CPerson p where :year between p.birth_year and p.death_year")
   List<CPerson> aliveAuthorsByYear(int year);

//   @Query(value = "select * from authors where name ilike %:authorName%", nativeQuery = true)
   @Query("select p from CPerson p where name ilike %:authorName%")
   Optional<CPerson> findByNameContainsIgnoreCase(String authorName);
}
