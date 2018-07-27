package com.oracle.utils;

import java.sql.*;
import java.text.MessageFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 */
public class JdbcConnectionPerformanceTest {

    private String hostName;
    private String userName;
    private String password;
    private int numberOfIterations;


    //Constants
    public static final String QUERY = "select 1 from DUAL";
    public static final String JDBC_DRIVER_CLASS_NAME = "com.mysql.jdbc.Driver";
    public static final String JDBC_URL_TEMPLATE = "jdbc:mysql//{0}:3306/lib.library.priv";

    /**
     * Answer a default instance of me
     */
    public JdbcConnectionPerformanceTest() {

        super();
    }

    /**
     * Main method. Arguments should be of the following format:
     * user pw numberOfIteractions
     * Example: "hostName acs_user acs_user 25"
     */
    public static void main(String[] args) throws Exception {

        JdbcConnectionPerformanceTest   tempTest;


        tempTest = new JdbcConnectionPerformanceTest();
        tempTest.initializeParameters(new Scanner(System.in));
        tempTest.performQueries();
    }

    /**
     * Perform queries
     */
    public void performQueries() throws Exception {

        Connection       tempConnection = null;
        Statement        tempStatement = null;
        StringBuilder    tempBuilder = new StringBuilder();
        long             tempTotalSeconds = 0;
        Duration         tempCurrentQueryDuration;

        try {

            tempBuilder.append("Starting query test for : " + QUERY);
            tempBuilder.append(System.getProperty("line.separator"));

            this.loadJdbcDriver();
            tempConnection = this.createConnection();
            tempStatement = this.createStatement(tempConnection);

            for (int i = 0; i < this.getNumberOfIterations(); i++) {

                tempCurrentQueryDuration =
                        this.performSingleQuery(tempBuilder,
                                                tempConnection,
                                                tempStatement);
                tempTotalSeconds = tempCurrentQueryDuration.getSeconds();
            }

        }
        finally {

            this.safelyClose(tempStatement);
            this.safelyClose(tempConnection);
        }

        //Dump final time statistics
        tempBuilder.append(System.getProperty("line.separator"));
        tempBuilder.append("Total number of iterations: "
                           + this.getNumberOfIterations()
                           + " total time(seconds): "
                           + tempTotalSeconds + " average(seconds): "
                           + tempTotalSeconds/this.getNumberOfIterations());


        System.out.println(tempBuilder.toString());
    }


    /**
     * Perform single query
     * @param aBuilder
     * @param aConnection
     * @param aStatement
     * @throws Exception
     */
    protected Duration performSingleQuery(StringBuilder aBuilder,
                                          Connection aConnection,
                                          Statement aStatement) throws Exception {

        LocalTime       tempBeforeDate;
        LocalTime       tempAfterDate;
        ResultSet       tempResultSet = null;
        Duration        tempDuration = null;

        try {

            aBuilder.append(System.getProperty("line.separator"));
            tempBeforeDate = LocalTime.now();
            tempResultSet = this.basicPerformDefaultQuery(aStatement);
            tempAfterDate = LocalTime.now();

            this.dumpResultSetToBuilder(aBuilder, tempResultSet);
            tempDuration = Duration.between(tempBeforeDate, tempAfterDate);
            aBuilder.append("Query time: " + tempDuration.toString());
            aBuilder.append(System.getProperty("line.separator"));

        }
        finally {

            this.safelyClose(tempResultSet);
        }

        return tempDuration;

    }

    /**
     * Safely close result set
     * @param aResultSet
     */
    protected void safelyClose(ResultSet aResultSet)  {

        try {
            if (aResultSet != null) {

                aResultSet.close();
            }
        }
        catch (Exception e) {

            //swallow this
        }
    }

    /**
     * Safely close result set
     * @param aStatement
     */
    protected void safelyClose(Statement aStatement)  {

        try {
            if (aStatement != null) {

                aStatement.close();
            }
        }
        catch (Exception e) {

            //swallow this
        }
    }

    /**
     * Safely close result set
     * @param aConnection
     */
    protected void safelyClose(Connection aConnection)  {

        try {
            if (aConnection != null) {

                aConnection.close();
            }
        }
        catch (Exception e) {

            //swallow this
        }
    }

    /**
     * Validate input parameter
     * @param aTokenizer
     * @param aMsg String
     */
    protected void validateParameter(StringTokenizer aTokenizer,
                                     String aMsg) {

        if (!aTokenizer.hasMoreElements()) {

            throw new IllegalArgumentException("No " + aMsg + " specified");
        }


    }

    /**
     * Answer my host name
     * @return String
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * Set my host name
     * @param hostName
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * Answer my user name
     *
     * @return String
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Set my user name
     *
     * @param userName
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * Answer my password
     *
     * @return String
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set my password
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Answer my number of iterations
     *
     * @return int
     */
    public int getNumberOfIterations() {
        return numberOfIterations;
    }

    /**
     * Set my number of iterations
     *
     * @param numberOfIterations
     */
    public void setNumberOfIterations(int numberOfIterations) {
        this.numberOfIterations = numberOfIterations;
    }


    /**
     * Initialiize parameters
     * @param aScanner Scanner
     */
    protected void initializeParameters(Scanner aScanner) {

        try {

            String                                tempMsg;
            StringTokenizer                       tempTokenizer;

            aScanner = new Scanner(System.in);
            tempMsg = aScanner.nextLine();

            tempTokenizer = new StringTokenizer(tempMsg,
                                                " ");

            //Host name
            this.validateParameter(tempTokenizer,
                                   "hostName");
            this.setHostName(tempTokenizer.nextToken());

            //User
            this.validateParameter(tempTokenizer,
                                   "userName");
            this.setUserName(tempTokenizer.nextToken());

            //Password
            this.validateParameter(tempTokenizer,
                                   "password");
            this.setPassword(tempTokenizer.nextToken());

            //Number of iterations
            this.validateParameter(tempTokenizer,
                                   "numberOfIterations");
            this.setNumberOfIterations(Integer.parseInt(tempTokenizer.nextToken()));

        }
        catch(NumberFormatException f) {

            System.out.println("Invalid number encountered");
        }
        catch (Exception e) {

            System.out.println("Usage: java JdbcConnectionPerformanceTest hostName userName password numberOfIterations");
        }

    }

    /**
     * Load jdbc driver
     */
    protected void loadJdbcDriver()
            throws ClassNotFoundException {

        Class.forName(JDBC_DRIVER_CLASS_NAME);

    }

    /**
     * Create connection
     * @return Connection
     */
    protected Connection createConnection() throws SQLException {

        return DriverManager.getConnection(this.createJdbcUrlFromTemplate(),
                                            this.getUserName(),
                                            this.getPassword());
    }

    /**
     * Perform default query
     * @param aStatement Statement
     */
    protected ResultSet basicPerformDefaultQuery(Statement aStatement)
                        throws SQLException {

        return aStatement.executeQuery(QUERY);

    }

    /**
     * Create atatement for aConnection
     * @param aConnection
     * @return Statement
     * @throws SQLException
     */
    protected Statement createStatement(Connection aConnection) throws SQLException {

        return aConnection.createStatement();

    }

    /**
     * Dump queried result set to aBuilder
     * @param aBuilder StringBuilder
     * @param aResultSet ResultSet
     */
    protected void dumpResultSetToBuilder(StringBuilder aBuilder,
                                          ResultSet aResultSet)
                        throws SQLException {

        ResultSetMetaData tempData;
        int               tempRowNumber = 1;

        tempData = aResultSet.getMetaData();
        while (aResultSet.next()) {

            aBuilder.append("Row Number: " +  tempRowNumber);
            aBuilder.append(System.getProperty("line.separator"));

            for (int i = 1; i <= tempData.getColumnCount(); i++) {

                aBuilder.append("columnName: " + tempData.getColumnLabel(i) + " value:" + aResultSet.getObject(i));
                aBuilder.append(System.getProperty("line.separator"));
            }

            aBuilder.append(System.getProperty("line.separator"));
            tempRowNumber++;
        }

    }


    /**
     * Create jdbc url from template
     * @return String
     */
    protected String createJdbcUrlFromTemplate() {

        Object[]    tempArgs = {this.getHostName()};

        return MessageFormat.format(JDBC_URL_TEMPLATE,
                                    tempArgs);
    }


}
