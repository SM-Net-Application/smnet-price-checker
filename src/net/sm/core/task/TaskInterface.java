package net.sm.core.task;

import java.util.HashMap;

public interface TaskInterface {

    public abstract void start(HashMap<String, Object> hashMap);

    public abstract void feedback(HashMap<String, Object> hashMap);
}
