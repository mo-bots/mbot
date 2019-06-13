package mobots.mbot.logic.context.state;

import mobots.mbot.logic.context.BaseContext;
import mobots.mbot.logic.context.Input;
import mobots.mbot.model.UnitName;

import java.util.Optional;

public abstract class BaseState extends ActorState<BaseContext> {

    @Override
    public Optional<? extends ActorState<BaseContext>> handleInput(BaseContext context, Input input) {
        Optional<BaseState> state = deliverSupplies(input);
        if (state.isPresent()) {
            return state;
        }
        return Optional.of(new BaseStateIdle());
    }

    private Optional<BaseState> deliverSupplies(Input input) {
        int workersCount = countUnits(input, UnitName.WORKER);
        if (workersCount < 3) {
            return Optional.of(new BaseStateSupply());
        }
        return Optional.empty();
    }

    private int countUnits(Input input, UnitName unitName) {
        return (int) input.getGameDelegate().getActive().getUnits().stream().filter((u) -> u.getName().equals(unitName)).count();
    }


}
