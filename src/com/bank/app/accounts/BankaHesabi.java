package com.bank.app.accounts;

import java.util.Random;

// Tüm hesap türlerinin miras aldığı soyut temel sınıf
public abstract class BankaHesabi {

    // Her hesaba özgü rastgele IBAN ve mevcut bakiye
    private String iban;
    private double bakiye;

    // Constructor: başlangıç bakiyesi alınır, IBAN otomatik üretilir
    public BankaHesabi(double bakiye) {
        this.iban = uretIban();
        this.bakiye = bakiye;
    }

    // TR ile başlayan 26 karakterli rastgele IBAN üretir
    private String uretIban() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder("TR");
        for (int i = 0; i < 24; i++) {
            sb.append(rand.nextInt(10)); // Her basamak 0-9 arası rastgele
        }
        return sb.toString();
    }

    // ---- Getter ve Setter Metotları ----

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public double getBakiye() {
        return bakiye;
    }

    public void setBakiye(double bakiye) {
        this.bakiye = bakiye;
    }

    // Hesap bilgilerini döndürür; alt sınıflar override edebilir
    @Override
    public String toString() {
        return "BankaHesabi{iban='" + iban + "', bakiye=" + bakiye + "}";
    }
}

