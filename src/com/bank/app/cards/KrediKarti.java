package com.bank.app.cards;

import java.util.Random;

// Müşterilere tanımlanabilecek kredi kartı sınıfı
public class KrediKarti {

	//fieldlar:
    private String kartNumarasi;
    private double limit;
    private double guncelBorc;

    private double kullanilabilirLimit;

    // Constructor: limit ve başlangıç borcu alınır, numara otomatik üretilir
    public KrediKarti(double limit, double guncelBorc) {
        this.kartNumarasi = uretKartNumarasi();
        this.limit = limit;
        this.guncelBorc = guncelBorc;
        this.kullanilabilirLimit = limit - guncelBorc; // Kllanılabilir limit hesaplanır
    }

    // 16 haneli rastgele kredi kartı numarası üretir
    private String uretKartNumarasi() {
        Random rand = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            sb.append(rand.nextInt(10));
            if (i == 3 || i == 7 || i == 11) sb.append("-"); // Gruplar arasına tire ekle
        }
        return sb.toString();
    }

    // Kullanılabilir limiti güncel borç bilgisine göre hesaplar ve günceller
    public void kullanilabilirLimitGuncelle() {
        this.kullanilabilirLimit = this.limit - this.guncelBorc;
    }

    // ---- Getter ve Setter Metotları ----

    public String getKartNumarasi() {
        return kartNumarasi;
    }

    public void setKartNumarasi(String kartNumarasi) {
        this.kartNumarasi = kartNumarasi;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
        kullanilabilirLimitGuncelle(); // Limit değişince kullanılabilir limit de güncellenir
    }

    public double getGuncelBorc() {
        return guncelBorc;
    }

    public void setGuncelBorc(double guncelBorc) {
        this.guncelBorc = guncelBorc;
        kullanilabilirLimitGuncelle(); // Borç değişince kullanılabilir limit de güncellenir
    }

    public double getKullanilabilirLimit() {
        return kullanilabilirLimit;
    }

    public void setKullanilabilirLimit(double kullanilabilirLimit) {
        this.kullanilabilirLimit = kullanilabilirLimit;
    }

    // Kredi kartı bilgilerini özetler
    @Override
    public String toString() {
        return "KrediKarti{" +
               "kartNumarasi='" + kartNumarasi + "'" +
               ", limit=" + limit +
               ", guncelBorc=" + guncelBorc +
               ", kullanilabilirLimit=" + kullanilabilirLimit +
               "}";
    }
}

