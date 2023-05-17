package com.camoi.contacts.data;

import android.net.Uri;
import android.provider.BaseColumns;

public class Contract {
/* TODO (1) Add content provider constants to the Contract
     Clients need to know how to access the task data, and it's your job to provide
     these content URI's for the path to that data:
        1) Content authority,
        2) Base content URI,
        3) Path(s) to the tasks directory
        4) Content URI for data in the TaskEntry class
      */
    //The authority, which is how your code knows which Content Provider to access
    public static  final String AUTHORITY = "com.camoi.contacts";
    // The base content URI = "content://" + <authority>
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + AUTHORITY);
    // Define the possible paths for accessing data in the contract
    // This is the path for the "data" directory
    public static final String PATH_TASKS = "data";

    /* ContactEntry is an inner class that define the contents of the contact table*/
    public static final class ContactEntry implements BaseColumns {

        // ContactEntry content URI = base content URI + path
        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build();

        // Task table and column names
        public static final String TABLE_NAME = "contacts";

        // Since TaskEntry implements the interface "BaseColumns", it has an automatically produced
        // "_ID" column in addition to the two below
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PHONE = "phone";


        /*
        The above table structure looks something like the sample table below.
        With the name of the table and columns on top, and potential contents in rows

        Note: Because this implements BaseColumns, the _id column is generated automatically

        tasks
         - - - - - - - - - - - - - - - - - - - - - -
        | _id  |    name    |    phone   |
         - - - - - - - - - - - - - - - - - - - - - -
        |  1   |  john vu  |  12233333   |
         - - - - - - - - - - - - - - - - - - - - - -
        |  2   |  can lack |   233333    |
         - - - - - - - - - - - - - - - - - - - - - -
        .
        .
        .
         - - - - - - - - - - - - - - - - - - - - - -
        | 43   |   Learn guitar     |       2       |
         - - - - - - - - - - - - - - - - - - - - - -

         */

    }

}
