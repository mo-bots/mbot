package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.Input;
import mobots.mbot.logic.context.WorkerContext;

import java.util.Optional;

public class WorkerStateGoToMine extends WorkerState {


    @Override
    public void update(WorkerContext context, Input input) {
        input.getGameDelegate().goToRandomMine(context.getActor());
    }
}
