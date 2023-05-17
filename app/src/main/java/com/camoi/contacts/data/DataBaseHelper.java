package com.camoi.contacts.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.camoi.contacts.ContactModel;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String CONTACT_TABLE = "contacts";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_CONTACT_NAME = "name";
    public static final String COLUMN_CONTACT_PHONE = "phone";

    public DataBaseHelper(@Nullable Context context) {
        super(context, "contacts.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CONTACT_TABLE + " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CONTACT_NAME + " TEXT, " + COLUMN_CONTACT_PHONE + " CHAR(13))";
        db.execSQL(createTableStatement);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //add new contact
    public boolean addOne(ContactModel contactModel) {
        SQLiteDatabase db =this.getWritableDatabase();
        ContentValues cv =new ContentValues();
        cv.put(COLUMN_CONTACT_NAME, contactModel.getName());
        cv.put(COLUMN_CONTACT_PHONE, contactModel.getPhone());
        long insert =db.insert(CONTACT_TABLE, null, cv);
        if(insert == -1) {
            return false;
        }
        else {
            return true;
        }
    }

    //delete contact
    public boolean deleteOne(ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "DELETE FROM " + CONTACT_TABLE + " WHERE " + COLUMN_ID + " = " + contactModel.getId();
        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }
    }

    //update contact
    public boolean updateOne(ContactModel contactModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = "UPDATE " + CONTACT_TABLE + " SET " + COLUMN_CONTACT_NAME + " = " + contactModel.getName() +
                " , " + COLUMN_CONTACT_PHONE + " = " + contactModel.getPhone() + " WHERE " + COLUMN_ID + " = " + contactModel.getId();

        Cursor cursor = db.rawQuery(queryString, null);
        if(cursor.moveToFirst()) {
            return true;
        }
        else {
            return false;
        }

    }

    public List<ContactModel> getEveryone() {
        List<ContactModel> returnList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String queryString = "SELECT * FROM " + CONTACT_TABLE;
        Cursor cursor = db.rawQuery(queryString, null);

        if(cursor.moveToFirst()) {

            do {
                int contactID = cursor.getInt(0);
                String contactName = cursor.getString(1);
                String contactPhone = cursor.getString(2);
                ContactModel contactModel = new ContactModel(contactID, contactName, contactPhone);
                returnList.add(contactModel);
            } while(cursor.moveToNext());
        }
        else {
            //failure. do not add any thing to the list
        }
        cursor.close();
        db.close();
        return returnList;
    }
}

