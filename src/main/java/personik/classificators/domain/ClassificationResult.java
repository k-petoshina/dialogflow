package personik.classificators.domain;

import java.util.ArrayList;
import java.util.List;

public class ClassificationResult {

    private Intent intent;
    private List<Entity> entities;

    public ClassificationResult(Intent intent, List<Entity> entities) {
        this.intent = intent;
        this.entities = entities;
    }

    public ClassificationResult(Intent intent) {
        this.intent = intent;
    }

    public Intent category() {
        return intent;
    }

    public List<Entity> entities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        if (entities == null) {
            entities = new ArrayList<Entity>();
        }
        entities.add(entity);
    }
}
