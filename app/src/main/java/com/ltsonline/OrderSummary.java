package com.ltsonline;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

public class OrderSummary extends AppCompatActivity {

    TextView total_amount, bag_total, big_discount, sub_total, coupon_discount, delivery, delivery_charge, total_paybale,
            delivery_option, submit, cart_full_address, pay_wallet, order_summary_wallet_amount;

    String uid, cart_id, payment_type = "", payable_amount, pay_amount, wallet_amount = "0", ccavenu_type = "1";
    private RadioButton r2, r3;
    CheckBox check;
    LinearLayout cod_linear, pay_wallet_linear;
    ImageView edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            GlobalElements.setStatusBar(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_order_summary);
        GlobalElements.setActionBar(OrderSummary.this, "Payment");

        total_amount = (TextView) findViewById(R.id.cart_total_item);
        bag_total = (TextView) findViewById(R.id.cart_bag_total);
        big_discount = (TextView) findViewById(R.id.cart_big_discount);
        sub_total = (TextView) findViewById(R.id.cart_sub_total);
        coupon_discount = (TextView) findViewById(R.id.cart_copun_discount);
        cart_full_address = (TextView) findViewById(R.id.cart_full_address);

        delivery = (TextView) findViewById(R.id.cart_delivery);
        delivery_charge = (TextView) findViewById(R.id.cart_delivery_charge);
        delivery_option = (TextView) findViewById(R.id.cart_deliver_option);
        pay_wallet = (TextView) findViewById(R.id.cart_total_pay_wallet);
        order_summary_wallet_amount = (TextView) findViewById(R.id.order_summary_wallet_amount);

        total_paybale = (TextView) findViewById(R.id.cart_total_payabale);
        submit = (TextView) findViewById(R.id.cart_submit);
        edit = (ImageView) findViewById(R.id.order_summery_edit);
        check = (CheckBox) findViewById(R.id.order_summary_checkbox);
        r2 = (RadioButton) findViewById(R.id.radioButton1);
        r3 = (RadioButton) findViewById(R.id.radioButton2);
        cod_linear = (LinearLayout) findViewById(R.id.order_summary_linear);
        pay_wallet_linear = (LinearLayout) findViewById(R.id.order_summary_wallet_pay_linear);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderSummary.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        check.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                try {
                    if (isChecked) {
                        r3.setEnabled(false);
                        double total = Double.parseDouble("" + payable_amount) - Double.parseDouble(wallet_amount);
                        if (Double.parseDouble(wallet_amount) > Double.parseDouble("" + payable_amount)) {
                            payment_type = "1";
                            pay_amount = "" + payable_amount;
                            pay_wallet.setText("Rs." + payable_amount);
                            total_paybale.setText("Rs.0");
                            pay_wallet_linear.setVisibility(View.VISIBLE);

                        } else {
                            payment_type = "2";
                            ccavenu_type = "0";
                            pay_amount = "" + total;
                            pay_wallet.setText("Rs." + wallet_amount);
                            total_paybale.setText("Rs." + pay_amount);
                            pay_wallet_linear.setVisibility(View.VISIBLE);
                        }
                    } else {
                        ccavenu_type = "1";
                        payment_type = "2";
                        pay_amount = payable_amount;
                        total_paybale.setText("Rs." + payable_amount);
                        pay_wallet_linear.setVisibility(View.GONE);
                        r3.setEnabled(true);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_type = "2";
                r2.setChecked(true);
                r3.setChecked(false);
            }
        });

        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payment_type = "3";
                r2.setChecked(false);
                r3.setChecked(true);
            }
        });

        bag_total.setText("Rs.0");
        big_discount.setText("Rs.0");
        sub_total.setText("Rs.0");
        coupon_discount.setText("Rs.0");
        delivery.setText("--");
        total_paybale.setText("Rs.0");

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
}
