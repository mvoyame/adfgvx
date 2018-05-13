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
    public static ArrayList<String> getTexteIntermediaireCrypte (String text){
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
    public ArrayList<ArrayList<String>> getTableOrderedCrypte(ArrayList<String> TCI, String pwd){
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
        
        for (int i = 0; TCI.size()<37; i++) {
            TCI.add("F");
        }
        
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
    
    /**
     * Reçoit le tableau crypté (TC), le parcours
     * puis retourne le texte final
     * @param TC Tableau crypté
     * @return le texte final
     */
    public String getTexteCrypted(ArrayList<ArrayList<String>> TC){
        StringBuilder CT = new StringBuilder();
        
        // parcours des colonnes
        for (int i = 0; i < 6; i++) {
            
            // parcours des lignes
            for (int j = 1; j < 7; j++) {
                
                // Ajoute l'élément j qui se trouve dans la colonne i
                CT.append(TC.get(i).get(j));
            }
        }
        return CT.toString();
    }
    
    /**
     * Retourne le tableau crypté avec le mot clé trié alphabétiquement
     * @param CT Texte chiffré que l'on veut décrypter
     * @param pwd Mot clé utilisé pour crypter
     * @return Le tableau trié avec le mot clé
     */
    public ArrayList<ArrayList<String>> getTableOrderedDecrypt(String CT, String pwd){
        //Initialisation
        ArrayList<ArrayList<String>> tableOrdered = new ArrayList<>();
        ArrayList<String> motCle = new ArrayList<>();
        ArrayList<String> textChiffre = new ArrayList<>();
        ArrayList<String> lineTemp = new ArrayList<>();
        
        //Ajoute le mot clé dans une liste et le trie par ordre alphabétique
        for (int i = 0; i < 6; i++) {
            motCle.add(pwd.split("")[i]); 
        }
        Collections.sort(motCle);
        
        //Récupère le texte chiffré dans une liste pour la parcourir
        for (int i = 0; i < CT.length(); i++) {
            textChiffre.add(CT.split("")[i]); 
        }
        
        //Préparation du tableau crypté ordonné
        int indexMin = 0;
        int indexMax = 6;
        for (int i = 0; i < pwd.length(); i++){
            lineTemp.add(motCle.get(i));
            for(int j = indexMin; j < indexMax; j++){
                lineTemp.add(textChiffre.get(j));
            }
            indexMin = indexMin+6;
            indexMax = indexMax+6;
            tableOrdered.add(i , (ArrayList<String>) lineTemp.clone());
            lineTemp.clear();
        }
        
        return tableOrdered;
    }
    
    /**
     * Retourne le tableau décrypté avec le mot clé dans le bon ordre
     * @param tableauOrdonne Tableau trié alphabétiquement sur le mot clé
     * @param pwd Mot clé utilisé pour le cryptage
     * @return Retourne le tableau décrypté avec le mot clé dans le bon ordre
     */
    public ArrayList<ArrayList<String>> getTableDecrypted(ArrayList<ArrayList<String>> tableauOrdonne, String pwd){
        //Initialisation
        ArrayList<ArrayList<String>> tableDecrypted = new ArrayList<>();
        ArrayList<String> motCle = new ArrayList<>();
        
        //Ajoute le mot clé dans une ligne temporaire et le trie par ordre alphabétique
        for (int i = 0; i < pwd.length(); i++) {
            motCle.add(pwd.split("")[i]);
        }
        
        int compteur = 0;
        for(int i = 0; i < pwd.length(); i++){
            for(int j = 0; j < pwd.length(); j++){
                //Si dans le tableau ordonné on trouve la première lettre ("h" - motCle.get(compteur)) etc...
                // on ajoute cette liste dans le tableau décrypté
                if(tableauOrdonne.get(j).get(0).equals(motCle.get(compteur))){
                    tableDecrypted.add(tableauOrdonne.get(j));
                }
            }
            compteur++;
        }

        return tableDecrypted;
    }
    
    /**
     * Retourne le texte intermediaire sous forme d'ArrayList d'ArrayList
     * @param tableDecrypted Tableau décrypté (avec le mot clé dans le bon ordre)
     * @return Le texte intermediaire sous forme de liste
     */
    public ArrayList<ArrayList<String>> getTexteIntermediaireDecrypte(ArrayList<ArrayList<String>> tableDecrypted){
        //Initialisation
        ArrayList<ArrayList<String>> tableTextIntermediaire = new ArrayList<>();
        ArrayList<String> lineTemp = new ArrayList<>();
        
        int indexTabTextInter = 0;
        int indexTabDecrypted2 = 1;
        int indexTabDecrypted1 = 0;
        int checkClear = 0;
        //Parcours du tableau décrypté pour remplir le tableau avec le texte intermediaire
        //pour les 6 emplacements des lettres (chaque ArrayList compte 6 caractère ADFGVX)
        for(int i = 0; i < 6; i++){
            // j < 9 car 3*2 lineTemp + 2*Clear = 8
            //pour chaque ArrayList contenu dans l'ArrayList tableDecrypted
            //on récupère un caractère pour former des couples
            //ces couples seront utilisé pour le tableau de substitution
            for(int j = 0; j < 9; j++){
                if(checkClear == 2){
                    tableTextIntermediaire.add(indexTabTextInter, (ArrayList<String>) lineTemp.clone());
                    indexTabTextInter++;
                    lineTemp.clear();
                    checkClear = 0;
                }else{
                    lineTemp.add(tableDecrypted.get(indexTabDecrypted1).get(indexTabDecrypted2));
                    indexTabDecrypted1++;
                    checkClear++;
                }
            }
            indexTabDecrypted1 = 0;
            indexTabDecrypted2++;
        }
        
        return tableTextIntermediaire;
    }
    
    
    /**
     * Affiche un pseudo tableau
     * @param TO Un tableau de liste
     */
    public void toStringTable(ArrayList<ArrayList<String>> TO){
        
        // parcours des lignes
        for (int i = 0; i < 7; i++) {
            System.out.print("|");
            
            // parcours des colonnes
            for (int j = 0; j < 6; j++) {
                
                // Affiche l'élément i qui se trouve dans la colonne j
                System.out.print(TO.get(j).get(i) + "|");
            }
            System.out.print("\n------------\n");
        }
    }
}
