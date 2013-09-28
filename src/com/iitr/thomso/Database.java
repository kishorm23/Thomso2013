package com.iitr.thomso;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorIndexOutOfBoundsException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


	public class Database {

		public static final String ROWID = "_id";
		public static final String NAME = "_name";
		public static final String VENUE = "_venue";
		public static final String TIME = "_time";
		public static final String LATTITUDE = "_lat";
		public static final String LONGITUDE = "_lon";
		public static final String COMMENTS = "_comments";//// COMMENTS INCLUDES DESCRIPTION AND CONTACTS OF COORDINATERS 
		private static final String AMT_DB = "AMOUNTDB";
		private static final String TABLE0 = "DAY0";
		private static final String TABLE1 = "DAY1";
		private static final String TABLE2 = "DAY2";
		private static final String TABLE3 = "DAY3";
		private static final int DBVERSION = 1;

		private Dbmaker1 maker;
		private final Context context;
		private SQLiteDatabase database;

		private static class Dbmaker1 extends SQLiteOpenHelper {

			public Dbmaker1(Context context) {
				super(context, AMT_DB, null, DBVERSION);
				// TODO Auto-generated constructor stub
			}

			@Override
			public void onCreate(SQLiteDatabase db) {
				// TODO Auto-generated method stub
				db.execSQL("CREATE TABLE " + TABLE0 + "(" + ROWID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
						+ " TEXT NOT NULL, "  + VENUE
						+ " TEXT NOT NULL, " + TIME
						+ " TEXT NOT NULL, " + LATTITUDE
						+ " TEXT NOT NULL, " + LONGITUDE
						+ " TEXT NOT NULL, " + COMMENTS
						+ " TEXT NOT NULL); ");
				db.execSQL("CREATE TABLE " + TABLE1 + "(" + ROWID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
						+ " TEXT NOT NULL, "  + VENUE
						+ " TEXT NOT NULL, " + TIME
						+ " TEXT NOT NULL, " + LATTITUDE
						+ " TEXT NOT NULL, " + LONGITUDE
						+ " TEXT NOT NULL, " + COMMENTS
						+ " TEXT NOT NULL); ");
				db.execSQL("CREATE TABLE " + TABLE2 + "(" + ROWID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
						+ " TEXT NOT NULL, "  + VENUE
						+ " TEXT NOT NULL, " + TIME
						+ " TEXT NOT NULL, " + LATTITUDE
						+ " TEXT NOT NULL, " + LONGITUDE
						+ " TEXT NOT NULL, " + COMMENTS
						+ " TEXT NOT NULL); ");
				db.execSQL("CREATE TABLE " + TABLE3 + "(" + ROWID
						+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + NAME
						+ " TEXT NOT NULL, "  + VENUE
						+ " TEXT NOT NULL, " + TIME
						+ " TEXT NOT NULL, " + LATTITUDE
						+ " TEXT NOT NULL, " + LONGITUDE
						+ " TEXT NOT NULL, " + COMMENTS
						+ " TEXT NOT NULL); ");


			}

			@Override
			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
				// TODO Auto-generated method stub
				db.execSQL("DROP TABLE IF EXISTES" + TABLE0);
				
				db.execSQL("DROP TABLE IF EXISTES" + TABLE1);
				
				db.execSQL("DROP TABLE IF EXISTES" + TABLE2);
				
				db.execSQL("DROP TABLE IF EXISTES" + TABLE3);
				onCreate(db);
			
			}

		}

		public Database(Context r) {
			context = r;

		}

		public Database open() {
			maker = new Dbmaker1(context);

			database = maker.getWritableDatabase();
			return this;
		}

		public void close() {
			maker.close();
		}

		public long createEntry(String table_num,String name, String venue,String time,String lattitude,String longitude,String comments)
				{
			// TODO Auto-generated method stub

			ContentValues cv = new ContentValues();
			cv.put(NAME, name);
			cv.put(VENUE, venue);
			cv.put(TIME, time);
			cv.put(LATTITUDE, lattitude);
			cv.put(LONGITUDE, longitude);
			cv.put(COMMENTS, comments);
			return database.insert("TABLE"+table_num, null, cv);

		}

		public void updateEntry(String table_num,String name, String venue,String time,String lattitude,String longitude,String comments)
				throws CursorIndexOutOfBoundsException {
			// TODO Auto-generated method stub



			ContentValues cv = new ContentValues();
			cv.put(NAME,name );
			cv.put(VENUE,venue );
			cv.put(TIME,time );
			cv.put(LATTITUDE,lattitude );
			cv.put(LONGITUDE,longitude );
			cv.put(COMMENTS,comments );
			database.update("TABLE"+table_num, cv, NAME + " = \'" + name + "\'," + VENUE + " = \'" + venue + "\'," + TIME + " = \'" + time + "\'," + LATTITUDE + " = \'" + lattitude + "\'," + LONGITUDE +" = \'" + longitude + "\'," + COMMENTS + " = \'" + comments + "\'", null);

		}

		
		public String getDataOfday(String table_num) {

			// TODO Auto-generated method stub
			String[] columns = new String[] { ROWID, NAME, VENUE,TIME,LATTITUDE,LONGITUDE };
			Cursor c = database.query("TABLE"+table_num, columns, null, null, null, null, null);
			String result = "";

			int iName = c.getColumnIndex(NAME);

			for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				result = result + c.getString(iName) + "\n";

			}

			return result;
		}

		public String getComments(String table_num,String name) {
			// TODO Auto-generated method stub
			String[] columns = new String[] { COMMENTS };
			Cursor c = database.query("TABLE"+table_num, columns, null, null, null, null, null);

			int iName = c.getColumnIndex(NAME);

			return	c.getString(iName);
			}
	

		public void deleteEntryof(String table_num,String name) {
			// TODO Auto-generated method stub
			database.delete("TABLE"+table_num, NAME + "=?", new String[] { name });
		}

	}


