package net.sm.core.task;

import javafx.concurrent.Task;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import net.sm.core.dialog.AlertBuilder;

import java.util.HashMap;

public class TaskManager {

    public static void run(AlertBuilder alertBuilder, Stage stage, String waitMessage, TaskInterface operation) {

        Alert wait = alertBuilder.wait(stage, waitMessage);

        Task<HashMap> task = new Task<HashMap>() {

            {
                setOnSucceeded(value -> {
                    wait.close();
                    operation.feedback(getValue());
                });

                setOnCancelled(value -> {
                    wait.close();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("error", "Cancelled");
                    operation.feedback(hashMap);
                });

                setOnFailed(value -> {
                    wait.close();

                    HashMap<String, Object> hashMap = new HashMap<>();
                    hashMap.put("error", "Failed");
                    operation.feedback(hashMap);
                });
            }

            @Override
            protected HashMap call() {

                HashMap<String, Object> hashMap = new HashMap<>();
                operation.start(hashMap);
                return hashMap;
            }
        };

        wait.show();

        Thread thread = new Thread(task);
        thread.start();
    }
}
