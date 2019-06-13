package mobots.mbot.model;

public enum UnitName {
    WORKER("W", 1),
    HORSEMAN("H", 2),
    WARRIOR("F",3),
    ARCHER("A",4);

    private final String shortcut;
    private final int order;

    UnitName(String shortcut, int order) {
        this.shortcut = shortcut;
        this.order = order;
    }

    public String getShortcut() {
        return shortcut;
    }

    public int getOrder() {
        return order;
    }

    public void accept(ActorVisitor visitor, Unit unit) {
        switch (this) {
            case WORKER: visitor.visitWorker(unit);
                break;
            case HORSEMAN: visitor.visitHorseman(unit);
                break;
            case WARRIOR:  visitor.visitWarrior(unit);
                break;
            case ARCHER:  visitor.visitArcher(unit);
                break;
            default: throw new RuntimeException("Not supported unit type " + this);
        }
    }
}
