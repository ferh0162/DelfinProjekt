package com.company;

import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
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
          System.out.println(Color.RED + "HAR DU HUSKET AT GEMME DINE NYE REGISTRERINGER?" + Color.RESET);
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
//TODO: HVIS DER INDTASTES EN TAL ISTEDET FOR STRING CRASHER PROGRAMMET. DETTE SKAL RETTES

    System.out.println("Opret ny medlem\n-----------------");
    Scanner input = new Scanner(System.in);
    System.out.print("Fulde Navn: ");
    String navn = input.nextLine();
    System.out.print("alder: ");
    int alder = input.nextInt();

    System.out.print("Email: ");
    String eMail = input.next();
    input.nextLine();

    System.out.print("Telefon nr.: ");
    int telefonNr = input.nextInt();
    input.nextLine();

    System.out.println("Hvilken svømmeType er du?");
    System.out.println("1.  Motionist");
    System.out.println("2.  Konkurrence Svømmer");
    int choice = input.nextInt();
    SvømmeType svømmeType = SvømmeType.MOTIONIST;
    while (choice < 1 || choice > 2) {
      System.out.println("Du kan kun vælge mellem 1-2");
      choice = input.nextInt();
    }
    switch (choice) {
      case 1 -> svømmeType = SvømmeType.MOTIONIST;
      case 2 -> svømmeType = SvømmeType.KONKURRENCESVØMMER;
    }

    //opretter ny medlem i MedlemmerBase
    medlemmerBase.opretNyMedlem(navn, alder, eMail, telefonNr, MedlemskabsStatus.AKTIV, false, svømmeType);

  }


  public void list() {
    System.out.println("Liste over alle medlemmere");
    System.out.println("-----------------------");
    for (Medlem medlem : medlemmerBase.getAllMedlemmere()) {
      System.out.println(Color.YELLOW + medlem + Color.RESET);
    }
    System.out.println("Der er " + medlemmerBase.getMedlemCount() + " medlemmere i klubben.");
  }

  public void velkommenBesked() throws InterruptedException {
    System.out.println("DELFINENS ADMINISTRATIVE SYSTEM");
    loadingSkærm();
  }

  public void start() throws InterruptedException, FileNotFoundException {
    velkommenBesked();
    load();
    menu();
  }

  public void menu() throws FileNotFoundException, InterruptedException {
    clearScreen();
    System.out.println("==========================================");
    System.out.println("Vælg mellem følgende valgmuligheder\n");
    System.out.println("1.  Indregisterings portal");
    System.out.println("2.  Kassere portal");
    System.out.println("3.  Træner portal");
    System.out.println("0.  Exit");

    Scanner input = new Scanner(System.in);
    int choice = 0;
    boolean loop = true;
    while (loop) {
      try {
        System.out.print("Vælg venligst: ");
        choice = input.nextInt();
        while (choice < 0 || choice > 3) {
          System.out.println(Color.RED+"Kun tal fra 0-3 er tilladt"+Color.RESET);
          choice = input.nextInt();
        }
        loop = false;
      } catch (InputMismatchException e) {
        System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
        input.nextLine();
      }
    }

    while (true) {
      switch (choice) {
        case 1 -> medlemsSystem();
        case 2 -> kassere();
        case 3 -> træner();
        case 0 -> {
          clearScreen();
          clearScreen();
          System.out.println(Color.RED + "APPLICATION AFSLUTTET" + Color.RESET);
          System.exit(0);
        }
      }
    }
  }

  public void save() throws FileNotFoundException {
    System.out.println(Color.GREEN + "Saving the database ...");
    medlemmerBase.saveDatabase();
    System.out.println("Saving database completed succesfully");
    System.out.println("You can now exit the application" + Color.RESET);
  }

  public void load() throws FileNotFoundException {
    System.out.println(Color.GREEN + "Loading the database ...");
    medlemmerBase.loadDatabase();
    System.out.println("Done!" + Color.RESET);
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
      System.out.println(Color.GREEN + "Medlemmet med navnet '" + Color.RESET + name + Color.GREEN + "' er blevet afksrivet fra klubben" + Color.RESET);
    } else {
      System.out.println(Color.RED + "Medlemmet '" + Color.RESET + name + Color.RED + "' findes ikke, og kan derfor ikke blive afskrivet fra klubben" + Color.RESET);
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
    System.out.println("0.  Menu");

    Scanner input = new Scanner(System.in);
    int choice = 0;
    boolean loop = true;
    while (loop) {
      try {
        System.out.print("Vælg venligst: ");
        choice = input.nextInt();
        while (choice < 0 || choice > 4) {
          System.out.println(Color.RED+"Kun tal fra 0-4 er tilladt"+Color.RESET);
          choice = input.nextInt();
        }
        loop = false;
      } catch (InputMismatchException e) {
        System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
        input.nextLine();
      }
    }

    switch (choice) {
      case 1 -> kontingentOversigt();
      case 2 -> samletBetalingsOversigt();
      case 3 -> hentRestance();
      case 4 -> setIRestance();
      case 0 -> menu();
    }
  }

  public void loadingSkærm() throws InterruptedException {
   /* for (int i = 0; i < 1; i++) {
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

    Thread.sleep(700);
    System.out.println(Color.GREEN_BOLD + "Programmet Kører" + Color.RESET);
    Thread.sleep(1300);
*/
    clearScreen();
  }

  public void kontingentOversigt() throws FileNotFoundException {
    clearScreen();
    medlemmerBase.hentKontingentOversigt();
  }

  public void samletBetalingsOversigt() throws FileNotFoundException {
    clearScreen();
    System.out.println(Color.YELLOW_BOLD + medlemmerBase.hentSamletBetaling() + " kr." + Color.RESET);
    System.out.println();
  }

  public void hentRestance() {
    medlemmerBase.hentIRestance();
  }

  public void clearScreen() {
    System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n");

  }

  public void setIRestance() {
    System.out.println("Sæt medlem i restance");
    System.out.println("-------------");
    list();
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
      System.out.println(Color.GREEN + "Medlemmet med navnet '" + Color.RESET + name + Color.GREEN + "' er blevet sat i restance" + Color.RESET);
    } else {
      System.out.println(Color.RED + "Medlemmet '" + Color.RESET + name + Color.RED + "' findes ikke, og kan derfor ikke blive sat i restance" + Color.RESET);
    }

  }

  public void træner() throws InterruptedException, FileNotFoundException {
    System.out.println("==========================================");
    System.out.println("Vælg mellem følgende valgmuligheder\n");
    System.out.println("1.  Se hold oversigt");
    System.out.println("2.  Top 5");
    System.out.println("3.  Gem svømmedsicplin");
    System.out.println("4.  Registrer disciplin");
    System.out.println(Color.RED+"5.  Gem ny Konkurrence Deltager SKAL IKKE BRUGRES"+Color.RESET);
    System.out.println("6.  Se konkurrence deltager");
    System.out.println("0.  Exit");

    Scanner input = new Scanner(System.in);
    int choice = 0;
    boolean loop = true;
    while (loop) {
      try {
        System.out.print("Vælg venligst: ");
        choice = input.nextInt();
        while (choice < 0 || choice > 6) {
          System.out.println(Color.RED+"Kun tal fra 0-6 er tilladt"+Color.RESET);
          choice = input.nextInt();
        }
        loop = false;
      } catch (InputMismatchException e) {
        System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
        input.nextLine();
      }
    }

    switch (choice) {
      case 1 -> seHoldOversigt();
      case 2 -> top5();
      case 3 -> saveSvømmeDisciplin();
      case 4 -> registerDisciplin();
      case 5 -> stævneDeltagere();
      case 6 -> seStævneDeltagere();
      case 0 -> menu();
    }
  }
  public void seStævneDeltagere() throws FileNotFoundException{
    medlemmerBase.seStævneDeltagere();
  }
public void stævneDeltagere() throws FileNotFoundException {
    medlemmerBase.saveStævneDeltagere();

}
  public void seHoldOversigt() throws InterruptedException, FileNotFoundException {
    //TODO: Lav senior- og juniorkonkurrence svømmer
    System.out.println("======================================");
    System.out.println("Velkommen til konkurrence Svømmer portalen");
    System.out.println("1. Se Junior holdet");
    System.out.println("2. Se Senior Holder");
    System.out.println("3. Se begge hold");
    System.out.println("0. Returner til træner programmet");
    System.out.println();

    Scanner input = new Scanner(System.in);
    int choice = 0;
    boolean loop = true;
    while (loop) {
      try {
        System.out.print("Vælg venligst: ");
        choice = input.nextInt();
        while (choice < 0 || choice > 3) {
          System.out.println(Color.RED+"Kun tal fra 0-3 er tilladt"+Color.RESET);
          choice = input.nextInt();
        }
        loop = false;
      } catch (InputMismatchException e) {
        System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
        input.nextLine();
      }
    }
    switch (choice) {
      case 1 -> seHoldJunior();
      case 2 -> seHoldSenior();
      case 3 -> medlemmerBase.se2Hold();
      case 0 -> træner();
    }
  }

  public void seHoldJunior() {
    medlemmerBase.seJuniorHold();
  }

  public void seHoldSenior() {
    medlemmerBase.seSeniorHold();
  }

  public void saveSvømmeDisciplin() throws FileNotFoundException {
    medlemmerBase.lavKonkurrenceSvømmerDisciplin();
  }

  public void registerDisciplin() throws FileNotFoundException, InterruptedException {
    System.out.println("Opret disciplin for medlem\n-----------------");
    Scanner input = new Scanner(System.in);

    for (Medlem medlem : medlemmerBase.getAllMedlemmere()) {
      System.out.println(Color.YELLOW + medlem.getNavn() + " " +Color.YELLOW_BRIGHT+ medlem.getSvømmedisciplin()+ Color.RESET);
    }
    System.out.println("Der er " + medlemmerBase.getMedlemCount() + " medlemmere i klubben.");

    System.out.println("Indtast navnet på den person, som skal regsistrere disciplin");
    String navn = input.nextLine();
    if (!medlemmerBase.checkOmMedlemEksistere(navn)) {
      System.out.println(Color.RED+"Medlem findes ikke"+Color.RESET);
      træner();
    } else if (medlemmerBase.checkOmMedlemEksistere(navn)) {
      System.out.println(Color.GREEN_BOLD+"Medlemmet "+ navn+" er fundet"+Color.RESET);

      System.out.println("Vælg disciplin: ");
      System.out.println("1. Brystsvømning");
      System.out.println("2. Butterfly");
      System.out.println("3. Crawl");
      System.out.println("4. RygCrawl");
      System.out.println("0. ingen disciplin");

      Svømmedisciplin svømmedisciplin = null;

      //TODO: Hvis man skriver String istedet for en int skal programmet ikke crashe
      int choice = 0;
      boolean loop = true;
      while (loop) {
        try {
          System.out.print("Vælg venligst: ");
          choice = input.nextInt();
          while (choice < 0 || choice > 4) {
            System.out.println(Color.RED+"Kun tal fra 1-4 er tilladt"+Color.RESET);
            choice = input.nextInt();
          }
          loop = false;
        } catch (InputMismatchException e) {
          System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
          input.nextLine();
        }
      }

      switch (choice) {
        case 1 -> svømmedisciplin = Svømmedisciplin.BRYSTSVØMNING;
        case 2 -> svømmedisciplin = Svømmedisciplin.BUTTERFLY;
        case 3 -> svømmedisciplin = Svømmedisciplin.CRAWL;
        case 4 -> svømmedisciplin = Svømmedisciplin.RYGCRAWL;
        case 0 -> svømmedisciplin = null;
      }

      System.out.println("-------------------------------");
      System.out.println("indtast svømme tid");
      System.out.println("Indtast venligst din tid i dette format 00:00");
      LocalTime svømmeTid = null;
      boolean isNotLocalTime = true;
      while (isNotLocalTime) {
        try {
          svømmeTid = LocalTime.parse(input.next());
          isNotLocalTime = false;
        } catch (DateTimeException e) {
          System.out.println("Du skal indtaste i dette format 00:00");
        }
      }
      System.out.println("--------------------------------");
      System.out.println("indtast venligst datoen for svømmeturen i dette format 0000-00-00");
      System.out.println("År - Måned - Dag");
      LocalDate svømmeDato = null;
      boolean isNotLocalDate = true;
      while (isNotLocalDate) {
        try {
          svømmeDato = LocalDate.parse(input.next());
          isNotLocalDate = false;
        } catch (DateTimeException e) {
          System.out.println("Du skal indtaste i dette format 0000-00-00");
          System.out.println("År - Måned - Dag");
          System.out.println();
        }
      }

      medlemmerBase.setDisciplin(navn, svømmedisciplin, svømmeTid, svømmeDato);
    }
  }
  public void top5() {
    System.out.println();
    System.out.println("====================================");
    System.out.println("se top 5 indenfor disciplin: ");
    System.out.println("1. Brystsvømning");
    System.out.println("2. Butterfly");
    System.out.println("3. Crawl");
    System.out.println("4. RygCrawl");
    System.out.println("0. ingen disciplin");

    Scanner input = new Scanner(System.in);
    int choice = 0;
    boolean loop = true;
    while (loop) {
      try {
        System.out.print("Vælg venligst: ");
        choice = input.nextInt();
        while (choice < 0 || choice > 4) {
          System.out.println(Color.RED+"Kun tal fra 1-4 er tilladt"+Color.RESET);
          choice = input.nextInt();
        }
        loop = false;
      } catch (InputMismatchException e) {
        System.out.println(Color.RED + "Skriv venligst et tal og ikke et andet symbol" + Color.RESET);
        input.nextLine();
      }
      System.out.println("\n\n");
    }
    switch (choice) {
      case 1 -> sorterBrystSvømning();
      case 2 -> sorterButterFly();
      case 3 -> sorterCrawl();
      case 4 -> sorterRygCrawl();
    }
  }
  public void sorterBrystSvømning(){
    System.out.println(Color.BLUE_BOLD+"SENIORHOLDET"+Color.RESET);
    medlemmerBase.sorterBrystSvømningSenior();

    System.out.println();

    System.out.println(Color.BLUE_BOLD+"JUNIORHOLDET"+Color.RESET);
    medlemmerBase.sorterBrystSvømningJunior();
  }
  public void sorterCrawl(){
    System.out.println(Color.BLUE_BOLD+"SENIORHOLDET"+Color.RESET);
    medlemmerBase.sorterCrawlSenior();

    System.out.println();

    System.out.println(Color.BLUE_BOLD+"JUNIORHOLDET"+Color.RESET);
    medlemmerBase.sorterCrawlJunior();
  }
  public void sorterButterFly(){
    System.out.println(Color.BLUE_BOLD+"SENIORHOLDET"+Color.RESET);
    medlemmerBase.sorterButterFlySenior();

    System.out.println();

    System.out.println(Color.BLUE_BOLD+"JUNIORHOLDET"+Color.RESET);
    medlemmerBase.sorterButterFlyJunior();
  }
  public void sorterRygCrawl(){
    System.out.println(Color.BLUE_BOLD+"SENIORHOLDET"+Color.RESET);
    medlemmerBase.sorterRygCrawlSenior();

    System.out.println();

    System.out.println(Color.BLUE_BOLD+"JUNIORHOLDET"+Color.RESET);
    medlemmerBase.sorterRygCrawlJunior();
  }

}
