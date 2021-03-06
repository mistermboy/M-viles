package com.example.uo252406.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDBHelper extends SQLiteOpenHelper {

    /**
     * Nombre y version de la base de datos
     */
    private static final String DATABASE_NAME = "valorations.db";
    private static final int DATABASE_VERSION = 1;
    /**
     * Nombre de la tabla valorations y sus columnas
     */
    public static final String TABLE_VALORATIONS = "valorations";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_ASIGNATURA = "subject";
    public static final String COLUMN_RATING = "rating";
    public static final String COLUMN_COMMENT = "comment";



    /**
     * Script para crear la base datos
     */
    private static final String DATABASE_CREATE = "create table " + TABLE_VALORATIONS
            + "( " + COLUMN_ID + " " +
            "integer primary key autoincrement, " + COLUMN_ASIGNATURA
            + " text not null, " + COLUMN_COMMENT + " text not null, " + COLUMN_RATING +
            " integer not null" + ");";

    /**
     * Script para borrar la base de datos
     */
    private static final String DATABASE_DROP = "DROP TABLE IF EXISTS " + TABLE_VALORATIONS;

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory,
                      int version) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //invocacamos execSQL pq no devuelve ningún tipo de dataset
        db.execSQL(DATABASE_CREATE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DATABASE_DROP);
        this.onCreate(db);

    }
}
