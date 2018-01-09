package Tools;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DataProvider {

	public static Iterator<Object[]> getDataFromXLSFile(String xlsRelativeFilePath, String sheetName) {
		final List<Object[]> list = new ArrayList<Object[]>();
		
		try {
			final String targetFilePath = DataProvider.class.getResource(xlsRelativeFilePath).getPath();	
			final FileInputStream file = new FileInputStream(new File(targetFilePath));
			final HSSFWorkbook workbook = new HSSFWorkbook(file);
			final HSSFSheet sheet = workbook.getSheet(sheetName);
			final Iterator<Row> rowIterator = sheet.iterator();
			Row row;
			Iterator<Cell> cellIterator;
			List<String> dataFromCellsInARow;
			Cell cell;
			
			while (rowIterator.hasNext()) {
				row = rowIterator.next();
				cellIterator = row.cellIterator();
				dataFromCellsInARow = new ArrayList<String>();
				
				while (cellIterator.hasNext()) {
					cell = cellIterator.next();
					dataFromCellsInARow.add(cell.getStringCellValue());
				}
				list.add(dataFromCellsInARow.toArray());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list.iterator();
	}
	public static String[][] getExcelData(String fileName, String sheetName) throws IOException {
		String[][] arrayExcelData = null;
		try {
			String targetFilePath = DataProvider.class.getResource(fileName).getPath();
			FileInputStream fs = new FileInputStream(new File(targetFilePath));
			Workbook wb = Workbook.getWorkbook(fs);
			Sheet sh = wb.getSheet(sheetName);

			int totalNoOfCols = sh.getColumns();
			int totalNoOfRows = sh.getRows();

			arrayExcelData = new String[totalNoOfRows-1][totalNoOfCols];//[totalNoOfRows]

			for (int i= 1 ; i < totalNoOfRows; i++) {//i=0

				for (int j=0; j < totalNoOfCols; j++) {
					arrayExcelData[i-1][j] = sh.getCell(j, i).getContents();//arrayExcelData[i]
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.printStackTrace();
		} catch (BiffException e) {
			e.printStackTrace();
		}
		return arrayExcelData;
	}
}
