package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.BaseContext;
import mobots.mbot.logic.context.Input;
import mobots.mbot.model.UnitName;

public class BaseStateSupply extends BaseState {


    @Override
    public void update(BaseContext context, Input input) {
            input.getGameDelegate().recruitUnit(UnitName.WORKER);
    }
}
