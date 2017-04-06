package mypocketvakil.example.com.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sanyam jain on 06-02-2017.
 */

public class CommentsDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns={MySQLiteHelper.COLUMN_ID,MySQLiteHelper.COLUMN_COMMENT};
    Context context;

    public CommentsDataSource(Context context)
    {
        this.context=context;
        dbHelper=new MySQLiteHelper(context);
    }
    public void open() throws SQLException{
        database=dbHelper.getWritableDatabase();
            }
    public void close(){
        dbHelper.close();
    }
    public Comment createComment(String comment)
    {
        ContentValues values=new ContentValues();
                values.put(MySQLiteHelper.COLUMN_COMMENT,comment);
        long insertid=database.insert(MySQLiteHelper.TABLE_COMMENTS,null,values);

        Cursor cursor=database.query(MySQLiteHelper.TABLE_COMMENTS,allColumns,MySQLiteHelper.COLUMN_ID + "=" + insertid,null,null,null,null);
        cursor.moveToFirst();
        Comment newcomment= cursorToComment(cursor);
        cursor.close();
        return newcomment;

    }
    public void deleteComment(Comment comment){
        long id=comment.getId();
        Toast.makeText(context,"Comment deleted with id ="+ id,Toast.LENGTH_LONG).show();
        database.delete(MySQLiteHelper.TABLE_COMMENTS,MySQLiteHelper.COLUMN_ID + "=" + id,null);


    }
    public List<Comment> getAllComments(){
        List<Comment> comments= new ArrayList<Comment>();
        Cursor cursor=database.query(MySQLiteHelper.TABLE_COMMENTS,allColumns,null,null,null,null,null);
        cursor.moveToFirst();
        while(!cursor.isAfterLast())
        {
            Comment comment=cursorToComment(cursor);
            comments.add(comment);
            cursor.moveToNext();

        }
        cursor.close();
        return comments;

    }
    private Comment cursorToComment(Cursor cursor)
    {
        Comment comment=new Comment();
        comment.setId(cursor.getLong(0));
        comment.setComment(cursor.getString(0));
        return comment;

    }
}
