package com.ray.literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Person(
      String name,
      String birth_year,
      String death_year,
      List<String> books
) {
}
