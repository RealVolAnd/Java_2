package Homework_4;

import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AboutDialogWindow extends JDialog {
    public AboutDialogWindow(JFrame owner) {
        super(owner, "AboutDialog", true);

        setBounds(200,200,400,200);
        setUndecorated(true);
        setBackground(Color.LIGHT_GRAY);
        getRootPane().setBorder(BorderFactory.createLineBorder(Color.BLACK));

        SimpleAttributeSet attribs = new SimpleAttributeSet();
        StyleConstants.setAlignment(attribs, StyleConstants.ALIGN_CENTER);
        StyleConstants.setFontSize(attribs, 18);
        StyleConstants.setBold(attribs, true);

        JTextPane jp1 = new JTextPane();
        jp1.setBackground(Color.LIGHT_GRAY);
        jp1.setParagraphAttributes(attribs, true);
        jp1.setText("\n\nMyChat 1.0.0 Beta\nDesigned by Real VolAnd(c)\n2020");
        jp1.setEditable(false);
        add(jp1);

        Font font = new Font("Verdana", Font.PLAIN, 18);
        JButton ok = new JButton("OK");
        ok.setFont(font);
        ok.setPreferredSize(new Dimension(120, 30));
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        JPanel panel = new JPanel();
        panel.setBackground(Color.LIGHT_GRAY);
        panel.add(ok);
        add(panel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setVisible(true);
    }

}
