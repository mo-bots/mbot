package mobots.mbot.logic.context;

import mobots.mbot.logic.context.state.WorkerStateIdle;
import mobots.mbot.model.Unit;

public class WorkerContext extends ActorContext<Unit> {
    public WorkerContext(String id, Unit actor) {
        super(id, actor, new WorkerStateIdle());
    }
}
