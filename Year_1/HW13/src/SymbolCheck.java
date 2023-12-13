import acm.program.ConsoleProgram;

import java.io.*;

public class SymbolCheck extends ConsoleProgram {
	private static final String MY_PATH = "C:\\Users\\Win10\\Desktop\\";

	public void run() {
		this.setSize(600, 400);
		this.setFont("SansSherif-24");
		while (true) {
			int numb = readInt("Enter numb 1 to 5: ");
			this.getConsole().clear();
			switch (numb) {
			case 1: {
				try {
					println("Change substrint to substring");
					findPhraseInFile(readLine("Input file name: "),
							readLine("Input phrase: "),
							readLine("Input new phrase: "));
				} catch (IOException e) {
					println("Input was incorrect");
				}
				println();
				break;
			}
			case 2: {
				try {
					println("File to replace for \".bak\"");
					bakFile(readLine("Input file name: "),
							readLine("Input new file name: "));
				} catch (Exception e) {
					println("Input was incorrect");
				}
				println();
				break;
			}
			case 3: {
				try {
					println("Read file make two.");
					copyFileInTwo(readLine("Input file name: "));
				} catch (IOException e) {
					println("Input was incorrect");
				}
				println();
				break;
			}
			case 4: {
				try {
					println("Encrypt");
					secureFile(readLine("Input file name: "),
							readInt("Input key number: "));
				} catch (IOException e) {
					println("Input was incorrect");
				}
				println();
				break;
			}
			case 5: {
				try {
					println("Decrypt");
					unSecureFile(readLine("Input file name: "),
							readInt("Input key number: "));
				} catch (IOException e) {
					println("Input was incorrect");
				}
				println();
				break;
			}
			case 0:
				return;

			}
		}
	}

	/**
	 * Outout file
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 */
	public String fileOutput(String filename) throws IOException {
		File file = new File(MY_PATH + filename);
		FileReader fileReader = new FileReader(file);
		BufferedReader bf = new BufferedReader(fileReader);
		String res = "";
		String line = bf.readLine();
		while (line != null) {
			res += line + "\n";
			line = bf.readLine();
		}
		return res;
	}

	/**
	 * Change string to string
	 * 
	 * @param filename
	 * @param phrase
	 * @param phraseInstead
	 * @return
	 * @throws IOException
	 */
	public String findPhraseInFile(String filename, String phrase,
			String phraseInstead) throws IOException {
		String fileS = fileOutput(filename);
		int count = 0;
		FileWriter fr = new FileWriter(MY_PATH + filename);
		BufferedWriter bf = new BufferedWriter(fr);
		String r = "";
		while (fileS.indexOf(phrase) != -1) {
			r += fileS.substring(0, fileS.indexOf(phrase));
			r += phraseInstead;
			fileS = fileS.substring(fileS.indexOf(phrase) + phrase.length(),
					fileS.length());
			count++;
		}
		r += fileS;
		String[] ops = r.split("\n");
		for (int i = 0; i < ops.length; i++) {
			bf.write(ops[i]);
			bf.newLine();
		}
		bf.close();
		return "phrase \"" + phrase + "\" was found in this file " + count
				+ " times";
	}

	/**
	 * ".bak" File.
	 * 
	 * @param filename
	 * @param newFileName
	 * @throws IOException
	 */
	public void bakFile(String filename, String newFileName) throws IOException {
		String fileS = fileOutput(filename);
		String[] lines = fileS.split("\n");
		File file = new File(MY_PATH + newFileName + ".bak");
		FileWriter fr = new FileWriter(file);
		BufferedWriter bf = new BufferedWriter(fr);
		for (int i = 0; i < lines.length; i++) {
			bf.write(lines[i]);
			bf.newLine();
		}
		bf.close();
	}

	/**
	 * Make two files
	 * 
	 * @param filename
	 * @throws IOException
	 */
	public void copyFileInTwo(String filename) throws IOException {
		File file = new File(MY_PATH + filename);
		FileReader fileReader = new FileReader(file);
		BufferedReader bf = new BufferedReader(fileReader);
		FileWriter wr1 = new FileWriter(MY_PATH + "1.txt");
		FileWriter wr2 = new FileWriter(MY_PATH + "2.txt");
		BufferedWriter bf1 = new BufferedWriter(wr1);
		BufferedWriter bf2 = new BufferedWriter(wr2);
		String line = bf.readLine();
		int count = 0;
		while (line != null) {
			if (count % 2 == 1) {
				bf1.write(line);
				bf1.newLine();
			} else {
				bf2.write(line);
				bf2.newLine();
			}
			line = bf.readLine();
			count++;
		}
		bf1.close();
		bf2.close();
	}

	/**
	 * Encrypt.
	 * 
	 * @param filename
	 * @param key
	 * @throws IOException
	 */
	public void secureFile(String filename, int key) throws IOException {
		String fl = fileOutput(filename);
		FileWriter wr1 = new FileWriter(MY_PATH + filename);
		BufferedWriter bf1 = new BufferedWriter(wr1);
		String[] ops = fl.split("\n");
		for (int i = 0; i < ops.length; i++) {
			bf1.write(encrypt2(ops[i], key));
			bf1.newLine();
		}
		bf1.close();
	}

	/**
	 * Decrypt
	 * 
	 * @param filename
	 * @param key
	 * @throws IOException
	 */
	public void unSecureFile(String filename, int key) throws IOException {
		String fl = fileOutput(filename);
		FileWriter wr1 = new FileWriter(MY_PATH + filename);
		BufferedWriter bf1 = new BufferedWriter(wr1);
		String[] ops = fl.split("\n");
		for (int i = 0; i < ops.length; i++) {
			bf1.write(encrypt2(ops[i], 26 - key % 26));
			bf1.newLine();
		}
		bf1.close();
	}

	/**
	 * encrypt string with a number key
	 * 
	 * @param input
	 * @param num
	 * @return
	 */
	private static String encrypt2(String input, int num) throws IOException {
		if (num < 0) {
			throw new IOException();
		}
		String result = "";
		int len = input.length();
		for (int i = 0; i < len; i++) {
			if (input.charAt(i)==' ')
            {
				result+=input.charAt(i);
            }
			else if (Character.isUpperCase(input.charAt(i)))
            {
                char ch = (char)(((int)input.charAt(i) +
                		num - 65) % 26 + 65);
                result+=ch;
            }
            else
            {
                char ch = (char)(((int)input.charAt(i) +
                		num - 97) % 26 + 97);
                result+=ch;
            }
		}
		return result;
	}


}