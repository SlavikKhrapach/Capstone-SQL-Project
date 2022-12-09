/**
 * Slavik Khrapach
 *
 */

import java.sql.*;

public class SelectApp {

    /**
     * Connect to the capstone.db database
     * @return the Connection object
     */
    private static Connection connect() {
        // SQLite connection string
        String url = "jdbc:sqlite:/C:/Stuff/College/SDEV280/CapstoneProject/database/capstone.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }


    /**
     * Print out the first 10 rows from schools table
     */
    public static void inspectingTheData() throws SQLException {

        System.out.println("1. inspectingTheData\n" +
                "\n" +
                "   Every year,  American high school students take SATs,  which are standardized tests intended \n" +
                "   to measure literacy,  numeracy, and writing skills. There are three sections - reading, math,\n" +
                "   and writing,  each with a maximum  score of 800 points.  These tests are extremely important \n" +
                "   for students and colleges, as they play a pivotal role in the admissions process.\n" +
                "\n" +
                "   Analyzing the performance  of schools is important for a variety of stakeholders,  including \n" +
                "   policy and education professionals,  researchers,  government,  and even parents considering \n" +
                "   which school their children should attend.\n" +
                "\n" +
                "   In this notebook, we will take a look at data on SATs across public schools in New York City.\n" +
                "   Our database contains a single table:\n" +
                "\n" +
                "   Let's familiarize ourselves with the data by taking a looking at the first few schools!\n");



        String sql = "SELECT * FROM schools LIMIT 10;";

        printTable(sql);

    }

    /**
     * Count rows with null in percent_tested column
     */
    public static void findingMissingValues() throws SQLException {

        System.out.println("2. Finding missing values\n" +
                "\n" +
                "   It looks like the first school in our database had no data in the percent_tested column!\n" +
                "\n" +
                "   Let's identify how many schools  have missing data for this column,  indicating schools that \n" +
                "   did not report the percentage of students tested.\n" +
                "\n" +
                "   To understand  whether this  missing data  problem is  widespread  in New York, we will also \n" +
                "   calculate the total number of schools in the database.\n");

        String sql = "SELECT " +
                "   COUNT(*) - (SELECT COUNT(percent_tested) FROM schools WHERE percent_tested != \"\")" +
                "AS num_tested_missing, \n" +
                "COUNT(*) AS num_schools\n" +
                "FROM schools;";

        printTable(sql);
    }

    /**
     * Count how many schools there are
     */
    public static void schoolsByBuildingCode() throws SQLException {

        System.out.println("3. Schools by building code\n" +
                "\n" +
                "   There  are 20 schools  with  missing data for percent_tested,  which only makes up 5% of all \n" +
                "   rows in the database.\n" +
                "\n" +
                "   Now let's turn our attention to how  many schools there are. When we displayed the first ten \n" +
                "   rows \n" +
                "   of the database,  several had the same value  in the building_code column,  suggesting there \n" +
                "   are  multiple schools  based in the  same location.  Let's  find out how  many unique school \n" +
                "   locations exist in our database.\n");

        String sql = "SELECT COUNT(DISTINCT building_code) AS num_school_buildings FROM schools;";

        printTable(sql);
    }

    /**
     * Find school with the average math results over 80%
     */
    public static void bestSchoolsForMath() throws SQLException {
        System.out.println("4. Best schools for math\n" +
                "\n" +
                "   Out of 375 schools, only 233 (62%) have a unique building_code!\n" +
                "\n" +
                "   Now let's start our analysis of school performance. As each school reports individually, we \n" +
                "   will treat them this way rather than grouping them by building_code.\n" +
                "\n" +
                "   First, let's find all schools with an average math score of at least 80% (out of 800).\n");

        String sql = "SELECT school_name, average_math FROM schools\n" +
                "WHERE average_math >= 640\n" +
                "ORDER BY average_math DESC;";

        printTable(sql);
    }

    /**
     * Find the lowest average reading result
     */
    public static void lowestReadingScore() throws SQLException {
        System.out.println("5. Lowest reading score\n" +
                "\n" +
                "   Wow, there are only ten public schools in New York City with an average math score of at least 640!\n" +
                "\n" +
                "   Now let's look at the other end of the spectrum and find the single lowest score for reading. We will \n" +
                "   only select the score, not the school, to avoid naming and shaming!\n");

        String sql = "SELECT MIN(average_reading) AS lowest_reading FROM schools;";

        printTable(sql);
    }

    /**
     * Find the school with the highest average writing score
     */
    public static void bestWritingSchool() throws SQLException {
        System.out.println("6. Best writing school\n" +
                "\n" +
                "   The lowest average score for reading across schools in New York City is less than 40% of the total \n" +
                "   available points!\n" +
                "\n" +
                "   Now let's find the school with the highest average writing score.\n");

        String sql = "SELECT school_name, MAX(average_writing) \n" +
                "AS max_writing \n" +
                "FROM schools\n" +
                "GROUP BY school_name\n" +
                "ORDER BY max_writing DESC\n" +
                "LIMIT 1;";

        printTable(sql);
    }

    /**
     * Find 10 school with the highest average scores from all tests
     */
    public static void top10Schools() throws SQLException {
        System.out.println("7. Top 10 schools\n" +
                "\n" +
                "   An average writing score of 693 is pretty impressive!\n" +
                "\n" +
                "   This top writing score was at the same school that got the top math score, Stuyvesant High School. \n" +
                "   Stuyvesant is widely known as a perennial top school in New York.\n" +
                "\n" +
                "   What other schools are also excellent across the board? Let's look at scores across reading, writing, \n" +
                "   and math to find out.\n");

        String sql = "SELECT school_name, average_math + average_reading + average_writing\n" +
                "AS average_sat\n" +
                "FROM schools\n" +
                "GROUP BY school_name\n" +
                "ORDER BY average_sat DESC\n" +
                "LIMIT 10;";

        printTable(sql);
    }

    /**
     *
     * @throws SQLException
     */
    public static void rankingBoroughs() throws SQLException {
        System.out.println("8. Ranking boroughs\n" +
                "\n" +
                "   There are four schools with average SAT scores of over 2000! Now let's analyze performance by\n" +
                "   New York City borough.\n" +
                "\n" +
                "   We will build a query that calculates the number of schools and the average SAT score per borough!\n");

        String sql = "SELECT borough, \n" +
                "COUNT(*) AS num_schools, \n" +
                "SUM(average_math + average_reading + average_writing) / COUNT(*) AS average_borough_sat\n" +
                "FROM schools\n" +
                "GROUP BY borough\n" +
                "ORDER BY average_borough_sat DESC;";

        printTable(sql);
    }

    public static void brooklynNumbers() throws SQLException {
        System.out.println("9. Brooklyn numbers\n" +
                "\n" +
                "   It appears that schools in Staten Island, on average, produce higher scores across all three\n" +
                "   categories. However, there are only 10 schools in Staten Island, compared to an average of 91\n" +
                "   schools in the other four boroughs!\n" +
                "\n" +
                "   For our final query of the database, let's focus on Brooklyn, which has 109 schools. We wish to\n" +
                "   find the top five schools for math performance.\n");

        String sql = "SELECT school_name, average_math\n" +
                "FROM schools\n" +
                "WHERE borough = 'Brooklyn'\n" +
                "GROUP BY school_name\n" +
                "ORDER BY average_math DESC\n" +
                "LIMIT 5;";


            printTable(sql);


            // loop through the result set





    }

    /**
     * This method draws a table from given query. It finds the right sizes for each column
     * @param sql
     * @throws SQLException
     */
    public static void printTable(String sql) throws SQLException {


        // connecting with sql and making the query
        Connection conn = SelectApp.connect();
        Statement stmt  = conn.createStatement();
        ResultSet rs    = stmt.executeQuery(sql);

        // getting info from query result
        ResultSetMetaData rsmd = rs.getMetaData();
        int columnCount = rsmd.getColumnCount();

        // getting sizes of columns
        int[] sizes = new int[columnCount];

        for (int i = 1; i <= columnCount; i++) {

            rs = stmt.executeQuery(sql);
            sizes[i - 1] = rsmd.getColumnName(i).length();

            while (rs.next()) {
                int l = rs.getString(i).length();
                if (l > sizes[i - 1]) sizes[i - 1] = l;
            }
        }

        // reset ResultSet
        rs = stmt.executeQuery(sql);

        // printing the table

        // print line
        printLine(sizes);
        // print column names
        for (int i = 0; i < sizes.length; i++) {
            System.out.format("| %" + sizes[i] + "s" + " ", rsmd.getColumnName(i + 1));
        }
        System.out.print("|\n");

        // print line
        printLine(sizes);
        // print each row
        while (rs.next()) {
            for (int i = 0; i < sizes.length; i++) {
                System.out.format("| %" + sizes[i] + "s" + " ", rs.getString(i + 1));
            }
            System.out.print("|\n");
        }
        // print line
        printLine(sizes);

        System.out.println("\n\n");
    }


    /**
     * This method prints a table line with given sizes
     * @param sizes
     */
    public static void printLine(int sizes[]){

        System.out.print("#");

        for (int i = 0; i < sizes.length; i++) {

            for (int j = 0; j < sizes[i] + 2; j++) {
                System.out.print("=");
            }
            System.out.print("#");
        }

        System.out.print("\n");
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        inspectingTheData();
        findingMissingValues();
        schoolsByBuildingCode();
        bestSchoolsForMath();
        lowestReadingScore();
        bestWritingSchool();
        top10Schools();
        rankingBoroughs();
        brooklynNumbers();
    }

}