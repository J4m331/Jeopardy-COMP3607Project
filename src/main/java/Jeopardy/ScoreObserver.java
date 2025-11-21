package Jeopardy;

public interface ScoreObserver extends Observer {
    void UpdateScore(int score);
}
