package nl.hu.dp.ovchip.domein;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
@Table(name="Product")
public class Product {
    @Id
    @Column(name="product_nummer")
    private int productNummer;
    private String naam;
    private String beschrijving;
    private double prijs;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "ov_chipkaart_product",
            joinColumns = { @JoinColumn(name = "product_nummer") },
            inverseJoinColumns = { @JoinColumn(name = "kaart_nummer") }
    )
    private List<OVChipkaart> ovkaarten;
    public Product(){}
    public Product(int product_nummer, String naam, String beschrijving, double prijs) {
        this.productNummer = product_nummer;
        this.naam = naam;
        this.beschrijving = beschrijving;
        this.prijs = prijs;
        ovkaarten = new ArrayList<>();
    }

    public List<OVChipkaart> getOvkaarten() {
        return ovkaarten;
    }
    public void addOvkaart(OVChipkaart ovChipkaart) {
        ovkaarten.add(ovChipkaart);
    }
    public void removeOvkaart(OVChipkaart ovChipkaart) {
        ovkaarten.removeIf(ovChipkaart1 -> ovChipkaart1 == ovChipkaart);
    }

    public boolean contains(OVChipkaart ovkaart){
        for(OVChipkaart ovChipkaart : ovkaarten){
            if(ovChipkaart.getKaartnummer() == ovkaart.getKaartnummer()){
                return true;
            }
        }
        return false;
    }

    public int getProductNummer() {
        return productNummer;
    }

    public void setProductNummer(int product_nummer) {
        this.productNummer = product_nummer;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getBeschrijving() {
        return beschrijving;
    }

    public void setBeschrijving(String beschrijving) {
        this.beschrijving = beschrijving;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productNummer == product.productNummer &&
                Double.compare(product.prijs, prijs) == 0 &&
                naam.equals(product.naam) &&
                beschrijving.equals(product.beschrijving);
    }

    @Override
    public String toString() {
        return  "product: nummer = " + productNummer +
                ", naam = '" + naam + '\'' +
                ", beschrijving = '" + beschrijving + '\'' +
                ", prijs = " + prijs +
                "\nOvKaarten: " + ovkaarten;
    }
}
