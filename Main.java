import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmotionTracker emotionTracker = new EmotionTracker();
        ResponseGenerator responseGenerator = new ResponseGenerator(emotionTracker);
        User user = new User();

        System.out.println("╔═════════════════════════════════╗");
        System.out.println("║     EMOTION-AWARE CHAT BOT      ║");
        System.out.println("╚═════════════════════════════════╝");
        
        System.out.println("\nHello! I'm your emotionally intelligent Java assistant. What's your name?");
        System.out.print(">> ");
        user.setName(scanner.nextLine());
        
        System.out.println("\nNice to meet you, " + user.getName() + "! I can sense your emotions.");
        System.out.println("(Type 'help' for commands, 'emotion' for your current emotion, or 'bye' to exit)");

        boolean isFirstInteraction = true;
        
        while (true) {
            System.out.print("\n" + user.getName() + ": ");
            String input = scanner.nextLine().toLowerCase();
            
            emotionTracker.updateEmotion(input);

            if (input.contains("bye")) {
                System.out.println("Bot: Goodbye, " + user.getName() + "! It was nice chatting with you.");
                emotionTracker.printEmotionReport(user.getName());
                break;
            }
            
            if (isFirstInteraction) {
                System.out.println("Bot: " + responseGenerator.getFirstInteractionResponse(user.getName()));
                isFirstInteraction = false;
            } else {
                System.out.println("Bot: " + responseGenerator.generateResponse(input, user.getName(), scanner));
            }
        }
        scanner.close();
    }
}