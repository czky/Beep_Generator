# Beep Generator (Java)
This Java program demonstrates how to generate a sound waveform and visualize it using JFreeChart. It also plays the generated sound using the javax.sound.sampled package.

## Prerequisites
* Java Development Kit (JDK)
* JFreeChart library

## Usage / How to run
1. Compile the program using the following command:
```
javac -cp .:jfreechart-1.5.0.jar BeepGenerator.java
```
2. Run the program using the following command:
```
java -cp .:jfreechart-1.5.0.jar BeepGenerator
```

## Program Description
The program generates a sound waveform using the following formula:
```
y = A * sin(2 * pi * f * t)
```
where:
* y = amplitude
* A = peak amplitude
* f = frequency
* t = time
* pi = 3.14

The program is able to generate any sound waveform with a specified frequency and duration. The program also allows the user to specify the sample rate and the bit depth of the generated sound.

The program uses the JFreeChart library to visualize the generated sound waveform. The program also uses the javax.sound.sampled package to play the generated sound.


## Code Explanation
The sound generation and playback are done in a separate thread in order to prevent blocking the main UI thread.
The generated sound wave is displayed in a chart with time (seconds) on the X-axis and amplitude on the Y-axis.
The only method (*playBeepSound*) generates it using the formula above with the user specified parameters for frequency and duration.