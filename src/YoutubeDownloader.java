import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

import java.io.File;

public class YoutubeDownloader implements ActionListener {

    Color main, second, third, contrast;
    JFrame main_window, settings_window;
    Toolkit tools;
    Dimension screen_size;
    JPanel for_input, for_checkbox, for_buttons;
    JTextField input;
    JCheckBox only_audio;
    Image non_scale_1, non_scale_2;
    ImageIcon checked, non_checked;
    JButton location, download, apply;
    Border line_border;
    JFileChooser chose_dir;
    
    
    YoutubeDownloader() {

        // * Supply elements
        
        chose_dir = new JFileChooser();
        chose_dir.setCurrentDirectory(new File("../downloads"));

        main = new Color(0x000000);
        second = new Color(0x3E3636);
        third = new Color(0xD72323);
        contrast = new Color(0xF5EDED);

        line_border = BorderFactory.createLineBorder(third, 3);

        tools = Toolkit.getDefaultToolkit();
        screen_size = tools.getScreenSize();
        int screen_width = (int) screen_size.getWidth();
        int screen_height = (int) screen_size.getHeight();

        non_scale_1 =  new ImageIcon("../imgs/empty-checkbox.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        non_scale_2 =  new ImageIcon("../imgs/done.png").getImage().getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        checked = new ImageIcon(non_scale_2);
        non_checked = new ImageIcon(non_scale_1);

        // * Buttons

        location = new JButton("Preferred location");
        location.setFocusPainted(false);
        location.setContentAreaFilled(false);
        location.setBorder(line_border);
        location.setForeground(contrast);
        location.setBackground(second);
        location.addActionListener(this);

        download = new JButton("Download");
        download.setFocusPainted(false);
        download.setContentAreaFilled(false);
        download.setBorder(line_border);
        download.setForeground(contrast);
        download.setBackground(second);
        download.addActionListener(this);

        // * Checkboxes

        only_audio = new JCheckBox();
        only_audio.setBackground(third);
        only_audio.setForeground(contrast);
        only_audio.setText("Only Audio");
        only_audio.setFocusPainted(false);
        only_audio.setIcon(non_checked);
        only_audio.setSelectedIcon(checked);

        // * Text Components

        input = new JTextField();
        input.setPreferredSize(new Dimension((screen_width/4)-25, (screen_height/8)/4));
        input.setForeground(contrast);
        input.setCaretColor(third);
        input.setBackground(main);
        input.setBorder(line_border);
        input.setText("URL");

        // * Div Components

        for_input = new JPanel();
        for_input.setBackground(main);
        for_input.add(input);
        for_input.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        for_checkbox = new JPanel();
        for_checkbox.setBackground(main);
        for_checkbox.add(only_audio);
        for_checkbox.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        
        for_buttons = new JPanel();
        for_buttons.setBackground(main);
        for_buttons.add(location);
        for_buttons.add(download);
        for_buttons.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        // * Windows Settings

        main_window = new JFrame();
        main_window.setSize(screen_width/4,screen_height/6);
        main_window.setResizable(false);
        main_window.setLayout(new GridLayout(3,1));
        main_window.setLocation(screen_width, 50);
        main_window.setTitle("Youtube Downloader");
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        settings_window = new JFrame();
        settings_window.setSize(screen_width/4,screen_height/4);
        settings_window.setResizable(false);
        // settings_window.setLayout(new GridLayout(2,1));
        settings_window.setTitle("Settings");
        settings_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // * Add Components and Activate

        main_window.add(for_input);
        main_window.add(for_checkbox);
        main_window.add(for_buttons);

        main_window.setVisible(true);
    }

    public static void main(String[] args) {
        new YoutubeDownloader();
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == location) {
            settings_window.setLocation(main_window.getLocationOnScreen());
            main_window.setVisible(false);
            settings_window.setVisible(true);
        }
        if (event.getSource() == download) {

            String current_dir = System.getProperty("user.dir");
            String url = input.getText();
            String argument = only_audio.isSelected()? "-a":"";
            try {
                Runtime.getRuntime().exec(String.format("python3 %s/downloader.py %s %s", current_dir, argument, url));
                JOptionPane.showMessageDialog(main_window, "Your video was downloaded", "Download Completed", JOptionPane.INFORMATION_MESSAGE);
            }
            
            catch (Exception error) {
                JOptionPane.showMessageDialog(main_window, String.format("Can'download from: %s", url), "Error Downloading", JOptionPane.ERROR_MESSAGE);
                System.out.println(error.getMessage());
            }
        }
    }

    private String init() {
        FileHandler path = new FileHandler();
        if (!path.check_settings_file()) {path.create_settings();}
        return path.get_settings();
    }
}
