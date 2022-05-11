package com.company;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
  private MedlemmerBase medlemmerBase = new MedlemmerBase();

  public void start() throws FileNotFoundException {
    //TODO: tilføj funktioner, til prorgammet, så det ikke crasher når man skriver en String istedet for int f.eks.
    System.out.println("Velkommen til Medlemmer håndteringsprogrammet");
    System.out.println("====================================");
    System.out.println("Beta version");

    while (true) {
      System.out.println();
      switch (mainMenu()) {
        case 0 -> System.out.println();//exit();
        case 1 -> list();
        case 2 -> medlemsRegistrering();
        case 3 -> delete();
        case 4 -> load();
        case 5 -> save();
      }
    }
  }

  public int mainMenu() {
    System.out.println("""
                Main menu
                ---------
                1) Vis alle medlemmere
                2) Registrer nyt medlem
                3) Smid et medlem ud fra klubben
                4) Load medlemmere fra fil
                5) Gem medlemmere til file
                                
                0) Exit application
                """);
    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 7) {
      System.out.println("Only values 0-7 allowed");
      choice = input.nextInt();
    }
    return choice;
  }

  public void medlemsRegistrering() throws FileNotFoundException {

    System.out.println("Opret ny medlem\n-----------------");
    Scanner input = new Scanner(System.in);
    //TODO: fulde navn skal registreres
    System.out.print("Navn: ");
    String navn = input.nextLine();
    System.out.print("alder: ");
    int alder = input.nextInt();

    System.out.print("Email: ");
    String eMail = input.next();
    input.nextLine();

    System.out.print("Telefon nr.: ");
    int telefonNr = input.nextInt();
    input.nextLine();

    //opretter ny medlem i MedlemmerBase
medlemmerBase.opretNyMedlem(navn,alder,eMail,telefonNr);
  }

  public void list(){
      System.out.println("Liste over alle medlemmere");
      System.out.println("-----------------------");
      for (Medlem medlem : medlemmerBase.getAllAnimals()) {
        System.out.println(Color.YELLOW + medlem+ Color.RESET);
      }
      System.out.println("Der er " + medlemmerBase.getMedlemCount() + " medlemmere i klubben.");
  }
  public void velkommenBesked(){
    System.out.println("DELFINENS ADMINISTRATIVE SYSTEM");
    for (int i = 0; i < 5; i++) {
      System.out.println(".");
    }
  }

  public void menu(){
    System.out.println("Vælg mellem følgende valgmuligheder\n");
    System.out.println("1.  Registrer nyt medlem");
    System.out.println("2.  Tilgå kontingent betalinger");
    System.out.println("3.  Se konkurrencesvømmer informationer");
  }
  private void save() throws FileNotFoundException {
    System.out.println(Color.GREEN+"Saving the database ...");
    medlemmerBase.saveDatabase();
    System.out.println("Saving database completed succesfully");
    System.out.println("You can now exit the application"+Color.RESET);
  }
  private void load() throws FileNotFoundException {
    System.out.println(Color.GREEN+"Loading the database ...");
    medlemmerBase.loadDatabase();
    System.out.println("Done!"+Color.RESET);
  }
  private void delete() {
    System.out.println("Slet medlem");
    System.out.println("-------------");
    System.out.println("Venligst skriv fornavnet, på det medlem der skal slettes: ");
    Scanner input = new Scanner(System.in);
    String name = input.nextLine();

    boolean success = medlemmerBase.sletMedlem(name);
    if (success) {
      System.out.println("Medlemmet med navnet '" + name + "' er blevet afksrivet fra klubben");
    } else {
      System.out.println("Medlemmet '" + name + "' findes ikke, og kan derfor ikke blive afskrivet fra klubben");
    }
  }
}
