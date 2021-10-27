/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package run;

/**
 *
 * @author Ashu
 */
public interface DBInterface {

    String STUDENT_DETAILS = "student_details";
    String SUBJECT_DETAILS = "subject_details";
    String BACKSUB_DETAILS = "backpaper_details";

    //STUDENT_DETAILS table attributes
    String ST_USN = "usn";
    String ST_NAME = "name";
    String ST_TOTAL = "f_total";
    String ST_RESULT = "f_result";

    //SUBJECT_DETAILS table attributes
    String SUB_USN = "usn";
    String SUB_SUBNAME = "subname";
    String SUB_INTERNAL = "internal";
    String SUB_EXTERNAL = "external";
    String SUB_TOTAL = "total";
    String SUB_RESULT = "result";

}
