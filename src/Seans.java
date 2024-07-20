import java.time.LocalTime;

// Seans bilgilerini tutan sınıf
// Seans sınıfı, bir seans nesnesini temsil eder.
public class Seans {
    private int seansId;
    private int filmId;
    private LocalTime seansZamani;
    private int biletStogu;
    // Seans sınıfının yapıcı metodu, seans nesnesini oluşturur.
    public Seans(int seansId, int filmId, LocalTime seansZamani, int biletStogu) {
        this.seansId = seansId;
        this.filmId = filmId;
        this.seansZamani = seansZamani;
        this.biletStogu = biletStogu;
    }

    // Getter ve setter metodları, seans özelliklerine erişim ve değişiklik sağlar.
    public int getSeansId() {
        return seansId;
    }

    public int getFilmId() {
        return filmId;
    }

    public LocalTime getSeansZamani() {
        return seansZamani;
    }

    public int getBiletStogu() {
        return biletStogu;
    }

    public void setBiletStogu(int biletStogu) {
        this.biletStogu = biletStogu;
    }
}
