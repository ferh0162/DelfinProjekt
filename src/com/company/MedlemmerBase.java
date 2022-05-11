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

  public Iterable<Medlem> getAllAnimals() {
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
  public boolean sletMedlem(String navn) {
    // find animal with this name
    Medlem medlem = findMedlemByName(navn);
    if(medlem == null) {
      return false;
    } else {
      medlemmere.remove(medlem);
      return true;
    }
  }
  private Medlem findMedlemByName(String navn) {
    //TODO: loop kun igennem fornavnet
    for(Medlem medlem : medlemmere) {
      if(medlem.getNavn().equalsIgnoreCase(navn)) {
        return medlem;
      }
    }
    return null;
  }
}
