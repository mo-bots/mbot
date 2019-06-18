package mobots.mbot.model;

public interface Actor {
    void accept(ActorVisitor visitor);

    String getId();

    int getOrder();
}
