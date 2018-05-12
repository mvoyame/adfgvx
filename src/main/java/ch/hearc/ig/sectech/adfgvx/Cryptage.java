/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.sectech.adfgvx;

import java.util.ArrayList;
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
    
}
