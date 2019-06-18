package mobots.mbot.model;

public class Base implements Actor {
	
	private Coordinates coordinates;
	private int hp;
	private String id;
	private Unit newUnit;
	private String owner;

	public Base() {
	}

	@Override
	public void accept(ActorVisitor visitor) {
		visitor.visitBase(this);
	}

	@Override
	public int getOrder() {
		return Integer.MAX_VALUE;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	@Override
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Unit getNewUnit() {
		return newUnit;
	}

	public void setNewUnit(Unit newUnit) {
		this.newUnit = newUnit;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Base [coordinates=" + coordinates + ", hp=" + hp + ", id=" + id + ", newUnit=" + newUnit + ", owner="
				+ owner + "]";
	}

}
