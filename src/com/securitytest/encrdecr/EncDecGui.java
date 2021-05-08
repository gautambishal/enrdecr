package com.securitytest.encrdecr;

import com.securitytest.exception.InvalidDecryption;
import com.securitytest.exception.InvalidEncryptedStringException;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class EncDecGui extends JPanel
{
    private JTextArea output;
    private JLabel hashDisplay ;
    
    public EncDecGui()
    {
        gui();
    }

    public void gui()
    {
        JFrame jf = new JFrame("Encryption-Decryption application");
        // Creates a constructor for JFrame.

        String info = fileOpener();

        JMenuBar bar = new JMenuBar();
        jf.setJMenuBar(bar);

        JMenu file = new JMenu("File");
        bar.add(file);

        JMenuItem load = new JMenuItem("Load");
        file.add(load);
        load.addActionListener(new LoadClick());
        
        JMenuItem exit = new JMenuItem("Exit");
        file.add(exit);
        exit.addActionListener(new ExitClick());

        JMenu help = new JMenu("Help");
        bar.add(help);

        JMenuItem guide = new JMenuItem("User Guide");
        help.add(guide);

        JMenuItem about = new JMenuItem("About");
        help.add(about);
        about.addActionListener(new AboutClick());

        JPanel jpOne = new JPanel();
      //********************************************************************************** 	
        	output = new JTextArea(20,40);
        	output.setText(info);
        	JScrollPane pane = new JScrollPane(output);
        	
        //jpOne end

        JPanel jpTwo = new JPanel();
      //**********************************************************************************  
        	JButton encrypt = new JButton("Encrypt");
        		jpTwo.add(encrypt);
        		encrypt.addActionListener(new EncryptClick());
        	
        	JButton decrypt = new JButton("Decrypt");
        		jpTwo.add(decrypt);
        		decrypt.addActionListener(new DecryptClick());

        JButton hash  = new JButton("Hash");
        		jpTwo.add(hash);
        		hash.addActionListener(new HashClick());
        		
        	hashDisplay = new JLabel("hash");
        	
        //jpTwo end
        
        jf.add(jpOne);
        jf.add(jpTwo);
        
        jf.setLayout(new GridLayout(2, 1));
        
        jpOne.add(pane);
        jpTwo.add(hashDisplay);
        
        jpOne.setSize(800, 200);
        jpTwo.setSize(800, 200);
        
        jf.setVisible(true); //the frame needs to be made visible.
        jf.setSize(800, 600); //Sets frame size to 800px by 600px.
        jf.setResizable(false);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Closes application on pressing the 'X' button on the top right corner.        
    }// void gui end

    public String fileOpener()
    {
        JFileChooser chooser = new JFileChooser();

        int status = chooser.showOpenDialog(null);

        try
        {
            if (status != JFileChooser.APPROVE_OPTION)
            {
                return null;
                // Error
            }
            else
            {
                File file = chooser.getSelectedFile();
                System.out.println(file.getAbsolutePath());
                BufferedReader bf= new BufferedReader(new FileReader(file));
                String line;
                String fullText="";
                System.out.println("I am here");
                
                    while((line=bf.readLine())!=null)
                    {
                        fullText=fullText+line;
                        System.out.println(fullText);
                    }

                return fullText;
            }//else end
        } catch (Exception e)
        {
            System.out.println(e);
            return "error";
        }//try catch end
    }//filechooser end

    public static void main(String[] args)
    {
        new EncDecGui();
        //calls the class as a constructor

    }// main end
    
    //ActionListener adds events to the GUI objects.
    
    class ExitClick implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            System.exit(0);//System.exit is used to procedurally terminate the spplication.
        }//ExitClick listener end

    }// ExitClick end

    class AboutClick implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            JOptionPane.showMessageDialog(null, "This program shows graph for religious diversity in percentage.", "About",
                JOptionPane.INFORMATION_MESSAGE);
            //Opens a dialogbox to show info about the application.
        }//AboutClick listener end

    }// AboutClick end

    class LoadClick implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String info = fileOpener();
            output.setText(info);
        }//loadClick listener end

    }// LoadClick end
    
    class EncryptClick implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		String fileText=output.getText();
    		String encryptedFileText=EncrDecr.encrypt(fileText);
    		output.setText(encryptedFileText);
    	}//EncryptClick listener end
    }//EncryptClick end
    
    class DecryptClick implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		String encryptedText=output.getText();
            String plainText= null;
            try {
                plainText = EncrDecr.decrypt(encryptedText);
            } catch (InvalidEncryptedStringException e1) {
                e1.printStackTrace();
            } catch (InvalidDecryption invalidDecryption) {
                invalidDecryption.printStackTrace();
            }
            output.setText(plainText);
    	}//DecryptClick listener end
    }//DecryptClick end
    
    class HashClick implements ActionListener
    {
    	public void actionPerformed(ActionEvent e)
    	{
    		String textTobeHashed=output.getText();
    		String hashedText=EncrDecr.hasCod(textTobeHashed);
           hashDisplay.setText(hashedText);
    	}
    }

}// class end
