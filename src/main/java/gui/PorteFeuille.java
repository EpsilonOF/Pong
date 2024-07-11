package gui;

import java.util.LinkedList;
import java.util.UUID;

public class PorteFeuille{
    private final String IDPorteFeuille;
    private final Banque b;
    private int total;
    private LinkedList<Monnaie> monnaies;
    private LinkedList<Monnaie> faussesMonnaies;

    public PorteFeuille(Banque b){
        this.total = 0;
        this.monnaies = new LinkedList<Monnaie>();
        this.b = b;
        this.IDPorteFeuille = UUID.randomUUID().toString();
    }
    public int getTotal(){
        return this.total;
    }
    public LinkedList<Monnaie> getMonnaies(){
        return this.monnaies;
    }
    public void ajouter(Monnaie valeur){
        this.total += valeur.getValeur();
        try{
            this.monnaies.add(this.newMonnaie(valeur.getValeur()));
        }catch(ExceptionImpossibleNewMonnaie e){
            //afficher un msg sur la vue pour dire banque vide
        }
    }
    public void retirer(Monnaie valeur)throws ExceptionNotEnoughMoney{
        if(this.total >= -valeur.getValeur()){
            this.total += valeur.getValeur();
        }else{
            throw new ExceptionNotEnoughMoney();
        }
    }
    public boolean transferer(Monnaie valeur, PorteFeuille p){
        try{
            this.retirer(valeur);
            p.ajouter(valeur);
            return true;
        }catch(ExceptionNotEnoughMoney e){
            return false;
        }
    }
    public boolean transaction(Monnaie valeur){
        if(valeur.getValeur() >0){
            ajouter(valeur); return true;
        }else{ 
            try{
                retirer(valeur);
                return true;
            }catch(ExceptionNotEnoughMoney e){
                return false;
            }
        }
    }
    public Monnaie newMonnaie(int valeur)throws ExceptionImpossibleNewMonnaie{
        if(valeur < 1 || this.b.getUniqueIDs().size() > this.b.getQuantiteMax()){
            throw new ExceptionImpossibleNewMonnaie();
        }else{
            return new Monnaie(valeur, b);
        }
    }
    public String getIDPorteFeuille(){
        return this.IDPorteFeuille;
    }
    public void convertir(Monnaie m){
        b.getUniqueIDs().forEach((s) ->{
            if(s.equals(m.getUniqueID())){
                this.faussesMonnaies.add(new Monnaie(m.getValeur()*2)); this.monnaies.remove(m);
            }else{
                this.monnaies.add(new Monnaie(m.getValeur()/4, this.b)); this.faussesMonnaies.remove(m);
            }
        });
    }
}