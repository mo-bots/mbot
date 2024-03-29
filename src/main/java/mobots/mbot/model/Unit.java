package mobots.mbot.model;

public class Unit implements Actor {

	private int actionPoints;
	private Coordinates coordinates;
	private int cost;
	private int damage;
	private boolean entrench;
	private int hp;
	private String id;
	private UnitName name;
	private String owner;
	private int rangeOfAttack;
	
	public Unit() {
	}

	@Override
	public void accept(ActorVisitor visitor) {
		name.accept(visitor,this);
	}

	@Override
	public int getOrder() {
		return name.getOrder();
	}

	public int getActionPoints() {
		return actionPoints;
	}

	public void setActionPoints(int actionPoints) {
		this.actionPoints = actionPoints;
	}

	public Coordinates getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isEntrench() {
		return entrench;
	}

	public void setEntrench(boolean entrench) {
		this.entrench = entrench;
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

	public UnitName getName() {
		return name;
	}

	public void setName(UnitName name) {
		this.name = name;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public int getRangeOfAttack() {
		return rangeOfAttack;
	}

	public void setRangeOfAttack(int rangeOfAttack) {
		this.rangeOfAttack = rangeOfAttack;
	}

	@Override
	public String toString() {
		return "Unit [actionPoints=" + actionPoints + ", coordinates=" + coordinates + ", cost=" + cost + ", damage="
				+ damage + ", entrench=" + entrench + ", hp=" + hp + ", id=" + id + ", name=" + name + ", owner="
				+ owner + ", rangeOfAttack=" + rangeOfAttack + "]";
	}
	
}
