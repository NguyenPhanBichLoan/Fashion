package com.fashion.firebase.dlfashion.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.fashion.firebase.dlfashion.utils.Constants;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class DatabaseHelper extends SQLiteOpenHelper {

    private SQLiteDatabase mDatabase;
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void createDatabase() {
        boolean mDatabaseExist = checkDatabase();
        if (!mDatabaseExist) {
            this.getReadableDatabase();
            this.close();
            try {
                copyDatabase();
            } catch (IOException mIOException) {
                throw new Error("Error!!!");
            }
        }
    }

    public boolean checkDatabase() {
        File dbFile = new File(Constants.DATABASE_PATH + Constants.DATABASE_NAME);
        return dbFile.exists();
    }

    public void copyDatabase() throws IOException {
        InputStream mInput = context.getAssets().open(Constants.DATABASE_NAME);
        String outFileName = Constants.DATABASE_PATH + Constants.DATABASE_NAME;
        OutputStream mOutput = new FileOutputStream(outFileName);
        byte[] mBuffer = new byte[1024];
        int mLength;
        while ((mLength = mInput.read(mBuffer)) > 0) {
            mOutput.write(mBuffer, 0, mLength);
        }
        mOutput.flush();
        mOutput.close();
        mInput.close();
    }

    public boolean openDatabase() {
        String mPath = Constants.DATABASE_PATH + Constants.DATABASE_NAME;
        mDatabase = SQLiteDatabase.openDatabase(mPath, null, SQLiteDatabase.CREATE_IF_NECESSARY);
        return mDatabase != null;
    }

    @Override
    public synchronized void close() {
        if (mDatabase != null)
            mDatabase.close();
        super.close();
    }
}
