package nl.jhutting.modernjava.patternmatching.model;

import java.util.Currency;

public record CardPayment(String cardId, Currency currency, long amount) implements Payment {}
