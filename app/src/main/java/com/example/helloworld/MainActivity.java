package com.example.helloworld;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    int x,y,z; // Save/Restore in Activity's instance state

    TextView tvHello;
    EditText editTextHello;
    Button btnCopy;

    ///////////////////////
    //Start Calculator here
    ///////////////////////
    EditText editText1;
    EditText editText2;
    TextView tvResult;
    Button btnCalculate;
    RadioGroup rgOperator;

    int width;
    int height;

    private CustomViewGroup viewGroup1;
    private CustomViewGroup viewGroup2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getResources().getBoolean(R.bool.portrait_only)) {
            //Fix screen orientation to Portrait
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        setContentView(R.layout.welcome);

        //initInstances();

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x; // Screen Width
        height = size.y; // Screen Height
        Toast.makeText(MainActivity.this, "Width = " + width + ", Height = " + height, Toast.LENGTH_SHORT).show();

        viewGroup1 = (CustomViewGroup) findViewById(R.id.viewGroup1);
        viewGroup2 = (CustomViewGroup) findViewById(R.id.viewGroup2);

//        viewGroup1.setButtonText("Hello");
//        viewGroup2.setButtonText("World");

    }

    private void initInstances() {
        tvHello = (TextView) findViewById(R.id.tvHello);
        tvHello.setMovementMethod(LinkMovementMethod.getInstance());
        tvHello.setText(Html.fromHtml("<b>Hello</b> <i>World</i> <font color=\"#fafafa\">La la la</font> <a href=\"https://www.google.com/\">Google"));

        editTextHello = (EditText) findViewById(R.id.editTextHello);
        editTextHello.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // Copy text in EditText to TextView
                    tvHello.setText(editTextHello.getText());
                    //Handle
                    return true;
                }
                return false;
            }
        });

        btnCopy = (Button) findViewById(R.id.btnCopy);
        btnCopy.setOnClickListener(this);

        ///////////////////////
        //Start Calculator Here
        ///////////////////////

        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        tvResult = findViewById(R.id.tvResult);

        btnCalculate = findViewById(R.id.btnCalculate);
        btnCalculate.setOnClickListener(this);

        rgOperator = findViewById(R.id.rgOperator);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable @org.jetbrains.annotations.Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Check if it is a result from SecondActivity
        if(requestCode == 12345) {
            if(resultCode == RESULT_OK) {
                //Get data from data's extra
                String result = data.getStringExtra("result");
                Toast.makeText(MainActivity.this,
                            result,
                            Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v == btnCopy) {
            tvHello.setText(editTextHello.getText());
        }
        if (v == btnCalculate) {
            int val1 = 0;
            int val2 = 0;
            int result = 0;
            try {
                val1 = Integer.parseInt(editText1.getText().toString());
            } catch (NumberFormatException e) {
            }
            try {
                val2 = Integer.parseInt(editText2.getText().toString());
            } catch (NumberFormatException e) {
            }

            switch (rgOperator.getCheckedRadioButtonId()) {
                case R.id.rbPlus:
                    result = val1 + val2;
                case R.id.rbMinus:
                    result = val1 - val2;
                    break;
                case R.id.rbMultiply:
                    result = val1 * val2;
                    break;
                case R.id.rbDevide:
                    result = val1 / val2;
                    break;
            }
            tvResult.setText("= " + result);

            Log.d("Calculation", "Result = " + result);

            Toast.makeText(MainActivity.this, "Result = " + result, Toast.LENGTH_SHORT).show();

            //send to next activity
            Intent intent = new Intent(MainActivity.this,
                    SecondActivity.class);
            intent.putExtra("result",result);

            //Playground
            Coordinate c1 = new Coordinate();
            c1.x = 5;
            c1.y = 10;
            c1.z = 20;
            Bundle bundle = new Bundle();
            bundle.putInt("x", c1.x);
            bundle.putInt("y", c1.y);
            bundle.putInt("z", c1.z);
            intent.putExtra("cBundle", bundle);

            //Serializable Lab
            CooridinateSerializable c2 = new CooridinateSerializable();
            c2.x = 5;
            c2.y = 10;
            c2.z = 20;
            intent.putExtra("cSerialazable", c2);

            CooridinateParcelable c3 = new CooridinateParcelable();
            c3.x = 5;
            c3.y = 10;
            c3.z = 20;
            intent.putExtra("cParcelable", c3);

            startActivityForResult(intent, 12345);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.action_setting) {
            // Do what you want

            //Handled
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onSaveInstanceState(@NonNull @org.jetbrains.annotations.NotNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //Save only Activity thing(s) here

    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Restore only Activity thing(s) here

    }
}