import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Files {

    public static File[] listDirectories(String path) {
        return new File(path).listFiles(File::isDirectory);
    }

    public static File[] listFilesInDirectory(final File folder) {
        return folder.listFiles(File::isFile);
    }

    public static List<File> listAllFiles(String path) {
        var all = new ArrayList<File>();
        var list = new File(path).listFiles();
        if(list != null) {
            for(var f: list) { // in case of access error, listFiles is null
                if(f.isDirectory()) {
                    all.addAll(listAllFiles(f.getAbsolutePath()));
                } else {
                    all.add(f.getAbsoluteFile());
                }
            }
        }
        return all;
    }

    public static List<String> readLines(String filename) throws IOException {
        return Files.readLines(new File(filename).toPath().toString());
    }

    public static void zipFile(String srcFilename, String zipFilename) throws IOException {
        var srcFile = new File(srcFilename);
        try (
                var fileOut = new FileOutputStream(zipFilename);
                var zipOut = new ZipOutputStream(fileOut);
                var fileIn = new FileInputStream(srcFile);
                ) {
            var zipEntry = new ZipEntry(srcFile.getName());
            zipOut.putNextEntry(zipEntry);
            final var bytes = new byte[1024];
            int length;
            while((length = fileIn.read(bytes)) >= 0) {
                zipOut.write(bytes, 0, length);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Test file functions in here!!!");
    }

}
