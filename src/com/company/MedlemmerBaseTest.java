package com.company;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class MedlemmerBaseTest {

  @org.junit.jupiter.api.Test
  void getMedlemCount() {
    ArrayList<Medlem> medlmmere = new ArrayList<>();
    Medlem medlem1 = new Medlem("mikail", 18, "dede@gmail.com", 123123123,MedlemskabsStatus.AKTIV,false,SvømmeType.KONKURRENCESVØMMER);
    medlmmere.add(medlem1);
    assertEquals(1, medlmmere.size());

  }


  @org.junit.jupiter.api.Test
  void hentSamletBetaling() {
    ArrayList<Medlem> medlmmere = new ArrayList<>();
    Medlem medlem1 = new Medlem("ferhat baran",20,"ferhat@gmail.com",42568645,MedlemskabsStatus.AKTIV,false,SvømmeType.KONKURRENCESVØMMER);
    Medlem medlem2 = new Medlem("ferhat baran",20,"ferhat@gmail.com",42568645,MedlemskabsStatus.AKTIV,false,SvømmeType.KONKURRENCESVØMMER);
    Medlem medlem3 = new Medlem("ferhat baran",20,"ferhat@gmail.com",42568645,MedlemskabsStatus.AKTIV,false,SvømmeType.KONKURRENCESVØMMER);
    Medlem medlem4 = new Medlem("ferhat baran",11,"ferhat@gmail.com",42568645,MedlemskabsStatus.AKTIV,false,SvømmeType.KONKURRENCESVØMMER);

    medlmmere.add(medlem1);
    medlmmere.add(medlem2);
    medlmmere.add(medlem3);
    medlmmere.add(medlem4);

    int samletBetaling = 0;
    for (int i = 0; i < medlmmere.size(); i++) {
      Medlem medlemI = medlmmere.get(i);

      if (medlemI.isInRestance() == true) {
        System.out.println(Color.PURPLE_BOLD + medlemI.getFornavn() + Color.RESET + "Mangler betaling");
      } else if (!medlemI.getMedlemskabsStatus().equals(MedlemskabsStatus.AKTIV)) {
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
    System.out.println(samletBetaling);
    assertEquals(5800,samletBetaling);

  }

}