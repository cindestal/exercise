import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SaoLei implements ActionListener {

    JFrame frame = new JFrame("扫雷游戏");
    JButton reset = new JButton("重新开始");
    Container container = new Container();

    //任务三：数据结构
    final int row = 20;
    final int col = 20;
    final int leiCount = 30;
    JButton[][] buttons = new JButton[row][col];
    int[][] count = new int[row][col];
    final int LEICODE = 10;

    //构造函数
    public SaoLei(){

        //任务一：显示框
        frame.setSize(600,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        //任务二：显示重新按钮
        addResetButton();

        //任务四：add buttons
        addButtons();

        frame.setVisible(true);


    }

    //任务二：显示重新按钮
    void addResetButton(){
        reset.setBackground(Color.green);
        reset.setOpaque(true);

        frame.add(reset,BorderLayout.NORTH);
    }

    //任务四：add buttons
    void addButtons(){
        frame.add(container,BorderLayout.CENTER);
        container.setLayout(new GridLayout(row,col));
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                JButton button = new JButton();
                button.setBackground(Color.yellow);
                button.setOpaque(true);
                button.addActionListener(this);
                buttons[i][j] = button;
                container.add(button);
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton)e.getSource();
        if(button.equals(reset)) {
        }
        else{
            button.setText("0");
            button.setEnabled(false);
        }

    }


    public static void main(String[] args)
    {

        SaoLei lei = new SaoLei();
    }
}


