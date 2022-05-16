package com.company;

public class Medlem {

private String navn;
private int alder;
private String eMail;
private int telefonNr;
private Køn køn;
private SvømmerHold svømmerHold;
private MedlemskabsStatus medlemskabsStatus;
private Svømmedisciplin svømmedisciplin;

public Medlem (String navn, int alder, String eMail, int telefonNr, Køn køn, SvømmerHold svømmerHold, MedlemskabsStatus medlemskabsStatus, Svømmedisciplin svømmedisciplin){

  this.navn = navn;
      this.alder = alder;
          this.eMail = eMail;
              this.telefonNr = telefonNr;
              this.køn = køn;
              this.svømmerHold = svømmerHold;
              this.medlemskabsStatus = medlemskabsStatus;
              this. svømmedisciplin = svømmedisciplin;

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

  public SvømmerHold getSvømmerHold() {
    return svømmerHold;
  }

  public void setSvømmerHold(SvømmerHold svømmerHold) {
    this.svømmerHold = svømmerHold;
  }

  public MedlemskabsStatus getMedlemskabsStatus() {
    return medlemskabsStatus;
  }

  public void setMedlemskabsStatus(MedlemskabsStatus medlemskabsStatus) {
    this.medlemskabsStatus = medlemskabsStatus;
  }

  public Svømmedisciplin getSvømmedisciplin() {
    return svømmedisciplin;
  }

  public void setSvømmedisciplin(Svømmedisciplin svømmedisciplin) {
    this.svømmedisciplin = svømmedisciplin;
  }

}
