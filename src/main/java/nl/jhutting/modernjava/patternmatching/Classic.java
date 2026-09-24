package nl.jhutting.modernjava.patternmatching;

import nl.jhutting.modernjava.patternmatching.model.BankTransfer;
import nl.jhutting.modernjava.patternmatching.model.CardPayment;
import nl.jhutting.modernjava.patternmatching.model.CashPayment;
import nl.jhutting.modernjava.patternmatching.model.Payment;

import java.util.Currency;

public class Classic {

    void main() {
        var euro = Currency.getInstance("EUR");

        System.out.println(process(new CardPayment("card-4821", euro, 75)));
        System.out.println(process(new BankTransfer(
                "NL36INGB0007654321",
                "NL69INGB0123456789",
                euro,
                1_250)));
        System.out.println(process(new CashPayment(euro, 50)));

        try {
            System.out.println(process(null));
        } catch (IllegalArgumentException exception) {
            System.out.println(exception.getMessage());
        }
    }

    String process(final Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Unknown payment type");
        }

        if (payment.getClass() == CardPayment.class) {
            CardPayment card = (CardPayment) payment;
            return "Charge card " + card.cardId() + " for amount " + card.amount() + " " + card.currency().getDisplayName();
        } else if (payment.getClass() == BankTransfer.class) {
            BankTransfer transfer = (BankTransfer) payment;
            return "Transfer amount " + transfer.amount() + " " + transfer.currency().getDisplayName() + " to " + mask(transfer.to());
        } else if (payment.getClass() == CashPayment.class) {
            CashPayment cash = (CashPayment) payment;
            return "Receive " + cash.amount() + " " + cash.currency();
        }

        throw new IllegalArgumentException("Unknown payment");
    }

    private String mask(String card) {
        return card.substring(0, 4) + "****" + card.substring(card.length() - 4);
    }
}
