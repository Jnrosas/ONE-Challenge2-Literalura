package com.ray.literalura.main;

import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.repository.CPersonRepository;
import com.ray.literalura.service.DataService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
   private Scanner scanner = new Scanner(System.in);
   private DataService service = new DataService();
   private CBooksRepository cBooksRepository;
   private CPersonRepository cPersonRepository;

   public Main(CBooksRepository cBooksRepository, CPersonRepository cPersonRepository) {
      this.cBooksRepository = cBooksRepository;
      this.cPersonRepository = cPersonRepository;
   }

   public void menu() {
      var menu = """
            
            Menu
            1. Search book by title
            2. Display all registered books
            3. Display all registered authors
            4. Provided a year, display the authors alive then
            5. Search registered book by language
            6. Display the most downloaded registered book
            7. Display the least downloaded registered book
            8. Search author registered by name
            9. How many books are there, registered in the DB?
            
            0. Exit
            
            Select option:\t""";

      var option = -1;

      try {
         while (option != 0) {
            System.out.print(menu);
            option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
               case 1:
                  service.searchBookByTitle(cBooksRepository, cPersonRepository);
                  break;
               case 2:
                  service.displayRegisteredBooks(cBooksRepository);
                  break;
               case 3:
                  service.displayRegisteredAuthors(cPersonRepository);
                  break;
               case 4:
                  service.aliveAuthorsByYear(cPersonRepository);
                  break;
               case 5:
                  service.displayRegisteredBooksByLanguage(cBooksRepository);
                  break;
               case 6:
                  service.mostDownloadedRegisteredBook(cBooksRepository);
                  break;
               case 7:
                  service.leastDownloadedRegisteredBook(cBooksRepository);
                  break;
               case 8:
                  service.authorByName(cPersonRepository);
                  break;
               case 9:
                  service.countBooks(cBooksRepository);
                  break;
               case 0:
                  System.out.println("\nApplication closed\n");
                  break;
               default:
                  System.out.println("\nInvalid option\n");
            }
         }
      }
      catch (InputMismatchException e) {
         System.out.println("\nInvalid option, enter a number from the Menu option\nApplication closed\n");
      }
   }
}
