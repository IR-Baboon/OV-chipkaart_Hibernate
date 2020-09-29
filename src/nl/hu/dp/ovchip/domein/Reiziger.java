package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name="reiziger")
public class Reiziger {
    @Id
    @Column(name="reiziger_id")
    private int reizigerId;

    private String voorletters;
    private String tussenvoegsel;
    private String achternaam;
    private Calendar geboortedatum;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reiziger_id")
    private Adres adres;

    @OneToMany
    @JoinColumn(name="reiziger_id")
    private List<OVChipkaart> ovkaarten;

    public Reiziger(){}

    public Reiziger(int id, String voorletters, String tussenvoegsel, String achternaam, Calendar geboortedatum) {
        this.reizigerId = id;
        this.voorletters = voorletters;
        this.tussenvoegsel = tussenvoegsel;
        this.achternaam = achternaam;
        this.geboortedatum = geboortedatum;
        this.ovkaarten = new ArrayList<>();
    }

    public List<OVChipkaart> getOvkaarten() {
        return ovkaarten;
    }

    public void addCard(OVChipkaart ovkaart){
        this.ovkaarten.add(ovkaart);
    };
    public void removeCard(OVChipkaart ovkaart){
        this.ovkaarten.remove(ovkaart);
    };

    public Adres getAdres() {
        return this.adres;
    }

    public void setAdres(Adres adres) {
        this.adres = adres;
    }

    public String getVoorletters() {
        return voorletters;
    }

    public void setVoorletters(String voorletters) {
        this.voorletters = voorletters;
    }

    public String getTussenvoegsel() {
        return tussenvoegsel;
    }

    public void setTussenvoegsel(String tussenvoegsel) {
        this.tussenvoegsel = tussenvoegsel;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        this.achternaam = achternaam;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public void setReizigerId(int reizigerId) {
        this.reizigerId = reizigerId;
    }

    public Calendar getGeboortedatum() {
        return geboortedatum;
    }

    public void setGeboortedatum(Calendar geboortedatum) {
        this.geboortedatum = geboortedatum;
    }

    public String getNaam(){
        return this.voorletters + (this.tussenvoegsel != null? " " + this.tussenvoegsel + " " : " ") + this.achternaam;
    }

    public String toString(){
        String reiziger = "Reiziger: #" + getReizigerId() + ", naam: " + getNaam() + ", geboren op: " + getGeboortedatum().getTime().toString() + ". ";
        if(adres != null){
            reiziger = reiziger + "Woont op: " + getAdres().getStraat() + " " + getAdres().getHuisnummer() + ", " + getAdres().getPostcode() + " te " + getAdres().getWoonplaats();
        }
        String ovkaartBegin = "\nReiziger " + getNaam() + " bezit de volgende OV-Chip kaarten: \n";
        String kaarten = "";
        if(ovkaarten.size() > 0){
            for(OVChipkaart kaart : ovkaarten){
                kaarten += "    kaart " + kaart.getKaartnummer() + ": reist in klasse: " + kaart.getKlasse() + ", heeft saldo: " + kaart.getSaldo() + " euro en is geldig tot: " + kaart.getGeldigTot().getTime().toString() + ".\n";
                String producten = "";
                if(kaart.getProducten().size() > 0){
                    for(Product product : kaart.getProducten()){
                        producten += "      " + product.getProductNummer() + ": " + product.getNaam() + ", " + product.getBeschrijving() + ". Prijs: " + product.getPrijs() + "\n";
                    }
                }
                if(producten == ""){
                    producten = "       --: Deze kaart heeft geen producten. \n";
                }
                kaarten += producten;
            }
        }

        if(kaarten == ""){
            kaarten = "     --: Reiziger heeft nog geen kaarten in bezit. \n";
        }
        return reiziger + ovkaartBegin + kaarten;

//        return reiziger;
    }
}
