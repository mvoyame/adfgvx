/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.sectech.adfgvx;

import java.util.HashMap;

/**
 *
 * @author mael.voyame
 */
public class ADFGVX {
    private HashMap<String, HashMap<String, String>> tableau_substitution;

    

    public ADFGVX(){
    }
  
    public HashMap<String, HashMap<String, String>> getTableau_substitution() {
          return tableau_substitution;
    }


    public void init_tableau(){
      tableau_substitution = new HashMap<String, HashMap<String, String>>();

      HashMap<String, String> temp_A = new HashMap<String, String>();
      HashMap<String, String> temp_D = new HashMap<String, String>();
      HashMap<String, String> temp_F = new HashMap<String, String>();
      HashMap<String, String> temp_G = new HashMap<String, String>();
      HashMap<String, String> temp_V = new HashMap<String, String>();
      HashMap<String, String> temp_X = new HashMap<String, String>();

      temp_A.put("A", "c");
      temp_A.put("D", "1");
      temp_A.put("F", "o");
      temp_A.put("G", "f");
      temp_A.put("V", "w");
      temp_A.put("X", "j");

      tableau_substitution.put("A", temp_A);

      temp_D.put("A", "y");
      temp_D.put("D", "m");
      temp_D.put("F", "t");
      temp_D.put("G", "5");
      temp_D.put("V", "b");
      temp_D.put("X", "4");

      tableau_substitution.put("D", temp_D);

      temp_F.put("A", "i");
      temp_F.put("D", "7");
      temp_F.put("F", "a");
      temp_F.put("G", "2");
      temp_F.put("V", "8");
      temp_F.put("X", "s");

      tableau_substitution.put("F", temp_F);

      temp_G.put("A", "p");
      temp_G.put("D", "3");
      temp_G.put("F", "0");
      temp_G.put("G", "q");
      temp_G.put("V", "h");
      temp_G.put("X", "x");

      tableau_substitution.put("G", temp_G);

      temp_V.put("A", "k");
      temp_V.put("D", "e");
      temp_V.put("F", "u");
      temp_V.put("G", "l");
      temp_V.put("V", "6");
      temp_V.put("X", "d");

      tableau_substitution.put("V", temp_V);

      temp_X.put("A", "v");
      temp_X.put("D", "r");
      temp_X.put("F", "g");
      temp_X.put("G", "z");
      temp_X.put("V", "n");
      temp_X.put("X", "9");

      tableau_substitution.put("X", temp_X);
    }
}
