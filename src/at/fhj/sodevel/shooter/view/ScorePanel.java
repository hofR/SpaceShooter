package at.fhj.sodevel.shooter.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ScorePanel extends JPanel {
    private JLabel scoreLabel;
    private JLabel playerLabel;
    private JLabel liveLabel;

    public ScorePanel() {
        this.setLayout(new BorderLayout());
        this.setForeground(Color.white);
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(800, 50));
        this.setMaximumSize(this.getPreferredSize());
        this.setMinimumSize(this.getPreferredSize());
        this.setFont(new Font("Verdana", Font.BOLD, 15));
        this.setVisible(true);

        this.playerLabel = new JLabel("", SwingConstants.LEFT);
        configureJLabel(this.playerLabel, 200);

        this.liveLabel = new JLabel("", SwingConstants.CENTER);
        configureJLabel(this.liveLabel, 400);

        this.scoreLabel = new JLabel("", SwingConstants.RIGHT);
        configureJLabel(this.scoreLabel, 200);

        this.add(playerLabel, BorderLayout.WEST);
        this.add(scoreLabel, BorderLayout.EAST);
        this.add(liveLabel, BorderLayout.CENTER);
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getPlayerLabel() {
        return playerLabel;
    }

    public JLabel getLiveLabel() {
        return liveLabel;
    }

    //Method sets font color, margin and size for the class' JLabels
    private void configureJLabel(JLabel label, int width) {
        label.setForeground(Color.white);
        Border labelBorder = label.getBorder();
        Border labelMargin = new EmptyBorder(10, 10, 10, 10);
        label.setBorder(new CompoundBorder(labelBorder, labelMargin));
        label.setPreferredSize(new Dimension(width, 50));
    }
}