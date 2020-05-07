package com.example.smsboomer.ui.stats;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.content.CursorLoader;

import com.example.smsboomer.MainActivity;
import com.example.smsboomer.R;

public class StatsFragment extends Fragment {

    private StatsViewModel statsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        statsViewModel =
                ViewModelProviders.of(this).get(StatsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_stats, container, false);
        final TextView smsTotal = root.findViewById(R.id.sms_total);
        final TextView contactsTotal = root.findViewById(R.id.contact_total);

        statsViewModel.getTotalSMS().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                int permissionCheck = ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.READ_SMS);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    smsTotal.setText(s + " : " + getTotalSms());
                }
            }
        });

        statsViewModel.getTotalContacts().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                int permissionCheck = ContextCompat.checkSelfPermission(
                        getContext(), Manifest.permission.READ_CONTACTS);

                if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
                    contactsTotal.setText(s + " : " + getTotalContacts());
                }
            }
        });
        return root;
    }


    public String getTotalSms() {

        int total = 0;
        Uri SMS_INBOX = Uri.parse("content://sms/conversations/");

        CursorLoader cl = new CursorLoader(getContext(), SMS_INBOX, null, null, null, null);

        Cursor c = cl.loadInBackground();

        String[] count = new String[c.getCount()];
        String[] snippet = new String[c.getCount()];
        String[] thread_id = new String[c.getCount()];

        c.moveToFirst();
        for (int i = 0; i < c.getCount(); i++) {
            count[i] = c.getString(c.getColumnIndexOrThrow("msg_count"));
            total += Integer.parseInt(count[i]);
            c.moveToNext();
        }
        return Integer.toString(total);
    }

    public String getTotalContacts() {

        int total = 0;
        Uri CONTACTS = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        CursorLoader cl = new CursorLoader(getContext(), CONTACTS, null, null, null, null);

        Cursor c = cl.loadInBackground();

        total = c.getCount();
        return Integer.toString(total);
    }
}
