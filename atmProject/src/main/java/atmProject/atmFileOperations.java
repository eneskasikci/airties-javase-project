package atmProject;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class atmFileOperations {
    private static String operationDate() {
        Locale locale = new Locale("tr", "TR");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMMM-yyyy HH:mm:ss", locale);
        return simpleDateFormat.format(new Date());
    }

    public static void createDirectoryForAtm() throws atmException {
        File file = new File(atmFilePath.MY_PATH);
        try {
            if (!file.mkdirs()) {
                System.out.println("Directory already exists.");
            } else {
                System.out.println(atmFilePath.MY_PATH + " directory created.");
            }
        } catch (Exception e) {
            throw new atmException(e.toString());
        }
    }

    public static void createALogFile() throws atmException {
        File dataFile = new File(atmFilePath.MY_PATH + "\\atmLog.txt");
        try {
            if (!dataFile.createNewFile()) {
                System.out.println("Log file already exists.");
            } else {
                System.out.println(atmFilePath.MY_PATH + " log file created.");
                FileWriter fileWriter = new FileWriter(dataFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write("Log file created at " + operationDate());
                bufferedWriter.close();
            }
        } catch (Exception e) {
            throw new atmException(e.toString());
        }
    }

    public static void createACustomerFile() throws atmException {
        File dataFile = new File(atmFilePath.MY_PATH + "\\atmCustomer.csv");
        atmCustomerClass customer = new atmCustomerClass("admin@gmail.com", "root", "adming.jpg", 1000);
        try {
            if (!dataFile.createNewFile()) {
                System.out.println("Customer file already exists.");
            } else {
                System.out.println(atmFilePath.MY_PATH + " customer file created.");
                FileWriter fileWriter = new FileWriter(dataFile);
                BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                bufferedWriter.write(customer.getName() + "," + customer.getPassword() + "," + customer.getImage() + "," + customer.getBalance());
                bufferedWriter.close();
            }
        } catch (Exception e) {
            throw new atmException(e.toString());
        }
    }

    public static atmCustomerClass readCustomerFile(String username, String password) {
        atmCustomerClass customer = null;
        try {
            File dataFile = new File(atmFilePath.MY_PATH + "\\atmCustomer.csv");
            FileReader fileReader = new FileReader(dataFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(username) && data[1].equals(password)) {
                    customer = new atmCustomerClass(data[0], data[1], data[2], Double.parseDouble(data[3]));
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return customer;
    }
    public static void writeLog(String log) {
        try {
            File dataFile = new File(atmFilePath.MY_PATH + "\\atmLog.txt");
            FileWriter fileWriter = new FileWriter(dataFile, true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.newLine();
            bufferedWriter.write(log + " [ " + operationDate() + " ]\n----------------------------\n");
            bufferedWriter.close();
        } catch (IOException e) {
            System.out.println(e.toString());
        }
    }
}