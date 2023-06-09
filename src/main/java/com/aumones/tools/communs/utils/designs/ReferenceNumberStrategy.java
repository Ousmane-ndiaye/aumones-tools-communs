package com.aumones.tools.communs.utils.designs;

import java.time.LocalDate;
import java.util.Random;

public class ReferenceNumberStrategy {
  public static String generateReferenceNumber(IReferenceNumberStrategy repository, String initial) {
    int m = (int) Math.pow(10, 6 - 1);
    int randomInt = m + new Random().nextInt(9 * m);
    String year = String.valueOf((LocalDate.now()).getYear()).substring(4 - 2);

    String registrationNumber = String.format("%s%s%s", initial, year, randomInt);

    if (repository.existsByReferenceNumber(registrationNumber)) {
      return generateReferenceNumber(repository, initial);
    }

    return registrationNumber;
  }
}
