package it.sevenbits.homework6.Repository;

import java.util.HashMap;
import java.util.Set;
import java.util.UUID;

public class TaskRepository {
    private static TaskRepository instance;
    private HashMap<String,String> tasks;
    private static String nextId;

    private TaskRepository(){
        tasks = new HashMap<String, String>();
    }

    public static TaskRepository getInstance() {
        if (instance == null) {
            instance = new TaskRepository();
        }
        return instance;
    }

    public String add(String value) {
        synchronized (this) {
            nextId = getNextId();
            tasks.put(nextId,value);
            return nextId;
        }
    }

    public String get(String id) {
        synchronized (this) {
            return tasks.getOrDefault(id,"");
        }
    }

    public String delete(String id) {
        synchronized (this) {
            if (tasks.containsKey(id)) {
                tasks.remove(id);
                return id;
            }
            return "";
        }
    }

    public boolean contains(String id) {
        synchronized (this) {
        return tasks.containsKey(id);
        }
    }

    public Set<String> getIdList() {
        synchronized (this) {
            return tasks.keySet();
        }
    }

    private String getNextId() {
        return UUID.randomUUID().toString();
    }
}
