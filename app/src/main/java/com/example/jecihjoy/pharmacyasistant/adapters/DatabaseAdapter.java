package com.example.jecihjoy.pharmacyasistant.adapters;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;
    import android.util.Log;

    import com.example.jecihjoy.pharmacyasistant.Model.Medicine;
    import com.example.jecihjoy.pharmacyasistant.Model.Replacements;

    import java.util.ArrayList;
    import java.util.Calendar;

/**
 * Created by Jecihjoy on 4/2/2018.
 */

public class DatabaseAdapter {
    private static final String DATABASE_NAME = "mymeds.db";
    private static final int DATABASE_VERSION = 4;

    public static final String MED_TABLE = "medicine";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_AMOUNT = "amount";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_DESC = "desc";
    public static final String COLUMN_ENDDATE = "enddate";


    public static long id;

    String [] allcolumns = {"id", "amount", "name", "type", "desc" , "enddate"};

    String [] columnUsers = {"id", "fname", "lname", "username", "phone", "email",
                    "location", "pass1", "pass2"};

    private SQLiteDatabase sqlDB;
    private MedicineDbHelper medicineDbHelper;
    private Context context;

    public DatabaseAdapter(Context ctx){
        context = ctx;
    }

    public DatabaseAdapter open() throws android.database.SQLException{
        medicineDbHelper = new MedicineDbHelper(context);
        sqlDB = medicineDbHelper.getWritableDatabase();
        return  this;
    }

    public void close(){
        medicineDbHelper.close();
    }

    public Medicine createMeds(int amount, String name, String type, String desc, String expirydate){
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME,name);
        values.put(COLUMN_DESC,desc);
        values.put(COLUMN_AMOUNT,amount);
        values.put(COLUMN_TYPE,type);
        values.put(COLUMN_ENDDATE, expirydate);
        long insertId = sqlDB.insert(MED_TABLE, null, values);
        Cursor cursor = sqlDB.query(MED_TABLE, allcolumns, COLUMN_ID + " = " + insertId, null, null, null, null);

        cursor.moveToFirst();
        Medicine newMed = cursorToMedicine(cursor);
        cursor.close();
        return  newMed;
    }

    public long updateStock(int idToUpdate,int amount, String expirydate){
        ContentValues values = new ContentValues();
        values.put(COLUMN_AMOUNT,amount);
        values.put(COLUMN_ENDDATE, expirydate);
        return  sqlDB.update(MED_TABLE, values, COLUMN_ID+ " = "+idToUpdate, null );
    }

    public long deleteMedicine(long idToDelete){
        return sqlDB.delete(MED_TABLE, COLUMN_ID+ " = " +idToDelete,null);
    }
    public void deleteAllMeds(){
        sqlDB.execSQL(" DELETE FROM " + MED_TABLE);
        //return sqlDB.delete(MED_TABLE, null,null);
    }

    //getAll
    public ArrayList<Medicine> getAllMeds(){
        ArrayList<Medicine> meds = new ArrayList<Medicine>();
        Cursor cursor = sqlDB.query(MED_TABLE, allcolumns,null,null,null,null,null);
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Medicine myMed = cursorToMedicine(cursor);
            meds.add(myMed);
            //id = myMed.getMedId();
        }
        cursor.close();
        return meds;
    }

    //get medicines below 200
    public ArrayList<Medicine>getMinimumMeds(String minAmount){
        ArrayList<Medicine> meds = new ArrayList<Medicine>();
        Cursor cursor = sqlDB.query(MED_TABLE, allcolumns,"amount < ?", new String[] {minAmount},null,null,null);
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Medicine myMed = cursorToMedicine(cursor);
            meds.add(myMed);
        }
        cursor.close();
        return meds;
    }

    private Medicine cursorToMedicine(Cursor cursor){
        Medicine newMed = new Medicine(cursor.getInt(0), cursor.getInt(1), cursor.getString(2),
                cursor.getString(3), cursor.getString(4), cursor.getString(5));
        return newMed;

    }

    //more, replaced, contradicting, newmeds
    public void insertReplaced(String old, String newmed, String reason){
        ContentValues values = new ContentValues();
        values.put("med1",old);
        values.put("med2",newmed);
        values.put("description",reason);
        long insertId = sqlDB.insert("medReplaced", null, values);
    }

    public ArrayList<Replacements> getReplacedMeds(){
        String [] cols = {"id", "med1", "med2", "description"};
        ArrayList<Replacements> meds = new ArrayList<Replacements>();
        Cursor cursor = sqlDB.query("medReplaced", cols,null,null,null,null,null);
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Replacements myMed = cursorToMeds(cursor);
            meds.add(myMed);
            //id = myMed.getMedId();
        }
        cursor.close();
        return meds;
    }

    private Replacements cursorToMeds(Cursor cursor){
        Replacements newMed = new Replacements(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3));
        return newMed;

    }

    public void createNewMeds(String newMed, String reason, String typeA, String date){
        ContentValues values = new ContentValues();
        values.put("name",newMed);
        values.put("type",typeA);
        values.put("date",date);
        values.put("description",reason);
        long insertId = sqlDB.insert("newMeds", null, values);
    }

    public ArrayList<Replacements> getNewMeds(){
        String [] cols = {"id", "name", "type", "date", "description"};
        ArrayList<Replacements> meds = new ArrayList<Replacements>();
        Cursor cursor = sqlDB.query("newMeds", cols,null,null,null,null,null);
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Replacements myMed = cursorToNewMeds(cursor);
            meds.add(myMed);
            //id = myMed.getMedId();
        }
        cursor.close();
        return meds;
    }

    private Replacements cursorToNewMeds(Cursor cursor){
        Replacements newMed = new Replacements(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4));
        return newMed;

    }

    public void createcontradicting(String med1, String med2,String effects,String med1type,String med2type, String alternatives){
        ContentValues values = new ContentValues();
        values.put("med1",med1);
        values.put("med1type",med1type);
        values.put("med2",med2);
        values.put("med2type",med2type);
        values.put("effects",effects);
        values.put("alternatives",alternatives);
        long insertId = sqlDB.insert("Contradictios", null, values);
    }

    public ArrayList<Replacements> getContradictions(){
        String [] cols = {"id", "med1", "med1type", "med2", "med2type", "effects", "alternatives"};
        ArrayList<Replacements> meds = new ArrayList<Replacements>();
        Cursor cursor = sqlDB.query("Contradictios", cols,null,null,null,null,null);
        for(cursor.moveToLast(); !cursor.isBeforeFirst(); cursor.moveToPrevious()){
            Replacements myMed = cursorToContradictingMeds(cursor);
            meds.add(myMed);
            //id = myMed.getMedId();
        }
        cursor.close();
        return meds;
    }

    private Replacements cursorToContradictingMeds(Cursor cursor){
        Replacements newMed = new Replacements(cursor.getInt(0), cursor.getString(1),
                cursor.getString(2), cursor.getString(3), cursor.getString(4),
                cursor.getString(5), cursor.getString(6));
        return newMed;

    }

    //users
    public void insertUsers(int id, String fname, String lname, String username, String phone, String email,
                            String location, String pass1, String pass2){
        ContentValues values = new ContentValues();
        values.put("id",id);
        values.put("fname",fname);
        values.put("lname",lname);
        values.put("username",username);
        values.put("phone",phone);
        values.put("email",email);
        values.put("location",location);
        values.put("pass1",pass1);
        values.put("pass2",pass2);
        long insertId = sqlDB.insert("users2", null, values);
    }

    public Cursor getUserInfo() {

        String select_all = " SELECT * FROM  users2 LIMIT 1 " ;
        return sqlDB.query("users2", columnUsers, null, null, null, null, String.valueOf(1));
    }

    public long updateProfile(String id, String pname, String gname, String pgname){
        ContentValues values = new ContentValues();
        values.put("pName",pname);
        values.put("gName",gname);
        values.put("pgName",pgname);
        return  sqlDB.update("users2", values, "id"+ " = "+id, null );

    }

    //sql helper class
    private static class MedicineDbHelper extends SQLiteOpenHelper {
        MedicineDbHelper(Context ctx){
            super(ctx,DATABASE_NAME,null,DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            //int amount, int id, String name, String type, String desc, String expirydate
            String sql = "  CREATE TABLE  " + MED_TABLE + "( id integer not null PRIMARY KEY AUTOINCREMENT , amount integer ,  name TEXT, type TEXT, " +
                    "desc TEXT, enddate TEXT)";
            db.execSQL(sql);

            String sqlUsers = "  CREATE TABLE  users2 ( id integer not null PRIMARY KEY, fname TEXT, lname TEXT, username TEXT, phone TEXT, email TEXT, location TEXT," +
                    "pass1 TEXT, pass2 TEXT)";
            db.execSQL(sqlUsers);

            String sqlReplacements = "  CREATE TABLE  medReplaced ( id integer not null PRIMARY KEY, med1 TEXT, med2 TEXT, description TEXT)";
            db.execSQL(sqlReplacements);

            String sqlNewMeds = "  CREATE TABLE  newMeds ( id integer not null PRIMARY KEY, name TEXT, type TEXT, date TEXT, description TEXT)";
            db.execSQL(sqlNewMeds);

            String sqlContradictios = "  CREATE TABLE  Contradictios ( id integer not null PRIMARY KEY, med1 TEXT, med1type TEXT, med2 TEXT, med2type TEXT, effects TEXT, alternatives TEXT)";
            db.execSQL(sqlContradictios);
        }
        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
            Log.d(MedicineDbHelper.class.getName(),
                    "Upgrading database from version"+oldVersion+"to" +newVersion+ ", which will destroy all the old data");
            sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MED_TABLE);
            //sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + MED_TABLE);
            onCreate(sqLiteDatabase);
        }
    }


}
