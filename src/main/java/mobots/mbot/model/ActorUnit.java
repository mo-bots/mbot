package mobots.mbot.model;

public interface ActorUnit {
    void accept(ActorVisitor visitor);

    String getId();

    int getOrder();
}
