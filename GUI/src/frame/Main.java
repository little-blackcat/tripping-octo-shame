package frame;

import generator.RandomObjectGenerator;
import questions.ObjectFromFileCreator;
import questions.StatsService;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Paula on 24.01.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {
        ObjectFromFileCreator coff = new ObjectFromFileCreator("questions");
        RandomObjectGenerator rog = new RandomObjectGenerator(coff);
        StatsService stats = new StatsService();

        EventQueue.invokeLater(new Runnable() {
            public void run(){
                MainFrame frame = null;
                try {
                    frame = new MainFrame(rog, stats);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
