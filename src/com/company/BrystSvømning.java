package com.company;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class BrystSvømning {
  private LocalTime localTime;
  private String navn;

  public BrystSvømning(LocalTime localTime, String navn) {
    this.localTime = localTime;
    this.navn = navn;
  }

  @Override
  public String toString() {
    return navn+" "+ localTime;

  }
}
