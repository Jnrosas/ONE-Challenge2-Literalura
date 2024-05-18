package com.ray.literalura.service;

import com.ray.literalura.models.*;
import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.repository.CPersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Scanner;

@Service
public class DataService {

   //didn't use this, because I get error "this.cPersonRepository is null"
//   @Autowired
//   CPersonRepository cPersonRepository;
//   @Autowired
//   CBooksRepository cBooksRepository;


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


   public void aliveAuthorsByYear(CPersonRepository cPersonRepository) {
      System.out.println("Enter a year: ");
      var year = scanner.nextInt();
      scanner.nextLine();
      var aliveAuthors = cPersonRepository.aliveAuthorsByYear(year);
      aliveAuthors.forEach(System.out::println);
   }

   public void displayRegisteredBooksByLanguage(CBooksRepository cBooksRepository) {
      System.out.print("""
            \nChoose the language to look for
            EN - English
            ES - Spanish
            PT - Portuguese
            FR - French
            DE - German
            (for example: en):\t""");
      var option = scanner.nextLine();
      switch (option.toLowerCase()) {
         case "en":
            var en = cBooksRepository.findByLanguagesIgnoreCase(option);
            if (en.size() > 0) {
               en.forEach(System.out::println);
               break;
            } else System.out.println("\nNo books in English found"); break;

         case "es":
            var es = cBooksRepository.findByLanguagesIgnoreCase(option);
            if (es.size() > 0) {
               es.forEach(System.out::println);
               break;
            } else System.out.println("\nNo books in Spanish found"); break;

         case "pt":
            var pt = cBooksRepository.findByLanguagesIgnoreCase(option);
            if (pt.size() > 0) {
               pt.forEach(System.out::println);
               break;
            } else System.out.println("\nNo books in Portuguese found"); break;

         case "fr":
            var fr = cBooksRepository.findByLanguagesIgnoreCase(option);
            if (fr.size() > 0) {
               fr.forEach(System.out::println);
               break;
            } else System.out.println("\nNo books in French found"); break;

         case "de":
            var de = cBooksRepository.findByLanguagesIgnoreCase(option);
            if (de.size() > 0) {
               de.forEach(System.out::println);
               break;
            } else System.out.println("\nNo books in German found"); break;

         default:
            System.out.println("Invalid option");

      }
   }

   public void mostDownloadedRegisteredBook(CBooksRepository cBooksRepository) {
      var mostDownloadedBook = cBooksRepository.findByDownload_countMax();
      System.out.println(mostDownloadedBook);
   }

   public void leastDownloadedRegisteredBook(CBooksRepository cBooksRepository) {
      var leastDownloadedBook = cBooksRepository.findByDownload_countMin();
      System.out.println(leastDownloadedBook);
   }

   public void authorByName(CPersonRepository cPersonRepository) {
      System.out.println("\nEnter the author's name: ");
      var authorName = scanner.nextLine();
      var author = cPersonRepository.findByNameContainsIgnoreCase(authorName);
      if (author.isPresent()) {
         System.out.println("\nAuthor found\n" + author.get());
      } else System.out.print("\nAuthor not found\n");
   }

   public void countBooks(CBooksRepository cBooksRepository) {
      var numberOfBooks = cBooksRepository.count();
      System.out.println("\nThere are currently " + numberOfBooks +
            " books registered in the Database\n");
   }
}
