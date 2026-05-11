package com.bank.app.accounts;

import com.bank.app.cards.KrediKarti;

// BankaHesabi'ndan miras alan vadesiz hesap sınıfı
public class VadesizHesap extends BankaHesabi {

    private String hesapTuru;

    // Constructor: başlangıç bakiyesi alınır, hesap türü sabittir
    public VadesizHesap(double bakiye) {
        super(bakiye);
        this.hesapTuru = "Vadesiz Hesap";
    }

    // Gönderen hesaptan alıcı hesaba para transferi yapar
    public void paraTransferi(BankaHesabi aliciHesap, BankaHesabi gonderenHesap, double miktar) {
        if (miktar <= 0) {
            System.out.println("Transfer miktarı sıfırdan büyük olmalıdır.");
            return;
        }
        if (gonderenHesap.getBakiye() < miktar) {
            // Yetersiz bakiye kontrolü
            System.out.println("Yetersiz bakiye! Transfer gerçekleştirilemedi.");
            return;
        }
        // Gönderenden düş, alıcıya ekle
        gonderenHesap.setBakiye(gonderenHesap.getBakiye() - miktar);
        aliciHesap.setBakiye(aliciHesap.getBakiye() + miktar);
        System.out.printf("Transfer başarılı! %.2f TL aktarıldı.%n", miktar);
        System.out.printf("Gönderen bakiye: %.2f TL | Alıcı bakiye: %.2f TL%n",
                gonderenHesap.getBakiye(), aliciHesap.getBakiye());
    }

    // Hesaptan kredi kartı borcu öder
    public void krediKartiBorcOdeme(KrediKarti kart, double miktar) {
        if (miktar <= 0) {
            System.out.println("Ödeme miktarı sıfırdan büyük olmalıdır.");
            return;
        }
        if (getBakiye() < miktar) {
            // Hesap bakiyesi ödemeye yetmiyorsa uyarı ver
            System.out.println("Yetersiz bakiye! Ödeme gerçekleştirilemedi.");
            return;
        }
        if (kart.getGuncelBorc() < miktar) {
            System.out.println("Ödeme miktarı mevcut borçtan fazla olamaz.");
            return;
        }
        // Bakiyeyi düş, kart borcunu azalt
        setBakiye(getBakiye() - miktar);
        kart.setGuncelBorc(kart.getGuncelBorc() - miktar);
        System.out.printf("Kredi kartı borç ödemesi yapıldı: %.2f TL%n", miktar);
        System.out.printf("Kalan hesap bakiyesi: %.2f TL | Kalan kart borcu: %.2f TL%n",
                getBakiye(), kart.getGuncelBorc());
    }

    // ---- Getter ve Setter Metotları ----

    public String getHesapTuru() {
        return hesapTuru;
    }

    public void setHesapTuru(String hesapTuru) {
        this.hesapTuru = hesapTuru;
    }

    // Hesap bilgilerini özetler
    @Override
    public String toString() {
        return "VadesizHesap{" +
               "hesapTuru='" + hesapTuru + "'" +
               ", iban='" + getIban() + "'" +
               ", bakiye=" + getBakiye() +
               "}";
    }
}

