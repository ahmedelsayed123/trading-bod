package com.example.tradingbod.biddingGame;

import com.example.tradingbod.auction.BidderImpl;
import com.example.tradingbod.enums.GameResult;
import com.example.tradingbod.model.AuctionTransaction;

import java.util.List;

/**
 * This class represents {@link BiddingGame} the bidding game by giving the bidder and opponent strategy using
 * {@link BiddingGame#BiddingGame(BidderImpl, BidderImpl)} and then get the game results based on each strategy of bidding using
 * {@link BiddingGame#tradingBodGame()}
 */
public class BiddingGame {

    private BidderImpl bidder;
    private BidderImpl opponent;
    private final int auctionQu;

    public BiddingGame(BidderImpl bidder, BidderImpl opponent) {
        this.bidder = bidder;
        this.opponent = opponent;
        auctionQu = bidder.getAuctionQuantity();

    }

    public GameResult tradingBodGame() {
        getBidder().init(getBidder().getAuctionQuantity(), getBidder().getCash());
        getOpponent().init(getOpponent().getAuctionQuantity(), getOpponent().getCash());
        for (int i = 0; i < getAuctionQu() / 2; i++) {
            int bidderBid = getBidder().placeBid();
            int opponentBid = getOpponent().placeBid();

            getBidder().bids(bidderBid, opponentBid);
            getOpponent().bids(opponentBid, bidderBid);
        }
        List<AuctionTransaction> auctionTransactions = getBidder().getAuctionInformation()
                .getTransactions();
        int bidderTotalQuReceived = auctionTransactions.stream()
                .mapToInt(AuctionTransaction::getBidderQu)
                .sum();
        int opponentTotalQuReceived = auctionTransactions.stream()
                .mapToInt(AuctionTransaction::getOpponentQu)
                .sum();
        System.out.println("Auction result that own max QU won is " + bidderTotalQuReceived + "vs opponent " + opponentTotalQuReceived);
        System.out.println("cash remained for bidder " + bidder.getCash() + "vs cash remained for opponent " + opponent.getCash());

        if (bidderTotalQuReceived > opponentTotalQuReceived || (bidderTotalQuReceived == opponentTotalQuReceived
                && getBidder().getCash() > getOpponent().getCash())) {
            //bidder wins in case more QU or same QU but more MU retained
            return GameResult.getGameResultFromString("win");
        } else if (bidderTotalQuReceived < opponentTotalQuReceived || (bidderTotalQuReceived == opponentTotalQuReceived
                && getBidder().getCash() < getOpponent().getCash())) {
            //bidder loses in case less QU or same QU but less MU retained
            return GameResult.getGameResultFromString("lose");
        } else {
            //draw
            return GameResult.getGameResultFromString("draw");
        }
    }

    public BidderImpl getBidder() {
        return bidder;
    }

    public BidderImpl getOpponent() {
        return opponent;
    }

    public int getAuctionQu() {
        return auctionQu;
    }
}
