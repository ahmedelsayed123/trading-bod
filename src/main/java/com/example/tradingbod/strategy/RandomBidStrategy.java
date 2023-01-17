package com.example.tradingbod.strategy;

import com.example.tradingbod.auction.BidderInformation;

import java.util.Random;

/**
 * this class represents the bid with a random number in his cash range
 */
public class RandomBidStrategy implements BidStrategy {
    private BidderInformation bidderInformation;

    @Override
    public void init(BidderInformation bidderInformation) {
        this.bidderInformation = bidderInformation;
    }

    /**
     * @return Random bid within his cash limit
     */
    @Override
    public int nextBid() {
        return (int) Math.ceil(new Random().nextInt(bidderInformation.getCash()));
    }
}
