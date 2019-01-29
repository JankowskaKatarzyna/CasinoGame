import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestGame {
    private ArrayList<Award> awardsListExtra = new ArrayList(Arrays.asList(Award.FIVE_EURO, Award.TEN_EURO, Award.TWENTY_EURO, Award.SECOND_CHANCE));
    private ArrayList<Award> awardsListExtraAfterSecondChance = new ArrayList(Arrays.asList(Award.FIVE_EURO, Award.TEN_EURO, Award.TWENTY_EURO));
    private Game game;

    @BeforeEach
    public void createNewGame() {
        game = new Game();
    }

    @Test
    public void lifeShouldBeZeroWhenGameEnds() {
        game.playGame();
        assertEquals(0, game.getLifeNumber());
    }

    @Test
    public void totalAwardShouldBeEqualMinimumFiveEuro() {
        game.playGame();
        assertTrue(game.getMoneyAmount() >= 5);
    }

    @Test
    public void shouldContainFourAwardsIfSecondGameHasNotBeenPlayed() {
        boolean secondGamePlayed = false;
        ArrayList<Award> awardsFromGame = game.createAwardsListExtra(secondGamePlayed);
        assertEquals(awardsListExtra, awardsFromGame);
    }

    @Test
    public void shouldContainThreeAwardsIfSecondGameHasBeenPlayed() {
        boolean secondGamePlayed = true;
        ArrayList<Award> awardsFromGame = game.createAwardsListExtra(secondGamePlayed);
        assertEquals(awardsListExtraAfterSecondChance, awardsFromGame);
    }


}
