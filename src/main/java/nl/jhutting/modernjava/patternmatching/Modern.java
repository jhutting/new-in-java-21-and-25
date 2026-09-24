package nl.jhutting.modernjava.patternmatching;

import nl.jhutting.modernjava.patternmatching.model.BankTransfer;
import nl.jhutting.modernjava.patternmatching.model.CardPayment;
import nl.jhutting.modernjava.patternmatching.model.CashPayment;
import nl.jhutting.modernjava.patternmatching.model.Payment;

import java.util.Currency;

public class Modern {

    void main() {
        final Currency euro = Currency.getInstance("EUR");

        IO.println(process(new CardPayment("card-4821", euro, 75)));
        IO.println(process(new BankTransfer(
                "NL36INGB0007654321",
                "NL69INGB0123456789",
                euro,
                1_250)));
        IO.println(process(new CashPayment(euro, 50)));


        try {
            IO.println(process(null));
        } catch (IllegalArgumentException exception) {
            IO.println(exception.getMessage());
        }

        IO.println(classify(new CardPayment("card-4821", euro, 100_001)));
        IO.println(classify(new BankTransfer(
                "NL36INGB0007654321",
                "NL69INGB0123456789",
                euro,
                100_001)));
        IO.println(classify(new CardPayment("card-4821", euro, 75)));
        IO.println(classify(new BankTransfer(
                "NL36INGB0007654321",
                "NL69INGB0123456789",
                euro,
                1_250)));
        IO.println(classify(new CashPayment(euro, 50)));
    }
    
    String process(final Payment payment) {
        return switch (payment) {
            case null -> throw new IllegalArgumentException("Unknown payment type");

            case CardPayment(String cardID, Currency currency, long amount) ->
                    "Charge card %s for %d %s".formatted(cardID, amount, currency.getDisplayName());

            case BankTransfer(_, String to, Currency currency, long amount) ->
                    "Transfer amount %d %s to %s".formatted(amount, currency.getDisplayName(), mask(to));

            case CashPayment(Currency currency, long amount) ->
                    "Receive %d %s in cash ".formatted(amount, currency.getDisplayName());
        };
    }

    String classify(Payment payment) {
        return switch (payment) {
            case null -> throw new IllegalArgumentException("Unknown payment type");

            case CardPayment(_, _, long amount) when amount > 100_000 ->
                    "Large card payment";

            case BankTransfer(_, _, _, long amount) when amount > 100_000 ->
                    "Large bank transfer";

            case CardPayment(_, _, _) ->
                    "Regular card payment";

            case BankTransfer(_, _, _, _) ->
                    "Regular bank transfer";

            case CashPayment(_, _) ->
                    "Cash payment";
        };
    }

    private String mask(String card) {
        return card.substring(0, 4) + "****" + card.substring(card.length() - 4);
    }
}
