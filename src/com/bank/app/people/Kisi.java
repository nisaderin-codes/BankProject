package com.bank.app.people;

// Banka personeli ve müşterilerin ortak özelliklerinin oldugu base class
public class Kisi {

    // Kişinin temel bilgileri
    private String ad;
    private String soyad;
    private String email;
    private long telefonNumarasi;

    // Constructor: tüm özellikleri parametre olarak alır
    public Kisi(String ad, String soyad, String email, long telefonNumarasi) {
        this.ad = ad;
        this.soyad = soyad;
        this.email = email;
        this.telefonNumarasi = telefonNumarasi;
    }

    // ---- Getter ve Setter Metotları ----

    public String getAd() {
        return ad;
    }

    public void setAd(String ad) {
        this.ad = ad;
    }

    public String getSoyad() {
        return soyad;
    }

    public void setSoyad(String soyad) {
        this.soyad = soyad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefonNumarasi() {
        return telefonNumarasi;
    }

    public void setTelefonNumarasi(long telefonNumarasi) {
        this.telefonNumarasi = telefonNumarasi;
    }

    // Kişi bilgilerini okunabilir biçimde döndürür
    @Override
    public String toString() {
        return "Ad: " + ad + " " + soyad +
               " | Email: " + email +
               " | Tel: " + telefonNumarasi;
    }
}
