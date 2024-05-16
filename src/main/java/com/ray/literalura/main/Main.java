package com.ray.literalura.main;

import com.ray.literalura.repository.CBooksRepository;
import com.ray.literalura.service.DataService;

import java.util.Scanner;

public class Main {
   private Scanner scanner = new Scanner(System.in);
   private DataService service = new DataService();
   private CBooksRepository repository;

   public Main(CBooksRepository repository) {
      this.repository = repository;
   }

   public void menu() {
      var menu = """
            
            Menu
            1. Search book by title
            2. Display all books searched
            
            0. Exit
            
            Select option: """;

      var option = -1;

      while (option != 0) {
         System.out.println(menu);
         option = scanner.nextInt();
         scanner.nextLine();

         switch (option) {
            case 1:
               service.searchBookByTitle(repository);
               break;
            case 2:
               service.displayAllBooks(repository);
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
