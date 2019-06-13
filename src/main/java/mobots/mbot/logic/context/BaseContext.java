package mobots.mbot.logic.context;

import mobots.mbot.logic.context.state.BaseStateIdle;
import mobots.mbot.model.Base;

public class BaseContext extends ActorContext<Base> {

    public BaseContext(String id, Base actor) {
        super(id, actor, new BaseStateIdle());
    }
}
