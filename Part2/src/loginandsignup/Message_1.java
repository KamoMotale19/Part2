package loginandsignup;


// Import required libraries for JSON and file writing
import java.io.File;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Message_1 {
    public static void viewStoredMessages() {
    File file = new File("stored_messages.json");
    if (!file.exists()) {
        JOptionPane.showMessageDialog(null, "No stored messages found.");
        return;
    }

    try (Scanner scanner = new Scanner(file)) {
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            JSONArray array = new JSONArray(line);
            JSONObject msg = array.getJSONObject(0);

            String output = "Stored Message\n"
                    + "------------------\n"
                    + "ID: " + msg.getString("MessageID") + "\n"
                    + "Number: " + msg.getInt("MessageNumber") + "\n"
                    + "To: " + msg.getString("Recipient") + "\n"
                    + "Text: " + msg.getString("MessageText") + "\n"
                    + "Hash: " + msg.getString("Hash");

            JOptionPane.showMessageDialog(null, output);
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error reading stored messages: " + e.getMessage());
    }
}


    // Stores all sent messages for display
    static ArrayList<String> messages = new ArrayList<>();
    static int messageCounter = 0; // Tracks how many messages have been sent

    // Generates a random 10-digit Message ID
    public static String generateMessageID() {
        return String.valueOf((long)(Math.random() * 1_000_000_0000L));
    }

    // Checks if the recipient number starts with +27 and is the correct length
    public static boolean checkRecipientCell(String number) {
        return number.startsWith("+27") && number.length() <= 13;
    }

    // Creates a unique hash for the message (ID, number, first + last word of message)
    public static String createMessageHash(String id, int msgNum, String message) {
        String[] words = message.split(" ");
        String first = words[0].toUpperCase();
        String last = words[words.length - 1].toUpperCase();
        return id.substring(0, 2) + ":" + msgNum + ":" + first + last;
    }

    // Full message flow: collect input, validate, generate data, and act (send/disregard/store)
    public static String sendMessageFlow() {
        String recipient = JOptionPane.showInputDialog("Enter recipient phone number:");
        if (!checkRecipientCell(recipient)) {
            return "Cell phone number is incorrectly formatted or does not contain international code.";
        }

        String messageText = JOptionPane.showInputDialog("Enter message (max 250 ONLY WORDS):");
        if (messageText.length() > 250) {
            return "Message exceeds 250 characters by " + (messageText.length() - 250) + ", please reduce size.";
        }

        String messageID = generateMessageID();
        String hash = createMessageHash(messageID, messageCounter, messageText);

        String[] options = {"Send", "Disregard", "Store"};
        int choice = JOptionPane.showOptionDialog(null, "What do you want to do with this message?",
            "Message Action", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        switch (choice) {
            case 0 -> {
                // If user chooses "Send", add message to memory and increase the counter
                messages.add("Message ID: " + messageID + "\nHash: " + hash + "\nTo: " + recipient + "\nMessage: " + messageText);
                messageCounter++;
                return "Message successfully sent.";
            }
            case 1 -> {
                // If user chooses "Disregard", cancel the message
                return "Message disregarded.";
            }
            case 2 -> {
                // If user chooses "Store", call the method to write the message to a JSON file
                storeMessageToJSON(messageID, messageCounter, recipient, messageText, hash);
                return "Message stored in stored_messages.json";
            }
            default -> {
                return "No action taken.";
            }
        }
    }

    // Stores a message to a JSON file on disk
    public static void storeMessageToJSON(String id, int msgNum, String recipient, String messageText, String hash) {
        try {
            // Create a JSON object for this message
            JSONObject messageObj = new JSONObject();
            messageObj.put("MessageID", id);
            messageObj.put("MessageNumber", msgNum);
            messageObj.put("Recipient", recipient);
            messageObj.put("MessageText", messageText);
            messageObj.put("Hash", hash);

            // Wrap the message object in an array for future compatibility
            JSONArray messageList = new JSONArray();
            messageList.put(messageObj);

            // Open the file in append mode and write the message array as a new line
            FileWriter file = new FileWriter("stored_messages.json", true);
            file.write(messageList.toString() + "\n"); // One JSON array per line
            file.flush();
            file.close();
        } catch (IOException e) {
            // Show error if file writing fails
            JOptionPane.showMessageDialog(null, "Error saving to JSON: " + e.getMessage());
        }
    }

    // Displays all sent messages (not stored ones) using JOptionPane pop-ups
    public static void printMessages() {
        if (messages.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No messages sent yet.");
        } else {
            for (String msg : messages) {
                JOptionPane.showMessageDialog(null, msg);
            }
        }
    }

    // Returns the total number of sent (not stored) messages
    public static int returnTotalMessages() {
        return messageCounter;
    }

    static String sendMessage_1FlowWithInputs(String number, String messageText) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    static String sendMessageFlowWithInputs(String number, String messageText) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
