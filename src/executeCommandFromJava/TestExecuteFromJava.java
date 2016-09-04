package executeCommandFromJava;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Anna on 04.09.2016.
 */
public class TestExecuteFromJava {

    public static void main(String[] args) {
        String out = executeCommand("javac " + "D:\\ACP14\\ACP14\\src\\Test.java");
        if (out.length() == 0) System.out.println("Compiled!");
        else System.out.println("Bad code\n" + out);

        System.out.println(out);
    }

    public static String executeCommand(String command) {

        StringBuffer output = new StringBuffer();
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
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return output.toString();
    }

}
