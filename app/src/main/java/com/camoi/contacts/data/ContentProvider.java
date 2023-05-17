package com.camoi.contacts.data;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProvider extends android.content.ContentProvider {
    // TODO (1) Define final integer constants for the directory of tasks and a single item.
    // It's convention to use 100, 200, 300, etc for directories,
    // and related ints (101, 102, ..) for items in that directory.
    public static final int TASKS = 100;
    public static final int TASK_WITH_ID = 101;

    // TODO (3) Declare a static variable for the Uri matcher that you construct
    private static final UriMatcher sUriMatcher = buildUriMatcher();
    public static final String TABLE_NAME = "contacts";

    // TODO (2) Define a static buildUriMatcher method that associates URI's with their int match
    public static UriMatcher buildUriMatcher() {
        //construct an empty match
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        // add matches with addURI(String authority, String path, int code)
        //directory
        uriMatcher.addURI(Contract.AUTHORITY, Contract.PATH_TASKS, TASKS );
        //single item
        uriMatcher.addURI(Contract.AUTHORITY, Contract.PATH_TASKS + "/#", TASK_WITH_ID);

        return uriMatcher;
    }

    // Member variable for a TaskDbHelper that's initialized in the onCreate() method
    private DataBaseHelper mDataBaseHelper;
    /* onCreate() is where you should initialize anything you’ll need to setup
  your underlying data source.
  In this case, you’re working with a SQLite database, so you’ll need to
  initialize a DbHelper to gain access to it.
   */
    @Override
    public boolean onCreate() {
        // Complete onCreate() and initialize a TaskDbhelper on startup
        // [Hint] Declare the DbHelper as a global variable
        Context context = getContext();
        mDataBaseHelper = new DataBaseHelper(context);


        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        // TODO (1) Get access to underlying database (read-only for query)
            final SQLiteDatabase db = mDataBaseHelper.getReadableDatabase();

        // TODO (2) Write URI match code and set a variable to return a Cursor
            int match = sUriMatcher.match(uri);
        // TODO (3) Query for the tasks directory and write a default case
            Cursor retCursor;
            switch(match) {
                case TASKS:
                    retCursor = db.query(TABLE_NAME,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder);
                    break;
                default:
                    throw new UnsupportedOperationException("Unknow Uri: " + uri);
            }
        // TODO (4) Set a notification URI on the Cursor and return that Cursor
        retCursor.setNotificationUri(getContext().getContentResolver(), uri);

        return retCursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        // TODO (1) Get access to the task database (to write new data to)
        final SQLiteDatabase db = mDataBaseHelper.getWritableDatabase();
        // TODO (2) Write URI matching code to identify the match for the tasks directory
        int match  = sUriMatcher.match(uri);
        // TODO (3) Insert new values into the database
        // TODO (4) Set the value for the returnedUri and write the default case for unknown URI's
        Uri returnUri;
        switch(match){
            case TASKS:
                //insert values into task table
                long id = db.insert(TABLE_NAME, null, values);
                if (id > 0) {
                    //success
                    returnUri = ContentUris.withAppendedId(Contract.ContactEntry.CONTENT_URI, id);
                } else {
                    throw new android.database.SQLException("FAiled to insert row into " + uri);
                }
                break;

            default:
                throw new UnsupportedOperationException("Unknow uri: " + uri);
        }


        // TODO (5) Notify the resolver if the uri has been changed, and return the newly inserted URI
        getContext().getContentResolver().notify();

       return returnUri;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }
}
