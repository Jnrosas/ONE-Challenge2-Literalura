package com.ray.literalura.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Books(
      String title,
      List<Person> authors,
      List<String> languages,
      Double download_count
) {
}
