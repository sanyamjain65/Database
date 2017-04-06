package mypocketvakil.example.com.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by sanyam jain on 06-02-2017.
 */

public class SQLiteHelper extends SQLiteOpenHelper {
    static String DATABASE_NAME="DemoDataBase";

    public static final String KEY_ID="id";

    public static final String TABLE_NAME="demoTable";

    public static final String KEY_Name="name";

    public static final String KEY_PhoneNumber="phone_number";

    public static final String KEY_Subject="subject";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }
    @Override
    public void onCreate(SQLiteDatabase database) {
        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS"+TABLE_NAME+" ("+KEY_ID+" INTEGER PRIMARY KEY, "+KEY_Name+" TEXT, "+KEY_PhoneNumber+" TEXT, "+KEY_Subject+" TEXT)";
        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
