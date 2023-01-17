package com.example.tradingbod.auction;

import com.example.tradingbod.auction.auctionInformation.AuctionInformation;
import com.example.tradingbod.auction.auctionInformation.AuctionInformationImpl;
import com.example.tradingbod.strategy.BidStrategy;
import com.example.tradingbod.model.AuctionTransaction;

/**
 * Represents the implementation of the {@link Bidder} interface
 */
public class BidderImpl implements Bidder, BidderInformation {

    private int auctionQuantity;
    private int cash;
    private int ownQuantity;

    private int opponentCash;
    private int opponentQuantity;

    private final AuctionInformation auctionInformation;

    private final BidStrategy bidStrategy;


    public BidderImpl(int cash, int auctionQuantity, BidStrategy bidStrategy) {
        this.bidStrategy = bidStrategy;
        this.cash = cash;
        this.auctionQuantity = auctionQuantity;
        this.auctionInformation = new AuctionInformationImpl();

    }

    @Override
    public void init(int quantity, int cash) {
        if (quantity < 0 || cash < 0 || quantity % 2 == 1) {
            return;
        }
        this.auctionQuantity = quantity;
        this.cash = cash;
        this.opponentCash = cash;
        bidStrategy.init(this);
    }

    @Override
    public int placeBid() {
        if (cash == 0) {
            return 0;
        }
        final int nextBid = bidStrategy.nextBid();
        if (nextBid < 0) {
            return 0;
        }
        return Math.min(nextBid, cash);
    }

    @Override
    public void bids(int ownBid, int opponentBid) {
        int bidderQuWon = 0, opponentQuWon = 0;
        if (ownBid > opponentBid) {
            bidderQuWon = 2;
        } else if (ownBid < opponentBid) {
            opponentQuWon = 2;
        } else {
            bidderQuWon = opponentQuWon = 1;
        }
        this.ownQuantity += bidderQuWon;
        this.opponentQuantity += opponentQuWon;
        this.auctionQuantity -= 2;
        this.cash -= ownBid;
        this.opponentCash -= opponentBid;
        auctionInformation.addTransaction(new AuctionTransaction(ownBid, opponentBid, bidderQuWon, opponentQuWon));
    }

    @Override
    public int getAuctionQuantity() {
        return auctionQuantity;
    }

    @Override
    public int getCash() {
        return cash;
    }

    @Override
    public int getOwnQuantity() {
        return ownQuantity;
    }

    @Override
    public int getOpponentCash() {
        return opponentCash;
    }

    @Override
    public int getOpponentQuantity() {
        return opponentQuantity;
    }

    @Override
    public AuctionInformation getAuctionInformation() {
        return auctionInformation;
    }

}
