package com.iitr.thomso;

import java.io.FileOutputStream;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.R.string;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseHelper extends SQLiteOpenHelper {

	private static String DB_PATH = "/data/data/com.iitr.thomso/databases/";
	private static String DB_NAME = "thomso13.db";
	private SQLiteDatabase myDataBase; 
	private final Context myContext;
	
	private static final String TABLE_NAME = "table_details";
	private static final String KEY_ID = "id";
	private static final String KEY_DAY = "day";
	private static final String KEY_EVENT_NAME = "event_name";
	private static final String KEY_VENUE = "venue";
	private static final String KEY_START_TIME = "start_time";
	private static final String KEY_END_TIME = "end_time";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_COORDI = "coordinators";
	private static final String KEY_DATE = "date";
	private static final String KEY_TYPE = "type";

	
	public DatabaseHelper(Context context) {
		
		super(context, DB_NAME, null, 1);
        this.myContext = context;
	}
	
	public void createDataBase() throws IOException{
		 boolean dbExist = checkDataBase();
		 if(dbExist)return;
		 else{
			 	this.getReadableDatabase();
			 	try
			 	{
			 		copyDataBase();
			 	} 
			 	catch(IOException e) 
			 	{
				 	throw new Error("Error copying database");
			 	}
			 }
		 }
	
	private boolean checkDataBase(){
		 
    	SQLiteDatabase checkDB = null;
    	try
    	{
    		String myPath = DB_PATH + DB_NAME;
    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    	}
    	catch(SQLiteException e){
    	}
    	if(checkDB != null) checkDB.close();
    	return checkDB!= null?true:false;
    }
	
	 private void copyDataBase() throws IOException{
		 
	    	//Open your local db as the input stream
	    	InputStream myInput = myContext.getAssets().open(DB_NAME);
	 
	    	// Path to the just created empty db
	    	String outFileName = DB_PATH + DB_NAME;
	 
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
	 
	    }
	 
	 public void openDataBase() throws SQLException{
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 	}
	 
	    @Override
		public synchronized void close() {
	    	if(myDataBase != null) myDataBase.close();
	    	super.close();
	    }
	public String[][] getEventbyDay(int Day)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		/*Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
				KEY_DAY,KEY_EVENT_NAME,KEY_VENUE,KEY_START_TIME,
				KEY_END_TIME,KEY_DESCRIPTION,KEY_COORDI,KEY_DATE,KEY_TYPE}, KEY_DAY + "=?",
				new String[] { String.valueOf(Day) }, null, null, null, null);*/
		Cursor cursor=db.rawQuery("SELECT * FROM table_details WHERE day='"+Day+"' ORDER BY  start_time",null);
		String data[][] = new String[cursor.getCount()][cursor.getColumnCount()];

		if (cursor != null) {
			Log.i("DEBUG","Coloumn count :"+cursor.getColumnCount());
	        int i = 0;
	        while (cursor.moveToNext()) {
	            int j = 0;
	            while (j < cursor.getColumnCount()) {
	                data[i][j] = cursor.getString(j);
	                Log.i("DEBUG","data["+i+"]["+j+"]: "+data[i][j]);
	                j++;
	            }
	            i++;
	        }
	        cursor.close();
	    }
	    return data;
	}
	
	public String[][] getEventbyType(String type,int Day)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor=db.rawQuery("SELECT * FROM table_details WHERE day='"+Day+"' AND TYPE='"+type+"' ORDER BY  start_time",null);
		String data[][] = new String[cursor.getCount()][cursor.getColumnCount()];

		if (cursor != null) {
			Log.i("DEBUG","Coloumn count :"+cursor.getColumnCount());
	        int i = 0;
	        while (cursor.moveToNext()) {
	            int j = 0;
	            while (j < cursor.getColumnCount()) {
	                data[i][j] = cursor.getString(j);
	                Log.i("DEBUG","data["+i+"]["+j+"]: "+data[i][j]);
	                j++;
	            }
	            i++;
	        }
	        cursor.close();
	    }
	    return data;
		
	}
	
	public String[][] getReducedEvent(String time, String type,int Day)
	{
		SQLiteDatabase db=this.getReadableDatabase();
		Cursor cursor = null;
		if(type!="All") cursor=db.rawQuery("SELECT * FROM table_details WHERE day='"+Day+"' AND end_time>'"+time+"' AND type='"+type+"'",null);
		else cursor=db.rawQuery("SELECT * FROM table_details WHERE day='"+Day+"' AND end_time>'"+time+"'",null);
		String data[][] = new String[cursor.getCount()][cursor.getColumnCount()];

		if (cursor != null) {
			Log.i("DEBUG","Coloumn count :"+cursor.getColumnCount());
	        int i = 0;
	        while (cursor.moveToNext()) {
	            int j = 0;
	            while (j < cursor.getColumnCount()) {
	                data[i][j] = cursor.getString(j);
	                Log.i("DEBUG","data["+i+"]["+j+"]: "+data[i][j]);
	                j++;
	            }
	            i++;
	        }
	        cursor.close();
	    }
	    return data;
		
	}
	    
	@Override
	public void onCreate(SQLiteDatabase db) {
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		
	}

}
