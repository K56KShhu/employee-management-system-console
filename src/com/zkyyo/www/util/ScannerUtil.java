package com.zkyyo.www.util;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.regex.*;

public class ScannerUtil {

    /**
     * 验证输入薪水,薪水最小精度为0.5元
     *
     * @return 薪水
     */
    public static double scanSalary() {
        Scanner in = new Scanner(System.in);
        Pattern p;
        Matcher m;
        String regex = "^\\d*\\.?[05]?0*$";

        do {
            String enterStr = in.nextLine();
            enterStr = enterStr.trim();
            if (enterStr.equals("")) {
                System.out.println("检测到空行,请重新输入:");
            } else {
                p = Pattern.compile(regex);
                m = p.matcher(enterStr);
                if (m.matches()) {
                    return Double.valueOf(enterStr);
                } else {
                    System.out.println("检测到非法字符,请重新输入:");
                }
            }
        } while (true);
    }

    public static int scanNum() {
        Scanner in = new Scanner(System.in);
        Pattern p;
        Matcher m;
        String regex = "^\\d+$";

        do {
            String enterStr = in.nextLine();
            enterStr = enterStr.trim();
            if (enterStr.equals("")) {
                System.out.println("检测到空行,请重新输入:");
            } else {
                p = Pattern.compile(regex);
                m = p.matcher(enterStr);
                if (m.matches()) {
                    return Integer.valueOf(enterStr);
                } else {
                    System.out.println("请输入纯数字:");
                }
            }
        } while (true);
    }

    /**
     * 验证输入邮箱
     *
     * @return 符合要求的邮箱号
     */
    public static String scanEmail() {
        Scanner in = new Scanner(System.in);
        Pattern p;
        Matcher m;
        String regex = "^[\\w.+-]+@[\\w.+-]+\\.[\\w.+-]+$";

        do {
            String enterStr = in.nextLine();
            enterStr = enterStr.trim();
            if (enterStr.equals(""))
                return "";
            else {
                p = Pattern.compile(regex);
                m = p.matcher(enterStr);
                if (m.matches()) {
                    return String.valueOf(enterStr);
                } else {
                    System.out.println("非法邮箱格式,请重新输入:");
                }
            }
        } while (true);
    }

    public static String scanString(boolean required) {
        Scanner in = new Scanner(System.in);

        do {
            String enterStr = in.nextLine();
            enterStr = enterStr.trim();
            if (enterStr.equals("")) {
                if (required) {
                    System.out.println("检测到空行,请重新输入:");
                } else {
                    return "";
                }
            } else {
                return enterStr;
            }
        } while (true);
    }

    /**
     * 验证输入日期yyyy-MM-dd
     *
     * @return 合法的日期
     */

    public static Date scanSqlDate() {
        Scanner in = new Scanner(System.in);
        Pattern p;
        Matcher m;
        String regex = "^\\d{4}-\\d{1,2}-\\d{1,2}";

        do {
            String enterStr = in.nextLine();
            enterStr = enterStr.trim();
            //检测到空行时,设为当前日期
            if (enterStr.equals("")) {
                return new Date(System.currentTimeMillis());
            } else {
                p = Pattern.compile(regex);
                m = p.matcher(enterStr);
                //检查是否匹配xxxx-xx-xx格式
                if (!m.matches()) {
                    System.out.println("非法格式,请重新输入:");
                } else {
                    String[] yearMonthDay = enterStr.split("-");
                    int year = Integer.valueOf(yearMonthDay[0]);
                    int month = Integer.valueOf(yearMonthDay[1]);
                    int day = Integer.valueOf(yearMonthDay[2]);
                    //检查是否为合法日期
                    if (month > 12) {
                        System.out.println("非法月份,请重新输入:");
                    } else {
                        Calendar mycal = new GregorianCalendar(year, month - 1, 1); //起始月份为0
                        int daysInMonth = mycal.getActualMaximum(Calendar.DAY_OF_MONTH);
                        if (day > daysInMonth) {
                            System.out.println("非法日数,请重新输入:");
                        } else {
                            return Date.valueOf(enterStr);
                        }
                    }
                }
            }
        } while (true);
    }

    public static String scanPwd() {
        Scanner in = new Scanner(System.in);

        do {
            String enterStr = in.nextLine();
            if (enterStr.equals("")) {
                System.out.println("密码不能为空,请重新输入");
            } else {
                return enterStr;
            }
        } while (true);
    }
}
