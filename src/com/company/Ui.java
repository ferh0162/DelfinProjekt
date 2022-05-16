package com.company;

import com.sun.jdi.ClassNotLoadedException;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class Ui {
  private MedlemmerBase medlemmerBase = new MedlemmerBase();

  public void medlemsSystem() throws FileNotFoundException, InterruptedException {
    //TODO: tilføj funktioner, til prorgammet, så det ikke crasher når man skriver en String istedet for int f.eks.
    clearScreen();
    System.out.println("Velkommen til Medlemmer håndteringsprogrammet");
    System.out.println("====================================");
    System.out.println("Beta version");

    while (true) {
      System.out.println();
      switch (mainMenu()) {
        case 0 -> {
          System.out.println(Color.RED+"HAR DU HUSKET AT GEMME DINE NYE REGISTRERINGER?"+Color.RESET);
          exit();
        }
        case 1 -> list();
        case 2 -> medlemsRegistrering();
        case 3 -> delete();
        case 4 -> load();
        case 5 -> save();
      }
    }
  }
  public void exit() throws FileNotFoundException, InterruptedException {
    menu();
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
      for (Medlem medlem : medlemmerBase.getAllMedlemmere()) {
        System.out.println(Color.YELLOW + medlem+ Color.RESET);
      }
      System.out.println("Der er " + medlemmerBase.getMedlemCount() + " medlemmere i klubben.");
  }
  public void velkommenBesked() throws InterruptedException {
    System.out.println("DELFINENS ADMINISTRATIVE SYSTEM");
loadingSkærm();
  }
  public void start() throws InterruptedException, FileNotFoundException {
    velkommenBesked();
    menu();
  }

  public void menu() throws FileNotFoundException, InterruptedException {
    clearScreen();
    System.out.println("==========================================");
    System.out.println("Vælg mellem følgende valgmuligheder\n");
    System.out.println("1.  Registrer nyt medlem");
    System.out.println("2.  Tilgå kontingent betalinger");
    System.out.println("3.  Exit");

    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 0 || choice > 3) {
      System.out.println("Only values 0-3 allowed");
      choice = input.nextInt();
    }
    while (true) {
      switch (choice) {
        case 1 -> medlemsSystem();
        case 2 -> kassere();
        case 3 -> {
          clearScreen();
          clearScreen();
          System.out.println(Color.RED+"APPLICATION AFSLUTTET"+Color.RESET);
          System.exit(0);
        }
      }
    }
  }
  public void save() throws FileNotFoundException {
    System.out.println(Color.GREEN+"Saving the database ...");
    medlemmerBase.saveDatabase();
    System.out.println("Saving database completed succesfully");
    System.out.println("You can now exit the application"+Color.RESET);
  }
  public void load() throws FileNotFoundException {
    System.out.println(Color.GREEN+"Loading the database ...");
    medlemmerBase.loadDatabase();
    System.out.println("Done!"+Color.RESET);
  }
  public void delete() {
    System.out.println("Slet medlem");
    System.out.println("-------------");
    System.out.println("Venligst skriv fornavnet, på det medlem der skal slettes: ");

    Scanner input = new Scanner(System.in);
    String name = input.nextLine();
    String text = name;

    String firstWord = "";
    String secondWord = "";
    int index = text.indexOf(' ');

    if (index > -1) { // Check if there is more than one word.

      firstWord = text.substring(0, index).trim(); // Extract first word.
      secondWord = text.substring(index + 1, text.length());

    } else {
      firstWord = text; // Text is the first word itself.
    }
    System.out.println(firstWord);
    boolean success = medlemmerBase.sletMedlem(firstWord);
    if (success) {
      System.out.println(Color.GREEN+"Medlemmet med navnet '" +Color.RESET+ name +Color.GREEN+ "' er blevet afksrivet fra klubben"+Color.RESET);
    } else {
      System.out.println(Color.RED+"Medlemmet '" +Color.RESET+ name +Color.RED+ "' findes ikke, og kan derfor ikke blive afskrivet fra klubben"+Color.RESET);
    }
  }
  public void kassere() throws FileNotFoundException, InterruptedException {
    System.out.println();
    System.out.println("========================================");
    System.out.println("Vælg mellem følgende valgmuligheder\n");
    System.out.println("1.  Oversigt over medlemmer kontingent");
    System.out.println("2.  Oversigt over samlede betalinger");
    System.out.println("3.  Oversigt over restance liste");
    System.out.println("4.  Sæt medlem i Restance");
    System.out.println("");

    Scanner input = new Scanner(System.in);
    int choice = input.nextInt();
    while (choice < 1 || choice > 5) {
      System.out.println("Du kan kun vælge mellem 1-5");
      choice = input.nextInt();
    }

    switch (choice){
      case 1 -> kontingentOversigt();
      case 2 -> samletBetalingsOversigt();
      case 3 -> hentRestance();
      case 4 -> setIRestance();
      case 0 -> menu();
    }
  }
  public void loadingSkærm() throws InterruptedException {
    for (int i = 0; i < 3; i++) {
      System.out.println(".");
      Thread.sleep(300);
      System.out.println(".");
      Thread.sleep(300);
      System.out.println(".");
      Thread.sleep(500);
      System.out.print("LOADING");
      for (int j = 0; j < 3; j++) {
        Thread.sleep(300);
        System.out.print(".");
      }
    }
    System.out.println();
    System.out.println(".");
    Thread.sleep(300);
    System.out.println(".");
    Thread.sleep(300);
    System.out.println(Color.GREEN_BOLD+"Programmet Kører"+Color.RESET);
    Thread.sleep(800);

clearScreen();
  }
  public void kontingentOversigt() throws FileNotFoundException {
    clearScreen();
    medlemmerBase.hentKontingentOversigt();
  }
  public void samletBetalingsOversigt() throws FileNotFoundException {
    clearScreen();
    System.out.println(Color.YELLOW_BOLD+medlemmerBase.hentSamletBetaling()+" kr." + Color.RESET);
    System.out.println();
  }
  public void hentRestance(){
    medlemmerBase.hentIRestance();
  }
  public void clearScreen(){
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

  }
  public void setIRestance(){
    System.out.println("Sæt medlem i restance");
    System.out.println("-------------");
    System.out.println("Venligst skriv fornavnet, på det medlem der skal sættes i restance: ");

    Scanner input = new Scanner(System.in);
    String name = input.nextLine();
    String text = name;

    String firstWord = "";
    String secondWord = "";
    int index = text.indexOf(' ');

    if (index > -1) { // Check if there is more than one word.

      firstWord = text.substring(0, index).trim(); // Extract first word.
      secondWord = text.substring(index + 1, text.length());

    } else {
      firstWord = text; // Text is the first word itself.
    }
    System.out.println(firstWord);
    boolean success = medlemmerBase.setMedlemIRestance(firstWord);
    if (success) {
      System.out.println(Color.GREEN+"Medlemmet med navnet '" +Color.RESET+ name +Color.GREEN+ "' er blevet sat i restance"+Color.RESET);
    } else {
      System.out.println(Color.RED+"Medlemmet '" +Color.RESET+ name +Color.RED+ "' findes ikke, og kan derfor ikke blive sat i restance"+Color.RESET);
    }

  }
}
