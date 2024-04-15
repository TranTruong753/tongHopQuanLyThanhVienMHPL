package BUS;

import java.text.SimpleDateFormat;

import com.google.protobuf.TextFormat.ParseException;
import java.util.Date;
import javax.swing.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.awt.Color;

public class Check {
     public static String Date_String(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.format(date);
    }
    public static Date String_Date(String date) throws ParseException, java.text.ParseException{
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        return formatter.parse(date);
    }
    public static boolean isNumber(String str) {
        String str2 = str.replace(".", "");
        for (int i = 0; i < str2.length(); i++) {
            if (!Character.isDigit(str2.charAt(i))) {
                return false;
            }
        }
        return true;
    }
    public static void validateNumber(JTextField phoneTextField) {
        String phoneNumber = phoneTextField.getText();
        Pattern pattern = Pattern.compile("^\\d+(\\.\\d+)?");
        Matcher matcher = pattern.matcher(phoneNumber);
        boolean isValid = matcher.matches();
        phoneTextField.setBorder(BorderFactory.createLineBorder(isValid ? Color.BLACK : Color.RED));
    }
    public static boolean isValidTimeFormat(String time) {
        Pattern pattern = Pattern.compile("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
        Matcher matcher = pattern.matcher(time);
        return matcher.matches();
    }
}
