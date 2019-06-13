package mobots.mbot.logic.context;

import mobots.mbot.logic.context.state.ActorDummyIdleState;
import mobots.mbot.model.Unit;

public class WarriorContext extends FighterContext {
    public WarriorContext(String id, Unit actor) {
        super(id, actor, new ActorDummyIdleState());
    }
}
