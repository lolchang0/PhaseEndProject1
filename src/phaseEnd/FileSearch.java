package phaseEnd;

import java.util.Scanner;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileSearch {
	public static void main(String[] args) {
		
		//ArrayList<String> fileList = new ArrayList<String>();
		
		//Creating String array to store file names
		String[] fileNames;
		
		//Creating scanners to take inputs
		Scanner scan = new Scanner(System.in);
		Scanner scanFile = new Scanner(System.in);
		Scanner scanString = new Scanner(System.in);
		
		//Welcome message
		System.out.println("Developer: Kevin Chang");
		System.out.println("Phase End Project 1");
		
		//Initializing variables
		char choice1, choice2;
			
		//Do-while loop to repeat the code until the user prompts for exit
		do {
			
			//Main menu screen
			System.out.println("\nMain Manu: ");
			System.out.println("1. Display current files");
			System.out.println("2. Edit the file directory");
			System.out.println("3. Exit the Program");
			System.out.print("Please select an option: ");
			
			//Obtaining first user input
			choice1 = scan.next().charAt(0);
			
			//Switch case for input choice1
			switch(choice1) {
			//Return directory of current files in ascending order
			case '1':
				//Using my current project directory as an example, can be changed to whichever directory to add, delete, or search files
				File f = new File("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\");
				fileNames = f.list();
				
				//If no files are found return message
				if(fileNames.length == 0) {
					System.out.println("Currently no files in the directory");
					break;
				}
				//Return list of file names in current directory
				else {
					//enhanced for loop to print out file names
					for(String fileName: fileNames) {
						System.out.println(fileName);
					}
					break;
				}
			//Go to second menu screen to see directory editing options
			case '2':
				System.out.println("\nDirectory editing options: ");
				System.out.println("1. Add a file");
				System.out.println("2. Delete a file");
				System.out.println("3. Search for a specific file");
				System.out.println("4. Return to main menu");
				System.out.print("Please select an option: ");
				
				//user input for choice2
				choice2 = scan.next().charAt(0);
				
				//switch case for second menu choice
				switch(choice2) {
				
				//add file
				case '1':
					char choice3;
					System.out.print("\nPlease input file name to add: ");
					String inputF = scanFile.nextLine();
					try {
						File myFile = new File("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\" + inputF);
						if(myFile.createNewFile()) {
							//fileList.add(myFile.getName());
							do {
								System.out.println("Would you like to add text to " + inputF + "? (Y/N): ");
								choice3 = scan.next().charAt(0);
								
								switch(Character.toLowerCase(choice3)) {
								case 'y':
									try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\" + inputF, true))){
										
										//enter a name
										System.out.print("Please enter text: ");
										String input = scanString.nextLine();
										//creates a new line for the new name
										bw.newLine();
										bw.write(input);
										
										//close the BufferedWriter
										bw.close();
										
									//Exception Handling
									} catch(FileNotFoundException e){
										e.printStackTrace();
									} catch(IOException e) {
										e.printStackTrace();
									}
									break;
								case 'n':
									break;
								default:
									System.out.println("Invalid Option");
									break;
								}
							}while(Character.toLowerCase(choice3) != 'y' && Character.toLowerCase(choice3) != 'n');
							System.out.println("File " + myFile.getName() + " has been successfully added");
						}
						else {
							System.out.println("File Already Exists");
						}
					} catch(IOException e) {
						System.out.println("An error has occurred");
						e.printStackTrace();
					}
					break;
					
				//delete file
				case '2':
					System.out.print("\nPlease input file name to delete: ");
					inputF = scanFile.nextLine();
					File myFile = new File("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\" + inputF);
					if(myFile.delete()) {
						//fileList.remove(myFile.getName());
						//System.out.println(filedirectory.size());
						System.out.println("File " + myFile.getName() + " has been successfully deleted");
					}
					else {
						System.out.println("Failed to delete file");
					}
					break;
				
				//search for file
				case '3':
					System.out.print("\nPlease input file name to search: ");
					inputF = scanFile.nextLine();
					File findFile;
					findFile = new File("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\" + inputF);
					boolean exists = findFile.exists();
					if(exists == true) {
						System.out.println("\nFile " + inputF + " exists in the directory");
						try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\Kevin Chang\\eclipse-workspace-phase2\\PhaseEndProject1\\src\\phaseEnd\\" + inputF))){
							
							String line = br.readLine();
							if(line == null) {
								System.out.println("\nNo contents found in file");
							}
							else {
								System.out.println("Contents of " + inputF + ":");
								while(line != null) {
									System.out.println(line);
									line = br.readLine();
								}
							}		
						//Exception Handling
						} catch(FileNotFoundException e) {
							e.printStackTrace();
						} catch(IOException e) {
							e.printStackTrace();
						}
					}
					else {
						System.out.println("\nFile " + inputF + " was not found in the directory");
					}
					break;
					
				//return to main menu screen
				case '4':
					System.out.println("Returning the main menu screen");
					break;
					
				//to cover for invalid option inputs
				default:
					System.out.println("Invalid Option, returning to main menu screen");
					break;
				}
				break;
			
			//exit the program
			case '3':
				System.out.println("You have successfully exited the program.");
				System.exit(0);
				
			//to cover invalid option inputs
			default:
				System.out.println("Invalid Option");
				break;
			}
		}while(choice1 != '3');
	}
}

/*To-do list:
 *Option 1 display files (done)
 *Option 2.1 add (done)
 *Option 2.1.1 add option to add text to file? (done)
 *Option 2.2 delete (done)
 *Option 2.3 find (done)
 *Option 2.4 return (done)
 *Option 3 exit program (done)
*/
