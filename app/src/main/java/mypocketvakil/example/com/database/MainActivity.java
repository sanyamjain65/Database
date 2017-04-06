package mypocketvakil.example.com.database;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText GetName,GetPhoneNumber,GetSubject ;
    Button Submit, ShowValues;
    SQLiteDatabase SQLITEDATABASE;
    String Name, PhoneNumber, Subject ;
    Boolean CheckEditTextEmpty ;
    String SQLiteQuery ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GetName = (EditText) findViewById(R.id.editText1);

        GetPhoneNumber = (EditText) findViewById(R.id.editText2);

        GetSubject = (EditText) findViewById(R.id.editText3);

        Submit = (Button) findViewById(R.id.button1);

        ShowValues = (Button) findViewById(R.id.button2);

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBCreate();

                SubmitData2SQLiteDB();
            }
        });
        ShowValues.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, List_View.class);
                startActivity(intent);
            }
        });
    }

    private void SubmitData2SQLiteDB() {
        Name = GetName.getText().toString();

        PhoneNumber = GetPhoneNumber.getText().toString();

        Subject = GetSubject.getText().toString();
        CheckEditTextIsEmptyOrNot( Name,PhoneNumber, Subject);
        if(CheckEditTextEmpty == true)
        {

            SQLiteQuery = "INSERT INTO demoTable (name,phone_number,subject) VALUES('"+Name+"', '"+PhoneNumber+"', '"+Subject+"');";
            SQLITEDATABASE.execSQL(SQLiteQuery);
            Toast.makeText(MainActivity.this,"Data Submit Successfully", Toast.LENGTH_LONG).show();

            ClearEditTextAfterDoneTask();

        }
        else {

            Toast.makeText(MainActivity.this,"Please Fill All the Fields", Toast.LENGTH_LONG).show();
        }

    }
    public void ClearEditTextAfterDoneTask(){

        GetName.getText().clear();
        GetPhoneNumber.getText().clear();
        GetSubject.getText().clear();

    }

    public void DBCreate(){
            SQLITEDATABASE =openOrCreateDatabase("DemoDataBase", Context.MODE_PRIVATE,null);

            SQLITEDATABASE.execSQL("CREATE TABLE IF NOT EXISTS demoTable(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, name VARCHAR, phone_number VARCHAR, subject VARCHAR);");
        }
    public void CheckEditTextIsEmptyOrNot(String Name,String PhoneNumber, String subject ){

        if(TextUtils.isEmpty(Name) || TextUtils.isEmpty(PhoneNumber) || TextUtils.isEmpty(Subject)){

            CheckEditTextEmpty = false ;

        }
        else {
            CheckEditTextEmpty = true ;
        }
    }


        /*insert=(Button)findViewById(R.id.insert);
        retrieve=(Button)findViewById(R.id.ret);
        title=(TextView)findViewById(R.id.title);
        sub=(TextView)findViewById(R.id.sub);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySQLiteHelper mDbHelper = new MySQLiteHelper(MainActivity.this);

                SQLiteDatabase db=mDbHelper.getWritableDatabase();
                ContentValues values=new ContentValues();
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,"Sanyam");
                values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE,"Jain");

                long newRowId= db.insert(FeedReaderContract.FeedEntry.TABLE_NAME,null,values);
                Toast.makeText(MainActivity.this,"Data Entered",Toast.LENGTH_LONG).show();
            }
        });

        retrieve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MySQLiteHelper mDbHelper = new MySQLiteHelper(MainActivity.this);
                SQLiteDatabase db = mDbHelper.getReadableDatabase();

                String[] projection = {
                        FeedReaderContract.FeedEntry._ID,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE,
                        FeedReaderContract.FeedEntry.COLUMN_NAME_SUBTITLE

                };
                String selection = FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE + " = ?";
                String[] selectionArgs = { "Sanyam" };
                Cursor cursor=db.query(FeedReaderContract.FeedEntry.TABLE_NAME,projection,selection,selectionArgs,null,null,null);

                if (cursor.moveToFirst()) {
                    do {
                        String emailid=cursor.getString(0); // Here you can get data from table and stored in string if it has only one string.
                        title.setText(emailid);;


                    } while (cursor.moveToNext());
                }
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                if(db!=null)
                {
                    db.close();
                }

            }
        });




    }*/
    }


