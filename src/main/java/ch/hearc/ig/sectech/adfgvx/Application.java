/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.sectech.adfgvx;

import java.util.ArrayList;

/**
 *
 * @author mael.voyame
 */
public class Application {
    public static void main(String[] args) {
        //Déclaration des variables
      // ADFGVX adfgvx = new ADFGVX();
      
      //Initialisation du table de substitution
      // adfgvx.init_tableau();
                  
      Cryptage cr = new Cryptage();
      
      //Partie cryptage
      ArrayList<String> TCI = cr.getTexteIntermediaireCrypte("Ce papillon est beau");
      System.out.println(TCI);
      
      ArrayList<ArrayList<String>> TO = cr.getTableOrderedCrypte(TCI, "humide");
      System.out.println(TO);
      cr.toStringTable(TO);
      
      ArrayList<ArrayList<String>> TC = cr.getTableCrypted(TO, "humide");
      System.out.println(TC);
      cr.toStringTable(TC);
      
      String CT = cr.getTexteCrypted(TC);
      System.out.println(CT);
      System.out.println("__________________________________________________");
      
      //Partie décryptage
      ArrayList<ArrayList<String>> tableCrypteOrdonne = cr.getTableOrderedDecrypt(CT, "humide");
      System.out.println(tableCrypteOrdonne);
      cr.toStringTable(tableCrypteOrdonne);
      
      ArrayList<ArrayList<String>> tableDecrypte = cr.getTableDecrypted(tableCrypteOrdonne, "humide");
      System.out.println(tableDecrypte);
      cr.toStringTable(tableDecrypte);
      
      ArrayList<ArrayList<String>> tableTextInter = cr.getTexteIntermediaireDecrypte(tableDecrypte);
      System.out.println(tableTextInter);
  }
}
