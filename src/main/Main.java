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
		boolean finConfig = false;
		writer.write("const config = {");
		writer.newLine();
		while(reader.ready()) {
			currentLine = reader.readLine();
			if(currentLine.equals("function move()")) {	//Gere le const move
				if(!finConfig) {
				writer.write("}");
				writer.newLine();
				}
				writer.write("const move = ["); 
				writer.newLine(); 
			}
			else if(currentLine.equals("function bank()")) { //Gere la function bank
				if(!finConfig) {
					writer.write("}");
					writer.newLine();
					}
				writer.write("const bank = [");
				writer.newLine();
			}
			else if (currentLine.equals("function phenix()")) { //Gere le phenix
				if(!finConfig) {
					writer.write("}");
					writer.newLine();
					}
				writer.write("const phenix = [");
				writer.newLine();
			}
			else if(currentLine.contains("end")) { //Gere les end
				writer.write("]");
				writer.newLine();
			}
			else if(currentLine.length() == 0) {} //Gere ligne vide
			else if(currentLine.contains("function")){ //Gere les customs
				if(!finConfig) {
					writer.write("}");
					writer.newLine();
					}
				writer.write(currentLine);
				writer.newLine();
				while(!currentLine.contains("end")) {
					custom(currentLine, writer);
					writer.newLine();
					currentLine = reader.readLine();
				}
			}
			else if(currentLine.contains("=") && !currentLine.contains("{")){ 
				for(int i = 0; i<currentLine.length(); i++) {
					if(currentLine.charAt(i) == '=') {
						temp += ":";
					} else {
						temp += currentLine.charAt(i);
					}
				}
				temp += ",";
				writer.write(temp);
				writer.newLine();
				temp = "";
			}
			else if(currentLine.contains("{") && !currentLine.contains("return") || currentLine.contains("=") && !currentLine.contains("{")){ //Gere les moves normaux
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
	
	public static void custom(String currentLine, BufferedWriter writer) {
		
	}
}
