/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.sectech.adfgvx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author bastien.mittempe
 */
public class Cryptage extends ADFGVX {
    static ADFGVX tab = new ADFGVX();
    
    /**
     * Cette méthode va scinder le texte à chiffrer dans un tableau (une case un caractère),
     * elle va ensuite parcourir le tableau de substitution et tester la correspondance des valeurs.
     * Quand le test passe, ajoute le couple de clef au tableau de retour.
     * @param text Texte à chiffrer
     * @return ArrayList de string correspondant au texte chiffré intermédiaire
     */
    public static ArrayList<String> getTexteIntermediaire (String text){
        // Récupération du tableau de substitution
        ArrayList<String> tabTI = new ArrayList<>();
        HashMap<String, HashMap<String, String>> tabSub = new HashMap<>(tab.getTableau_substitution());
        
        // Sépaaration du texte en tableau de String[]
        String[] tabSplit = text.split("");
        
        // Parcours du tableau de String[]
        for (int i = 0; i < tabSplit.length; i++) {
            String current = tabSplit[i];
            
            // Parcours du tableau de substitution
            for (Map.Entry<String, HashMap<String, String>> en : tabSub.entrySet()) {
            Object key = en.getKey();
            Object value = en.getValue();
            
            // Récupération de la ligne du tableau de substitution
            HashMap<String, String> tabSubLine = new HashMap<>(tabSub.get(key));
            
                // Parcours de la ligne du tableau de substitution
                for (Map.Entry<String, String> entry : tabSubLine.entrySet()) {
                    String key1 = entry.getKey();
                    String value1 = entry.getValue();
                    
                    // Test de la valeur du String courrent et de la valeur de la case
                    if(value1.equals(current)){
                        
                        // Ajout du couple de clefs correspondant dans le tableau de retour
                        tabTI.add((String) key);
                        tabTI.add(key1);                      
                    } 
                }               
            }     
        }        
        return tabTI;
    }
    
    /**
     * Reçoit le TCI et le mot de passe, et remplis le tableau 
     * @param TCI Texte chiffré intermédiaire à passer dans le tableau
     * @param pwd Mot de passe pour le cryptage
     * @return 
     */
    public ArrayList<ArrayList<String>> getTableOrdered(ArrayList<String> TCI, String pwd){
        // Déclaration des lignes du tableau
        ArrayList<ArrayList<String>> tableOrdered = new ArrayList<>();
        // ArrayList<String> tableLine0 = new ArrayList<>();
        ArrayList<String> tableLine1 = new ArrayList<>();
        ArrayList<String> tableLine2 = new ArrayList<>();
        ArrayList<String> tableLine3 = new ArrayList<>();
        ArrayList<String> tableLine4 = new ArrayList<>();
        ArrayList<String> tableLine5 = new ArrayList<>();
        ArrayList<String> tableLine6 = new ArrayList<>();
        
        // Séparation du mot de passe
        String[] tabSplit = pwd.split("");
        
        /*
        // Ajout des lettres dans les lignes
        for (int i = 0; i < tabSplit.length; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine0.add(tabSplit[i]);
            }
        }
        for (int i = 0; i < 6; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine1.add(TCI.get(i));
            }
        }
        for (int i = 6; i < 12; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine2.add(TCI.get(i));
            }
            
        }
        for (int i = 12; i < 18; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine3.add(TCI.get(i));
            }
            
        }
        for (int i = 18; i < 24; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine4.add(TCI.get(i));
            }
            
        }
        for (int i = 24; i < 30; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine5.add(TCI.get(i));
            }
            
        }
        for (int i = 30; i < 36; i++) {
            if(!TCI.get(i).isEmpty()){
                tableLine6.add(TCI.get(i));
            }  
        }
        */
        // Version 2 : En colonne
        tableLine1.add(tabSplit[0]);
        tableLine2.add(tabSplit[1]);
        tableLine3.add(tabSplit[2]);
        tableLine4.add(tabSplit[3]);
        tableLine5.add(tabSplit[4]);
        tableLine6.add(tabSplit[5]);
        for (int i = 0; i < 36; i = i+6) {
            if(!TCI.get(i).isEmpty()){
                tableLine1.add(TCI.get(i));
                tableLine2.add(TCI.get(i+1));
                tableLine3.add(TCI.get(i+2));
                tableLine4.add(TCI.get(i+3));
                tableLine5.add(TCI.get(i+4));
                tableLine6.add(TCI.get(i+5));
            }
        }
        
        
        // Ajout des ligne dans le tableau pour le retour
        // tableOrdered.add(tableLine0);
        tableOrdered.add(tableLine1);
        tableOrdered.add(tableLine2);
        tableOrdered.add(tableLine3);
        tableOrdered.add(tableLine4);
        tableOrdered.add(tableLine5);
        tableOrdered.add(tableLine6);
        
        return tableOrdered;
    }
    
    /**
     * Prend le tableau ordonné et le mot de passe afin de les parcourir,
     * puis tester la valeur de la lettre d'en-tête (mot de passe), puis ajoute la colonne à son nouvel emplacement.
     * @param TO Tableau ordonné 
     * @param pwd Mot de passe
     * @return Tableau crypté
     */
    public ArrayList<ArrayList<String>> getTableCrypted(ArrayList<ArrayList<String>> TO, String pwd){
        // Déclaration des variables 
        ArrayList<ArrayList<String>> tableCrypted = new ArrayList<>();
        ArrayList<String> lineTemp = new ArrayList<>();
        String lp1, lp2, lp3, lp4, lp5, lp6;
        
        // Ajout de ligne vide
        for (int i = 0; i < 6; i++) {
            tableCrypted.add(i, null);
        }
        
        // Séparation du mot de passe et tri alphabethique
        ArrayList tabSplit = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            tabSplit.add(pwd.split("")[i]); 
        }
        Collections.sort(tabSplit);
        
        // Ajout des lettres du mot de passe dans des variables
        lp1 = (String) tabSplit.get(0);
        lp2 = (String) tabSplit.get(1) ;
        lp3 = (String) tabSplit.get(2) ;
        lp4 = (String) tabSplit.get(3) ;
        lp5 = (String) tabSplit.get(4) ; 
        lp6 = (String) tabSplit.get(5) ;
        
        // Parcours de la table ordonnée (TO)
        for (int i = 0; i < TO.size(); i++) {
            lineTemp = (TO.get(i));
            String currentL = lineTemp.get(0);
            
            // Test des valeurs de la clef
            if(currentL.equals(lp1)){
                // Remove la ligne null
                tableCrypted.remove(0);
                // Ajoute un clone de la colonne en cours
                tableCrypted.add(0, (ArrayList<String>) lineTemp.clone());
            }
            if(currentL.equals(lp2)){
                tableCrypted.remove(1);
                tableCrypted.add(1, (ArrayList<String>) lineTemp.clone());
            }
            if(currentL.equals(lp3)){
                tableCrypted.remove(2);
                tableCrypted.add(2, (ArrayList<String>) lineTemp.clone()); 
            }
            if(currentL.equals(lp4)){
                tableCrypted.remove(3);
                tableCrypted.add(3, (ArrayList<String>) lineTemp.clone());
            }
            if(currentL.equals(lp5)){
                tableCrypted.remove(4);
                tableCrypted.add(4, (ArrayList<String>) lineTemp.clone());
            }
            if(currentL.equals(lp6)){
                tableCrypted.remove(5);
                tableCrypted.add(5, (ArrayList<String>) lineTemp.clone()); 
            }
        }
           
        return tableCrypted;
    }
    
}
