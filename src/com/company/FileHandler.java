package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class FileHandler {
  public void saveMedlemmereToFile(ArrayList<Medlem> medlemmere) throws FileNotFoundException {
    PrintStream out = new PrintStream(("Medlem.csv"));

    for (Medlem medlem: medlemmere) {
      out.print(medlem.getNavn());
      out.print(";");
      out.print(medlem.getAlder());
      out.print(";");
      out.print(medlem.geteMail());
      out.print(";");
      out.print(medlem.getTelefonNr());
      out.print("\n");
    }

  }
  public ArrayList<Medlem> loadMedlemmereFraFil() throws FileNotFoundException {

    ArrayList<Medlem> medlemmere = new ArrayList<>();

    Scanner fileScanner = new Scanner(new File("Medlem.csv"));
    while (fileScanner.hasNextLine()) {
      String line = fileScanner.nextLine();

      Medlem medlem = readMedlemmere(line);

      medlemmere.add(medlem);
    }
    return medlemmere;
  }

  public Medlem readMedlemmere(String line){
    Scanner input = new Scanner(line).useDelimiter(";").useLocale(Locale.ENGLISH);

    String navn = input.next();
    int alder = input.nextInt();
    String eMail = input.next();
    int telefonNr = input.nextInt();

    Medlem medlem = new Medlem(navn, alder, eMail, telefonNr);
    return medlem;
  }
}
