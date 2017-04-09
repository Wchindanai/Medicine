package dev.medicine;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DrugDatabase extends SQLiteOpenHelper {

    private static final String TAG = "Database";
    private static final String DATABASE_NAME="medicine";
    private static final int DATABASE_VERSION=1;


    public DrugDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDrugTable(db);
    }

    //Create Drug Table
    private void createDrugTable(SQLiteDatabase db) {
        Log.i(TAG, "Drug Table Creating");
        //Specific Table Column and Table name
        String columnId = "id";
        String columnDrugName = "name";
        String columnHowToUse = "how_to";
        String columnRisk = "risk";
        String columnNote = "note";
        String columnReference = "reference";
        String drugTable="drug";


        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS " + drugTable + " ("
                + columnId + "INTEGER PRIMARY KEY NOT NULL, "
                + columnDrugName + "TEXT, "
                + columnHowToUse + "TEXT, "
                + columnRisk + "TEXT, "
                + columnNote + "TEXT, "
                + columnReference + "TEXT"
                + ")";

        db.execSQL(sqlCreateTable);
        Log.i(TAG, "Drug Table Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}