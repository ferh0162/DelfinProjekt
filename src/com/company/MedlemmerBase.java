package com.company;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class MedlemmerBase {
  private ArrayList<Medlem> medlemmere;
  private ArrayList<Medlem> restanceListe;

  public MedlemmerBase() {
    medlemmere = new ArrayList<>();
    restanceListe = new ArrayList<>();
  }

  public void opretNyMedlem(String navn, int alder, String eMail, int telefonNr, MedlemskabsStatus medlemskabsStatus, boolean isInRestance, SvømmeType svømmeType) {
    Medlem medlem = new Medlem(navn, alder, eMail, telefonNr, medlemskabsStatus, isInRestance, svømmeType);
    medlem.setSvømmeType(svømmeType);
    medlemmere.add(medlem);
  }

  public void redigerMedlem(Medlem medlem, int alder, String eMail, int telefonNr, MedlemskabsStatus medlemskabsStatus, boolean isInRestance, SvømmeType svømmeType){
    medlem.setAlder(alder);
   medlem.seteMail(eMail);
    medlem.setTelefonNr(telefonNr);
   medlem.setMedlemskabsStatus(medlemskabsStatus);
   medlem.setInRestance(isInRestance);
    medlem.setSvømmeType(svømmeType);
  }

  public Iterable<Medlem> getAllMedlemmere() {
    return medlemmere;
  }

  public int getMedlemCount() {
    return medlemmere.size();
  }

  public void saveDatabase() throws FileNotFoundException {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveMedlemmereToFile(medlemmere);
  }

  public void loadDatabase() throws FileNotFoundException {
    FileHandler fileHandler = new FileHandler();
    medlemmere = fileHandler.loadMedlemmereFraFil();
  }

  public void hentKontingentOversigt() throws FileNotFoundException {
    loadDatabase();
    System.out.println();
    System.out.println(Color.WHITE_BOLD_BRIGHT+"PASSIVE MEDLEMSKAB" + Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      if (!medlemmere.get(i).medlemskabsStatus.equals(MedlemskabsStatus.AKTIV)) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getNavn() + Color.RESET + " Betaler: " + Color.YELLOW_BOLD + "500 kr." + Color.RESET);
      }
    }
    System.out.println();
    System.out.println(Color.WHITE_BOLD_BRIGHT+"JUNIOR MEDLEMSKAB" + Color.RESET);
      for (int i = 0; i < medlemmere.size(); i++) {
        if (medlemmere.get(i).getAlder() < 18) {
          System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getNavn() + Color.RESET + " Betaler: " + Color.YELLOW_BOLD + "1000 kr." + Color.RESET);

        }
      }
    System.out.println();
    System.out.println(Color.WHITE_BOLD_BRIGHT+"SENIOR MEDLEMSKAB"+Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
       if (18 < medlemmere.get(i).getAlder() && medlemmere.get(i).getAlder() < 60) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getNavn() + Color.RESET + " Betaler: " + Color.YELLOW_BOLD + "1600 kr." + Color.RESET);

      }
    }
    System.out.println();
    System.out.println(Color.WHITE_BOLD_BRIGHT+"60+ ÅR MEDLEMBSKAB"+Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      if (60 < medlemmere.get(i).getAlder()) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getNavn() + Color.RESET + " Betaler: " + Color.YELLOW_BOLD + "1200 kr." + Color.RESET);
      }
    }
    }


  public int hentSamletBetaling() throws FileNotFoundException {
    loadDatabase();
    int samletBetaling = 0;
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlemI = medlemmere.get(i);

      if (medlemI.isInRestance() == true) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + "Mangler betaling");
      } else if (!medlemI.medlemskabsStatus.equals(MedlemskabsStatus.AKTIV)) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + " Betaler: " + "500 kr.");
        samletBetaling += 500;
      } else if (medlemI.getAlder() < 18) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + " Betaler: " + "1000 kr.");
        samletBetaling += 1000;

      } else if (18 < medlemI.getAlder() && medlemI.getAlder() < 60) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + " Betaler: " + "1600 kr.");
        samletBetaling += 1600;

      } else if (60 < medlemI.getAlder()) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + " Betaler: " + "1200 kr.");
        samletBetaling += 1200;
      }
    }
    System.out.println();
    System.out.print("Samlede Betaling for alle medlemmere: ");
    return samletBetaling;
  }

  public void hentIRestance() {
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlemI = medlemmere.get(i);
      if (medlemI.isInRestance()) {
        System.out.println(Color.RED + medlemI.getNavn() + Color.RESET + " er i restance");

      }
    }
  }

  public boolean sletMedlem(String navn) {
    // find animal with this name
    Medlem medlem = findMedlemByName(navn);
    if (medlem == null) {
      return false;
    } else {
      medlemmere.remove(medlem);
      return true;
    }
  }

  public boolean setMedlemIRestance(String navn) {
    // find medlem with this name
    Medlem medlem = findMedlemByName(navn);
    if (medlem == null) {
      return false;
    } else {
      restanceListe.add(medlem);
      medlem.setInRestance(true);
      return true;
    }
  }

  public void se2Hold() {
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlem = medlemmere.get(i);
      if (medlem.getAlder() < 18) {
        System.out.println(medlem.getNavn() + " Er i Juniorholdet");
      } else if (medlem.getAlder() >= 18) {
        System.out.println(medlem.getNavn() + " Er i Seniorholdet");
      }
    }
  }

  public void seJuniorHold() {
    System.out.println(Color.PURPLE_BOLD + "Junior holdet:" + Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlem = medlemmere.get(i);
      if (medlem.getAlder() < 18 && medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER) {
        System.out.println(medlem.getNavn());

      }
    }
  }

  public void seSeniorHold() {
    System.out.println(Color.PURPLE_BOLD + "Senior Holdet" + Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlem = medlemmere.get(i);
      if (medlem.getAlder() >= 18 && medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER) {
        System.out.println(medlem.getNavn() + " " + medlem.getSvømmedisciplin() + " " + medlem.getSvømmeTid() + " " + medlem.getSvømmeDato());

      }
    }
  }

  public Medlem findMedlemByName(String navn) {
    for (Medlem medlem : medlemmere) {
      if (medlem.getFornavn().equalsIgnoreCase(navn)) {
        return medlem;
      } else if (medlem.getNavn().equalsIgnoreCase(navn)) {
        return medlem;
      }
    }
    return null;
  }

  public Medlem findMedlemByMedlemsNummer(int medlemsNummer) {
    for (Medlem medlem : medlemmere) {
      if (medlem.getMedlemsNummer() == medlemsNummer) {
        return medlem;
      }
    }
    return null;
  }

  public void loadKonkurrenceSvømmerDisciplin() throws FileNotFoundException {
    FileHandler fileHandler = new FileHandler();
    medlemmere = fileHandler.loadKonkurrenceMedlemmereFraFil();

  }

  public void lavKonkurrenceSvømmerDisciplin() throws FileNotFoundException {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveKonkurrenceSvømmereToFile(medlemmere);
  }
  public void saveStævneDeltagere() throws FileNotFoundException {
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveStævnedeltagereToFile(medlemmere);
  }
  public void seStævneDeltagere() throws FileNotFoundException{

    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getStævneNavn()!=null){
        System.out.println(medlemmere.get(i).getNavn());
        System.out.println(medlemmere.get(i).getMedlemsNummer());
        System.out.println(medlemmere.get(i).getStævneNavn());
        System.out.println(medlemmere.get(i).getStævneLokation());
        System.out.println(medlemmere.get(i).getStævneDato());
        System.out.println(medlemmere.get(i).getStævneTid());

      }
    }
  }
  public void setDisciplin(String navn, Svømmedisciplin svømmedisciplin, LocalTime svømmeTid, LocalDate svømmeDato) {
    // find animal with this name
    Medlem medlem = findMedlemByName(navn);
    medlem.setSvømmedisciplin(svømmedisciplin);
    medlem.setSvømmeTid(svømmeTid);
    medlem.setSvømmeDato(svømmeDato);

  }

  public boolean checkOmMedlemEksistere(String navn) {
    Medlem medlem = findMedlemByName(navn);
    if (medlem == null) {
      return false;
    } else {
      return true;
    }
  }

  public void sorterBrystSvømningJunior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.BRYSTSVØMNING && medlemmere.get(i).getAlder() < 18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor BRYSTSVØMNING JUNIOR" + Color.RESET);
    sorterTop5(brystSvømning);
  }
  public void sorterBrystSvømningSenior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.BRYSTSVØMNING && medlemmere.get(i).getAlder() >= 18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor BRYSTSVØMNING" + Color.RESET);
    sorterTop5(brystSvømning);
  }

  public void sorterButterFlyJunior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.BUTTERFLY && medlemmere.get(i).getAlder()<18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor BUTTERFLY" + Color.RESET);
    sorterTop5(brystSvømning);
  }
  public void sorterButterFlySenior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.BUTTERFLY && medlemmere.get(i).getAlder()>=18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor BUTTERFLY" + Color.RESET);
    sorterTop5(brystSvømning);
  }
  public void sorterCrawlJunior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.CRAWL && medlemmere.get(i).getAlder()<18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor CRAWL" + Color.RESET);
    sorterTop5(brystSvømning);
  }
  public void sorterCrawlSenior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.CRAWL && medlemmere.get(i).getAlder() >= 18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor CRAWL" + Color.RESET);
    sorterTop5(brystSvømning);
  }

  public void sorterRygCrawlSenior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.RYGCRAWL && medlemmere.get(i).getAlder() >= 18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor RYGCRAWL" + Color.RESET);
    sorterTop5(brystSvømning);
  }
  public void sorterRygCrawlJunior() {

    ArrayList<LocalTime> brystSvømning = new ArrayList<>();
    for (int i = 0; i < medlemmere.size(); i++) {
      if (medlemmere.get(i).getSvømmeType() != SvømmeType.KONKURRENCESVØMMER) {

      } else if (medlemmere.get(i).getSvømmeType() == SvømmeType.KONKURRENCESVØMMER && medlemmere.get(i).getSvømmedisciplin() == Svømmedisciplin.RYGCRAWL && medlemmere.get(i).getAlder() < 18) {
        brystSvømning.add(medlemmere.get(i).getSvømmeTid());
      }
    }
    Collections.sort(brystSvømning);

    System.out.println(Color.WHITE_BOLD_BRIGHT + "Top 5 indenfor RYGCRAWL" + Color.RESET);
    sorterTop5(brystSvømning);
  }

  public void sorterTop5(ArrayList<LocalTime> brystSvømning) {
    for (int i = 0; i < brystSvømning.size(); i++) {
      for (int j = 0; j < medlemmere.size(); j++) {
        if (brystSvømning.get(i) == medlemmere.get(j).getSvømmeTid() && (i + 1) <= 5) {
          System.out.println(Color.WHITE_BOLD_BRIGHT + (i + 1) + ". Plads " + Color.RESET + "Medlem " + Color.WHITE_BRIGHT + medlemmere.get(j).medlemsNummer + Color.RESET + " " + medlemmere.get(j).getNavn() + " " + Color.GREEN_BOLD + brystSvømning.get(i) + Color.RESET);
        }
      }
    }
  }

  @Override
  public String toString() {
    return "MedlemmerBase{" +
        "medlemmere=" + medlemmere +
        ", restanceListe=" + restanceListe +
        '}';
  }
}
