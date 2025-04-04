package com.gamerentalclub.models;

public class Rental {
    private int rentalId;
    private int gameId;
    private int userId;
    private String rentDate;
    private String expectedReturnDate;
    private String status;

    public Rental(int gameId, int userId, String rentDate, String expectedReturnDate) {
        this.gameId = gameId;
        this.userId = userId;
        this.rentDate = rentDate;
        this.expectedReturnDate = expectedReturnDate;
        this.status = "Active";
    }

    public int getRentalId() { return rentalId; }
    public void setRentalId(int rentalId) { this.rentalId = rentalId; }
    public int getGameId() { return gameId; }
    public int getUserId() { return userId; }
    public String getRentDate() { return rentDate; }
    public String getExpectedReturnDate() { return expectedReturnDate; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}