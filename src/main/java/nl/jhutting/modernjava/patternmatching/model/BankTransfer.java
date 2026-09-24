package nl.jhutting.modernjava.patternmatching.model;

import java.util.Currency;

public record BankTransfer(String from, String to, Currency currency, long amount) implements Payment {}
