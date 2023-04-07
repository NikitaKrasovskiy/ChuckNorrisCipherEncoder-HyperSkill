import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input string:");
            String message = scanner.nextLine();
            scanner.close();

            StringBuilder binaryMessage = new StringBuilder();
            for (char c : message.toCharArray()) {
                String binary = Integer.toBinaryString(c);
                binaryMessage.append(String.format("%7s", binary).replace(' ', '0'));
            }

            StringBuilder encodedMessage = new StringBuilder();
            int i = 0;
            while (i < binaryMessage.length()) {
                char currentBit = binaryMessage.charAt(i);
                int count = 1;
                while (i + 1 < binaryMessage.length() && binaryMessage.charAt(i + 1) == currentBit) {
                    count++;
                    i++;
                }
                i++;

                encodedMessage.append(currentBit == '0' ? "00 " : "0 ");
                for (int j = 0; j < count; j++) {
                    encodedMessage.append("0");
                }
                encodedMessage.append(" ");
            }
            System.out.println("The result:");
            System.out.println(encodedMessage.toString().trim());
        }
}