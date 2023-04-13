import java.util.ArrayList;
import java.util.Scanner;

public class ChackNoris {
	private static final Scanner scanner = new Scanner(System.in);
	
	
	static void menu() {
		while (true) {
			System.out.println("Please input operation (encode/decode/exit):");
			String inputMessage = scanner.nextLine();
			switch (inputMessage) {
				case "encode":
					encoded();
					break;
				case  "decode":
					System.out.println("Input encoded string:");
					String message = scanner.nextLine();
					if (!checkValidInputUsers(message)) {
						System.out.println("Ошибка");
						break;
					}
					String output = decoded(message);
					System.out.println(output);
					break;
				case "exit":
					System.out.println("Bye!");
					return;
				
				default:
					System.out.println("There is no " + inputMessage + " operation");
			}
		}
	}
	
	static boolean checkValidInputUsers(String message) {
		boolean isFlag = true;
		
		if (checkFirstBlock(message)) return false;
		
		if (checkZeroinSpaceMessage(message)) return false;
		
		if (checkNumberBlocksOdd(message)) return false;
		
		if (checkFirstBlockMessage(message)) return false;
		
		
		if (isValidBinaryStringLength(decoded(message))) return false;
		
		return isFlag;
	}
	
	private static String decoded(String message) {
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
		return binaryToString(sb.toString());
	}
	
	private static boolean isValidBinaryStringLength(String binaryString) {
		return binaryString.length() % 7 != 0;
	}
	
	private static boolean checkFirstBlockMessage(String message) {
		int whiteSpaceCounter = 0, startingIndex = 0;
		for (int i = 0; i < message.length(); i++) {
			if (message.charAt(i) == ' ') {
				whiteSpaceCounter++;
			}
			
			if (whiteSpaceCounter == 2 || i == message.length() - 1) {
				String[] pair = message.substring(startingIndex, i + 1).trim().split(" ");
				if (pair[0].length() > 2) {
					return true;
				}
				
				startingIndex = i;
				whiteSpaceCounter = 0;
			}
		}
		return false;
	}
	
	private static boolean checkNumberBlocksOdd(String message) {
		String[] blocks = message.split(" ");
		
		if (blocks.length % 2 != 0) { // работает
			return true;
		}
		return false;
	}
	
	private static boolean checkZeroinSpaceMessage(String message) {
		for (int i = 0; i < message.length(); i++) { // работает
			if (message.charAt(i) != '0' && message.charAt(i) != ' ') {
				return true;
			}
		}
		return false;
	}
	
	private static boolean checkFirstBlock(String message) {
		String checkForInvalidCharacters = message.replaceAll("\\s", "")
				.replaceAll("0", "");
		if (checkForInvalidCharacters.length() != 0) {
			return true;
		}
		return false;
	}
	private static void encoded() {
		System.out.println("Input string:");
		String message = scanner.nextLine();
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
		System.out.println("Encoded string:");
		System.out.println(encodedMessage.toString().trim() + "\n");
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
	
	public static void main(String[] args) {
	menu();
	}
}
