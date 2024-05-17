package com.ray.literalura.models;

import jakarta.persistence.*;

import java.util.ArrayList;
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
   private List<String> books = new ArrayList<>();


   public CPerson() {}

   public CPerson(Person person, String book) {
      this.name = person.name();
      this.birth_year = person.birth_year();
      this.death_year = person.death_year();
      this.books.add(book);
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

   public List<String> getBooks() {
      return books;
   }

   public void setBooks(List<String> books) {
      this.books = books;
   }

   @Override
   public String toString() {
      return "\nAuthors\n=================================" +
            "\nAuthor's name:\t" + name +
            "\nBirth year:\t" + birth_year +
            "\nDeath year:\t" +  death_year +
            "\nBooks:\t" + books +
            "\n=================================\n";
   }
}
