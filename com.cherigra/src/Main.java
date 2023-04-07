import java.util.*;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Input string:");
            String message = scanner.nextLine();
            scanner.close();
//
//            StringBuilder binaryMessage = new StringBuilder();
//            for (char c : message.toCharArray()) {
//                String binary = Integer.toBinaryString(c);
//                binaryMessage.append(String.format("%7s", binary).replace(' ', '0'));
//            }
//
//            StringBuilder encodedMessage = new StringBuilder();
//            int i = 0;
//            while (i < binaryMessage.length()) {
//                char currentBit = binaryMessage.charAt(i);
//                int count = 1;
//                while (i + 1 < binaryMessage.length() && binaryMessage.charAt(i + 1) == currentBit) {
//                    count++;
//                    i++;
//                }
//                i++;
//
//                encodedMessage.append(currentBit == '0' ? "00 " : "0 ");
//                for (int j = 0; j < count; j++) {
//                    encodedMessage.append("0");
//                }
//                encodedMessage.append(" ");
//            }
//            System.out.println("The result:");
//            System.out.println(encodedMessage.toString().trim());
            StringBuilder sb = new StringBuilder();

            int whiteSpaceCounter = 0, startingIndex = 0;
            for (int i = 0; i < message.length(); i++) {
                if (message.charAt(i) == ' ') {
                    whiteSpaceCounter++;
                }

                if (whiteSpaceCounter == 2 || i == message.length() - 1) {
                    String pair = message.substring(startingIndex, i + 1).trim();
                    startingIndex = i;
                    whiteSpaceCounter = 0;

                    String[] pairArr = pair.split(" ");
                    String oneZero = pairArr[0].equals("0") ? "1" : "0";
                    sb.append(oneZero.repeat(pairArr[1].length()));
                }
            }
            System.out.println("The result:");
            System.out.println( binaryToString(sb.toString()));
        }
    private static String binaryToString(String binaryString) {
        ArrayList<String> lettersInBinary = new ArrayList<>();
        StringBuilder sb = new StringBuilder();

        // Split at 7.
        for (int i = 0; i < binaryString.length(); i += 7) {
            lettersInBinary.add(binaryString.substring(i, i + 7));
        }

        // Converts the binary to character.
        for (String letter : lettersInBinary) {
            sb.append((char) Integer.parseInt(letter, 2));
        }

        return sb.toString();
    }
        }
