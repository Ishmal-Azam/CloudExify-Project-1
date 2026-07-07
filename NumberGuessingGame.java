import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;
import javax.swing.border.*;

public class NumberGuessingGame extends JFrame implements ActionListener {

    //COLORS 
    private final Color PRIMARY = new Color(25, 55, 170);
    private final Color GREEN = new Color(0, 140, 60);
    private final Color RED = new Color(220, 70, 70);
    private final Color ORANGE = new Color(255, 150, 40);
    private final Color BG = new Color(245,248,255);

    //GAME VARIABLES
    Random random = new Random();
    int secretNumber;
    int attempts;
    int bestScore = Integer.MAX_VALUE;
    int previousDistance = -1;
    int maxRange = 50;
    int maxAttempts = 10;

    //COMPONENTS
    JLabel titleLabel;
    JLabel subtitleLabel;
    JLabel bestTitle;
    JLabel attemptTitle;
    JLabel bestScoreLabel;
    JLabel attemptLabel;
    JLabel enterLabel;
    JLabel hintTitle;
    JLabel hintLabel;
    JTextField guessField;
    JButton guessButton;
    JButton newGameButton;
    JComboBox<String> difficultyBox;
    JPanel lowCard;
    JPanel highCard;

    private static NumberGuessingGame numberGuessingGame;

    public NumberGuessingGame() {
        setTitle("Number Guessing Game");
        setSize(1000,700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(BG);

        //TOP 
        JPanel top = new JPanel();
        top.setBackground(BG);
        top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
        top.setBorder(new EmptyBorder(25,20,20,20));

        titleLabel = new JLabel("NUMBER GUESSING GAME");
        titleLabel.setFont(new Font("Segoe UI",Font.BOLD,40));
        titleLabel.setForeground(PRIMARY);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        subtitleLabel = new JLabel("I have selected a number between 1 and 50.");
        subtitleLabel.setFont(new Font("Segoe UI",Font.BOLD,18));
        subtitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        subtitleLabel.setBorder(new EmptyBorder(20,0,20,0));

        top.add(titleLabel);
        top.add(subtitleLabel);
        add(top,BorderLayout.NORTH);

        //CENTER 
        JPanel center = new JPanel();
        center.setBackground(BG);
        center.setLayout(new BoxLayout(center,BoxLayout.Y_AXIS));
        add(center,BorderLayout.CENTER);

        //SCORE PANEL
        RoundedPanel scorePanel = new RoundedPanel(20);
        scorePanel.setBackground(Color.WHITE);
        scorePanel.setMaximumSize(new Dimension(850,130));
        scorePanel.setLayout(new GridLayout(1,2));

        JPanel left = new JPanel();
        left.setOpaque(false);
        left.setLayout(new BoxLayout(left,BoxLayout.Y_AXIS));

        bestTitle = new JLabel("BEST SCORE");
        bestTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        bestTitle.setForeground(PRIMARY);
        bestTitle.setFont(new Font("Segoe UI",Font.BOLD,20));

        bestScoreLabel = new JLabel("--");
        bestScoreLabel.setForeground(new Color(120,20,170));
        bestScoreLabel.setFont(new Font("Segoe UI",Font.BOLD,34));
        bestScoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        left.add(Box.createVerticalGlue());
        left.add(bestTitle);
        left.add(Box.createVerticalStrut(10));
        left.add(bestScoreLabel);
        left.add(Box.createVerticalGlue());

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.setLayout(new BoxLayout(right,BoxLayout.Y_AXIS));

        attemptTitle = new JLabel("ATTEMPTS");
        attemptTitle.setForeground(GREEN);
        attemptTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        attemptTitle.setFont(new Font("Segoe UI",Font.BOLD,20));

        attemptLabel = new JLabel("0");
        attemptLabel.setForeground(new Color(0,120,0));
        attemptLabel.setFont(new Font("Segoe UI",Font.BOLD,34));
        attemptLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        right.add(Box.createVerticalGlue());
        right.add(attemptTitle);
        right.add(Box.createVerticalStrut(10));
        right.add(attemptLabel);
        right.add(Box.createVerticalGlue());

        scorePanel.add(left);
        scorePanel.add(right);
        center.add(scorePanel);
        center.add(Box.createVerticalStrut(30));

        //DIFFICULTY 
        JPanel diffPanel = new JPanel();
        diffPanel.setOpaque(false);

        JLabel diffLabel = new JLabel("Difficulty");
        diffLabel.setFont(new Font("Segoe UI",Font.BOLD,18));

        difficultyBox = new JComboBox<>();
        difficultyBox.addItem("Easy (1-50)");
        difficultyBox.addItem("Hard (1-200)");
        difficultyBox.setFont(new Font("Segoe UI",Font.PLAIN,16));

        diffPanel.add(diffLabel);
        diffPanel.add(Box.createHorizontalStrut(15));
        diffPanel.add(difficultyBox);
        center.add(diffPanel);
        center.add(Box.createVerticalStrut(20));

        //INPUT
        enterLabel = new JLabel("Enter your guess");
        enterLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        enterLabel.setFont(new Font("Segoe UI",Font.BOLD,22));

        center.add(enterLabel);
        center.add(Box.createVerticalStrut(15));

        JPanel inputPanel = new JPanel();
        inputPanel.setOpaque(false);

        guessField = new JTextField();
        guessField.setPreferredSize(new Dimension(320,45));
        guessField.setFont(new Font("Segoe UI",Font.PLAIN,20));

        guessButton = new RoundedButton("Guess");
        guessButton.setPreferredSize(new Dimension(150,45));
        guessButton.addActionListener(this);

        inputPanel.add(guessField);
        inputPanel.add(Box.createHorizontalStrut(15));
        inputPanel.add(guessButton);
        center.add(inputPanel);
        center.add(Box.createVerticalStrut(30));

        //HINT
        hintTitle = new JLabel("Hint");
        hintTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        hintTitle.setForeground(PRIMARY);
        hintTitle.setFont(new Font("Segoe UI",Font.BOLD,24));

        center.add(hintTitle);
        center.add(Box.createVerticalStrut(10));
        RoundedPanel hintPanel = new RoundedPanel(18);

        hintPanel.setBackground(Color.WHITE);
        hintPanel.setMaximumSize(new Dimension(700,80));
        hintPanel.setLayout(new GridBagLayout());
        hintLabel = new JLabel("Start Guessing!");
        hintLabel.setFont(new Font("Segoe UI",Font.BOLD,22));

        hintPanel.add(hintLabel);
        center.add(hintPanel);
        center.add(Box.createVerticalStrut(30));

        //LOW HIGH
        JPanel cards = new JPanel();
        cards.setOpaque(false);

        lowCard = new RoundedPanel(15);
        lowCard.setPreferredSize(new Dimension(180,70));
        lowCard.setBackground(new Color(255,230,230));

        JLabel lowLabel = new JLabel("Too Low");
        lowLabel.setForeground(RED);
        lowLabel.setFont(new Font("Segoe UI",Font.BOLD,24));
        lowCard.add(lowLabel);

        highCard = new RoundedPanel(15);
        highCard.setPreferredSize(new Dimension(180,70));
        highCard.setBackground(new Color(225,240,255));

        JLabel highLabel = new JLabel("Too High");
        highLabel.setForeground(PRIMARY);
        highLabel.setFont(new Font("Segoe UI",Font.BOLD,24));

        highCard.add(highLabel);
        cards.add(lowCard);
        cards.add(Box.createHorizontalStrut(80));
        cards.add(highCard);
        center.add(cards);
        center.add(Box.createVerticalStrut(25));

        //NEW GAME 
        newGameButton = new RoundedButton("New Game");
        newGameButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        newGameButton.setMaximumSize(new Dimension(180,45));
        newGameButton.addActionListener(this);
        center.add(newGameButton);
        difficultyBox.addActionListener(this);
    startNewGame();
    setVisible(true);
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (e.getSource() == guessButton) {
        checkGuess();
    }
    if (e.getSource() == newGameButton) {
        startNewGame();
    }
    if (e.getSource() == difficultyBox) {

        if (difficultyBox.getSelectedIndex() == 0) {
            maxRange = 50;
        } else {
            maxRange = 200;
        }
        startNewGame();
    }
}
private void checkGuess() {
    String text = guessField.getText().trim();

    if (text.isEmpty()) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a number!");
        return;
    }
    int guess;
    try {
        guess = Integer.parseInt(text);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(
                this,
                "Please enter a valid number!");
        return;
    }
    if (guess < 1 || guess > maxRange) {
        JOptionPane.showMessageDialog(
                this,
                "Enter a number between 1 and " + maxRange);
        return;
    }
    attempts++;
    attemptLabel.setText(String.valueOf(attempts));

    int distance = Math.abs(secretNumber - guess);

    //WIN 
    if (guess == secretNumber) {
    hintLabel.setForeground(GREEN);
    hintLabel.setText("🎉 Correct!");

    if (attempts < bestScore) {
        bestScore = attempts;
        bestScoreLabel.setText(String.valueOf(bestScore));
    }
    guessButton.setEnabled(false);
    showWinDialog();
    return;
}
    //HIGH / LOW 
    if (guess < secretNumber) {
        hintLabel.setForeground(RED);
        hintLabel.setText("Too Low");
        lowCard.setBackground(new Color(255,170,170));
        highCard.setBackground(new Color(225,240,255));
    } else {
        hintLabel.setForeground(PRIMARY);
        hintLabel.setText("Too High");
        highCard.setBackground(new Color(170,220,255));
        lowCard.setBackground(new Color(255,230,230));
    }
    //WARMER / COLDER 
    if (previousDistance != -1) {
        if (distance < previousDistance) {
            hintLabel.setForeground(GREEN);
            hintLabel.setText(hintLabel.getText() + " • Warmer 🔥");
        } else if (distance > previousDistance) {
            hintLabel.setForeground(ORANGE);
            hintLabel.setText(hintLabel.getText() + " • Colder ❄");
        }
    }
    //EXTRA HINTS
    if (distance <= 5) {
        hintLabel.setText(hintLabel.getText() + " • VERY CLOSE!");
    } else if (distance <= 15) {
        hintLabel.setText(hintLabel.getText() + " • Close");
    } else if (distance >= 40) {
        hintLabel.setText(hintLabel.getText() + " • Far Away");
    }
    previousDistance = distance;
    guessField.setText("");

    //GAME OVER
    if (attempts >= maxAttempts) {
        guessButton.setEnabled(false);
        showGameOverDialog();
        return;
    }
}
    //ROUNDED PANEL
    class RoundedPanel extends JPanel {
        int radius;

        RoundedPanel(int radius) {
            this.radius = radius;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(getBackground());
            g2.fillRoundRect(0,0,getWidth(),getHeight(),radius,radius);
            super.paintComponent(g2);
            g2.dispose();
        }
    }
    //BUTTON
    class RoundedButton extends JButton {

        RoundedButton(String text){
            super(text);
            setBackground(PRIMARY);
            setForeground(Color.WHITE);
            setFocusPainted(false);
            setFont(new Font("Segoe UI",Font.BOLD,18));
            setBorder(new EmptyBorder(10,20,10,20));
        }
    }
    private void startNewGame() {
    secretNumber = random.nextInt(maxRange) + 1;
    attempts = 0;
    previousDistance = -1;
    attemptLabel.setText("0");
    guessField.setText("");
    guessButton.setEnabled(true);
    hintLabel.setForeground(Color.BLACK);
    hintLabel.setText("Start Guessing!");
    lowCard.setBackground(new Color(255,230,230));
    highCard.setBackground(new Color(225,240,255));
    subtitleLabel.setText(
            "I have selected a number between 1 and "
                    + maxRange + ".");
}
private void showWinDialog() {
    JDialog dialog = new JDialog(this, "Congratulations!", true);
    dialog.setSize(450, 450);
    dialog.setLocationRelativeTo(this);
    dialog.setUndecorated(true);

    RoundedPanel panel = new RoundedPanel(25);
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(new EmptyBorder(25,25,25,25));

    JLabel trophy = new JLabel("🏆", SwingConstants.CENTER);
    trophy.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 55));
    trophy.setAlignmentX(Component.CENTER_ALIGNMENT);
    trophy.setHorizontalAlignment(SwingConstants.CENTER);
    trophy.setPreferredSize(new Dimension(120, 80));
    trophy.setMaximumSize(new Dimension(120, 80));
    trophy.setBorder(new EmptyBorder(15, 0, 10, 0));

    JLabel title = new JLabel("CONGRATULATIONS!");
    title.setFont(new Font("Segoe UI", Font.BOLD, 28));
    title.setForeground(new Color(25,55,170));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel message = new JLabel(
        "<html><div style='text-align:center;'>"
        + "You guessed the number correctly!"
        + "</div></html>",
        SwingConstants.CENTER);
    message.setFont(new Font("Segoe UI", Font.PLAIN,18));
    message.setAlignmentX(Component.CENTER_ALIGNMENT);

    JLabel tries = new JLabel("Attempts Used : " + attempts, SwingConstants.CENTER);
    tries.setFont(new Font("Segoe UI", Font.PLAIN,20));
    tries.setAlignmentX(Component.CENTER_ALIGNMENT);
    tries.setMaximumSize(new Dimension(400,40));

    JLabel score = new JLabel("⭐ Best Score : " + bestScore);
    score.setForeground(new Color(130,40,200));
    score.setFont(new Font("Segoe UI", Font.BOLD,20));
    score.setAlignmentX(Component.CENTER_ALIGNMENT);
    JLabel quote = new JLabel(
        "<html><div style='text-align:center;'>"
        + "Success comes to those who never stop trying!"
        + "</div></html>",
        SwingConstants.CENTER);
    quote.setForeground(Color.GRAY);
    quote.setFont(new Font("Segoe UI", Font.ITALIC,16));
    quote.setAlignmentX(Component.CENTER_ALIGNMENT);

    RoundedButton playAgain = new RoundedButton("Play Again");
    playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
    playAgain.setPreferredSize(new Dimension(180,45));

    playAgain.addActionListener(e -> {
        dialog.dispose();
        startNewGame();
    });
    panel.add(Box.createVerticalStrut(10));
    panel.add(trophy);
    panel.add(Box.createVerticalStrut(15));
    panel.add(title);
    panel.add(Box.createVerticalStrut(20));
    panel.add(message);
    panel.add(Box.createVerticalStrut(15));
    panel.add(tries);
    panel.add(Box.createVerticalStrut(15));
    panel.add(score);
    panel.add(Box.createVerticalStrut(20));
    panel.add(quote);
    panel.add(Box.createVerticalGlue());
    panel.add(playAgain);

    dialog.setContentPane(panel);
    dialog.setVisible(true);
}
private void showGameOverDialog() {
    JDialog dialog = new JDialog(this, "Game Over", true);
    dialog.setSize(450, 520);
    dialog.setLocationRelativeTo(this);
    dialog.setUndecorated(true);

    RoundedPanel panel = new RoundedPanel(25);
    panel.setBackground(Color.WHITE);
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
    panel.setBorder(new EmptyBorder(45,25,25,25));

    // Sad Icon
    JLabel sad = new JLabel("😔", SwingConstants.CENTER);
    sad.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 70));
    sad.setAlignmentX(Component.CENTER_ALIGNMENT);
    sad.setPreferredSize(new Dimension(100, 90));
    sad.setMaximumSize(new Dimension(100, 90));
    sad.setBorder(new EmptyBorder(15, 0, 0, 0));

    // Title
    JLabel title = new JLabel("GAME OVER");
    title.setFont(new Font("Segoe UI", Font.BOLD, 30));
    title.setForeground(new Color(220,70,70));
    title.setAlignmentX(Component.CENTER_ALIGNMENT);

    // Message
    JLabel message = new JLabel(
    "<html><center>"
    + "<h2>You couldn't guess the number.</h2>"
    + "<br>"
    + "Correct Number : <b>" + secretNumber + "</b>"
    + "</center></html>",
    SwingConstants.CENTER);
    message.setFont(new Font("Segoe UI", Font.PLAIN, 18));
    message.setAlignmentX(Component.CENTER_ALIGNMENT);

    //Quote
    String[] quotes = {
        "Every expert was once a beginner.",
        "Don't give up. The next game could be your best one!",
        "Success is built one attempt at a time.",
        "Failure is simply the opportunity to begin again.",
        "Practice makes progress!"
    };
    Random r = new Random();
    JLabel quote = new JLabel(
        "<html><center>\"" +
        quotes[r.nextInt(quotes.length)] +
        "\"</center></html>");

    quote.setForeground(Color.GRAY);
    quote.setFont(new Font("Segoe UI", Font.ITALIC,15));
    quote.setForeground(new Color(120,120,120));
    quote.setBorder(new EmptyBorder(0,20,0,20));
    quote.setAlignmentX(Component.CENTER_ALIGNMENT);
    // Play Again Button
    RoundedButton playAgain = new RoundedButton("Play Again");
    playAgain.setAlignmentX(Component.CENTER_ALIGNMENT);
    playAgain.setPreferredSize(new Dimension(180,45));

    playAgain.addActionListener(e -> {
        dialog.dispose();
        startNewGame();
    });
    // Exit Button
    RoundedButton exit = new RoundedButton("Exit");
    exit.setAlignmentX(Component.CENTER_ALIGNMENT);
    exit.setPreferredSize(new Dimension(180,45));
    exit.addActionListener(e -> {
        System.exit(0);
    });
    panel.add(Box.createVerticalStrut(30 ));
    panel.add(sad);
    panel.add(Box.createVerticalStrut(10));
    panel.add(title);
    panel.add(Box.createVerticalStrut(20));
    panel.add(message);
    panel.add(Box.createVerticalStrut(12));
    panel.add(quote);
    panel.add(Box.createVerticalStrut(18));
    panel.add(playAgain);
    panel.add(Box.createVerticalStrut(10));
    panel.add(exit);

    dialog.setContentPane(panel);
    dialog.setVisible(true);
}
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            numberGuessingGame = new NumberGuessingGame();
        });
    }}