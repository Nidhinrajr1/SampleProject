/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Forms.MainForm;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import run.DBConnect;
import run.DBInterface;

public class GenReport implements DBInterface {

    int slNo;
    int failArray[];        //Retriveing per subject
    int passArray[];
    double passPercentArray[];
    int fcdArray[];
    int firstClassArray[];
    int secondClassArray[];
    int registeredArray[];
    String failQuery;
    String passQuery;
    String fcdQuery;
    String firstClassQuery;
    String secondClassQuery;
    private String subName;
    private int subjectSize;
    Connection con;
    int classFCD;
    int classFC;
    int classSC;
    int classFail;

    public int getClassFCD() {
        return classFCD;
    }

    public void setClassFCD(int classFCD) {
        this.classFCD = classFCD;
    }

    public int getClassFC() {
        return classFC;
    }

    public void setClassFC(int classFC) {
        this.classFC = classFC;
    }

    public int getClassSC() {
        return classSC;
    }

    public void setClassSC(int classSC) {
        this.classSC = classSC;
    }

    public int getClassFail() {
        return classFail;
    }

    public void setClassFail(int classFail) {
        this.classFail = classFail;
    }

    public DefaultTableModel fillReportTable() {
        con = DBConnect.connection;
        retrieveSubjectNames();
        subjectSize = MainForm.subNamesV.size();
        DefaultTableModel model = new DefaultTableModel() {
            Class[] types = new Class[]{
                java.lang.String.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
        };
        failArray = new int[subjectSize];
        passArray = new int[subjectSize];
        passPercentArray = new double[subjectSize];
        fcdArray = new int[subjectSize];
        firstClassArray = new int[subjectSize];
        secondClassArray = new int[subjectSize];
        registeredArray = new int[subjectSize];
        //model.addColumn("Sl. no");
        model.addColumn("Subject Names ");
        model.addColumn("Registered");
        model.addColumn("Pass %");
        model.addColumn("Fail");
        model.addColumn("FCD");
        model.addColumn("FC");
        model.addColumn("SC");
        slNo = 0;

        for (int i = 0; i < subjectSize; i++) {
            subName = MainForm.subNamesV.get(i);
            //System.out.println("sub name : " + subName);
            failArray[i] = getFailCount(subName);
            passArray[i] = getPassCount(subName);
            fcdArray[i] = getFCDCount(subName);
            firstClassArray[i] = getFCCount(subName);
            secondClassArray[i] = getSCCount(subName);
            registeredArray[i] = failArray[i] + passArray[i];
            passPercentArray[i] = ((double) passArray[i] / (double) registeredArray[i]) * 100;
            // System.out.println(subName + " " + failArray[i]);
            model.insertRow(slNo++, new Object[]{subName, registeredArray[i], passPercentArray[i], failArray[i], fcdArray[i], firstClassArray[i], secondClassArray[i]});
        }
        return model;
    }

    public void getAllLabelValues() {
        setClassFCD(getClassFCDCount());
        setClassFC(getClassFirstCount());
        setClassSC(getClassSecondCount());
        setClassFail(getClassFailCount());
    }

    private int getFailCount(String subName) {
        int FailCount = 0;

        ResultSet rs = null;
        failQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_RESULT + "='F'";
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(failQuery);
            if (rs.next()) {
                FailCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return FailCount;
    }

    private int getPassCount(String subName) {
        int PassCount = 0;

        ResultSet rs = null;
        passQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_RESULT + "='P'";
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(passQuery);
            if (rs.next()) {
                PassCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return PassCount;

    }

    private int getFCDCount(String subName) {
        int fcdCount = 0;

        ResultSet rs = null;
        if (islab(subName)) {
            fcdQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 53 AND 76";

        } else {
            fcdQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 88 AND 126";
        }
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(fcdQuery);
            if (rs.next()) {
                fcdCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fcdCount;

    }

    private int getFCCount(String subName) {
        int fcdCount = 0;

        ResultSet rs = null;
        if (islab(subName)) {
            firstClassQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 45 AND 52";

        } else {
            firstClassQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 75 AND 87";
        }
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(firstClassQuery);
            if (rs.next()) {
                fcdCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fcdCount;

    }

    private int getSCCount(String subName) {
        int scCount = 0;
        if (islab(subName));
        ResultSet rs = null;
        if (islab(subName)) {
            secondClassQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 38 AND 44";
        } else {
            secondClassQuery = "SELECT COUNT(*) FROM " + SUBJECT_DETAILS + " WHERE " + SUB_SUBNAME + "='" + subName + "' AND " + SUB_TOTAL + " BETWEEN 63 AND 74";
        }
        try {
            Statement stmt;
            stmt = con.createStatement();
            rs = stmt.executeQuery(secondClassQuery);
            if (rs.next()) {
                scCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scCount;

    }

    public void retrieveSubjectNames() {
        MainForm.subNamesV.clear();

        ResultSet rs = null;
        String sql = "Select DISTINCT " + SUB_SUBNAME + " From " + SUBJECT_DETAILS;
        try {
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                MainForm.subNamesV.add(rs.getString(SUB_SUBNAME));
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);

        }

    }

    private int getClassFCDCount() {
        int fcdCount = 0;

        ResultSet rs = null;
        String Query = "SELECT COUNT(*) FROM " + STUDENT_DETAILS + " WHERE " + ST_RESULT + "='FIRST CLASS WITH DISTINCTION'";

        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
            if (rs.next()) {
                fcdCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fcdCount;
    }

    private int getClassFirstCount() {
        int fcCount = 0;

        ResultSet rs = null;
        String Query = "SELECT COUNT(*) FROM " + STUDENT_DETAILS + " WHERE " + ST_RESULT + "='FIRST CLASS'";
        // System.out.println(Query);
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
            if (rs.next()) {
                fcCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return fcCount;
    }

    private int getClassSecondCount() {

        int scCount = 0;

        ResultSet rs = null;
        String Query;
        Query = "SELECT COUNT(*) FROM " + STUDENT_DETAILS + " WHERE " + ST_RESULT + "='SECOND CLASS'";
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
            if (rs.next()) {
                scCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return scCount;
    }

    private int getClassFailCount() {
        int failCount = 0;

        ResultSet rs = null;
        String Query;
        Query = "SELECT COUNT(*) FROM " + STUDENT_DETAILS + " WHERE " + ST_RESULT + "='FAIL'";
        Statement stmt;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery(Query);
            if (rs.next()) {
                failCount = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GenReport.class.getName()).log(Level.SEVERE, null, ex);
        }

        return failCount;
    }

    boolean islab(String sub) {
        String temp = sub.split("\\(")[1].substring(4, 5);
        if (temp.equalsIgnoreCase("L")) {
            return true;
        }
        return false;
    }

}
