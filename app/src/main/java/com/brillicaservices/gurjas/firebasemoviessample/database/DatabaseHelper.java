package com.brillicaservices.gurjas.firebasemoviessample.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.brillicaservices.gurjas.firebasemoviessample.movies.MoviesModelView;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "student_db";
    private static final String TABLE_NAME = "movies_record";
    private static final String MOVIE_TITLE = "movie_name";
    private static final String ROW_ID = "id";
    private static final String RELEASE_YEAR = "release_year";
    private static final String DESCRIPTION = "description";
    private static final String RATING = "movie_rating";
    private static final String IMAGE ="image";


    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ( "+
            ROW_ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT," + MOVIE_TITLE + " TEXT, " +
            DESCRIPTION + " TEXT, " + RATING
            + " REAL, "+ RELEASE_YEAR
            + " INTEGER , " + IMAGE + " INTEGER ); ";

    public DatabaseHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);

        onCreate(db);
    }


    public long addNewMovie(MoviesModelView MoviesModelView) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put(RELEASE_YEAR, MoviesModelView.releaseYear);
        contentValues.put(MOVIE_TITLE, MoviesModelView.title);
        contentValues.put(DESCRIPTION, MoviesModelView.description);
        contentValues.put(RATING, MoviesModelView.rating);
        contentValues.put(IMAGE , MoviesModelView.image);

        long id = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);

        sqLiteDatabase.close();

        return id;
    }


    public List<MoviesModelView> allMovieDetails() {
        List<MoviesModelView> studentsList = new ArrayList<>();

        String selectQuery = " SELECT * FROM " + TABLE_NAME ;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()){
            do {
                MoviesModelView MoviesModelView = new MoviesModelView();
                MoviesModelView.setTitle(cursor.getString(cursor.getColumnIndex(MOVIE_TITLE)));
                MoviesModelView.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                MoviesModelView.setRating(cursor.getFloat(cursor.getColumnIndex(RATING)));
                MoviesModelView.setReleaseYear(cursor.getInt(cursor.getColumnIndex(RELEASE_YEAR)));
                MoviesModelView.setImage(cursor.getInt(cursor.getColumnIndex(IMAGE )));

                studentsList.add(MoviesModelView);
            } while (cursor.moveToNext());
        }

        sqLiteDatabase.close();
        return  studentsList;
    }

    public int getMoviesCount() {

        String query = "SELECT * FROM " + TABLE_NAME;

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();

        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

        int totalStudentsCount = cursor.getCount();
        cursor.close();

        return totalStudentsCount;
    }

}