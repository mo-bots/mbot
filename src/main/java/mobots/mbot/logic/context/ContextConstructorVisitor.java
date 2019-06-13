package mobots.mbot.logic.context;

import mobots.mbot.model.Base;
import mobots.mbot.model.Unit;
import mobots.mbot.model.ActorVisitor;

public class ContextConstructorVisitor implements ActorVisitor {

    private ActorContext actorContext;


    public ActorContext getActorContext() {
        return actorContext;
    }

    @Override
    public void visitBase(Base base) {
        actorContext = new BaseContext(base.getId(), base);
    }

    @Override
    public void visitWorker(Unit unit) {
        actorContext = new WorkerContext(unit.getId(), unit);
    }

    @Override
    public void visitHorseman(Unit unit) {
        actorContext = new HorsemanContext(unit.getId(), unit);
    }

    @Override
    public void visitWarrior(Unit unit) {
        actorContext = new WarriorContext(unit.getId(), unit);
    }

    @Override
    public void visitArcher(Unit unit) {
        actorContext = new ArcherContext(unit.getId(), unit);
    }
}
