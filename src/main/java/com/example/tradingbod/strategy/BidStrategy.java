package com.example.tradingbod.strategy;

import com.example.tradingbod.auction.BidderInformation;
import com.example.tradingbod.auction.Bidder;

/**
 * Represents a strategy that will determine optimal bidding amount on each
 */
public interface BidStrategy {

    /**
     * Provides ability for BiddingStrategy to prepare for bidding session Method should be called once {@link Bidder#bids(int, int)} was executed
     * Once executed, initial parameters such as {@link BidderInformation#getCash()} should be set
     *
     * @param bidderInformation BidderInformation that contains initial information about auction.
     */
    void init(BidderInformation bidderInformation);

    /**
     * Computes or predicts next bid amount
     *
     * @return optimal cash amount to bid on next quantity
     */
    int nextBid();
}
