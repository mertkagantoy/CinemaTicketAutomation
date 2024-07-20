import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        IDatabaseAdapter dbAdapter = new DatabaseConnectionProxy();
        SinemaService sinemaService = new SinemaService(dbAdapter);

        Scanner scanner = new Scanner(System.in);
        TicketInvoker invoker = new TicketInvoker();

        while (true) {
            System.out.println("Tüm Filmler:");
            List<Film> filmler = sinemaService.getAllFilms();
            for (Film film : filmler) {
                System.out.println(film.getFilmId() + ": " + film.getAd());
            }
            System.out.print("Film ID'si seçin: ");
            int filmId = scanner.nextInt();

            System.out.println("Seanslar:");
            List<Seans> seanslar = sinemaService.getSeanslarByFilmId(filmId);
            if (seanslar != null && !seanslar.isEmpty()) {
                for (Seans seans : seanslar) {
                    System.out.println(seans.getSeansId() + ": " + seans.getSeansZamani());
                }
                System.out.print("Seans ID'si seçin: ");
                int seansId = scanner.nextInt();

                System.out.print("Bilet sayısı girin: ");
                int numTickets = scanner.nextInt();

                BuyTicketCommand buyTicketCommand = new BuyTicketCommand(sinemaService, seansId, numTickets);
                invoker.setCommand(buyTicketCommand);

                if (invoker.executeCommand()) {
                    System.out.println("Bilet satın alındı!");
                } else {
                    System.out.println("Yeterli bilet yok!");
                }
            } else {
                System.out.println("Seans bulunamadı.");
            }
        }
    }
}
