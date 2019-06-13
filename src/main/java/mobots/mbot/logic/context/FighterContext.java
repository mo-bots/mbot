package mobots.mbot.logic.context;

import mobots.mbot.logic.context.state.ActorState;
import mobots.mbot.model.Unit;

abstract class FighterContext extends ActorContext<Unit> {

    public FighterContext(String id, Unit actor, ActorState<?> initialState) {
        super(id, actor, initialState);
    }
}
