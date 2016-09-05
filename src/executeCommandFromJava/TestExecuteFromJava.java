package executeCommandFromJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.concurrent.ExecutionException;

/**
 * Created by Anna on 04.09.2016.
 */

public class TestExecuteFromJava {

    public static final String TEST_JAVA = "resources/Test.java";

    public static void main(String[] args) {

        try {
            executeCommand("javac " + TEST_JAVA);
            System.out.println("Compiled!");
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static String executeCommand(String command) throws ExecutionException {

        try {
            Process process = Runtime.getRuntime().exec(command);
            process.waitFor();
            int resultCode = process.exitValue();
            String output = readAll(process.getInputStream());
            String error = readAll(process.getErrorStream());

            if (resultCode != 0 || !error.isEmpty()) {
                throw new ExecutionException("Bad code\n" + error, null);
            }

            return output;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "";
    }

    private static String readAll(InputStream is) {
        StringBuffer output = new StringBuffer();
        BufferedReader reader =
                new BufferedReader(new InputStreamReader(is));
        String line;
        try {
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
            return output.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output.toString();
    }

}
