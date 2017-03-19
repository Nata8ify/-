package com.sleeplesstofu.quartierlatin.trag.trag;

/**
 * Created by QuartierLatin on 24/8/2559.
 */
public class Player {
    private int playerID;
    private String playerName;
    private int highScore;
    private int totalScore;
    private int rank;

    public Player(String playerName, int highScore, int totalScore, int rank) {
        this.playerName = playerName;
        this.highScore = highScore;
        this.totalScore = totalScore;
        this.rank = rank;
    }

    public Player() {
    }



    public int getPlayerID() {
        return playerID;
    }

    public void setPlayerID(int playerID) {
        this.playerID = playerID;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(int totalScore) {
        this.totalScore = totalScore;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerID=" + playerID +
                ", playerName='" + playerName + '\'' +
                ", highScore=" + highScore +
                ", totalScore=" + totalScore +
                ", rank=" + rank +
                '}';
    }
}
