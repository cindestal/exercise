import javax.swing.*;

public class SaoLei {

    JFrame frame = new JFrame("扫雷游戏");

    //构造函数
    public SaoLei(){
        frame.setSize(600,700);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }

    public static void main(String[] args)
    {

        SaoLei lei = new SaoLei();
    }
}


