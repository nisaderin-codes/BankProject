package com.bank.app.people;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;

import java.util.ArrayList;
import java.util.Random;

// Kisi sınıfından miras aldı
public class Musteri extends Kisi {

    private String musteriNumarasi;

    // Müşteriye ait banka hesapları listesi
    private ArrayList<BankaHesabi> hesaplar;

    // Müşteriye ait kredi kartları listesi
    private ArrayList<KrediKarti> krediKartlari;

    // Constructor: müşteri bilgileri alınır, numara ve listeler otomatik hazırlanır
    public Musteri(String ad, String soyad, String email, long telefonNumarasi) {
        super(ad, soyad, email, telefonNumarasi);
        this.musteriNumarasi = uretMusteriNumarasi();
        this.hesaplar = new ArrayList<>();
        this.krediKartlari = new ArrayList<>();
    }

    // 8 haneli rastgele müşteri numarası üretir
    private String uretMusteriNumarasi() {
        Random rand = new Random();
        int no = 10000000 + rand.nextInt(90000000);
        return "M" + no;
    }

    // Hesap türüne göre VadesizHesap veya YatirimHesabi oluşturur ve listeye ekler
    public void hesapEkle(String hesapTuru) {
        if (hesapTuru.equalsIgnoreCase("vadesiz")) {
            // Vadesiz hesap açılıyor
            VadesizHesap hesap = new VadesizHesap(0.0);
            hesaplar.add(hesap);
            System.out.println("Vadesiz hesap açıldı. IBAN: " + hesap.getIban());
        } else if (hesapTuru.equalsIgnoreCase("yatirim")) {
            // Yatırım hesabı açılıyor
            YatirimHesabi hesap = new YatirimHesabi(0.0);
            hesaplar.add(hesap);
            System.out.println("Yatırım hesabı açıldı. IBAN: " + hesap.getIban());
        } else {
            System.out.println("Geçersiz hesap türü! 'vadesiz' veya 'yatirim' giriniz.");
        }
    }

    // Yeni kredi kartı oluşturur ve krediKartlari listesine ekler
    public void krediKartiEkle(double limit) {
        KrediKarti kart = new KrediKarti(limit, 0.0);
        krediKartlari.add(kart);
        System.out.println("Kredi kartı tanımlandı. Kart No: " + kart.getKartNumarasi());
    }

    // Belirtilen hesabı siler; bakiye 0'dan büyükse uyarı verir
    public void hesapSil(BankaHesabi hesap) {
        if (hesap.getBakiye() > 0) {
            // Bakiye varsa silme işlemi engellenir
            System.out.println("Lütfen öncelikle bakiyenizi başka bir hesaba aktarınız.");
        } else {
            hesaplar.remove(hesap);
            System.out.println("Hesap başarıyla silindi. IBAN: " + hesap.getIban());
        }
    }

    // Belirtilen kredi kartını siler; borç varsa uyarı verir
    public void krediKartiSil(KrediKarti kart) {
        if (kart.getGuncelBorc() != 0) {
            // Borç varsa kart silinemez
            System.out.println("Lütfen öncelikle borç ödemesi yapınız.");
        } else {
            krediKartlari.remove(kart);
            System.out.println("Kredi kartı başarıyla silindi.");
        }
    }

    // ---- Getter ve Setter Metotları ----

    public String getMusteriNumarasi() {
        return musteriNumarasi;
    }

    public void setMusteriNumarasi(String musteriNumarasi) {
        this.musteriNumarasi = musteriNumarasi;
    }

    public ArrayList<BankaHesabi> getHesaplar() {
        return hesaplar;
    }

    public void setHesaplar(ArrayList<BankaHesabi> hesaplar) {
        this.hesaplar = hesaplar;
    }

    public ArrayList<KrediKarti> getKrediKartlari() {
        return krediKartlari;
    }

    public void setKrediKartlari(ArrayList<KrediKarti> krediKartlari) {
        this.krediKartlari = krediKartlari;
    }

    // Müşteri bilgilerini özetlemek icin toString override edildi.
    @Override
    public String toString() {
        return "Musteri{" +
               "musteriNumarasi='" + musteriNumarasi + "'" +
               ", " + super.toString() +
               ", hesapSayisi=" + hesaplar.size() +
               ", kartSayisi=" + krediKartlari.size() +
               "}";
    }
}
