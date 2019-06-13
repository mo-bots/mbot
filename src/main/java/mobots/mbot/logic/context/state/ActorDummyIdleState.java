package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.ActorContext;
import mobots.mbot.logic.context.Input;
import mobots.mbot.model.Unit;

import java.util.Optional;

public class ActorDummyIdleState extends ActorState<ActorContext<Unit>> {

    @Override
    public Optional<? extends ActorState<ActorContext<Unit>>> handleInput(ActorContext<Unit> context, Input input) {
        return Optional.empty();
    }

    @Override
    public void update(ActorContext<Unit> context, Input input) {

    }
}
