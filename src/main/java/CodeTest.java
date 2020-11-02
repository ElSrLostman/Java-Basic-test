import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.runner.JUnitCore;
import org.junit.runner.Request;


public class CodeTest {
    public static void main(String[] args) {
        
        System.out.println("#### SQL ####");
        System.out.println("SELECT DINSTINCT COLUMN1 FROM TABLE;");
        System.out.println("INSERT INTO CUSTOMER (NAME, LASTNAME) VALUES ('Iban', 'Serrano');");
        System.out.println("SELECT * FROM CUSTOMERS LEFT JOIN ORDERS ON CUSTOMER.ID = ORDERS.ID_CUSTOMER;");
        System.out.println("");

        Request request;
        JUnitCore junit = new JUnitCore();

        // Call to every test
        // To call test I need to have the test class in the same package, I couldn't find how to call it from test folder
        request = Request.method(CodeTestSpec.class, "reverseArray_returnsExpectedResult");
        junit.run(request);                

        request = Request.method(CodeTestSpec.class, "uppercaseArray_returnsExpectedResult");
        junit.run(request);

        request = Request.method(CodeTestSpec.class, "findWordCount_returnsExpectedResult");
        junit.run(request);

        request = Request.method(CodeTestSpec.class, "composeU_returnsExpectedResult");
        junit.run(request);

        request = Request.method(CodeTestSpec.class, "writeContentsToConsole_returnsExpectedResult");
        junit.run(request);

        request = Request.method(CodeTestSpec.class, "handleInvalidArgument_returnsExpectedResult");
        junit.run(request);

        request = Request.method(CodeTestSpec.class, "puzzle_returnsExpectedResult");
        junit.run(request);
        

    }

    public static String[] reverseArray(String[] input) {
        // add code here
        List<String> list = Arrays.asList(input);
        Collections.reverse(list);

        input = list.toArray(new String[list.size()]);

        return input;
    }

    public static String[] uppercaseArray(String[] input) {
        // add code here

        List<String> list = Arrays.asList(input);
        list = list.stream().map(l -> l.toUpperCase()).collect(Collectors.toList());

        input = list.toArray(new String[list.size()]);

        return input;
    }

    public static int findWordCount(String text, String wordToFind) {
        // add code here

        String[] words = text.trim().split("\\s+");

        Map<String, Long> frequencyMap = Stream.of(words).map(String::toLowerCase)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return frequencyMap.get(wordToFind).intValue();
    }

    public static Function<Integer, Integer> composeU(Function<Integer, Integer> f1, Function<Integer, Integer> f2) {
        // add code here
        return f1.compose(f2);
    }

    public static void writeContentsToConsole() {
        // add code here

        String s;
        try {
            s = new String(Files.readAllBytes(Paths.get("prueba.txt")));
            System.out.println(s);
        } catch (IOException e) {
            System.out.println("File can't be found");
            e.printStackTrace();
        }
    }

    public static void handleInvalidArgument() {
        // add code here
        List<Integer> integers = Arrays.asList(3, 9, 7, 0, 10, 20);
        integers.forEach(i -> {
            try {
                System.out.println(50 / i);
            } catch (ArithmeticException e) {
                System.err.println("Arithmetic Exception occured : " + e.getMessage());
                throw e;
            }
        });
    }

    public static void puzzle() {
        // add code here

        Scanner scan= new Scanner(System.in);
        String result = "";
        Character lastChar = null;

        String text = scan.nextLine();

        for (int i = 0; i < text.length(); i++) {
            Character item = text.charAt(i);

            if (Character.isDigit(item)) {
                if (i > 0) {
                    result += ",";
                }
    
                result += item;
    
                if (item.equals(lastChar)) {
                    result += "," + "'Snap'";
                    break;
                }
    
                lastChar = item;
            }

        }
    
        System.out.println(result);

        scan.close();
    }
}