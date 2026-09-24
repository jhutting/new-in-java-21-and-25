package nl.jhutting.modernjava.patternmatching.model;

public sealed interface Payment permits CardPayment, BankTransfer, CashPayment {}
