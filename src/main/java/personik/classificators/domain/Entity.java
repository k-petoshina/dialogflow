package personik.classificators.domain;

public class Entity {

    private String name;
    private String value;

    public Entity(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String name() {
        return name;
    }

    public String value() {
        return value;
    }
}
