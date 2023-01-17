# trading-bod
### Installation
1. Clone this repository
    ```commandline
    git clone https://github.com/ahmedelsayed123/trading-bod.git
    
### Project Class Diagram
![Screenshot (32)](https://user-images.githubusercontent.com/9481273/212879752-ece6bf8c-7f73-47dc-ad8d-1ab717204609.png)

#### 1-BidderImpl
Main class that's responsible for execution and redirection of logic called `BidderImpl`. 

You can create new instance and forwarding your Strategy
```java

new BidderImpl(new MyStrategy()) // Create Bidder with custom strategy
    
