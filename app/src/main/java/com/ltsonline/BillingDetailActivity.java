package com.ltsonline;

import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.ltsonline.custom.MyTextWatcher;

import fr.ganfra.materialspinner.MaterialSpinner;

public class BillingDetailActivity extends AppCompatActivity {

    MaterialSpinner spin_country;
    TextInputLayout inputLayout_name, inputLayout_address, inputLayout_locality, inputLayout_city, inputLayout_state, inputLayout_zip, inputLayout_phone;
    EditText name, address, locality, city, state, zip, phone;
    TextView save;

    int country_id;
    String uid, cart_id, _name, _address, _locality, _city, _state, _zip, _phone, _otp, _old_password, _new_password, _con_password;
    String pincode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_billing_detail);
        GlobalElements.setActionBar(BillingDetailActivity.this, "Billing Detail");

        spin_country = (MaterialSpinner) findViewById(R.id.input_spinner);
        inputLayout_name = (TextInputLayout) findViewById(R.id.edit_profile_layout_name);
        inputLayout_address = (TextInputLayout) findViewById(R.id.edit_profile_layout_address);
        inputLayout_locality = (TextInputLayout) findViewById(R.id.edit_profile_layout_locality);
        inputLayout_city = (TextInputLayout) findViewById(R.id.edit_profile_layout_city);
        inputLayout_state = (TextInputLayout) findViewById(R.id.edit_profile_layout_state);
        inputLayout_zip = (TextInputLayout) findViewById(R.id.edit_profile_layout_zip);
        inputLayout_phone = (TextInputLayout) findViewById(R.id.edit_profile_layout_phone);

        name = (EditText) findViewById(R.id.edit_profile_name);
        address = (EditText) findViewById(R.id.edit_profile_address);
        locality = (EditText) findViewById(R.id.edit_profile_locality);
        city = (EditText) findViewById(R.id.edit_profile_city);
        state = (EditText) findViewById(R.id.edit_profile_state);
        zip = (EditText) findViewById(R.id.edit_profile_zip);
        phone = (EditText) findViewById(R.id.edit_profile_phone);
        save = (TextView) findViewById(R.id.edit_profile_save);

        name.addTextChangedListener(new MyTextWatcher(inputLayout_name));
        address.addTextChangedListener(new MyTextWatcher(inputLayout_address));
        locality.addTextChangedListener(new MyTextWatcher(inputLayout_locality));
        city.addTextChangedListener(new MyTextWatcher(inputLayout_city));
        state.addTextChangedListener(new MyTextWatcher(inputLayout_state));
        zip.addTextChangedListener(new MyTextWatcher(inputLayout_zip));
        phone.addTextChangedListener(new MyTextWatcher(inputLayout_phone));

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatename()) {
                    return;
                }
                if (!validateaddress()) {
                    return;
                }
                if (!validatelocality()) {
                    return;
                }
                if (!validatecity()) {
                    return;
                }
                if (!validatestate()) {
                    return;
                }
                if (!validatestate()) {
                    return;
                }
                if (!validateZip()) {
                    return;
                }
                if (!validatePhone()) {
                    return;
                }

                Intent i = new Intent(BillingDetailActivity.this, OrderSummary.class);
                i.putExtra("uid", uid);
                i.putExtra("cart_id", cart_id);
                startActivity(i);

                if (GlobalElements.isConnectingToInternet(BillingDetailActivity.this)) {
                    //new AddBilling().execute("update_billing");
                } else {
                    GlobalElements.showDialog(BillingDetailActivity.this);
                }
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validatename() {
        if (name.getText().toString().equals("")) {
            inputLayout_name.setError(getString(R.string.err_msg_name));
            requestFocus(name);
            return false;
        } else {
            inputLayout_name.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateaddress() {
        if (address.getText().toString().equals("")) {
            inputLayout_address.setError(getString(R.string.err_msg_address));
            requestFocus(address);
            return false;
        } else {
            inputLayout_address.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatelocality() {
        if (locality.getText().toString().equals("")) {
            inputLayout_locality.setError(getString(R.string.err_msg_locality));
            requestFocus(locality);
            return false;
        } else {
            inputLayout_locality.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatecity() {
        if (city.getText().toString().equals("")) {
            inputLayout_city.setError(getString(R.string.err_msg_city));
            requestFocus(city);
            return false;
        } else {
            inputLayout_city.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatestate() {
        if (state.getText().toString().equals("")) {
            inputLayout_state.setError(getString(R.string.err_msg_state));
            requestFocus(state);
            return false;
        } else {
            inputLayout_state.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validatePhone() {
        if (phone.getText().toString().length() < 10) {
            inputLayout_phone.setError(getString(R.string.err_msg_phone));
            requestFocus(phone);
            return false;
        } else {
            inputLayout_phone.setErrorEnabled(false);
        }
        return true;
    }

    private boolean validateZip() {
        if (zip.getText().toString().length() < 6) {
            inputLayout_zip.setError(getString(R.string.err_msg_zip));
            requestFocus(zip);
            return false;
        } else {
            inputLayout_zip.setErrorEnabled(false);
        }
        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

}
