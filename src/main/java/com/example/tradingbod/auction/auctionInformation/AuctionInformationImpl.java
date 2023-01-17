package com.example.tradingbod.auction.auctionInformation;

import com.example.tradingbod.model.AuctionTransaction;

import java.util.ArrayList;
import java.util.List;

public class AuctionInformationImpl implements AuctionInformation {

    private final List<AuctionTransaction> transactions;

    public AuctionInformationImpl() {
        this.transactions = new ArrayList<>();
    }

    public AuctionInformationImpl(List<AuctionTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public void addTransaction(AuctionTransaction auctionTransaction) {
        transactions.add(auctionTransaction);
    }

    @Override
    public List<AuctionTransaction> getTransactions() {
        return transactions;
    }
}
