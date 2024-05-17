package com.ray.literalura.main;

import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.repository.CPersonRepository;
import com.ray.literalura.service.DataService;

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
            
            0. Exit
            
            Select option: """;

      var option = -1;

      while (option != 0) {
         System.out.println(menu);
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
            case 0:
               System.out.println("Application closed");
               break;
            default:
               System.out.println("Invalid option");
         }
      }
   }
}
