/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sourcegetter;

/**
 *
 * @author Rupinder S
 */
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.InputStream;  
import java.net.*;  
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
public class SourceGetter extends JFrame implements ActionListener{  
    JTextField tf;  
    TextArea ta;  
    JButton b;  
    JLabel l;  
    SourceGetter(){  
        super("INSTANT DUNZZZ");  
        l=new JLabel("SongName");  
        l.setBounds(50,50,80,20);  
    
        tf=new JTextField();  
        tf.setBounds(120,50,250,20);  
          
        b=new JButton("Download");  
        b.setBounds(120, 100,120,30);  
        b.addActionListener(this);  
          
        ta=new TextArea();  
        ta.setBounds(120,150,250,150);  
          
        add(l);add(tf);add(b);add(ta);  
        setSize(400,400);  
        setLayout(null);  
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
    }  
    public void actionPerformed(ActionEvent e){  
        String s=tf.getText(); 
        s=s.replaceAll(" ","");
        if(s==null){}  
        else{  
            try{
            String url1="http://127.0.0.1:5000/songlinks/name="+s;          
            URL u=new URL(url1);  
            HttpURLConnection uc=(HttpURLConnection)u.openConnection();  
            uc.setRequestMethod("GET");
            uc.connect();    
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));      
            StringBuilder sb=new StringBuilder();  
            String line;
            while ((line = br.readLine()) != null) {
              sb.append(line);
            }
            String source=sb.toString();
            System.out.println("dataaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa "+source);
            JSONObject result=new JSONObject(source);
            String downloadurl=result.getString("link");
            System.out.println("Download Link----> "+downloadurl);
            ta.setText(downloadurl);  
            }catch(Exception ex){System.out.println(ex);}  
        }  
    }  
    public static void main(String[] args) {  
        new SourceGetter();  
    }  
}  
