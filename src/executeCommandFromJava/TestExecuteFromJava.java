package executeCommandFromJava;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Anna on 04.09.2016.
 */
public class TestExecuteFromJava {

    public static void main(String[] args) {
        String out = executeCommand("javac " + "D:\\ACP14\\Test.java");
        if (out.length() == 0) System.out.println("Compiled!");
        else System.out.println("Bad code\n" + out);

        System.out.println(out);
    }

    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();
        String error = "";
        Process p;
        try {
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(p.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line + "\n");
            }
           error = readAll(p.getErrorStream());
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return error;
    }

  private static String readAll(InputStream is){
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
