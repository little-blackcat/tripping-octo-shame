package frame;

import generator.RandomObjectGenerator;

import javax.swing.*;
import java.awt.*;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javafx.scene.text.*;
import questions.Question;
import questions.StatsService;

/**
 * Created by Paula on 24.01.2016.
 */
public class MainFrame extends JFrame {
    private JPanel buttonPanel;
    private JPanel textPanel;
    private JPanel checkBoxesPanel;
    private JPanel buttonsAndStatsPanel;

    //
    private JCheckBox cbAnswer1;
    private JCheckBox cbAnswer2;
    private JCheckBox cbAnswer3;

    //
    JButton checkButton;
    JButton showAnswerButton;
    JButton nextQuestionButton;

    //
    private JLabel questionText;
    private JLabel statsText;

    private RandomObjectGenerator rog;
    private Question currentQuestion;
    private StatsService stats;

    public MainFrame(RandomObjectGenerator rog, StatsService stats) throws IOException {
        this.rog = rog;
        this.currentQuestion = rog.getRandomQuestion();
        this.stats = stats;

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        checkButton = new JButton("Sprawdz");
        setSize(screenWidth / 2, screenHeight / 2);
        setLocationByPlatform(true);

        //tworzenie przycisk�w
        showAnswerButton = new JButton("Pokaz odpowiedz");
        nextQuestionButton = new JButton("Dalej");

        //tworzenie pola tesktowego
        questionText = new JLabel(currentQuestion.getQuestionText());
        questionText.setFont(new Font("Courier", Font.BOLD,16));
        statsText = new JLabel("Powodzenia!");

        //tworzenie checkbox�w
        String[] answers = currentQuestion.getAnswers();
        cbAnswer1 = new JCheckBox(answers[0]);
        cbAnswer2 = new JCheckBox(answers[1]);
        cbAnswer3 = new JCheckBox(answers[2]);

        buttonPanel = new JPanel();
        textPanel = new JPanel();
        checkBoxesPanel = new JPanel();
        buttonsAndStatsPanel = new JPanel();

        //dodanie przycisk�w do panelu
        textPanel.add(questionText);

        checkBoxesPanel.setLayout(new GridLayout(3, 1));
        checkBoxesPanel.add(cbAnswer1);
        checkBoxesPanel.add(cbAnswer2);
        checkBoxesPanel.add(cbAnswer3);

        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.add(checkButton);
        buttonPanel.add(showAnswerButton);
        buttonPanel.add(nextQuestionButton);

        buttonsAndStatsPanel.setLayout(new GridLayout(2,1));
        buttonsAndStatsPanel.add(statsText);
        buttonsAndStatsPanel.add(buttonPanel);

        //dodanie panelu do ramki
        add(textPanel, BorderLayout.NORTH);
        add(checkBoxesPanel, BorderLayout.CENTER);
        add(buttonsAndStatsPanel, BorderLayout.SOUTH);

        //utworzenie akcji przycisk�w
        CheckAnswers checkAnswers = new CheckAnswers();
        ShowAnswer showAnswerAction = new ShowAnswer();
        NextQuestion nextQuestionAction = new NextQuestion();

        //powiazanie akcji z przyciskami
        checkButton.addActionListener(checkAnswers);
        showAnswerButton.addActionListener(showAnswerAction);
        nextQuestionButton.addActionListener(nextQuestionAction);
    }

    private class ShowAnswer implements ActionListener {
        public ShowAnswer(){}

        public void actionPerformed(ActionEvent event){
            String[] correct = currentQuestion.getCorrect();
            cbAnswer1.setSelected(correct[0].equals("1"));
            cbAnswer2.setSelected(correct[1].equals("1"));
            cbAnswer3.setSelected(correct[2].equals("1"));
            lockCheckAndShow();
        }
    }

    private class CheckAnswers implements ActionListener {
        public CheckAnswers(){}

        public void actionPerformed(ActionEvent event){
            String[] correct = currentQuestion.getCorrect();

            JCheckBox[] cbox = {cbAnswer1, cbAnswer2, cbAnswer3};
            int selectedCheckBoxes = 0;
            boolean isGood = false;
            for(int i=0; i<3; ++i) {
                if(cbox[i].isSelected()) ++selectedCheckBoxes;

                if (correct[i].equals("1")) {
                    // good answer
                    cbox[i].setBackground(Color.GREEN);
                    // was checked?
                    if(cbox[i].isSelected()) {
                        isGood = true;
                    }
                }
                else if (cbox[i].isSelected() && correct[i].equals("0")) {
                    // bad answer
                    cbox[i].setBackground(Color.RED);
                }
            }
            if(selectedCheckBoxes > 1) isGood = false;

            if(isGood)
                stats.good();
            else
                stats.bad();

            updateStats();
            lockCheckAndShow();
        }
    }

    private class NextQuestion implements ActionListener {
        public NextQuestion(){}

        public void actionPerformed(ActionEvent event) {
            currentQuestion = rog.getRandomQuestion();
            questionText.setText(currentQuestion.getQuestionText());
            prepareQuestion();
            unlockCheckAndShow();
        }
    }

    private void prepareQuestion() {
        String[] answers = currentQuestion.getAnswers();
        cbAnswer1.setText(answers[0]);
        cbAnswer2.setText(answers[1]);
        cbAnswer3.setText(answers[2]);
        clearCheckBox();
    }

    private void clearCheckBox() {
        cbAnswer1.setSelected(false);
        cbAnswer2.setSelected(false);
        cbAnswer3.setSelected(false);

        cbAnswer1.setBackground(checkBoxesPanel.getBackground());
        cbAnswer2.setBackground(checkBoxesPanel.getBackground());
        cbAnswer3.setBackground(checkBoxesPanel.getBackground());
    }

    private void updateStats() {
        statsText.setText("Odpowiedziales dobrze na " + stats.getGood() +" z "+ stats.getAnswers() +" pytan ( "+ (int)stats.getStats() +"% )");
    }

    private void lockCheckAndShow() {
        checkButton.setEnabled(false);
        showAnswerButton.setEnabled(false);
    }

    private void unlockCheckAndShow() {
        checkButton.setEnabled(true);
        showAnswerButton.setEnabled(true);
    }


}
