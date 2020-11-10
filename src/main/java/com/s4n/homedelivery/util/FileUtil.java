package com.s4n.homedelivery.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    public static  List<String> loadFile(String fileName) {
        List<String> lineList = new ArrayList<>();
        Path path = Paths.get( "src", "main", "resources", fileName);
        File file = path.toFile();
        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line=br.readLine()) != null) {
                lineList.add(line);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lineList;
    }

    public static void appendFile(String fileName, String line) {
        try {
            Path path = Paths.get( "src", "main", "resources", fileName);
            File file = path.toFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(line);
            bw.newLine();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectoryContent(String directory) {
        Path path = Paths.get( "src", "main", "resources", directory);
        File d = path.toFile();
        try {
            FileUtils.cleanDirectory(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
