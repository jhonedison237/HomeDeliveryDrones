package com.s4n.homedelivery.test.util;

import org.apache.commons.io.FileUtils;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class FileUtil {

    public static String loadFile(String fileName, Path path) {
//        File file = new File(FileUtil.class.getClassLoader().getResource(fileName).getFile());

        StringBuilder content = new StringBuilder();
        File file = path.resolve(fileName).toFile();

        try {
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);

            String line;

            while ((line=br.readLine()) != null) {
                content.append(line);
                content.append("\n");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return content.toString();
    }

    public static void writeContentToFile(String fileName, Path path, String content) {
        try {
            File file = path.resolve(fileName).toFile();
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void deleteDirectoryContent(Path path) {
        File d = path.toFile();
        try {
            FileUtils.cleanDirectory(d);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int countFilesInDirectory(Path path) {
        File file = path.toFile();
        File[] fileList = file.listFiles();
        return fileList != null ? (int) Stream.of(fileList).filter(File::isFile).count() : 0;
    }

}
