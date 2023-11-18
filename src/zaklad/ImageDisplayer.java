package zaklad;

import javax.swing.*;

class ImageDisplayer {
    public static void showImage(final String fileName) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame f = new JFrame();
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                // Uzyskanie ścieżki do pliku z katalogu src
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
}


