package com.lnw.acceeeeeppt.scene.level.level3_term_of_service;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Level3ToSUI extends Level3BaseGameLevel {
    private JCheckBox[] uiCheckBoxes;
    private JButton agreeButton;
    private Level3CheckboxPuzzle puzzle;

    public Level3ToSUI() {
        setPreferredSize(new Dimension(1080, 640));
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(20, 20, 20, 20));

        JLabel titleLabel = new JLabel("Terms of Service");
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        titleLabel.setBorder(new EmptyBorder(0, 0, 10, 0));
        add(titleLabel, BorderLayout.NORTH);

        JTextArea tosText = new JTextArea();
        tosText.setEditable(false);
        tosText.setFocusable(false);
        tosText.setLineWrap(true);
        tosText.setWrapStyleWord(true);
        tosText.setFont(new Font("Serif", Font.PLAIN, 14));
        tosText.setText(
                "Please read these Terms of Service (\"Terms\", \"ToS\") carefully before proceeding. By accessing, clicking, hovering, or even looking at this application, you agree to be bound by these Terms. If you do not agree, you must immediately close the application, though we both know you will just blindly click \"Agree\" anyway.\n\n1. Acceptance of the Inevitable\nBy attempting to interact with the Checkboxes below, the User acknowledges that their sense of control is merely an illusion. The Service is provided \"as is,\" and any frustration, existential dread, or sudden urges to throw the mouse at the monitor are solely the User's responsibility.\n\n2. Culinary Obligations and Contingencies\nIn the event of a total logical deadlock, the User agrees to remain calm and procure the following materials: 1 cup of all-purpose flour, 2 tablespoons of sugar, 1 cup of milk, and 2 large eggs. Whisk the dry ingredients, gently fold in the wet ingredients, and pour onto a hot, buttered griddle. Wait until bubbles form on the surface before flipping. Never gonna give you up, never gonna let you down.\n\n3. Quantum Boolean Entanglement (CRITICAL CLAUSE)\nThe User explicitly acknowledges that modifying the state of any single agreement parameter shall inherently trigger a localized quantum reversal effect, instantaneously inverting the truth values of its immediate vertical adjacent neighbors.\n\n4. Limitation of Liability\nUnder no circumstances shall the developers be held liable for any loss of sanity.\n\n5. Mandatory Scrolling Provision\nThis section exists solely to ensure that the document is long enough to require the use of a scrollbar. You must scroll to the very bottom to prove your dedication to this interface.\n\n6. The Illusion of Choice\nEven after you finish reading this entire text block, you still have to solve the paradox of the checkbox puzzle below. Good luck.\n\n7. Final Confirmation\nBy reaching this point, you have officially scrolled through the text. Now, go solve the puzzle.");

        tosText.setMargin(new Insets(10, 10, 10, 10));

        JScrollPane scrollPane = new JScrollPane(tosText);
        add(scrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BorderLayout());

        JPanel checkboxPanel = new JPanel();
        checkboxPanel.setLayout(new BoxLayout(checkboxPanel, BoxLayout.Y_AXIS));
        checkboxPanel.setBorder(new EmptyBorder(15, 0, 15, 0));

        String[] labels = {
                "I unconditionally accept the terms and conditions stated in this document.",
                "I acknowledge that agreeing to this clause will actively contradict and modify my agreement to the adjacent clauses.",
                "I confirm that my consent herein is highly volatile and will toggle the state of the surrounding agreements.",
                "I declare that I have fully read, understood, and accept the logical paradoxes created by my previous actions.",
                "I hereby completely refuse to decline any of the aforementioned terms."
        };

        boolean[] initialState = { false, false, false, false, false };
        puzzle = new Level3CheckboxPuzzle(initialState);
        uiCheckBoxes = new JCheckBox[5];

        Font checkboxFont = new Font("Serif", Font.PLAIN, 14);

        for (int i = 0; i < 5; i++) {
            uiCheckBoxes[i] = new JCheckBox(labels[i]);
            uiCheckBoxes[i].setFont(checkboxFont);
            final int index = i;
            uiCheckBoxes[i].addActionListener(e -> handleToggle(index));
            checkboxPanel.add(uiCheckBoxes[i]);
            checkboxPanel.add(Box.createVerticalStrut(10));
        }

        bottomPanel.add(checkboxPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        agreeButton = new JButton("Agree");
        agreeButton.setEnabled(false);

        agreeButton.addActionListener(e -> {
            if (!isReadingComplete) {
                JOptionPane.showMessageDialog(this,
                        "You must scroll down and read the entire Terms of Service before agreeing!",
                        "Reading Incomplete",
                        JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Level 3 Passed!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        buttonPanel.add(agreeButton);

        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        startScrollMonitor(scrollPane);
    }

    private void handleToggle(int index) {
        puzzle.toggle(index);
        boolean[] states = puzzle.getStates();

        for (int i = 0; i < 5; i++) {
            uiCheckBoxes[i].setSelected(states[i]);
        }

        validatePuzzle(puzzle);
    }

    @Override
    public void validatePuzzle(Level3CheckboxPuzzle puzzle) {
        agreeButton.setEnabled(puzzle.isSolved());
    }

    @Override
    public void startScrollMonitor(JScrollPane scrollPane) {
        Thread monitorThread = new Thread(() -> {
            try {
                while (!isReadingComplete) {
                    Thread.sleep(50);
                    JScrollBar verticalBar = scrollPane.getVerticalScrollBar();
                    int max = verticalBar.getMaximum() - verticalBar.getVisibleAmount();

                    if (max > 0) {
                        int currentValue = verticalBar.getValue();
                        int percentage = (int) (((double) currentValue / max) * 100);

                        if (percentage > readProgress) {
                            readProgress = percentage;
                            repaint();
                        }

                        if (readProgress >= 99) {
                            readProgress = 100;
                            isReadingComplete = true;
                            repaint();
                        }
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        monitorThread.start();
    }

    @Override
    public void checkInterface(Level3GameLevel level) {
    }

    @Override
    public void resetPuzzle() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        renderCustomGraphics(g);
    }

    // in case someone want to test it
    /*
     * public static void main(String[] args) {
     * JFrame frame = new JFrame("Term of Service");
     * frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     * frame.setSize(750, 650);
     * frame.add(new ToS_UI());
     * frame.setLocationRelativeTo(null);
     * frame.setVisible(true);
     * }
     */
}
