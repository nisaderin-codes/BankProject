# Banka Yönetim Sistemi

BLM0121 Nesneye Yönelik Programlama dersi dönem projesi. Java ile yazılmış banka yönetim sistemi.

## Proje Hakkında

Bu projede bir bankanın temel işlevlerini modelledim. Müşteri oluşturma, hesap açma, para transferi, kredi kartı yönetimi gibi işlemler nesneye yönelik programlama prensipleriyle implement edildi.

## Sınıf Yapısı

```
com.bank.app
├── people
│   ├── Kisi.java             
│   ├── Musteri.java
│   └── BankaPersoneli.java
├── accounts
│   ├── BankaHesabi.java      
│   ├── VadesizHesap.java
│   └── YatirimHesabi.java
├── cards
│   └── KrediKarti.java
├── service
│   └── BankaService.java
└── main
    └── Main.java
```

## Özellikler

- Banka personeli ve müşteri oluşturma
- Vadesiz ve yatırım hesabı açma
- Hesaplar arası para transferi
- Kredi kartı tanımlama ve borç ödeme
- Bakiyesi/borcu olmayan hesap ve kart silme
- Geçersiz işlemlerde hata mesajı

## Çalıştırma

JDK 21 gereklidir.


## Kullanılan Teknolojiler

- Java 21
- java.util.ArrayList
- java.util.Random

## Notlar

- Tüm ID, IBAN ve kart numaraları program her çalıştırıldığında rastgele üretilir
- Veri kalıcılığı yok, veriler bellekte tutulur
- Proje konsol tabanlıdır, arayüz yoktur