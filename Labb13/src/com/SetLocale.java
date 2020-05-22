/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author user
 */
public class SetLocale {
    public SetLocale(String language){
            String camp = "res.Messages";
            Locale.setDefault(Locale.forLanguageTag(language));
            Locale l =Locale.getDefault();
            ResourceBundle messages =ResourceBundle.getBundle(camp, l);
            System.out.println(messages.getString("locale.set"));
    }
}
