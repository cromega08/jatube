//     <Jatube: A Youtube Downloader application implemented with Java and Python>
//     Copyright (C) <2022>  <Cromega>

//     This program is free software: you can redistribute it and/or modify
//     it under the terms of the GNU Affero General Public License as published
//     by the Free Software Foundation, either version 3 of the License, or
//     (at your option) any later version.

//     This program is distributed in the hope that it will be useful,
//     but WITHOUT ANY WARRANTY; without even the implied warranty of
//     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//     GNU Affero General Public License for more details.

//     You should have received a copy of the GNU Affero General Public License
//     along with this program.  If not, see <https://www.gnu.org/licenses/>.

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YoutubeDownloader implements ActionListener {

    private static Color main, second, third, contrast;
    private static JFrame main_window;
    private static Toolkit tools;
    private static Dimension screen_size;
    private static JPanel for_input, for_buttons;
    private static JTextField input_url;
    private static ImageIcon logo; //* The Image was downloaded from FlatIcon
    private static JButton location, download, only_audio;
    private static Border line_border;
    private static JFileChooser choose_dir;
    private static String default_path;
    private static FileHandler file_handler;
    
    YoutubeDownloader() {

        // * Supply elements

        file_handler = new FileHandler();
        default_path = init();
        
        choose_dir = new JFileChooser();
        choose_dir.setDialogTitle("New Location");
        choose_dir.setCurrentDirectory(new File(default_path));
        choose_dir.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        choose_dir.setAcceptAllFileFilterUsed(false);

        main = new Color(0x000000);
        second = new Color(0x3E3636);
        third = new Color(0xD72323);
        contrast = new Color(0xF5EDED);

        line_border = BorderFactory.createLineBorder(third, 3);

        tools = Toolkit.getDefaultToolkit();
        screen_size = tools.getScreenSize();
        int screen_width = (int) screen_size.getWidth();
        int screen_height = (int) screen_size.getHeight();

        logo = new ImageIcon("../imgs/jatube.png");

        // * Buttons

        location = new JButton(default_path);
        location.setPreferredSize(new Dimension((screen_width/4)-25, (screen_height/8)/4));
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

        only_audio = new JButton("Only Audio");
        only_audio.setFocusPainted(false);
        only_audio.setContentAreaFilled(false);
        only_audio.setBorder(line_border);
        only_audio.setForeground(contrast);
        only_audio.setBackground(second);
        only_audio.addActionListener(this);

        // * Text Components

        input_url = new JTextField();
        input_url.setPreferredSize(new Dimension((screen_width/4)-25, (screen_height/8)/4));
        input_url.setForeground(contrast);
        input_url.setCaretColor(third);
        input_url.setBackground(main);
        input_url.setBorder(line_border);
        input_url.setText("URL");
        input_url.setHorizontalAlignment(JTextField.CENTER);

        // * Div Components

        for_input = new JPanel();
        for_input.setBackground(main);
        for_input.add(input_url);
        for_input.add(location);
        for_input.setAlignmentY(JPanel.CENTER_ALIGNMENT);;

        for_buttons = new JPanel();
        for_buttons.setLayout(new FlowLayout());
        for_buttons.setBackground(main);
        for_buttons.add(download);
        for_buttons.add(only_audio);
        for_buttons.setAlignmentY(JPanel.CENTER_ALIGNMENT);

        // * Windows Settings

        main_window = new JFrame();
        main_window.setIconImage(logo.getImage());
        main_window.setTitle("Youtube Downloader");
        main_window.setSize(screen_width/4,screen_height/5);
        main_window.setResizable(false);
        main_window.setLayout(new GridLayout(2,1));
        main_window.setLocation(screen_width, 50);
        main_window.getContentPane().setBackground(main);
        main_window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // * Add Components and Activate

        main_window.add(for_input);
        main_window.add(for_buttons);

        main_window.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == location) {
            int choosed_dir = choose_dir.showOpenDialog(main_window);

            if (choosed_dir == JFileChooser.APPROVE_OPTION) {
                String new_path =  choose_dir.getSelectedFile().getAbsolutePath();
                file_handler.write_settings(new_path);
                default_path = new_path;
                location.setText(new_path);
                JOptionPane.showMessageDialog(main_window, "The default location was changed successfully", "Default Location", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        if (event.getSource() == download) {
            if (check_url(input_url.getText())) {download_stream(input_url.getText(), false);}
            else {JOptionPane.showMessageDialog(main_window, "Please, enter a valid URL", "Invalid URL", JOptionPane.ERROR_MESSAGE);}
        }

        if (event.getSource() == only_audio) {
            if (check_url(input_url.getText())) {download_stream(input_url.getText(), true);}
            else {JOptionPane.showMessageDialog(main_window, "Please, enter a valid URL", "Invalid URL", JOptionPane.ERROR_MESSAGE);}
        }
    }

    private static String init() {
        if (!file_handler.check_settings_file()) {file_handler.create_settings();}
        return file_handler.get_settings();
    }

    private static boolean check_url(String url) {
        Pattern pattern = Pattern.compile("(www\\.|music\\.|youtu\\.)(youtube|youtu\\.be|be)");
        Matcher matcher = pattern.matcher(url);
        if (matcher.find()){return true;}
        return false;
    }

    private static void download_stream(String url, boolean selected_audio) {
        String current_dir = System.getProperty("user.dir");
        String argument = selected_audio? "-a":"";
        try {
            Runtime.getRuntime().exec(String.format("python3 %s/downloader.py %s %s %s", current_dir, argument, default_path, url));
            JOptionPane.showMessageDialog(main_window, "The download may take time, wait until the file appears in your directory and enjoy", "Download Completed", JOptionPane.INFORMATION_MESSAGE);
        }
        
        catch (Exception error) {
            JOptionPane.showMessageDialog(main_window, String.format("Can'download from: %s", url), "Error Downloading", JOptionPane.ERROR_MESSAGE);
            System.out.println(error.getMessage());
        }
    }
}
