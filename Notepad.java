/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package notepad;

/**
 *
 * @author osaks
 */
import java.io.*;//for bufferreader
import javax.swing.*;//for swing
import java.awt.*;//for Image class
import java.awt.event.*;//for importing event package for mnemonics and eventlisteners
import javax.swing.filechooser.FileNameExtensionFilter;//if you want to use the FileNameExtensionFilter class, you'll need to import it separately because it is located in the javax.swing.filechooser package, not in javax.swing.
public class Notepad extends JFrame implements ActionListener{
      
    JTextArea area;//so it can be accessed globally
    String text;//to store selected text for copy
    Notepad()
    {
        setTitle("Notepad");//set title
        ImageIcon image=new ImageIcon(ClassLoader.getSystemResource("notepad/icons/notepad"));
        Image i=image.getImage();
        setIconImage(i);
        
        /*MENU BAR*/
        JMenuBar menubar=new JMenuBar();
        menubar.setBackground(Color.white);//to set the bg color of menubar
        //menu options:
        JMenu file=new JMenu("File");
        //changing its fonts
        file.setFont(new Font("Arial", Font.PLAIN, 14));//font family,font type,font size
        //adding items to file
        JMenuItem newdoc=new JMenuItem("New");
        //adding action
        newdoc.addActionListener(this);//ineternally calls actionPerformed method
        //adding mnemonics
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,ActionEvent.CTRL_MASK));
        //multiple more:
        JMenuItem open=new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,ActionEvent.CTRL_MASK));
        
        JMenuItem save=new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,ActionEvent.CTRL_MASK));
        
        JMenuItem Print=new JMenuItem("Print");
        Print.addActionListener(this);
        Print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,ActionEvent.CTRL_MASK));
        
        JMenuItem exit=new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE,ActionEvent.CTRL_MASK));
        //end of file menuitems
        
        //adding them to file
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(Print);
        file.add(exit);//end
        
        menubar.add(file);//to add file to menubar
        //edit
        JMenu edit=new JMenu("Edit");
        edit.setFont(new Font("Arial", Font.PLAIN, 14));//font family,font type,font size
        JMenuItem copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C,ActionEvent.CTRL_MASK));
        
        JMenuItem paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V,ActionEvent.CTRL_MASK));
        
        JMenuItem cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,ActionEvent.CTRL_MASK));
        
        JMenuItem selectAll=new JMenuItem("Select All");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A,ActionEvent.CTRL_MASK));
        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);
        menubar.add(edit);
        
        //help
        JMenu helpmenu=new JMenu("About");
        helpmenu.setFont(new Font("Arial", Font.PLAIN, 14));//font family,font type,font size
        JMenuItem help=new JMenuItem("About");
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,ActionEvent.CTRL_MASK));
        help.addActionListener(this);
        helpmenu.add(help);
        menubar.add(helpmenu);//end
        
        //adding menubar to the screen
        setJMenuBar(menubar);
        //for typing-add textareas
        area=new JTextArea();
        area.setFont(new Font("SansSerif",Font.PLAIN,10));
        area.setLineWrap(true);//to move to next line when the line is over
        area.setWrapStyleWord(true);//the upper line breaks the word into two when end is reached. this shifts the entire word below 
        
        
        //for scrollbar
        JScrollPane pane =new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());//remove border
        add(pane);//add(area);->text area is added within scrollpannel
        
        setExtendedState(JFrame.MAXIMIZED_BOTH);//By Deafult Size: full screen
        setVisible(true);
    }
    //we will be overriding only one abstract method from Action listener:
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        if(ae.getActionCommand().equals("New"))
            area.setText("");//will replace text written in text area with empty string
        else if(ae.getActionCommand().equals("Open"))
        {
            JFileChooser chooser = new JFileChooser(); // to open the selection window
            chooser.setAcceptAllFileFilterUsed(false);//Disables the "All Files" option, so only the files matching the filters you add are shown.
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files "/*this is message*/, "txt");//Creates a filter that allows only .txt files. The first argument is a description that will appear in the file chooser, and the second argument is the file extension you want to filter by.
            chooser.setFileFilter(restrict); // Apply the filter
            
            int action=chooser.showOpenDialog(this);//open the dialog & 'this' refers to the current JFrame or component
            if(action!=JFileChooser.APPROVE_OPTION)
                return;// Check if the user did not select a file then return
            //if not returned then that means the user chose a file.lets open it:
            File selectedfile = chooser.getSelectedFile();
            try
            {
                BufferedReader reader=new BufferedReader(new FileReader(selectedfile));//using bufferedreader class to read the elected file
                area.read(reader,null);//put the content of the file read from reader in the text area for display
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Save"))
        {
            JFileChooser saveas=new JFileChooser();//as we will have to choose a directory
            saveas.setApproveButtonText("Save");
            
            int action=saveas.showOpenDialog(this);//open the dialog & 'this' refers to the current JFrame or component
            if(action!=JFileChooser.APPROVE_OPTION)
                return;//if you dont click save option,then return
            File filename =new File(saveas.getSelectedFile()+" .txt");//save the selected file as .txt
            BufferedWriter outFile =null;
            
            try
            {
                outFile=new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            }catch(Exception e)
            {
                e.printStackTrace();
            }
        }
        else if(ae.getActionCommand().equals("Print"))
        {
            try
            {
                area.print();
            }catch(Exception e)
            {
                e.printStackTrace();//not having a printer can raise exception
            }
        }else if(ae.getActionCommand().equals("Exit"))
        {
            System.exit(0);
        }
        else if(ae.getActionCommand().equals("Copy"))
        {
            text=area.getSelectedText();
        }
        else if(ae.getActionCommand().equals("Paste"))
        {
            area.insert(text/*text copied*/,/*pos*/area.getCaretPosition());
        }
        else if(ae.getActionCommand().equals("Cut"))
        {
            text=area.getSelectedText();//copy
            area.replaceRange("",area.getSelectionStart(),area.getSelectionEnd());
        }
        else if(ae.getActionCommand().equals("Select All"))
        {
            area.selectAll();
        }
        else if(ae.getActionCommand().equals("About"))
        {
            new About().setVisible(true);
        }
        
    }
    public static  void main(String[] args) {
       new Notepad();//anonomous object
    }
    
}
