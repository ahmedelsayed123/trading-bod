package com.example.tradingbod.auction;

import com.example.tradingbod.auction.auctionInformation.AuctionInformation;

/**
 * Represents the bidder Information used in {@link com.example.tradingbod.strategy.BidStrategy } to get access into current state of bidder
 */
public interface BidderInformation {

    /**
     * Get current available quantity on auction,
     *
     * @return quantity left in auction
     */
    int getAuctionQuantity();

    /**
     * Get current available cash remaining to be used in his next bid
     *
     * @return cash left in bidder
     */
    int getCash();

    /**
     * Get current quantity for this bidder
     *
     * @return quantity owned by bidder
     */
    int getOwnQuantity();

    /**
     * Get current available cash remaining with opponent to be used in his next bid
     *
     * @return cash left in opponent
     */
    int getOpponentCash();

    /**
     * Get current quantity for the opponent
     *
     * @return quantity owned by opponent
     */
    int getOpponentQuantity();

    /**
     * Get current history of all transactions excluding currently active
     *
     * @return history of all bidding transactions
     */
    AuctionInformation getAuctionInformation();

}
