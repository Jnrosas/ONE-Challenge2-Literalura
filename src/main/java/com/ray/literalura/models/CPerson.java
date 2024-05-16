package com.ray.literalura.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "authors")
public class CPerson {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   private String name;
   private String birth_year;
   private String death_year;

   public CPerson(Person person) {
      this.name = person.name();
      this.birth_year = person.birth_year();
      this.death_year = person.death_year();
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getBirth_year() {
      return birth_year;
   }

   public void setBirth_year(String birth_year) {
      this.birth_year = birth_year;
   }

   public String getDeath_year() {
      return death_year;
   }

   public void setDeath_year(String death_year) {
      this.death_year = death_year;
   }

   @Override
   public String toString() {
      return "name= " + name +
            ", birth_year= " + birth_year +
            ", death_year= " + death_year;
   }
}
