package main;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileSystemView;

public class Main {
	public static void scanFile() throws IOException {
		File selectedFile = null;
		String currentLine = "";
		JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
		int returnValue = jfc.showOpenDialog(null);
		if (returnValue == JFileChooser.APPROVE_OPTION) {
			selectedFile = jfc.getSelectedFile();
		}
		FileReader reader1 = new FileReader(selectedFile);
		BufferedReader reader = new BufferedReader(reader1);
		String newpath = selectedFile.getAbsolutePath();
		newpath = newpath.substring(0, newpath.lastIndexOf("\\"));
		newpath += "\\SBSCRIPT.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter(newpath));
		String temp = "";
		String var = "";
		while(reader.ready()) {
			currentLine = reader.readLine();
			if(currentLine.equals("function move()")) {	//Gere le const move
				writer.write("const move = ["); 
				writer.newLine(); 
			}
			else if(currentLine.equals("function bank()")) { //Gere la function bank
				writer.write("const bank = [");
				writer.newLine();
			}
			else if (currentLine.equals("function phenix()")) {
				writer.write("const phenix = [");
				writer.newLine();
			}
			else if(currentLine.contains("end")) { //Gere les end
				writer.write("]");
				writer.newLine();
			}
			else if(currentLine.length() == 0) {} //Gere ligne vide
			else if(currentLine.contains("{") && !currentLine.contains("return")){ //Gere les moves normaux
				for(int i = 0; i < currentLine.length(); i++) {
					if(currentLine.charAt(i) == '=') {
						temp += ":";
					} else {
						temp += currentLine.charAt(i);
					}
				}
				writer.write(temp);
				writer.newLine();
				temp = "";
			}
		}
		writer.close();
	}
}
