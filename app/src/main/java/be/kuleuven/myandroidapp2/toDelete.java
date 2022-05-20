/*
package be.kuleuven.myandroidapp2;

import com.android.volley.toolbox.StringRequest;

public class toDelete {
    public void onBtnCreateJob(View caller) {

        //Start an animating progress widget
        progressDialog = new ProgressDialog(freelancerCreateJobForm.this);
        progressDialog.setMessage("Uploading, please wait...");
        progressDialog.show();


        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, stream);
        byte[] byteFormat = stream.toByteArray();

        // Get the Base64 string
        String imageString = Base64.encodeToString(byteFormat, Base64.NO_WRAP);


        StringRequest submitRequest = new StringRequest (Request.Method.POST, POST_URL,  new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Turn the progress widget off
                progressDialog.dismiss();
//                Toast.makeText(freelancerCreateJobForm.this, "Post request executed", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
//                Toast.makeText(freelancerCreateJobForm.this, "Post request failed", Toast.LENGTH_LONG).show();
            }
        }) { //NOTE THIS PART: here we are passing the parameter to the webservice, NOT in the URL!
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                EditText job = (EditText) findViewById(R.id.textJob);
                EditText price = (EditText) findViewById(R.id.textPrice);
                EditText description = (EditText) findViewById(R.id.textDesciption);

                newPreference = getSharedPreferences("details", MODE_PRIVATE);
                String firstName = newPreference.getString("firstname",null);
                String lastName = newPreference.getString("lastname",null);
                String city = newPreference.getString("city",null);
                String country = newPreference.getString("country",null);
                String userID = newPreference.getString("id",null);

                System.out.println(String.valueOf(job.getText()) + job.getText());
                Map<String, String> params = new HashMap<String, String>();

                if (job.getText().toString().equals("") || price.getText().equals("") || description.getText().equals("")) {
//                    progressDialog.dismiss();
                    Toast.makeText(freelancerCreateJobForm.this, "Please enter all info", Toast.LENGTH_SHORT).show();
                } else {
                    params.put("image", imageString);
                    params.put("name", firstName);
                    params.put("job", String.valueOf(job.getText()));
                    params.put("price", String.valueOf(price.getText()));
                    params.put("familyname", lastName);
                    params.put("city", city);
                    params.put("country", country);
                    params.put("jobdescription", String.valueOf(description.getText()));
                    params.put("userid",userID);
                    return params;
                }
                return params;
            }
        };

        requestQueue.add(submitRequest);




    }
}
*/
////
/*
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
                //requestQueue = Volley.newRequestQueue(this);

                //Bundle info = getIntent().getExtras();

                String requestURL = SUBMIT_URL  +
                "0"  +"/" +
                description + "/" +titl+
                "/"+ imageString +
                "/" +
                "0" ;
                Log.d("Database","creating response");
                System.out.println("Hey");
                StringRequest submitRequest = new StringRequest(Request.Method.POST, requestURL,
                new Response.Listener<String>() {
@Override
public void onResponse(String response) {
        System.out.println("IVAN");
        Log.d("Database","response received");
        pd.dismiss();
        Toast.makeText(getContext(), "Published", Toast.LENGTH_LONG).show();
        title.setText("");
        des.setText("");
        image.setImageURI(null);
        imageuri = null;
        startActivity(new Intent(getContext(), Dash.class)); //change was made
        getActivity().finish();
        }
        },

        new Response.ErrorListener() {
@Override
public void onErrorResponse(VolleyError error) {
        System.out.println("IVAN2");
        System.out.println(error);
        pd.dismiss();
        Toast.makeText(getContext(), "Failed", Toast.LENGTH_LONG).show();
        }
        }

        );

        System.out.println("here");
        requestQueue.add(submitRequest);
        System.out.println("here2");
        Log.d("Database","response sent");

 */