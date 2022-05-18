package com.company;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

public class MedlemmerBase {
  private ArrayList<Medlem> medlemmere;
  private ArrayList<Medlem> restanceListe;
  private ArrayList<KonkurrenceMedlem> konkurreMedlemmere = new ArrayList<>();

  public MedlemmerBase() {
    medlemmere = new ArrayList<>();
    restanceListe = new ArrayList<>();
  }

  public void opretNyMedlem(String navn, int alder, String eMail, int telefonNr, MedlemskabsStatus medlemskabsStatus, boolean isInRestance, SvømmeType svømmeType) {
    Medlem medlem = new Medlem(navn, alder, eMail, telefonNr, medlemskabsStatus, isInRestance, svømmeType);
    medlem.setSvømmeType(svømmeType);
    medlemmere.add(medlem);
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
    for (int i = 0; i < medlemmere.size(); i++) {
      if (!medlemmere.get(i).medlemskabsStatus.equals(MedlemskabsStatus.AKTIV)) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "500 kr.");
      } else if (medlemmere.get(i).getAlder() < 18) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1000 kr.");

      } else if (18 < medlemmere.get(i).getAlder() && medlemmere.get(i).getAlder() < 60) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1600 kr.");

      } else if (60 < medlemmere.get(i).getAlder()) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1200 kr.");
      }
    }
    System.out.println(medlemmere);
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
        System.out.println(Color.RED + medlemI.getFornavn() + Color.RESET + " er i restance");

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
      if (medlem.getAlder()<18){
        System.out.println(medlem.getNavn()+" Er i Juniorholdet");
      } else if (medlem.getAlder()>= 18){
        System.out.println(medlem.getNavn()+" Er i Seniorholdet");
      }
    }
    }
  public void seJuniorHold() {
    System.out.println(Color.PURPLE_BOLD+"Junior holdet:"+Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlem = medlemmere.get(i);
      if (medlem.getAlder() < 18 && medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER) {
        System.out.println(medlem.getNavn());

      }
    }
  }
  public void seSeniorHold() {
    System.out.println(Color.PURPLE_BOLD+"Senior Holdet" + Color.RESET);
    for (int i = 0; i < medlemmere.size(); i++) {
      Medlem medlem = medlemmere.get(i);
      if (medlem.getAlder() >= 18 && medlem.getSvømmeType() == SvømmeType.KONKURRENCESVØMMER) {
        System.out.println(medlem.getNavn());

      }
    }
  }
  private Medlem findMedlemByName(String navn) {
    for (Medlem medlem : medlemmere) {
      if (medlem.getFornavn().equalsIgnoreCase(navn)) {
        return medlem;
      }
    }
    return null;
  }

  public void lavKonkurrenceSvømmerDisciplin() throws FileNotFoundException{
    FileHandler fileHandler = new FileHandler();
    fileHandler.saveKonkurrenceSvømmereToFile(medlemmere);
  }
  public void
  setDisciplin(String navn, Svømmedisciplin svømmedisciplin, LocalTime svømmeTid, LocalDate svømmeDato) {
    // find animal with this name
    Medlem medlem = findMedlemByName(navn);
    if (medlem == null) {
      System.out.println("Medlem findes ikke");
    } else {
      System.out.println("Medlem findes");
      medlem.setSvømmedisciplin(svømmedisciplin);
      medlem.setSvømmeTid(svømmeTid);
      medlem.setSvømmeDato(svømmeDato);

    }
  }
public boolean checkOmMedlemEksistere(String navn) {
  Medlem medlem = findMedlemByName(navn);
  if (medlem == null) {
    System.out.println(Color.RED+"Medlem findes ikke"+Color.RESET);
    return false;
  } else {
    System.out.println(Color.GREEN_BOLD+"Medlemmet"+ medlem.getNavn()+"er fundet"+Color.RESET);
    return true;
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
