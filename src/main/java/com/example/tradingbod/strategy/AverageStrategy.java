package com.example.tradingbod.strategy;

import com.example.tradingbod.auction.BidderInformation;

/**
 * bidding strategy that bids the average amount per game
 */
public class AverageStrategy implements BidStrategy {
    private BidderInformation bidderInformation;

    @Override
    public void init(BidderInformation bidderInformation) {
        this.bidderInformation = bidderInformation;
    }

    /**
     * Returns constant percent of  amount in relation to each game
     *
     * @return {@link BidderInformation#getCash()} / ({@link BidderInformation#getAuctionQuantity()} / 2), or 0 if either of input is 0
     */
    @Override
    public int nextBid() {
        if (bidderInformation.getCash() == 0 || bidderInformation.getAuctionQuantity() == 0)
            return 0;

        return bidderInformation.getCash() / (bidderInformation.getAuctionQuantity() / 2);
    }
}
