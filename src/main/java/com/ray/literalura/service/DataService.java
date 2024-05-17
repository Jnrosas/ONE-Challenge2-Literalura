package com.ray.literalura.service;

import com.ray.literalura.models.*;
import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.repository.CPersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DataService {

   private final String URL_BASE = "https://gutendex.com/books/";
   private ConsumingAPI consumingAPI = new ConsumingAPI();
   private ConvertingData convertingData = new ConvertingData();
   private Scanner scanner = new Scanner(System.in);
   private Data data;


   public void searchBookByTitle(CBooksRepository cBooksRepository, CPersonRepository cPersonRepository) {
      System.out.println("Enter the book title: ");
      String bookTitle = scanner.nextLine();

      System.out.println("searching...\n");

      var bookInDB = cBooksRepository.findByTitleContainsIgnoreCase(bookTitle);
      if (bookInDB.isPresent()) {
         System.out.println("Book already in local Database:\n" +
               bookInDB.get());
      } else {
         var json = consumingAPI.getData(URL_BASE + "?search=" + bookTitle.replace(" ", "%20"));
         data = convertingData.getData(json, Data.class);

         Optional<Books> bookSought = data.results().stream()
            .filter(b -> b.title().toLowerCase().contains(bookTitle.toLowerCase())) //using search not according to api
            .findFirst();
         if (bookSought.isPresent()) {
            CBooks cbooks = new CBooks(bookSought.get());
            CPerson cperson = new CPerson(bookSought.get().authors().get(0), bookSought.get().title());
            cBooksRepository.save(cbooks);
            cPersonRepository.save(cperson);
            System.out.println("Book found\n" + cbooks);
         } else System.out.println("Book not found");
      }
   }


   public void displayRegisteredBooks(CBooksRepository cBooksRepository) {
      var allBooks = cBooksRepository.findAll();
      allBooks.forEach(System.out::println);
   }


   public void displayRegisteredAuthors(CPersonRepository cPersonRepository) {
      var allAuthors = cPersonRepository.findAll();
      allAuthors.forEach(System.out::println);
   }
}
