package com.challengers.services;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Malika(mxp134930) on 12/6/2015.
 */
public class KeyGenerator {

    public static String generateKey(Long id, LocalDate date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return id + "_" + date.format(formatter);
    }

}
