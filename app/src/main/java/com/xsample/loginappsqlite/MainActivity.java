package com.xsample.loginappsqlite;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "UserManagemntt";
    EditText editTextmEmail,editTextmPassword;

    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextmEmail = findViewById(R.id.editTextEmail);
        editTextmPassword = findViewById(R.id.editTextPassword);

        sqLiteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        
        createTable();

    }

    private void createTable() {
        sqLiteDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS employ1 (\n" +
                        "    id INTEGER  PRIMARY KEY autoincrement,\n" +
                        "    email varchar(200) NOT NULL,\n" +
                        "    password varchar(200) NOT NULL\n" +
                        ");"
        );
    }

    public void saveUser(View view) {
        String email = editTextmEmail.getText().toString().trim();
        String password = editTextmPassword.getText().toString().trim();

        if(inputsAreCorrect(email,password)){
            String insertSQL = "INSERT INTO employ1 \n" +
                    "(email,password)\n" +
                    "VALUES \n" +
                    "(?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            sqLiteDatabase.execSQL(insertSQL, new String[]{email,password});
            Toast.makeText(this, "User Added Successfully", Toast.LENGTH_SHORT).show();
        }

    }
    private boolean inputsAreCorrect(String email, String password) {
        if (email.isEmpty()) {
            editTextmEmail.setError("Please enter a email");
            editTextmEmail.requestFocus();
            return false;
        }

        if (password.isEmpty()) {
            editTextmPassword.setError("Please enter password");
            editTextmPassword.requestFocus();
            return false;
        }
        return true;
    }
}
