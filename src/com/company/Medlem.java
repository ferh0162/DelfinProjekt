package com.company;

import java.time.LocalDate;
import java.time.LocalTime;

public class Medlem {
  private static int nummer = 1000;
  protected int medlemsNummer;
  private String navn;
  private int alder;
  private String eMail;
  private int telefonNr;
  private boolean isInRestance;
  private MedlemskabsStatus medlemskabsStatus;
  private SvømmeType svømmeType;
  private Svømmedisciplin svømmedisciplin;
  private LocalTime svømmeTid;
  private LocalDate svømmeDato;
  private String stævneNavn;
  private String stævneLokation;
  private LocalDate stævneDato;
  private LocalTime stæveTid;


  public Medlem(String navn, int alder, String eMail, int telefonNr, MedlemskabsStatus medlemskabsStatus, boolean isInRestance, SvømmeType svømmeType) {
    this.navn = navn;
    this.alder = alder;
    this.eMail = eMail;
    this.telefonNr = telefonNr;
    this.isInRestance = isInRestance;
    this.medlemskabsStatus = medlemskabsStatus;
    this.svømmeType = svømmeType;
    this.medlemsNummer = nummer++;
  }

  public Medlem(int medlemsNummer, Svømmedisciplin svømmedisciplin, LocalTime svømmeTid, LocalDate svømmeDato) {
    this.medlemsNummer = medlemsNummer;
    this.svømmedisciplin = svømmedisciplin;
    this.svømmeTid = svømmeTid;
    this.svømmeDato = svømmeDato;
  }

  public Medlem(int medlemsnummer, String stævneNavn, LocalDate stævneDato, LocalTime stævneTid) {
    this.medlemsNummer = medlemsnummer;
    this.stævneNavn = stævneNavn;
    this.stævneDato = stævneDato;
    this.stæveTid = stævneTid;
  }

  public static int getNummer() {
    return nummer;
  }

  public static void setNummer(int nummer) {
    Medlem.nummer = nummer;
  }

  public Svømmedisciplin getSvømmedisciplin() {
    return svømmedisciplin;
  }

  public void setSvømmedisciplin(Svømmedisciplin svømmedisciplin) {
    this.svømmedisciplin = svømmedisciplin;
  }

  public LocalTime getSvømmeTid() {
    return svømmeTid;
  }

  public void setSvømmeTid(LocalTime svømmeTid) {
    this.svømmeTid = svømmeTid;
  }

  public LocalDate getSvømmeDato() {
    return svømmeDato;
  }

  public void setSvømmeDato(LocalDate svømmeDato) {
    this.svømmeDato = svømmeDato;
  }


  public String getNavn() {
    return navn;
  }

  public SvømmeType getSvømmeType() {
    return svømmeType;
  }

  public void setSvømmeType(SvømmeType svømmeType) {
    this.svømmeType = svømmeType;
  }

  public String getFornavn() {
    navn = navn.toLowerCase();

    String text = navn;

    String firstWord = "";
    String secondWord = "";
    int index = text.indexOf(' ');

    if (index > -1) { // Check if there is more than one word.

      firstWord = text.substring(0, index).trim(); // Extract first word.
      secondWord = text.substring(index + 1, text.length());

    } else {
      firstWord = text; // Text is the first word itself.
    }
    return firstWord;
  }

  public void setNavn(String navn) {
    this.navn = navn;
  }

  public int getAlder() {
    return alder;
  }

  public void setAlder(int alder) {
    this.alder = alder;
  }

  public String geteMail() {
    return eMail;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public int getTelefonNr() {
    return telefonNr;
  }

  public void setTelefonNr(int telefonNr) {
    this.telefonNr = telefonNr;
  }


  public MedlemskabsStatus getMedlemskabsStatus() {
    return medlemskabsStatus;
  }

  public void setMedlemskabsStatus(MedlemskabsStatus medlemskabsStatus) {
    this.medlemskabsStatus = medlemskabsStatus;
  }

  public boolean isInRestance() {
    return isInRestance;
  }

  public void setInRestance(boolean inRestance) {
    isInRestance = inRestance;
  }

  public int getMedlemsNummer() {
    return medlemsNummer;
  }

  public void setMedlemsNummer(int medlemsNummer) {
    this.medlemsNummer = medlemsNummer;
  }

  public String getStævneNavn() {
    return stævneNavn;
  }

  public void setStævneNavn(String stævneNavn) {
    this.stævneNavn = stævneNavn;
  }

  public String getStævneLokation() {
    return stævneLokation;
  }

  public void setStævneLokation(String stævneLokation) {
    this.stævneLokation = stævneLokation;
  }

  public LocalDate getStævneDato() {
    return stævneDato;
  }

  public void setStævneDato(LocalDate stævneDato) {
    this.stævneDato = stævneDato;
  }

  public LocalTime getStævneTid() {
    return stæveTid;
  }

  public void setStæveTid(LocalTime stæveTid) {
    this.stæveTid = stæveTid;
  }

  @Override
  public String toString() {
    return "Nr. " + medlemsNummer + " " +
        "Navn: " + navn + " "
        + " " + alder + " år " + " "
        + "Email: " + eMail + " "
        + "tlf nr: " + telefonNr + " " +
        "Svømme type: " + svømmeType + "\n";
  }

}
