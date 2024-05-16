package com.ray.literalura.service;

import com.ray.literalura.models.Books;
import com.ray.literalura.models.CBooks;
import com.ray.literalura.models.Data;
import com.ray.literalura.repository.CBooksRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DataService {

//   @Autowired
//   private CBooksRepository repository; //not working

   private final String URL_BASE = "https://gutendex.com/books/";
   private ConsumingAPI consumingAPI = new ConsumingAPI();
   private ConvertingData convertingData = new ConvertingData();
   private Scanner scanner = new Scanner(System.in);


   public void searchBookByTitle(CBooksRepository repository) {
      System.out.println("Enter the book title: ");
      String bookTitle = scanner.nextLine();

      System.out.println("searching...\n");

      var bookInDB = repository.findByTitleContainsIgnoreCase(bookTitle);
      if (bookInDB.isPresent()) {
         System.out.println("Book already in local Database:\n" +
               bookInDB.get());
      } else {
         var json = consumingAPI.getData(URL_BASE + "?search=" + bookTitle.replace(" ", "%20"));
         var data = convertingData.getData(json, Data.class);

         Optional<Books> bookSought = data.results().stream()
//            .filter(b -> b.title().toLowerCase().contains(bookTitle.toLowerCase())) //using search according to api
               .findFirst();
         if (bookSought.isPresent()) {
            CBooks cbooks = new CBooks(bookSought.get());
            repository.save(cbooks);
            System.out.println("Book found\n" + cbooks);
         } else System.out.println("Book not found");
      }
   }


   public void displayRegisteredBooks(CBooksRepository repository) {
      var allBooks = repository.findAll();
      allBooks.forEach(System.out::println);
   }


   public void displayRegisteredAuthors(CBooksRepository repository) {

   }
}
