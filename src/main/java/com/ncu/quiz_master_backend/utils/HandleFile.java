package com.ncu.quiz_master_backend.utils;


import com.ncu.quiz_master_backend.entity.Question;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class HandleFile {

    public static List<Question> excelReader(InputStream inputStream) {
        List<Question> questionList = new ArrayList<>();
        try {
            Workbook workbook = new XSSFWorkbook(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            // 读取数据并填充到 question 中
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;
                Question question = new Question();
                Cell questionDescCell = row.getCell(0); //
                Cell optionACell = row.getCell(1); //
                Cell optionBCell = row.getCell(2); //
                Cell optionCCell = row.getCell(3); //
                Cell optionDCell = row.getCell(4); //
                Cell answerCell = row.getCell(5); //
                Cell typeCell = row.getCell(6); //

                question.setQuestionDesc(getCellStringValue(questionDescCell));
                question.setOptionA(getCellStringValue(optionACell));
                question.setOptionB(getCellStringValue(optionBCell));
                question.setOptionC(getCellStringValue(optionCCell));
                question.setOptionD(getCellStringValue(optionDCell));
                question.setAnswer(getCellStringValue(answerCell));
                question.setType(convertStringToInt(getCellStringValue(typeCell)));
                log.info("ExcelTest"+question.toString());
                questionList.add(question);
            }
            workbook.close();
            inputStream.close();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return questionList;
    }

    /**
     * 根据传入的cell类型自动转换成String类型
     *
     * @param cell
     * @return
     */
    private static String getCellStringValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                } else {
                    return String.valueOf(cell.getNumericCellValue());
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getStringCellValue();
            default:
                return "";
        }
    }

    /**
     * 防止带小数的String转化成Integer时NumberFormatException报错
     *
     * @param str
     * @return
     * @throws NumberFormatException
     */
    private static int convertStringToInt(String str) throws NumberFormatException {
        try {
            if (str.contains(".")) {
                str = str.substring(0, str.indexOf('.'));
            }
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            log.info("NumberFormatException: Unable to convert '" + str + "' to integer.");
            return 0; // 或其他适当的默认值
        }
    }

    /**
     * 将答案字符串切分成字符串数组
     *
     * @param answer
     * @return
     */
    private static String[] AnswerProcess(String answer) {
        if (answer != null && !answer.isEmpty()) {
            String[] strArray = new String[answer.length()];
            // 遍历字符串，并将每个字符转换为字符串数组的一个元素
            for (int i = 0; i < answer.length(); i++) {
                strArray[i] = String.valueOf(answer.charAt(i));
            }
            return strArray;
        } else {
            return new String[0]; // 创建一个空的字符串数组
        }
    }

}
