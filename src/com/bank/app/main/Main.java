package com.bank.app.main;

import com.bank.app.accounts.BankaHesabi;
import com.bank.app.accounts.VadesizHesap;
import com.bank.app.accounts.YatirimHesabi;
import com.bank.app.cards.KrediKarti;
import com.bank.app.people.BankaPersoneli;
import com.bank.app.people.Musteri;
import com.bank.app.service.BankaService;

// Test islemleri icin olusturulan sınıf
public class Main {

    public static void main(String[] args) {

        // Servis nesnesi: tüm iş mantığı buradan yürütülür
        BankaService servis = new BankaService();

        // ---- 1. Banka Personeli Oluşturma ----
        System.out.println("===== BANKA YÖNETİM SİSTEMİ =====\n");
        BankaPersoneli personel = new BankaPersoneli("Derin", "Erdoğan",
                "derin.erdogan@gmail.com", 510123456);
        System.out.println("Personel oluşturuldu:");
        System.out.println(personel);
        System.out.println();

        // ---- 2. Müşteri Oluşturma ----
        System.out.println("===== MÜŞTERİ OLUŞTURMA =====");
        Musteri musteri1 = servis.musteriOlustur(personel, "Nisa", "Derin",
                "nisa.derin@gmail.com", 520123456);
        Musteri musteri2 = servis.musteriOlustur(personel, "Güneş", "Yalçın",
                "gunes.yalcin@gmail.com", 530123456);
        System.out.println("\nMüşteri 1 bilgileri:");
        System.out.println(musteri1);
        System.out.println("\nMüşteri 2 bilgileri:");
        System.out.println(musteri2);
        System.out.println();

        // ---- 3. Müşteriye Hesap Açma ----
        System.out.println("===== HESAP AÇMA =====");
        servis.hesapAc(musteri1, "vadesiz");  // Nisa Derin'e vadesiz hesap
        servis.hesapAc(musteri1, "yatirim");  // Nisa Derin'e yatırım hesabı
        servis.hesapAc(musteri2, "vadesiz");  // Güneş Yalçın'a vadesiz hesap
        servis.hesapAc(musteri2, "yatirim");  // Güneş Yalçın'a yatırım hesabı
        System.out.println();

        // Hesaplara kolayca erişmek için referans değişkenleri tanımla
        VadesizHesap nisaDerinVadesiz = (VadesizHesap) musteri1.getHesaplar().get(0);
        YatirimHesabi nisaDerinYatirim = (YatirimHesabi) musteri1.getHesaplar().get(1);
        VadesizHesap gunesYalcinVadesiz = (VadesizHesap) musteri2.getHesaplar().get(0);
        YatirimHesabi gunesYalcinYatirim = (YatirimHesabi) musteri2.getHesaplar().get(1);

        // ---- 4. Hesaba Para Yatırma ----
        System.out.println("===== PARA YATIRMA =====");
        servis.paraYatir(nisaDerinYatirim, 5000.0);   // Nisa Derin yatırım hesabına 5000 TL
        servis.paraYatir(gunesYalcinYatirim, 10000.0);  // Güneş Yalçın yatırım hesabına 10000 TL

        // Vadesiz hesaplara başlangıç bakiyesi için direkt bakiye set ediyoruz
        nisaDerinVadesiz.setBakiye(3000.0);
        System.out.println("Nisa Derin vadesiz hesabına 3000.00 TL yüklendi. Bakiye: 3000.00 TL");
        gunesYalcinVadesiz.setBakiye(2000.0);
        System.out.println("Güneş Yalçın vadesiz hesabına 2000.00 TL yüklendi. Bakiye: 2000.00 TL");
        System.out.println();

        // ---- 5. Hesaplar Arası Para Transferi ----
        System.out.println("===== PARA TRANSFERİ =====");
        // Nisa Derin vadesiz → Güneş Yalcin vadesiz: 1000 TL transfer
        servis.paraTransferi(nisaDerinVadesiz, gunesYalcinVadesiz, 1000.0);
        System.out.println();

        // ---- 6. Müşteriye Kredi Kartı Tanımlama ----
        System.out.println("===== KREDİ KARTI TANIMLAMA =====");
        KrediKarti nisaDerinKarti = servis.krediKartiTanimla(musteri1, 5000.0);
        KrediKarti gunesYalcinKarti = servis.krediKartiTanimla(musteri2, 8000.0);
        System.out.println("\nNisa Derin'in kart bilgileri: " + nisaDerinKarti);
        System.out.println("Güneş Yalcin'ın kart bilgileri: " + gunesYalcinKarti);
        System.out.println();

        // Kredi kartına borç yükle (harcama simülasyonu)
        nisaDerinKarti.setGuncelBorc(750.0);
        System.out.println("Nisa Derin'in kredi kartına 750.00 TL borç yüklenmiştir.");

        // ---- 7. Kredi Kartı Borcu Ödeme ----
        System.out.println("\n===== KREDİ KARTI BORÇ ÖDEME =====");
        servis.borcOde(nisaDerinVadesiz, nisaDerinKarti, 750.0);
        System.out.println();

        // ---- 8. Hesap Silme İşlemi ----
        System.out.println("===== HESAP SİLME =====");

        // Önce silinecek geçici hesabı oluştur ve bakiyesi sıfır yap
        servis.hesapAc(musteri2, "vadesiz");
        VadesizHesap geciciHesap = (VadesizHesap) musteri2.getHesaplar().get(2);

        // Bakiyesi sıfır olmayan hesabı silmeye çalış (beklenen: uyarı mesajı)
        gunesYalcinVadesiz.setBakiye(500.0);
        System.out.println("Bakiyesi olan hesabı silme denemesi:");
        servis.hesapSil(musteri2, gunesYalcinVadesiz);

        // Bakiyesi sıfır olan hesabı sil (beklenen: başarılı silme)
        System.out.println("\nBakiyesi sıfır olan hesabı silme denemesi:");
        servis.hesapSil(musteri2, geciciHesap);
        System.out.println();

        // ---- 9. Kredi Kartı Silme İşlemi ----
        System.out.println("===== KREDİ KARTI SİLME =====");
        // Geçici kredi kartı oluştur, borç var
        servis.krediKartiTanimla(musteri2, 2000.0);
        KrediKarti geciciKart = musteri2.getKrediKartlari().get(1);
        geciciKart.setGuncelBorc(300.0);

        // Borçlu kartı silmeye çalış (beklenen: uyarı)
        System.out.println("Borcu olan kartı silme denemesi:");
        servis.krediKartiSil(musteri2, geciciKart);

        // Borcu sıfırla ve tekrar sil (beklenen: başarılı silme)
        geciciKart.setGuncelBorc(0.0);
        System.out.println("\nBorcu sıfırlanan kartı silme denemesi:");
        servis.krediKartiSil(musteri2, geciciKart);

        // ---- 10. Son Durum Özeti ----
        System.out.println("\n===== SON DURUM ÖZETİ =====");
        System.out.println("Nisa Derin hesap sayısı: " + musteri1.getHesaplar().size());
        System.out.println("Guneş Yalçın hesap sayısı: " + musteri2.getHesaplar().size());
        System.out.println("\nNisa Derin vadesiz bakiye: " + nisaDerinVadesiz.getBakiye() + " TL");
        System.out.println("Nisa Derin yatırım bakiye: " + nisaDerinYatirim.getBakiye() + " TL");
        System.out.println("Nisa Derin kart borcu: " + nisaDerinKarti.getGuncelBorc() + " TL");
        System.out.println("\nGunes Yalcın vadesiz bakiye: " + gunesYalcinVadesiz.getBakiye() + " TL");
        System.out.println("Gunes Yalcın yatırım bakiye: " + gunesYalcinYatirim.getBakiye() + " TL");
        System.out.println("\nPersonel müşteri sayısı: " + personel.getMusteriler().size());
    }
}
