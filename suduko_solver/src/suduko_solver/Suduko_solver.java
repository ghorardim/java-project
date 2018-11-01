import java.awt.Container;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
 
public class Suduko_solver extends JFrame implements ActionListener {
 
    private final Container C;
    private final int[][] grid = new int[10][10];
    private final JTextField tf[][] = new JTextField[10][10];
    private JButton btn, btn2;
    private Font f;
 
    boolean isSafe(int num, int r, int c) {
        int i, j, rs, cs;
        for (i = 0; i < 9; i++) {
            if (grid[r][i] == num) {
                return false;
            }
        }
        for (i = 0; i < 9; i++) {
            if (grid[i][c] == num) {
                return false;
            }
        }
        rs = r - r % 3;
        cs = c - c % 3;
        for (i = 0; i < 3; i++) {
            for (j = 0; j < 3; j++) {
                if (grid[rs + i][cs + j] == num) {
                    return false;
                }
            }
        }
        return true;
    }
 
    boolean solve() {
        //     System.out.println("hello");
        boolean flag;
        flag = false;
        int i, j = 0;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (grid[i][j] == 0) {
                    flag = true;
                    break;
                }
            }
            if (flag) {
                break;
            }
        }
        int rval = i;
        int cval = j;
        if (!flag) {
            return true;
        } else {
            for (i = 1; i <= 9; i++) {
                if (isSafe(i, rval, cval)) {
                    grid[rval][cval] = i;
                    if (solve()) {
                        return true;
                    }
                    grid[rval][cval] = 0;
                }
            }
        }
        return false;
    }
 
    Suduko_solver() {
        C = this.getContentPane();
        C.setLayout(null);
        f = new Font("Arial", Font.BOLD, 25);
        JButton btn = new JButton("Solve");
        btn.setBounds(180, 520, 80, 50);
        C.add(btn);
 
//        JButton btn2 = new JButton("Clear");
//        btn2.setBounds(250, 520, 80, 50);
//        C.add(btn2);
        int a, b;
        a = 10;
        b = 60;
        String str;
        for (int i = 0; i < 9; i++) {
            a = 10;
            for (int j = 0; j < 9; j++) {
                grid[i][j] = 0;
                tf[i][j] = new JTextField();
                tf[i][j].setFont(f);
                tf[i][j].setHorizontalAlignment(JTextField.CENTER);
                tf[i][j].setBounds(a, b, 50, 50);
               
                C.add(tf[i][j]);
                a += 50;
            }
            b += 50;
        }
        btn.addActionListener(this);
        //   btn2.addActionListener(this);
 
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {
 
        // System.out.println("hellow...2");
        //  if (e.getSource() == btn) {
        String str, str2;
        boolean key = true;
 
        for (int i = 0; i < 9; i++) {
 
            for (int j = 0; j < 9; j++) {
                str = tf[i][j].getText();
                if (str.length() > 0) {
                    grid[i][j] = Integer.parseInt(str);
                    if (grid[i][j] > 9 && grid[i][j] < 1) {
                        key = false;
                        str2 = "Wrong input in row " + (i + 1) + " colom " + (j + 1);
                        JOptionPane.showMessageDialog(null, str2);
                    }
                }
            }
 
        }
 
        if (solve() && key) {
            for (int i = 0; i < 9; i++) {
 
                for (int j = 0; j < 9; j++) {
                    str = "" + grid[i][j];
                    tf[i][j].setText(str);
                }
 
            }
        } else {
            JOptionPane.showMessageDialog(null, "No solution exists for this input");
 
        }
//        }
//else {
//
//            Suduko_solver frm = new Suduko_solver();
//            frm.setVisible(true);
//            frm.setBounds(100, 100, 480, 600);
//            frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frm.setResizable(false);
//        }
 
    }
 
    public static void main(String[] args) {
        Suduko_solver frm = new Suduko_solver();
        frm.setVisible(true);
        frm.setBounds(100, 100, 480, 600);
        frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frm.setResizable(false);
    }
 
}