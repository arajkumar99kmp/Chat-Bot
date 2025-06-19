# Emotion-Aware Chat Bot

A Java-based chat bot that detects user emotions through conversation and responds with emotion-aware interactions. The bot tracks emotional states, generates context-aware responses, and provides conversation reports.

## Features

- **Real-time Emotion Tracking**: Detects 5 emotional states (happy, sad, angry, excited, neutral)
- **Emotion-Based Responses**: Generates context-aware replies based on detected emotions
- **Conversation History**: Maintains emotional state history throughout the session
- **Interactive Commands**:
  - `emotion` - Show current emotional state
  - `reset emotion` - Reset emotional tracking
  - `joke` - Get emotion-appropriate jokes
  - `time`/`date` - Show current time/date
  - `weather` - Get weather information
  - `advice` - Receive life advice
  - `fact` - Get interesting facts
  - `calc` - Perform calculations
  - `feeling` - Express your mood
  - `help` - Show command list
- **Emotion Reports**: Generates end-of-conversation emotion summaries
- **Gradual Emotion Decay**: Emotions naturally return to neutral over time

## Class Structure

| File                 | Description                                                                 |
|----------------------|-----------------------------------------------------------------------------|
| `EmotionTracker.java`| Tracks emotional states, scores, and history                                |
| `ResponseGenerator.java`| Generates emotion-aware responses and handles commands                    |
| `Main.java`          | Entry point with chat loop and user interaction logic                       |
| `User.java`          | Stores user information (name)                                              |

## Getting Started

### Prerequisites
- Java JDK 11 or higher
- Maven (optional)

### Installation & Execution
1. Compile all Java files:
```bash
javac EmotionTracker.java Main.java ResponseGenerator.java User.java
