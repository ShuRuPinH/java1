package mess;


import javax.swing.*;
import javax.swing.border.TitledBorder;

import java.awt.*;
import java.awt.event.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TimerTask;


public class Asker extends JDialog {
    private JPanel contentPane;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField textField1;
    private JProgressBar progressBar1;
    private JLabel label2;
    private JLabel Label;
    private JLabel STATUSLabel;
    private JTextArea STATUSTextArea;
    private JLabel temp;
    private JSpinner spinnerH;
    private JSpinner spinnerS;
    private JSpinner spinnerM;
    private JLabel temp2;
    private JPanel jlpTemp;
    private JButton TEMPButton;
    private JProgressBar progressBar2;
    private JButton VIZZButton;
    private JLabel timeNow;
    private JButton TIMERButton;
    private JComboBox comboBox1;
    LocalDateTime endTime = LocalDateTime.now();
    java.util.Timer timer = new java.util.Timer("TimeR");

    int cnt = 0;
    int ttime = 0;

    TimerTask left = new TimerTask() {

        @Override
        public void run() {

            int tt = ttime();

            label2.setText((tt / 60) + ":" + (String.valueOf(tt % 60).length() == 1 ? "0" + tt % 60 : tt % 60));
            progressBar1.setValue(tt);
            timeNow.setText(LocalDateTime.now().toString());

            if (tt == 0) message(textField1.getText());

        }
    };

    public Asker() {

        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);
        STATUSLabel.setText(String.valueOf(treadLive()));

        buttonOK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        buttonCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

        TEMPButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TEMPButton();
            }
        });
        TIMERButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TIMERButton();
            }

            private void TIMERButton() {
                timer();
            }
        });

        VIZZButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                VIZZButton();
            }
        });

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });
// visibles
        jlpTemp.setVisible(false);


        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
    }


    private void VIZZButton() {
        if (this.isVisible() == true) this.setVisible(false);
        else {
            this.setVisible(true);
            timer.schedule(left, 0, 1 * 1 * 1000l);
        }
    }

    private void TEMPButton() {
        if (jlpTemp.isVisible() == true) jlpTemp.setVisible(false);
        else jlpTemp.setVisible(true);


    }

    private void message(String tst) {
        JFrame jf = new JFrame("NB");
        jf.setAlwaysOnTop(true);
        JOptionPane.showMessageDialog(jf,
                "<html><h2><i>" + tst + "</i></h2>");
        VIZZButton();

    }


    private void onOK() {
        // add your code here

        temp.setText(textField1.getText());

        STATUSLabel.setText(String.valueOf(treadLive()));
        STATUSTextArea.setText(treads());
        setTimer();


    }

    void setTimer() {
        progressBar1.setMinimum(0);


        Calendar today = Calendar.getInstance();
        today.setTime(new Date());
        ttime = (int) spinnerH.getValue() * 60 * 60 + (int) spinnerM.getValue() * 60 + (int) spinnerS.getValue();

        endTime = LocalDateTime.of(today.get(Calendar.YEAR), today.get(Calendar.MONTH) + 1, today.get(Calendar.DAY_OF_MONTH), (int) spinnerH.getValue(), (int) spinnerM.getValue(), (int) spinnerS.getValue());
        temp.setText(endTime.toString());
        temp2.setText(String.valueOf(ttime));
        progressBar1.setMaximum(ttime());


        label2.setVisible(true);
    }


    int treadLive() {

        return Thread.activeCount();
    }

    String treads() {
        String res = "";
        for (Map.Entry x : Thread.getAllStackTraces().entrySet()) {
            res = res + x.getKey() + "\n";
        }
        System.out.println();
        return res;
    }


    private void onCancel() {
        // add your code here if necessary
        dispose();
        System.exit(0);

    }

    ///////////
    void timer() {


        label2.setVisible(false);


        timer.schedule(left, 0, 1 * 1 * 1000l);


    }

    //////////////
    Integer ttime() {
        LocalDateTime now = LocalDateTime.now();
        Duration d = Duration.between(now, endTime);
        int tt = (int) d.toSeconds();


        // temp2.setText(String.valueOf(tt));
        return tt;
    }

    public static void main(String[] args) {
        Asker dialog = new Asker();
        dialog.setTitle("Title TTT");
        dialog.timer();
        dialog.pack();
        dialog.setVisible(true);


    }


    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        contentPane = new JPanel();
        contentPane.setLayout(new GridBagLayout());
        contentPane.setBackground(new Color(-7824243));
        contentPane.setMinimumSize(new Dimension(400, 700));
        contentPane.setPreferredSize(new Dimension(400, 800));
        contentPane.setRequestFocusEnabled(false);
        contentPane.setBorder(BorderFactory.createTitledBorder(null, "ProgTimer", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.TOP, this.$$$getFont$$$(null, -1, -1, contentPane.getFont()), new Color(-10269198)));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-7824243));
        GridBagConstraints gbc;
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 27;
        gbc.gridwidth = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel1, gbc);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-7824243));
        panel1.add(panel2, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        buttonCancel = new JButton();
        buttonCancel.setText("EXIT");
        panel2.add(buttonCancel, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        buttonOK = new JButton();
        buttonOK.setText("OK");
        panel1.add(buttonOK, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-873406));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 26;
        gbc.gridwidth = 10;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(panel3, gbc);
        panel3.setBorder(BorderFactory.createTitledBorder(BorderFactory.createLineBorder(new Color(-16777216)), null, TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-873406));
        panel3.add(panel4, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        Label = new JLabel();
        Label.setBackground(new Color(-873406));
        Label.setText("Treads:");
        panel4.add(Label, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        STATUSLabel = new JLabel();
        STATUSLabel.setBackground(new Color(-873406));
        STATUSLabel.setText("STATUS");
        panel4.add(STATUSLabel, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setFocusable(false);
        scrollPane1.setVerticalScrollBarPolicy(22);
        panel4.add(scrollPane1, new com.intellij.uiDesigner.core.GridConstraints(2, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_BOTH, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_SHRINK | com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        STATUSTextArea = new JTextArea();
        STATUSTextArea.setAutoscrolls(true);
        STATUSTextArea.setBackground(new Color(-873406));
        STATUSTextArea.setText("STATUS");
        scrollPane1.setViewportView(STATUSTextArea);
        progressBar1 = new JProgressBar();
        progressBar1.setBackground(new Color(-862637));
        progressBar1.setBorderPainted(false);
        progressBar1.setForeground(new Color(-10909809));
        progressBar1.setIndeterminate(false);
        progressBar1.setStringPainted(false);
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 15;
        gbc.gridwidth = 10;
        gbc.gridheight = 11;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipady = 25;
        contentPane.add(progressBar1, gbc);
        textField1 = new JTextField();
        textField1.setSelectionColor(new Color(-904659));
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 10;
        gbc.gridheight = 7;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(textField1, gbc);
        label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$(null, Font.BOLD, 48, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("TIME");
        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 10;
        gbc.gridheight = 5;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        contentPane.add(label2, gbc);
        spinnerH = new JSpinner();
        spinnerH.setAutoscrolls(false);
        spinnerH.setBackground(new Color(-1));
        spinnerH.setDoubleBuffered(false);
        spinnerH.setEnabled(true);
        Font spinnerHFont = this.$$$getFont$$$(null, -1, -1, spinnerH.getFont());
        if (spinnerHFont != null) spinnerH.setFont(spinnerHFont);
        spinnerH.setForeground(new Color(-16777216));
        spinnerH.setInheritsPopupMenu(false);
        spinnerH.setMaximumSize(new Dimension(100, 26));
        spinnerH.setMinimumSize(new Dimension(100, 26));
        spinnerH.setOpaque(false);
        spinnerH.setToolTipText("hour");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.insets = new Insets(0, 10, 0, 10);
        contentPane.add(spinnerH, gbc);
        spinnerS = new JSpinner();
        spinnerS.setMaximumSize(new Dimension(100, 26));
        spinnerS.setMinimumSize(new Dimension(100, 26));
        spinnerS.setToolTipText("88");
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.insets = new Insets(0, 10, 0, 0);
        contentPane.add(spinnerS, gbc);
        spinnerM = new JSpinner();
        spinnerM.setMaximumSize(new Dimension(100, 26));
        spinnerM.setMinimumSize(new Dimension(100, 26));
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 10;
        gbc.insets = new Insets(0, 10, 0, 10);
        contentPane.add(spinnerM, gbc);
        final JLabel label1 = new JLabel();
        label1.setText("H:");
        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(label1, gbc);
        final JLabel label3 = new JLabel();
        label3.setText("M:");
        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(label3, gbc);
        final JLabel label4 = new JLabel();
        label4.setText("S:");
        gbc = new GridBagConstraints();
        gbc.gridx = 6;
        gbc.gridy = 8;
        gbc.anchor = GridBagConstraints.WEST;
        contentPane.add(label4, gbc);
        TEMPButton = new JButton();
        TEMPButton.setText("TEMP !");
        gbc = new GridBagConstraints();
        gbc.gridx = 5;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(TEMPButton, gbc);
        jlpTemp = new JPanel();
        jlpTemp.setLayout(new com.intellij.uiDesigner.core.GridLayoutManager(3, 2, new Insets(0, 0, 0, 0), -1, -1));
        gbc = new GridBagConstraints();
        gbc.gridx = 7;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.BOTH;
        contentPane.add(jlpTemp, gbc);
        temp = new JLabel();
        temp.setForeground(new Color(-15323637));
        temp.setText("Label");
        jlpTemp.add(temp, new com.intellij.uiDesigner.core.GridConstraints(0, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
        jlpTemp.add(spacer1, new com.intellij.uiDesigner.core.GridConstraints(0, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_HORIZONTAL, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        temp2 = new JLabel();
        temp2.setText("Label");
        jlpTemp.add(temp2, new com.intellij.uiDesigner.core.GridConstraints(1, 0, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_WEST, com.intellij.uiDesigner.core.GridConstraints.FILL_NONE, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
        jlpTemp.add(spacer2, new com.intellij.uiDesigner.core.GridConstraints(2, 1, 1, 1, com.intellij.uiDesigner.core.GridConstraints.ANCHOR_CENTER, com.intellij.uiDesigner.core.GridConstraints.FILL_VERTICAL, 1, com.intellij.uiDesigner.core.GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        VIZZButton = new JButton();
        VIZZButton.setText("VIZZ");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(VIZZButton, gbc);
        timeNow = new JLabel();
        Font timeNowFont = this.$$$getFont$$$("JetBrains Mono", -1, 20, timeNow.getFont());
        if (timeNowFont != null) timeNow.setFont(timeNowFont);
        timeNow.setText("Label");
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridwidth = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(timeNow, gbc);
        TIMERButton = new JButton();
        TIMERButton.setText("TIMER");
        gbc = new GridBagConstraints();
        gbc.gridx = 9;
        gbc.gridy = 14;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(TIMERButton, gbc);
        comboBox1 = new JComboBox();
        comboBox1.setBackground(new Color(-869995));
        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 15;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        contentPane.add(comboBox1, gbc);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return contentPane;
    }
}
