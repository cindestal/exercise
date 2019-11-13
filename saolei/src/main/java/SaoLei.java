import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class SaoLei implements ActionListener {

    JFrame frame = new JFrame("扫雷游戏");
    JButton reset = new JButton("重新开始");
    Container container = new Container();

    //任务三：数据结构
    final int row = 20;
    final int col = 20;
    final int leiCount = 30;
    JButton[][] buttons = new JButton[row][col];
    int[][] counts = new int[row][col];
    final int LEICODE = 10;

    //构造函数
    public SaoLei() {

        //任务一：显示框
        frame.setSize(600, 700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //任务二：显示重新按钮
        addResetButton();

        //任务四：add buttons
        addButtons();

        //任务6：埋雷
        addLei();

        //任务7：添加雷的计算
        calcNeiboLei();

        frame.setVisible(true);


    }

    void addResetButton() {
        reset.setBackground(Color.green);
        reset.setOpaque(true);

        frame.add(reset, BorderLayout.NORTH);
    }

    void addButtons() {
        frame.add(container, BorderLayout.CENTER);
        container.setLayout(new GridLayout(row, col));
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                JButton button = new JButton();
                button.setBackground(Color.yellow);
                button.setOpaque(true);
                button.addActionListener(this);
                buttons[i][j] = button;
                container.add(button);
            }
        }
    }

    void addLei() {
        Random rand = new Random();
        int randRow, randCol;
        for (int i = 0; i < leiCount; i++) {
            randRow = rand.nextInt(row);
            randCol = rand.nextInt(col);

            if (counts[randRow][randCol] == LEICODE) {
                i--;
            } else {
                counts[randRow][randCol] = LEICODE;
                //buttons[randRow][randCol].setText("x");
            }
        }
    }

    void calcNeiboLei() {
        int count;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                count = 0;
                if (counts[i][j] == LEICODE) continue;

                if (i > 0 && j > 0 && counts[i - 1][j - 1] == LEICODE) count++;

                if (i > 0 && counts[i - 1][j] == LEICODE) count++;

                if (i > 0 && j < 19 && counts[i - 1][j + 1] == LEICODE) count++;
                if (j > 0 && counts[i][j - 1] == LEICODE) count++;
                if (j < 19 && counts[i][j + 1] == LEICODE) count++;
                if (i < 19 && j > 0 && counts[i + 1][j - 1] == LEICODE) count++;
                if (i < 19 && counts[i + 1][j] == LEICODE) count++;
                if (i < 19 && j < 19 && counts[i + 1][j + 1] == LEICODE) count++;

                counts[i][j] = count;
                //buttons[i][j].setText(counts[i][j]+"");

            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) e.getSource();
        if (button.equals(reset)) {

        } else {
            int count = 0;
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < col; j++) {
                    if (button.equals(buttons[i][j])) {
                        count = counts[i][j];

                        if (count == LEICODE) {
                            loseGame();

                        } else {
                            openCeil(i, j);

                        }
                    }
                }
            }
        }
    }

    void openCeil(int i, int j) {
        //已经打开的格子，不再打开
        if (buttons[i][j].isEnabled() == false) return;

        buttons[i][j].setEnabled(false);

        if (counts[i][j] == 0) {
            if (i > 0 && j > 0 && counts[i - 1][j - 1] != LEICODE) openCeil(i - 1, j - 1);

            if (i > 0 && counts[i - 1][j] != LEICODE) openCeil(i - 1, j - 1);

            if (i > 0 && j < 19 && counts[i - 1][j + 1] != LEICODE) openCeil(i - 1, j + 1);
            if (j > 0 && counts[i][j - 1] != LEICODE) openCeil(i, j - 1);
            if (j < 19 && counts[i][j + 1] != LEICODE) openCeil(i, j + 1);
            if (i < 19 && j > 0 && counts[i + 1][j - 1] != LEICODE) openCeil(i + 1, j - 1);
            if (i < 19 && counts[i + 1][j] != LEICODE) openCeil(i + 1, j);
            if (i < 19 && j < 19 && counts[i + 1][j + 1] != LEICODE) openCeil(i + 1, j + 1);

        } else {
            buttons[i][j].setText(counts[i][j] + "");
        }
    }


    void loseGame() {

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                int count = counts[i][j];
                if(count == LEICODE){
                    buttons[i][j].setText("X");
                    buttons[i][j].setBackground(Color.red);
                    buttons[i][j].setEnabled(false);
                }else{
                    buttons[i][j].setText(count + "");
                    buttons[i][j].setEnabled(false);
                }

                }
            }
    }


    public static void main(String[] args)
    {

        SaoLei lei = new SaoLei();
    }
}


