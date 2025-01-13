import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        String regex = "\\b \\b";
        Pattern p = Pattern.compile(regex);

        Scanner in = new Scanner(System.in);

        System.out.println("Ingrese una cadena de texto:");
        String input = in.nextLine();

        Matcher m = p.matcher(input);

        int count = 0;
        String[] words = input.trim().split("\\s+");

        if (input.trim().isEmpty()) {
            System.out.println("Se ingresó una cadena de caracteres vacía");
        } else {
            for (String word : words) {
                count++;
                System.out.println("Palabra " + count + ": " + word);
            }
        }

        System.out.println("La frase ingresada cuenta con: " + count + " palabras.");

        in.close();

        String returnedString = words[0];

        for (int i = 0; i < words.length - 1; i++) {

            if (words[i + 1].equals(words[i])) {
                words[i + 1] = "";
            }
            returnedString = returnedString.concat(words[i + 1] + " ");
        }
        System.out.println(returnedString);
    }
}