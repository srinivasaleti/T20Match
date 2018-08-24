import context.ContextHolder;
import controller.GameController;

public class App {
    public static void main(String[] args) {
        int totalScore = 40;
        int totalOvers = 4;
        GameController gameController = new GameController(totalOvers, totalScore, ContextHolder.getTeam());

        gameController.start();
    }
}
