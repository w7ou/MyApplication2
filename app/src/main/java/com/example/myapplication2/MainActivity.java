package com.example.myapplication2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
{
    EditText amount_editText;
    SeekBar size_seekBar;
    TextView tip15_textView;
    TextView total15_textView;
    TextView tip_textView;
    TextView total_textView;
    TextView textView6;

    OnSeekBarChangeHandler seekBarHandler = new OnSeekBarChangeHandler();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        amount_editText=(EditText) findViewById(R.id.amount_editText);
        size_seekBar=(SeekBar) findViewById(R.id.size_seekBar);
        tip15_textView=(TextView) findViewById(R.id.tip15_textView);
        total15_textView=(TextView) findViewById(R.id.total15_textView);
        tip_textView=(TextView) findViewById(R.id.tip_textView);
        total_textView=(TextView) findViewById(R.id.total_textView);
        textView6=(TextView) findViewById(R.id.textView6);
        seekBarHandler = new OnSeekBarChangeHandler();
        size_seekBar.setOnSeekBarChangeListener(seekBarHandler);

        TextWatcherHandler editTextHandler = new TextWatcherHandler();
        amount_editText.addTextChangedListener(editTextHandler);
    }

    public class OnSeekBarChangeHandler implements SeekBar.OnSeekBarChangeListener
    {
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser)
        {
            String amountText = amount_editText.getText().toString();
            if (amountText.isEmpty()) {
                return;
            }

            double amount = Double.parseDouble(amount_editText.getText().toString());
            double tipPercent=progress;
            double tip = amount * progress / 100;
            double total = amount + tip;
            double tip15 = amount * 0.15;
            double total15 = amount + tip15;
            textView6.setText(String.format("%.0f",tipPercent)+"%");
            tip15_textView.setText(String.format("%.2f",tip15));
            total15_textView.setText(String.format("%.2f", total15));
            tip_textView.setText(String.format("%.2f", tip));
            total_textView.setText(String.format("%.2f", total));
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar){}

        @Override
        public void onStopTrackingTouch(SeekBar seekBar){}
    }

    public class TextWatcherHandler implements TextWatcher
    {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

        public void onTextChanged(CharSequence s, int start, int before, int count)
        {
            seekBarHandler.onProgressChanged(size_seekBar, size_seekBar.getProgress(), false);
        }

        public void afterTextChanged(Editable s){}
    }
}

