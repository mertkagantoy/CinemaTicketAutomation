import java.sql.SQLException;

public class BuyTicketCommand implements Command {
    private SinemaService sinemaService;
    private int seansId;
    private int numTickets;

    public BuyTicketCommand(SinemaService sinemaService, int seansId, int numTickets) {
        this.sinemaService = sinemaService;
        this.seansId = seansId;
        this.numTickets = numTickets;
    }

    @Override
    public boolean execute() {
        try {
            return sinemaService.buyTickets(seansId, numTickets);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
