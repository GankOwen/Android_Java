package com.example.class12database;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.class12database.data.ContactContract.ContactEntry;
import com.example.class12database.data.LoaderID;

public class ContactEditorActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    EditText name_editText;
    EditText phone_editText;
    EditText email_editText;
    EditText address_editText;
    Uri currentUri;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.contact_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.contactEditor_save_item:
                saveContact();
                break;
            case R.id.contactEditor_delete_item:
                deleteContact();
                break;
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this); //we can change finish(), so that we can avoid keep onCreate();
                break;
        }
        return true;
    }

    private void deleteContact() {
        getContentResolver().delete(currentUri, null, null);
        Toast.makeText(this, "delete success", Toast.LENGTH_SHORT).show();
        finish();
    }

    private void saveContact() {
        /*String name = name_editText.getText().toString().trim(); // trim to replace empty space, front and back empty space
        String phone = phone_editText.getText().toString().trim();
        String email = email_editText.getText().toString().trim();
        String address = address_editText.getText().toString().trim();*/


        if (name_editText.getText().toString().matches("\\s*")) { // one \s represent 1 empty space, \s* represent any number of empty space, the first \ escape right side \
            Toast.makeText(this, "Name is invalid", Toast.LENGTH_SHORT).show();
        } else if (phone_editText.getText().toString().matches("\\s*")) {
            Toast.makeText(this, "Phone is invalid", Toast.LENGTH_SHORT).show();
        } else if (address_editText.getText().toString().matches("\\s*")) {
            Toast.makeText(this, "Address is invalid", Toast.LENGTH_SHORT).show();
        } else if (email_editText.getText().toString().matches("\\s*")) {
            Toast.makeText(this, "Email is invalid", Toast.LENGTH_SHORT).show();
        } else {
            ContentValues values = new ContentValues();
            values.put(ContactEntry.COLUMN_NAME, name_editText.getText().toString().trim());
            values.put(ContactEntry.COLUMN_ADDRESS, address_editText.getText().toString().trim());
            values.put(ContactEntry.COLUMN_EMAIL, email_editText.getText().toString().trim());
            values.put(ContactEntry.COLUMN_PHONE, phone_editText.getText().toString().trim());

            if (currentUri == null) {
                getContentResolver().insert(ContactEntry.CONTENT_URI, values);
                Toast.makeText(this, "insert success", Toast.LENGTH_SHORT).show();
            } else {

                int updateRow = getContentResolver().update(currentUri, values, null, null);
                Log.v("Apple", ""+updateRow);
            }
            finish(); //return to previous page;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_editor);

        name_editText = findViewById(R.id.contactEditor_name_editView);
        phone_editText = findViewById(R.id.contactEditor_phone_editView);
        email_editText = findViewById(R.id.contactEditor_email_editView);
        address_editText = findViewById(R.id.contactEditor_address_editView);
        currentUri = getIntent().getData();
        if (currentUri == null) {
            setTitle("Add Contact");
        } else {
            setTitle("Edit Contact");
        }

        if (currentUri != null) {
            getSupportLoaderManager().initLoader(LoaderID.CONTACT_LOADER, null, this);
        }


    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, currentUri, null, null, null, null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        if (data.moveToFirst()) {//if we have something, we will move to first, to check if empty
            String name = data.getString(data.getColumnIndex(ContactEntry.COLUMN_NAME));
            String phone = data.getString(data.getColumnIndex(ContactEntry.COLUMN_PHONE));
            String address = data.getString(data.getColumnIndex(ContactEntry.COLUMN_ADDRESS));
            String email = data.getString(data.getColumnIndex(ContactEntry.COLUMN_EMAIL));
            name_editText.setText(name);
            phone_editText.setText(phone);
            address_editText.setText(address);
            email_editText.setText(email);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        //loader will work with life-cycle
        Log.v("what","success");
        name_editText.setText("");
        phone_editText.setText("");
        address_editText.setText("");
        email_editText.setText("");
    }
}