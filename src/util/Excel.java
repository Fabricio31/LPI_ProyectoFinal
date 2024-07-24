package util;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;

public class Excel {
		

	@SuppressWarnings("resource")
	public static void crearArchivoExcel(JTable j) throws IOException {
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos de Excel", "xls");
	chooser.setFileFilter(filter);
	chooser.setDialogTitle("Guardar Archivo");
	chooser.setAcceptAllFileFilterUsed(false);
	if(j.getRowCount()==0) {
		JOptionPane.showMessageDialog(null,"No Hay datos");
	}
	else if (chooser.showSaveDialog(null)==JFileChooser.APPROVE_OPTION) {
		String ruta = chooser.getSelectedFile().toString().concat(".xls");
		try {
		File archivoXLs = new File(ruta);
		if(archivoXLs.exists()) {
			archivoXLs.delete();
		}
		archivoXLs.createNewFile();
		Workbook libro = new HSSFWorkbook();
		FileOutputStream archivo=new FileOutputStream(archivoXLs);
		org.apache.poi.ss.usermodel.Sheet hoja = libro.createSheet("Datos");
		hoja.setDisplayGridlines(false);
		for (int f = 0; f < j.getRowCount(); f++) {
			org.apache.poi.ss.usermodel.Row fila = hoja.createRow(f);
			for (int c = 0; c < j.getColumnCount() ; c++) {
				org.apache.poi.ss.usermodel.Cell celda=fila.createCell(c);
				if(f==0) {
					celda.setCellValue(j.getColumnName(c));
				}
				
			}
		}
		int filaInicio=1;
		for (int f = 0; f < j.getRowCount(); f++) {
			org.apache.poi.ss.usermodel.Row fila = hoja.createRow(filaInicio);
			filaInicio++;
			for (int c = 0; c < j.getColumnCount(); c++) {
				org.apache.poi.ss.usermodel.Cell celda =fila.createCell(c);
				if(j.getValueAt(f, c) instanceof Double) {
					celda.setCellValue(Double.parseDouble(j.getValueAt(f, c).toString()));
				}else if(j.getValueAt(f, c) instanceof Float) {
					celda.setCellValue(Float.parseFloat((String) j.getValueAt(f, c)));
				}else {
					celda.setCellValue(String.valueOf(j.getValueAt(f, c)));
				}
			}
			
		}
		libro.write(archivo);
		archivo.close();
		Desktop.getDesktop().open(archivoXLs);
		}catch (IOException|NumberFormatException e) {
			throw e;
		}
	}
		
	
	}
}
