package com.bank.app.accounts;

// BankaHesabi'ndan miras alan yatırım hesabı sınıfı
public class YatirimHesabi extends BankaHesabi {

    private String hesapTuru;

    // Constructor: başlangıç bakiyesi alınır, hesap türü sabittir
    public YatirimHesabi(double bakiye) {
        super(bakiye);
        this.hesapTuru = "Yatırım Hesabı";
    }

    // Yatırım hesabına para ekler (para yatırma işlemi)
    public void paraEkle(double miktar) {
        if (miktar <= 0) {
            System.out.println("Yatırılacak miktar sıfırdan büyük olmalıdır.");
            return;
        }
        // Mevcut bakiyeye ekleme yap
        setBakiye(getBakiye() + miktar);
        System.out.printf("Yatırım hesabına %.2f TL eklendi. Yeni bakiye: %.2f TL%n",
                miktar, getBakiye());
    }

    // Yatırım hesabından para çeker
    public void paraCek(double miktar) {
        if (miktar <= 0) {
            System.out.println("Çekilecek miktar sıfırdan büyük olmalıdır.");
            return;
        }
        if (getBakiye() < miktar) {
            // Yeterli bakiye yoksa işlem iptal edilir
            System.out.println("Yetersiz bakiye! Para çekme işlemi gerçekleştirilemedi.");
            return;
        }
        // Bakiyeyi azalt
        setBakiye(getBakiye() - miktar);
        System.out.printf("Yatırım hesabından %.2f TL çekildi. Kalan bakiye: %.2f TL%n",
                miktar, getBakiye());
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
        return "YatirimHesabi{" +
               "hesapTuru='" + hesapTuru + "'" +
               ", iban='" + getIban() + "'" +
               ", bakiye=" + getBakiye() +
               "}";
    }
}

