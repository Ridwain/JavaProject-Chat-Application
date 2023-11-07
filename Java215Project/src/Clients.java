import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;

public class Clients extends JFrame implements ActionListener ,KeyListener,Runnable{
    //static Frame loginFrame;
    JTextField messageField;
    JTextArea chatTextArea;
    JScrollPane scrollBar;
    BufferedReader reader;
    BufferedWriter writer;
    Socket socket;
    static String name;
    public String getName(){
        return this.name;
    }
    Clients(Socket socket,String userName){
        this.setSize(700,700);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocation(400,300);
        this.setTitle("Messenger");
        this.setLayout(new BorderLayout());
        this.setResizable(false);
        name = userName;

        ImageIcon defaultDp = new ImageIcon("man1.png");  //  Default Display picture
        Image dfDP = defaultDp.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon defaultPic = new ImageIcon(dfDP);

        ImageIcon groupChatPic = new ImageIcon("icons8-facebook_messenger.png");  // GroupChat Pic (Messenger Type)
        Image groupChatResize = groupChatPic.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        ImageIcon groupChatImage = new ImageIcon(groupChatResize);

        ImageIcon dots = new ImageIcon("dots.png"); // Three Dots Pic
        Image dotsResize = dots.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i18 = new ImageIcon(dotsResize);

        ImageIcon vdo = new ImageIcon("videoLogo.png"); //Video call Logo pic
        Image vdoReset = vdo.getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT);
        ImageIcon i16 = new ImageIcon(vdoReset);

        ImageIcon call = new ImageIcon("call.png");  // Call logo Pic
        Image callResize = call.getImage().getScaledInstance(40, 35, Image.SCALE_DEFAULT);
        ImageIcon i17 = new ImageIcon(callResize);

        ImageIcon send = new ImageIcon("sends.png");  // Sends Logo Pic


        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();
        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();

        messageField = new JTextField();   // Message field Where We Write message
        messageField.setForeground(Color.BLACK);
        chatTextArea = new JTextArea();   // chatTextArea Where We Will See our sending and recieving msges
        chatTextArea.setEditable(false);  // Not editable

        JButton button = new JButton();
        button.setIcon(send);
        button.addActionListener(this);  // I have Added Actionlistener to the button (Mouse Clicked)
        messageField.addKeyListener(this); // I Have Added KeyListener To the Button (KeyBoard Listener)

        scrollBar = new JScrollPane(chatTextArea);  // Added a Scroll Bar to the chatTextArea
        scrollBar.setBounds(0,70,400,540);  // set the scroll size and position



        panel1.setBounds(0,0,700,700);
        panel1.setBackground(Color.WHITE);
        panel1.setLayout(null);
        panel6.setBounds(0,0,300,70);
        panel6.setBackground(new Color(51, 153, 255));
        JLabel chats = new JLabel("Messenger");
        chats.setBounds(0,0,65,60);
        chats.setForeground(Color.BLACK);
        chats.setFont(new Font("SAN_SERIF", Font.BOLD, 40));
        panel6.add(chats);

        panel7.setBounds(0,70,300,60);
        panel7.setBackground(new Color(224,224,224));
        panel7.setLayout(null);
        JLabel groupChat = new JLabel(groupChatImage);
        groupChat.setBounds(0,0,60,50);
        panel7.add(groupChat);

        JLabel chatName = new JLabel("GROUP CHAT");
        chatName.setBounds(70,0,120,40);
        chatName.setFont(new Font("SAN_SERIF", Font.BOLD, 15));
        panel7.add(chatName);

        JLabel chatNameStatus = new JLabel("Batman,SuperMan,SpiderMan,IronMan");
        chatNameStatus.setBounds(70,30,160,13);
        chatNameStatus.setFont(new Font("SAN_SERIF", Font.PLAIN, 12));
        panel7.add(chatNameStatus);

        panel1.add(panel6);
        panel1.add(panel7);


        panel2.setBounds(300,0,700,700);
        panel2.setBackground(Color.WHITE);
        panel2.setLayout(null);

        panel3.setBounds(0,0,400,70);
        panel3.setBackground(Color.WHITE);
        JLabel dp = new JLabel(defaultPic); // here I Added The Default pic of profile

        // Here I Added The function for Choosing display Picture
        dp.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent ae) {
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setCurrentDirectory(new File(".")); //sets current directory

                int response = fileChooser.showOpenDialog(null); //select file to open

                if(response == JFileChooser.APPROVE_OPTION) {
                    File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
                    ImageIcon displayImage = new ImageIcon(fileChooser.getSelectedFile().getAbsolutePath());

                    Image displayImage2 = displayImage.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
                    ImageIcon i15 = new ImageIcon(displayImage2);
                    dp.setIcon(i15);
                }
            }

        });
        dp.setBounds(0,0,70,70);

        JLabel name = new JLabel(getName()); // Here I got the name of the person to print on the label
        name.setBounds(70,0,80,50);
//        name.setText(name);
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));

        JLabel status = new JLabel();
        status.setText("Active Now");
        status.setBounds(70,40,130,15);
        status.setFont(new Font("SAN_SERIF", Font.PLAIN, 13));

        JLabel dot = new JLabel(i18);
        dot.setBounds(350,5,60,60);

        JLabel videoIcon = new JLabel(i16);
        videoIcon.setBounds(300,5,60,60);

        JLabel callIcon = new JLabel(i17);
        callIcon.setBounds(250,5,60,60);


        panel3.add(dp);
        panel3.add(name);
        panel3.add(status);
        panel3.add(dot);
        panel3.add(videoIcon);
        panel3.add(callIcon);
        panel3.setLayout(null);



        panel4.setBounds(0,620,400,50);
        panel4.setBackground(new Color(51, 153, 255));
        panel4.setLayout(new BorderLayout());
        panel4.add(button,BorderLayout.EAST);
        panel4.add(messageField,BorderLayout.CENTER);

        panel5.setBounds(0,70,400,550);
        panel5.setBackground(Color.WHITE);
        panel5.setLayout(new BorderLayout());
        panel5.add(scrollBar,BorderLayout.CENTER);  // Panel5 used for ChatArea,You have to Work with this Panel

        panel1.add(panel2);
        panel2.add(panel3);
        panel2.add(panel4);
        panel2.add(panel5);

        this.add(panel1);
        this.setVisible(true);
        try {
            this.socket = socket;
            writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Do your code Here in actionPerformed
    @Override
    public void actionPerformed(ActionEvent e) {
        String message =name + " : "+ messageField.getText();
        chatTextArea.append( message + "\n");
        messageField.setText("");
        try {
            writer.write(message);
            writer.write("\r\n");
            writer.flush();
        } catch (Exception ea) {
            ea.printStackTrace();
        }
    }




    @Override
    public void keyTyped(KeyEvent e) {

    }

    //  Do Your Same code her Also inside the if
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_ENTER) {
            String message1 = name + " : "+messageField.getText();
            chatTextArea.append(message1 + "\n");
            try {
                writer.write(message1);
                writer.write("\r\n");
                writer.flush();
            } catch (Exception es) {
                es.printStackTrace();
            }
            messageField.setText("");

        }
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    // do your Code For Run Also
    @Override
    public void run() {
        while (socket.isConnected()) {
            try {
                String msg = reader.readLine();
                System.out.println(name);
                chatTextArea.append(msg + "\n");
                messageField.setText("");
            } catch (Exception et) {
                et.printStackTrace();
            }
        }
    }

    // If The Port Is in use in your laptop then try different port which will be same on both server and client class
//    public static void main(String[] args) throws IOException{
//
//
////        Scanner sc = new Scanner(System.in);
////        name = sc.next(); // I first took a Name from the Client Terminal By User Input
//        Socket socket = new Socket("localhost", 2000);
//        Clients one = new Clients(socket);
//        Thread t1 = new Thread(one);
//        t1.start();
//    }




}

