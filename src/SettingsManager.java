import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SettingsManager {
    JFrame window;
    JLabel text;

    SettingsManager() {

        text = new JLabel();
        text.setText("text");

        window = new JFrame();
        window.setSize(111,111);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        window.add(text);

        window.setVisible(true);
    }
}
