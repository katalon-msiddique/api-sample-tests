import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileOutputStream

import internal.GlobalVariable

class ExcelUtils {

	@Keyword
	def writeDataToExcel(String filePath, String sheetName, List<Map<String, Object>> data) {
		try {
			File file = new File(filePath)
			Workbook workbook
			Sheet sheet

			if (file.exists()) {
				FileInputStream fis = new FileInputStream(file)
				workbook = WorkbookFactory.create(fis)
				fis.close()
			} else {
				workbook = new XSSFWorkbook()
			}

			sheet = workbook.getSheet(sheetName) ?: workbook.createSheet(sheetName)

			// Write headers
			Row headerRow = sheet.getRow(0) ?: sheet.createRow(0)
			String[] headers = [
				"ID",
				"Email",
				"First Name",
				"Last Name",
				"Avatar"
			]
			headers.eachWithIndex { header, colIndex ->
				Cell cell = headerRow.getCell(colIndex) ?: headerRow.createCell(colIndex)
				cell.setCellValue(header)
			}

			// Write data rows
			data.eachWithIndex { rowData, rowIndex ->
				Row row = sheet.getRow(rowIndex + 1) ?: sheet.createRow(rowIndex + 1)
				row.createCell(0).setCellValue(rowData.id.toString())
				row.createCell(1).setCellValue(rowData.email)
				row.createCell(2).setCellValue(rowData.first_name)
				row.createCell(3).setCellValue(rowData.last_name)
				row.createCell(4).setCellValue(rowData.avatar)
			}

			FileOutputStream fos = new FileOutputStream(file)
			workbook.write(fos)
			fos.close()

			println("Data written successfully to Excel!")
		} catch (Exception e) {
			println("Error writing to Excel: ${e.message}")
			e.printStackTrace()
		}
	}
}