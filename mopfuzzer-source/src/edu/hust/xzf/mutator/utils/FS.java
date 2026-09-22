// Modified for EvoFuzz-AE: instrumentation, scheduling, execution accounting, and portable examples.
package edu.hust.xzf.mutator.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import edu.hust.xzf.mutator.Scheduler;

public class FS {
    
    public static Boolean isPathDir(String path) {
        try {
            File file = new File(path);
            return file.exists() && file.isDirectory();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> listAllFiles(String dirPath) {
        try {
            Path dir = Paths.get(dirPath);
            List<String> ret = new ArrayList<>();
            if (Files.isDirectory(dir)) {
                Stream<Path> stream = Files.walk(dir);
                stream.filter(Files::isRegularFile)
                    .filter(p -> p.toString().endsWith(".java"))
                    .forEach(p -> {
                        ret.add(p.toAbsolutePath().toString());
                    });
                stream.close();
            }
            return ret;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getStem(String path) {
        try {
            Path p = Paths.get(path);
            String fileName = p.getFileName().toString();
            int dotIndex = fileName.lastIndexOf('.');
            if (dotIndex > 0) {
                return fileName.substring(0, dotIndex);
            } else {
                return fileName; // No extension found, return the whole name
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static List<String> readLines(String path) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            Scheduler.log.debug("Read " + lines.size() + " lines from " + path);
            return lines;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void writeLines(String path, List<String> lines) {
        try {
            Files.write(Paths.get(path), lines);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void writeString(String path, String content) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            writer.write(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createNewFile(String path) {
        try {
            Path p = Paths.get(path);
            if (!Files.exists(p)) {
                Files.createFile(p);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isExist(String path) {
        try {
            return Files.exists(Paths.get(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static String getParentDirString(String path) {
        try {
            Path p = Paths.get(path);
            Path parent = p.getParent();
            if (parent != null) {
                String ret = parent.toString();
                if (!ret.endsWith(File.separator)) {
                    ret += File.separator;
                }
                return ret;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void ensureDirExists(String dirPath) {
        try {
            Path dir = Paths.get(dirPath);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void cpFile(String srcPath, String dstDirPath) {
        try {
            Path src = Paths.get(srcPath);
            Path dstDir = Paths.get(dstDirPath);
            if (Files.exists(src) && Files.isRegularFile(src) && Files.exists(dstDir) && Files.isDirectory(dstDir)) {
                Path dst = dstDir.resolve(src.getFileName());
                Files.copy(src, dst);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getFileName(String path) {
        try {
            Path p = Paths.get(path);
            return p.getFileName().toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}
