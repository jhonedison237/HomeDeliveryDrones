package com.s4n.homedelivery.test;

import com.s4n.homedelivery.model.Drone;
import com.s4n.homedelivery.test.util.FileUtil;
import org.junit.BeforeClass;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertEquals;

public class DeliveryAppTest {

    private static final String PATH_TEST_RESOURCES = "src/test/resources";
    private static final String PATH_RESOURCES = "src/main/resources";
    private static final String EXPECTED_RESPONSE = "expectedResponse";
    private static final String TEST_INPUTS = "testInputs";
    private static final String IN_DIRECTORY = "in";
    private static final String OUT_DIRECTORY = "out";

    private static ExecutorService executorDrones;

    @BeforeClass
    public static void setup() {

        FileUtil.deleteDirectoryContent(Paths.get(PATH_RESOURCES, IN_DIRECTORY));
        FileUtil.deleteDirectoryContent(Paths.get(PATH_RESOURCES, OUT_DIRECTORY));

        executorDrones = Executors.newFixedThreadPool(3);

        List<Drone> droneList = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            droneList.add(new Drone(String.valueOf(i), 3));

            String fileNameTestInput = "inputDrone" + i + ".txt";
            String testInputContent = FileUtil.loadFile(fileNameTestInput, Paths.get(PATH_TEST_RESOURCES, TEST_INPUTS));
            String fileName = "in" + i + ".txt";
            FileUtil.writeContentToFile(fileName, Paths.get(PATH_RESOURCES, IN_DIRECTORY), testInputContent);
        }
        executeDrones(droneList);
    }

    @Test
    public void checkDifferencesForDrones() {

        for (int i = 1; i <= 5; i++) {
            String result = FileUtil.loadFile("out" + i + ".txt", Paths.get(PATH_RESOURCES, OUT_DIRECTORY));
            String expected = FileUtil.loadFile("expectedDrone" + i + ".txt", Paths.get(PATH_TEST_RESOURCES, EXPECTED_RESPONSE));

            assertEquals("Diferencias en drone " + i, expected, result);
        }
    }

    @Test
    public void checkNumberOfProducedFiles() {
        Path path = Paths.get( PATH_RESOURCES, OUT_DIRECTORY);
        int result = FileUtil.countFilesInDirectory(path);

        assertEquals(5, result);
    }

    private static void executeDrones(List<Drone> drones) {
        drones.parallelStream()
                .map(executorDrones::submit)
                .forEach(f -> {
                    try {
                        f.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                });
    }

}
