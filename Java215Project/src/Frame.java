import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

class Frame extends JFrame {
    ImageIcon imageIcon = new ImageIcon("chat.png"); //icon from the frame and for our chat application
    ImageIcon imageIcon1 = new ImageIcon("back.png"); //back button icon
    Image image = imageIcon.getImage();

    Cursor cur = new Cursor(Cursor.HAND_CURSOR); //changes the cursor to hand when hovering over a button

    JPanel panel = new JPanel(); //this is the panel with blue background
    JPanel panel1 = new JPanel(); //this is the panel where all the works are shown and done

    JButton b1 = new JButton("SIGN UP"); //button in the 1st page
    JButton b2 = new JButton("LOGIN"); //button in the 1st page
    JButton b3 = new JButton(imageIcon1); //this back button is added in panel
    /*b4 button will appear in the signup page.
    upon clicking b4, it'll open a specific folder in out program folder where users will be able to select avatars from their profile*/
    JButton b4 = new JButton("click here");
    JButton b5 = new JButton("CREATE ACCOUNT"); //button in signup page
    JButton b6 = new JButton("LOGIN"); //button in login page
    JButton b7 = new JButton("Okay"); //this button will appear when user makes mistakes with the error message. upon clicking, it'll relaunch the frame
    JButton b8 = new JButton("Continue"); //this will appear when user had created an account or logged in succesfully
    /*since I've created a method for the login, it could be called after account creation and also in the first page.
    but the button only appears in signup and login page. so I had to create a 2nd button that does the same thing but at different times, to stop back button from
    appearing elsewhere.*/
    JButton b9 = new JButton(imageIcon1);

    JLabel l1 = new JLabel(imageIcon); //this appears at the top of panel1 in launch, signup, login pages
    JLabel l2 = new JLabel("ChatOn", JLabel.CENTER); //the name of our software. name it whatever you want
    JLabel l3 = new JLabel("SIGN UP", JLabel.CENTER);
    JLabel l4 = new JLabel("Phone", JLabel.LEFT);
    JLabel l5 = new JLabel("Password", JLabel.LEFT);
    JLabel l6 = new JLabel("Username", JLabel.LEFT);
    JLabel l7 = new JLabel("Avatar", JLabel.CENTER);
    JLabel l8 = new JLabel("<html>ACCOUNT<br>ALREADY EXISTS!", JLabel.CENTER);
    JLabel l9 = new JLabel("<html>ACCOUNT<br>CREATED!", JLabel.CENTER);
    JLabel l10 = new JLabel("<html>INVALID<br>MOBILE NUMBER!", JLabel.CENTER);
    JLabel l11 = new JLabel("LOGIN", JLabel.CENTER);
    JLabel l12 = new JLabel("<html>LOGIN<br>SUCCESSFUL!", JLabel.CENTER);
    JLabel l13 = new JLabel("<html>USER<br>NOT FOUND!", JLabel.CENTER);
    JLabel l14 = new JLabel("<html>WRONG<br>PASSWORD!", JLabel.CENTER);
    JLabel l15 = new JLabel("<html>PASSWORD HAS<br>TO BE AT LEAST<br>4 CHARACTERS!", JLabel.CENTER);
    JLabel l16 = new JLabel("<html>USERNAME TAKEN.<br>SELECT ANOTHER!", JLabel.CENTER);
    JLabel l17 = new JLabel("", JLabel.CENTER);

    JTextField mobileNumber = new JTextField(); //takes mobile number
    JTextField username = new JTextField(); //takes the username

    JPasswordField password = new JPasswordField(); //takes the password

    String avatar; //this has been used to store in the name of the image that the user selects as their profile picture

    Frame() {
        setIconImage(image);
        setTitle("ChatOn");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(600, 100, 600, 800);
        setResizable(false);
        setVisible(true);

        panel.setBackground(new Color(51, 153, 255));
        panel.setLayout(null);

        panel1.setBounds(100, 75, 400, 600);
        panel1.setLayout(null);
        panel1.setBackground(Color.white);

        b1.setFont(new Font("Arial", Font.BOLD, 25));
        b2.setFont(new Font("Arial", Font.BOLD, 25));
        b4.setFont(new Font("Arial", Font.BOLD, 20));
        b5.setFont(new Font("Arial", Font.BOLD, 20));
        b6.setFont(new Font("Arial", Font.BOLD, 20));
        b7.setFont(new Font("Arial", Font.BOLD, 20));
        b8.setFont(new Font("Arial", Font.BOLD, 20));

        b1.setOpaque(true);
        b2.setOpaque(true);
        b3.setOpaque(true);
        b4.setOpaque(true);
        b5.setOpaque(true);
        b6.setOpaque(true);
        b7.setOpaque(true);
        b8.setOpaque(true);

        b1.setBackground(new Color(51, 153, 255));
        b2.setBackground(new Color(51, 153, 255));
        b3.setBackground(new Color(51, 153, 255));
        b4.setBackground(Color.black); //button to select an image for profile picture
        b5.setBackground(new Color(51, 153, 255));
        b6.setBackground(new Color(51, 153, 255));
        b7.setBackground(new Color(51, 153, 255));
        b8.setBackground(new Color(51, 153, 255));
        b9.setBackground(new Color(51, 153, 255));

        b1.setForeground(new Color(51, 153, 255));
        b2.setForeground(new Color(51, 153, 255));
        b4.setForeground(new Color(51, 153, 255));
        b5.setForeground(new Color(51, 153, 255));
        b6.setForeground(new Color(51, 153, 255));
        b7.setForeground(new Color(51, 153, 255));
        b8.setForeground(new Color(51, 153, 255));

        b1.setBounds(100, 310, 200, 50);
        b2.setBounds(100, 400, 200, 50);
        b3.setBounds(30, 30, 50, 50);
        b4.setBounds(125, 445, 150, 30);
        b5.setBounds(90, 520, 220, 40);
        b6.setBounds(90, 520, 220, 40);
        b7.setBounds(80, 520, 240, 40);
        b8.setBounds(80, 520, 240, 40);
        b9.setBounds(30, 30, 50, 50);

        b1.setCursor(cur);
        b2.setCursor(cur);
        b3.setCursor(cur);
        b4.setCursor(cur);
        b5.setCursor(cur);
        b6.setCursor(cur);
        b7.setCursor(cur);
        b8.setCursor(cur);
        b9.setCursor(cur);

        b3.setBorderPainted(false);
        b9.setBorderPainted(false);

        b1.setVisible(false);
        b2.setVisible(false);
        b3.setVisible(false);
        b4.setVisible(false);
        b5.setVisible(false);
        b6.setVisible(false);
        b7.setVisible(false);
        b8.setVisible(false);
        b9.setVisible(false);

        l2.setFont(new Font("Ariel", Font.BOLD, 40));
        l3.setFont(new Font("Ariel", Font.BOLD, 40));
        l4.setFont(new Font("Ariel", Font.BOLD, 20));
        l5.setFont(new Font("Ariel", Font.BOLD, 20));
        l6.setFont(new Font("Ariel", Font.BOLD, 20));
        l7.setFont(new Font("Ariel", Font.BOLD, 20));
        l8.setFont(new Font("Ariel", Font.BOLD, 25));
        l9.setFont(new Font("Ariel", Font.BOLD, 25));
        l10.setFont(new Font("Ariel", Font.BOLD, 25));
        l11.setFont(new Font("Ariel", Font.BOLD, 40));
        l12.setFont(new Font("Ariel", Font.BOLD, 25));
        l13.setFont(new Font("Ariel", Font.BOLD, 25));
        l14.setFont(new Font("Ariel", Font.BOLD, 25));
        l15.setFont(new Font("Ariel", Font.BOLD, 25));
        l16.setFont(new Font("Ariel", Font.BOLD, 25));
        l17.setFont(new Font("Ariel", Font.BOLD, 25));

        l2.setForeground(new Color(51, 153, 255));
        l3.setForeground(new Color(51, 153, 255));
        l8.setForeground(Color.black);
        l9.setForeground(new Color(51, 153, 255));
        l10.setForeground(Color.red);
        l11.setForeground(new Color(51, 153, 255));
        l12.setForeground(new Color(51, 153, 255));
        l13.setForeground(Color.red);
        l14.setForeground(Color.red);
        l15.setForeground(Color.red);
        l16.setForeground(Color.red);
        l17.setForeground(Color.red);

        l1.setBounds(150, 20, 100, 100);
        l2.setBounds(50, 140, 300, 50);
        l3.setBounds(100, 140, 200, 50);
        l4.setBounds(75, 205, 250, 30);
        l5.setBounds(75, 275, 250, 30);
        l6.setBounds(75, 345, 250, 30);
        l7.setBounds(165, 415, 70, 30);
        l8.setBounds(80, 285, 240, 70);
        l9.setBounds(80, 285, 240, 70);
        l10.setBounds(80, 285, 240, 70);
        l11.setBounds(100, 140, 200, 50);
        l12.setBounds(80, 285, 240, 70);
        l13.setBounds(80, 285, 240, 70);
        l14.setBounds(80, 285, 240, 70);
        l15.setBounds(80, 265, 240, 110);
        l16.setBounds(70, 265, 260, 80);
        l17.setBounds(80, 285, 240, 70);

        l8.setBorder(BorderFactory.createLineBorder(Color.black, 3));
        l9.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 3));
        l10.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        l12.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 3));
        l13.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        l14.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        l15.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        l16.setBorder(BorderFactory.createLineBorder(Color.red, 3));
        l17.setBorder(BorderFactory.createLineBorder(Color.red, 3));

        l1.setVisible(false);
        l2.setVisible(false);
        l3.setVisible(false);
        l4.setVisible(false);
        l5.setVisible(false);
        l6.setVisible(false);
        l7.setVisible(false);
        l8.setVisible(false);
        l9.setVisible(false);
        l10.setVisible(false);
        l11.setVisible(false);
        l12.setVisible(false);
        l13.setVisible(false);
        l14.setVisible(false);
        l15.setVisible(false);
        l16.setVisible(false);
        l17.setVisible(false);

        mobileNumber.setFont(new Font("Ariel", Font.BOLD, 20));
        username.setFont(new Font("Ariel", Font.BOLD, 20));

        mobileNumber.setBounds(75, 235, 250, 30);
        username.setBounds(75, 375, 250, 30);

        mobileNumber.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 3));
        username.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 3));

        mobileNumber.setVisible(false);
        username.setVisible(false);

        password.setFont(new Font("Ariel", Font.BOLD, 20));
        password.setBounds(75, 305, 250, 30);
        password.setBorder(BorderFactory.createLineBorder(new Color(51, 153, 255), 3));
        password.setVisible(false);

        panel1.add(b1);
        panel1.add(b2);
        panel1.add(b4);
        panel1.add(b5);
        panel1.add(b6);
        panel1.add(b7);
        panel1.add(b8);
        panel1.add(l1);
        panel1.add(l2);
        panel1.add(l3);
        panel1.add(l4);
        panel1.add(l5);
        panel1.add(l6);
        panel1.add(l7);
        panel1.add(l8);
        panel1.add(l9);
        panel1.add(l10);
        panel1.add(l11);
        panel1.add(l12);
        panel1.add(l13);
        panel1.add(l14);
        panel1.add(l15);
        panel1.add(l16);
        panel1.add(l17);
        panel1.add(mobileNumber);
        panel1.add(password);
        panel1.add(username);

        panel.add(b3);
        panel.add(b9);
        panel.add(panel1);

        add(panel);

        l1.setVisible(true);
        l2.setVisible(true);
        b1.setVisible(true);
        b2.setVisible(true);

        //b1 is sigup button
        b1.addActionListener(new ActionListener() {
            //upon clicking b1, the signup page appears
            @Override
            public void actionPerformed(ActionEvent e) {
                l2.setVisible(false);
                b1.setVisible(false);
                b2.setVisible(false);
                b3.setVisible(true);
                //b3 is back button
                b3.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        dispose();
                        new Frame();
                    }
                });
                l3.setVisible(true);
                l4.setVisible(true);
                mobileNumber.setVisible(true);
                l5.setVisible(true);
                password.setVisible(true);
                l6.setVisible(true);
                username.setVisible(true);
                l7.setVisible(true);
                b4.setVisible(true);
                //b4 is select image button
                b4.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFileChooser fc = new JFileChooser();
                        //please change the directory to wherever you have this images folder. otherwise, there will be an error
                        fc.setCurrentDirectory(new File("/Users/ridwainislam/Desktop/PROJECTS /JavaProject/Java215Project"));
                        int response = fc.showOpenDialog(null);
                        File file = fc.getSelectedFile();
                        if(response == JFileChooser.APPROVE_OPTION) {
                            avatar = file.getName();
                        }else if(response == JFileChooser.CANCEL_OPTION) {
                            avatar = "default.png"; //in case any error occurs, a user will have the default image as their profile picture
                        }else if(response == JFileChooser.ERROR_OPTION) {
                            avatar = "default.png";
                        } else if(file == null) {
                            avatar = "default.png";
                        }
                    }
                });
                b5.setVisible(true);
                //after clicking b5, the following program will check all the data if they meet requirements for creating an account.
                b5.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        b3.setVisible(false);
                        l4.setVisible(false);
                        mobileNumber.setVisible(false);
                        l5.setVisible(false);
                        password.setVisible(false);
                        l6.setVisible(false);
                        username.setVisible(false);
                        l7.setVisible(false);
                        b4.setVisible(false);
                        b5.setVisible(false);
                        try {
                            //if an account doesn't exist, it'll jump to catch block. otherwise it'll show the error message and relaunch the frame.
                            ObjectInputStream search = new ObjectInputStream(Files.newInputStream(Paths.get(mobileNumber.getText() + ".dat")));
                            search.close();
                            l8.setVisible(true);
                            b7.setVisible(true);
                            b7.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    dispose();
                                    new Frame();
                                }
                            });
                        } catch (IOException ex1) {
                            try {
                                int trigger = 0; //only when trigger is 0, it'll create an account
                                //if mobile number is not of length 11, it'll set trigger to 1
                                if(mobileNumber.getText().length() == 11) {
                                    for(int i = 0; i < mobileNumber.getText().length(); ++i) {
                                        if(!Character.isDigit(mobileNumber.getText().charAt(i))) {
                                            //if it catches a non digit in the mobile number, it'll set trigger to 1
                                            trigger = 1;
                                            break;
                                        }
                                    }
                                    if(trigger == 0) {
                                        //a password has to be at elast 4 characters
                                        if(new String(password.getPassword()).length() < 4) {
                                            l15.setVisible(true);
                                            b7.setVisible(true);
                                            b7.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent e) {
                                                    dispose();
                                                    new Frame();
                                                }
                                            });
                                        }else {
                                            BufferedReader read = new BufferedReader(new FileReader("usernames.txt"));
                                            String name; //this is for temporary use
                                            //username can't be empty
                                            if(username.getText().equals("")) {
                                                trigger = 3;
                                            }
                                            //it will check if the username matches with any in list of existing usernames
                                            while((name = read.readLine()) != null) {
                                                if(username.getText().equals(name)) {
                                                    read.close();
                                                    trigger = 2;
                                                    break;
                                                }
                                            }
                                            read.close();
                                            //a username can't contain spaces
                                            for(int i = 0; i < username.getText().length(); ++i) {
                                                if(Character.isSpaceChar(username.getText().charAt(i))) {
                                                    trigger = 4;
                                                    break;
                                                }
                                            }
                                            /*if trigger is still 0, it creates and account, saves the username to the text file of usernames
                                            the program will create a .dat file for each user where it'll save the name, password, username, avatar name as an object.
                                            later we can take the username and avatar name to use in the chat window*/
                                            if(trigger == 0) {
                                                read.close();
                                                BufferedWriter save = new BufferedWriter(new FileWriter("usernames.txt", true));
                                                save.write(username.getText() + "\n");
                                                save.close();
                                                ObjectOutputStream user = new ObjectOutputStream(Files.newOutputStream(Paths.get(mobileNumber.getText() + ".dat")));
                                                User temp = new User(mobileNumber.getText(), new String(password.getPassword()), username.getText(), avatar);
                                                user.writeObject(temp);
                                                user.close();
                                                l9.setVisible(true);
                                                b8.setVisible(true);
                                                //after creating an account, it'll take you to login page
                                                b8.addActionListener(new ActionListener() {
                                                    @Override
                                                    public void actionPerformed(ActionEvent e) {
                                                        login();
                                                    }
                                                });
                                            }

                                        }
                                    }
                                }else trigger = 1;
                                if(trigger == 1){
                                    //if phone number is invalid
                                    l10.setVisible(true);
                                    b7.setVisible(true);
                                    b7.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dispose();
                                            new Frame();
                                        }
                                    });
                                }
                                if(trigger == 2) {
                                    //if username is taken
                                    l16.setVisible(true);
                                    b7.setVisible(true);
                                    b7.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dispose();
                                            new Frame();
                                        }
                                    });
                                }
                                if(trigger == 3) {
                                    //if username is empty
                                    l17.setText("<html>PLEASE ENTER<br>A USERNAME!");
                                    l17.setVisible(true);
                                    b7.setVisible(true);
                                    b7.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dispose();
                                            new Frame();
                                        }
                                    });
                                }
                                if(trigger == 4) {
                                    //if username contains spaces
                                    l17.setText("<html>USERNAME CAN'T<br>CONTAIN SPACES!");
                                    l17.setVisible(true);
                                    b7.setVisible(true);
                                    b7.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dispose();
                                            new Frame();
                                        }
                                    });
                                }
                            } catch (IOException ignored) {

                            }
                        }
                    }
                });
            }
        });

        //b2 is login button
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });
    }
    void login() {
        b3.setVisible(false);
        b9.setVisible(true);
        b8.setVisible(false);
        l3.setVisible(false);
        l2.setVisible(false);
        b1.setVisible(false);
        b2.setVisible(false);
        l9.setVisible(false);
        mobileNumber.setText(""); //since we are using only one of this variable over the program, it had to be set "" in the login page as to erase if it had something
        password.setText("");
        b9.setVisible(true);
        //b9 is the back button from the login page
        b9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new Frame();
            }
        });
        l11.setVisible(true);
        l4.setLocation(75, 250);
        l4.setVisible(true);
        mobileNumber.setLocation(75, 280);
        mobileNumber.setVisible(true);
        l5.setLocation(75, 320);
        l5.setVisible(true);
        password.setLocation(75, 350);
        password.setVisible(true);
        b6.setVisible(true);
        //after clicking b6, it'll check if the number is valid, if the user exists or if the password is wrong
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                b9.setVisible(false);
                l4.setVisible(false);
                mobileNumber.setVisible(false);
                l5.setVisible(false);
                password.setVisible(false);
                b6.setVisible(false);
                try {
                    int trigger = 0;
                    if(mobileNumber.getText().length() == 11) {
                        for(int i = 0; i < mobileNumber.getText().length(); ++i) {
                            if(!Character.isDigit(mobileNumber.getText().charAt(i))) {
                                trigger = 1;
                                break;
                            }
                        }
                        if(trigger == 0) {
                            if(new String(password.getPassword()).length() < 4) {
                                l14.setVisible(true);
                                b7.setVisible(true);
                                b7.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        dispose();
                                        new Frame();
                                    }
                                });
                            }else {
                                //finds the user, checks their .dat file and extracts the password to match with given info
                                ObjectInputStream read = new ObjectInputStream(Files.newInputStream(Paths.get(mobileNumber.getText() + ".dat")));
                                User temp = (User) read.readObject();
                                if(temp.getPassword().equals(new String(password.getPassword()))) {
                                    read.close();
                                    l12.setVisible(true);
                                    b8.setVisible(true);
                                    b8.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent e) {
                                            dispose();
                                            //THIS IS THE PLACE WHERE IT WILL LAUNCH THE CHAT WINDOW USING THE USERNAME AND AVATAR NAME FROM "temp"
                                            Socket socket =null;
                                            try {
                                                socket= new Socket("localhost", 2004);
                                            } catch (IOException ex) {
                                                throw new RuntimeException(ex);
                                            }
                                            String userName = temp.getUsername();
                                            Clients one = new Clients(socket,userName);
                                            Thread t1 = new Thread(one);
                                            t1.start();
                                        }
                                    });
                                }
                                else trigger = 2;
                            }
                        }
                    }else trigger = 1;
                    if(trigger == 1) {
                        //if number is invalid
                        l10.setVisible(true);
                        b7.setVisible(true);
                        b7.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispose();
                                new Frame();
                            }
                        });
                    }else if(trigger == 2) {
                        //if password is wrong
                        l14.setVisible(true);
                        b7.setVisible(true);
                        b7.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                dispose();
                                new Frame();
                            }
                        });
                    }
                }catch (IOException ex3) {
                    //program fails to find the .dat file for the given number, it'll come here due to the error. thus, it will show user not found
                    l13.setVisible(true);
                    b7.setVisible(true);
                    b7.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            dispose();
                            new Frame();
                        }
                    });
                } catch (ClassNotFoundException ex) {

                }
            }
        });
    }
}

class User implements Serializable {
    private String phone;
    private String password;
    private String username;
    private String avatar;
    User(String phone, String password, String username, String avatar) {
        this.phone = phone;
        this.password = password;
        this.username = username;
        this.avatar = avatar;
    }
    String getUsername(){
        return username;
    }
    String getPassword() {
        return password;
    }
    String getAvatar() {
        return avatar;
    }
}