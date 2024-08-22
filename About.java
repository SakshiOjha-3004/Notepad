/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package notepad;

/**
 *
 * @author osaks
 */
import javax.swing.*;
import java.awt.*;//for image
import java.awt.event.*;
public class About extends JFrame implements ActionListener{
    About()
    {
        setBounds(400,100,600,500);
        setLayout(null);
        getContentPane().setBackground(Color.white);
        
        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/windows.png"));
        Image i2=i1.getImage().getScaledInstance(300, 60, Image.SCALE_DEFAULT);
        ImageIcon i3=new ImageIcon(i2);//Image class is AWT and JLabel is swing so we cannot directly pass awt to swing
        JLabel headericon=new JLabel(i3);
        headericon.setBounds(150, 50, 300, 60);//works only when setLayout is null
        add(headericon);
        
        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad"));
        Image i5=i4.getImage().getScaledInstance(70, 70, Image.SCALE_DEFAULT);
        ImageIcon i6=new ImageIcon(i5);//Image class is AWT and JLabel is swing so we cannot directly pass awt to swing
        JLabel icon=new JLabel(i6);
        icon.setBounds(50, 180, 70, 70);//works only when setLayout is null
        add(icon);
        
        JLabel text=new JLabel("<html>WINDOWS<br>Version 0.1.0 (OS BUILD JAVA)<br>WINDOWS. All rights reserved</html>");//to write on label
        text.setBounds(150,100,500,200);
        text.setFont(new Font("SansSerif",Font.PLAIN,16));
        add(text);
        JButton b1 = new JButton("OK");
        b1.setBounds(150, 350, 120, 25); // Set position (x, y) and size (width, height)
        b1.addActionListener(this);
        add(b1); // Add the button to the container
        setVisible(true);//to build frame
    }
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        this.setVisible(false);
    }
    public static void main(String []args)
    {
        new About();
    }
}
