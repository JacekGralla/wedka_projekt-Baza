package com.example.luptowski.wedka;

/**
 * Created by lukas on 11.05.2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLite extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private String url = "http://wedkarze.cba.pl/data.xml/xml";

    private HandleXML obj;
    public MySQLite(Context context) {
        super(context, "braniaDB", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String DATABASE_CREATE = "CREATE TABLE calendar (" +
                "id INTEGER PROMARY_KEY AUTOINCREMENT" +
                "date TEXT NOT NULL" +
                "dayName TEXT NOT NULL" +
                "sunrise TEXT NOT NULL" +
                "sunset TEXT NOT NULL" +
                "moonStateId INTEGER NOT NULL" +
                "conditionStateId INTEGER NOT NULL);";
        database.execSQL(DATABASE_CREATE);
        DATABASE_CREATE = "CREATE TABLE condition (" +
                "id INTEGER PROMARY_KEY AUTOINCREMENT" +
                "description TEXT NOT NULL" +
                "imageURL TEXT NOT NULL);";
        database.execSQL(DATABASE_CREATE);
        DATABASE_CREATE = "CREATE TABLE moonstate (" +
                "id INTEGER PROMARY_KEY AUTOINCREMENT" +
                "description TEXT NOT NULL" +
                "imageURL TEXT NOT NULL);";
        database.execSQL(DATABASE_CREATE);
        DATABASE_CREATE = "CREATE TABLE fish (" +
                "id INTEGER PROMARY_KEY AUTOINCREMENT" +
                "name TEXT NOT NULL" +
                "length INTEGER NOT NULL" +
                "weight INTEGER NOT NULL);";
        database.execSQL(DATABASE_CREATE);
        DATABASE_CREATE = "CREATE TABLE ranking (" +
                "id INTEGER PROMARY_KEY AUTOINCREMENT" +
                "place INTEGER NOT NULL" +
                "userid INTEGER NOT NULL" +
                "fishid INTEGER NOT NULL);";
        database.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS calendar");
        db.execSQL("DROP TABLE ID EXISTS condition");
        db.execSQL("DROP TABLE ID EXISTS moonstate");
        db.execSQL("DROP TABLE ID EXISTS fish");
        db.execSQL("DROP TABLE ID EXISTS ranking");
        onCreate(db);
    }

    public void dodaj(Calendar rybka) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", rybka.getDate());
        values.put("dayName", rybka.getDayName());
        values.put("sunrise", rybka.getSunrise());
        values.put("sunset", rybka.getSunset());
        values.put("moonStateId", rybka.getMoonStateId());
        values.put("conditionStateId", rybka.getConditionStateId());

        db.insert("calendar", null, values);
        db.close();
    }
    public void dodaj(Condition condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("description", condition.getDescription());
        values.put("imageURL", condition.getImageURL());

        db.insert("condition", null, values);
        db.close();
    }
    public void dodaj(MoonState moonState) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("description", moonState.getDescription());
        values.put("imageURL", moonState.getImageURL());

        db.insert("moonstate", null, values);
        db.close();
    }

    public void dodaj(Fish fish) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("length", fish.getLength());
        values.put("weight", fish.getWeight());
        values.put("name", fish.getName());


        db.insert("fish", null, values);
        db.close();
    }
    public void dodaj(Ranking ranking) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("userid", ranking.getUserID());
        values.put("fishid", ranking.getFishID());
        values.put("place", ranking.getPlace());


        db.insert("ranking", null, values);
        db.close();
    }

    public void usunCalendar(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("calendar", " id = ?", new String[]{id});
        db.close();
    }
    public void usunConditiion(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("condition", " id = ?", new String[]{id});
        db.close();
    }
    public void usunMoonState(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("moonstate", " id = ?", new String[]{id});
        db.close();
    }
    public void usunFish(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("fish", " id = ?", new String[]{id});
        db.close();
    }
    public void usunRanking(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("ranking", " id = ?", new String[]{id});
        db.close();
    }

    public int aktualizuj(Calendar rybka) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("date", rybka.getDate());
        values.put("dayName", rybka.getDayName());
        values.put("sunrise", rybka.getSunrise());
        values.put("sunset", rybka.getSunset());
        values.put("moonStateId", rybka.getMoonStateId());
        values.put("conditionStateId", rybka.getConditionStateId());
        int i = db.update("calendar", values, " id = ?", new String[]{String.valueOf(rybka.getId())});
        db.close();
        return i;
    }

    public int updateToXml(Calendar calendar) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        obj = new HandleXML(url);
        obj.execute();
        while (obj.parsingComplete) {
            if (obj.getID().equals(calendar.getId())) {

                values.put("date", obj.getDate());
                values.put("dayName", obj.getDayName());
                values.put("sunrise", obj.getSunrise());
                values.put("sunset", obj.getSunset());
                values.put("moonStateId", obj.getMoonState());
                values.put("conditionStateId", obj.getCondition());

            }
        }
        int i = db.update("calendar", values, " id = ?", new String[]{String.valueOf(obj.getID())});
        db.close();
        return i;
    }
    public int aktualizuj(Condition condition) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("description", condition.getDescription());
        values.put("imageURL", condition.getImageURL());

        int i = db.update("condition", values, " id = ?", new String[]{String.valueOf(condition.getId())});
        db.close();
        return i;
    }

    public int aktualizuj(MoonState moonState) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("description", moonState.getDescription());
        values.put("imageURL", moonState.getImageURL());

        int i = db.update("moonstate", values, " id = ?", new String[]{String.valueOf(moonState.getId())});
        db.close();
        return i;
    }

    public int aktualizuj(Fish fish) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("weight", fish.getWeight());
        values.put("length", fish.getLength());
        values.put("name", fish.getName());

        int i = db.update("fish", values, " id = ?", new String[]{String.valueOf(fish.getId())});
        db.close();
        return i;
    }

    public int aktualizuj(Ranking ranking) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("place", ranking.getPlace());
        values.put("fishid", ranking.getFishID());
        values.put("userid", ranking.getUserID());

        int i = db.update("ranking", values, " id = ?", new String[]{String.valueOf(ranking.getId())});
        db.close();
        return i;
    }

    public Calendar pobierzCalendar(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("brania", //a. table name
                new String[]{"_id", "date", "dayName", "sunrise", "sunset","moonStateId", "conditionStateId"}, // b.column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null);// h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Calendar rybka = new Calendar(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
        rybka.setId(Integer.parseInt(cursor.getString(0)));

        return rybka;
    }

    public Condition pobierzCondition(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("condition", //a. table name
                new String[]{"_id", "description", "imageURL"}, // b.column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null);// h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Condition condition = new Condition(cursor.getString(1),cursor.getString(2));
        condition.setId(Integer.parseInt(cursor.getString(0)));

        return condition;
    }

    public MoonState pobierzMoonState(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("moonstate", //a. table name
                new String[]{"_id", "description", "imageURL"}, // b.column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null);// h. limit

        if (cursor != null)
            cursor.moveToFirst();

        MoonState moonState = new MoonState(cursor.getString(1),cursor.getString(2));
        moonState.setId(Integer.parseInt(cursor.getString(0)));

        return moonState;
    }

    public Fish pobierzFish (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("fish", //a. table name
                new String[]{"_id", "name", "length", "weight"}, // b.column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null);// h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Fish fish = new Fish(cursor.getString(1),cursor.getFloat(2),cursor.getFloat(3));
        fish.setId(Integer.parseInt(cursor.getString(0)));

        return fish;
    }
    public Ranking pobierzRanking (int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query("ranking", //a. table name
                new String[]{"_id", "place", "userid", "fishid"}, // b.column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null);// h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Ranking ranking = new Ranking(cursor.getInt(1),cursor.getInt(2),cursor.getInt(3));
        ranking.setId(Integer.parseInt(cursor.getString(0)));

        return ranking;
    }

    public Cursor listaCalendar() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM calendar", null);
    }
    public Cursor listaCondition() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM condition", null);
    }
    public Cursor listaMoonState() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM moonstate", null);
    }
    public Cursor listaFish() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM fish", null);
    }
    public Cursor listaRanking() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM ranking", null);
    }
}
