package developers.android.attendencemanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ECEDatabaseHelper2 extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "Student.db10";
    public final static String TABLE_NAME = "student_table";
    public static final String COL_1 = "ROLL";
    public static final String COL_2 = "NAME";


    public ECEDatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(ROLL INTEGER PRIMARY KEY ,NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String roll,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_1, roll);
        cv.put(COL_2, name);
        long result = db.insert(TABLE_NAME, null, cv);
        db.close();
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean updateData(String roll,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, roll);
        contentValues.put(COL_2, name);

        db.update(TABLE_NAME, contentValues, "ROLL=?", new String[]{roll});
        return true;
    }

    public Integer deleteData (String roll,String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i=db.delete(TABLE_NAME, null,null);
        return i;
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM "+TABLE_NAME, null);
        return res;
    }
}
