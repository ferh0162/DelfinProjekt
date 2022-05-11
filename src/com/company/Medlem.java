package com.company;

//TODO: lave konkurrence svømmer til en arveklasse, da den skal have flere attributter som svømmedisciplin
public class Medlem {

  protected String navn;
  protected int alder;
  protected String eMail;
  protected int telefonNr;
  protected Køn køn;
  protected boolean isInRestance;
  protected MedlemskabsStatus medlemskabsStatus;


  public Medlem(String navn, int alder, String eMail, int telefonNr) {

    this.navn = navn;
    this.alder = alder;
    this.eMail = eMail;
    this.telefonNr = telefonNr;
    medlemskabsStatus = MedlemskabsStatus.AKTIV;
    isInRestance = false;
  }


  public String getNavn() {
    return navn;
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

  public Køn getKøn() {
    return køn;
  }

  public void setKøn(Køn køn) {
    this.køn = køn;
  }

  public MedlemskabsStatus getMedlemskabsStatus() {
    return medlemskabsStatus;
  }

  public void setMedlemskabsStatus(MedlemskabsStatus medlemskabsStatus) {
    this.medlemskabsStatus = medlemskabsStatus;
  }

  @Override
  public String toString() {
    return "Navn: "+navn +" "
        + " " + alder + " år "+" "
        + "Email: " + eMail+" "
        + "tlf nr: " + telefonNr +" "+
        "Køn: " + køn +" "+
        "Restance: " + isInRestance +" "+
        "MedlemskabsStatus " + medlemskabsStatus+"\n";
  }
}
