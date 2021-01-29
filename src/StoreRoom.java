/*
MADE BY AKBARBEK RAKHMATULLAEV
ID: U1910101
GROUP:19-03
With support of Saidkarim Yuldashev
 */



import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.Scanner;

public class StoreRoom {
    //GLOBAL VARS
    private static String filePath = System.getProperty("C:/Programs/storeRoom.xlsx");
    private static String FILE_NAME = "storeRoom.xlsx";


    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("\t\t\tWelcome to Auto-parts store!!!");


        System.out.println("Please select any preffered option you want");
        //VARS
        int allRows;

        Workbook wb = null;
        Sheet sheet1;
        try
        {
            FileInputStream fin = new FileInputStream(new File(FILE_NAME));
            wb = WorkbookFactory.create(fin);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if(wb.getNumberOfSheets()==0)
        {
            sheet1 = wb.createSheet("Invoices");
            try
            {
                GenerateEx(sheet1, wb);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            sheet1 = wb.getSheetAt(0);
            try
            {
                GenerateEx(sheet1, wb);
            } catch (IOException e)
            {
                e.printStackTrace();
            }
        }

            //CONStruCTOR



        //END OF VARS


        //menu driven template
        while(true){
            System.out.println("\nInput 1. Find ");
            System.out.println("Input 2. Show ");
            System.out.println("Input 3. Add a product ");
            System.out.println("Input 4. Sell \n");
            System.out.println("Input 0. Exit ");

            int choice = scan.nextInt();//accept user input

            switch(choice){
                case 1:
                    try
                    {
                        Search(sheet1, wb);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    //method call or logic for case 1

                    break;
                case 2:
                    //method call or logic for case 2
                    XSSFWorkbook wbread = null;
                    try
                    {
                        wbread = new XSSFWorkbook(new FileInputStream(new File(FILE_NAME)));
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    Sheet sheetread = wbread.getSheetAt(0);
                    allRows = sheetread.getLastRowNum();
                    for(int i =0; i<=allRows;i++)
                    {
                        System.out.print(sheet1.getRow(i).getCell(0));
                        System.out.print(sheet1.getRow(i).getCell(1));
                        System.out.print(sheet1.getRow(i).getCell(2));
                        System.out.print(sheet1.getRow(i).getCell(3));
                    }
                    break;
                case 3:
                    Write(sheet1,wb);
                   // method call or logic for case 3
                    break;
                case 4:
                    //method call or logic for case 4
                    try
                    {
                        Delete(sheet1, wb);
                    } catch (IOException e)
                    {
                        e.printStackTrace();
                    }
                    break;
                case 0: System.out.println("Exiting the application");
                    System.exit(0);
                default: System.out.println("Incorrect input!!! Please re-enter choice from our menu");
            }
        }

    }
    public static void Write(Sheet sheet1, Workbook wb)
    {
        FileInputStream fin3 = null;
        try
        {
            fin3 = new FileInputStream(new File(FILE_NAME));
            Workbook wbread3 = new XSSFWorkbook(fin3);
            Sheet sheetread = wbread3.getSheetAt(0);
            int allRows = sheetread.getLastRowNum();
            for (int i = 0; i <= allRows; i++)
            {
                String rowString1 = String.valueOf(sheetread.getRow(i).getCell(0));
                String rowString2 = String.valueOf(sheetread.getRow(i).getCell(1));
                String rowString3 = String.valueOf(sheetread.getRow(i).getCell(2));
                String rowString4 = String.valueOf(sheetread.getRow(i).getCell(3));
                sheet1.getRow(i).getCell(0).setCellValue(rowString1);
                sheet1.getRow(i).getCell(1).setCellValue(rowString2);
                sheet1.getRow(i).getCell(2).setCellValue(rowString3);
                sheet1.getRow(i).getCell(3).setCellValue(rowString4);
            }

            Scanner scanIn = new Scanner(System.in);

            System.out.print("\n\nEnter part name: ");
            String partName = "\n" + scanIn.nextLine() + "\t\t\t";
            int partID = -1;
            float importCost = -1;
            float sellingCost = -1;
            while (importCost < 0 || sellingCost < 0 || partID <0)
            {
                System.out.print("Enter part id[in Numbers]: ");
                 partID = scanIn.nextInt();
                System.out.print("Enter part import Cost: ");
                importCost = scanIn.nextFloat();
                System.out.print("Enter part selling Cost: ");
                sellingCost = scanIn.nextFloat();
            }
            allRows = sheet1.getLastRowNum() + 1;
            Row row = sheet1.createRow(allRows);
            row.createCell(0).setCellValue(partName + "\t\t\t");
            row.createCell(1).setCellValue(partID + "\t\t\t");
            row.createCell(2).setCellValue(importCost + "\t\t\t");
            row.createCell(3).setCellValue(sellingCost+"\t\t\t");
            // FileOutputStream fout = new FileOutputStream(new File("C:/Programs/storeRoom.xlsx"), true);
            FileOutputStream fout = new FileOutputStream(new File(FILE_NAME));
            wb.write(fout);
            fout.flush();
            fout.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void GenerateEx(Sheet sheet1, Workbook workbook) throws IOException
    {
        Row initialRow = sheet1.createRow(0);
        initialRow.createCell(0).setCellValue("NAME\t\t\t\t");
        initialRow.createCell(1).setCellValue("ID\t\t\t\t");
        initialRow.createCell(2).setCellValue("importCOST\t\t\t\t");
        initialRow.createCell(3).setCellValue("sellingCOST\t\t\t\t");

        FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
        workbook.write(fileOutputStream);
        fileOutputStream.close();
    }

    public static void Delete(Sheet sheet1, Workbook workbook) throws IOException
    {
        System.out.print("\nEnter Item ID To Delete[In numbers]: ");
        Scanner scanner = new Scanner(System.in);
        int itemIDCheck = scanner.nextInt();
        int itemIDHelp;

        int searchRow = sheet1.getLastRowNum();
        int endCheck= 1;
        int binaryCheck =0;
        for(int i = 1; i<= searchRow; i++)
        {
            itemIDHelp = (int) Float.parseFloat(String.valueOf(sheet1.getRow(i).getCell(1)));
           if(itemIDHelp == itemIDCheck)
           {
               for(int j=i; j<=searchRow;j++)
               {
                   if(j==searchRow)
                   {
                       Row row1 = sheet1.getRow(j);
                       sheet1.removeRow(row1);
//                       sheet1.getRow(j).getCell(1).setCellValue(null);
//                       sheet1.getRow(j).getCell(2).setCellValue(s3);
//                       sheet1.getRow(j).getCell(3).setCellValue(s4);
                       System.out.print("\nSuccessfully Deleted1!\n");
                   }
                       else
                   {
                       String s1 = String.valueOf(sheet1.getRow(j + 1).getCell(0));
                       String s2 = String.valueOf(sheet1.getRow(j + 1).getCell(1));
                       String s3 = String.valueOf(sheet1.getRow(j + 1).getCell(2));
                       String s4 = String.valueOf(sheet1.getRow(j + 1).getCell(3));
                       sheet1.getRow(j).getCell(0).setCellValue(s1);
                       sheet1.getRow(j).getCell(1).setCellValue(s2);
                       sheet1.getRow(j).getCell(2).setCellValue(s3);
                       sheet1.getRow(j).getCell(3).setCellValue(s4);
                   }

               }
               i = searchRow +7;
               FileOutputStream fileOutputStream = new FileOutputStream(new File(FILE_NAME));
               workbook.write(fileOutputStream);
               fileOutputStream.close();
               System.out.print("\nSuccessfully Deleted!\n");

           }
           endCheck++;
        }
        if(endCheck>searchRow)
            System.out.print("Invalid ID or it doesn't exist!");
    }

    public static void Search(Sheet sheet1, Workbook workbook) throws IOException
    {
        System.out.print("\nEnter Item ID To Search[In numbers]: ");
        Scanner scanner = new Scanner(System.in);
        int itemIDCheck = scanner.nextInt();
        int itemIDHelp;
        int searchRow = sheet1.getLastRowNum();
        int itemEnd = 1;
        for(int i = 1; i<= searchRow; i++)
        {
            itemIDHelp = (int) Float.parseFloat(String.valueOf(sheet1.getRow(i).getCell(1)));
            if(itemIDHelp == itemIDCheck)
            {
                String s1 = String.valueOf(sheet1.getRow(i).getCell(0));
                String s2 = String.valueOf(sheet1.getRow(i).getCell(1));
                String s3 = String.valueOf(sheet1.getRow(i).getCell(2));
                String s4 = String.valueOf(sheet1.getRow(i).getCell(3));
                System.out.print("Item Name: " + s1);
                System.out.print("Item ID: " + s2);
                System.out.print("Item Import Cost: " + s3);
                System.out.print("Item Selling Cost: " + s4);
                i = searchRow+7;
                itemEnd = 7;
            }
        }
        if(itemEnd !=7)
        {
            System.out.print("Invalid ID or it doesn't exist!");
        }
        else
        {
            System.out.print("\nSuccessfully Found!\n");
        }
    }

    public static int NullExceptionHandler(String s)
    {
        String str;
        int ch = 0;
        if (s != null) {
            str =s;
            ch = 1;
        } else {
            str = "String is null";
            ch = 0;
        }
        return ch;
    }

}