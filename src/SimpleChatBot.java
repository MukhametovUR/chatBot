import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.regex.*;

class SimpleChatBot extends JFrame implements ActionListener {

    final String TITLE_OF_PROGRAM = "Chatter: simple chatbot";
    final int START_LOCATION = 200;
    final int WINDOW_WIDTH = 350;
    final int WINDOW_HEIGHT = 450;

    JTextArea dialogue; 					//area for dialog
    JCheckBox ai;						//enable/disable AI
    JTextField message; 			//field for entering message
    SimpleBot sbot;						//chat bot class (int bot package)
    SimpleAttributeSet botStyle; 	//style bot text

    public static void main(String[] args){
        new SimpleChatBot();
    }
    SimpleChatBot(){
        setTitle(TITLE_OF_PROGRAM);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(START_LOCATION, START_LOCATION, WINDOW_WIDTH, WINDOW_WIDTH);
        //area for dialog
        dialogue = new JTextArea();
        dialogue.setLineWrap(true);//строки будут переносится
        JScrollPane scrollBar = new JScrollPane(dialogue);//Прокрутка переписки
        add(BorderLayout.CENTER, scrollBar);//добавление объекта в центальную часть srollBar c dialogue
        //panel for checkbox, message field and button
        JPanel bp = new JPanel();
        bp.setLayout(new BoxLayout(bp, BoxLayout.X_AXIS));// Размещение по горизонтали
        ai = new JCheckBox("AI");
        ai.doClick();
        message = new JTextField();
        message.addActionListener(this);        //Пепехватывает ввод через клпввишу Enter
        JButton enter = new JButton("Enter");
        enter.addActionListener(this);          //Запускае прослушиватель на кнопке enter
        add(BorderLayout.CENTER, scrollBar);
        bp.add(ai);                                //На панели создаются кнопки
        bp.add(message);//На панели создаются кнопки
        bp.add(enter);//На панели создаются кнопки
        add(BorderLayout.CENTER,scrollBar);
        add(BorderLayout.SOUTH, bp);//
        setVisible(true);
        sbot = new SimpleBot();
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) { //Переопределение метода, возникат когда нажимают на кнопку
        if (message.getText().trim().length() > 0 ) {       //убираем пробелы и замеряем длину
            dialogue.append(message.getText() + "\n");      //Добавление в dialog из компонента message
            dialogue.append(TITLE_OF_PROGRAM.substring(0,9) +
                    sbot.sayInReturn(message.getText(), ai.isSelected()) + "\n");//Метод сказать обратно у бота
        }
        message.setText("");
        message.requestFocusInWindow();
    }
}