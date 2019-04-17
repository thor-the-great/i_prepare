package stream_api;

public class Ship {
    String id;
    String name;
    Type type;
    int crew;
    int speed;

    public Ship(String id, String name, Type type, int crew, int speed) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.crew = crew;
        this.speed = speed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public int getCrew() {
        return crew;
    }

    public void setCrew(int crew) {
        this.crew = crew;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}

enum Type {
    FIGHTER, CARGO, DESTROYER
}
