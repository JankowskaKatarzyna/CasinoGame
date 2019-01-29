public class GamePlay {
    public static void main(String[] args) {
        Game newGame = new Game();
        int totalAward = 0;
        int numberOfGames = 10000000;
        for (int i = 0; i < numberOfGames; i++) {
            totalAward += newGame.playGame();
        }
        double average = totalAward / (double) numberOfGames;
        System.out.printf("Average winner award estimated on the base of %d games is: %.1f", numberOfGames, average);
    }
}
