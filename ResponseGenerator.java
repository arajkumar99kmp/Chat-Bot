import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class ResponseGenerator {
    private final Random random = new Random();
    private final EmotionTracker emotionTracker;

    public ResponseGenerator(EmotionTracker emotionTracker) {
        this.emotionTracker = emotionTracker;
    }
    
    public String getFirstInteractionResponse(String name) {
        return "How can I assist you today, " + name + "?";
    }

    public String generateResponse(String input, String name, Scanner scanner) {
        if (input.contains("help")) {
            return getHelpMenu();
        }
        if (input.contains("emotion")) {
            return getEmotionResponse(name);
        }
        if (input.contains("reset emotion")) {
            emotionTracker.resetEmotion();
            return "Emotion tracking has been reset to neutral.";
        }
        if (input.contains("hello") || input.contains("hi") || input.contains("hey")) {
            return getGreeting(name);
        }
        if (input.contains("how are you")) {
            return getHowAreYouResponse();
        }
        if (input.contains("name")) {
            return "I'm EmoBot, your emotion-aware Java assistant!";
        }
        if (input.contains("thank")) {
            return getThanksResponse();
        }
        if (input.contains("joke")) {
            return "Joke: " + getJoke();
        }
        if (input.contains("time")) {
            return getTime();
        }
        if (input.contains("date")) {
            return getDate();
        }
        if (input.contains("weather")) {
            return getWeather();
        }
        if (input.contains("advice")) {
            return getAdvice();
        }
        if (input.contains("fact")) {
            return getFact();
        }
        if (input.contains("calc") || input.contains("calculate")) {
            return handleCalculation(scanner, name);
        }
        if (input.contains("feeling") || input.contains("mood")) {
            return getFeelingResponse();
        }
        return getFallbackResponse();
    }

    // Response generation methods
    private String getGreeting(String name) {
        String emotion = emotionTracker.getCurrentEmotion();
        // Emotion-based greetings
        return switch (emotion) {
            case "happy" -> "Hello " + name + "! 😊 So nice to see you!";
            case "sad" -> "Hello... I'm here for you. 🥺";
            case "angry" -> "Hello. Let's keep this calm. 😌";
            case "excited" -> "WOO-HOO! HELLO " + name.toUpperCase() + "! 😆";
            default -> "Hello!";
        };
    }
    
    private String getHowAreYouResponse() {
        String emotion = emotionTracker.getCurrentEmotion();
        // Emotion-based responses
        return switch (emotion) {
            case "happy" -> "I'm great, especially seeing you so happy! How can I help?";
            case "sad" -> "I'm okay, but I'm more concerned about you. Want to talk?";
            case "angry" -> "I'm here and calm. How can I help improve your mood?";
            default -> "I'm just a program, but I'm running great! How about you?";
        };
    }
    
    private String getJoke() {
        String emotion = emotionTracker.getCurrentEmotion();
        // Emotion-based jokes
        return switch (emotion) {
            case "sad" -> "Why was the math book sad? Because it had too many problems!";
            case "angry" -> "Why was the computer cold? It left its Windows open!";
            default -> "Why do Java developers wear glasses? Because they can't C#!";
        };
    }
    
    private String getTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return "Current time is " + dtf.format(LocalDateTime.now());
    }

    private String getDate() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("EEEE, MMMM dd, yyyy");
        return "Today is " + dtf.format(LocalDateTime.now());
    }
    
    private String getWeather() {
        
                return "The weather is perfect! Sunny with a gentle breeze...";
    }
    private String getAdvice() {
       
                return "The best time to plant a tree was 20 years ago...";

    }

    private String getFact() {
        String[] facts = {
            "Java was originally called Oak...",
            "There are about 3 billion devices running Java...",
        };
        return facts[random.nextInt(facts.length)];
    }

    private String getFeelingResponse() {
        String emotion = emotionTracker.getCurrentEmotion();
        return switch (emotion) {
            case "happy" -> "Your happiness brightens the virtual space!...";
            case "sad" -> "I'm here to listen...";
            default -> "It's okay to feel neutral...";
        };
    }


    private String handleCalculation(Scanner scanner, String name) {
        System.out.println("Bot: Enter a simple calculation (e.g., '5 + 3'):");
        System.out.print(name + ": ");
        String calcInput = scanner.nextLine();
        try {
            String[] parts = calcInput.split(" ");
            double num1 = Double.parseDouble(parts[0]);
            double num2 = Double.parseDouble(parts[2]);
            return switch (parts[1]) {
                case "+" -> "Result = " + (num1 + num2);
                case "-" -> "Result = " + (num1 - num2);
                case "*" -> "Result = " + (num1 * num2);
                case "/" -> num2 == 0 ? "Error: Division by zero!" : "Result = " + (num1 / num2);
                default -> "Invalid operator. Use +, -, *, or /";
            };
        } catch (Exception e) {
            return "Sorry, I couldn't understand that calculation.";
        }
    }
    
    private String getEmotionResponse(String name) {
        String emotion = emotionTracker.getCurrentEmotion();
        return switch (emotion) {
            case "happy" -> "You seem happy today! 😊";
            case "sad" -> "I sense you're feeling sad. 🥺";
            case "angry" -> "You seem angry. 😠 Take a deep breath.";
            case "excited" -> "Wow, you're excited! 😆";
            default -> "You seem neutral. 😐";
        };
    }
    
    private String getHelpMenu() {
        return """
               ╔════════════════ CHAT COMMANDS ═══════════════╗
               ║ emotion       - Show current emotion         ║
               ║ reset emotion - Reset emotion tracking       ║
               ║ hello/hi      - Greet the bot                ║
               ║ joke          - Get a random joke            ║
               ║ time          - Get current time             ║
               ║ weather       - Get weather info             ║
               ║ advice        - Get life advice              ║
               ║ fact          - Get interesting fact         ║
               ║ calc          - Simple calculations          ║
               ║ feeling       - Express your mood            ║
               ║ help          - Show this help menu          ║ 
               ║ bye           - Exit the chat bot            ║
               ╚══════════════════════════════════════════════╝""";
    }
    
    // Other helper response methods...
    private String getThanksResponse() {
        String[] appreciations = {"You're welcome!", "Anytime!", "My pleasure!"};
        return appreciations[random.nextInt(appreciations.length)];
    }
    
    private String getFallbackResponse() {
        String[] fallbacks = {
            "I'm still learning about that. Could you rephrase?",
            "Interesting! Tell me more about that.",
            "I'm not sure I understand. Could you ask something else?"
        };
        return fallbacks[random.nextInt(fallbacks.length)];
    }
}