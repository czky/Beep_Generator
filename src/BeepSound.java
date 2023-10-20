import javax.swing.*;
import java.awt.*;
import org.jfree.chart.*;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import javax.sound.sampled.*;

public class BeepSound {
    public static void playBeepSound(int freq, int duration) {
        XYSeries series = new XYSeries("Generated Sound Waveform");
        for (int i = 0; i < duration; i++) {
            double time = (double) i / 1000; // convert from millisec to sec
            double amplitude = Math.sin(2 * Math.PI * freq * time);
            series.add(time, amplitude);
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("Sound Waveform", "Time (sec)", "Amplitude", dataset);

        JFrame frame = new JFrame("Sound Waveform");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        frame.add(chartPanel, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

        // Play the sound in a separate thread
        Thread soundThread = new Thread(() -> {
            try {
                AudioFormat format = new AudioFormat(44100, 16, 1, true, false);
                SourceDataLine line = AudioSystem.getSourceDataLine(format);
                line.open(format);
                line.start();
                byte[] buffer = new byte[2 * 44100 * duration / 1000];
                for (int i = 0; i < buffer.length; i += 2) {
                    short sample = (short) (Short.MAX_VALUE * Math.sin(2 * Math.PI * freq * i / (44100 * duration / 1000)));
                    buffer[i] = (byte) (sample & 0x00FF);
                    buffer[i + 1] = (byte) ((sample & 0xFF00) >> 8);
                }
                line.write(buffer, 0, buffer.length);
                line.drain();
                line.close();
            } catch (LineUnavailableException e) {
                e.printStackTrace();
            }
        });
        soundThread.start();
    }
}
