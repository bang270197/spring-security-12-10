package com.example.springsecurity.controller;

import com.example.springsecurity.entity.Student;
import com.example.springsecurity.service.StudentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api")
public class StudentController {
    private final StudentService studentService;

    public static final String[] title = {"stt","name","description"};

    @PostMapping("/student")
    public ResponseEntity<String> saveStudent(@RequestBody Student student){
        try {
            studentService.save(student);
            return new ResponseEntity<String>("Create success", HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.CREATED);
        }

    }

    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudent(){
        try {
            List<Student> students = studentService.findAll();
            return new ResponseEntity(students, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/export")
    public ResponseEntity exportFile()
    {
        try{
            Workbook workbook = new XSSFWorkbook();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
                Sheet sheet = workbook.createSheet("Sheet1");

                Font headerFont = workbook.createFont();
                headerFont.setBold(true);
                headerFont.setFontHeightInPoints((short) 14);
                headerFont.setColor(IndexedColors.BLACK.getIndex());

                CellStyle headerCellStyle = workbook.createCellStyle();
                headerCellStyle.setFont(headerFont);
                headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

                Row headerRow = sheet.createRow(0);
                for (int i = 0; i < title.length; ++i) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(title[i]);
                    cell.setCellStyle(headerCellStyle);
                }

                int numRow = 1;
                List<Student> students = studentService.findAll();
                for (Student std : students) {
                    Row row = sheet.createRow(numRow);
                    Cell firstCell = row.createCell(0);
                    Cell secondCell = row.createCell(1);
                    Cell thirdCell = row.createCell(2);

                    firstCell.setCellValue(numRow);
                    secondCell.setCellValue(std.getName());
                    thirdCell.setCellValue(std.getDescription());

                    numRow++;
                }
                for (int i = 0; i < title.length; ++i) {
                    sheet.autoSizeColumn(i);
                }
//            File fileExport = new File("RelationshipsAgents.xlsx");
//            FileOutputStream outFile = new FileOutputStream(fileExport);
//            wb.write(outFile);
//            outFile.close();
//            HttpHeaders respHeaders = new HttpHeaders();
//            respHeaders.setContentType(MediaType.APPLICATION_OCTET_STREAM);
//            respHeaders.setContentDispositionFormData("attachment", titleName + ngayGioXem + ".xlsx");
//            InputStreamResource isr = new InputStreamResource(new FileInputStream(fileExport));
//
//            wb.close();
//
//            outFile.flush();
//            return ResponseEntity.ok().headers(respHeaders).body(isr);
                workbook.write(out);
                String fileName = "test"+ LocalDate.now() + ".xlsx";
                ByteArrayInputStream inp = new ByteArrayInputStream(out.toByteArray());
                InputStreamResource file = new InputStreamResource(inp);
                return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName)
                        .contentType(MediaType.parseMediaType("application/vnd.ms-excel")).body(file);
        }catch (Exception e) {

        }
        return ResponseEntity.ok("ok");
    }

    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try (OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }

    private static void writeBook(Student student,Row row){
            Cell cellStt = row.createCell(0);
            cellStt.setCellValue(student.getId());
            Cell cellName = row.createCell(1);
            cellName.setCellValue(student.getName());
            Cell cellDescription = row.createCell(2);
            cellDescription.setCellValue(student.getDescription());

    }

    private static void autosizeColumn(Sheet sheet, int lastColumn){
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++){
            sheet.autoSizeColumn(columnIndex);
        }
    }






}
