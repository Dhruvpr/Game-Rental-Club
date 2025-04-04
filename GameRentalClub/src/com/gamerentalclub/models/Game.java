package com.gamerentalclub.models;

public class Game {
    private int gameId;
    private String title;
    private String genre;
    private int stockAvailability;
    private double rentalPrice;

    public Game(int gameId, String title, String genre, int stockAvailability, double rentalPrice) {
        this.gameId = gameId;
        this.title = title;
        this.genre = genre;
        this.stockAvailability = stockAvailability;
        this.rentalPrice = rentalPrice;
    }

    public int getGameId() { return gameId; }
    public void setGameId(int gameId) { this.gameId = gameId; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public String getGenre() { return genre; }
    public void setGenre(String genre) { this.genre = genre; }
    public int getStockAvailability() { return stockAvailability; }
    public void setStockAvailability(int stockAvailability) { this.stockAvailability = stockAvailability; }
    public double getRentalPrice() { return rentalPrice; }
    public void setRentalPrice(double rentalPrice) { this.rentalPrice = rentalPrice; }
}