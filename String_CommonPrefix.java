import javax.swing.*;

public class String_CommonPrefix {
    public static void main(String [] args){
        JFrame frame = new JFrame("common prefix in two strings");
        //taking input through OptionPane
        String St1= JOptionPane.showInputDialog(frame,"Enter first string");
        String St2= JOptionPane.showInputDialog(frame,"Enter second string");
        //creating object of stringComPrefix class
        stringComPrefix obj = new stringComPrefix();
        //calling Commonpre method
        String ComPre = obj.CommonPre(St1,St2);
        JOptionPane.showMessageDialog(frame,ComPre);
        
        frame.setVisible(true); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //CommonPre method
    String CommonPre(String s1,String s2){
      for(int i =0;i<Math.min(s1.length(),s2.length());i++){
          
          if(s1.charAt(0)!=s2.charAt(0)){
              s1="no common prefix";
              return s1;
          }
          else
             if(s1.charAt(i)!=s2.charAt(i)){
                 return s1.substring(0,i);
          }
         
      } 
     return s1.substring(0,Math.min(s1.length(),s2.length()));
   }
    
}
