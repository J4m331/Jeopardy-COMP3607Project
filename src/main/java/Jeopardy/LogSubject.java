package Jeopardy;

public interface LogSubject extends Subject{

    void LinkLogObserver(Observer o);
    void UpdateLogObserver(LogEvent logEvent);
}
