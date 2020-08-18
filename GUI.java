import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Lab5 {

    private static Connection con;
    private static Statement statement, statement1, statement2, statement3, statement4, statement5, statement6, statement7, statement8, statement9, statement10, statement11, statement12, statement13, statement14, statement15, statement16, statement17, statement18, statement19, statement20, statement21, statement22, statement23, statement24, statement25, statement26, statement27, statement28, statement29, statement30;
    private static ResultSet rs, rs1, rs2, rs3, rs4, rs5, rs6, rs7, rs8, rs9, rs10, rs11, rs12, rs13, rs14, rs15, rs16, rs17, rs18, rs19, rs20, rs21, rs22, rs23, rs24, rs25, rs26, rs27, rs28, rs29, rs30;
    private static int value, value6, tbc, cbc, tbc1, cbc1, tbc2, cbc2, tbc3, cbc3, tbc4, cbc4, tbc5, cbc5;
    private static String value1, value2, value3, value4, value5, value7, value8, value9, value10, value11;
    private static boolean flag;

    public static void main(String args[]) {

        try {

            con = null;
            // Register the JDBC driver for MySQL.
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Define URL of database server for
            // database named 'user' on the faure.
                String url =
                        "jdbc:mysql://faure/abi3896?serverTimezone=UTC";

            /*String url =
                    "jdbc:mysql://localhost:8080/abi3896?serverTimezone=UTC";*/

            // Get a connection to the database for a
            // user named 'user' with the password
            // 123456789.
            con = DriverManager.getConnection(
                    url, "abi3896", "832565063");

            // Display URL and connection information
            System.out.println("URL: " + url);
            System.out.println("Connection: " + con);
            System.out.println();

            statement = con.createStatement();
            statement1 = con.createStatement();
            statement2 = con.createStatement();
            statement3 = con.createStatement();
            statement4 = con.createStatement();
            statement5 = con.createStatement();
            statement6 = con.createStatement();
            statement7 = con.createStatement();
            statement8 = con.createStatement();
            statement9 = con.createStatement();
            statement10 = con.createStatement();
            statement11 = con.createStatement();
            statement12 = con.createStatement();
            statement13 = con.createStatement();
            statement14 = con.createStatement();
            statement15 = con.createStatement();
            statement16 = con.createStatement();
            statement17 = con.createStatement();
            statement18 = con.createStatement();
            statement19 = con.createStatement();
            statement20 = con.createStatement();
            statement21 = con.createStatement();
            statement22 = con.createStatement();
            statement23 = con.createStatement();
            statement24 = con.createStatement();
            statement25 = con.createStatement();
            statement26 = con.createStatement();
            statement27 = con.createStatement();
            statement28 = con.createStatement();
            statement29 = con.createStatement();
            statement30 = con.createStatement();


        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
        }//end catch


        do {
            flag = false;
            try {

                System.out.println("Asking for MemberID");

                // Get the value
                value = Integer.parseInt(JOptionPane.showInputDialog("Enter a MemberID:"));

                System.out.println("Got MemberID: " + value);
                System.out.println();

                JOptionPane.showMessageDialog(null, "Got MemberID: " + value);

                rs = statement.executeQuery("SELECT * FROM Member where MemberID="
                        + value
                );
                if (rs.next()) {

                    JOptionPane.showMessageDialog(null, "Member there, MemberID is " + value);
                } else {
                    value1 = JOptionPane.showInputDialog("Member not there, do you want to add (Yes/No)?");
                    if (value1.equals("YES") || value1.equals("yes") || value1.equals("Y") || value1.equals("y") || value1.equals("Yes")) {
                        value2 = JOptionPane.showInputDialog("Enter First Name:");
                        value3 = JOptionPane.showInputDialog("Enter Last Name:");
                        value4 = JOptionPane.showInputDialog("Enter Gender:");
                        value5 = JOptionPane.showInputDialog("Enter DOB (MM/DD/YYYY):");

                        System.out.println("Got First Name: " + value2);
                        System.out.println("Got Last Name: " + value3);
                        System.out.println("Got Gender: " + value4);
                        System.out.println("Got DOB: " + value5);

                        statement1.executeUpdate("INSERT INTO Member VALUES("
                                + value + ",'"
                                + value2 + "','"
                                + value3 + "','"
                                + value5 + "','"
                                + value4 + "')"
                        );

                        System.out.println("New Member added");
                        System.out.println();

                        JOptionPane.showMessageDialog(null, "New MemberID: " + value + " added");


                    }
                }
                value6 = Integer.parseInt(JOptionPane.showInputDialog("Enter number option (1 or 2 or 3):\n1. ISBN\n2. Book Title\n3. Author"));
                if (value6 == 1) {
                    value7 = JOptionPane.showInputDialog("Enter ISBN (XX-XXXXX-XXXXX):");
                    System.out.println("Got ISBN: " + value7);
                    rs1 = statement2.executeQuery("SELECT * FROM Book where ISBN='"
                            + value7 + "'");
                    if (rs1.next()) {
                        System.out.println("ISBN: " + rs1.getString("ISBN"));
                        System.out.println("Book Title: " + rs1.getString("Title"));
                        System.out.println("Year: " + rs1.getInt("Year_Published"));
                        System.out.println();

                        rs2 = statement3.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                + value7 + "' and Checkin_Date is NULL group by Book_ISBN");
                        if (rs2.next()) {
                            System.out.println("ISBN: " + rs2.getString("Book_ISBN"));
                            System.out.println("Count: " + rs2.getInt("count(*)"));
                            System.out.println();
                            cbc = rs2.getInt("count(*)");
                        }

                        rs3 = statement4.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                + value7 + "' group by Book_ISBN");
                        if (rs3.next())
                            tbc = rs3.getInt("sum(No_of_Copies)");
                        else
                            tbc = 0;
                        if (tbc - cbc != 0) {
                            rs4 = statement5.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                    + value7 + "'");
                            while (rs4.next()) {
                                System.out.println("Library: " + rs4.getString("Library_Name"));
                                System.out.println("Shelf: " + rs4.getInt("Shelf"));
                                System.out.println();

                                JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs4.getString("Library_Name") + "\n2. Shelf: " + rs4.getInt("Shelf"));
                            }

                        } else {
                            System.out.println("All the copies are currently checked out");
                            System.out.println();
                            JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                        }

                    } else {
                        System.out.println("Book not in stock in both the libraries");
                        System.out.println();

                        JOptionPane.showMessageDialog(null, "Book not in stock in both the libraries");

                    }
                } else if (value6 == 2) {
                    value9 = JOptionPane.showInputDialog("Enter Book Title:");
                    rs5 = statement6.executeQuery("SELECT count(*) FROM Book where Title like '%"
                            + value9 + "%'");
                    if (rs5.next()) {
                        System.out.println("Count: " + rs5.getInt("count(*)"));
                        System.out.println();
                        if (rs5.getInt("count(*)") > 1) {

                            rs10 = statement11.executeQuery("SELECT * FROM Book where Title like '%"
                                    + value9 + "%'");
                            while (rs10.next()) {
                                System.out.println("Title: " + rs10.getString("Title"));
                                System.out.println();

                                value10 = JOptionPane.showInputDialog("Is this the book you want to select (Yes/No)? " + rs10.getString("Title"));
                                if (value10.equals("YES") || value10.equals("yes") || value10.equals("Y") || value10.equals("y") || value10.equals("Yes")) {
                                    JOptionPane.showMessageDialog(null, "Selected Book is " + rs10.getString("Title"));

                                    System.out.println("ISBN: " + rs10.getString("ISBN"));
                                    System.out.println("Book Title: " + rs10.getString("Title"));
                                    System.out.println("Year: " + rs10.getInt("Year_Published"));
                                    System.out.println();

                                    rs11 = statement12.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                            + rs10.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                                    if (rs11.next()) {
                                        System.out.println("ISBN: " + rs11.getString("Book_ISBN"));
                                        System.out.println("Count: " + rs11.getInt("count(*)"));
                                        System.out.println();
                                        cbc2 = rs11.getInt("count(*)");
                                    }

                                    rs12 = statement13.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                            + rs10.getString("ISBN") + "' group by Book_ISBN");
                                    if (rs12.next())
                                        tbc2 = rs12.getInt("sum(No_of_Copies)");
                                    else
                                        tbc2 = 0;
                                    if (tbc2 - cbc2 != 0) {
                                        rs13 = statement14.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                                + rs10.getString("ISBN") + "'");
                                        while (rs13.next()) {
                                            System.out.println("Library: " + rs13.getString("Library_Name"));
                                            System.out.println("Shelf: " + rs13.getInt("Shelf"));
                                            System.out.println();

                                            JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs13.getString("Library_Name") + "\n2. Shelf: " + rs13.getInt("Shelf"));
                                        }

                                    } else {
                                        System.out.println("All the copies are currently checked out");
                                        System.out.println();
                                        JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                                    }
                                }
                            }


                        } else {
                            rs6 = statement7.executeQuery("SELECT * FROM Book where Title like '%"
                                    + value9 + "%'");
                            rs6.next();
                            JOptionPane.showMessageDialog(null, "Selected Book is " + rs6.getString("Title"));

                            System.out.println("ISBN: " + rs6.getString("ISBN"));
                            System.out.println("Book Title: " + rs6.getString("Title"));
                            System.out.println("Year: " + rs6.getInt("Year_Published"));
                            System.out.println();

                            rs7 = statement8.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                    + rs6.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                            if (rs7.next()) {
                                System.out.println("ISBN: " + rs7.getString("Book_ISBN"));
                                System.out.println("Count: " + rs7.getInt("count(*)"));
                                System.out.println();
                                cbc1 = rs7.getInt("count(*)");
                            }

                            rs8 = statement9.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                    + rs6.getString("ISBN") + "' group by Book_ISBN");
                            if (rs8.next())
                                tbc1 = rs8.getInt("sum(No_of_copies)");
                            else
                                tbc1 = 0;
                            if (tbc1 - cbc1 != 0) {
                                rs9 = statement10.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                        + rs6.getString("ISBN") + "'");
                                while (rs9.next()) {
                                    System.out.println("Library: " + rs9.getString("Library_Name"));
                                    System.out.println("Shelf: " + rs9.getInt("Shelf"));
                                    System.out.println();

                                    JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs9.getString("Library_Name") + "\n2. Shelf: " + rs9.getInt("Shelf"));
                                }

                            } else {
                                System.out.println("All the copies are currently checked out");
                                System.out.println();
                                JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                            }


                        }
                    } else {
                        System.out.println("No such book title in the library");
                        System.out.println();

                        JOptionPane.showMessageDialog(null, "No such book title in the library");
                    }

                    while (rs5.next()) {
                        System.out.println("Title: " + rs5.getString("Title"));
                    }

                } else {

                    value11 = JOptionPane.showInputDialog("Enter Author Name (First or Last):");
                    rs14 = statement15.executeQuery("SELECT count(*) FROM Author where First_name like'%"
                            + value11 + "%' or last_name like '%"
                            + value11 + "%'"
                    );
                    if(rs14.next())
                    {
                        if (rs14.getInt("count(*)") > 1) {


                            rs10 = statement11.executeQuery("SELECT * FROM Author where First_name like'%"
                                    + value11 + "%' or last_name like '%"
                                    + value11 + "%'"
                            );

                            while (rs10.next()) {
                                System.out.println("Author is : " + rs10.getString("First_name") + " " + rs10.getString("last_name"));
                                System.out.println();

                                value10 = JOptionPane.showInputDialog("Is this the Author you want to select (Yes/No)? " + rs10.getString("First_name") + " " + rs10.getString("last_name"));
                                if (value10.equals("YES") || value10.equals("yes") || value10.equals("Y") || value10.equals("y") || value10.equals("Yes")) {
                                    JOptionPane.showMessageDialog(null, "Selected Author is " + rs10.getString("First_name") + " " + rs10.getString("last_name"));

                                    rs16 = statement17.executeQuery("SELECT count(*) FROM Written_By where Author_AuthorID="
                                            + rs10.getInt("AuthorID")
                                    );
                                    rs16.next();
                                    if (rs16.getInt("count(*)") > 1) {

                                        rs29 = statement30.executeQuery("SELECT * FROM Written_By where Author_AuthorID="
                                                + rs10.getInt("AuthorID")
                                        );
                                        rs29.next();

                                        while(rs29.next())
                                        {
                                            rs28 = statement11.executeQuery("SELECT * FROM Book where ISBN='"
                                                    + rs29.getString("Book_ISBN")+"'");
                                            while (rs28.next()) {
                                                System.out.println("ISBN: " + rs28.getString("ISBN"));
                                                System.out.println();

                                                value10 = JOptionPane.showInputDialog("Is this the book you want to select (Yes/No)? " + rs28.getString("Title"));
                                                if (value10.equals("YES") || value10.equals("yes") || value10.equals("Y") || value10.equals("y") || value10.equals("Yes")) {
                                                    JOptionPane.showMessageDialog(null, "Selected Book is " + rs28.getString("Title"));

                                                    System.out.println("ISBN: " + rs28.getString("ISBN"));
                                                    System.out.println("Book Title: " + rs28.getString("Title"));
                                                    System.out.println("Year: " + rs28.getInt("Year_Published"));
                                                    System.out.println();

                                                    rs11 = statement12.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                                            + rs28.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                                                    if (rs11.next()) {
                                                        System.out.println("ISBN: " + rs11.getString("Book_ISBN"));
                                                        System.out.println("Count: " + rs11.getInt("count(*)"));
                                                        System.out.println();
                                                        cbc2 = rs11.getInt("count(*)");
                                                    }

                                                    rs12 = statement13.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                                            + rs28.getString("ISBN") + "' group by Book_ISBN");
                                                    if (rs12.next())
                                                        tbc2 = rs12.getInt("sum(No_of_Copies)");
                                                    else
                                                        tbc2 = 0;
                                                    if (tbc2 - cbc2 != 0) {
                                                        rs13 = statement14.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                                                + rs28.getString("ISBN") + "'");
                                                        while (rs13.next()) {
                                                            System.out.println("Library: " + rs13.getString("Library_Name"));
                                                            System.out.println("Shelf: " + rs13.getInt("Shelf"));
                                                            System.out.println();

                                                            JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs13.getString("Library_Name") + "\n2. Shelf: " + rs13.getInt("Shelf"));
                                                        }

                                                    } else {
                                                        System.out.println("All the copies are currently checked out");
                                                        System.out.println();
                                                        JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                                                    }
                                                }
                                            }
                                        }




                                    } else {
                                        rs17 = statement18.executeQuery("SELECT * FROM Written_By where Author_AuthorID="
                                                + rs15.getInt("AuthorID")
                                        );
                                        rs17.next();
                                        JOptionPane.showMessageDialog(null, "Selected Author is " + rs16.getString("First_name") + " " + rs16.getString("last_name"));

                                        rs18 = statement19.executeQuery("SELECT * FROM Book where ISBN="
                                                + rs17.getString("Book_ISBN")
                                        );
                                        rs18.next();
                                        JOptionPane.showMessageDialog(null, "Selected Book is " + rs18.getString("Title"));

                                        System.out.println("ISBN: " + rs18.getString("ISBN"));
                                        System.out.println("Book Title: " + rs18.getString("Title"));
                                        System.out.println("Year: " + rs18.getInt("Year_Published"));
                                        System.out.println();

                                        rs19 = statement20.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                                + rs18.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                                        if (rs19.next()) {
                                            System.out.println("ISBN: " + rs19.getString("ISBN"));
                                            System.out.println("Count: " + rs19.getInt("count(*)"));
                                            System.out.println();
                                            cbc3 = rs19.getInt("count(*)");
                                        }

                                        rs20 = statement21.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                                + rs18.getString("ISBN") + "' group by Book_ISBN");
                                        if (rs20.next())
                                            tbc3 = rs20.getInt("sum(No_of_Copies)");
                                        else
                                            tbc3 = 0;
                                        if (tbc3 - cbc3 != 0) {
                                            rs21 = statement22.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                                    + rs18.getString("ISBN") + "'");
                                            while (rs21.next()) {
                                                System.out.println("Library: " + rs21.getString("Library_Name"));
                                                System.out.println("Shelf: " + rs21.getInt("Shelf"));
                                                System.out.println();

                                                JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs21.getString("Library_Name") + "\n2. Shelf: " + rs21.getInt("Shelf"));
                                            }

                                        } else {
                                            System.out.println("All the copies are currently checked out");
                                            System.out.println();
                                            JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                                        }
                                    }


                                }
                            }


                        } else {
                            rs15 = statement16.executeQuery("SELECT * FROM Author where First_name like'%"
                                    + value11 + "%' or last_name like '%"
                                    + value11 + "%'"
                            );
                            rs15.next();
                            rs16 = statement17.executeQuery("SELECT count(*) FROM Written_By where Author_AuthorID="
                                    + rs15.getInt("AuthorID")
                            );
                            rs16.next();
                            if (rs16.getInt("count(*)") > 1) {

                                rs29 = statement30.executeQuery("SELECT * FROM Written_By where Author_AuthorID="
                                        + rs15.getInt("AuthorID")
                                );
                                while(rs29.next())
                                {
                                    rs10 = statement11.executeQuery("SELECT * FROM Book where ISBN='"
                                            + rs29.getString("Book_ISBN")+"'");
                                    while (rs10.next()) {
                                        System.out.println("ISBN: " + rs10.getString("ISBN"));
                                        System.out.println();

                                        value10 = JOptionPane.showInputDialog("Is this the book you want to select (Yes/No)? " + rs10.getString("Title"));
                                        if (value10.equals("YES") || value10.equals("yes") || value10.equals("Y") || value10.equals("y") || value10.equals("Yes")) {
                                            JOptionPane.showMessageDialog(null, "Selected Book is " + rs10.getString("Title"));

                                            System.out.println("ISBN: " + rs10.getString("ISBN"));
                                            System.out.println("Book Title: " + rs10.getString("Title"));
                                            System.out.println("Year: " + rs10.getInt("Year_Published"));
                                            System.out.println();

                                            rs11 = statement12.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                                    + rs10.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                                            if (rs11.next()) {
                                                System.out.println("ISBN: " + rs11.getString("Book_ISBN"));
                                                System.out.println("Count: " + rs11.getInt("count(*)"));
                                                System.out.println();
                                                cbc2 = rs11.getInt("count(*)");
                                            }

                                            rs12 = statement13.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                                    + rs10.getString("ISBN") + "' group by Book_ISBN");
                                            if (rs12.next())
                                                tbc2 = rs12.getInt("sum(No_of_Copies)");
                                            else
                                                tbc2 = 0;
                                            if (tbc2 - cbc2 != 0) {
                                                rs13 = statement14.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                                        + rs10.getString("ISBN") + "'");
                                                while (rs13.next()) {
                                                    System.out.println("Library: " + rs13.getString("Library_Name"));
                                                    System.out.println("Shelf: " + rs13.getInt("Shelf"));
                                                    System.out.println();

                                                    JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs13.getString("Library_Name") + "\n2. Shelf: " + rs13.getInt("Shelf"));
                                                }

                                            } else {
                                                System.out.println("All the copies are currently checked out");
                                                System.out.println();
                                                JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                                            }
                                        }
                                    }
                                }





                            } else {
                                rs17 = statement18.executeQuery("SELECT * FROM Written_By where Author_AuthorID="
                                        + rs15.getInt("AuthorID")
                                );
                                rs17.next();
                                JOptionPane.showMessageDialog(null, "Selected Author is " + rs15.getString("First_name") + " " + rs15.getString("last_name"));

                                rs18 = statement19.executeQuery("SELECT * FROM Book where ISBN='"
                                        + rs17.getString("Book_ISBN")+"'"
                                );
                                rs18.next();
                                JOptionPane.showMessageDialog(null, "Selected Book is " + rs18.getString("Title"));

                                System.out.println("ISBN: " + rs18.getString("ISBN"));
                                System.out.println("Book Title: " + rs18.getString("Title"));
                                System.out.println("Year: " + rs18.getInt("Year_Published"));
                                System.out.println();

                                rs19 = statement20.executeQuery("SELECT Book_ISBN,count(*) FROM Borrowed_By where Book_ISBN='"
                                        + rs18.getString("ISBN") + "' and Checkin_Date is NULL group by Book_ISBN");
                                if (rs19.next()) {
                                    System.out.println("ISBN: " + rs19.getString("Book_ISBN"));
                                    System.out.println("Count: " + rs19.getInt("count(*)"));
                                    System.out.println();
                                    cbc3 = rs19.getInt("count(*)");
                                }

                                rs20 = statement21.executeQuery("SELECT Book_ISBN,sum(No_of_Copies) FROM Located_at where Book_ISBN='"
                                        + rs18.getString("ISBN") + "' group by Book_ISBN");
                                if (rs20.next())
                                    tbc3 = rs20.getInt("sum(No_of_Copies)");
                                else
                                    tbc3 = 0;
                                if (tbc3 - cbc3 != 0) {
                                    rs21 = statement22.executeQuery("SELECT Library_Name,Shelf FROM Located_at where Book_ISBN='"
                                            + rs18.getString("ISBN") + "'");
                                    while (rs21.next()) {
                                        System.out.println("Library: " + rs21.getString("Library_Name"));
                                        System.out.println("Shelf: " + rs21.getInt("Shelf"));
                                        System.out.println();

                                        JOptionPane.showMessageDialog(null, "Book Details:\n1. Library: " + rs21.getString("Library_Name") + "\n2. Shelf: " + rs21.getInt("Shelf"));
                                    }

                                } else {
                                    System.out.println("All the copies are currently checked out");
                                    System.out.println();
                                    JOptionPane.showMessageDialog(null, "All the copies are currently checked out");
                                }
                            }
                        }
                    }
                    else{
                        System.out.println("Author Not There in database");
                        System.out.println();
                    }


                }

                value8 = JOptionPane.showInputDialog("Do you want to continue from start (Yes/No)?");
                if (value8.equals("YES") || value8.equals("yes") || value8.equals("Y") || value8.equals("y") || value8.equals("Yes")) {
                    flag = true;
                }


            } catch (Exception e) {
                e.printStackTrace();
                System.out.println();
            }//end catch
        } while (flag);

    }//end main
}
