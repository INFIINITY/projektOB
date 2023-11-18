package zaklad;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;

class Dodatek {
    public static void odpalZdjecie(final String fileName) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                ClassLoader classLoader = getClass().getClassLoader();
                java.net.URL imgUrl = classLoader.getResource(fileName);

                if (imgUrl != null) {
                    ImageIcon icon = new ImageIcon(imgUrl);
                    JLabel label = new JLabel(icon);
                    f.getContentPane().add(label);
                } else {
                    System.out.println("Nie można odnaleźć pliku obrazka.");
                }

                f.pack();
                f.setLocationRelativeTo(null);
                f.setVisible(true);
            }
        });
    }

    public static void odpalDzwiek(String soundFileName) {
        try {
            ClassLoader classLoader = Dodatek.class.getClassLoader();
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(
                    classLoader.getResourceAsStream(soundFileName));

            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception e) {
            System.err.println("Błąd podczas odtwarzania dźwięku: " + e.getMessage());
        }
    }
}


