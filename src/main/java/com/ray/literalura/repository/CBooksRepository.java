package com.ray.literalura.repository;

import com.ray.literalura.models.CBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CBooksRepository extends JpaRepository<CBooks, Long> {

   Optional<CBooks> findByTitleContainsIgnoreCase(String bookTitle);

   List<CBooks> findByLanguagesIgnoreCase(String option);

   @Query("select b from CBooks b order by download_count desc limit 1")
   CBooks findByDownload_countMax();

   @Query("select b from CBooks b order by download_count asc limit 1")
   CBooks findByDownload_countMin();
}
