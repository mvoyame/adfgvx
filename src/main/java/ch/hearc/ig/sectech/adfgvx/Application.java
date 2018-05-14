/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hearc.ig.sectech.adfgvx;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author mael.voyame
 */
public class Application {
    public static void main(String[] args) {
      //Déclaration des variables                  
      Cryptage cr = new Cryptage();
      
      /**
       * PARTIE 1 - Affichage pas à pas
       */
      //Partie cryptage
      //Message à crypter --> Texte Intermediaire
      ArrayList<String> TCI = cr.getTexteIntermediaireCrypte("ce papillon est beau");
      System.out.println("Message à chiffrer :\n");
      System.out.println("\t" + "Ce papillon est beau\n\n");
      System.out.println("Texte intermediaire du message :\n");
      System.out.println("\t" + TCI);
      System.out.println("\n\n");
      
      //Texte Intermediaire --> Tableau Ordonné
      ArrayList<ArrayList<String>> TO = cr.getTableOrderedCrypte(TCI, "humide");
      System.out.println("Tableau Ordonné avec le mot clé 'humide' :\n");
      cr.toStringTable(TO);
      System.out.println("\n\n");
      
      //Tableau Ordonné --> Tableau Chiffré
      ArrayList<ArrayList<String>> TC = cr.getTableCrypted(TO, "humide");
      System.out.println("Tableau Chiffré (transposition du tableau ordonné):\n");
      cr.toStringTable(TC);
      System.out.println("\n\n");
      
      //Tableau Chiffré --> Message Crypté
      String CT = cr.getTexteCrypted(TC);
      System.out.println("Message crypté :\n");
      System.out.println(CT);
      System.out.println("\n\n-------------------------------------------\n\n");
      
      //Partie décryptage
      //Message Crypté --> Tableau Chiffré
      ArrayList<ArrayList<String>> tableCrypteOrdonne = cr.getTableOrderedDecrypt(CT, "humide");
      System.out.println("Tableau Chiffré sur la base du message crypté :\n");
      cr.toStringTable(tableCrypteOrdonne);
      System.out.println("\n\n");
      
      //Tableau Chiffré --> Tableau Ordonné
      ArrayList<ArrayList<String>> tableDecrypte = cr.getTableDecrypted(tableCrypteOrdonne, "humide");
      System.out.println("Tableau Ordonné sur la base du tableau chiffré :\n");
      cr.toStringTable(tableDecrypte);
      System.out.println("\n\n");
      
      //Tableau Ordonné --> Tableau Texte Intermediaire
      ArrayList<ArrayList<String>> tableTextInter = cr.getTexteIntermediaireDecrypte(tableDecrypte);
      System.out.println("Tableau du texte intermediaire :\n");
      System.out.println(tableTextInter);
      System.out.println("\n\n");
      
      //Tableau Text Intermediaire --> Message Décrypté
      StringBuilder message = cr.getMessageDecrypte(tableTextInter);
      System.out.println("Message décrypté :\n");
      System.out.println("\t" + message.toString() + "\n\n");
      
      /**
       * PARTIE 2 - Saisie utilisateur
       */
      Scanner sc = new Scanner(System.in);
      System.out.println("Veuillez saisir le message que vous voulez crypter : ");
      String messageUser = sc.nextLine();
      System.out.println("\nVeuillez saisir le code secret pour chiffrer le message : ");
      String pwdUser = sc.nextLine();
      
      ArrayList<String> TCIUser = cr.getTexteIntermediaireCrypte(messageUser);
      ArrayList<ArrayList<String>> TOUser = cr.getTableOrderedCrypte(TCIUser, pwdUser);
      ArrayList<ArrayList<String>> TCUser = cr.getTableCrypted(TOUser, pwdUser);
      String CTUser = cr.getTexteCrypted(TCUser);
      System.out.println("\nVotre message est chiffré : " + CTUser);
      
      System.out.println("\n\nVeuillez saisir le message crypté pour le déchiffré : ");
      String messageChiffreUser = sc.nextLine();
      System.out.println("\nVeuillez saisir le code secret pour déchiffrer le message : ");
      String pwd2User = sc.nextLine();
      
      ArrayList<ArrayList<String>> tableCrypteOrdonneUser = cr.getTableOrderedDecrypt(messageChiffreUser, pwd2User);
      ArrayList<ArrayList<String>> tableDecrypteUser = cr.getTableDecrypted(tableCrypteOrdonneUser, pwd2User);
      ArrayList<ArrayList<String>> tableTextInterUser = cr.getTexteIntermediaireDecrypte(tableDecrypteUser);
      StringBuilder messageDecrypteUser = cr.getMessageDecrypte(tableTextInterUser);
      System.out.println("\n\nMessage décrypté :\n");
      System.out.println("\t" + messageDecrypteUser.toString());
  }
}
