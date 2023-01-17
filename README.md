# trading-bod
### Installation
1. Clone this repository
    ```commandline
    git clone https://github.com/ahmedelsayed123/trading-bod.git
    
### Project Class Diagram
![Screenshot (32)](https://user-images.githubusercontent.com/9481273/212879752-ece6bf8c-7f73-47dc-ad8d-1ab717204609.png)

#### 1-BidderImpl
Main class that's responsible the bidding and transactions for the auction `BidderImpl`. 

You can create new instance and forwarding your Strategy
```java

 BidderImpl bidder = new BidderImpl(1000, 50, new MyStrategy());
 BidderImpl opponent = new BidderImpl(1000, 50, new AverageStrategy());
```

### Strategy

the project contains the strategy that i created and another two strategies (averageStrategy,randomStrategy) that it competes with thim

#### MyStrategy
 I build this strategy based on some factors done from my research to try to win an auction 
 1-initial bid is important as you don't know what your opponent's bid  so based on the auction quantity and your cash you put the bid 
   i-Return maximum bid if there is only one game
   iv-if the auction quantity is <=10 this means there is maximum 5 games so our bid will be in range from [(yourCash/numbeOfGames) to yourCash] 
   iiv- if the auction quantity is large then more games will be played so our bid will be in range from [0 to (yourCash/numbeOfGames)]
   hint : this technique we do not waste money and try to win depending on the number of games will be played , (yourCash/numbeOfGames) is is avg cash per game
2-if i knew that i already won or lost the game i don't waste my money and save them for maybe other auctions
3-if my opponent finished his cash then we don't bid with high MU we can bid with 1
4-if opponents bids for the last n number of time with the same bid we can use strategy of bidding his bid+1 
5-try checking while playing that in comparing minimumTurnsToWin with opponents cash(if it is to small) we can bid his cash+1 using condition if my cash >= (opponent's cash+1)*minimumTurnsToWin
6-try to evict my opponent if the QU is large we can evict him in 20 % of the games by giving random bids , in the bound of (0 to avg(cash/game)) so he can waste more money , and we can take advantage
7-if we didn't go to those condition we will try predicting next bid for opponent based on the previous bids/game by adding to the previous winner 1 or 2

    
