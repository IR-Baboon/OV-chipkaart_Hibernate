package nl.hu.dp.ovchip.domein;

import javax.persistence.*;

@Entity
@Table(name="adres")
public class Adres {
    @Id
    @Column(name="adres_id")
    private int adresId;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "reiziger_id")
    private Reiziger reiziger;
    public Adres(){}
    public Adres(int adres_id, String postcode, String huisnummer, String straat, String woonplaats) {
        this.adresId = adres_id;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
    }

    public int getAdres_id() {
        return adresId;
    }

    public Reiziger getReiziger() {
        return reiziger;
    }

    public void setReiziger(Reiziger reiziger) {
        this.reiziger = reiziger;
    }

    public void setAdres_id(int adres_id) {
        this.adresId = adres_id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public void setHuisnummer(String huisnummer) {
        this.huisnummer = huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public void setStraat(String straat) {
        this.straat = straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public void setWoonplaats(String woonplaats) {
        this.woonplaats = woonplaats;
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();

        str.append("Adres #");
        str.append(getAdres_id());
        str.append(" ");
        str.append(getStraat());
        str.append(getHuisnummer());
        str.append(", ");
        str.append(getPostcode());
        str.append(" ");
        str.append(getWoonplaats());

        if(reiziger != null){
            str.append(" en wordt bewoond door: ");
            str.append(getReiziger().getNaam());
            str.append(" en is geboren op: ");
            str.append(getReiziger().getGeboortedatum().getTime().toString());
            str.append(".");
        }

        return str.toString();
    }
}
