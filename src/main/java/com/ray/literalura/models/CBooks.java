package com.ray.literalura.models;

import jakarta.persistence.*;

@Entity
@Table(name = "books")
public class CBooks {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   Long id;

   String title;
   String authors;
   String languages;
   Double download_count;

   public CBooks() {}


   public CBooks(Books book) {
      this.title = book.title();
      this.authors = book.authors().get(0).name();
      this.languages = book.languages().get(0);
      this.download_count = book.download_count();
   }


   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getTitle() {
      return title;
   }

   public void setTitle(String title) {
      this.title = title;
   }

   public String getAuthors() {
      return authors;
   }

   public void setAuthors(String authors) {
      this.authors = authors;
   }

   public String getLanguages() {
      return languages;
   }

   public void setLanguages(String languages) {
      this.languages = languages;
   }

   public Double getDownload_count() {
      return download_count;
   }

   public void setDownload_count(Double download_count) {
      this.download_count = download_count;
   }

   @Override
   public String toString() {
      return "\nBook\n=================================" +
      "\nBook title:\t" + title +
      "\nAuthor:\t" + authors +
      "\nLanguages:\t" +  languages +
      "\nDownload count:\t" +  download_count +
      "\n=================================\n";
   }
}
