package com.caihua.controller;

import ch.qos.logback.core.rolling.helper.FileNamePattern;
import com.caihua.bean.People;
import com.caihua.service.PeopleService;
import com.sun.xml.internal.messaging.saaj.packaging.mime.internet.MimeUtility;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.List;

@Controller
@RequestMapping("people")
public class PeopleController {
    @Autowired
    private PeopleService peopleService;

    @RequestMapping("findPeople")
    private void downloadexcel(String birth, HttpServletResponse response, HttpServletRequest request) {
        List<People> list = peopleService.findPeople(birth);
        if (list != null) {
            // 开始输出到表格
            // 第一步，创建一个webbook，对应一个Excel文件
            HSSFWorkbook wb = null;
            HSSFSheet sheet = null;
            HSSFRow row;
            wb = new HSSFWorkbook();
            // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
            sheet = wb.createSheet("人员信息表");
            //设置列宽
            sheet.setColumnWidth(0, 7000);
            sheet.setColumnWidth(1, 6500);
            sheet.setColumnWidth(2, 6500);
            sheet.setColumnWidth(3, 6500);
            sheet.setColumnWidth(4, 6500);
            sheet.setColumnWidth(5, 6500);
            sheet.setColumnWidth(6, 6500);
            sheet.setColumnWidth(7, 6500);
            sheet.setColumnWidth(8, 6500);

            // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
            row = sheet.createRow((int) 0);

            //设置第一行颜色的style
            CellStyle style = wb.createCellStyle();
            style.setFillForegroundColor(IndexedColors.RED.getIndex());
            style.setFillPattern(FillPatternType.SOLID_FOREGROUND);

            // 第四步，创建单元格，并设置值表头 设置表头居中
            //HSSFCellStyle style = wb.createCellStyle();
            //style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

            HSSFCell cell = row.createCell((short) 0);
            cell.setCellStyle(style);
            cell.setCellValue("身份证");

            cell = row.createCell((short) 1);
            cell.setCellStyle(style);
            cell.setCellValue("姓名");

            cell = row.createCell((short) 2);
            cell.setCellStyle(style);
            cell.setCellValue("年龄");

            cell = row.createCell((short) 3);
            cell.setCellStyle(style);
            cell.setCellValue("性别");

            cell = row.createCell((short) 4);
            cell.setCellStyle(style);
            cell.setCellValue("出生年月");

            cell = row.createCell((short) 5);
            cell.setCellStyle(style);
            cell.setCellValue("爱好");

            for (int i = 0; i < list.size(); i++) {
                row = sheet.createRow(i + 1);
                People people = list.get(i);

                // 第四步，创建单元格，并设置值
                row.createCell((short) 0).setCellValue(people.getIdCard());
                row.createCell((short) 1).setCellValue(people.getName());
                row.createCell((short) 2).setCellValue(people.getAge());
                row.createCell((short) 3).setCellValue(people.getGender());
                row.createCell((short) 4).setCellValue(people.getBirth());
                row.createCell((short) 5).setCellValue(people.getHobby());
            }
            try {

                String fileName = "人员信息.xls";//设置下载时客户端Excel的名称
                fileName = encodeFilename(fileName, request);
                response.setContentType("application/vnd.ms-excel;charset=utf-8");
                response.setHeader("Content-Disposition", "Attachment;Filename=" + fileName);
                OutputStream ouputStream = response.getOutputStream();
                wb.write(ouputStream);
                ouputStream.flush();
                ouputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String encodeFilename(String filename, HttpServletRequest request) {
        /**
         * 获取客户端浏览器和操作系统信息
         * 在IE浏览器中得到的是：User-Agent=Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1; Maxthon; Alexa Toolbar)
         * 在Firefox中得到的是：User-Agent=Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.7.10) Gecko/20050717 Firefox/1.0.6
         */
        String agent = request.getHeader("USER-AGENT");
        try {
            if ((agent != null) && (-1 != agent.indexOf("MSIE"))) {
                String newFileName = URLEncoder.encode(filename, "UTF-8");
                newFileName = StringUtils.replace(newFileName, "+", "%20");
                if (newFileName.length() > 150) {
                    newFileName = new String(filename.getBytes("GB2312"), "ISO8859-1");
                    newFileName = StringUtils.replace(newFileName, " ", "%20");
                }
                return newFileName;
            }
            if ((agent != null) && (-1 != agent.indexOf("Mozilla"))) {
                return MimeUtility.encodeText(filename, "UTF-8", "B");
            }

            return filename;
        } catch (Exception ex) {
            return filename;
        }
    }
}

