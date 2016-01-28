package com.example.jacob_ke.sqlitedemoactivity;

import static android.provider.BaseColumns._ID;

import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.ContentValues;
import android.content.Context;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.jar.Attributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContentResolverActivity extends Activity implements OnClickListener {


    private ListView listData = null;
    private EditText editName = null;
    private EditText editTel = null;
    private EditText editEmail = null;
    private EditText editId = null;
    private EditText editCompany = null;
    private EditText editWork = null;
    private Button btnAdd = null;
    private Button btnDel = null;
    private Button btnUpdate = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        initView();

        showInList();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    private void initView() {
        listData = (ListView) findViewById(R.id.listData);
        editName = (EditText) findViewById(R.id.editName);
        editTel = (EditText) findViewById(R.id.editTel);
        editEmail = (EditText) findViewById(R.id.editEmail);
        editId = (EditText) findViewById(R.id.editId);
        editCompany = (EditText) findViewById(R.id.editCompany);
        editWork = (EditText) findViewById(R.id.editWork);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnDel = (Button) findViewById(R.id.btnDel);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);

        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAdd:
                add();
                break;

            case R.id.btnDel:
                del();
                break;

            case R.id.btnUpdate:
                update();
                break;
            case R.id.btnDetail:
                Detail();
            default:
                break;
        }
        showInList();
    }

    private void add() {
        Log.v("Resolver", "add");
        String Name = editName.getText().toString();
        String Tel = editTel.getText().toString();
        String Email = editEmail.getText().toString();
        String Company = editCompany.getText().toString();
        String Work = editWork.getText().toString();

        if (!Name.isEmpty()) {
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
//            int rawContactInsertIndex = ops.size();
            ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                    .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, Name) // Name of the person
                    .build());
            ops.add(ContentProviderOperation
                    .newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(
                            ContactsContract.Data.RAW_CONTACT_ID,   0)
                    .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, Tel) // Number of the person
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE).build());

            //------------------------------------------------------ Email
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, Email)
                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                    .build());

            //------------------------------------------------------ Organization
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, Company)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, Work)
                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                    .build());
            try
            {
                ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            }
            catch (RemoteException e)
            {
                // error
            }
            catch (OperationApplicationException e)
            {
                // error
            }
        }
        cleanEditText();
    }

    private Cursor getContacts() {
        // Run query
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
        };
        String selection = null;
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC";

        return managedQuery(uri, projection, selection, selectionArgs, sortOrder);
    }
    private Cursor getCommonData() {
        // Run query
        Uri uri = ContactsContract.Data.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                ContactsContract.CommonDataKinds.Phone.NUMBER,
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Organization.COMPANY,
                ContactsContract.CommonDataKinds.Organization.TITLE
        };
        String selection = null;
        String[] selectionArgs = null;

        return managedQuery(uri, projection, selection, selectionArgs, null);
    }


    private void showInList() {

        Cursor cursor = getContacts();
        String[] from = new String[]{
                ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME
                };

        int[] to = new int[]{R.id.txtId, R.id.txtName};
//        int[] to = new int[]{R.id.txtId};
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_item, cursor, from, to);
        listData.setAdapter(adapter);
    }
    private void Detail() {
        Log.v("!!!!!!!!!!!!","GJ");
        Cursor cursor = getCommonData();
        Log.v("!!!!!!!!!!!!","GJ");
//        String[] from = new String[]{ContactsContract.Contacts._ID,
//                ContactsContract.Contacts.DISPLAY_NAME};
//        int[] to = new int[]{R.id.txtId, R.id.txtName};
//        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.data_item, cursor, from, to);
//        listData.setAdapter(adapter);
    }

    private void del() {
        String id = editId.getText().toString();
        if ((isNumeric(id)) && (!id.isEmpty())) {

            String[] args = new String[] {id};
            ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
            ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                    .withSelection(ContactsContract.RawContacts.CONTACT_ID + "=?", args)
                    .build());
        try
            {
                ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            }
            catch (RemoteException e)
            {
                // error
            }
            catch (OperationApplicationException e)
            {
                // error
            }

        }
        cleanEditText();
    }

    private void update() {
        String Name = editName.getText().toString();
        String Tel = editTel.getText().toString();
        String Email = editEmail.getText().toString();
        String Company = editCompany.getText().toString();
        String Work = editWork.getText().toString();
        String id = editId.getText().toString();

        if ((isNumeric(id))&&(!id.isEmpty())) {
            ArrayList<ContentProviderOperation> ops =
                    new ArrayList<ContentProviderOperation>();

            String selection = ContactsContract.Data.CONTACT_ID + "=?" + " AND " + ContactsContract.Data.MIMETYPE + "=?";
            String[] selectionArgs = new String[] {id , ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE};
            ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI)
                    .withSelection(selection, selectionArgs)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, Name)
                    .build());

            try
            {
                ContentProviderResult[] res = getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            }
            catch (RemoteException e)
            {
                // error
            }
            catch (OperationApplicationException e)
            {
                // error
            }
        }
        cleanEditText();
    }

    private void cleanEditText() {
        editName.setText("");
        editTel.setText("");
        editEmail.setText("");
        editId.setText("");
        editCompany.setText("");
        editWork.setText("");
    }

    public boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (!isNum.matches()) {
            return false;
        }
        return true;
    }

//    public final class MyView {
//        public TextView title;
//        public TextView subtitle;
//        public Button bt;
//    }
//
//    public class btnadapter extends SimpleCursorAdapter{
//        public btnadapter(Context context, int layout, Cursor c, String[] from, int[] to) {
//            super(context, layout, c, from, to);
//            LayoutInflater inflater;
//            View convertView;
//
//            @Override
//            public View getView (final int position, View convertView) {
//                MyView myviews = null;
//                myviews = new MyView();
//                convertView = inflater.inflate(R.layout.data_item, null);
//                myviews.title = (TextView) convertView.findViewById(R.id.txtId);
//
//                myviews.subtitle = (TextView) convertView.findViewById(R.id.txtName);
//
//                myviews.bt = (Button) convertView.findViewById(R.id.btnDetail);
//
//
//                myviews.title.setText((String) list.get(position).get("title"));
//                myviews.subtitle.setText((String) list.get(position).get("subtitle"));
//
//                myviews.bt.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        Toast.makeText(Basic.this, titleArr[position], Toast.LENGTH_SHORT).show();
//                        Log.v("!!!!!!!!!!","GJ");
//                    }
//                });
//                return convertView;
//            }
//        }
//    }
}