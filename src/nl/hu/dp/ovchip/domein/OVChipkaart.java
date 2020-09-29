package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
@Entity
@Table(name="OV_Chipkaart")
public class OVChipkaart {
    @Id
    @Column(name="kaart_nummer")
    private int kaartnummer;
    @Column(name="geldig_tot")
    private Calendar geldigTot;
    private int klasse;
    private double saldo;

    @ManyToOne
    @JoinColumn(name="reiziger_id")
    private Reiziger reiziger;
    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = { @JoinColumn(name = "kaart_nummer") },
            inverseJoinColumns = { @JoinColumn(name = "product_nummer") }
    )
    private List<Product> producten;

    public OVChipkaart(){}
    public OVChipkaart(int kaartnummer, Calendar geldigTot, int klasse, double saldo) {
        this.kaartnummer = kaartnummer;
        this.geldigTot = geldigTot;
        this.klasse = klasse;
        this.saldo = saldo;
        this.producten = new ArrayList<>();
    }

    public List<Product> getProducten() {
        return producten;
    }
    public void addProduct(Product product) {
        producten.add(product);
    }
    public void removeProduct(Product product) {
        producten.removeIf(product1 -> product1.getProductNummer() == product.getProductNummer());
    }

    public boolean contains(Product product){
        for(Product product1: producten){
            if(product.getProductNummer() == product1.getProductNummer()){
                return true;
            }
        }
        return false;
    }

    public int getKaartnummer() {
        return kaartnummer;
    }

    public void setKaartnummer(int kaartnummer) {
        this.kaartnummer = kaartnummer;
    }

    public Calendar getGeldigTot() {
        return geldigTot;
    }

    public void setGeldigTot(Calendar geldigTot) {
        this.geldigTot = geldigTot;
    }

    public int getKlasse() {
        return klasse;
    }

    public void setKlasse(int klasse) {
        this.klasse = klasse;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OVChipkaart)) return false;
        OVChipkaart ovkaart = (OVChipkaart) o;
        return kaartnummer == ovkaart.getKaartnummer() &&
                klasse == ovkaart.klasse;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    @Override
    public String toString() {
        String kaart = "kaartnummer: " + kaartnummer + ", geldig tot " + geldigTot.getTime().toString() + ", klasse: " + klasse + ", saldo: " + saldo  + " ";
        if(reiziger!=null){
           kaart +=  "en staat op naam van: " + reiziger.getNaam() + "\n";
        }
        String productbegin = "Deze kaart bevat de volgende producten: \n";
        String producten = "";

        for(Product product : this.producten){
            producten += "  " + product.getProductNummer() + ": " + product.getNaam() + ", " + product.getBeschrijving() + ", Prijs: " + product.getPrijs() + "\n";
        }
        if(producten == ""){
            producten = "   --: Deze kaart heeft geen producten. \n";
        }
        return kaart + productbegin + producten;

    }
}
