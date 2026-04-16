package VerifyLogin;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class SheetUtility {
	FileInputStream fiStream;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	XSSFRow row;
	XSSFCell cell;
	public SheetUtility(String file_path) throws IOException
	{
		this.fiStream= new FileInputStream(new File(file_path));
		this.workbook = new XSSFWorkbook(fiStream);
	}
	public String getUsername(int sheet_index, int row_Index) throws IOException 
	{
		sheet= workbook.getSheetAt(sheet_index);
		row= sheet.getRow(row_Index);
		cell = row.getCell(0);
		return  (cell == null) ? "" : cell.toString();
	}
	public String getPass(int sheet_index, int row_Index) throws IOException 
	{
		sheet= workbook.getSheetAt(sheet_index);
		row= sheet.getRow(row_Index);
		cell =row.getCell(1);
		return (cell==null) ? "": cell.toString();
	}
	public void close() throws IOException 
	{
		workbook.close();
		fiStream.close();
	}
}
