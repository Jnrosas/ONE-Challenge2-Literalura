package com.ray.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ray.literalura.models.CPerson;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CPersonRepository extends JpaRepository<CPerson, Long> {

   @Query("select p from CPerson p where :year between p.birth_year and p.death_year")
   List<CPerson> aliveAuthorsByYear(int year);
}
