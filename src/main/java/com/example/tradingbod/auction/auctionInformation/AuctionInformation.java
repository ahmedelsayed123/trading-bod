package com.example.tradingbod.auction.auctionInformation;

import com.example.tradingbod.model.AuctionTransaction;

import java.util.List;

/**
 * Represents information of auction, all bidding transactions.
 */
public interface AuctionInformation {

    /**
     * Add new bidding transaction to the auction
     *
     * @param auctionTransaction Transaction that holds bidder and opponent money expended and quantity earned
     */
    void addTransaction(AuctionTransaction auctionTransaction);

    /**
     * Get bidding transactions
     *
     * @return All transaction information
     */
    List<AuctionTransaction> getTransactions();
}
