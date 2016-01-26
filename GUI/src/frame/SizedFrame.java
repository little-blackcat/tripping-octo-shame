package frame;

import generator.RandomObjectGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import questions.CreatingObjectFromFile;
import questions.Question;
import sun.font.CreatedFontTracker;

/**
 * Created by Paula on 24.01.2016.
 */
public class SizedFrame extends JFrame {
    public JPanel buttonPanel;
    public JPanel textPanel;
    public JPanel checkBoxesPanel;

    public SizedFrame() throws IOException {

        CreatingObjectFromFile coff = new CreatingObjectFromFile();
        coff.convertToObject();
        RandomObjectGenerator rog = new RandomObjectGenerator(coff);
        Question q = rog.getRandomQuestion();

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        //tworzenie przycisków
        JButton confirmButton = new JButton("Sprawdz");
        JButton showAnswerButton = new JButton("Pokaz odpowiedz");
        JButton nextQuestionButton = new JButton("Dalej");

        //tworzenie pola tesktowego
        JLabel questionText = new JLabel(q.questionText);

        //tworzenie checkboxów
        JCheckBox answer1 = new JCheckBox(q.answers[0]);
        JCheckBox answer2 = new JCheckBox(q.answers[1]);
        JCheckBox answer3 = new JCheckBox(q.answers[2]);

        buttonPanel = new JPanel();
        textPanel = new JPanel();
        checkBoxesPanel = new JPanel();

        //dodanie przycisków do panelu
        textPanel.add(questionText);

        checkBoxesPanel.add(answer1);
        checkBoxesPanel.add(answer2);
        checkBoxesPanel.add(answer3);

        buttonPanel.add(confirmButton);
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(nextQuestionButton);

        //dodanie panelu do ramki
        add(buttonPanel, BorderLayout.SOUTH);
        add(checkBoxesPanel);
        add(textPanel, BorderLayout.NORTH);

        //utworzenie akcji przycisków
        ConfirmAction confirmAction = new ConfirmAction(Color.GREEN, questionText, answer1, answer2, answer3, coff);
        ShowAnswer showAnswerAction = new ShowAnswer(answer1, answer2, answer3, q);
        ConfirmAction nextQuestionAction = new ConfirmAction(Color.RED, questionText, answer1, answer2, answer3, coff);

        //powiazanie akcji z przyciskami
        confirmButton.addActionListener(confirmAction);
        showAnswerButton.addActionListener(showAnswerAction);
        nextQuestionButton.addActionListener(nextQuestionAction);
    }

    public class ShowAnswer implements ActionListener {

        private Color backgroundColor;
        private JCheckBox ans1, ans2, ans3;
        private RandomObjectGenerator rod;

        public ShowAnswer(JCheckBox a1, JCheckBox a2, JCheckBox a3, Question q){
            ans1 = a1;
            ans2 = a2;
            ans3 = a3;
        }

        public void actionPerformed(ActionEvent event){
            buttonPanel.setBackground(backgroundColor);
            ans1.setSelected(true);
            ans3.setSelected(true);
        }
    }

    public class ConfirmAction implements ActionListener {

        private Color backgroundColor;
        private JLabel text;
        private JCheckBox checkBox1;
        private JCheckBox checkBox2;
        private JCheckBox checkBox3;
        private String questiontext;
        private CreatingObjectFromFile coff;
        private Question rog;

        public ConfirmAction(Color c, JLabel tp, JCheckBox jcb1, JCheckBox jcb2, JCheckBox jcb3, CreatingObjectFromFile coff){
            backgroundColor = c;
            text = tp;
            checkBox1 = jcb1;
            checkBox2 = jcb2;
            checkBox3 = jcb3;
            this.coff = coff;
            rog = new RandomObjectGenerator(this.coff).getRandomQuestion();
        }

        public void actionPerformed(ActionEvent event){
            buttonPanel.setBackground(backgroundColor);
            this.questiontext = rog.questionText;
            this.checkBox1.setText(rog.answers[0]);
            this.checkBox2.setText(rog.answers[1]);
            this.checkBox3.setText(rog.answers[2]);
            text.setText(this.questiontext);
        }

        public Question returnNewQuestion(){
            return rog;
        }

    }


}
