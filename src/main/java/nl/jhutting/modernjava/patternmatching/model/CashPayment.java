package nl.jhutting.modernjava.patternmatching.model;

import java.util.Currency;

public record CashPayment(Currency currency, long amount) implements Payment {}
