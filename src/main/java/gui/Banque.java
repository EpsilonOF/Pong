package gui;

import java.util.LinkedList;
import java.util.Random;

public class Banque{
    private long pourcentageControl;
    private final int quantiteMax;
    private boolean verifie;
    private LinkedList<String> uniqueIDs;
    public Banque(){
        this.quantiteMax = 10000;
        this.uniqueIDs = new LinkedList<String>();
        this.verifie = false;
        this.pourcentageControl = 0;
    }
    public int getQuantiteMax(){
        return this.quantiteMax;
    }
    public LinkedList<String> getUniqueIDs(){
        return this.uniqueIDs;
    }
    // permet de payer un montant à un porte feuille
    public boolean payement(Monnaie valeur, PorteFeuille p1){
       if(p1.transaction(valeur)) return true;
       else return false; 
    }
    //controle de Monnaie si elle est valide
    public void compare(Monnaie m){
        uniqueIDs.forEach((s) ->{
           if(s.equals(m.getUniqueID())) this.verifie = true;
        });
    }
    private void nbControl(){
        Random rd = new Random();
        int random = rd.nextInt(this.quantiteMax)+1;
        this.pourcentageControl =91-(((this.quantiteMax-this.uniqueIDs.size())*90)/random*this.quantiteMax);
    }
    public boolean control(Monnaie m)throws ExceptionNotUniqueID{
        this.nbControl();
        Random rd = new Random();
        long n = rd.nextInt(100000)*100/100000;
        if(this.pourcentageControl > n){
            this.compare(m);
            if(this.verifie) throw new ExceptionNotUniqueID();
        }
        return true;
    }
}