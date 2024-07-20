import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.sql.SQLException;

public class SinemaServiceTest {

    private static IDatabaseAdapter dbAdapter;
    private static SinemaService sinemaService;
    private static SeansDAO seansDAO;

    @BeforeAll
    public static void setup() {
        dbAdapter = new DatabaseConnectionProxy();
        sinemaService = new SinemaService(dbAdapter);
        seansDAO = new SeansDAO(dbAdapter);
    }

    @Test
    public void testBuyTickets() throws SQLException {
        // Test için seans ID ve bilet sayısı
        int seansId = 4; // Mevcut bir seans ID'si
        int numTickets = 2; // Satın alınacak bilet sayısı

        // Seansın mevcut bilet stoğunu al
        Seans seansBefore = seansDAO.getSeansById(seansId);
        int initialStok = seansBefore.getBiletStogu();

        // Bilet satın alma işlemini gerçekleştir
        boolean purchaseResult = sinemaService.buyTickets(seansId, numTickets);

        // Satın alma işleminin başarılı olup olmadığını kontrol et
        assertTrue(purchaseResult, "Bilet satın alma işlemi başarısız.");

        // Seansın güncellenmiş bilet stoğunu al
        Seans seansAfter = seansDAO.getSeansById(seansId);
        int updatedStok = seansAfter.getBiletStogu();

        // Stok durumunu kontrol et
        assertEquals(initialStok - numTickets, updatedStok, "Stok güncellenmedi. Beklenen stok: " + (initialStok - numTickets) + ", Güncel stok: " + updatedStok);
    }

    @AfterAll
    public static void tearDown() throws SQLException {
        // Veritabanı bağlantısını kapat
        dbAdapter.closeConnection();
    }
}
