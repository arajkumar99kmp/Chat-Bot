import java.util.HashMap;
import java.util.Map;

public class EmotionTracker {
    private String currentEmotion = "neutral";
    private int emotionScore = 0;
    private final Map<String, Integer> emotionHistory = new HashMap<>();

    public EmotionTracker() {
        emotionHistory.put("happy", 0);
        emotionHistory.put("sad", 0);
        emotionHistory.put("angry", 0);
        emotionHistory.put("excited", 0);
        emotionHistory.put("neutral", 0);
    }

    public void updateEmotion(String input) {
        // Emotion detection logic
        if (input.contains("happy") || input.contains("joy") || input.contains("awesome")) {
            updateEmotionState("happy", 2);
        }
        else if (input.contains("sad") || input.contains("depressed") || input.contains("unhappy")) {
            updateEmotionState("sad", -2);
        }
        else if (input.contains("angry") || input.contains("mad") || input.contains("frustrated")) {
            updateEmotionState("angry", -3);
        }
        else if (input.contains("excited") || input.contains("thrilled") || input.contains("ecstatic")) {
            updateEmotionState("excited", 3);
        }
        else if (input.contains("love") || input.contains("like")) {
            emotionScore += 1;
        }
        else if (input.contains("hate") || input.contains("dislike")) {
            emotionScore -= 1;
        }
        
        // Gradual return to neutral
        if (emotionScore > 0) emotionScore--;
        else if (emotionScore < 0) emotionScore++;
        
        // Update emotion based on score
        if (emotionScore > 5) currentEmotion = "excited";
        else if (emotionScore > 2) currentEmotion = "happy";
        else if (emotionScore < -5) currentEmotion = "angry";
        else if (emotionScore < -2) currentEmotion = "sad";
        else if (emotionScore == 0) currentEmotion = "neutral";
    }
    
    private void updateEmotionState(String emotion, int scoreDelta) {
        currentEmotion = emotion;
        emotionScore += scoreDelta;
        emotionHistory.put(emotion, emotionHistory.get(emotion) + 1);
    }
    
    public String getCurrentEmotion() {
        return currentEmotion;
    }
    
    public void resetEmotion() {
        currentEmotion = "neutral";
        emotionScore = 0;
        emotionHistory.keySet().forEach(key -> emotionHistory.put(key, 0));
    }
    
    public void printEmotionReport(String name) {
        System.out.println("\n╔════════════ EMOTION REPORT ═══════════╗");
        System.out.println("║ " + name + "'s emotional state during conversation:");
        System.out.println("╠══════════════════════════════════════╣");
        emotionHistory.forEach((emotion, count) -> {
            if (count > 0) {
                System.out.printf("║ %-10s: %-3d times\n", emotion.toUpperCase(), count);
            }
        });
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║ FINAL EMOTION: " + currentEmotion.toUpperCase());
        System.out.println("╚══════════════════════════════════════╝");
    }
}