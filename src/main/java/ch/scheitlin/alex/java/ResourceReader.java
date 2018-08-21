package ch.scheitlin.alex.java;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Provides a function to read files from the resource folder.
 */
public class ResourceReader {
    /**
     * Reads the content of a file stored in the resource folder (src/main/resources/) to a {@code String}.
     *
     * @param fileName the name of the file to read
     * @return the content of the specified file
     */
    public static String readResourceFile(String fileName) throws FileNotFoundException {
        StringBuilder result = new StringBuilder();
        File file = new File(ResourceReader.class.getResource("/" + fileName).getFile());
        Scanner scanner = new Scanner(file);

        while (scanner.hasNextLine()) {
            result.append(scanner.nextLine());

            if (scanner.hasNextLine()) {
                result.append("\n");
            }
        }

        scanner.close();

        return result.toString();
    }
}
