package com.digital.util;

import com.digital.model.Personne;

import java.util.Random;

public class Utility {

    public static String getRef(Personne pers){

        System.out.println("personne de getRef =>"+pers.getFirstname()+" "+pers.getReference());
        String username = String.valueOf(pers.getReference());
        String identifiant = "";
        if (username.length() == 8) {
            System.out.println("identifiant de 8 caracteres");
            identifiant = String.valueOf(pers.getReference());
        } else if (username.length()<8) {
            System.out.println("identifiant < 8 caracteres");
            identifiant = String.valueOf(pers.getReference()+10000000);
            System.out.println("nouvel identifiant de 8 caracteres"+ identifiant);
        }else if (username.length()>8){
            identifiant = String.valueOf(pers.getReference()+10000000);
        }
        return identifiant;
    }

    public static String getCodeReinit(int taille,String content) {
        char[] chars = content.toCharArray();
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < taille; i++) {
            char c = chars[random.nextInt(chars.length)];
            sb.append(c);
        }
        String output = sb.toString();
        System.out.println(output);
        return output.trim();
    }

    public static String isLocal(String phone) {
        phone = phone.replaceAll("\\s", "").replaceAll("\\)", "").replaceAll("\\(", "").trim();
        String provider = null;
        if (phone.length() > 13 || phone.length() < 8) {
            provider = null;
        } else if (phone.length() == 9) {
            provider = null;
        } else {
            if (phone.length() == 8 || phone.length() == 10) {
               // provider = contactHeadersMap.get(phone.substring(0, 2));
                provider = String.format("225%s",phone);
            } else if (phone.length() == 11) {
                if (!phone.startsWith("225")) {
                    provider = null;
                } else {
                    //provider = contactHeadersMap.get(phone.substring(3, 5));
                    provider = phone;
                }
            } else if (phone.length() == 12) {
                if (!phone.startsWith("+225")) {
                    provider = null;
                } else {
                    //provider = contactHeadersMap.get(phone.substring(4, 6));
                    provider = phone.substring(1);
                }
            } else if (phone.length() == 13) {
                if (!phone.startsWith("00225")) {
                    provider = null;
                } else {
                    provider = phone.substring(2);
                }
            }
        }
        return provider;
    }

    public static String removeRegex(String caractere, String regex){
        String result = "";

        if (caractere != null && !caractere.isEmpty()){
            String[] tailleEntree = caractere.split(regex);

            for (int i = 0 ; i < tailleEntree.length ; i++){
                result += tailleEntree[i];
            }
        }

        return result;
    }

    public static String changeRegex(String caractere, String found, String expected){
        String result = "";

        if (caractere != null && !caractere.isEmpty()){
            String[] tailleEntree = caractere.split(found);

            for (int i = 0 ; i < tailleEntree.length ; i++){
                result += tailleEntree[i].concat(expected);

                if (i == (tailleEntree.length - 1)) result += tailleEntree[i];
            }
        }
        return result;
    }
}
