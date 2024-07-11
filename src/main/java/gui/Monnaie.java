package gui;
import java.util.UUID;
public class Monnaie{
    private final int valeur;
    private final String uniqueID;
    public Monnaie(int valeur, Banque b){
        this.valeur = valeur;
        this.uniqueID = UUID.randomUUID().toString();
        b.getUniqueIDs().add(this.uniqueID);
    }
    public Monnaie(int valeur){
        this.valeur=valeur;
        this.uniqueID = UUID.randomUUID().toString();
    }
    public int getValeur(){
        return this.valeur;
    }
    public String getUniqueID(){
        return this.uniqueID;
    }
}