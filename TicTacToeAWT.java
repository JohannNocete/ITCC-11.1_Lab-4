
import java.awt.*;
import java.awt.event.*;

public class TicTacToeAWT extends Frame implements ActionListener {
    private Button[] buttons = new Button[9];
    private Button resetButton;
    private Label statusLabel;
    private boolean xTurn = true; 

    public TicTacToeAWT() {
       
        setTitle("AWT Tic Tac Toe");
        setSize(400, 450);
        setLayout(new BorderLayout());
        setResizable(false);

        
        statusLabel = new Label("Player X's turn");
        statusLabel.setAlignment(Label.CENTER);
        add(statusLabel, BorderLayout.NORTH);

        
        Panel gridPanel = new Panel();
        gridPanel.setLayout(new GridLayout(3, 3));
        for (int i = 0; i < 9; i++) {
            buttons[i] = new Button("");
            buttons[i].setFont(new Font("Arial", Font.BOLD, 60));
            buttons[i].addActionListener(this);
            gridPanel.add(buttons[i]);
        }
        add(gridPanel, BorderLayout.CENTER);

        resetButton = new Button("New Game");
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Button clicked = (Button) e.getSource();
        if (!clicked.getLabel().equals("")) return;

        clicked.setLabel(xTurn ? "X" : "O");
        clicked.setEnabled(false);

        if (checkWinner()) {
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + " wins!");
            disableAllButtons();
        } else if (isDraw()) {
            statusLabel.setText("It's a draw!");
        } else {
            xTurn = !xTurn;
            statusLabel.setText("Player " + (xTurn ? "X" : "O") + "'s turn");
        }
    }

    private boolean checkWinner() {
        String[][] combos = {
            {getLabel(0), getLabel(1), getLabel(2)},
            {getLabel(3), getLabel(4), getLabel(5)},
            {getLabel(6), getLabel(7), getLabel(8)},
            {getLabel(0), getLabel(3), getLabel(6)},
            {getLabel(1), getLabel(4), getLabel(7)},
            {getLabel(2), getLabel(5), getLabel(8)},
            {getLabel(0), getLabel(4), getLabel(8)},
            {getLabel(2), getLabel(4), getLabel(6)}
        };

        for (String[] combo : combos) {
            if (!combo[0].equals("") && combo[0].equals(combo[1]) && combo[1].equals(combo[2])) {
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        for (Button b : buttons) {
            if (b.getLabel().equals("")) {
                return false;
            }
        }
        return true;
    }

    private void disableAllButtons() {
        for (Button b : buttons) {
            b.setEnabled(false);
        }
    }

    private void resetGame() {
        for (Button b : buttons) {
            b.setLabel("");
            b.setEnabled(true);
        }
        xTurn = true;
        statusLabel.setText("Player X's turn");
    }

    private String getLabel(int index) {
        return buttons[index].getLabel();
    }

    public static void main(String[] args) {
        new TicTacToeAWT();
    }
}
