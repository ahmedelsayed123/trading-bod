package com.example.tradingbod;

import com.example.tradingbod.auction.BidderImpl;
import com.example.tradingbod.enums.GameResult;
import com.example.tradingbod.biddingGame.BiddingGame;
import com.example.tradingbod.strategy.AverageStrategy;
import com.example.tradingbod.strategy.BidStrategy;
import com.example.tradingbod.strategy.MyStrategy;
import com.example.tradingbod.strategy.RandomBidStrategy;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class TradingBodApplicationTests {

    private BiddingGame biddingGame;

    /**
     * Bidder with {@link MyStrategy} versus Opponent with {@link AverageStrategy}. bidder smart enough to understand his opponent's strategy and deal
     * with it
     */
    @Test
    void MyStrategyVsAverageStrategyWinTest() {
        //given
        BidderImpl bidder = new BidderImpl(1000, 50, new MyStrategy());
        BidderImpl opponent = new BidderImpl(1000, 50, new AverageStrategy());
        biddingGame = new BiddingGame(bidder, opponent);
        //when
        GameResult gameResult = biddingGame.tradingBodGame();
        //then
        assertEquals("WIN", gameResult.name());
    }

    /**
     * Bidder with {@link MyStrategy} versus Opponent with {@link AverageStrategy}. when there is only one game so bidder can deal against avg
     * strategy which will bid all the amount in this game so will end in a draw
     */
    @Test
    void MyStrategyVsAverageStrategyDrawTest() {
        //given
        BidderImpl bidder = new BidderImpl(1000, 2, new MyStrategy());
        BidderImpl opponent = new BidderImpl(1000, 2, new AverageStrategy());
        biddingGame = new BiddingGame(bidder, opponent);
        //when
        GameResult gameResult = biddingGame.tradingBodGame();
        //then
        assertEquals("DRAW", gameResult.name());
    }

    /**
     * Bidder with {@link MyStrategy} versus Opponent with {@link RandomBidStrategy}. bidder smart enough to deal with his opponent's strategy if he
     * can compete against him according to their MU
     */
    @Test
    void MyStrategyVsRandomBidStrategyWinTest() {
        //given
        BidderImpl bidder = new BidderImpl(1000, 50, new MyStrategy());
        BidderImpl opponent = new BidderImpl(1000, 50, new RandomBidStrategy());
        biddingGame = new BiddingGame(bidder, opponent);
        //when
        GameResult gameResult = biddingGame.tradingBodGame();
        //then
        assertEquals("WIN", gameResult.name());
    }

    //    /**
    //     * Bidder with {@link MyStrategy} versus Opponent with {@link RandomBidStrategy}. bidder try to deal with his opponent's strategy but in this case
    //     * his opponent has a power of more MU , so his probability lose is more
    //     */
    //    @Test
    //    void MyStrategyVsRandomBidStrategyLoseTest() {
    //        //given
    //        BidderImpl bidder = new BidderImpl(100, 20, new MyStrategy());
    //        BidderImpl opponent = new BidderImpl(500, 20, new RandomBidStrategy());
    //        biddingGame = new BiddingGame(bidder, opponent);
    //        //when
    //        GameResult gameResult = biddingGame.tradingBodGame();
    //        //then
    //        assertEquals("LOSE", gameResult.name());
    //    }
}
