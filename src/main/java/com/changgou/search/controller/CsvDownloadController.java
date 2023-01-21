package com.changgou.search.controller;

import com.changgou.search.entity.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
public class CsvDownloadController {

    @RequestMapping(value="/csv")
    public String showCSVDownloadPage() {

        return "csvDownload";
    }

    @RequestMapping(value = "/downloadCSV", method = RequestMethod.GET)
    public @ResponseBody
    ResponseEntity<byte[]> download() {
        //filename
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = format.format(new Date());
        String fileName = "your_file_name" + currentTime + ".csv";

        // 查询数据库表并导出为CSV格式
        byte[] data = exportToCsv();
        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("text/csv"));
        headers.add("Content-Disposition", "attachment; filename="+fileName+".csv");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        // 返回数据
        return new ResponseEntity<byte[]>(data, headers, HttpStatus.OK);
    }

    private byte[] exportToCsv() {
        // 查询数据库表
        //List<YourDataModel> dataList = yourDataService.findAll();
        //for test
        List<Product> dataList = new ArrayList<Product>(){{
            add(new Product("zhangsan","123",5));
            add(new Product("李四","456",7));
            add(new Product("王五","789",9));
        }};

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        // 使用Apache CSV库导出数据
        try (
                OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT);
                //在导出csv文件时使用Excel兼容的CSV格式
                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.EXCEL);
        ) {
            // 输出表头
            csvPrinter.printRecord("name", "price", "number");
            // 输出数据
            for (Product data : dataList) {
                csvPrinter.printRecord(data.getName(), data.getPrice(), data.getNumber());
            }
            csvPrinter.flush();
        } catch (IOException e) {
            // handle exception
        }
        return outputStream.toByteArray();
    }

    @GetMapping("/your_backend_url")
    public ResponseEntity<Map<String,String>> downloadCSV() {
        Map<String,String> response = new HashMap<>();
        //文件名
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String currentTime = format.format(new Date());
        String fileName = "your_file_name" + currentTime + ".csv";
        response.put("fileName", fileName);
        //内容；在CSV文件中使用UTF-8编码，通常可以在文件头添加一行字符：\ufeff
        response.put("content","\ufeff"+"csvContent");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
