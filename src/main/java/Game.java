import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Game {
    private int lifeNumber;
    private int moneyAmount;

    public int getLifeNumber() {
        return lifeNumber;
    }

    public void setLifeNumber(int lifeNumber) {
        this.lifeNumber = lifeNumber;
    }

    public int getMoneyAmount() {
        return moneyAmount;
    }

    public void setMoneyAmount(int moneyAmount) {
        this.moneyAmount = moneyAmount;
    }

    private ArrayList<Award> createAwardsList() {
        ArrayList awardsList = new ArrayList(Arrays.asList(Award.FIVE_EURO, Award.FIVE_EURO, Award.FIVE_EURO, Award.FIVE_EURO, Award.FIVE_EURO,
                Award.TWENTY_EURO, Award.TWENTY_EURO, Award.ONE_HUNDRED_EURO, Award.EXTRA_LIFE, Award.GAME_OVER, Award.GAME_OVER, Award.GAME_OVER));
        return awardsList;
    }

    public ArrayList<Award> createAwardsListExtra(boolean secondGamePlayed) {
        return secondGamePlayed ? new ArrayList(Arrays.asList(Award.FIVE_EURO, Award.TEN_EURO, Award.TWENTY_EURO)) :
                new ArrayList(Arrays.asList(Award.FIVE_EURO, Award.TEN_EURO, Award.TWENTY_EURO, Award.SECOND_CHANCE));
    }

    private List<Box> createBoxList(List<Award> awardsList) {
        List<Box> boxList = new ArrayList<Box>();
        Random random = new Random();
        int awardInitialSize = awardsList.size();
        for (int i = 0; i < awardInitialSize; i++) {
            int index = random.nextInt(awardsList.size());
            Award randomAward = awardsList.get(index);
            Box newBox = new Box(randomAward);
            boxList.add(newBox);
            awardsList.remove(index);
        }
        return boxList;
    }

    private int playBaseGame(List<Box> boxList) {
        Random random = new Random();
        while (getLifeNumber() != 0) {
            int index = random.nextInt(boxList.size());
            handleResult(boxList, index);
            boxList.remove(index);
        }
        return moneyAmount;
    }

    private int playExtraGame(List<Box> boxList) {
        Random random = new Random();
        handleResult(boxList, random.nextInt(boxList.size()));
        return moneyAmount;
    }

    private void handleResult(List<Box> boxList, int index) {
        switch (boxList.get(index).getAward()) {
            case FIVE_EURO:
                moneyAmount += 5;
                break;
            case TEN_EURO:
                moneyAmount += 10;
                break;
            case TWENTY_EURO:
                moneyAmount += 20;
                break;
            case ONE_HUNDRED_EURO:
                moneyAmount += 100;
                break;
            case EXTRA_LIFE:
                lifeNumber++;
                break;
            case GAME_OVER:
                lifeNumber--;
                break;
            case SECOND_CHANCE:
                play(moneyAmount, true);
                break;
            default:
                break;
        }
    }

    private int play(int moneyStart, boolean secondGamePlayed) {
        List<Award> awardsList = createAwardsList();
        List<Box> boxList = createBoxList(awardsList);
        setLifeNumber(1);
        setMoneyAmount(moneyStart);
        playBaseGame(boxList);
        List<Award> awardsListExtra = createAwardsListExtra(secondGamePlayed);
        List<Box> boxListExtra = createBoxList(awardsListExtra);
        playExtraGame(boxListExtra);
        return moneyAmount;
    }

    public int playGame() {
        int gamePrice = play(0, false);
        return gamePrice;
    }
}
