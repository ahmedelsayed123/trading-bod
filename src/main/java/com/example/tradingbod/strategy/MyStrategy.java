package com.example.tradingbod.strategy;

import com.example.tradingbod.auction.BidderInformation;
import com.example.tradingbod.model.AuctionTransaction;
import com.example.tradingbod.utils.StrategyUtil;

import java.util.Random;

/**
 * This class Represents my optimized strategy for bidding based on some factors like(# of QU of auction , opponent last N bids , evict opponent with
 * random bids in the middle,...etc)
 */
public class MyStrategy implements BidStrategy {
    private BidderInformation bidderInformation;

    @Override
    public void init(BidderInformation bidderInformation) {
        this.bidderInformation = bidderInformation;
    }

    @Override
    public int nextBid() {
        AuctionTransaction lastTransaction = bidderInformation.getAuctionInformation()
                .getTransactions()
                .stream()
                .reduce((first, second) -> second)
                .orElse(null);
        int numberOfQuantityAchieved = bidderInformation.getOwnQuantity();
        int numberOfQuantityAchievedByOpponent = bidderInformation.getAuctionInformation()
                .getTransactions()
                .stream()
                .mapToInt(AuctionTransaction::getOpponentQu)
                .sum();

        /*initial bid is important as you don't know what your opponent is bid  so based on the auction quantity
         *and your cash you put the bid using this strategy
         */
        if (lastTransaction == null) {
            /*
             *Return maximum bid if there is only one game
             */
            if (bidderInformation.getAuctionQuantity() == 2) {
                return bidderInformation.getCash();
            }
            /*
             *  if the auction quantity is <=10 this means there is maximum 5 games so our bid will be in range from [ (yourCash/numbeOfGames) to  yourCash]
             *  where (yourCash/numbeOfGames) is avg cash per game
             *  this technique we do not to waste money and try to win depending on the number of games will be played
             *  else  if the auction quantity is large then more games will be played so our bid will be in range from [0 to  (yourCash/numbeOfGames) ]
             *  where (yourCash/numbeOfGames) is avg cash per game
             */
            if (bidderInformation.getAuctionQuantity() <= 10) {
                return new Random().nextInt(
                        bidderInformation.getCash() - (bidderInformation.getCash() / (bidderInformation.getAuctionQuantity() / 2))) + (
                        bidderInformation.getCash() / (bidderInformation.getAuctionQuantity() / 2));
            } else {

                return new Random().nextInt(bidderInformation.getCash() / (bidderInformation.getAuctionQuantity() / 2));
            }
        }
        /* this for saving money as much as i can when i know that i already won the game
         * or
         *this is for saving money if you new that you already lost the game start bidding with zeros
         */
        if (numberOfQuantityAchieved > (bidderInformation.getAuctionQuantity() + numberOfQuantityAchievedByOpponent)
                || numberOfQuantityAchievedByOpponent > (bidderInformation.getAuctionQuantity() + numberOfQuantityAchieved)) {

            return 0;
        }
        /*
         * if opponent finished his cash then we don't need to bid with high MU
         */
        if (bidderInformation.getOpponentCash() == 0) {
            return 1;
        }

        /*
         *If opponent consistently bids the same amount we can bid with their bid+1
         */
        if (bidderInformation.getAuctionInformation()
                .getTransactions()
                .size() > 2 && StrategyUtil.opponentBidsTheSameLastNRounds(2, bidderInformation)) {
            return StrategyUtil.getLastOpponentBid(bidderInformation) + 1;

        }

        /*
         *  Check if it is possible to win by placing opponent's cash + 1 (when opponent's cash is too small)
         */
        long minimumTurnsToWin = (bidderInformation.getAuctionQuantity() / 2) - (bidderInformation.getOwnQuantity() / 2);
        if (minimumTurnsToWin > 0 && bidderInformation.getCash() >= (bidderInformation.getOpponentCash() + 1) * minimumTurnsToWin) {
            return bidderInformation.getOpponentCash() + 1;
        }

        /*
         * try to evict my opponent if the QU is large we can evict him in 20 % of the games by giving random bids
         * in the bound of (0 to avg(cash/game)) so he can waste more money , and we can take advantage
         */
        if (bidderInformation.getAuctionQuantity() > 20) {
            return (int) Math.ceil(new Random().nextInt(bidderInformation.getCash() / (bidderInformation.getAuctionQuantity() / 2)) * 0.2);
        }

        /*try predicting next bid for opponent based on the previous bids/game
         * by adding to the previous winner 1 or 2
         */
        return lastTransaction.getBidderQu() == 0
                ? (int) Math.ceil(lastTransaction.getOpponentAmount() + (new Random().nextBoolean() ? 1 : 2)) > bidderInformation.getCash()
                ? new Random().nextInt(
                bidderInformation.getCash())
                : (int) Math.ceil(lastTransaction.getOpponentAmount() + (new Random().nextBoolean() ? 1 : 2))
                : (int) Math.ceil(lastTransaction.getBidderAmount() + (new Random().nextBoolean() ? 1 : 2)) > bidderInformation.getCash()
                        ? new Random().nextInt(
                        bidderInformation.getCash())
                        : (int) Math.ceil(lastTransaction.getBidderAmount() + (new Random().nextBoolean() ? 1 : 2));

    }

}
