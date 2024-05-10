package com.example.e_library;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "librarysystem.db";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Member";
    public static final String COL_FIRST_NAME = "FirstName";
    public static final String COL_LAST_NAME = "LastName";
    public static final String COL_CONTACT = "Contact";
    public static final String COL_EMAIL = "Email";
    public static final String COL_USERNAME = "UserName";
    public static final String COL_PASSWORD = "Password";

    public static final String TABLE_NAME_BOOK = "Book";
    public static final String COL_BOOK_ID = "BookID";
    public static final String COL_BOOK_NAME = "BookName";
    public static final String COL_BOOK_PUBLISHER = "BookPublisher";
    public static final String COL_BOOK_AUTHOR = "BookAuthor";
    public static final String COL_BRANCH = "Branch";

    public static final String TABLE_NAME_PUBLISHER = "Publisher";
    public static final String COL_PUBLISHER_NAME = "Name";
    public static final String COL_PUBLISHER_ADDRESS = "Address";
    public static final String COL_PUBLISHER_PHONE = "Phone";

    public static final String TABLE_NAME_BRANCH = "Branch";
    public static final String COL_BRANCH_ID = "BranchID";
    public static final String COL_BRANCH_NAME = "BranchName";
    public static final String COL_BRANCH_ADDRESS = "BranchAddress";

    public static final String TABLE_NAME_BOOK_LOAN = "BookLoan";
    public static final String COL_ACCESS_NO = "AccessNo";
    public static final String COL_DATE_OUT = "DateOut";
    public static final String COL_DATE_DUE = "DateDue";
    public static final String COL_DATE_RETURNED = "DateReturned";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createMemberTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_FIRST_NAME + " TEXT, " +
                COL_LAST_NAME + " TEXT, " +
                COL_CONTACT + " TEXT, " +
                COL_EMAIL + " TEXT, " +
                COL_USERNAME + " TEXT, " +
                COL_PASSWORD + " TEXT)";

        String createBookTableQuery = "CREATE TABLE " + TABLE_NAME_BOOK + " (" +
                COL_BOOK_ID + " TEXT PRIMARY KEY, " +
                COL_BOOK_NAME + " TEXT, " +
                COL_BOOK_PUBLISHER + " TEXT, " +
                COL_BOOK_AUTHOR + " TEXT, " +
                COL_BRANCH + " TEXT)";

        String createPublisherTableQuery = "CREATE TABLE " + TABLE_NAME_PUBLISHER + " (" +
                COL_PUBLISHER_NAME + " TEXT PRIMARY KEY, " +
                COL_PUBLISHER_ADDRESS + " TEXT, " +
                COL_PUBLISHER_PHONE + " TEXT)";

        String createBranchTableQuery = "CREATE TABLE " + TABLE_NAME_BRANCH + " (" +
                COL_BRANCH_ID + " TEXT PRIMARY KEY, " +
                COL_BRANCH_NAME + " TEXT, " +
                COL_BRANCH_ADDRESS + " TEXT)";

        String createBookLoanTableQuery = "CREATE TABLE " + TABLE_NAME_BOOK_LOAN + " (" +
                COL_ACCESS_NO + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_BRANCH_ID + " TEXT, " +
                "CardNo VARCHAR(50), " +
                COL_DATE_OUT + " VARCHAR(50), " +
                COL_DATE_DUE + " VARCHAR(50), " +
                COL_DATE_RETURNED + " VARCHAR(50))";

        db.execSQL(createMemberTableQuery);
        db.execSQL(createBookTableQuery);
        db.execSQL(createPublisherTableQuery);
        db.execSQL(createBranchTableQuery);
        db.execSQL(createBookLoanTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOK);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_PUBLISHER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BRANCH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BOOK_LOAN);
        onCreate(db);
    }

    public Cursor searchUsers(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK + " WHERE " + COL_BOOK_NAME + " LIKE '%" + text + "%' OR " + COL_BOOK_AUTHOR + " LIKE '%" + text + "%'";
        return db.rawQuery(query, null);
    }

    public Cursor getAllBooks() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK;
        return db.rawQuery(query, null);
    }

    public Cursor searchUsers1(String text) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK + " WHERE " + COL_BOOK_NAME + " LIKE '%" + text + "%' OR " + COL_BOOK_AUTHOR + " LIKE '%" + text + "%'";
        return db.rawQuery(query, null);
    }

    public Cursor viewData() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME_BOOK;
        return db.rawQuery(query, null);
    }
    public boolean isValidUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        String selection = COL_USERNAME + " = ? AND " + COL_PASSWORD + " = ?";
        String[] selectionArgs = {username, password};
        Cursor cursor = db.query(TABLE_NAME, null, selection, selectionArgs, null, null, null);
        boolean isValid = cursor.getCount() > 0;
        cursor.close();
        return isValid;
    }
    public Cursor getAllMembers() {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        return db.rawQuery(query, null);
    }

}
