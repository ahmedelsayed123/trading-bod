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


### Strategy

the project contains the strategy that i created and another two strategies that it competes with thim

#### MyStrategy
 I build this strategy based on some factors done from my research to try to win an auction 
    
