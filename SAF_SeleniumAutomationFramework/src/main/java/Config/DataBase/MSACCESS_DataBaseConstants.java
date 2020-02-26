package Config.DataBase;

public class MSACCESS_DataBaseConstants {

    public static final String MSACCESS_DATABASE_PATH = "src/test/DataBase";
    public static final String MSACCESS_DATABASE_NAME = "SAF.MDB";
    public static final String MSACCESS_REPORTS_TABLE_NAME = "REPORTS";

    public static String MSACCESS_REPORTS_TABLE_SCHEMA =
            "create table " + MSACCESS_REPORTS_TABLE_NAME + " ( "
                    + "Col_1 varchar(50),"
                    + "Col_2 varchar(500),"
                    + "Col_3 BIGINT )";


}
