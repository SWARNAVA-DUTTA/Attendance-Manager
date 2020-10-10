package developers.android.attendencemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CSEDatabaseHelper1 extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "Student.db1";
    public final static String TABLE_NAME = "student_table";
    public static final String COL_1 = "ROLL";
    public static final String COL_2 = "NAME";


    public CSEDatabaseHelper1(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase csedb1 = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase csedb1) {
        csedb1.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ROLL INTEGER PRIMARY KEY ,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase csedb1, int oldVersion, int newVersion) {
        csedb1.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(csedb1);
    }

    public boolean insertData(String roll,String name) {
        SQLiteDatabase csedb1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, roll);
        cv.put(COL_2, name);
        long result = csedb1.insert(TABLE_NAME, null, cv);
        csedb1.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }


    public boolean updateData(String roll,String name) {
        SQLiteDatabase csedb1 = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, roll);
        contentValues.put(COL_2, name);

        csedb1.update(TABLE_NAME, contentValues, "ROLL=?", new String[]{roll});
        return true;
    }

    public Integer deleteData (String roll,String name) {
        SQLiteDatabase csedb1 = this.getWritableDatabase();
        int i=csedb1.delete(TABLE_NAME, null,null);
        return i;
    }

    public Cursor getAllData() {
        SQLiteDatabase csedb1 = this.getWritableDatabase();
        Cursor res = csedb1.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }
}
