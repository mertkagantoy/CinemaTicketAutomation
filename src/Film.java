
// film bilgilerini tutan sınıf
// Film sınıfı, bir film nesnesini temsil eder.
public class Film {
    private int filmId;
    private String ad;
    private String yonetmen;
    private String tur;
    private int sure;

    // Film sınıfının yapıcı metodu, film nesnesini oluşturur.
    public Film(int filmId, String ad, String yonetmen, String tur, int sure) {
        this.filmId = filmId;
        this.ad = ad;
        this.yonetmen = yonetmen;
        this.tur = tur;
        this.sure = sure;
    }


    // Getter metodları, film özelliklerine erişim sağlar.
    public int getFilmId() {
        return filmId;
    }

    public String getAd() {
        return ad;
    }

    public String getYonetmen() {
        return yonetmen;
    }

    public String getTur() {
        return tur;
    }

    public int getSure() {
        return sure;
    }
}
