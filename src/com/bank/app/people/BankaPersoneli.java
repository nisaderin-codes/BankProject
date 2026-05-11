package com.bank.app.people;

import java.util.ArrayList;
import java.util.Random;

// Kisi sınıfından miras aldı
public class BankaPersoneli extends Kisi {

    private String personelID;

    // Personelin sorumlu olduğu müşteri listesi
    private ArrayList<Musteri> musteriler;

    // Constructor: personel bilgileri alınır, ID otomatik üretilir
    public BankaPersoneli(String ad, String soyad, String email, long telefonNumarasi) {
        super(ad, soyad, email, telefonNumarasi);
        this.personelID = uretPersonelID(); // Rastgele personel ID oluştur
        this.musteriler = new ArrayList<>();
    }

    // 6 haneli rastgele personel ID üretir
    private String uretPersonelID() {
        Random rand = new Random();
        int id = 100000 + rand.nextInt(900000);
        return "P" + id;
    }

    // Personelin sorumluluğuna yeni müşteri ekler
    public void musteriEkle(Musteri musteri) {
        musteriler.add(musteri);
        System.out.println("Müşteri eklendi: " + musteri.getAd() + " " + musteri.getSoyad());
    }

    // ---- Getter ve Setter Metotları ----

    public String getPersonelID() {
        return personelID;
    }

    public void setPersonelID(String personelID) {
        this.personelID = personelID;
    }

    public ArrayList<Musteri> getMusteriler() {
        return musteriler;
    }

    public void setMusteriler(ArrayList<Musteri> musteriler) {
        this.musteriler = musteriler;
    }

    // Personel bilgilerini ekrana yazarken kullanılır
    @Override
    public String toString() {
        return "BankaPersoneli{" +
               "personelID='" + personelID + "'" +
               ", " + super.toString() +
               ", musteriSayisi=" + musteriler.size() +
               "}";
    }
}
