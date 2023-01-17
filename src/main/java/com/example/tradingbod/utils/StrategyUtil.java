package com.example.tradingbod.utils;

import com.example.tradingbod.auction.BidderInformation;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * this class represents the methods we need to use while implementing the strategy
 */
public class StrategyUtil {
    /**
     * @param n
     * @param bidderInformation
     * @return true if last nth last rounds got the same bid
     */
    public static boolean opponentBidsTheSameLastNRounds(int n, BidderInformation bidderInformation) {
        int lastBid = getLastOpponentBid(bidderInformation);
        List<Integer> getLastNOpponentBids = bidderInformation.getAuctionInformation()
                .getTransactions()
                .stream()
                .map(i -> i.getOpponentAmount())
                .skip(bidderInformation.getAuctionInformation()
                        .getTransactions()
                        .size() - n)
                .collect(
                        Collectors.toList());

        return getLastNOpponentBids.stream()
                .sorted(Collections.reverseOrder())
                .skip(1)
                .allMatch(bid -> bid == lastBid);

    }

    /**
     * @param bidderInformation
     * @return getLastOpponentBid
     */
    public static int getLastOpponentBid(BidderInformation bidderInformation) {
        return bidderInformation.getAuctionInformation()
                .getTransactions()
                .stream()
                .reduce((first, second) -> second)
                .get()
                .getOpponentAmount();
    }

}
