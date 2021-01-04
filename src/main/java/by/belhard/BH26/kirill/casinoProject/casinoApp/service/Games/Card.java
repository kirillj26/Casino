package by.belhard.BH26.kirill.casinoProject.casinoApp.service.Games;

public class Card {
    final String suit;
    final int rank;
    final char colour;

    public Card(String suit, int rank, char colour) {
        this.suit = suit;
        this.rank = rank;
        this.colour = colour;
    }


    public int getRank() {
        return rank;
    }

    @Override
    public String toString() {
        return "Card{" +
                "suit='" + suit + '\'' +
                ", rank=" + rank +
                ", colour=" + colour +
                "}";
    }
}