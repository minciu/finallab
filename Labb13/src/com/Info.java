/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.text.DateFormatSymbols;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Arrays;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */
public class Info {
    public Info() {
        String camp = "res.Messages";
        Locale l = Locale.getDefault();
        ResourceBundle messages = ResourceBundle.getBundle(camp, l);
        System.out.println(messages.getString("info"));
        System.out.println(messages.getString("Country")+" "+l.getDisplayCountry());
        System.out.println(messages.getString("Language")+" "+l.getDisplayLanguage());
        System.out.println(messages.getString("Currency")+" "+Currency.getInstance(l));
        String[] weekDays = new DateFormatSymbols(l).getWeekdays();
        System.out.println(messages.getString("Week_days")+" "+Arrays.toString(weekDays));
        String[] months=new DateFormatSymbols(l).getMonths();
        System.out.println(messages.getString("Months")+" "+Arrays.toString(months));
        LocalDateTime today=LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter
                .ofLocalizedDate(FormatStyle.FULL)
                .withLocale(l);
        System.out.println(messages.getString("Today")+" "+today.format(formatter));
    }
}
