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
      ArrayList<String> test = cr.getTexteIntermediaire("Ce papillon est beau donc ça passe");
      System.out.println(test);
      System.out.println(cr.getTableOrdered(test, "humide"));
  }
}
