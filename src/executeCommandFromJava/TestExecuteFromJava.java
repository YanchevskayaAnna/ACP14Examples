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

        String output = "";
        String error = "";

        try {
            Process p;
            p = Runtime.getRuntime().exec(command);
            p.waitFor();
            output = readAll(p.getInputStream());
            error = readAll(p.getErrorStream());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return error == ""?output:error;
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
