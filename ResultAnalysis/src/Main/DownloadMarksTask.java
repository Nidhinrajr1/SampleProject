package Main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import Forms.EnterUsnForm;
import Forms.MainForm;
import java.sql.Connection;
import java.sql.Statement;
import java.util.List;
import javax.swing.SwingWorker;
import run.DBConnect;
import run.DBInterface;

public class DownloadMarksTask extends SwingWorker<Integer, Integer> implements DBInterface {

    Statement stmtStd, stmtSub, stmtBackSub;
    Connection con;

    public DownloadMarksTask() {
        //initialize 
        con = DBConnect.connection;

    }

    @Override
    public Integer doInBackground() throws InterruptedException {
        resultFetch r = new resultFetch();

        if (MainForm.stopFlag == true) {
            stopFetching();
        }
        for (int i = 0; i < MainForm.usnList.size() && MainForm.stopFlag == false; i++) {
            boolean resSuccess = false;
            try {

                setProgress((i + 1) * 100 / MainForm.usnList.size());
                //System.out.println(MainForm.usnList.get(i));

                
                //check the selected download server and fetch result accordingly
                if (MainForm.DownloadServer == 0) {
                    resSuccess = r.FetchTheresult(MainForm.usnList.get(i), EnterUsnForm.sem);
                } else {
                    resSuccess = r.FetchTheresultVTU(MainForm.usnList.get(i), EnterUsnForm.sem);
                }

                if (resSuccess) {

                    String stdQuery = "Insert into " + STUDENT_DETAILS + " values ('" + MainForm.usnList.get(i) + "',\"" + r.name + "\"," + Integer.parseInt(r.totalmarks) + ",'" + r.mk + "')";
                    stmtStd = con.createStatement();
                    stmtStd.executeUpdate(stdQuery);

                    int semsubs = 8;
                    if (EnterUsnForm.sem == 8) {
                        semsubs = 6;
                    }

                    for (int x = 0; x < semsubs; x++) {
                        String subQuery = "Insert into " + SUBJECT_DETAILS + " values ('" + MainForm.usnList.get(i) + "','" + r.subjects[x] + "'," + r.marks[x][1] + "," + r.marks[x][0] + "," + r.marks[x][2] + ",'" + r.res[x] + "')";
                        stmtSub = con.createStatement();
                        stmtSub.executeUpdate(subQuery);
                    }
                    for (int x = semsubs; x < r.subs; x++) {
                        String subQuery = "Insert into " + BACKSUB_DETAILS + " values ('" + MainForm.usnList.get(i) + "','" + r.subjects[x] + "'," + r.marks[x][1] + "," + r.marks[x][0] + "," + r.marks[x][2] + ",'" + r.res[x] + "')";
                        stmtBackSub = con.createStatement();
                        stmtBackSub.executeUpdate(subQuery);
                    }
                }

            } catch (Exception e) {
                System.out.println("Error:" + e);
                e.printStackTrace();
                MainForm.log(MainForm.usnList.get(i) + " : Record already exists");
                //Logger.getLogger(DownloadMarksTask.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return 1;
    }

    @Override
    protected void process(List<Integer> chunks) {

    }

    @Override
    protected void done() {
        if (MainForm.autoRetry && MainForm.retrylimit > 0 && !MainForm.RetryList.isEmpty() && MainForm.stopFlag == false) {
            MainForm.objCopy.clickRestart();
            MainForm.retrylimit--;
        } else {
            System.out.println("DONE!!!");
            MainForm.log("Download Completed.");
            MainForm.log("Please check download details to see list of USN failed to fetch.");
            MainForm.objCopy.clickStop();
        }

    }

    public void stopFetching() {
        this.cancel(true);
    }
}
