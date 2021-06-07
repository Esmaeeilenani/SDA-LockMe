
import java.io.File;
import java.io.IOException;
import java.util.*;

public class NewMain {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        //while loop to run the program
        while (true) {
            try {

                //Dispaly Main menu
                MainMenu();
                String choice = in.next();

                switch (choice) {
                    case "1":
                        ListFiles(in);
                        break;
                    case "2":

                        SubMenuFunc(in);
                        break;
                    case "3":
                        System.out.println("\n\nThank you for Using our App\n"
                                + "Contact Us:\n"
                                + "Email:Esmaeeilenani@gmail.com");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Wrong Selection Please try Again\n");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    //MainMenu Content
    public static void MainMenu() {
        System.out.println("*****************************************\n");
        System.out.println("Welcome to LockedMe System where you can track your files with all comfort"
                + "\n\nPowered by: Esmaeeil enani\n");
        System.out.println("*****************************************\n");
        System.out.println("Please Select from the menu below:");
        System.out.println("1.  List all the File in a Directory");
        System.out.println("2.  Directory Operations Menu");
        System.out.println("3.  Exite");
        System.out.print("Enter number: ");
    }
    //--------------------------------------------------------------------------

    //SubMenu Content
    public static void SubMenu() {
        System.out.println("\nPlease Select from the menu below:");
        System.out.println("1.  Add new File");
        System.out.println("2.  Delete a File");
        System.out.println("3.  Search for a file");
        System.out.println("4.  Return to MainMenu");
        System.out.print("Enter number: ");
    }
    //--------------------------------------------------------------------------

    //subMenu Functions
    public static void SubMenuFunc(Scanner in) throws IOException {
        while (true) {
            SubMenu();
            String choice = in.next();
            switch (choice) {

                case "1":
                    AddNewFile(in);
                    break;
                case "2":
                    DeleteFile(in);
                    break;
                case "3":
                    FindFile(in);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Wrong Selection Please try Again\n");
            }

        }
    }

    //Add new File to Directory
    public static void AddNewFile(Scanner in) throws IOException {
        File f = DirChecks(in);

        //if the file doesn't pass the checks exit the method 
        if (f == null) {
            return;
        }

        System.out.print("Enter the File name: ");
        String fileName = in.nextLine();

        new File(f.getAbsoluteFile() + "\\" + fileName).createNewFile();
        System.out.println("File Created Successfully");

    }
    //--------------------------------------------------------------------------

    //Delete File from Directory
    public static void DeleteFile(Scanner in) {
        File f = DirChecks(in);

        //if the file doesn't pass the checks exit the method 
        if (f == null) {
            return;
        }
        System.out.print("Enter the File name you want to delete: ");
        String fName = in.nextLine();

        File FileToDelete = SearchFile(f.listFiles(), fName);

        if (FileToDelete == null) {
            System.out.println("No File with this name: " + fName);
            return;
        }

        if (!FileToDelete.delete()) {
            System.out.println("The directory Should be empty to be deleted");
            return;
        }
        System.out.println("File Successfully Deleted\n");
    }
    //--------------------------------------------------------------------------

    //Find File in Directory
    public static void FindFile(Scanner in) {
        File f = DirChecks(in);

        //if the file doesn't pass the checks exit the method 
        if (f == null) {
            return;
        }

        System.out.print("Enter the File name you want to Find: ");
        String fName = in.nextLine();

        File FileToFind = SearchFile(f.listFiles(), fName);

        if (FileToFind == null) {
            System.out.println("No File with this name: " + fName);
            return;
        }

        System.out.println("\nFile is Found in the Directory " + FileToFind.getName());

    }

    //--------------------------------------------------------------------------
    //to List all the file and subDirectory 
    public static void ListFiles(Scanner in) {

        File f = DirChecks(in);

        //if the file doesn't pass the checks exit the method 
        if (f == null) {
            return;
        }

        System.out.println("Printing the files of " + f.getName());
        PrintDirectory(f);
        System.out.println("\n");
    }
    //--------------------------------------------------------------------------

    //Printing Func
    public static void PrintDirectory(File f) {
        ArrayList<String> names = new ArrayList<>(Arrays.asList(f.list()));

        //sorting
        Collections.sort(names, (o1, o2) -> o1.compareToIgnoreCase(o2));

        names.forEach(System.out::println);

    }
    //--------------------------------------------------------------------------

    //Directory Checks
    public static File DirChecks(Scanner in) {

        System.out.print("Enter the Directory Path: ");
        in.nextLine();
        String DrPath = in.nextLine();
        File f = new File(DrPath);

        if (!f.exists()) {
            System.out.println("Sorry the Directory is not Found!!\n");
            return null;
        }

        if (!f.isDirectory()) {
            System.out.println("Sorry this is not a Directory path (Only Directory path Allowed)!!\n");
            return null;
        }
        return f;
    }

    //Search for File
    public static File SearchFile(File[] files, String fileName) {
        for (File file : files) {
            if (file.getName().equalsIgnoreCase(fileName)) {
                return file;
            }
        }
        return null;
    }
    //--------------------------------------------------------------------------

}
