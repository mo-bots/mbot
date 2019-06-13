package mobots.mbot.model;

public interface ActorVisitor {
    void visitBase(Base base);

    void visitWorker(Unit unit);

    void visitHorseman(Unit unit);

    void visitWarrior(Unit unit);

    void visitArcher(Unit unit);
}
