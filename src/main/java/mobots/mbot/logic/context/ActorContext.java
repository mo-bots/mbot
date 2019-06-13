package mobots.mbot.logic.context;

import mobots.mbot.logic.context.state.ActorState;
import mobots.mbot.model.ActorUnit;

import java.util.Optional;

public abstract class ActorContext<T extends ActorUnit> {

    private final String id;
    private final T actor;

    private ActorState<ActorContext<T>> state;

    public ActorContext(String id, T actor, ActorState<?> initialState) {
        this.id = id;
        this.actor = actor;
        this.state = (ActorState<ActorContext<T>>) initialState;
    }

    public void update(Input input) {
        Optional<? extends ActorState<ActorContext<T>>> newState = state.handleInput(this, input);
        if (newState.isPresent()) {
            this.state = newState.get();
            this.state.update(this, input);
        }

    }

    public T getActor() {
        return actor;
    }
}
