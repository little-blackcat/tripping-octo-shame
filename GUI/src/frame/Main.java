package frame;

import generator.RandomObjectGenerator;
import questions.CreatingObjectFromFile;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Created by Paula on 24.01.2016.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        EventQueue.invokeLater(new Runnable() {
            public void run(){
                SizedFrame frame = null;
                try {
                    frame = new SizedFrame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}
