package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.Input;
import mobots.mbot.logic.context.WorkerContext;

import java.util.Optional;

abstract class WorkerState extends ActorState<WorkerContext> {

    @Override
    public Optional<? extends ActorState<WorkerContext>> handleInput(WorkerContext context, Input input) {
        return Optional.of(new WorkerStateGoToMine());
    }
}
