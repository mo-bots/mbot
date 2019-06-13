package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.ActorContext;
import mobots.mbot.logic.context.Input;

import java.util.Optional;

public abstract class ActorState<T extends ActorContext<?>> {

    public abstract Optional<? extends ActorState<T>> handleInput(T context, Input input);

    public abstract void update(T context, Input input);
}
