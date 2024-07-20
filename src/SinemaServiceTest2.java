import java.sql.SQLException;

public class SinemaServiceTest2 {
    private FilmDAO filmDAO;
    private SeansDAO seansDAO;

    public SinemaServiceTest2(IDatabaseAdapter dbAdapter) {
        this.filmDAO = new FilmDAO(dbAdapter);
        this.seansDAO = new SeansDAO(dbAdapter);
    }

    public static void main(String[] args) {
        try {
            // Proxy adapter oluştur
            IDatabaseAdapter dbAdapter = new DatabaseConnectionProxy();

            // Sinema servisini oluştur
            SinemaServiceTest2 sinemaServiceTest = new SinemaServiceTest2(dbAdapter);

            // Testi çalıştır
            sinemaServiceTest.testBuyTickets();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void testBuyTickets() throws SQLException {
        // Proxy adapter oluştur
        IDatabaseAdapter dbAdapter = new DatabaseConnectionProxy();

        // Sinema servisini oluştur
        SinemaService sinemaService = new SinemaService(dbAdapter);

        // Test için seans ID ve bilet sayısı
        int seansId = 4; // Mevcut bir seans ID'si
        int numTickets = 2; // Satın alınacak bilet sayısı

        // Seansın mevcut bilet stoğunu al
        Seans seansBefore = seansDAO.getSeansById(seansId);
        int initialStok = seansBefore.getBiletStogu();

        // Bilet satın alma işlemini gerçekleştir
        boolean purchaseResult = sinemaService.buyTickets(seansId, numTickets);

        // Satın alma işleminin başarılı olup olmadığını kontrol et
        if (purchaseResult) {
            // Seansın güncellenmiş bilet stoğunu al
            Seans seansAfter = seansDAO.getSeansById(seansId);
            int updatedStok = seansAfter.getBiletStogu();

            // Stok durumunu kontrol et
            if (updatedStok == initialStok - numTickets) {
                System.out.println("Test passed: Bilet satın alma işlemi başarılı ve stok doğru şekilde güncellendi.");
            } else {
                System.out.println("Test failed: Stok güncellenmedi. Beklenen stok: " + (initialStok - numTickets) + ", Güncel stok: " + updatedStok);
            }
        } else {
            System.out.println("Test failed: Bilet satın alma işlemi başarısız.");
        }

        // Veritabanı bağlantısını kapat
        dbAdapter.closeConnection();
    }
}
