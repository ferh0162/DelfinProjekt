package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {
  public void saveMedlemmereToFile(ArrayList<Medlem> medlemmere) throws FileNotFoundException {
    PrintStream out = new PrintStream(("Medlem.csv"));

    for (Medlem medlem : medlemmere) {
      out.print(medlem.getMedlemsNummer());
      out.print(";");
      out.print(medlem.getNavn());
      out.print(";");
      out.print(medlem.getAlder());
      out.print(";");
      out.print(medlem.geteMail());
      out.print(";");
      out.print(medlem.getTelefonNr());
      out.print(";");
      out.print(medlem.getMedlemskabsStatus());
      out.print(";");
      out.print(medlem.isInRestance());
      out.print(";");
      out.print(medlem.getSvømmeType());
      out.print("\n");
    }
  }

  public void saveKonkurrenceSvømmereToFile(ArrayList<Medlem> medlemmere) throws FileNotFoundException {
    PrintStream out = new PrintStream(("KonkurrenceSvømmere.csv"));

    for (Medlem medlem : medlemmere) {
      if (medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER) {
        out.print(medlem.getMedlemsNummer());
        out.print(";");
        out.print(medlem.getSvømmedisciplin());
        out.print(";");
        out.print(medlem.getSvømmeTid());
        out.print(";");
        out.print(medlem.getSvømmeDato());
        out.print("\n");
      }
    }
  }

  public void saveStævnedeltagereToFile(ArrayList<Medlem> medlemmere) throws FileNotFoundException {
    PrintStream out = new PrintStream(("StævneDeltagere.csv"));

    for (Medlem medlem : medlemmere) {
      if (medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlem.getStævneNavn() != null) {
        out.print(medlem.getMedlemsNummer());
        out.print(";");
        out.print(medlem.getStævneNavn());
        out.print(";");
        out.print(medlem.getStævneLokation());
        out.print(";");
        out.print(medlem.getStævneDato());
        out.print(";");
        out.print(medlem.getStævneTid());
        out.print("\n");
      }
    }
  }

  public ArrayList<Medlem> loadMedlemmereFraFil() throws FileNotFoundException {

    ArrayList<Medlem> medlemmere = new ArrayList<>();

    Scanner fileScanner = new Scanner(new File("Medlem.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      Medlem medlem = readMedlemmere(line);

      ArrayList<Medlem> konkurrenceSvømmere = loadKonkurrenceMedlemmereFraFil();
      for (Medlem konkurrenceMedlem : konkurrenceSvømmere) {
        if (konkurrenceMedlem.getMedlemsNummer() == medlem.getMedlemsNummer()) {
          medlem.setSvømmedisciplin(konkurrenceMedlem.getSvømmedisciplin());
          medlem.setSvømmeTid(konkurrenceMedlem.getSvømmeTid());
          medlem.setSvømmeDato(konkurrenceMedlem.getSvømmeDato());
        }
      }

      ArrayList<Medlem> stævneDeltagere = loadStævneDeltagereFraFil();
      for (Medlem stævneDeltager : stævneDeltagere) {
        if (stævneDeltager.getMedlemsNummer() == medlem.getMedlemsNummer()) {
          medlem.setStævneNavn(stævneDeltager.getStævneNavn());
          medlem.setStævneLokation(stævneDeltager.getStævneLokation());
          medlem.setStævneDato(stævneDeltager.getStævneDato());
          medlem.setStæveTid(stævneDeltager.getStævneTid());
        }
      }

      medlemmere.add(medlem);
    }
    return medlemmere;
  }

  public Medlem readMedlemmere(String line) {
    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

    int medlemsnummer = input.nextInt();
    String navn = input.next();
    int alder = input.nextInt();
    String eMail = input.next();
    int telefonNr = input.nextInt();
    MedlemskabsStatus medlemskabsStatus = MedlemskabsStatus.valueOf(input.next());
    boolean restance = input.nextBoolean();
    SvømmeType svømmeType = SvømmeType.valueOf(input.next());

    Medlem medlem = new Medlem(navn, alder, eMail, telefonNr, medlemskabsStatus, restance, svømmeType);
    medlem.setMedlemsNummer(medlemsnummer);

    return medlem;
  }

  public ArrayList<Medlem> loadKonkurrenceMedlemmereFraFil() throws FileNotFoundException {

    ArrayList<Medlem> medlemmere = new ArrayList<>();

    Scanner fileScanner = new Scanner(new File("KonkurrenceSvømmere.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      Medlem medlem = readKonkurrenceSvømmere(line);

      medlemmere.add(medlem);
    }
    return medlemmere;
  }

  public ArrayList<Medlem> loadStævneDeltagereFraFil() throws FileNotFoundException {

    ArrayList<Medlem> medlemmere = new ArrayList<>();

    Scanner fileScanner = new Scanner(new File("StævneDeltagere.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      Medlem medlem = readStævneDeltagere(line);

      medlemmere.add(medlem);
    }
    return medlemmere;
  }

  public Medlem readKonkurrenceSvømmere(String line) {

    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

    int medlemsnummer = input.nextInt();
    Svømmedisciplin svømmeDisciplin = Svømmedisciplin.valueOf(input.next());
    LocalTime svømmeTid = LocalTime.parse(input.next());
    LocalDate svømmeDato = LocalDate.parse(input.next());

    Medlem medlem = new Medlem(medlemsnummer, svømmeDisciplin, svømmeTid, svømmeDato);
    medlem.setMedlemsNummer(medlemsnummer);

    return medlem;
  }

  public Medlem readStævneDeltagere(String line) {

    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

    int medlemsnummer = input.nextInt();
    String stævneNavn = input.next();
    String stævneLokation = input.next();
    LocalDate svømmeDato = LocalDate.parse(input.next());
    LocalTime svømmeTid = LocalTime.parse(input.next());

    Medlem medlem = new Medlem(medlemsnummer, stævneNavn, svømmeDato, svømmeTid);
    medlem.setStævneLokation(stævneLokation);

    return medlem;
  }
}

