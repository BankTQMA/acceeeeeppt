package com.lnw.acceeeeeppt.scene.level.level3;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ToS_UI extends JPanel {
    private JCheckBox[] uiCheckBoxes;
    private JButton agreeButton;
    private CheckboxPuzzle puzzle;

    public ToS_UI() {
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
        tosText.setText("Please read these Terms of Service (\"Terms\", \"ToS\") carefully before proceeding. By accessing, clicking, hovering, or even looking at this application, you agree to be bound by these Terms. If you do not agree, you must immediately close the application, though we both know you will just blindly click \"Agree\" anyway.\n\n" +
                "1. Acceptance of the Inevitable\n" +
                "By attempting to interact with the Checkboxes below, the User acknowledges that their sense of control is merely an illusion. The Service is provided \"as is,\" and any frustration, existential dread, or sudden urges to throw the mouse at the monitor are solely the User's responsibility.\n\n" +
                "2. Culinary Obligations and Contingencies\n" +
                "In the event of a total logical deadlock, the User agrees to remain calm and procure the following materials: 1 cup of all-purpose flour, 2 tablespoons of sugar, 1 cup of milk, and 2 large eggs. Whisk the dry ingredients, gently fold in the wet ingredients, and pour onto a hot, buttered griddle. Wait until bubbles form on the surface before flipping. Never gonna give you up, never gonna let you down, never gonna run around and desert you. The User acknowledges that these pancakes will not solve the puzzle, but they are delicious.\n\n" +
                "3. Quantum Boolean Entanglement (CRITICAL CLAUSE)\n" +
                "The User explicitly acknowledges that the agreement interface located at the bottom of this document utilizes highly unstable, zero-gravity Boolean variables. Therefore, modifying the state of any single agreement parameter (hereinafter referred to as the \"Target Checkbox\") shall inherently trigger a localized quantum reversal effect. Specifically, activating or deactivating the Target Checkbox will instantaneously invert the truth values (True to False, or False to True) of its immediate vertical adjacent neighbors (both the preceding and succeeding Checkboxes, where applicable). The User must achieve absolute synchronization (all states = True) to proceed.\n\n" +
                "4. Limitation of Liability\n" +
                "Under no circumstances shall the developers be held liable for any loss of sanity, wasted time, or accidental agreements to sell your digital soul. You were warned in Section 3, assuming you actually read it.");
        
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

        boolean[] initialState = {false, false, false, false, false};
        puzzle = new CheckboxPuzzle(initialState);
        uiCheckBoxes = new JCheckBox[5];

        Font checkboxFont = new Font("Serif", Font.PLAIN, 14);

        for (int i = 0; i < 5; i++) {
            uiCheckBoxes[i] = new JCheckBox(labels[i]);
            uiCheckBoxes[i].setFont(checkboxFont);
            final int index = i;
            uiCheckBoxes[i].addActionListener((ActionEvent e) -> handleToggle(index));
            checkboxPanel.add(uiCheckBoxes[i]);
            checkboxPanel.add(Box.createVerticalStrut(10));
        }

        bottomPanel.add(checkboxPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        agreeButton = new JButton("Agree");
        agreeButton.setEnabled(false);
        buttonPanel.add(agreeButton);
        
        bottomPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);
    }

    private void handleToggle(int index) {
        puzzle.toggle(index);
        boolean[] states = puzzle.getStates();
        
        for (int i = 0; i < 5; i++) {
            uiCheckBoxes[i].setSelected(states[i]);
        }

        agreeButton.setEnabled(puzzle.isSolved());
    }
    //in case someone want to test it
    /*public static void main(String[] args) {
        JFrame frame = new JFrame("Term of Service");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750, 650);
        frame.add(new ToS_UI());
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }*/
}