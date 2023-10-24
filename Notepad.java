import javax.swing.*;
import java.awt.Color;
import java.awt.event.*;
import java.io.*;
import javax.swing.JFileChooser;

public class Notepad extends JFrame implements ActionListener {
    JMenuBar mb;
    JMenu fl, ed, vw, hp;
    JMenuItem m1, m2, m3, m4, m5, m6, m7, m8;
    JTextField t1, t2;
    JTextArea m9;
    JDialog b1;
    File currentFile;

    Notepad() {
        getContentPane().setBackground(Color.GRAY);
        mb = new JMenuBar();
        fl = new JMenu("File");
        ed = new JMenu("Edit");
        //vw = new JMenu("View");
        hp = new JMenu("Help");
        m1 = new JMenuItem("New");
        m2 = new JMenuItem("Open File");
        m3 = new JMenuItem("Save");
        m4 = new JMenuItem("About Notepad");
        m5 = new JMenuItem("Select");
        m6 = new JMenuItem("Cut");
        m7 = new JMenuItem("Copy");
        m8 = new JMenuItem("Paste");
        m9 = new JTextArea(5, 20);
        JScrollPane sp = new JScrollPane(m9);

        add(sp);

        fl.add(m1);
        fl.add(m2);
        fl.addSeparator();
        fl.add(m3);

        ed.add(m5);
        ed.add(m6);
        ed.addSeparator();
        ed.add(m7);
        ed.add(m8);

        hp.add(m4);

        mb.add(fl);
        mb.add(ed);
        //mb.add(vw);
        mb.add(hp);

        m1.addActionListener(this);
        m2.addActionListener(this);
        m3.addActionListener(this);
        m4.addActionListener(this);
        m5.addActionListener(this);
        m6.addActionListener(this);
        m7.addActionListener(this);
        m8.addActionListener(this);

        setLocationRelativeTo(null);
        setTitle("Untitled - Notepad");
        setJMenuBar(mb);
        setSize(500, 500);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String s = e.getActionCommand();
        if (s.equals("New")) {
            m9.setText("");
            currentFile = null;
            setTitle("Untitled - Notepad");
        } else if (s.equals("Open File")) {
            JFileChooser fc = new JFileChooser();
            int option = fc.showOpenDialog(this);
            if (option == JFileChooser.APPROVE_OPTION) {
                currentFile = fc.getSelectedFile();
                try {
                    BufferedReader br = new BufferedReader(new FileReader(currentFile));
                    m9.setText(""); // Clear existing text
                    String line;
                    while ((line = br.readLine()) != null) {
                        m9.append(line + "\n");
                    }
                    br.close();
                    setTitle(currentFile.getName() + " - Notepad");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        } else if (s.equals("Save")) {
            if (currentFile != null) {
                try {
                    BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
                    bw.write(m9.getText());
                    bw.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                saveAs(); // If no current file, use "Save As" functionality
            }
        } else if (s.equals("Select")) {
            m9.selectAll();
        } else if (s.equals("Cut")) {
            m9.cut();
        } else if (s.equals("Copy")) {
            m9.copy();
        } else if (s.equals("Paste")) {
            m9.paste();
        } else if (s.equals("About Notepad")) {
            JOptionPane.showMessageDialog(null, "Neha Hombal");
        }
    }

    private void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            currentFile = fileChooser.getSelectedFile();
            try {
                BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
                bw.write(m9.getText());
                bw.close();
                setTitle(currentFile.getName() + " - Notepad");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Notepad();
    }
}
