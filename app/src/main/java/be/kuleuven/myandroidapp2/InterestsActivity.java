package be.kuleuven.myandroidapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.PaintDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Arrays;

public class InterestsActivity extends AppCompatActivity
{


    private Button sportsbtn;
    private Button actingbtn;
    private Button moviesbtn;
    private Button artbtn;
    private Button politicsbtn;
    private Button extremesportsbtn;
    private Button musicbtn;
    private Button signbtn;
    private Button foodbtn;
    private Button businessbtn;
    private Button ConfirmButton;
    private String[] array;
    private SharedPreferences newPreference;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interests);
        sportsbtn = (Button) findViewById(R.id.sports);
        actingbtn = (Button) findViewById(R.id.acting);
        moviesbtn = (Button) findViewById(R.id.movies);
        artbtn = (Button) findViewById(R.id.art);
        politicsbtn = (Button) findViewById(R.id.politics);
        extremesportsbtn = (Button) findViewById(R.id.extremeSports);
        musicbtn = (Button) findViewById(R.id.music);
        signbtn = (Button) findViewById(R.id.sing);
        foodbtn = (Button) findViewById(R.id.foodDrinks);
        businessbtn = (Button) findViewById(R.id.business);
        array = new String[3];
        Arrays.fill(array, "");

    }




    public void onCofirm_clicked(View caller)
    {
        newPreference = getSharedPreferences("details", Context.MODE_PRIVATE);
        String email = newPreference.getString("email", null);
        RequestQueue requestQueue;
        String SUBMIT_URL =" https://studev.groept.be/api/a21pt206/accountInterestIole/";

        requestQueue = Volley.newRequestQueue(this);

        String requestURL = SUBMIT_URL  +
                array[0] +"/" +
                array[1] + "/" +
                array[2] + "/" +
                email;



        Log.d("Database","creating response");

        StringRequest submitRequest = new StringRequest(Request.Method.GET, requestURL,
                new Response.Listener<String>()
                {
                    @Override
                    public void onResponse(String response)
                    {
                        Log.d("Database","response received");
                        Intent intent = new Intent(caller.getContext(), AccountActivity.class);
                        startActivity(intent);
                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        requestQueue.add(submitRequest);
        Log.d("Database","response sent");


    }



    public boolean isAlreadyThere(Button btn)
    {
        for (int i = 0; i<array.length; i++ )
        {
            if(array[i]==btn.getText().toString())
            {
                return true;
            }
        }
        return false;
    }

    public int whereIsIt(Button btn)
    {
        for (int i = 0; i<array.length; i++ )
        {
            if(array[i]==btn.getText().toString())
            {
                return i;
            }
        }
        return -1;

    }

    public void changeColorSelected(Button btn)
    {
        Drawable buttonDrawable = btn.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorSelected));
        btn.setBackground(buttonDrawable);

    }


    public void changeColorUnSelected(Button btn)
    {
        Drawable buttonDrawable = btn.getBackground();
        buttonDrawable = DrawableCompat.wrap(buttonDrawable);
        DrawableCompat.setTint(buttonDrawable, getResources().getColor(R.color.colorUnSelected));
        btn.setBackground(buttonDrawable);

    }




    public void politicsBtn_clicked   (View caller)
    {
        boolean check=false;
        if(isAlreadyThere(politicsbtn))
        {
            changeColorUnSelected(politicsbtn);
            array[whereIsIt(politicsbtn)]="";
            check=true;
        }

        int b=0;
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }

        if (b==3){}

        else if(!check)
        {
           changeColorSelected(politicsbtn);
            int a=0;

            for(int i = 0; i<array.length; i++ )

            {
                if(array[i].equals("") && a==0)
                {
                    array[i]=politicsbtn.getText().toString();
                    a=1;
                }
                else{}
            }}
    }

    public void musicBtn_clicked   (View caller)
    {
        boolean check=false;
        if(isAlreadyThere(musicbtn)==true)
        {
            changeColorUnSelected(musicbtn);
            array[whereIsIt(musicbtn)]="";
            check=true;
        }

        int b=0;
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
        else if(!check)
        {

            changeColorSelected(musicbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=musicbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }




    public void extremeSportsbtn_clicked   (View caller)
    {
        boolean check=false;
        if(isAlreadyThere(extremesportsbtn)==true)
        {
            changeColorUnSelected(extremesportsbtn);
            array[whereIsIt(extremesportsbtn)]="";
            check=true;
        }
        int b=0;
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }




        if (b==3){}
        else if(!check)
        {
          changeColorSelected(extremesportsbtn);
          int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=extremesportsbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }


    public void foodDrinksBtn_clicked   (View caller)
    {
        boolean check = false;
        if(isAlreadyThere(foodbtn)==true)
        {
            changeColorUnSelected(foodbtn);
            array[whereIsIt(foodbtn)]="";
            check=true;
        }

        int b=0;
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
         else if(!check){
        changeColorSelected(foodbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=foodbtn.getText().toString();
                a=1;
            }
            else{}
        }}

    }


    public void singBtn_Clicked   (View caller)
    {
        boolean check=false;
        int b=0;
        if(isAlreadyThere(signbtn)==true)
        {
            changeColorUnSelected(signbtn);
            array[whereIsIt(signbtn)]="";
            check=true;
        }
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}

        else if(!check) {
       changeColorSelected(signbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=signbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }



    public void businessBtn_Clicked   (View caller)
    {
        boolean check=false;
        int b=0;
        if(isAlreadyThere(businessbtn)==true)
        {
            changeColorUnSelected(businessbtn);
            array[whereIsIt(businessbtn)]="";
            check=true;
        }
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
         else if(!check){
        changeColorSelected(businessbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=businessbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }







    public void onSports_clicked   (View caller)
    {
        boolean check=false;
        int b=0;

        if(isAlreadyThere(sportsbtn)==true)
        {
            changeColorUnSelected(sportsbtn);
            array[whereIsIt(sportsbtn)]="";
            check=true;
        }
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
         else if(!check) {
        changeColorSelected(sportsbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )

        {
            if(array[i].equals("") && a==0)
            {
                array[i]=sportsbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }

    public void actingBtn_Clicked   (View caller)
    {
        boolean check=false;
        int b=0;
        if(isAlreadyThere(actingbtn)==true)
        {
            changeColorUnSelected(actingbtn);
            array[whereIsIt(actingbtn)]="";
            check=true;
        }

        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}

         else if(!check){

             changeColorSelected(actingbtn);

        int a=0;

        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("")&& a==0)
            {
                array[i]= actingbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }

    public void moviesBtn_Clicked   (View caller)
    {
        boolean check = false;
        if(isAlreadyThere(moviesbtn)==true)
        {
            changeColorUnSelected(moviesbtn);
            array[whereIsIt(moviesbtn)]="";
            check=true;
        }

        int b=0;
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
         else if(!check ){
        changeColorSelected(moviesbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("") && a==0)
            {
                array[i]= moviesbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }

    public void onArtBtn_Clicked  (View caller)
    {
        boolean check=false;
        int b=0;

        if(isAlreadyThere(artbtn)==true)
        {
            changeColorUnSelected(artbtn);
            array[whereIsIt(artbtn)]="";
            check=true;
        }
        for(int i = 0; i<array.length; i++ )

        {
            if(!array[i].equals(""))
            {
                b=b+1;
            }

        }



        if (b==3){}
        else if(!check){
       changeColorSelected(artbtn);
        int a=0;

        for(int i = 0; i<array.length; i++ )
        {
            if(array[i].equals("") && a==0)
            {

                array[i]=  artbtn.getText().toString();
                a=1;
            }
            else{}
        }}
    }








}

