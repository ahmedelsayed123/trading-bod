package com.example.tradingbod.model;

/**
 * This class represents Auction transaction  when both participants submit their bids Created transaction cannot be changed once created
 */
public class AuctionTransaction {
    private final int bidderAmount;
    private final int opponentAmount;
    private final int bidderQu;
    private final int opponentQu;

    /**
     * @param bidderAmount
     * @param opponentAmount
     * @param bidderQu
     * @param opponentQu
     */

    public AuctionTransaction(int bidderAmount, int opponentAmount, int bidderQu, int opponentQu) {
        this.bidderAmount = bidderAmount;
        this.opponentAmount = opponentAmount;
        this.bidderQu = bidderQu;
        this.opponentQu = opponentQu;

    }

    public int getBidderAmount() {
        return bidderAmount;
    }

    public int getOpponentAmount() {
        return opponentAmount;
    }

    public int getBidderQu() {
        return bidderQu;
    }

    public int getOpponentQu() {
        return opponentQu;
    }

}
