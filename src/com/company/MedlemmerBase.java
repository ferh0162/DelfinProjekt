package com.company;

import java.io.FileNotFoundException;
import java.util.ArrayList;

public class MedlemmerBase {
  private ArrayList<Medlem> medlemmere;

  public MedlemmerBase() {
    medlemmere = new ArrayList<>();
  }

  public void opretNyMedlem(String navn, int alder, String eMail, int telefonNr) {
    Medlem medlem = new Medlem(navn, alder, eMail, telefonNr);
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
      if (!medlemmere.get(i).medlemskabsStatus.equals(MedlemskabsStatus.AKTIV)) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "500 kr.");
        samletBetaling+=500;
      } else if (medlemmere.get(i).getAlder() < 18) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1000 kr.");
        samletBetaling+=1000;

      } else if (18 < medlemmere.get(i).getAlder() && medlemmere.get(i).getAlder() < 60) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1600 kr.");
        samletBetaling+=1600;

      } else if (60 < medlemmere.get(i).getAlder()) {
        System.out.println(Color.PURPLE_BOLD + medlemmere.get(i).getFornavn() + Color.RESET + " Betaler: " + "1200 kr.");
        samletBetaling+=1200;
      }
    }
    System.out.println();
    System.out.print("Samlede Betaling for alle medlemmere: ");
    return samletBetaling;
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

  private Medlem findMedlemByName(String navn) {
    //TODO: loop kun igennem fornavnet
    for (Medlem medlem : medlemmere) {
      if (medlem.getFornavn().equalsIgnoreCase(navn)) {
        return medlem;
      }
    }
    return null;
  }
}
