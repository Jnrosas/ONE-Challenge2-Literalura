package com.ray.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ray.literalura.models.CPerson;

public interface CPersonRepository extends JpaRepository<CPerson, Long> {

}
