package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import model.Grid;
import model.Card;

/**
 * Main game window for the memory card game
 * Handles both the window and grid display
 */
public class GameView extends JFrame {
    private JButton[][] cardButtons;
    private JLabel statusLabel;
    private JLabel timerLabel;
    private JLabel lifeLabel;
    private int rows;
    private int cols;
    
    // Color palette for cards
    private static final Color CARD_BACK_COLOR = new Color(70, 130, 180); 
    private static final Color CARD_FACE_COLOR = Color.WHITE;
    private static final Color HIGHLIGHT_COLOR = Color.YELLOW;
    

    public GameView(int rows, int cols, ActionListener controller) {
        this.rows = rows;
        this.cols = cols;
        
        setTitle("Memory Card Game");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        
        JPanel gridPanel = new JPanel(new GridLayout(rows, cols, 5, 5));
        gridPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        initializeCardButtons(gridPanel, controller);
        add(gridPanel, BorderLayout.CENTER);
        
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        
        statusLabel = new JLabel("Select a card to begin", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        timerLabel = new JLabel("Time", JLabel.LEFT);
        timerLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        lifeLabel = new JLabel("Lives: ", JLabel.CENTER);
        lifeLabel.setFont(new Font("Arial", Font.BOLD, 14));
        
        JButton backButton = new JButton("Back to Menu");
        backButton.setActionCommand("back_to_menu");
        backButton.addActionListener(controller);
        
        statusPanel.add(statusLabel, BorderLayout.NORTH);
        statusPanel.add(timerLabel, BorderLayout.WEST);
        statusPanel.add(lifeLabel, BorderLayout.SOUTH);
        statusPanel.add(backButton, BorderLayout.EAST);
        add(statusPanel, BorderLayout.SOUTH);
        
        // Set window size and position
        setSize(cols * 90 + 40, rows * 90 + 100);
        setLocationRelativeTo(null);
    }
    
    /**
     * Initialize all card buttons in the grid
     */
    private void initializeCardButtons(JPanel gridPanel, ActionListener controller) {
        cardButtons = new JButton[rows][cols];
        
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                JButton button = new JButton();
                button.setPreferredSize(new Dimension(80, 80));
                button.setBackground(CARD_BACK_COLOR);
                button.setFont(new Font("Arial", Font.BOLD, 24));
                button.setActionCommand(r + "," + c);
                button.addActionListener(controller);
                
                cardButtons[r][c] = button;
                gridPanel.add(button);
            }
        }
    }
    
 
    public void updateGrid(Grid grid) {
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                Card card = grid.getCard(r, c);
                updateCardButton(r, c, card);
            }
        }
        
        revalidate();
        repaint();
    }
    

    private void updateCardButton(int row, int col, Card card) {
        if (card == null) return;
        
        JButton button = cardButtons[row][col];
        if (card.isFlipped()) {
            int value = card.getValue();
            Color cardColor = getColorForValue(value);
            
            button.setText(String.valueOf(value));
            button.setBackground(cardColor);
            
            if (isDarkColor(cardColor)) {
                button.setForeground(Color.WHITE);
            } else {
                button.setForeground(Color.BLACK);
            }
        } else {
            // Back of card
            button.setText("");
            button.setBackground(CARD_BACK_COLOR);
            button.setForeground(Color.WHITE);
        }
        
        // Highlight selected card
        if (card.isHighlighted()) {
            button.setBorder(BorderFactory.createLineBorder(HIGHLIGHT_COLOR, 3));
        } else {
            button.setBorder(UIManager.getBorder("Button.border"));
        }
        
        // Disable locked cards
        button.setEnabled(!card.isLocked());
        if(!card.isLocked() && card.isFrozen()) {
        	button.setEnabled(!card.isFrozen());
        }
    }

    private Color getColorForValue(int value) {
        Color[] cardColors = {
            new Color(255, 105, 97),  // Light red
            new Color(119, 221, 119), // Light green
            new Color(122, 179, 255), // Light blue
            new Color(255, 209, 102), // Yellow
            new Color(188, 143, 143), // Rosy brown
            new Color(179, 139, 255), // Lavender
            new Color(255, 167, 108), // Light orange
            new Color(158, 224, 158), // Pale green
            new Color(94, 185, 242),  // Sky blue
            new Color(250, 177, 237), // Pink
            new Color(129, 222, 196), // Aqua
            new Color(233, 133, 233), // Orchid
            new Color(169, 222, 244), // Pale blue
            new Color(168, 216, 185), // Mint
            new Color(255, 189, 189), // Salmon
            new Color(215, 189, 226), // Thistle
            new Color(192, 192, 192), // Silver
            new Color(255, 223, 186)  // Peach
        };
        
        int index = Math.abs(value) % cardColors.length;
        return cardColors[index];
    }

 
    private boolean isDarkColor(Color color) {
        double brightness = (0.299 * color.getRed() + 
                            0.587 * color.getGreen() + 
                            0.114 * color.getBlue()) / 255;
        
        return brightness < 0.5;
    }
        
    public void updateStatus(String message) {
        statusLabel.setText(message);
    }
    
    public void updateTime(String time) {
    	timerLabel.setText(time);
    }
    
    public void updateLife(String lives) {
    	lifeLabel.setText(lives);
    }
}