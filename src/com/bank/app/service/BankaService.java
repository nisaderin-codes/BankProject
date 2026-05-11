package com.bank.app.service;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;
import com.bank.app.people.BankaPersoneli;
import com.bank.app.people.Musteri;

// İş mantığını merkezi olarak yöneten servis sınıfı
public class BankaService {

    // ---- Müşteri İşlemleri ----

    // Yeni müşteri oluşturur ve personelin müşteri listesine ekler
    public Musteri musteriOlustur(BankaPersoneli personel,
                                  String ad, String soyad,
                                  String email, long telefonNumarasi) {
        Musteri musteri = new Musteri(ad, soyad, email, telefonNumarasi);
        personel.musteriEkle(musteri);
        System.out.println("Yeni müşteri oluşturuldu: " + musteri.getMusteriNumarasi());
        return musteri;
    }

    // ---- Hesap İşlemleri ----

    // Müşteriye hesap açar; hesap türü "vadesiz" veya "yatirim" olabilir
    public void hesapAc(Musteri musteri, String hesapTuru) {
        musteri.hesapEkle(hesapTuru);
    }

    // Yatırım hesabına para yatırır
    public void paraYatir(YatirimHesabi hesap, double miktar) {
        hesap.paraEkle(miktar);
    }

    // Yatırım hesabından para çeker
    public void paraCek(YatirimHesabi hesap, double miktar) {
        hesap.paraCek(miktar);
    }

    // Vadesiz hesaplar arasında para transferi gerçekleştirir
    public void paraTransferi(VadesizHesap gonderenHesap,
                               BankaHesabi aliciHesap,
                               double miktar) {
        gonderenHesap.paraTransferi(aliciHesap, gonderenHesap, miktar);
    }

    // ---- Kredi Kartı İşlemleri ----

    // Müşteriye belirtilen limitli kredi kartı tanımlar
    public KrediKarti krediKartiTanimla(Musteri musteri, double limit) {
        musteri.krediKartiEkle(limit);
        // Listeye eklenen en son kart döndürülür
        return musteri.getKrediKartlari().get(musteri.getKrediKartlari().size() - 1);
    }

    // Vadesiz hesaptan kredi kartı borcu öder
    public void borcOde(VadesizHesap hesap, KrediKarti kart, double miktar) {
        hesap.krediKartiBorcOdeme(kart, miktar);
    }

    // ---- Silme İşlemleri ----

    // Bakiyesi sıfır olan hesabı siler
    public void hesapSil(Musteri musteri, BankaHesabi hesap) {
        musteri.hesapSil(hesap);
    }

    // Borcu sıfır olan kredi kartını siler
    public void krediKartiSil(Musteri musteri, KrediKarti kart) {
        musteri.krediKartiSil(kart);
    }
}
