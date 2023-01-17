package com.example.tradingbod.enums;

import org.springframework.util.StringUtils;

public enum GameResult {
    WIN,
    LOSE,
    DRAW;
    /**
     * Convert {@link GameResult} string value to enum.
     *
     * @param value String representation of {@link GameResult} to be converted to enum
     * @return Value of {@link GameResult}, null if impossible to convert
     */

    public static GameResult getGameResultFromString(String value){
        GameResult result=null;
        if (StringUtils.hasText(value)){
            switch (value.toLowerCase()){
                case "win":
                    result = GameResult.WIN;
                    break;
                case "lose":
                    result = GameResult.LOSE;
                    break;
                case "draw":
                    result = GameResult.DRAW;
                    break;
                default:
                    result = null;
                    break;
            }
        }
        return result;
    }
}
