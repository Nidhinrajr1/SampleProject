package Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Forms.EnterUsnForm;
import Forms.MainForm;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class extractUSN {

    boolean allExtractSuccess = true;

    /**
     * @param args the command line arguments
     */
    public static Vector<String> myUsnList;

    public extractUSN() {
        myUsnList = new Vector<String>();
    }

    public Vector<String> getUsnFromFile(String inFile) {
        MainForm.log("File selected : " + inFile);
        MainForm.log("Extracting USN from input file..\nPlease wait ....");
        BufferedReader br;
        Pattern p = Pattern.compile("4[pP][aA][0-9]{2}[a-zA-Z]{2}[0-9]{3}");
        try {
            br = new BufferedReader(new FileReader(inFile));
            String line;

            while ((line = br.readLine()) != null) {
                Matcher m = p.matcher(line);
                while (m.find()) {
                    myUsnList.add(m.group(0));
                }
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
            allExtractSuccess = false;
            MainForm.logError("Error :" + e.getMessage());
            //JOptionPane.showMessageDialog(null, e);
            Logger.getLogger(EnterUsnForm.class.getName()).log(Level.SEVERE, null, e);
        }

        if (!allExtractSuccess) {
            JOptionPane.showMessageDialog(null, "Some USN have not been added \n Please check the input file if the USN is correct");
        }
        MainForm.log("USN extracted");
        return myUsnList;
    }
}
