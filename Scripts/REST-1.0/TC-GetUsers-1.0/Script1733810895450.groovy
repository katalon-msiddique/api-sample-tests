import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject
import com.kms.katalon.core.checkpoint.Checkpoint as Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling as FailureHandling
import com.kms.katalon.core.testcase.TestCase as TestCase
import com.kms.katalon.core.testdata.TestData as TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject as TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import internal.GlobalVariable as GlobalVariable
import org.openqa.selenium.Keys as Keys
import groovy.json.JsonSlurper as JsonSlurper
import ExcelUtils as ExcelUtils

response = WS.sendRequest(findTestObject('REST1.0/GETUsers-1.0'))

// 2. Parse API Response
def jsonResponse = new JsonSlurper().parseText(response.getResponseBodyContent())

// 3. Extract 'data' Field (List of Users)
def userData = jsonResponse.data

// 4. File Path and Sheet Name
String filePath = '/Users/mahtabsiddique/Documents/samplefiles/file.xlsx'

String sheetName = 'APIResponse'

// 5. Call Custom Keyword to Write Data to Excel
CustomKeywords.'ExcelUtils.writeDataToExcel'(filePath, sheetName, userData)

def str1 = "testdata"

response2 = WS.sendRequest(findTestObject('REST/Postman/Single-User/' + str1))




